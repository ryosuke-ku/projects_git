package com.netappsid.observable;

import java.util.Collection;
import java.util.Iterator;

import com.google.common.collect.ImmutableList;

abstract class AbstractObservableCollectionDecorator<E, T extends Collection<E>> implements ObservableCollectionCollection<E>,
		InternalObservableCollection<E, T>
{
	private final T internal;
	private transient InternalObservableCollectionSupport<E> support;

	public AbstractObservableCollectionDecorator(T source, InternalObservableCollectionSupport<E> support)
	{
		this.internal = source;
		this.support = support;
	}

	public AbstractObservableCollectionDecorator(T source)
	{
		this.internal = source;
	}

	@Override
	public int size()
	{
		return internal.size();
	}

	@Override
	public boolean isEmpty()
	{
		return internal.isEmpty();
	}

	@Override
	public boolean contains(Object o)
	{
		return internal.contains(o);
	}

	@Override
	public Iterator<E> iterator()
	{
		return new ObservableIterator<E>(internal.iterator(), getSupport());
	}

	@Override
	public Object[] toArray()
	{
		return internal.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a)
	{
		return internal.toArray(a);
	}

	@Override
	public boolean containsAll(Collection<?> c)
	{
		return internal.containsAll(c);
	}

	@Override
	public boolean equals(Object o)
	{
		return internal.equals(o);
	}

	@Override
	public int hashCode()
	{
		return internal.hashCode();
	}

	@Override
	public void executeBatchAction(BatchAction action)
	{
		getSupport().createSnapshot();
		action.execute(internal);
		getSupport().fireCollectionChangeEvent();
	}

	@Override
	public boolean add(E e)
	{
		getSupport().createSnapshot();
		final boolean result = internal.add(e);
		getSupport().fireCollectionChangeEvent();
		return result;
	}

	@Override
	public boolean remove(Object o)
	{
		getSupport().createSnapshot();
		final boolean result = internal.remove(o);
		getSupport().fireCollectionChangeEvent();
		return result;
	}

	@Override
	public boolean addAll(Collection<? extends E> c)
	{
		getSupport().createSnapshot();
		final boolean result = internal.addAll(c);
		getSupport().fireCollectionChangeEvent();
		return result;
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		getSupport().createSnapshot();
		final boolean result = internal.retainAll(c);
		getSupport().fireCollectionChangeEvent();
		return result;
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		getSupport().createSnapshot();
		final boolean result = internal.removeAll(c);
		getSupport().fireCollectionChangeEvent();
		return result;
	}

	@Override
	public void clear()
	{
		getSupport().createSnapshot();
		internal.clear();
		getSupport().fireCollectionChangeEvent();
	}

	@Override
	public void addCollectionChangeListener(CollectionChangeListener<E> listener)
	{
		getSupport().addCollectionChangeListener(listener);
	}

	@Override
	public void removeCollectionChangeListener(CollectionChangeListener<E> listener)
	{
		getSupport().removeCollectionChangeListener(listener);
	}

	@Override
	public ImmutableList<CollectionChangeListener<E>> getCollectionChangeListeners()
	{
		return getSupport().getCollectionChangeListeners();
	}

	@Override
	public InternalObservableCollectionSupport<E> getSupport()
	{
		if (support == null)
		{
			this.support = newSupport();
		}

		return support;
	}

	/**
	 * @return
	 */
	protected abstract InternalObservableCollectionSupport<E> newSupport();

	protected abstract T copyOf(T internal);

	@Override
	public T copyInternal()
	{
		return copyOf(internal);
	}

	@Override
	public void apply(CollectionDifference<E> difference)
	{
		for (E added : difference.getAdded())
		{
			add(added);
		}

		for (E removed : difference.getRemoved())
		{
			remove(removed);
		}
	}

	@Override
	public void unapply(CollectionDifference<E> difference)
	{
		for (E added : difference.getAdded())
		{
			remove(added);
		}

		for (E removed : difference.getRemoved())
		{
			add(removed);
		}
	}

	@Override
	public String toString()
	{
		return internal.toString();
	}
}

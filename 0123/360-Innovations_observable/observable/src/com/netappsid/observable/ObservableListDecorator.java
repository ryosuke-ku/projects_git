package com.netappsid.observable;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import com.google.common.collect.ImmutableList;
import com.netappsid.observable.internal.ListObservableCollectionSupport;

class ObservableListDecorator<E> extends AbstractObservableCollectionDecorator<E, List<E>> implements ObservableList<E>,
		InternalObservableCollection<E, List<E>>

{
	private final List<E> internal;

	ObservableListDecorator(List<E> source)
	{
		super(source);
		this.internal = source;
	}

	@Override
	public void add(int index, E element)
	{
		getSupport().createSnapshot();
		internal.add(index, element);
		getSupport().fireCollectionChangeEvent(index);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c)
	{
		getSupport().createSnapshot();
		boolean result = internal.addAll(index, c);
		getSupport().fireCollectionChangeEvent(index);
		return result;
	}

	@Override
	public E get(int index)
	{
		return internal.get(index);
	}

	@Override
	public int indexOf(Object o)
	{
		return internal.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o)
	{
		return internal.lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator()
	{
		return new ObservableListIterator<E>(internal.listIterator(), getSupport());
	}

	@Override
	public ListIterator<E> listIterator(int index)
	{
		return new ObservableListIterator<E>(internal.listIterator(index), getSupport());
	}

	@Override
	public E remove(int index)
	{
		getSupport().createSnapshot();
		final E element = internal.remove(index);
		getSupport().fireCollectionChangeEvent(index);
		return element;
	}

	@Override
	public boolean remove(Object o)
	{
		getSupport().createSnapshot();
		final int index = internal.indexOf(o);
		final boolean result = internal.remove(o);
		getSupport().fireCollectionChangeEvent(index);
		return result;
	}

	@Override
	public E set(int index, E element)
	{
		getSupport().createSnapshot();
		final E oldElement = internal.set(index, element);
		getSupport().fireCollectionChangeEvent(index);
		return oldElement;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex)
	{
		return new ObservableListDecorator<E>(internal.subList(fromIndex, toIndex));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.AbstractObservableCollectionDecorator#copyOf(java.util.Collection)
	 */
	@Override
	protected List<E> copyOf(List<E> internal)
	{
		return ImmutableList.copyOf(internal);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.AbstractObservableCollectionDecorator#newSupport()
	 */
	@Override
	protected InternalObservableCollectionSupport<E> newSupport()
	{
		return new ListObservableCollectionSupport<E, ObservableListDecorator<E>>(this);
	}
}

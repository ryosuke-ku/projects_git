package com.netappsid.observable.internal;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.google.common.collect.ImmutableList;
import com.netappsid.observable.BatchAction;
import com.netappsid.observable.CollectionChangeListener;
import com.netappsid.observable.CollectionDifference;
import com.netappsid.observable.InternalObservableCollection;
import com.netappsid.observable.InternalObservableCollectionSupport;
import com.netappsid.observable.LazyInitializer;
import com.netappsid.observable.ObservableCollections;
import com.netappsid.observable.ObservableList;

public class LazyInitializerList<E> implements ObservableList<E>, Serializable, InternalObservableCollection<E, List<E>>
{

	private ObservableList<E> initilized;
	private final LazyInitializer<E> lazyInitializer;

	public LazyInitializerList(LazyInitializer<E> lazyInitializer)
	{
		this.lazyInitializer = lazyInitializer;
	}

	@Override
	public void addCollectionChangeListener(CollectionChangeListener<E> listener)
	{
		getInitilized().addCollectionChangeListener(listener);
	}

	@Override
	public void removeCollectionChangeListener(CollectionChangeListener<E> listener)
	{
		getInitilized().removeCollectionChangeListener(listener);
	}

	@Override
	public int size()
	{
		return getInitilized().size();
	}

	@Override
	public boolean isEmpty()
	{
		return getInitilized().isEmpty();
	}

	@Override
	public boolean contains(Object o)
	{
		return getInitilized().contains(o);
	}

	@Override
	public Iterator<E> iterator()
	{
		return getInitilized().iterator();
	}

	@Override
	public Object[] toArray()
	{
		return getInitilized().toArray();
	}

	@Override
	public <T> T[] toArray(T[] a)
	{
		return getInitilized().toArray(a);
	}

	@Override
	public boolean add(E e)
	{
		return getInitilized().add(e);
	}

	@Override
	public boolean remove(Object o)
	{
		return getInitilized().remove(o);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c)
	{
		return getInitilized().addAll(index, c);
	}

	@Override
	public boolean containsAll(Collection<?> c)
	{
		return getInitilized().containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c)
	{
		return getInitilized().addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		return getInitilized().removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		return getInitilized().retainAll(c);
	}

	@Override
	public void clear()
	{
		getInitilized().clear();
	}

	@Override
	public boolean equals(Object o)
	{
		return getInitilized().equals(o);
	}

	@Override
	public E get(int index)
	{
		return getInitilized().get(index);
	}

	@Override
	public E set(int index, E element)
	{
		return getInitilized().set(index, element);
	}

	@Override
	public void add(int index, E element)
	{
		getInitilized().add(index, element);
	}

	@Override
	public int hashCode()
	{
		return getInitilized().hashCode();
	}

	@Override
	public E remove(int index)
	{
		return getInitilized().remove(index);
	}

	@Override
	public int indexOf(Object o)
	{
		return getInitilized().indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o)
	{
		return getInitilized().lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator()
	{
		return getInitilized().listIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index)
	{
		return getInitilized().listIterator(index);
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex)
	{
		return getInitilized().subList(fromIndex, toIndex);
	}

	@Override
	public void executeBatchAction(BatchAction action)
	{
		getInitilized().executeBatchAction(action);
	}

	@Override
	public ImmutableList<CollectionChangeListener<E>> getCollectionChangeListeners()
	{
		return getInitilized().getCollectionChangeListeners();
	}

	private ObservableList<E> getInitilized()
	{
		if (initilized == null)
		{
			List<E> newList = lazyInitializer.initialize();

			if (newList instanceof ObservableList)
			{
				initilized = (ObservableList<E>) newList;
			}
			else
			{
				initilized = ObservableCollections.decorateList(newList);
			}
		}

		return initilized;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollection#copy()
	 */
	@Override
	public List<E> copyInternal()
	{
		return ((InternalObservableCollection<E, List<E>>) initilized).copyInternal();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.internal.InternalObservableCollection#getSupport()
	 */
	@Override
	public InternalObservableCollectionSupport<E> getSupport()
	{
		return ((InternalObservableCollection<E, List<E>>) initilized).getSupport();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollection#apply(com.netappsid.observable.CollectionDifference)
	 */
	@Override
	public void apply(CollectionDifference<E> difference)
	{
		initilized.apply(difference);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollection#unapply(com.netappsid.observable.CollectionDifference)
	 */
	@Override
	public void unapply(CollectionDifference<E> difference)
	{
		initilized.unapply(difference);
	}
}

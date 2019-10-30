/**
 * 
 */
package com.netappsid.observable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

/**
 * @author xjodoin
 * @author NetAppsID inc.
 * 
 * @version
 * 
 */
public class EmptyObservableCollectionSupport<E> implements ObservableCollectionSupport<E>
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollectionSupport#fireCollectionChangeEvent(com.netappsid.observable.CollectionChangeEvent)
	 */
	@Override
	public void fireCollectionChangeEvent(CollectionChangeEvent<E> collectionChangeEvent)
	{}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollectionSupport#newCollectionChangeEvent(com.netappsid.observable.CollectionDifference)
	 */
	@Override
	public CollectionChangeEvent<E> newCollectionChangeEvent(CollectionDifference<E> difference)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollectionSupport#newCollectionChangeEvent(com.netappsid.observable.CollectionDifference, java.lang.Object)
	 */
	@Override
	public CollectionChangeEvent<E> newCollectionChangeEvent(CollectionDifference<E> difference, Object index)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollectionSupport#addCollectionChangeListener(com.netappsid.observable.CollectionChangeListener)
	 */
	@Override
	public void addCollectionChangeListener(CollectionChangeListener listener)
	{}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollectionSupport#removeCollectionChangeListener(com.netappsid.observable.CollectionChangeListener)
	 */
	@Override
	public void removeCollectionChangeListener(CollectionChangeListener listener)
	{}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollectionSupport#getCollectionChangeListeners()
	 */
	@Override
	public ImmutableList<CollectionChangeListener<E>> getCollectionChangeListeners()
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollectionSupport#fireCollectionChangeEvent(com.google.common.collect.ImmutableSet,
	 * com.google.common.collect.ImmutableSet)
	 */
	@Override
	public void fireCollectionChangeEvent(ImmutableSet<E> oldSet, ImmutableSet<E> newSet)
	{}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollectionSupport#fireCollectionChangeEvent(com.google.common.collect.ImmutableList,
	 * com.google.common.collect.ImmutableList)
	 */
	@Override
	public void fireCollectionChangeEvent(ImmutableList<E> oldList, ImmutableList<E> newList)
	{}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollectionSupport#fireCollectionChangeEvent(com.google.common.collect.ImmutableList,
	 * com.google.common.collect.ImmutableList, int)
	 */
	@Override
	public void fireCollectionChangeEvent(ImmutableList<E> oldList, ImmutableList<E> newList, int index)
	{}

}

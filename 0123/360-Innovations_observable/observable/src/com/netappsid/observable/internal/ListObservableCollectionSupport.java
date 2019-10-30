/**
 * 
 */
package com.netappsid.observable.internal;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.netappsid.observable.AbstractObservableCollectionSupport;
import com.netappsid.observable.CollectionChangeEvent;
import com.netappsid.observable.InternalObservableCollection;
import com.netappsid.observable.ListDifference;
import com.netappsid.observable.ObservableList;

/**
 * @author xjodoin
 * @author NetAppsID inc.
 * 
 * @version
 * 
 */
public class ListObservableCollectionSupport<E, T extends ObservableList<E> & InternalObservableCollection<E, List<E>>> extends
		AbstractObservableCollectionSupport<E, List<E>>
{

	/**
	 * @param source
	 */
	public ListObservableCollectionSupport(T source)
	{
		super(source);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.AbstractObservableCollectionSupport#createCollectionChangeEvent(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	protected CollectionChangeEvent<E> createCollectionChangeEvent(Object index)
	{
		ImmutableList<E> oldList = ImmutableList.copyOf(getSnapshot());
		ImmutableList<E> newList = ImmutableList.copyOf(takeSnapshot());
		ListDifference<E> difference = ListDifference.difference(oldList, newList);
		return newCollectionChangeEvent(difference, index);
	}

}

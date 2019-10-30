/**
 * 
 */
package com.netappsid.observable.internal;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.netappsid.observable.AbstractObservableCollectionSupport;
import com.netappsid.observable.CollectionChangeEvent;
import com.netappsid.observable.InternalObservableCollection;
import com.netappsid.observable.ObservableSet;

/**
 * @author xjodoin
 * @author NetAppsID inc.
 * 
 * @version
 * 
 */
public class SetObservableCollectionSupport<E, T extends ObservableSet<E> & InternalObservableCollection<E, Set<E>>> extends
		AbstractObservableCollectionSupport<E, Set<E>>
{

	/**
	 * @param source
	 */
	public SetObservableCollectionSupport(T source)
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
		Set<E> oldSet = ImmutableSet.copyOf(getSnapshot());
		Set<E> newSet = ImmutableSet.copyOf(takeSnapshot());
		final ImmutableSet<E> added = ImmutableSet.copyOf(Sets.difference(newSet, oldSet));
		final ImmutableSet<E> removed = ImmutableSet.copyOf(Sets.difference(oldSet, newSet));
		return newCollectionChangeEvent(new SetDifference(removed, added));
	}

}

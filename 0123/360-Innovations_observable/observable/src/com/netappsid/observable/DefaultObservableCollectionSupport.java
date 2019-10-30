/**
 * 
 */
package com.netappsid.observable;

import static com.google.common.collect.Lists.*;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.netappsid.observable.internal.SetDifference;

/**
 * @author xjodoin
 * @author NetAppsID inc.
 * 
 * @version
 * 
 */
public class DefaultObservableCollectionSupport<E> implements ObservableCollectionSupport<E>
{
	private final ObservableCollection<E> source;
	private final List<CollectionChangeListener<E>> listeners = newArrayList();

	public DefaultObservableCollectionSupport(ObservableCollection<E> source)
	{
		this.source = source;
	}

	@Override
	public void addCollectionChangeListener(CollectionChangeListener listener)
	{
		listeners.add(listener);
	}

	@Override
	public void removeCollectionChangeListener(CollectionChangeListener listener)
	{
		listeners.remove(listener);
	}

	@Override
	public ImmutableList<CollectionChangeListener<E>> getCollectionChangeListeners()
	{
		return ImmutableList.copyOf(listeners);
	}

	@Override
	public CollectionChangeEvent newCollectionChangeEvent(CollectionDifference<E> difference)
	{
		return new CollectionChangeEvent<E>(source, difference);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollectionSupport#newCollectionChangeEvent(com.netappsid.observable.CollectionDifference, java.lang.Object)
	 */
	@Override
	public CollectionChangeEvent<E> newCollectionChangeEvent(CollectionDifference<E> difference, Object index)
	{
		return new CollectionChangeEvent<E>(source, difference, index);
	}

	@Override
	public void fireCollectionChangeEvent(ImmutableSet<E> oldSet, ImmutableSet<E> newSet)
	{
		final ImmutableSet<E> added = ImmutableSet.copyOf(Sets.difference(newSet, oldSet));
		final ImmutableSet<E> removed = ImmutableSet.copyOf(Sets.difference(oldSet, newSet));
		fireCollectionChangeEvent(newCollectionChangeEvent(new SetDifference(removed, added)));
	}

	@Override
	public void fireCollectionChangeEvent(ImmutableList<E> oldList, ImmutableList<E> newList)
	{
		final CollectionDifference<E> difference = ListDifference.difference(oldList, newList);
		fireCollectionChangeEvent(newCollectionChangeEvent(difference));
	}

	@Override
	public void fireCollectionChangeEvent(ImmutableList<E> oldList, ImmutableList<E> newList, int index)
	{
		final CollectionDifference<E> difference = ListDifference.difference(oldList, newList);
		fireCollectionChangeEvent(newCollectionChangeEvent(difference, index));
	}

	@Override
	public void fireCollectionChangeEvent(CollectionChangeEvent<E> event)
	{
		if (event.getDifference().hasDifference())
		{
			for (CollectionChangeListener listener : listeners)
			{
				listener.onCollectionChange(event);
			}
		}
	}

}

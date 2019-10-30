package com.netappsid.observable;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.netappsid.observable.internal.SetObservableCollectionSupport;

public class ObservableSetDecorator<E> extends AbstractObservableCollectionDecorator<E, Set<E>> implements ObservableSet<E>
{
	public ObservableSetDecorator(Set<E> source, InternalObservableCollectionSupport<E> support)
	{
		super(source, support);
	}

	public ObservableSetDecorator(Set<E> source)
	{
		super(source);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.AbstractObservableCollectionDecorator#copyOf(java.util.Collection)
	 */
	@Override
	protected Set<E> copyOf(Set<E> internal)
	{
		return ImmutableSet.copyOf(internal);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.AbstractObservableCollectionDecorator#newSupport()
	 */
	@Override
	protected InternalObservableCollectionSupport<E> newSupport()
	{
		return new SetObservableCollectionSupport<E, ObservableSetDecorator<E>>(this);
	}
}

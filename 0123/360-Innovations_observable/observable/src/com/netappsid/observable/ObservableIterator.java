package com.netappsid.observable;

import java.util.Iterator;

import com.google.common.collect.ImmutableList;

public class ObservableIterator<E> implements Iterator<E>
{
	private final Iterator<E> internal;
	private final ObservableCollectionSupport<E> observableSupport;
	private E next;

	public ObservableIterator(Iterator<E> sourceIterator, ObservableCollectionSupport<E> sourceSupport)
	{
		this.internal = sourceIterator;
		this.observableSupport = sourceSupport;
	}

	@Override
	public boolean hasNext()
	{
		return internal.hasNext();
	}

	@Override
	public E next()
	{
		next = internal.next();
		return next;
	}

	@Override
	public void remove()
	{
		internal.remove();
		observableSupport.fireCollectionChangeEvent(observableSupport.newCollectionChangeEvent(new ListDifference<E>(ImmutableList.of(next), ImmutableList
				.<E> of())));
	}
}

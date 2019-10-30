package com.netappsid.observable;

import java.util.ListIterator;

import com.google.common.collect.ImmutableList;

public final class ObservableListIterator<E> implements ListIterator<E>
{
	private final ListIterator<E> internal;
	private final ObservableCollectionSupport<E> support;
	private E element;
	private int index;

	public ObservableListIterator(ListIterator<E> sourceIterator, ObservableCollectionSupport<E> sourceSupport)
	{
		this.internal = sourceIterator;
		this.support = sourceSupport;
		this.index = -1;
	}

	@Override
	public void add(E e)
	{
		internal.add(e);
		final int eventIndex = internal.previousIndex() != -1 ? internal.previousIndex() : 0;
		final CollectionChangeEvent event = support.newCollectionChangeEvent(new ListDifference<E>(ImmutableList.<E> of(), ImmutableList.of(e)), eventIndex);
		support.fireCollectionChangeEvent(event);
	}

	@Override
	public boolean hasNext()
	{
		return internal.hasNext();
	}

	@Override
	public boolean hasPrevious()
	{
		return internal.hasPrevious();
	}

	@Override
	public E next()
	{
		index = nextIndex();
		element = internal.next();
		return element;
	}

	@Override
	public int nextIndex()
	{
		return internal.nextIndex();
	}

	@Override
	public E previous()
	{
		index = previousIndex();
		element = internal.previous();
		return element;
	}

	@Override
	public int previousIndex()
	{
		return internal.previousIndex();
	}

	@Override
	public void remove()
	{
		internal.remove();
		support.fireCollectionChangeEvent(support.newCollectionChangeEvent(new ListDifference<E>(ImmutableList.of(element), ImmutableList.<E> of()), index));
	}

	@Override
	public void set(E e)
	{
		internal.set(e);
		support.fireCollectionChangeEvent(support.newCollectionChangeEvent(new ListDifference<E>(ImmutableList.of(element), ImmutableList.of(e)), index));
	}
}

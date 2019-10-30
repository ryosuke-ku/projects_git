package com.netappsid.collection.internal;

import java.util.List;
import java.util.ListIterator;

public class ListSetIterator<E> implements ListIterator<E>
{
	private E current;
	private final List<E> source;
	private final ListIterator<E> internal;

	public ListSetIterator(List<E> source, ListIterator<E> sourceIterator)
	{
		this.source = source;
		this.internal = sourceIterator;
	}

	public boolean hasNext()
	{
		return internal.hasNext();
	}

	public E next()
	{
		current = internal.next(); 
		return current;
	}

	public boolean hasPrevious()
	{
		return internal.hasPrevious();
	}

	public E previous()
	{
		current = internal.previous(); 
		return current;
	}

	public int nextIndex()
	{
		return internal.nextIndex();
	}

	public int previousIndex()
	{
		return internal.previousIndex();
	}

	public void remove()
	{
		internal.remove();
	}

	public void set(E e)
	{
		if (source.contains(e) && (current != null && !current.equals(e)))
		{
			throw new IllegalArgumentException("element " + e.toString() + " already present.");
		}

		internal.set(e);
	}

	public void add(E e)
	{
		if (source.contains(e))
		{
			throw new IllegalArgumentException("element " + e.toString() + " already present.");
		}

		internal.add(e);
	}
}

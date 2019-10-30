package com.netappsid.observable.internal;

import java.util.ListIterator;

import com.google.common.base.Predicate;

public class ObservableFilteredListIterator<E> implements ListIterator<E>
{
	private final ListIterator<E> source;
	private final Predicate<? super E> predicate;
	private int index = -1;

	public ObservableFilteredListIterator(ListIterator<E> source, Predicate<? super E> predicate)
	{
		this.source = source;
		this.predicate = predicate;
	}

	@Override
	public boolean hasNext()
	{
		if (source.hasNext())
		{
			try
			{
				return predicate.apply(source.next()) ? true : hasNext();
			}
			finally
			{
				source.previous();
			}
		}
		else
		{
			return false;
		}
	}

	@Override
	public E next()
	{
		index++;
		return recursiveNext();
	}

	@Override
	public boolean hasPrevious()
	{
		if (source.hasPrevious())
		{
			try
			{
				return predicate.apply(source.previous()) ? true : hasPrevious();
			}
			finally
			{
				source.next();
			}
		}
		else
		{
			return false;
		}
	}

	@Override
	public E previous()
	{
		index--;
		return recursivePrevious();
	}
	
	@Override
	public int nextIndex()
	{
		return index + 1;
	}

	@Override
	public int previousIndex()
	{
		return index;
	}

	@Override
	public void remove()
	{
		source.remove();
		index--;
	}

	@Override
	public void set(E e)
	{
		source.set(e);
		
		if (!predicate.apply(e))
		{
			index--;
		}
	}

	@Override
	public void add(E e)
	{
		source.add(e);
		
		if (predicate.apply(e))
		{
			index++;
		}
	}
	
	private E recursiveNext()
	{
		final E next = source.next();
		return predicate.apply(next) ? next : recursiveNext();
	}
	
	private E recursivePrevious()
	{
		final E previous = source.previous();
		return predicate.apply(previous) ? previous : recursivePrevious();
	}
}

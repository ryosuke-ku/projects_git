package com.netappsid.observable.internal;

import com.google.common.collect.ImmutableCollection;
import com.netappsid.observable.CollectionDifference;

public class AbstractCollectionDifference<E> implements CollectionDifference<E>
{

	protected final ImmutableCollection<E> removed;
	protected final ImmutableCollection<E> added;

	public AbstractCollectionDifference(ImmutableCollection<E> removed, ImmutableCollection<E> added)
	{
		this.removed = removed;
		this.added = added;
	}

	@Override
	public ImmutableCollection<E> getRemoved()
	{
		return removed;
	}

	@Override
	public ImmutableCollection<E> getAdded()
	{
		return added;
	}

	@Override
	public boolean hasDifference()
	{
		if (!removed.isEmpty() || !added.isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
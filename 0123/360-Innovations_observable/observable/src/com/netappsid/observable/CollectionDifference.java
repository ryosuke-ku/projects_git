package com.netappsid.observable;

import com.google.common.collect.ImmutableCollection;

public interface CollectionDifference<E>
{
	public ImmutableCollection<E> getRemoved();

	public ImmutableCollection<E> getAdded();

	public boolean hasDifference();
}
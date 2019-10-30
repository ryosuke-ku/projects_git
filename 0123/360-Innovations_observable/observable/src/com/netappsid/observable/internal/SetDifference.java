package com.netappsid.observable.internal;

import com.google.common.collect.ImmutableSet;

public class SetDifference<E> extends AbstractCollectionDifference<E>
{
	public SetDifference(ImmutableSet<E> removed, ImmutableSet<E> added)
	{
		super(removed, added);
	}
}

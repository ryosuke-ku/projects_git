/**
 * 
 */
package com.netappsid.observable.internal;

import java.util.Map.Entry;

import com.google.common.collect.ImmutableSet;

/**
 * @author xjodoin
 * @author NetAppsID inc.
 * 
 * @version
 * 
 */
public class MapDifference<K, V> extends AbstractCollectionDifference<Entry<K, V>>
{

	private final com.google.common.collect.MapDifference<K, V> difference;

	/**
	 * 
	 */
	public MapDifference(com.google.common.collect.MapDifference<K, V> difference)
	{
		super(ImmutableSet.copyOf(difference.entriesOnlyOnLeft().entrySet()), ImmutableSet.copyOf(difference.entriesOnlyOnRight().entrySet()));
		this.difference = difference;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.internal.AbstractCollectionDifference#hasDifference()
	 */
	@Override
	public boolean hasDifference()
	{
		return !difference.areEqual();
	}
}

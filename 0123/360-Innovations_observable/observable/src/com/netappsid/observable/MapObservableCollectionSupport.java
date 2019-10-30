/**
 * 
 */
package com.netappsid.observable;

import java.util.Map;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

/**
 * @author xjodoin
 * @author NetAppsID inc.
 * 
 * @version
 * 
 */
public class MapObservableCollectionSupport<K, V, T extends ObservableMap<K, V> & InternalObservableCollection<Map.Entry<K, V>, Map<K, V>>> extends
		AbstractObservableCollectionSupport<Map.Entry<K, V>, Map<K, V>>
{
	public MapObservableCollectionSupport(T source)
	{
		super(source);
	}

	@Override
	protected CollectionChangeEvent<Map.Entry<K, V>> createCollectionChangeEvent(Object index)
	{
		MapDifference difference = Maps.difference(getSnapshot(), takeSnapshot());
		com.netappsid.observable.internal.MapDifference mapDifference = new com.netappsid.observable.internal.MapDifference(difference);
		return newCollectionChangeEvent(mapDifference, index);
	}
}

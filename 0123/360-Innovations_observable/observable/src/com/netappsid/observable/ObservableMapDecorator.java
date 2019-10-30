/**
 * 
 */
package com.netappsid.observable;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

/**
 * @author xjodoin
 * @author NetAppsID inc.
 * 
 * @version
 * 
 */
public class ObservableMapDecorator<K, V> implements ObservableMap<K, V>, InternalObservableCollection<Map.Entry<K, V>, Map<K, V>>
{
	private Map<K, V> internal;
	private transient MapObservableCollectionSupport<K, V, ObservableMapDecorator<K, V>> support;

	/**
	 * 
	 */
	public ObservableMapDecorator()
	{}

	public ObservableMapDecorator(Map<K, V> source)
	{
		this.internal = source;
	}

	/**
	 * @param internal
	 *            the internal to set
	 */
	public void setInternal(Map<K, V> internal)
	{
		if (this.internal == null)
		{
			this.internal = internal;
		}
		else
		{
			throw new RuntimeException("the internal copllection is already set");
		}

	}

	/**
	 * @return
	 * @see java.util.Map#size()
	 */
	@Override
	public int size()
	{
		return internal.size();
	}

	/**
	 * @return
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty()
	{
		return internal.isEmpty();
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey(Object key)
	{
		return internal.containsKey(key);
	}

	/**
	 * @param value
	 * @return
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value)
	{
		return internal.containsValue(value);
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public V get(Object key)
	{
		return internal.get(key);
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public V put(K key, V value)
	{
		getSupport().createSnapshot();
		V result = internal.put(key, value);
		getSupport().fireCollectionChangeEvent(key);
		return result;
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public V remove(Object key)
	{
		getSupport().createSnapshot();
		V result = internal.remove(key);
		getSupport().fireCollectionChangeEvent(key);
		return result;
	}

	/**
	 * @param m
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends K, ? extends V> m)
	{
		getSupport().createSnapshot();
		internal.putAll(m);
		getSupport().fireCollectionChangeEvent();
	}

	/**
	 * 
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear()
	{
		getSupport().createSnapshot();
		internal.clear();
		getSupport().fireCollectionChangeEvent();
	}

	/**
	 * @return
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<K> keySet()
	{
		return new ObservableSetDecorator<K>(internal.keySet(), (InternalObservableCollectionSupport) getSupport());
	}

	/**
	 * @return
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<V> values()
	{
		return new ObservableCollectionDecorator(internal.values(), getSupport());
	}

	/**
	 * @return
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet()
	{
		return new ObservableSetDecorator<Map.Entry<K, V>>(internal.entrySet(), getSupport());
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.Map#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o)
	{
		return internal.equals(o);
	}

	/**
	 * @return
	 * @see java.util.Map#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return internal.hashCode();
	}

	@Override
	public void executeBatchAction(BatchAction action)
	{
		getSupport().createSnapshot();
		action.execute(internal);
		getSupport().fireCollectionChangeEvent();
	}

	@Override
	public MapObservableCollectionSupport<K, V, ObservableMapDecorator<K, V>> getSupport()
	{
		if (support == null)
		{
			this.support = new MapObservableCollectionSupport<K, V, ObservableMapDecorator<K, V>>(this);
		}

		return support;
	}

	@Override
	public void addCollectionChangeListener(CollectionChangeListener<Map.Entry<K, V>> listener)
	{
		getSupport().addCollectionChangeListener(listener);
	}

	@Override
	public void removeCollectionChangeListener(CollectionChangeListener<Map.Entry<K, V>> listener)
	{
		getSupport().removeCollectionChangeListener(listener);
	}

	@Override
	public ImmutableList<CollectionChangeListener<java.util.Map.Entry<K, V>>> getCollectionChangeListeners()
	{
		return getSupport().getCollectionChangeListeners();
	}

	@Override
	public Map<K, V> copyInternal()
	{
		return Maps.newHashMap(internal);
	}

	@Override
	public void apply(CollectionDifference<java.util.Map.Entry<K, V>> difference)
	{
		for (java.util.Map.Entry<K, V> added : difference.getAdded())
		{
			put(added.getKey(), added.getValue());
		}

		for (java.util.Map.Entry<K, V> removed : difference.getRemoved())
		{
			remove(removed.getKey());
		}
	}

	@Override
	public void unapply(CollectionDifference<java.util.Map.Entry<K, V>> difference)
	{
		for (java.util.Map.Entry<K, V> added : difference.getAdded())
		{
			remove(added.getKey());
		}

		for (java.util.Map.Entry<K, V> removed : difference.getRemoved())
		{
			put(removed.getKey(), removed.getValue());
		}
	}

	@Override
	public Iterator<Map.Entry<K, V>> iterator()
	{
		return entrySet().iterator();
	}

	@Override
	public String toString()
	{
		return internal.toString();
	}
}

package com.netappsid.observable;

import static com.google.common.collect.Lists.*;
import static com.google.common.collect.Maps.*;
import static com.google.common.collect.Sets.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.netappsid.collection.ArrayListSet;
import com.netappsid.collection.ListSet;
import com.netappsid.observable.internal.LazyInitializerList;

public class ObservableCollections
{
	private ObservableCollections()
	{

	}

	public static <E> ObservableSet<E> newObservableHashSet(E... elements)
	{
		return decorateSet(newHashSet(elements));
	}

	public static <K, E> ObservableMap<K, E> newObservableHashMap()
	{
		Map<K, E> newHashMap = newHashMap();
		return decorateMap(newHashMap);
	}

	/**
	 * @param newHashMap
	 * @return
	 */
	public static <K, E> ObservableMap<K, E> decorateMap(Map<K, E> source)
	{
		return new ObservableMapDecorator<K, E>(source);
	}

	public static <E> ObservableSet<E> newObservableHashSet(Iterator<E> iterator)
	{
		return decorateSet(newHashSet(iterator));
	}

	public static <E> ObservableList<E> newObservableArrayList(E... elements)
	{
		return decorateList(newArrayList(elements));
	}

	public static <E> ObservableList<E> newObservableArrayList(Iterator<E> iterator)
	{
		return decorateList(newArrayList(iterator));
	}

	public static <E> ObservableListSet<E> newObservableArrayListSet(E... elements)
	{
		return decorateListSet(ArrayListSet.of(elements));
	}

	public static <E> ObservableListSet<E> newObservableArrayListSet(Iterator<E> iterator)
	{
		return decorateListSet(ArrayListSet.copyOf(ImmutableList.copyOf(iterator)));
	}

	public static <E> ObservableList<E> decorateList(List<E> source)
	{
		return new ObservableListDecorator(source);
	}

	public static <E> ObservableSet<E> decorateSet(Set<E> source)
	{
		return new ObservableSetDecorator<E>(source);
	}

	public static <E> ObservableListSet<E> decorateListSet(ListSet<E> source)
	{
		return new ObservableListSetDecorator(source);
	}

	public static <E> ObservableList<E> newLazyInitializeObservableList(LazyInitializer<E> lazyInitializer)
	{
		return new LazyInitializerList<E>(lazyInitializer);
	}
}

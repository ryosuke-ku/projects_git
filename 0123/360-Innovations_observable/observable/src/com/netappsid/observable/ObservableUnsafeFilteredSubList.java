package com.netappsid.observable;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.netappsid.observable.internal.ObservableFilteredListIterator;

/**
 * If you think about using this class, think of something else.
 * 
 * Mixes 3 concerns into one list. - observable - filtered sublisting backed by source list - unsafe typing
 */
public class ObservableUnsafeFilteredSubList<E> extends ObservableUnsafeList<E> implements Serializable
{
	private final ObservableList source;
	private final BiMap<Integer, Integer> indexes;
	private Predicate<? super E> filterPredicate;

	ObservableUnsafeFilteredSubList(ObservableList<? super E> source, Predicate<? super E> predicate)
	{
		super(source);
		this.source = source;
		this.filterPredicate = predicate;
		this.indexes = HashBiMap.create();
		refreshIndexesMap();
		source.addCollectionChangeListener(new CollectionChangeHandler());
	}

	@Override
	public int size()
	{
		return indexes.size();
	}

	@Override
	public boolean isEmpty()
	{
		return indexes.isEmpty();
	}

	@Override
	public boolean contains(Object o)
	{
		return indexes.containsValue(super.indexOf(o));
	}

	@Override
	public Iterator<E> iterator()
	{
		return new ObservableFilteredListIterator<E>(super.listIterator(), filterPredicate);
	}

	@Override
	public Object[] toArray()
	{
		return ImmutableList.copyOf(Iterables.filter(source, filterPredicate)).toArray();
	}

	@Override
	public <T> T[] toArray(T[] a)
	{
		return (T[]) ImmutableList.copyOf(Iterables.filter(source, filterPredicate)).toArray(a);
	}

	@Override
	public boolean add(E e)
	{
		final int previousSize = indexes.size();
		super.add(e);
		return indexes.size() != previousSize;
	}

	@Override
	public boolean remove(Object o)
	{
		final int previousSize = indexes.size();
		super.remove(o);
		return indexes.size() != previousSize;
	}

	@Override
	public boolean containsAll(Collection<?> c)
	{
		for (Object o : c)
		{
			if (!contains(o))
			{
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> c)
	{
		final int previousSize = indexes.size();
		super.addAll(c);
		return indexes.size() != previousSize;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c)
	{
		final int previousSize = indexes.size();
		super.addAll(indexes.get(index), c);
		return indexes.size() != previousSize;
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		final int previousSize = indexes.size();
		super.removeAll(c);
		return indexes.size() != previousSize;
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		final int previousSize = indexes.size();
		super.retainAll(c);
		return indexes.size() != previousSize;
	}

	@Override
	public void clear()
	{
		super.clear();
	}

	@Override
	public E get(int index)
	{
		return super.get(indexes.get(index));
	}

	@Override
	public E set(int index, E element)
	{
		return super.set(indexes.get(index), element);
	}

	@Override
	public void add(int index, E element)
	{
		super.add(indexes.get(index), element);
	}

	@Override
	public E remove(int index)
	{
		return super.remove((int) indexes.get(index));
	}

	@Override
	public int indexOf(Object o)
	{
		final Integer filteredIndex = indexes.inverse().get(super.indexOf(o));
		return filteredIndex != null ? filteredIndex : -1;
	}

	@Override
	public int lastIndexOf(Object o)
	{
		final Integer filteredIndex = indexes.inverse().get(super.lastIndexOf(o));
		return filteredIndex != null ? filteredIndex : -1;
	}

	@Override
	public ListIterator<E> listIterator()
	{
		return new ObservableFilteredListIterator<E>(super.listIterator(), filterPredicate);
	}

	@Override
	public ListIterator<E> listIterator(int index)
	{
		return new ObservableFilteredListIterator<E>(super.listIterator(indexes.get(index)), filterPredicate);
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex)
	{
		final ObservableList<E> subList = (ObservableList<E>) super.subList(indexes.get(fromIndex), indexes.get(toIndex));

		subList.addCollectionChangeListener(new CollectionChangeHandler());
		return new ObservableUnsafeFilteredSubList<E>(subList, filterPredicate);
	}

	@Override
	public boolean equals(Object obj)
	{
		return ImmutableList.copyOf(Iterables.filter(source, filterPredicate)).equals(obj);
	}

	@Override
	public int hashCode()
	{
		return ImmutableList.copyOf(Iterables.filter(source, filterPredicate)).hashCode();
	}

	@Override
	public String toString()
	{
		return ImmutableList.copyOf(Iterables.filter(source, filterPredicate)).toString();
	}

	public void setFilter(Predicate<? super E> filterPredicate)
	{
		final ImmutableList<E> oldList = ImmutableList.copyOf(this);

		if (filterPredicate != null)
		{
			this.filterPredicate = filterPredicate;
		}
		else
		{
			this.filterPredicate = Predicates.alwaysTrue();
		}

		refreshIndexesMap();

		ListDifference<E> difference = ListDifference.difference(oldList, ImmutableList.copyOf(this));
		CollectionChangeEvent<E> newCollectionChangeEvent = getSupport().newCollectionChangeEvent(difference);
		getSupport().fireCollectionChangeEvent(newCollectionChangeEvent);
	}

	private void refreshIndexesMap()
	{
		indexes.clear();

		int filteredIndex = 0;
		for (int sourceIndex = 0; sourceIndex < super.size(); sourceIndex++)
		{
			if (filterPredicate.apply(super.get(sourceIndex)))
			{
				indexes.put(filteredIndex, sourceIndex);
				filteredIndex++;
			}
		}
	}

	private void fireFilteredEvent(CollectionChangeEvent<E> event)
	{
		final ImmutableList<E> filteredAdded = ImmutableList.copyOf(Iterables.filter(event.getAdded(), filterPredicate));
		final ImmutableList<E> filteredRemoved = ImmutableList.copyOf(Iterables.filter(event.getRemoved(), filterPredicate));
		Integer index = event.getIndex();
		index = index >= 0 ? findFilteredIndex(index) : -1;
		getSupport().fireCollectionChangeEvent(
				new CollectionChangeEvent(ObservableUnsafeFilteredSubList.this, new ListDifference<E>(filteredRemoved, filteredAdded), index));
	}

	private int findFilteredIndex(int sourceIndex)
	{
		final BiMap<Integer, Integer> inversed = indexes.inverse();
		return sourceIndex < 0 ? 0 : (inversed.containsKey(sourceIndex) ? inversed.get(sourceIndex) : findFilteredIndex(sourceIndex - 1));
	}

	private class CollectionChangeHandler implements CollectionChangeListener
	{
		@Override
		public void onCollectionChange(CollectionChangeEvent event)
		{
			refreshIndexesMap();
			fireFilteredEvent(event);
		}
	}

	public static <E> ObservableUnsafeFilteredSubList<E> of(ObservableList<? super E> source, Predicate<? super E> filterPredicate)
	{
		return new ObservableUnsafeFilteredSubList(source, filterPredicate);
	}
}

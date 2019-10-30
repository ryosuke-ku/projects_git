package com.netappsid.collection;

import static com.google.common.collect.Lists.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.google.common.collect.ImmutableSet;
import com.netappsid.collection.internal.ListSetIterator;

public class ArrayListSet<E> implements ListSet<E>, Serializable
{
	private final List<E> internal;

	ArrayListSet()
	{
		this.internal = newArrayList();
	}

	ArrayListSet(List<E> internal)
	{
		this.internal = internal;
	}

	@Override
	public int size()
	{
		return internal.size();
	}

	@Override
	public boolean isEmpty()
	{
		return internal.isEmpty();
	}

	@Override
	public boolean contains(Object o)
	{
		return internal.contains(o);
	}

	@Override
	public Iterator<E> iterator()
	{
		return internal.iterator();
	}

	@Override
	public Object[] toArray()
	{
		return internal.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a)
	{
		return internal.toArray(a);
	}

	@Override
	public boolean add(E e)
	{
		assertPossibleDuplication(e);
		return internal.add(e);
	}

	@Override
	public boolean remove(Object o)
	{
		return internal.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c)
	{
		return internal.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c)
	{
		assertPossibleDuplication(c);
		return internal.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c)
	{
		assertPossibleDuplication(c);
		return internal.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		return internal.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		return internal.retainAll(c);
	}

	@Override
	public void clear()
	{
		internal.clear();
	}

	@Override
	public E get(int index)
	{
		return internal.get(index);
	}

	@Override
	public E set(int index, E element)
	{
		assertPossibleDuplication(element);
		return internal.set(index, element);
	}

	@Override
	public void add(int index, E element)
	{
		assertPossibleDuplication(element);
		internal.add(index, element);
	}

	@Override
	public E remove(int index)
	{
		return internal.remove(index);
	}

	@Override
	public int indexOf(Object o)
	{
		return internal.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o)
	{
		return internal.lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator()
	{
		return new ListSetIterator<E>(this, internal.listIterator());
	}

	@Override
	public ListIterator<E> listIterator(int index)
	{
		return new ListSetIterator<E>(this, internal.listIterator(index));
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex)
	{
		return new ArrayListSet<E>(internal.subList(fromIndex, toIndex));
	}

	@Override
	public boolean equals(Object o)
	{
		return internal.equals(o);
	}

	@Override
	public int hashCode()
	{
		return internal.hashCode();
	}

	@Override
	public String toString()
	{
		return internal.toString();
	}

	private void assertPossibleDuplication(E element) throws IllegalArgumentException
	{
		final int indexOf = internal.indexOf(element);

		if (indexOf >= 0)
		{
			throw new IllegalArgumentException("element " + element.toString() + " already present at index " + indexOf);
		}
	}

	private void assertPossibleDuplication(Collection<? extends E> c) throws IllegalArgumentException
	{
		if (ImmutableSet.copyOf(c).size() != c.size())
		{
			throw new IllegalArgumentException("collection contains duplicate elements and thus cannot be added.");
		}

		for (E element : c)
		{
			assertPossibleDuplication(element);
		}
	}

	public static <E> ArrayListSet<E> of(E... elements)
	{
		final ArrayListSet<E> arrayListSet = new ArrayListSet<E>();

		Collections.addAll(arrayListSet, elements);
		return arrayListSet;
	}

	public static <E> ArrayListSet<E> copyOf(Collection<E> toCopy)
	{
		final ArrayListSet<E> arrayListSet = new ArrayListSet<E>();

		arrayListSet.addAll(toCopy);
		return arrayListSet;
	}
}

package com.netappsid.observable.internal;

import static com.google.common.collect.Lists.*;
import static org.junit.Assert.*;

import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableListIterator;
import com.netappsid.observable.internal.ObservableFilteredListIterator;

public class ObservableFilteredListIteratorTest
{
	@Test
	public void test_iteratesOverMatchingElements()
	{
		final ListIterator<Integer> iterator = new ObservableFilteredListIterator(ImmutableList.of(3, 4, 5, 6, 7).listIterator(), gte(5));
		assertEquals(ImmutableList.of(5, 6, 7), ImmutableList.copyOf(iterator));
	}

	@Test
	public void test_hasNextWithNoMatchingElements()
	{
		final ListIterator<Integer> iterator = new ObservableFilteredListIterator(ImmutableList.of(1, 2, 3, 4).listIterator(), gte(5));
		assertFalse(iterator.hasNext());
	}

	@Test
	public void test_hasNextWithMatchingElement()
	{
		final ListIterator<Integer> iterator = new ObservableFilteredListIterator(ImmutableList.of(1, 5, 2, 6, 3, 7).listIterator(), gte(5));
		assertTrue(iterator.hasNext());
	}

	@Test(expected = NoSuchElementException.class)
	public void test_nextWithNoMatchingElements()
	{
		final ListIterator<Integer> iterator = new ObservableFilteredListIterator(ImmutableList.of(1, 2, 3, 4).listIterator(), gte(5));
		iterator.next();
	}

	@Test
	public void test_nextWithMatchingElement()
	{
		final ListIterator<Integer> iterator = new ObservableFilteredListIterator(ImmutableList.of(1, 2, 5, 3, 4).listIterator(), gte(5));
		assertEquals(new Integer(5), iterator.next());
	}

	@Test
	public void test_hasPreviousWithNoMatchingElements()
	{
		final ListIterator<Integer> iterator = new ObservableFilteredListIterator(ImmutableList.of(1, 2, 3, 4).listIterator(), gte(5));
		assertFalse(iterator.hasPrevious());
	}

	@Test
	public void test_hasPreviousWithMatchingElements()
	{
		final ListIterator<Integer> iterator = new ObservableFilteredListIterator(ImmutableList.of(1, 2, 5, 6, 3, 4).listIterator(), gte(5));

		iterator.next();
		assertTrue(iterator.hasPrevious());
	}

	@Test
	public void test_hasPreviousWithMatchingElementsButOnFirstMatchingElement()
	{
		final ListIterator<Integer> iterator = new ObservableFilteredListIterator(ImmutableList.of(1, 2, 5, 6, 3, 4).listIterator(), gte(5));

		iterator.next();
		iterator.previous();
		assertFalse(iterator.hasPrevious());
	}

	@Test(expected = NoSuchElementException.class)
	public void test_previousWithNoMatchingElements()
	{
		final UnmodifiableListIterator<Integer> sourceIterator = ImmutableList.of(1, 2, 3, 4).listIterator();
		final ListIterator<Integer> filteredIterator = new ObservableFilteredListIterator(sourceIterator, gte(5));

		sourceIterator.next();
		filteredIterator.previous();
	}

	@Test
	public void test_previousWithMatchingElements()
	{
		final UnmodifiableListIterator<Integer> sourceIterator = ImmutableList.of(1, 2, 5, 3, 4, 6).listIterator();
		final ListIterator<Integer> filteredIterator = new ObservableFilteredListIterator(sourceIterator, gte(5));

		filteredIterator.next();
		assertEquals(new Integer(5), filteredIterator.previous());
	}

	@Test
	public void test_nextIndexWithNoMatchingElements()
	{
		final UnmodifiableListIterator<Integer> sourceIterator = ImmutableList.of(1, 2, 3, 4).listIterator();
		final ListIterator<Integer> filteredIterator = new ObservableFilteredListIterator(sourceIterator, gte(5));

		sourceIterator.next();
		assertEquals(0, filteredIterator.nextIndex());
	}

	@Test
	public void test_nextIndexWithMatchingElements()
	{
		final UnmodifiableListIterator<Integer> sourceIterator = ImmutableList.of(1, 5, 2, 6, 3, 7).listIterator();
		final ListIterator<Integer> filteredIterator = new ObservableFilteredListIterator(sourceIterator, gte(5));

		filteredIterator.next();
		assertEquals(1, filteredIterator.nextIndex());
	}

	@Test
	public void test_previousIndexWithNoMatchingElements()
	{
		final UnmodifiableListIterator<Integer> sourceIterator = ImmutableList.of(1, 2, 3, 4).listIterator();
		final ListIterator<Integer> filteredIterator = new ObservableFilteredListIterator(sourceIterator, gte(5));

		sourceIterator.next();
		assertEquals(-1, filteredIterator.previousIndex());
	}

	@Test
	public void test_previousIndexWithMatchingElements()
	{
		final UnmodifiableListIterator<Integer> sourceIterator = ImmutableList.of(1, 5, 2, 6, 3, 7, 4).listIterator();
		final ListIterator<Integer> filteredIterator = new ObservableFilteredListIterator(sourceIterator, gte(5));

		filteredIterator.next();
		filteredIterator.next();
		filteredIterator.previous();
		assertEquals(0, filteredIterator.previousIndex());
	}

	@Test
	public void test_setNonMatchingElement()
	{
		final ListIterator<Integer> sourceIterator = newArrayList(1, 5, 2, 6, 3, 7, 4).listIterator();
		final ListIterator<Integer> filteredIterator = new ObservableFilteredListIterator(sourceIterator, gte(5));

		assertIteration(filteredIterator, 0, 5, filteredIterator.next());
		filteredIterator.set(0);
		assertIteration(filteredIterator, 0, 6, filteredIterator.next());
		assertIteration(filteredIterator, -1, 6, filteredIterator.previous());
	}
	
	@Test
	public void test_removeElement()
	{
		final ListIterator<Integer> sourceIterator = newArrayList(1, 5, 2, 6, 3, 7, 4).listIterator();
		final ListIterator<Integer> filteredIterator = new ObservableFilteredListIterator(sourceIterator, gte(5));
		
		assertIteration(filteredIterator, 0, 5, filteredIterator.next());
		filteredIterator.remove();
		assertIteration(filteredIterator, 0, 6, filteredIterator.next());
		assertIteration(filteredIterator, -1, 6, filteredIterator.previous());
	}
	
	@Test
	public void test_addMatchingElement()
	{
		final ListIterator<Integer> sourceIterator = newArrayList(1, 5, 2, 6, 3, 7, 4).listIterator();
		final ListIterator<Integer> filteredIterator = new ObservableFilteredListIterator(sourceIterator, gte(5));
		
		assertIteration(filteredIterator, 0, 5, filteredIterator.next());
		filteredIterator.add(9);
		assertIteration(filteredIterator, 2, 6, filteredIterator.next());
		assertIteration(filteredIterator, 1, 6, filteredIterator.previous());
		assertIteration(filteredIterator, 0, 9, filteredIterator.previous());
		assertIteration(filteredIterator, -1, 5, filteredIterator.previous());
	}
	
	@Test
	public void test_addNonMatchingElement()
	{
		final ListIterator<Integer> sourceIterator = newArrayList(1, 5, 2, 6, 3, 7, 4).listIterator();
		final ListIterator<Integer> filteredIterator = new ObservableFilteredListIterator(sourceIterator, gte(5));
		
		assertIteration(filteredIterator, 0, 5, filteredIterator.next());
		filteredIterator.add(0);
		assertIteration(filteredIterator, 1, 6, filteredIterator.next());
		assertIteration(filteredIterator, 0, 6, filteredIterator.previous());
		assertIteration(filteredIterator, -1, 5, filteredIterator.previous());
	}
	
	private static void assertIteration(ListIterator<Integer> listIterator, int currentIndex, Integer expected, Integer actual)
	{
		assertEquals("element", expected, actual);
		assertEquals("nextIndex", currentIndex + 1, listIterator.nextIndex());
		assertEquals("previousIndex", currentIndex, listIterator.previousIndex());
	}

	public static Predicate<Integer> gte(final int value)
	{
		return new Predicate<Integer>()
			{
				@Override
				public boolean apply(Integer input)
				{
					return input.intValue() >= value;
				}
			};
	}
}

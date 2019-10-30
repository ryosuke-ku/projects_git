package com.netappsid.observable;

import static com.netappsid.observable.ObservableCollections.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.netappsid.observable.ObservableUnsafeFilteredSubList;
import com.netappsid.observable.ObservableCollections;
import com.netappsid.observable.ObservableList;

public class ObservableUnsafeFilteredSubListTest
{
	private ObservableList<Integer> original;
	private ObservableUnsafeFilteredSubList<Integer> filtered;

	@Before
	public void before()
	{
		original = newObservableArrayList(3, 4, 5, 6, 7);
		filtered = ObservableUnsafeFilteredSubList.of(original, gte(5));
	}

	@Test
	public void test_sizeOf()
	{
		assertEquals(3, filtered.size());
	}

	@Test
	public void test_isEmpty()
	{
		assertTrue(ObservableUnsafeFilteredSubList.of(ObservableCollections.<Integer> newObservableArrayList(), gte(5)).isEmpty());
	}

	@Test
	public void test_isNotEmptyWhenElementMatches()
	{
		assertFalse(ObservableUnsafeFilteredSubList.of(ObservableCollections.<Integer> newObservableArrayList(5), gte(5)).isEmpty());
	}

	@Test
	public void test_containsWhenElementNotInOriginalReturnFalse()
	{
		assertFalse(filtered.contains(9));
	}

	@Test
	public void test_containsWhenElementInOriginalButDoesntMatchReturnsFalse()
	{
		assertFalse(filtered.contains(3));
	}

	@Test
	public void test_containsWhenElementInOriginalAndMatchesReturnsTrue()
	{
		assertTrue(filtered.contains(5));
	}

	@Test
	public void test_isEmptyWhenNoElementMatches()
	{
		assertTrue(ObservableUnsafeFilteredSubList.of(ObservableCollections.<Integer> newObservableArrayList(1, 2), gte(5)).isEmpty());
	}

	@Test
	public void test_filteredIterator()
	{
		assertEquals(ImmutableList.of(5, 6, 7), ImmutableList.copyOf(filtered.iterator()));
	}

	@Test
	public void test_unfilteredIterator()
	{
		filtered.setFilter(null);
		assertEquals(ImmutableList.of(3, 4, 5, 6, 7), ImmutableList.copyOf(filtered.iterator()));
	}

	@Test
	public void test_removalInIteratorAffectsOriginalAndFiltered()
	{
		final Iterator<Integer> iterator = filtered.iterator();
		iterator.next();
		iterator.remove();

		assertEquals(ImmutableList.of(6, 7), filtered);
		assertEquals(ImmutableList.of(3, 4, 6, 7), original);
	}

	@Test
	public void test_filteredToArray()
	{
		assertArrayEquals(ImmutableList.of(5, 6, 7).toArray(), filtered.toArray());
	}

	@Test
	public void test_unfilteredToArray()
	{
		filtered.setFilter(null);
		assertArrayEquals(ImmutableList.of(3, 4, 5, 6, 7).toArray(), filtered.toArray());
	}

	@Test
	public void test_filteredToArrayWithArrayArgumentOfAppropriateSize()
	{
		final Integer[] array = new Integer[3];
		final Integer[] result = filtered.toArray(array);
		assertArrayEquals(ImmutableList.of(5, 6, 7).toArray(), result);
		assertSame(array, result);
	}

	@Test
	public void test_filteredToArrayWithArrayArgumentOfInnappropriateSize()
	{
		final Integer[] array = new Integer[0];
		final Integer[] result = filtered.toArray(array);
		assertArrayEquals(ImmutableList.of(5, 6, 7).toArray(), result);
		assertNotSame(array, result);
	}

	@Test
	public void test_unfilteredToArrayWithArrayArgumentOfAppropriateSize()
	{
		filtered.setFilter(null);

		final Integer[] array = new Integer[5];
		final Integer[] result = filtered.toArray(array);
		assertArrayEquals(ImmutableList.of(3, 4, 5, 6, 7).toArray(), result);
		assertSame(array, result);
	}

	@Test
	public void test_unfilteredToArrayWithArrayArgumentOfInnappropriateSize()
	{
		filtered.setFilter(null);

		final Integer[] array = new Integer[0];
		final Integer[] result = filtered.toArray(array);
		assertArrayEquals(ImmutableList.of(3, 4, 5, 6, 7).toArray(), result);
		assertNotSame(array, result);
	}

	@Test
	public void test_MatchingInsertionInOriginalAffectsFiltered()
	{
		original.add(10);
		assertEquals(ImmutableList.of(5, 6, 7, 10), filtered);
	}

	@Test
	public void test_NonMatchingInsertionInOriginalDoesntAffectFiltered()
	{
		original.add(-5);
		assertEquals(ImmutableList.of(5, 6, 7), filtered);
	}

	@Test
	public void test_MatchingInsertionInFilteredAffectsOriginal()
	{
		final boolean result = filtered.add(10);

		assertTrue(result);
		assertEquals(ImmutableList.of(3, 4, 5, 6, 7, 10), original);
	}

	@Test
	public void test_NonMatchingInsertionInFilteredAffectsOriginal()
	{
		final boolean result = filtered.add(2);

		assertFalse(result);
		assertEquals(ImmutableList.of(3, 4, 5, 6, 7, 2), original);
	}

	@Test
	public void test_MatchingRemovalInOriginalAffectsFiltered()
	{
		original.remove((Object) 7);
		assertEquals(ImmutableList.of(5, 6), filtered);
	}

	@Test
	public void test_NonMatchingRemovalInOriginalDoesntAffectFiltered()
	{
		original.remove((Object) 4);
		assertEquals(ImmutableList.of(5, 6, 7), filtered);
	}

	@Test
	public void test_MatchingRemovalInFilteredAffectsOriginal()
	{
		final boolean result = filtered.remove((Object) 7);

		assertTrue(result);
		assertEquals(ImmutableList.of(3, 4, 5, 6), original);
	}

	@Test
	public void test_NonMatchingRemovalInFilteredAffectsOriginal()
	{
		final boolean result = filtered.remove((Object) 4);

		assertFalse(result);
		assertEquals(ImmutableList.of(3, 5, 6, 7), original);
	}

	@Test
	public void test_containsAllWhenInOriginalAndMatches()
	{
		assertTrue(filtered.containsAll(ImmutableList.of(5, 6)));
	}

	@Test
	public void test_containsAllWhenNotInOriginal()
	{
		assertFalse(filtered.containsAll(ImmutableList.of(1, 2)));
	}

	@Test
	public void test_containsAllWhenNotMatching()
	{
		assertFalse(filtered.containsAll(ImmutableList.of(3, 4)));
	}

	@Test
	public void test_MatchingMassInsertionInOriginalAffectsFiltered()
	{
		original.addAll(ImmutableList.of(8, 9));
		assertEquals(ImmutableList.of(5, 6, 7, 8, 9), filtered);
	}

	@Test
	public void test_NonMatchingMassInsertionInOriginalDoesntAffectFiltered()
	{
		original.addAll(ImmutableList.of(1, 2));
		assertEquals(ImmutableList.of(5, 6, 7), filtered);
	}

	@Test
	public void test_PartialMatchingMassInsertionInOriginalAffectsFiltered()
	{
		original.addAll(ImmutableList.of(1, 8));
		assertEquals(ImmutableList.of(5, 6, 7, 8), filtered);
	}

	@Test
	public void test_MatchingMassInsertionInFilteredAffectsOriginal()
	{
		final boolean result = filtered.addAll(ImmutableList.of(8, 9));

		assertTrue(result);
		assertEquals(ImmutableList.of(3, 4, 5, 6, 7, 8, 9), original);
	}

	@Test
	public void test_NonMatchingMassInsertionInFilteredAffectsOriginal()
	{
		final boolean result = filtered.addAll(ImmutableList.of(1, 2));

		assertFalse(result);
		assertEquals(ImmutableList.of(3, 4, 5, 6, 7, 1, 2), original);
	}

	@Test
	public void test_PartialMatchingMassInsertionInFilteredAffectsFullyOriginal()
	{
		final boolean result = filtered.addAll(ImmutableList.of(1, 8));

		assertTrue(result);
		assertEquals(ImmutableList.of(3, 4, 5, 6, 7, 1, 8), original);
	}

	@Test
	public void test_MatchingMassIndexedInsertionInOriginalAffectsFiltered()
	{
		original.addAll(0, ImmutableList.of(8, 9));
		assertEquals(ImmutableList.of(8, 9, 5, 6, 7), filtered);
	}

	@Test
	public void test_NonMatchingMassIndexedInsertionInOriginalDoesntAffectFiltered()
	{
		original.addAll(0, ImmutableList.of(1, 2));
		assertEquals(ImmutableList.of(5, 6, 7), filtered);
	}

	@Test
	public void test_PartialMatchingMassIndexedInsertionInOriginalAffectsFiltered()
	{
		original.addAll(0, ImmutableList.of(1, 8));
		assertEquals(ImmutableList.of(8, 5, 6, 7), filtered);
	}

	@Test
	public void test_MatchingMassIndexedInsertionInFilteredAffectsOriginal()
	{
		final boolean result = filtered.addAll(0, ImmutableList.of(8, 9));

		assertTrue(result);
		assertEquals(ImmutableList.of(3, 4, 8, 9, 5, 6, 7), original);
	}

	@Test
	public void test_NonMatchingMassIndexedInsertionInFilteredAffectsOriginal()
	{
		final boolean result = filtered.addAll(0, ImmutableList.of(1, 2));

		assertFalse(result);
		assertEquals(ImmutableList.of(3, 4, 1, 2, 5, 6, 7), original);
	}

	@Test
	public void test_PartialMatchingMassIndexedInsertionInFilteredAffectsFullyOriginal()
	{
		final boolean result = filtered.addAll(0, ImmutableList.of(1, 8));

		assertTrue(result);
		assertEquals(ImmutableList.of(3, 4, 1, 8, 5, 6, 7), original);
	}

	@Test
	public void test_MatchingMassRemovalInOriginalAffectsFiltered()
	{
		original.removeAll(ImmutableList.of(5, 6));
		assertEquals(ImmutableList.of(7), filtered);
	}

	@Test
	public void test_NonMatchingMassRemovalInOriginalDoesntAffectFiltered()
	{
		original.removeAll(ImmutableList.of(1, 2));
		assertEquals(ImmutableList.of(5, 6, 7), filtered);
	}

	@Test
	public void test_PartialMatchingMassRemovalInOriginalAffectsFiltered()
	{
		original.removeAll(ImmutableList.of(3, 7));
		assertEquals(ImmutableList.of(5, 6), filtered);
	}

	@Test
	public void test_MatchingMassRemovalInFilteredAffectsOriginal()
	{
		final boolean result = filtered.removeAll(ImmutableList.of(6, 7));

		assertTrue(result);
		assertEquals(ImmutableList.of(3, 4, 5), original);
	}

	@Test
	public void test_NonMatchingMassRemovalInFilteredAffectsOriginal()
	{
		final boolean result = filtered.removeAll(ImmutableList.of(3, 4));

		assertFalse(result);
		assertEquals(ImmutableList.of(5, 6, 7), original);
	}

	@Test
	public void test_PartialMatchingMassRemovalInFilteredAffectsFullyOriginal()
	{
		final boolean result = filtered.removeAll(ImmutableList.of(3, 7));

		assertTrue(result);
		assertEquals(ImmutableList.of(4, 5, 6), original);
	}

	@Test
	public void test_MatchingRetentionInOriginalAffectsFiltered()
	{
		original.retainAll(ImmutableList.of(6, 7));
		assertEquals(ImmutableList.of(6, 7), filtered);
	}

	@Test
	public void test_NonMatchingRetentionInOriginalDoesntAffectFiltered()
	{
		original.retainAll(ImmutableList.of(3, 4));
		assertEquals(ImmutableList.of(), filtered);
	}

	@Test
	public void test_PartialMatchingRetentionInOriginalAffectsFiltered()
	{
		original.retainAll(ImmutableList.of(3, 7));
		assertEquals(ImmutableList.of(7), filtered);
	}

	@Test
	public void test_MatchingRetentionInFilteredAffectsOriginal()
	{
		final boolean result = filtered.retainAll(ImmutableList.of(6, 7));

		assertTrue(result);
		assertEquals(ImmutableList.of(6, 7), original);
	}

	@Test
	public void test_NonMatchingRetentionInFilteredAffectsOriginal()
	{
		final boolean result = filtered.retainAll(ImmutableList.of(3, 4));

		assertTrue(result);
		assertEquals(ImmutableList.of(3, 4), original);
	}

	@Test
	public void test_PartialRetentionInFilteredAffectsFullyOriginal()
	{
		final boolean result = filtered.retainAll(ImmutableList.of(3, 6));

		assertTrue(result);
		assertEquals(ImmutableList.of(3, 6), original);
	}

	@Test
	public void test_clearingFilteredAffectsOriginal()
	{
		filtered.clear();
		assertEquals(ImmutableList.of(), original);
	}

	@Test
	public void test_clearingOriginalAffectsFiltered()
	{
		original.clear();
		assertEquals(ImmutableList.of(), filtered);
	}

	@Test
	public void test_indexedRetrieval()
	{
		assertEquals(new Integer(5), filtered.get(0));
	}

	@Test
	public void test_MatchingSetInOriginalAffectsFiltered()
	{
		original.set(0, 8);
		assertEquals(ImmutableList.of(8, 5, 6, 7), filtered);
	}

	@Test
	public void test_NonMatchingSetInOriginalDoesntAffectFiltered()
	{
		original.set(0, 1);
		assertEquals(ImmutableList.of(5, 6, 7), filtered);
	}

	@Test
	public void test_MatchingSetInFilteredAffectsOriginal()
	{
		filtered.set(0, 8);
		assertEquals(ImmutableList.of(3, 4, 8, 6, 7), original);
	}

	@Test
	public void test_NonMatchingSetInFilteredAffectsOriginal()
	{
		filtered.set(0, 2);
		assertEquals(ImmutableList.of(3, 4, 2, 6, 7), original);
	}

	@Test
	public void test_MatchingIndexedAddInOriginalAffectsFiltered()
	{
		original.add(0, 8);
		assertEquals(ImmutableList.of(8, 5, 6, 7), filtered);
	}

	@Test
	public void test_NonMatchingIndexedAddInOriginalDoesntAffectFiltered()
	{
		original.add(0, 2);
		assertEquals(ImmutableList.of(5, 6, 7), filtered);
	}

	@Test
	public void test_MatchingIndexedAddInFilteredAffectsOriginal()
	{
		filtered.add(0, 8);
		assertEquals(ImmutableList.of(3, 4, 8, 5, 6, 7), original);
	}

	@Test
	public void test_NonMatchingIndexedAddInFilteredAffectsOriginal()
	{
		filtered.add(0, 2);
		assertEquals(ImmutableList.of(3, 4, 2, 5, 6, 7), original);
	}

	@Test
	public void test_MatchingIndexedRemovalInOriginalAffectsFiltered()
	{
		original.remove(original.indexOf(6));
		assertEquals(ImmutableList.of(5, 7), filtered);
	}

	@Test
	public void test_NonMatchingIndexedRemovalInOriginalDoesntAffectFiltered()
	{
		original.remove(original.indexOf(3));
		assertEquals(ImmutableList.of(5, 6, 7), filtered);
	}

	@Test
	public void test_MatchingIndexedRemovalInFilteredAffectsOriginal()
	{
		filtered.remove(filtered.indexOf(6));
		assertEquals(ImmutableList.of(3, 4, 5, 7), original);
	}

	@Test
	public void test_indexOfWhenElementNotInOriginal()
	{
		assertEquals(-1, filtered.indexOf(2));
	}

	@Test
	public void test_indexOfWhenElementInOriginalButDoesntMatch()
	{
		assertEquals(-1, filtered.indexOf(3));
	}

	@Test
	public void test_indexOfWhenElementInOriginalAndMatches()
	{
		assertEquals(1, filtered.indexOf(6));
	}

	@Test
	public void test_lastIndexOfWhenElementNotInOriginal()
	{
		assertEquals(-1, filtered.lastIndexOf(2));
	}

	@Test
	public void test_lastIndexOfWhenElementInOriginalButDoesntMatch()
	{
		assertEquals(-1, filtered.lastIndexOf(3));
	}

	@Test
	public void test_lastIndexOfWhenElementInOriginalAndMatches()
	{
		assertEquals(1, filtered.lastIndexOf(6));
	}

	@Test
	public void test_subList()
	{
		final List<Integer> subList = filtered.subList(0, 2);
		
		assertEquals(ImmutableList.of(5, 6), subList);
	}
	
	@Test
	public void test_modificationInSubListAffectsList()
	{
		final ObservableUnsafeFilteredSubList<Integer> spy = spy(filtered);
		final List<Integer> subList = spy.subList(0, 2);
		
		subList.add(9);
		assertEquals(ImmutableList.of(5, 6, 9), subList);
		assertEquals(ImmutableList.of(5, 6, 9, 7), filtered);
	}

	public static Predicate<Integer> gte(final int value)
	{
		return new Predicate<Integer>()
			{
				public boolean apply(Integer input)
				{
					return input.intValue() >= value;
				}
			};
	}
}

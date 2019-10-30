package com.netappsid.observable.internal;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.netappsid.observable.CollectionDifference;
import com.netappsid.observable.ListDifference;

public class ListDifferenceTest
{
	@Test
	public void test_whenFullIntersection()
	{
		final ImmutableList<Integer> left = ImmutableList.of(1, 2, 3);
		final ImmutableList<Integer> right = ImmutableList.of(1, 2, 3);

		final CollectionDifference<Integer> difference = ListDifference.difference(left, right);
		assertEquals(ImmutableList.of(), difference.getRemoved());
		assertEquals(ImmutableList.of(), difference.getAdded());
	}

	@Test
	public void test_whenIntersectionWithRemainingInLeft()
	{
		final ImmutableList<Integer> left = ImmutableList.of(1, 2, 3);
		final ImmutableList<Integer> right = ImmutableList.of(1, 2);

		final CollectionDifference<Integer> difference = ListDifference.difference(left, right);
		assertEquals(ImmutableList.of(3), difference.getRemoved());
		assertEquals(ImmutableList.of(), difference.getAdded());
	}

	@Test
	public void test_whenIntersectionWithRemainingInRight()
	{
		final ImmutableList<Integer> left = ImmutableList.of(1, 2);
		final ImmutableList<Integer> right = ImmutableList.of(1, 2, 3);

		final CollectionDifference<Integer> difference = ListDifference.difference(left, right);
		assertEquals(ImmutableList.of(), difference.getRemoved());
		assertEquals(ImmutableList.of(3), difference.getAdded());
	}

	@Test
	public void test_whenIntersectionWithBothRemaining()
	{
		final ImmutableList<Integer> left = ImmutableList.of(1, 2);
		final ImmutableList<Integer> right = ImmutableList.of(2, 3);

		final CollectionDifference<Integer> difference = ListDifference.difference(left, right);
		assertEquals(ImmutableList.of(1), difference.getRemoved());
		assertEquals(ImmutableList.of(3), difference.getAdded());
	}

	@Test
	public void test_whenNoIntersection()
	{
		final ImmutableList<Integer> left = ImmutableList.of(1, 2);
		final ImmutableList<Integer> right = ImmutableList.of(3, 4);

		final CollectionDifference<Integer> difference = ListDifference.difference(left, right);
		assertEquals(ImmutableList.of(1, 2), difference.getRemoved());
		assertEquals(ImmutableList.of(3, 4), difference.getAdded());
	}

	@Test
	public void test_whenFullIntersectionWithDuplicate()
	{
		final ImmutableList<Integer> left = ImmutableList.of(1, 1, 1);
		final ImmutableList<Integer> right = ImmutableList.of(1, 1, 1);

		final CollectionDifference<Integer> difference = ListDifference.difference(left, right);
		assertEquals(ImmutableList.of(), difference.getRemoved());
		assertEquals(ImmutableList.of(), difference.getAdded());
	}

	@Test
	public void test_whenIntersectionWithDuplicateWithRemainingInLeft()
	{
		final ImmutableList<Integer> left = ImmutableList.of(1, 1, 1);
		final ImmutableList<Integer> right = ImmutableList.of(1, 1);

		final CollectionDifference<Integer> difference = ListDifference.difference(left, right);
		assertEquals(ImmutableList.of(1), difference.getRemoved());
		assertEquals(ImmutableList.of(), difference.getAdded());
	}

	@Test
	public void test_whenIntersectionWithDuplicateWithRemainingInRight()
	{
		final ImmutableList<Integer> left = ImmutableList.of(1, 1);
		final ImmutableList<Integer> right = ImmutableList.of(1, 1, 1);

		final CollectionDifference<Integer> difference = ListDifference.difference(left, right);
		assertEquals(ImmutableList.of(), difference.getRemoved());
		assertEquals(ImmutableList.of(1), difference.getAdded());
	}
}

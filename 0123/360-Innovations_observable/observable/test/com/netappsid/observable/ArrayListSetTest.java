package com.netappsid.observable;

import static com.google.common.collect.Lists.*;
import static org.junit.Assert.*;

import java.util.ListIterator;

import org.junit.Test;

import com.netappsid.collection.ArrayListSet;

public class ArrayListSetTest
{
	@Test
	public void test_returnsTrueWhenElementAdded()
	{
		assertTrue(ArrayListSet.of(1).add(2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_throwWhenAddingADuplicateElement()
	{
		ArrayListSet.of(1).add(1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_throwsWhenAddingADuplicateElementByIndex()
	{
		ArrayListSet.of(1, 2).add(1, 1);
	}

	@Test
	public void test_returnsTrueWhenAddingElementsFromACollection()
	{
		assertTrue(ArrayListSet.of(1, 2).addAll(newArrayList(3, 4)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_throwsWhenAddingDuplicateElementsFromACollection()
	{
		ArrayListSet.of(1, 2, 3).addAll(newArrayList(1, 2));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_throwsWhenAddingElementsFromACollectionWhichContainsDuplicates()
	{
		ArrayListSet.of(1, 2, 3).addAll(newArrayList(4, 4));
	}

	@Test
	public void test_returnsTrueWhenAddingElementsByIndexFromACollection()
	{
		assertTrue(ArrayListSet.of(1, 2, 3).addAll(0, newArrayList(4, 5)));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_throwswhenAddingDuplicateElementsByIndexFromACollection()
	{
		ArrayListSet.of(1, 2, 3).addAll(0, newArrayList(2, 4));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_throwsWhenAddingElementsByIndexFromACollectionWhichContainsDuplicates()
	{
		ArrayListSet.of(1, 2, 3).addAll(0, newArrayList(4, 4));
	}

	@Test
	public void test_returnsElementAtIndexWhenSettingAnElement()
	{
		assertEquals(new Integer(1), ArrayListSet.of(1, 2, 3).set(0, 4));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_throwsWhenSettingADuplicateElement()
	{
		ArrayListSet.of(1, 2, 3).set(0, 3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_throwsWhenAddingDuplicateElementFromListIterator()
	{
		ArrayListSet.of(1, 2, 3).listIterator().add(3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_throwsWhenSettingDuplicateElementFromListIterator()
	{
		final ListIterator<Integer> listIterator = ArrayListSet.of(1, 2, 3).listIterator();

		listIterator.next();
		listIterator.set(3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_throwsWhenAddingDuplicateElementFromSpecifiedIndexListIterator()
	{
		ArrayListSet.of(1, 2, 3).listIterator(1).add(3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_throwsWhenSettingDuplicateElementFromSpecifiedIndexListIterator()
	{
		final ListIterator<Integer> listIterator = ArrayListSet.of(1, 2, 3).listIterator(1);

		listIterator.next();
		listIterator.set(3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_throwsWhenAddingDuplicateElementFromSubList()
	{
		ArrayListSet.of(1, 2, 3).subList(1, 3).add(2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_throwsWhenSettingDuplicateElementFromSubList()
	{
		ArrayListSet.of(1, 2, 3).subList(1, 3).set(0, 2);
	}

	@Test
	public void test_subListIsBackedByMainList()
	{
		final ArrayListSet<Integer> listSet = ArrayListSet.of(1, 2, 3);

		listSet.subList(1, 3).add(4);
		assertEquals(ArrayListSet.of(1, 2, 3, 4), listSet);
	}
}

package com.netappsid.observable;

import static com.google.common.collect.Lists.*;
import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;

import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableList;
import com.netappsid.observable.test.CollectionChangeEventSpy;
import com.netappsid.test.tools.LabelledParameterized;

@RunWith(LabelledParameterized.class)
public class ObservableListTest
{
	private final ObservableList<Integer> observableList;
	private CollectionChangeEventSpy eventSpy;

	public ObservableListTest(String label, ObservableList<Integer> observableList)
	{
		this.observableList = observableList;
	}

	@Before
	public void before()
	{
		observableList.addAll(ImmutableList.of(1, 2, 3));
		eventSpy = new CollectionChangeEventSpy();
		observableList.addCollectionChangeListener(eventSpy);
	}

	@After
	public void after()
	{
		observableList.removeCollectionChangeListener(eventSpy);
		observableList.clear();
	}

	@Parameters
	public static Collection implementations()
	{
		final Collection implementations = newArrayList();

		implementations.add(new Object[] { ObservableUnsafeFilteredSubList.class.getName(),
				ObservableUnsafeFilteredSubList.of(ObservableCollections.<Integer> newObservableArrayList(), Predicates.alwaysTrue()) });
		implementations.add(new Object[] { ObservableUnsafeList.class.getName(),
				ObservableUnsafeList.of(ObservableCollections.<Integer> newObservableArrayList()) });
		implementations.add(new Object[] { ObservableListDecorator.class.getName(), ObservableCollections.<Integer> newObservableArrayList() });
		return implementations;
	}

	@Test
	public void testAdd()
	{
		observableList.add(4);
		eventSpy.assertEvent(observableList, ImmutableList.of(4), ImmutableList.of(), -1);
	}

	@Test
	public void testAddAll()
	{
		observableList.addAll(ImmutableList.of(4, 5, 6));
		eventSpy.assertEvent(observableList, ImmutableList.of(4, 5, 6), ImmutableList.of(), -1);
	}

	@Test
	public void testAddAllIndexed()
	{
		observableList.addAll(1, ImmutableList.of(4, 5, 6));
		eventSpy.assertEvent(observableList, ImmutableList.of(4, 5, 6), ImmutableList.of(), 1);
	}

	@Test
	public void testAddIndexed()
	{
		observableList.add(1, 4);
		eventSpy.assertEvent(observableList, ImmutableList.of(4), ImmutableList.of(), 1);
	}

	@Test
	public void testClear()
	{
		observableList.clear();
		eventSpy.assertEvent(observableList, ImmutableList.of(), ImmutableList.of(1, 2, 3), -1);
	}

	@Test
	public void testRemove()
	{
		observableList.remove((Integer) 2);
		eventSpy.assertEvent(observableList, ImmutableList.of(), ImmutableList.of(2), 1);
	}

	@Test
	public void testRemoveAll()
	{
		observableList.removeAll(ImmutableList.of(1, 3));
		eventSpy.assertEvent(observableList, ImmutableList.of(), ImmutableList.of(1, 3), -1);
	}

	@Test
	public void testRemoveIndexed()
	{
		observableList.remove(1);
		eventSpy.assertEvent(observableList, ImmutableList.of(), ImmutableList.of(2), 1);
	}

	@Test
	public void testRetainAll()
	{
		observableList.retainAll(ImmutableList.of(1, 3));
		eventSpy.assertEvent(observableList, ImmutableList.of(), ImmutableList.of(2), -1);
	}

	@Test
	public void testSet()
	{
		observableList.set(1, 4);
		eventSpy.assertEvent(observableList, ImmutableList.of(4), ImmutableList.of(2), 1);
	}

	@Test
	public void testIterator()
	{
		for (Iterator<Integer> iterator = observableList.iterator(); iterator.hasNext();)
		{
			final Integer next = iterator.next();
			iterator.remove();
			eventSpy.assertEvent(observableList, ImmutableList.of(), ImmutableList.of(next));
		}
	}

	@Test
	public void test_listIteratorRemoval()
	{
		for (ListIterator<Integer> iterator = observableList.listIterator(); iterator.hasNext();)
		{
			final Integer next = iterator.next();
			iterator.remove();
			eventSpy.assertEvent(observableList, ImmutableList.of(), ImmutableList.of(next));
		}
	}

	@Test
	public void test_listIteratorInsertion()
	{
		final ImmutableList<Integer> values = ImmutableList.of(4, 5, 6);
		final ListIterator<Integer> listIterator = observableList.listIterator();

		int index = 0;
		for (Integer value : values)
		{
			listIterator.add(value);
			eventSpy.assertEvent(observableList, ImmutableList.of(value), ImmutableList.of(), index);
			index++;
		}
	}

	@Test
	public void test_listIteratorSet()
	{
		final ImmutableList<Integer> values = ImmutableList.of(4, 5, 6);
		final ListIterator<Integer> listIterator = observableList.listIterator();

		int index = 0;
		for (Integer value : values)
		{
			final Integer next = listIterator.next();
			listIterator.set(value);
			eventSpy.assertEvent(observableList, ImmutableList.of(value), ImmutableList.of(next), index);
			index++;
		}
	}

	@Test
	public void test_getCollectionChangeListeners_EnsureNotNull()
	{
		assertEquals("Must contain only 1 element", 1, observableList.getCollectionChangeListeners().size());
		assertTrue("Must contain only eventSpy", observableList.getCollectionChangeListeners().contains(eventSpy));
	}

	@Test
	public void test_applyDiff()
	{
		ListDifference<Integer> difference = ListDifference.difference(ImmutableList.<Integer> of(), ImmutableList.of(4));
		observableList.apply(difference);
		assertEquals(ImmutableList.of(1, 2, 3, 4), observableList);
	}

	@Test
	public void test_unapplyDiff()
	{
		ListDifference<Integer> difference = ListDifference.difference(ImmutableList.<Integer> of(), ImmutableList.of(3));
		observableList.unapply(difference);
		assertEquals(ImmutableList.of(1, 2), observableList);
	}
}

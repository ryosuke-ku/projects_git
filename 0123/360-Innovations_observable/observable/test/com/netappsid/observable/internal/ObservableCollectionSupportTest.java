package com.netappsid.observable.internal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.netappsid.observable.CollectionChangeEvent;
import com.netappsid.observable.CollectionChangeListener;
import com.netappsid.observable.CollectionDifference;
import com.netappsid.observable.InternalObservableCollection;
import com.netappsid.observable.InternalObservableCollectionSupport;
import com.netappsid.observable.ListDifference;
import com.netappsid.observable.ObservableSetDecorator;
import com.netappsid.observable.test.CollectionChangeEventSpy;

public class ObservableCollectionSupportTest
{
	private InternalObservableCollection<Integer, Set<Integer>> source;
	private InternalObservableCollectionSupport<Integer> support;

	@Before
	public void before()
	{
		source = new ObservableSetDecorator<Integer>(new HashSet<Integer>());
		support = source.getSupport();
	}

	@Test
	public void test_canAddListener()
	{
		final CollectionChangeListener listener = mock(CollectionChangeListener.class);
		support.addCollectionChangeListener(listener);

		assertTrue(support.getCollectionChangeListeners().contains(listener));
	}

	@Test
	public void test_canRemoveListener()
	{
		final CollectionChangeListener listener = mock(CollectionChangeListener.class);
		support.addCollectionChangeListener(listener);
		support.removeCollectionChangeListener(listener);

		assertFalse(support.getCollectionChangeListeners().contains(listener));
	}

	@Test
	public void test_createANewNonIndexedCollectionChangeEvent()
	{
		final ImmutableList<Integer> added = ImmutableList.of(1);
		final ImmutableList<Integer> removed = ImmutableList.of(2);

		final CollectionChangeEvent event = newCollectionChangeEvent(new ListDifference(removed, added));
		assertEquals(source, event.getSource());
		assertEquals(added, event.getAdded());
		assertEquals(removed, event.getRemoved());
	}

	private CollectionChangeEvent newCollectionChangeEvent(CollectionDifference difference)
	{
		return newCollectionChangeEvent(difference, null);
	}

	/**
	 * @param listDifference
	 * @return
	 */
	private CollectionChangeEvent newCollectionChangeEvent(CollectionDifference difference, Object index)
	{
		return new CollectionChangeEvent(source, difference, index);
	}

	@Test
	public void test_createAnIndexedCollectionChangeEvent()
	{
		final ImmutableList<Integer> added = ImmutableList.of(1);
		final ImmutableList<Integer> removed = ImmutableList.of(2);
		final int index = 1;

		final CollectionChangeEvent event = newCollectionChangeEvent(new ListDifference(removed, added), index);
		assertEquals(source, event.getSource());
		assertEquals(added, event.getAdded());
		assertEquals(removed, event.getRemoved());
		assertEquals(index, event.getIndex());
	}

	@Test
	public void test_firesACollectionChangeEventPassedInParameter()
	{
		final CollectionChangeEventSpy eventSpy = new CollectionChangeEventSpy();
		support.addCollectionChangeListener(eventSpy);

		final CollectionChangeEvent<Integer> event = newCollectionChangeEvent(new SetDifference(ImmutableSet.of(2), ImmutableSet.of(1)));
		support.fireCollectionChangeEvent(event);

		assertEquals(event, eventSpy.getEvent());
	}

	@Test
	public void test_doesNotFireWhenEventHasNoAddedOrRemoved()
	{
		final CollectionChangeEventSpy eventSpy = new CollectionChangeEventSpy();
		support.addCollectionChangeListener(eventSpy);

		final CollectionChangeEvent<Integer> event = newCollectionChangeEvent(new SetDifference(ImmutableSet.<Integer> of(), ImmutableSet.<Integer> of()));
		support.fireCollectionChangeEvent(event);

		assertNull(eventSpy.getEvent());
	}

}

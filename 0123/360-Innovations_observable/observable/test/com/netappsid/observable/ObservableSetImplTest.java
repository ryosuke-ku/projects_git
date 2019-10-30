package com.netappsid.observable;

import static com.google.common.collect.Sets.*;
import static com.netappsid.observable.ObservableCollections.*;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableSet;
import com.netappsid.observable.test.CollectionChangeEventSpy;

public class ObservableSetImplTest
{
	private ObservableSet<Integer> observableSet;
	private CollectionChangeEventSpy listener;

	@Before
	public void before()
	{
		this.observableSet = newObservableHashSet(1, 2, 3);
		this.listener = new CollectionChangeEventSpy();

		observableSet.addCollectionChangeListener(listener);
	}

	@Test
	public void test_firesEventOnAdd()
	{
		observableSet.add(4);
		listener.assertEvent(observableSet, ImmutableSet.of(4), ImmutableSet.of());
	}

	@Test
	public void test_firesEventOnRemove()
	{
		observableSet.remove(2);
		listener.assertEvent(observableSet, ImmutableSet.of(), ImmutableSet.of(2));
	}

	@Test
	public void test_firesEventOnAddAll()
	{
		observableSet.addAll(newHashSet(4, 5, 6));
		listener.assertEvent(observableSet, ImmutableSet.of(4, 5, 6), ImmutableSet.of());
	}

	@Test
	public void test_firesEventOnRetainAll()
	{
		observableSet.retainAll(newHashSet(1, 3));
		listener.assertEvent(observableSet, ImmutableSet.of(), ImmutableSet.of(2));
	}

	@Test
	public void test_firesEventOnRemovalAll()
	{
		observableSet.removeAll(newHashSet(1, 3));
		listener.assertEvent(observableSet, ImmutableSet.of(), ImmutableSet.of(1, 3));
	}

	@Test
	public void test_firesEventOnClear()
	{
		observableSet.clear();
		listener.assertEvent(observableSet, ImmutableSet.of(), ImmutableSet.of(1, 2, 3));
	}
}

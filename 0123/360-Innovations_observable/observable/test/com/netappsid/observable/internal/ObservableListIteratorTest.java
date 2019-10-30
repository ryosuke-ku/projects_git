package com.netappsid.observable.internal;

import static com.netappsid.observable.ObservableCollections.*;

import java.util.ListIterator;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.netappsid.observable.ObservableList;
import com.netappsid.observable.test.CollectionChangeEventSpy;

public class ObservableListIteratorTest
{
	private ObservableList<Integer> observableList;
	private CollectionChangeEventSpy eventSpy;

	@Before
	public void before()
	{
		observableList = newObservableArrayList(1, 2, 3);
		eventSpy = new CollectionChangeEventSpy();
		observableList.addCollectionChangeListener(eventSpy);
	}

	@Test
	public void test_firesEventOnAdd()
	{
		observableList.listIterator().add(4);
		eventSpy.assertEvent(observableList, ImmutableList.of(4), ImmutableList.of(), observableList.indexOf(4));
	}

	@Test
	public void test_firesEventOnRemove()
	{
		final ListIterator<Integer> iterator = observableList.listIterator();
		final int removedIndex = iterator.nextIndex();
		final Integer removed = iterator.next();

		iterator.remove();
		eventSpy.assertEvent(observableList, ImmutableList.of(), ImmutableList.of(removed), removedIndex);
	}

	@Test
	public void test_firesEventOnSet()
	{
		final ListIterator<Integer> iterator = observableList.listIterator();
		final int setIndex = iterator.nextIndex();
		final Integer replaced = iterator.next();

		iterator.set(5);
		eventSpy.assertEvent(observableList, ImmutableList.of(5), ImmutableList.of(replaced), setIndex);
	}
}

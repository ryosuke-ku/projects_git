package com.netappsid.observable.test;

import static com.google.common.base.Predicates.*;
import static com.netappsid.observable.ObservableCollections.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.netappsid.observable.ObservableList;
import com.netappsid.observable.ObservableUnsafeFilteredSubList;
import com.netappsid.observable.internal.ObservableFilteredListIteratorTest;

public class ObservableUnsafeFilteredSubListTest
{
	private ObservableList<Integer> observableList;
	private ObservableUnsafeFilteredSubList<Integer> subList;
	private CollectionChangeEventSpy eventSpy;
	
	@Before
	public void before()
	{
		observableList = newObservableArrayList(3, 9, 4, 5, 6, 2, 7, 8);
		subList = ObservableUnsafeFilteredSubList.of(observableList, ObservableFilteredListIteratorTest.gte(5)); // [9, 5, 6, 7, 8]
		eventSpy = new CollectionChangeEventSpy();
		
		subList.addCollectionChangeListener(eventSpy);
	}

	@Test
	public void test_firesEventWhenElementAddedInSourceThatMatchesFilter()
	{
		observableList.add(8);
		eventSpy.assertEvent(subList, ImmutableList.of(8), ImmutableList.of(), -1);
	}
	
	@Test
	public void test_doesntFireEventWhenNonMatchingElementAddedInSource()
	{
		observableList.add(4);
		assertNull(eventSpy.getEvent());
	}
	
	@Test
	public void test_firesEventWhenIndexedElementAddedInSourceThatMatchesFilter()
	{
		observableList.add(5, 8);
		eventSpy.assertEvent(subList, ImmutableList.of(8), ImmutableList.of(), 3);
	}
	
	@Test
	public void test_doesntFireEventWhenNonMatchingIndexedElementAddedInSource()
	{
		observableList.add(1, 4);
		assertNull(eventSpy.getEvent());
	}
	
	@Test
	public void test_firesEventWhenElementRemovedInSourceThatMatchesFilter()
	{
		observableList.remove((Integer) 7);
		eventSpy.assertEvent(subList, ImmutableList.of(), ImmutableList.of(7), 3);
	}
	
	@Test
	public void test_firesEventWhenNonMatchingElementSetOnMatchingElement()
	{
		observableList.set(1, 3);
		eventSpy.assertEvent(subList, ImmutableList.of(), ImmutableList.of(9), 0);
	}
	
	@Test
	public void test_firesEventWhenMatchingElementSetOnMatchingElement()
	{
		observableList.set(1, 7);
		eventSpy.assertEvent(subList, ImmutableList.of(7), ImmutableList.of(9), 0);
	}
	
	@Test
	public void test_firesEventWhenMatchingElementSetOnNonMatchingElement()
	{
		observableList.set(2, 7);
		eventSpy.assertEvent(subList, ImmutableList.of(7), ImmutableList.of(), 1);
	}
	
	@Test
	public void test_firesEventWhenPredicateChanged()
	{
		subList.setFilter(alwaysTrue());
		eventSpy.assertEvent(subList, ImmutableList.of(3, 4, 2), ImmutableList.of(), -1);
	}
}

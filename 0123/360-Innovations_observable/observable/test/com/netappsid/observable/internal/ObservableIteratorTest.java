package com.netappsid.observable.internal;

import static com.netappsid.observable.ObservableCollections.*;

import java.util.Iterator;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.netappsid.observable.ObservableSet;
import com.netappsid.observable.test.CollectionChangeEventSpy;

public class ObservableIteratorTest
{
	@Test
	public void test_firesEventOnRemoval()
	{
		final ObservableSet<Integer> observableSet = newObservableHashSet(1, 2, 3);
		final CollectionChangeEventSpy eventSpy = new CollectionChangeEventSpy();
		final Iterator<Integer> iterator = observableSet.iterator();

		observableSet.addCollectionChangeListener(eventSpy);
		iterator.next();
		final Integer removed = iterator.next();
		iterator.remove();

		eventSpy.assertEvent(observableSet, ImmutableList.of(), ImmutableList.of(removed));
	}
}

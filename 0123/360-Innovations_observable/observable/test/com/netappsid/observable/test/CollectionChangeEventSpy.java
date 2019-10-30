package com.netappsid.observable.test;

import static org.junit.Assert.*;

import java.util.Collection;

import com.netappsid.observable.CollectionChangeEvent;
import com.netappsid.observable.CollectionChangeListener;
import com.netappsid.observable.ObservableCollection;

public class CollectionChangeEventSpy<T> implements CollectionChangeListener<T>
{
	private CollectionChangeEvent<T> event = null;

	@Override
	public void onCollectionChange(CollectionChangeEvent<T> event)
	{
		this.event = event;
	}

	public CollectionChangeEvent<T> getEvent()
	{
		return event;
	}

	public void assertEvent(ObservableCollection<T> source, Collection<T> added, Collection<T> removed)
	{
		assertEquals("source", source, event.getSource());
		assertEquals("added", added, event.getAdded());
		assertEquals("removed", removed, event.getRemoved());
	}

	public void assertEvent(ObservableCollection<T> source, Collection<T> added, Collection<T> removed, Object index)
	{
		assertEquals("source", source, event.getSource());
		assertEquals("added", added, event.getAdded());
		assertEquals("removed", removed, event.getRemoved());
		assertEquals("index", index, event.getIndex());
	}
}
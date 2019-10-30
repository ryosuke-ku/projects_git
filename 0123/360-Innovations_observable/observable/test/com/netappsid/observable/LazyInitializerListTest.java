package com.netappsid.observable;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import com.netappsid.observable.internal.LazyInitializerList;

public class LazyInitializerListTest
{
	@Test
	public void testDelegateCallInitializer()
	{
		LazyInitializer lazyInitializer = mock(LazyInitializer.class);
		when(lazyInitializer.initialize()).thenReturn(Arrays.asList("Test"));

		LazyInitializerList lazyInitializerList = new LazyInitializerList(lazyInitializer);
		Object object = lazyInitializerList.get(0);

		assertEquals("Test", object);
	}

	@Test
	public void testDelegateCallInitializer_initializeOneShot()
	{
		LazyInitializer lazyInitializer = mock(LazyInitializer.class);
		when(lazyInitializer.initialize()).thenReturn(Arrays.asList("Test"));

		LazyInitializerList lazyInitializerList = new LazyInitializerList(lazyInitializer);
		lazyInitializerList.get(0);
		lazyInitializerList.get(0);

		verify(lazyInitializer, times(1)).initialize();
	}

	@Test
	public void testDelegateIsObservable()
	{
		LazyInitializer lazyInitializer = mock(LazyInitializer.class);
		when(lazyInitializer.initialize()).thenReturn(new ArrayList());

		LazyInitializerList lazyInitializerList = new LazyInitializerList(lazyInitializer);

		CollectionChangeListener changeListener = mock(CollectionChangeListener.class);
		lazyInitializerList.addCollectionChangeListener(changeListener);
		lazyInitializerList.add("TestAdd");

		verify(changeListener).onCollectionChange(any(CollectionChangeEvent.class));
	}
}

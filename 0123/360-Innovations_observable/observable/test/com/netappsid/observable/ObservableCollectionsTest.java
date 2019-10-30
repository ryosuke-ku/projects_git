package com.netappsid.observable;

import static com.google.common.collect.Lists.*;
import static com.google.common.collect.Sets.*;
import static com.netappsid.observable.ObservableCollections.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ObservableCollectionsTest
{
	@Test
	public void test_createAnObservableHashSetFromElements()
	{
		assertEquals(newObservableHashSet(1, 2, 3), newHashSet(1, 2, 3));
	}

	@Test
	public void test_createAnObservableArrayListFromElements()
	{
		assertEquals(newObservableArrayList(1, 2, 3), newArrayList(1, 2, 3));
	}

	@Test
	public void test_decorateAsObservableAnExistingArrayList()
	{
		final ArrayList<Integer> toDecorate = newArrayList(1, 2, 3);
		assertEquals(decorateList(toDecorate), toDecorate);
	}
}

package com.netappsid.observable;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

public class ClearAndAddAllBatchActionTest
{
	@Test
	public void testBatchAction()
	{
		Collection<String> newCollection = Arrays.asList("1", "2");

		ClearAndAddAllBatchAction<String> action = new ClearAndAddAllBatchAction<String>(newCollection);

		ArrayList<String> source = spy(new ArrayList<String>());
		ObservableList<String> targetCollection = ObservableCollections.decorateList(source);
		targetCollection.executeBatchAction(action);

		verify(source).clear();
		verify(source).addAll(eq(newCollection));
	}
}

package com.netappsid.observable;

import static org.mockito.Mockito.*;

import java.beans.PropertyChangeListener;

import org.junit.Before;
import org.junit.Test;

public class ObservableElementsCollectionHandlerTest
{
	private ObservableByName observableByName;
	private ObservableList<ObservableByName> observableArrayList;
	private PropertyChangeListener propertyChangeListener;
	private ObservableElementsCollectionHandler collectionValueChangeHandler;

	@Before
	public void setUp()
	{
		propertyChangeListener = mock(PropertyChangeListener.class);
		collectionValueChangeHandler = new ObservableElementsCollectionHandler(propertyChangeListener);

		observableByName = mock(ObservableByName.class);
		observableArrayList = spy(ObservableCollections.newObservableArrayList(observableByName));
	}

	@Test
	public void testInstall_EnsureItemListenersAdded()
	{
		collectionValueChangeHandler.install(observableArrayList);
		verify(observableByName).addPropertyChangeListener(propertyChangeListener);
	}

	@Test
	public void testInstall_EnsureItemListenersRemoved()
	{
		collectionValueChangeHandler.uninstall(observableArrayList);
		verify(observableByName).removePropertyChangeListener(propertyChangeListener);
	}

	@Test
	public void testInstall_EnsureCollectionChangeListenersAdded()
	{
		collectionValueChangeHandler.install(observableArrayList);
		verify(observableArrayList).addCollectionChangeListener(collectionValueChangeHandler);
	}

	@Test
	public void testUninstall_EnsureCollectionChangeListenerRemoved()
	{
		collectionValueChangeHandler.uninstall(observableArrayList);
		verify(observableArrayList).removeCollectionChangeListener(collectionValueChangeHandler);
	}
}

package com.netappsid.observable.listenerManager;

import java.beans.PropertyChangeListener;

import com.netappsid.observable.ObservableByName;
import com.netappsid.observable.ObservableCollection;
import com.netappsid.observable.ObservableElementsCollectionHandler;

/**
 * <p>
 * Same as {@link ObservableElementsCollectionHandler} but with a clearer name, to indicate what kind of listener <code>this</code> manages.
 * </p>
 * <p>
 * <b>The rules for installing this</b> are defined in the description of AbstractObservableCollectionElementListenerManager.
 * </p>
 * 
 * @author ftaillefer
 * 
 * @param <T>
 *            The type of the elements in the {@link ObservableCollection}.
 */
public class ObservableCollectionElementPropertyChangeListenerManager<T extends ObservableByName> extends ObservableElementsCollectionHandler<T>
{
	public ObservableCollectionElementPropertyChangeListenerManager(PropertyChangeListener elementListener)
	{
		super(elementListener);
	}

	public ObservableCollectionElementPropertyChangeListenerManager(String propertyName, PropertyChangeListener elementListener)
	{
		super(propertyName, elementListener);
	}


}

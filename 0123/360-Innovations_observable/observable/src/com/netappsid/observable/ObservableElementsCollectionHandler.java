package com.netappsid.observable;

import java.beans.PropertyChangeListener;

import com.netappsid.observable.listenerManager.AbstractObservableCollectionElementListenerManager;
import com.netappsid.observable.listenerManager.ObservableCollectionElementPropertyChangeListenerManager;

/**
 * Implementation of ObservableListListener that manages registration of a PropertyChangeListener on the elements of the list.
 * 
 * TODO: Add the functionality of managing registration of more then one PropertyChangeListener if necessary. TODO: Class is coupled with
 * com.netappsid.bo.model.Model. See if there is a way to remove this coupling.
 * 
 * <p>
 * <b>The rules for installing this</b> are defined in the description of AbstractObservableCollectionElementListenerManager.
 * </p>
 * 
 * @author Eric Bélanger
 * @author NetAppsID Inc.
 * @deprecated Use {@link ObservableCollectionElementPropertyChangeListenerManager} instead.
 */
@Deprecated
public class ObservableElementsCollectionHandler<T extends ObservableByName> extends AbstractObservableCollectionElementListenerManager<T>
{
	private final PropertyChangeListener elementListener;
	private final String propertyName;

	public ObservableElementsCollectionHandler(PropertyChangeListener elementListener)
	{
		this(null, elementListener);
	}

	public ObservableElementsCollectionHandler(String propertyName, PropertyChangeListener elementListener)
	{
		assert elementListener != null;
		this.elementListener = elementListener;
		this.propertyName = propertyName;
	}

	@Override
	protected void addElementListeners(T element)
	{
		if (propertyName != null)
		{
			element.addPropertyChangeListener(propertyName, elementListener);
		}
		else
		{
			element.addPropertyChangeListener(elementListener);
		}
	}

	@Override
	protected void removeElementListeners(T element)
	{
		if (propertyName != null)
		{
			element.removePropertyChangeListener(propertyName, elementListener);
		}
		else
		{
			element.removePropertyChangeListener(elementListener);
		}
	}
}

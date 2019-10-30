package com.netappsid.observable.listenerManager;

import com.netappsid.observable.CollectionChangeEvent;
import com.netappsid.observable.CollectionChangeListener;
import com.netappsid.observable.ObservableCollection;

/**
 * <p>
 * An abstract class that offers the basic functionality for maintaining listeners on the elements of an {@link ObservableCollection}. It will listen to that
 * ObservableCollection and will call {@link #addElementListeners(Object)} and {@link #removeElementListeners(Object)} when elements are added and removed.
 * </p>
 * <p>
 * <b>Rules for installing this: </b>Unlike most {@link CollectionChangeListener}s, the {@link AbstractObservableCollectionElementListenerManager} must not be
 * added/removed directly to/from its ObservableCollection. Instead, the {@link #install(ObservableCollection)} and {@link #uninstall(ObservableCollection)}
 * methods must be used - they do that and more.
 * </p>
 * 
 * @author ftaillefer
 * 
 */
public abstract class AbstractObservableCollectionElementListenerManager<T> implements CollectionChangeListener<T>
{

	@Override
	public void onCollectionChange(CollectionChangeEvent<T> event)
	{
		for (T element : event.getAdded())
		{
			addElementListeners(element);
		}

		for (T element : event.getRemoved())
		{
			removeElementListeners(element);
		}
	}

	/**
	 * Registers itself to the specified ObservableCollection and registers listeners on all elements present in the ObservableCollection.
	 */
	public void install(ObservableCollection<T> observableCollection)
	{
		observableCollection.addCollectionChangeListener(this);

		for (T element : observableCollection)
		{
			addElementListeners(element);
		}
	}

	/**
	 * Unregisters itself from the specified ObservableCollection and unregisters listeners on all elements present in the ObservableCollection.
	 */
	public void uninstall(ObservableCollection<T> observableCollection)
	{
		observableCollection.removeCollectionChangeListener(this);

		for (T element : observableCollection)
		{
			removeElementListeners(element);
		}
	}

	/**
	 * Registers listeners on the provided element. Called when installing <i>this</i> and when an element is added to the {@link ObservableCollection}.
	 * 
	 * @param element
	 */
	protected abstract void addElementListeners(T element);

	/**
	 * Unregisters listeners on the provided element. Called when uninstalling <i>this</i> and when an element is removed from the {@link ObservableCollection}.
	 * 
	 * @param element
	 */
	protected abstract void removeElementListeners(T element);
}

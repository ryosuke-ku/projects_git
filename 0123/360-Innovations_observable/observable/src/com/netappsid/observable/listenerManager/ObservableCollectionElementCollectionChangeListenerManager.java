package com.netappsid.observable.listenerManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.netappsid.observable.CollectionChangeListener;
import com.netappsid.observable.ObservableCollection;
import com.netappsid.utils.ReflectionUtils;

/**
 * <p>
 * A class whose role is to listen to an {@link ObservableCollection} to maintain a {@link CollectionChangeListener} on a specific property of each element.
 * This property must be an ObservableCollection of its own.
 * </p>
 * <p>
 * <b>The rules for installing this</b> are defined in the description of {@link AbstractObservableCollectionElementListenerManager}.
 * </p>
 * <p>
 * If the CollectionChangeListener to install on each element happens to be an {@link AbstractObservableCollectionElementListenerManager}, it will be installed
 * properly using those rules.
 * </p>
 * 
 * 
 * @author ftaillefer
 * 
 * @param <T>
 *            The type of the elements in the collection <code>this</code> will manage. This is <i>not</i> the type of the elements in the collections on which
 *            <code>this</code> will install a CollectionChangeListener.
 */
public class ObservableCollectionElementCollectionChangeListenerManager<T> extends AbstractObservableCollectionElementListenerManager<T>
{
	
	private final CollectionChangeListener<?> collectionChangeListener;
	private final Class<T> clazz;
	private final Method getter;

	/**
	 * Constructor which defines this ObservableCollectionChangeListenerManager. However, the manager will not serve any purpose until it is installed using
	 * {@link #install(ObservableCollection)}.
	 * 
	 * @param clazz
	 *            The Class of the elements in the collection <code>this</code> will manage.
	 * @param propertyName
	 *            A propertyName of class, whose value should be an {@link ObservableCollection} on which we want to maintain a CollectionChangeListener.
	 * @param collectionChangeListener
	 *            The CollectionChangeListener to maintain on propertyName.
	 * @throws IllegalArgumentException
	 *             If unable to obtain a getter for clazz.propertyName.
	 */
	public ObservableCollectionElementCollectionChangeListenerManager(Class<T> clazz, String propertyName, CollectionChangeListener<?> collectionChangeListener)
			throws IllegalArgumentException
	{
		super();
		Preconditions.checkNotNull(propertyName, "propertyName must not be null");
		this.collectionChangeListener = Preconditions.checkNotNull(collectionChangeListener, "collectionChangeListener must not be null");
		this.clazz = Preconditions.checkNotNull(clazz, "clazz must not be null");

		// Find the getter for obtaining propertyName of clazz
		getter = ReflectionUtils.findGetterOrSetter(clazz, propertyName, false);

		// This object is useless without a getter...
		if (getter == null)
		{
			throw new IllegalArgumentException("Could not find a getter for property " + propertyName + " of class " + clazz.getName() + "!");
		}

		// In theory, it would be sensible to check the getter type here to ensure it's an ObservableCollection.
		// However, in practice a bunch of classes that return an ObservableCollection declare a List instead for some reason,
		// so we'll have to wait until the cast at runtime to fail if the result of the invocation isn't one.
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void addElementListeners(T element)
	{
		// There's no reason to try to install listeners on a null element (and crash in the process)
		if (element != null)
		{
			// Fail fast if element is not actually a T
			clazz.cast(element);
			try
			{
				ObservableCollection collection = (ObservableCollection) getter.invoke(element);

				// If this is not just any CollectionChangeListener but also a ListenerManager, install it properly
				if (collectionChangeListener instanceof AbstractObservableCollectionElementListenerManager)
				{
					((AbstractObservableCollectionElementListenerManager<?>) collectionChangeListener).install(collection);
				}
				else
				{
					collection.addCollectionChangeListener(collectionChangeListener);
				}
			}
			catch (IllegalAccessException e)
			{
				Throwables.propagate(e);
			}
			catch (InvocationTargetException e)
			{
				Throwables.propagate(e.getCause());
			}
		}

	}

	@Override
	protected void removeElementListeners(T element)
	{
		// There's no reason to try to uninstall listeners from a null element (and crash in the process)
		if (element != null)
		{
			// Fail fast if element is not actually a T
			clazz.cast(element);
			try
			{
				ObservableCollection collection = (ObservableCollection) getter.invoke(element);

				// If this is not just any CollectionChangeListener but also a ListenerManager, uninstall it properly
				if (collectionChangeListener instanceof AbstractObservableCollectionElementListenerManager)
				{
					((AbstractObservableCollectionElementListenerManager) collectionChangeListener).uninstall(collection);
				}
				else
				{
					collection.removeCollectionChangeListener(collectionChangeListener);
				}
			}
			catch (IllegalAccessException e)
			{
				Throwables.propagate(e);
			}
			catch (InvocationTargetException e)
			{
				Throwables.propagate(e.getCause());
			}
		}
	}

}

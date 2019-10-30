/**
 * 
 */
package com.netappsid.observable;

import java.util.Collection;


/**
 * @author xjodoin
 * @author NetAppsID inc.
 * 
 * @version
 * 
 */
public class ObservableCollectionDecorator<E, T extends Collection<E> & InternalObservableCollection<E, Collection<E>>> extends
		AbstractObservableCollectionDecorator<E, T>
{

	/**
	 * @param source
	 */
	public ObservableCollectionDecorator(T source, InternalObservableCollectionSupport<E> support)
	{
		super(source, support);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.AbstractObservableCollectionDecorator#newSupport()
	 */
	@Override
	protected InternalObservableCollectionSupport<E> newSupport()
	{
		throw new UnsupportedOperationException("ObservableCollectionDecorator is not directly observable");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.AbstractObservableCollectionDecorator#copyOf(java.util.Collection)
	 */
	@Override
	protected T copyOf(T internal)
	{
		throw new UnsupportedOperationException("ObservableCollectionDecorator is not directly observable");
	}

}

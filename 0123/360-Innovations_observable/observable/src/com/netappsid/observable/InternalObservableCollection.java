/**
 * 
 */
package com.netappsid.observable;

import com.netappsid.observable.ObservableCollection;
import com.netappsid.observable.InternalObservableCollectionSupport;

/**
 * @author xjodoin
 * @author NetAppsID inc.
 * 
 * @version
 * 
 */
public interface InternalObservableCollection<E, T> extends ObservableCollection<E>
{
	T copyInternal();

	/**
	 * @return
	 */
	InternalObservableCollectionSupport<E> getSupport();
}

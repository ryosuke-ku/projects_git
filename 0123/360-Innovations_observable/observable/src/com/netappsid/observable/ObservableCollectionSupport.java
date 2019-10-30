/**
 * 
 */
package com.netappsid.observable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

/**
 * @author xjodoin
 * @author NetAppsID inc.
 * 
 * @version
 * 
 */
public interface ObservableCollectionSupport<E>
{

	void fireCollectionChangeEvent(CollectionChangeEvent<E> collectionChangeEvent);

	public CollectionChangeEvent<E> newCollectionChangeEvent(CollectionDifference<E> difference);

	public CollectionChangeEvent<E> newCollectionChangeEvent(CollectionDifference<E> difference, Object index);

	public void addCollectionChangeListener(CollectionChangeListener listener);

	public void removeCollectionChangeListener(CollectionChangeListener listener);

	public ImmutableList<CollectionChangeListener<E>> getCollectionChangeListeners();

	@Deprecated
	void fireCollectionChangeEvent(ImmutableSet<E> oldSet, ImmutableSet<E> newSet);

	@Deprecated
	void fireCollectionChangeEvent(ImmutableList<E> oldList, ImmutableList<E> newList);

	@Deprecated
	void fireCollectionChangeEvent(ImmutableList<E> oldList, ImmutableList<E> newList, int index);
}

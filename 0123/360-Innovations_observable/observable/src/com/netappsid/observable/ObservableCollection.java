package com.netappsid.observable;

import java.io.Serializable;

import com.google.common.collect.ImmutableList;

public interface ObservableCollection<E> extends Iterable<E>, Serializable
{
	void addCollectionChangeListener(CollectionChangeListener<E> listener);

	void removeCollectionChangeListener(CollectionChangeListener<E> listener);

	public ImmutableList<CollectionChangeListener<E>> getCollectionChangeListeners();

	void executeBatchAction(BatchAction action);

	void apply(CollectionDifference<E> difference);

	void unapply(CollectionDifference<E> difference);

	int size();

	boolean isEmpty();

}

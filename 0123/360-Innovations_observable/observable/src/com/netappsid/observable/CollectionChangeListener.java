package com.netappsid.observable;

public interface CollectionChangeListener<E>
{
	void onCollectionChange(CollectionChangeEvent<E> event);
}

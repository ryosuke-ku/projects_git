package com.netappsid.observable;


public interface InternalObservableCollectionSupport<E> extends ObservableCollectionSupport<E>
{
	public void fireCollectionChangeEvent();

	public void fireCollectionChangeEvent(Object index);

	public void createSnapshot();

}
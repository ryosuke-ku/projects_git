package com.netappsid.observable;

public interface ObservableCollectionSupportFactory
{
	<E> ObservableCollectionSupport<E> newObservableCollectionSupport(ObservableCollection<E> source);
}

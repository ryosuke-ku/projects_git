package com.netappsid.observable;

public class SwingObservableCollectionSupportFactory implements ObservableCollectionSupportFactory
{
	@Override
	public <E> ObservableCollectionSupport<E> newObservableCollectionSupport(ObservableCollection<E> source)
	{
		return new SwingObservableCollectionSupport<E, ObservableCollection<E>>(source);
	}
}
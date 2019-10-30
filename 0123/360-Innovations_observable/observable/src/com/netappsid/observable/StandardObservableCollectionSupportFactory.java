package com.netappsid.observable;

public class StandardObservableCollectionSupportFactory implements ObservableCollectionSupportFactory
{
	@Override
	public <E> ObservableCollectionSupport<E> newObservableCollectionSupport(ObservableCollection<E> source)
	{
		if (source instanceof InternalObservableCollection)
		{
			return ((InternalObservableCollection) source).getSupport();
		}
		else
		{
			return new DefaultObservableCollectionSupport<E>(source);
		}
	}
}
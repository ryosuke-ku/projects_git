package com.netappsid.observable;

import java.util.Collection;

public class ClearAndAddAllBatchAction<T> implements BatchAction<Collection<T>>
{
	private Collection<T> newCollection;

	public ClearAndAddAllBatchAction(Collection<T> newCollection)
	{
		this.newCollection = newCollection;
	}
	
	@Override
	public void execute(Collection<T> collection)
	{
		collection.clear();
		
		if (this.newCollection != null)
		{
			collection.addAll(this.newCollection);
		}
	}
}

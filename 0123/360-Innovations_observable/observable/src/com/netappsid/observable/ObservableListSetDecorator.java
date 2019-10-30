package com.netappsid.observable;

import com.netappsid.collection.ListSet;

class ObservableListSetDecorator<E> extends ObservableListDecorator<E> implements ObservableListSet<E>
{
	ObservableListSetDecorator(ListSet<E> source)
	{
		super(source);
	}
}

package com.netappsid.observable;

import java.io.Serializable;
import java.util.List;

public class ObservableUnsafeList<E> extends ObservableListDecorator<E> implements Serializable
{
	ObservableUnsafeList(ObservableList<? super E> source)
	{
		super((ObservableList) source);
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex)
	{
		ObservableList<E> subList = (ObservableList<E>) super.subList(fromIndex, toIndex);
		return new ObservableUnsafeList(subList);
	}

	public static <E> ObservableUnsafeList<E> of(ObservableList<? super E> source)
	{
		return new ObservableUnsafeList(source);
	}
}

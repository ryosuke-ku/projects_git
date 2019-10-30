package com.netappsid.observable;


public interface BatchAction<T>
{
	void execute(T collection);
}

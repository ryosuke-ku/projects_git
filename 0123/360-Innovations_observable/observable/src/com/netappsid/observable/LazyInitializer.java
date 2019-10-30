package com.netappsid.observable;

import java.util.List;

public interface LazyInitializer<E>
{
	List<E> initialize();
}

package com.netappsid.observable;

import java.beans.PropertyChangeListener;

import com.jgoodies.binding.beans.Observable;

public interface ObservableByName extends Observable
{
	void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);
	void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);
}

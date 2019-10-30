package org.vaadin.smartgwt.client.core;

import org.vaadin.smartgwt.client.ui.utils.PainterHelper;

import com.smartgwt.client.core.DataClass;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.UIDL;

public abstract class VDataClass<T extends DataClass> extends VJSObject<T>
{
	public static <T extends DataClass> VDataClass<T> getVDataClass(ApplicationConnection client, T dataClass)
	{
		return (VDataClass<T>) client.getPaintable(dataClass.getAttributeAsString(ATTRIBUTE_PID));
	}

	public static <T extends DataClass> T getDataClass(ApplicationConnection client, String pid)
	{
		return ((VDataClass<T>) client.getPaintable(pid)).getJSObject();
	}

	protected VDataClass(T dataClass)
	{
		super(dataClass);
	}

	@Override
	protected final void setStringAttribute(String name, String value)
	{
		getJSObject().setAttribute(name, value);
	}

	@Override
	protected final void updateJSObjectAttributes(UIDL uidl, ApplicationConnection client)
	{
		PainterHelper.updateDataObject(client, getJSObject(), uidl);
	}

	@Override
	protected void preAttributeUpdateFromUIDL(UIDL uidl, ApplicationConnection client)
	{

	}

	@Override
	protected void postAttributeUpdateFromUIDL(UIDL uidl, ApplicationConnection client)
	{

	}
}

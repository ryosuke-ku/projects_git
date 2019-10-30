package org.vaadin.smartgwt.client.ui.toolbar;

import org.vaadin.smartgwt.client.core.PaintableListListener;
import org.vaadin.smartgwt.client.core.PaintablePropertyUpdater;
import org.vaadin.smartgwt.client.ui.utils.PainterHelper;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.Paintable;
import com.vaadin.terminal.gwt.client.UIDL;

public class VToolStrip extends ToolStrip implements Paintable
{
	private final Element element = DOM.createDiv();
	private final PaintablePropertyUpdater propertyUpdater = new PaintablePropertyUpdater();

	public VToolStrip()
	{
		propertyUpdater.addPaintableListListener("members", new PaintableListListener()
			{
				@Override
				public void onAdd(Paintable[] source, Integer index, Paintable element)
				{
					if (index == null)
					{
						addMember((Canvas) element);
					}
					else
					{
						addMember((Canvas) element, index);
					}
				}

				@Override
				public void onRemove(Paintable[] source, Integer index, Paintable element)
				{
					removeMember((Canvas) element);
				}
			});
	}

	@Override
	public Element getElement()
	{
		return element;
	}

	@Override
	public void updateFromUIDL(UIDL uidl, ApplicationConnection client)
	{
		propertyUpdater.updateFromUIDL(uidl, client);
		PainterHelper.updateSmartGWTComponent(client, this, uidl);
	}
}
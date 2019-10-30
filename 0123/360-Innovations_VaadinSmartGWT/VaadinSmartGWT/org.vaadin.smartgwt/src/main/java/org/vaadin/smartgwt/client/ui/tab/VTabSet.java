package org.vaadin.smartgwt.client.ui.tab;

import org.vaadin.rpc.client.ClientSideHandler;
import org.vaadin.rpc.client.ClientSideProxy;
import org.vaadin.rpc.shared.Method;
import org.vaadin.smartgwt.client.core.PaintableListListener;
import org.vaadin.smartgwt.client.core.PaintablePropertyUpdater;
import org.vaadin.smartgwt.client.core.VDataClass;
import org.vaadin.smartgwt.client.ui.BaseWidgetExtension;

import com.google.gwt.user.client.Element;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.CloseClickHandler;
import com.smartgwt.client.widgets.tab.events.TabCloseClickEvent;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.Paintable;
import com.vaadin.terminal.gwt.client.UIDL;

public class VTabSet extends TabSet implements Paintable {
	private final BaseWidgetExtension extension = new BaseWidgetExtension(this);
	private final ClientSideProxy rpc = new ClientSideProxy("VTabSet", new ClientSideHandlerImpl());
	private final PaintablePropertyUpdater propertyUpdater = new PaintablePropertyUpdater();
	private String pid;
	private ApplicationConnection client;

	@Override
	public void updateFromUIDL(UIDL uidl, ApplicationConnection client) {
		if (this.pid == null) {
			this.pid = uidl.getId();
			this.client = client;
			
			addCloseClickHandler(new CloseClickHandler() {
				@Override
				public void onCloseClick(TabCloseClickEvent event) {
					VTabSet.this.client.updateVariable(pid, "TabCloseClickEvent.tab", VDataClass.getVDataClass(VTabSet.this.client, event.getTab()), true);
				}
			});

			rpc.register("selectTab", new Method() {
				@Override
				public void invoke(final String methodName, final Object[] data) {
					selectTab((Integer) data[0]);
				}
			});

			propertyUpdater.addPaintableListListener("tabs", new PaintableListListener() {
				@Override
				public void onAdd(Paintable[] source, Integer index, Paintable element) {
					final Tab tab = ((VDataClass<Tab>) element).getJSObject();

					if (index == null) {
						addTab(tab);
					} else {
						addTab(tab, index);
					}
				}

				@Override
				public void onRemove(Paintable[] source, Integer index, Paintable element) {
					removeTab(((VDataClass<Tab>) element).getJSObject());
				}
			});
		}

		propertyUpdater.updateFromUIDL(uidl, client);
		rpc.update(this, uidl, client);
		extension.updateFromUIDL(uidl, client);
	}

	@Override
	public Element getElement() {
		return extension.getElement();
	}

	private class ClientSideHandlerImpl implements ClientSideHandler {
		@Override
		public boolean initWidget(Object[] params) {
			return false;
		}

		@Override
		public void handleCallFromServer(String method, Object[] params) {

		}
	}
}

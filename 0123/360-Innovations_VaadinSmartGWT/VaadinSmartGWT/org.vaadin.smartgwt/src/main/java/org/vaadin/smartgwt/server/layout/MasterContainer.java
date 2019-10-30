package org.vaadin.smartgwt.server.layout;

import java.util.Collections;
import java.util.Iterator;

import org.vaadin.smartgwt.server.BaseWidget;
import org.vaadin.smartgwt.server.Canvas;
import org.vaadin.smartgwt.server.Window;
import org.vaadin.smartgwt.server.core.ComponentList;
import org.vaadin.smartgwt.server.core.ComponentPropertyPainter;
import org.vaadin.smartgwt.server.core.ComponentReference;
import org.vaadin.smartgwt.server.core.RegistrationEntry;
import org.vaadin.smartgwt.server.data.DataSource;
import org.vaadin.smartgwt.server.util.SC;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;

/**
 * The Master Container is the top most container of SmartVaadin applications. Why is it needed? There are objects that exist in a static-like context on the
 * client. DataSources, SC etc. These objects must have a server counterpart and it must be held somewhere.
 */
@com.vaadin.ui.ClientWidget(org.vaadin.smartgwt.client.ui.layout.VMasterContainer.class)
public class MasterContainer extends BaseWidget implements ComponentContainer {
	private final ComponentPropertyPainter paintablePropertyPainter = new ComponentPropertyPainter(this);
	private final ComponentList<NonUIComponent> nonUIComponents = paintablePropertyPainter.addComponentList("nonUIComponents");
	private final ComponentReference<SC> sc = paintablePropertyPainter.addProperty("sc");
	private final ComponentList<DataSource> dataSources = paintablePropertyPainter.addComponentList("dataSources");
	private final ComponentReference<Canvas> pane = paintablePropertyPainter.addProperty("pane");
	private final ComponentList<Window> window = paintablePropertyPainter.addComponentList("window");

	public MasterContainer() {
		sc.set(new SC());
	}

	public Iterator<NonUIComponent> getNonUIComponentIterator() {
		return nonUIComponents.iterator();
	}

	/**
	 * Add a non-visual component to the container.
	 * 
	 * @param component to be added.
	 */
	public void addNonUIComponent(NonUIComponent component) {
		nonUIComponents.add(component);
	}

	/**
	 * Removes a non-visual component from the container.
	 * 
	 * @param component to be removed.
	 */
	public void removeNonUIComponent(NonUIComponent component) {
		nonUIComponents.remove(component);
	}

	public void addDataSource(DataSource dataSource) {
		if (!dataSources.contains(dataSource)) {
			dataSources.add(dataSource);
		}
	}

	public Canvas getPane() {
		return pane.get();
	}

	public void setPane(Canvas pane) {
		this.pane.set(pane);
	}

	public SC getSC() {
		return sc.get();
	}

	public RegistrationEntry register(final Window window) {
		this.window.add(window);
		requestRepaint();

		return new RegistrationEntry() {
			@Override
			public void unregister() {
				MasterContainer.this.window.remove(window);
				requestRepaint();
			}

			@Override
			public boolean isRegistered() {
				return MasterContainer.this.window.contains(window);
			}
		};
	}

	@Override
	public void paintContent(PaintTarget target) throws PaintException {
		paintablePropertyPainter.paintContent(target);
		super.paintContent(target);
	}

	@Override
	public void addComponent(Component c) {

	}

	@Override
	public void removeComponent(Component c) {

	}

	@Override
	public void removeAllComponents() {

	}

	@Override
	public void replaceComponent(Component oldComponent, Component newComponent) {

	}

	@Override
	public Iterator<Component> getComponentIterator() {
		return Collections.<Component> emptyList().iterator();
	}

	@Override
	public void requestRepaintAll() {

	}

	@Override
	public void moveComponentsFrom(ComponentContainer source) {

	}

	@Override
	public void addListener(ComponentAttachListener listener) {

	}

	@Override
	public void removeListener(ComponentAttachListener listener) {

	}

	@Override
	public void addListener(ComponentDetachListener listener) {

	}

	@Override
	public void removeListener(ComponentDetachListener listener) {

	}
}

package org.vaadin.smartgwt.client.ui.form.fields;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.smartgwt.client.core.PaintableListListener;
import org.vaadin.smartgwt.client.core.PaintablePropertyUpdater;
import org.vaadin.smartgwt.client.core.VJSObject;
import org.vaadin.smartgwt.client.ui.grid.VListGridField;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.Paintable;
import com.vaadin.terminal.gwt.client.UIDL;

public class VSelectItem extends VAbstractFormItem<SelectItem, String>
{
	private final PaintablePropertyUpdater propertyUpdater = new PaintablePropertyUpdater();

	public VSelectItem()
	{
		super(new SelectItem());

		propertyUpdater.addPaintableListListener("pickListFields", new PaintableListListener()
			{
				@Override
				public void onAdd(Paintable[] source, Integer index, Paintable element)
				{
					final List<ListGridField> pickListFields = new ArrayList<ListGridField>();

					for (Paintable paintable : source)
					{
						pickListFields.add(((VListGridField) paintable).getJSObject());
					}

					getJSObject().setPickListFields(pickListFields.toArray(new ListGridField[0]));
				}

				@Override
				public void onRemove(Paintable[] source, Integer index, Paintable element)
				{

				}
			});
	}

	@Override
	protected String getUIDLFormItemValue(UIDL uidl, String attributeName)
	{
		return uidl.getStringAttribute(attributeName);
	}

	@Override
	protected String getFormItemValue()
	{
		return getJSObject().getValueAsString();
	}

	@Override
	protected void postAttributeUpdateFromUIDL(UIDL uidl, ApplicationConnection client)
	{
		propertyUpdater.updateFromUIDL(uidl, client);

		// the dataSource property is manually managed for now. Using the automatic painter doesn't work properly
		if (uidl.hasAttribute("optionDataSource"))
		{
			final Paintable paintable = uidl.getPaintableAttribute("optionDataSource", client);
			getJSObject().setOptionDataSource((DataSource) ((VJSObject<?>) paintable).getJSObject());
		}
	}
}

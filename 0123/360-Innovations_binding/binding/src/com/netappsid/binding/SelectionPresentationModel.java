package com.netappsid.binding;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import com.jgoodies.binding.value.ValueModel;
import com.netappsid.binding.beans.support.ChangeSupportFactory;
import com.netappsid.binding.selection.SelectionHolder;
import com.netappsid.binding.selection.SelectionModel;
import com.netappsid.binding.state.StateModel;
import com.netappsid.binding.value.ValueHolder;
import com.netappsid.observable.ObservableCollectionSupportFactory;
import com.netappsid.validate.Validate;

/**
 * 
 * 
 * @author Eric Belanger
 * @author NetAppsID Inc.
 * @version $Revision: 1.12 $
 */
@SuppressWarnings("serial")
public class SelectionPresentationModel extends PresentationModel
{
	public static final String DEFAULT_SELECTION = "selected";
	public static final String PROPERTYNAME_BEAN_LIST = "beanList";

	private final ValueModel beanListChannel;
	private Map<String, SelectionModel> selectionModels;

	public SelectionPresentationModel(ChangeSupportFactory changeSupportFactory, ObservableCollectionSupportFactory observableCollectionSupportFactory,
			Class<?> beanClass)
	{
		this(changeSupportFactory, observableCollectionSupportFactory, beanClass, new ValueHolder(null, true));
	}

	public SelectionPresentationModel(ChangeSupportFactory changeSupportFactory, ObservableCollectionSupportFactory observableCollectionSupportFactory,
			Class<?> beanClass, List<?> beanList)
	{
		this(changeSupportFactory, observableCollectionSupportFactory, beanClass, new ValueHolder(changeSupportFactory, beanList, true));
	}

	public SelectionPresentationModel(ChangeSupportFactory changeSupportFactory, ObservableCollectionSupportFactory observableCollectionSupportFactory,
			Class<?> beanClass, ValueModel beanListChannel)
	{
		super(changeSupportFactory, observableCollectionSupportFactory);
		this.beanListChannel = beanListChannel;
		setBeanClass(beanClass);
	}

	@Override
	public Object getBean()
	{
		return getSelectedBean(DEFAULT_SELECTION);
	}

	@Override
	public ValueModel getBeanChannel()
	{
		return getSelectedBeanChannel(DEFAULT_SELECTION);
	}

	public List<?> getBeanList()
	{
		return (List<?>) getBeanListChannel().getValue();
	}

	public ValueModel getBeanListChannel()
	{
		return beanListChannel;
	}

	public Object getSelectedBean(String selectionKey)
	{
		return getSubModel(selectionKey).getBean();
	}

	public ValueModel getSelectedBeanChannel(String selectionKey)
	{
		return getSubModel(selectionKey).getBeanChannel();
	}

	public SelectionModel getSelectionModel()
	{
		return getSelectionModel(DEFAULT_SELECTION);
	}

	public SelectionModel getSelectionModel(String selectionKey)
	{
		SelectionModel selectionModel = getSelectionModels().get(selectionKey);

		if (selectionModel == null)
		{
			selectionModel = new SelectionHolder((List<Object>) beanListChannel.getValue(), getChangeSupportFactory());
			selectionModel.addSelectionChangeListener(new SelectionChangeHandler(selectionKey));
			getSelectionModels().put(selectionKey, selectionModel);
		}

		return selectionModel;
	}

	@Override
	public Object getValue(String propertyName)
	{
		return getValueModel(propertyName).getValue();
	}

	@Override
	public ValueModel getValueModel(String propertyName)
	{
		ValueModel valueModel = null;
		int index = propertyName.lastIndexOf('.');

		if (index != -1)
		{
			valueModel = getSubModel(propertyName.substring(0, index)).getValueModel(propertyName.substring(index + 1, propertyName.length()));
		}
		else
		{
			throw new IllegalArgumentException("Property name must start with a selection key.");
		}

		return valueModel;
	}

	@Override
	public void releaseBeanListeners()
	{
		releaseSelectedBeanListeners(DEFAULT_SELECTION);
	}

	public void releaseSelectedBeanListeners(String selectionKey)
	{
		getSubModel(selectionKey).releaseBeanListeners();
	}

	@Override
	public void removeBeanPropertyChangeListener(PropertyChangeListener listener)
	{
		removeSelectedBeanPropertyChangeListener(DEFAULT_SELECTION, listener);
	}

	@Override
	public void removeBeanPropertyChangeListener(String propertyName, PropertyChangeListener listener)
	{
		int index = propertyName.indexOf('.');

		if (index != -1)
		{
			getSubModel(propertyName.substring(0, index)).removeBeanPropertyChangeListener(propertyName.substring(index + 1, propertyName.length()), listener);
		}
		else
		{
			throw new IllegalArgumentException("Property name must start with a selection key.");
		}
	}

	public void removeSelectedBeanPropertyChangeListener(String selectionKey, PropertyChangeListener listener)
	{
		getSubModel(selectionKey).removeBeanPropertyChangeListener(listener);
	}

	@Override
	public void setBean(Object newBean)
	{
		setSelectedBean(DEFAULT_SELECTION, newBean);
	}

	public void setBeanList(List<?> beanList)
	{
		beanListChannel.setValue(beanList);
	}

	public void setSelectedBean(String selectionKey, Object newBean)
	{
		getSubModel(selectionKey).setBean(newBean);
	}

	@Override
	public void setValue(String propertyName, Object newValue)
	{
		getValueModel(propertyName).setValue(newValue);
	}

	@Override
	public StateModel getStateModel()
	{
		return null;
	}

	private Map<String, SelectionModel> getSelectionModels()
	{
		if (selectionModels == null)
		{
			selectionModels = new HashMap<String, SelectionModel>();
		}

		return selectionModels;
	}

	private final class SelectionChangeHandler implements PropertyChangeListener
	{
		private String selectionKey;

		public SelectionChangeHandler(String selectionKey)
		{
			setSelectionKey(selectionKey);
		}

		@Override
		@SuppressWarnings("unchecked")
		public void propertyChange(PropertyChangeEvent evt)
		{
			SortedSet<Integer> indexes = (SortedSet<Integer>) evt.getNewValue();

			if (indexes != null && indexes.size() == 1 && getBeanList() != null && getBeanList().size() > indexes.first())
			{
				setSelectedBean(selectionKey, getBeanList().get(indexes.first()));
			}
			else
			{
				setSelectedBean(selectionKey, null);
			}
		}

		public void setSelectionKey(String selectionKey)
		{
			this.selectionKey = Validate.notNull(selectionKey, "Selection key must not be null.");
		}
	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub

	}
}

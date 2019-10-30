package org.vaadin.smartgwt.server;

import org.vaadin.smartgwt.server.types.Alignment;
import org.vaadin.smartgwt.server.types.SelectionType;
import org.vaadin.smartgwt.server.types.State;
import org.vaadin.smartgwt.server.types.VerticalAlignment;
import org.vaadin.smartgwt.server.util.EnumUtil;

public class StatefulCanvas extends Canvas
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Behavior on state changes -- BUTTON, RADIO or CHECKBOX
	 * 
	 * <br>
	 * <br>
	 * If this method is called after the component has been drawn/initialized: Update the 'actionType' for this canvas (radio / checkbox / button) If the
	 * canvas is currently selected, and the passed in actionType is 'button' this method will deselect the canvas.
	 * 
	 * @param actionType
	 *            actionType Default value is "button"
	 * @see com.smartgwt.client.docs.State State overview and related methods
	 */
	public void setActionType(SelectionType actionType)
	{
		setAttribute("actionType", actionType.getValue(), true);
	}

	/**
	 * Behavior on state changes -- BUTTON, RADIO or CHECKBOX
	 * 
	 * 
	 * @return Return the 'actionType' for this canvas (radio / checkbox / button)
	 * @see com.smartgwt.client.docs.State State overview and related methods
	 */
	public SelectionType getActionType()
	{
		return EnumUtil.getEnum(SelectionType.values(), getAttribute("actionType"));
	}

	/**
	 * Horizontal alignment of this component's title.
	 * 
	 * @param align
	 *            align Default value is Canvas.CENTER
	 * @see com.smartgwt.client.docs.Appearance Appearance overview and related methods
	 */
	public void setAlign(Alignment align)
	{
		setAttribute("align", align.getValue(), true);
	}

	/**
	 * Horizontal alignment of this component's title.
	 * 
	 * 
	 * @return Alignment
	 * @see com.smartgwt.client.docs.Appearance Appearance overview and related methods
	 */
	public Alignment getAlign()
	{
		return EnumUtil.getEnum(Alignment.values(), getAttribute("align"));
	}

	/**
	 * If true, ignore the specified size of this widget and always size just large enough to accommodate the title. If <code>setWidth()</code> is explicitly
	 * called on an autoFit:true button, autoFit will be reset to <code>false</code>.
	 * <P>
	 * Note that for StretchImgButton instances, autoFit will occur horizontally only, as unpredictable vertical sizing is likely to distort the media. If you
	 * do want vertical auto-fit, this can be achieved by simply setting a small height, and having overflow:"visible"
	 * 
	 * <br>
	 * <br>
	 * If this method is called after the component has been drawn/initialized: Setter method for the
	 * {@link com.smartgwt.client.widgets.StatefulCanvas#getAutoFit autoFit} property. Pass in true or false to turn autoFit on or off. When autoFit is set to
	 * <code>false</code>, canvas will be resized to it's previously specified size.
	 * 
	 * @param autoFit
	 *            New autoFit setting.. Default value is null
	 * @see com.smartgwt.client.docs.Sizing Sizing overview and related methods
	 */
	public void setAutoFit(Boolean autoFit)
	{
		setAttribute("autoFit", autoFit, true);
	}

	/**
	 * If true, ignore the specified size of this widget and always size just large enough to accommodate the title. If <code>setWidth()</code> is explicitly
	 * called on an autoFit:true button, autoFit will be reset to <code>false</code>.
	 * <P>
	 * Note that for StretchImgButton instances, autoFit will occur horizontally only, as unpredictable vertical sizing is likely to distort the media. If you
	 * do want vertical auto-fit, this can be achieved by simply setting a small height, and having overflow:"visible"
	 * 
	 * 
	 * @return Boolean
	 * @see com.smartgwt.client.docs.Sizing Sizing overview and related methods
	 */
	public Boolean getAutoFit()
	{
		return getAttributeAsBoolean("autoFit");
	}

	/**
	 * Base CSS style. As the component changes state and/or is selected, suffixes will be added to the base style.
	 * <P>
	 * When the component changes state (eg becomes disabled), a suffix will be appended to this style name, reflecting the following states: "Over", "Down", or
	 * "Disabled".
	 * <P>
	 * If the widget is selected, the suffixes will be "Selected", "SelectedOver", etc.
	 * <P>
	 * If the widget has focus and {@link com.smartgwt.client.widgets.StatefulCanvas#getShowFocused showFocused} is true, and
	 * {@link com.smartgwt.client.widgets.StatefulCanvas#getShowFocusedAsOver showFocusedAsOver} is false, the suffixes will be "Focused", "FocusedOver", etc,
	 * or if the widget is both selected and focused, "SelectedFocused", "SelectedFocusedOver", etc.
	 * <P>
	 * For example, if <code>baseStyle</code> is set to "button", this component is {@link com.smartgwt.client.widgets.StatefulCanvas#isSelected selected} and
	 * the mouse cursor is over this component, the style "buttonSelectedOver" will be used.
	 * 
	 * <br>
	 * <br>
	 * If this method is called after the component has been drawn/initialized: Sets the base CSS style. As the component changes state and/or is selected,
	 * suffixes will be added to the base style.
	 * 
	 * @param baseStyle
	 *            new base style. Default value is null
	 */
	public void setBaseStyle(String baseStyle)
	{
		setAttribute("baseStyle", baseStyle, true);
	}

	/**
	 * Base CSS style. As the component changes state and/or is selected, suffixes will be added to the base style.
	 * <P>
	 * When the component changes state (eg becomes disabled), a suffix will be appended to this style name, reflecting the following states: "Over", "Down", or
	 * "Disabled".
	 * <P>
	 * If the widget is selected, the suffixes will be "Selected", "SelectedOver", etc.
	 * <P>
	 * If the widget has focus and {@link com.smartgwt.client.widgets.StatefulCanvas#getShowFocused showFocused} is true, and
	 * {@link com.smartgwt.client.widgets.StatefulCanvas#getShowFocusedAsOver showFocusedAsOver} is false, the suffixes will be "Focused", "FocusedOver", etc,
	 * or if the widget is both selected and focused, "SelectedFocused", "SelectedFocusedOver", etc.
	 * <P>
	 * For example, if <code>baseStyle</code> is set to "button", this component is {@link com.smartgwt.client.widgets.StatefulCanvas#isSelected selected} and
	 * the mouse cursor is over this component, the style "buttonSelectedOver" will be used.
	 * 
	 * 
	 * @return String
	 */
	public String getBaseStyle()
	{
		return getAttributeAsString("baseStyle");
	}

	/**
	 * Optional icon to be shown with the button title text.
	 * <P>
	 * Specify as the partial URL to an image, relative to the imgDir of this component.
	 * 
	 * <br>
	 * <br>
	 * If this method is called after the component has been drawn/initialized: Change the icon being shown next to the title text.
	 * 
	 * @param icon
	 *            URL of new icon. Default value is null
	 * @see com.smartgwt.client.docs.ButtonIcon ButtonIcon overview and related methods
	 */
	public void setIcon(String icon)
	{
		setAttribute("icon", icon, true);
	}

	/**
	 * Optional icon to be shown with the button title text.
	 * <P>
	 * Specify as the partial URL to an image, relative to the imgDir of this component.
	 * 
	 * 
	 * @return String
	 * @see com.smartgwt.client.docs.ButtonIcon ButtonIcon overview and related methods
	 */
	// public String getIcon()
	// {
	// return getAttributeAsString("icon");
	// }

	/**
	 * Height in pixels of the icon image.
	 * <P>
	 * If unset, defaults to <code>iconSize</code>
	 * 
	 * @param iconHeight
	 *            iconHeight Default value is null
	 * @throws IllegalStateException
	 *             this property cannot be changed after the component has been created
	 * @see com.smartgwt.client.docs.ButtonIcon ButtonIcon overview and related methods
	 */
	public void setIconHeight(Integer iconHeight) throws IllegalStateException
	{
		setAttribute("iconHeight", iconHeight, false);
	}

	/**
	 * Height in pixels of the icon image.
	 * <P>
	 * If unset, defaults to <code>iconSize</code>
	 * 
	 * 
	 * @return Integer
	 * @see com.smartgwt.client.docs.ButtonIcon ButtonIcon overview and related methods
	 */
	public Integer getIconHeight()
	{
		return getAttributeAsInt("iconHeight");
	}

	/**
	 * If this button is showing an icon should it appear to the left or right of the title? valid options are <code>"left"</code> and <code>"right"</code>.
	 * 
	 * <br>
	 * <br>
	 * If this method is called after the component has been drawn/initialized: Changes the orientation of the icon relative to the text of the button.
	 * 
	 * @param iconOrientation
	 *            The new orientation of the icon relative to the text of the button.. Default value is "left"
	 * @throws IllegalStateException
	 *             this property cannot be changed after the component has been created
	 * @see com.smartgwt.client.docs.ButtonIcon ButtonIcon overview and related methods
	 */
	public void setIconOrientation(String iconOrientation) throws IllegalStateException
	{
		setAttribute("iconOrientation", iconOrientation, false);
	}

	/**
	 * If this button is showing an icon should it appear to the left or right of the title? valid options are <code>"left"</code> and <code>"right"</code>.
	 * 
	 * 
	 * @return String
	 * @see com.smartgwt.client.docs.ButtonIcon ButtonIcon overview and related methods
	 */
	public String getIconOrientation()
	{
		return getAttributeAsString("iconOrientation");
	}

	/**
	 * Size in pixels of the icon image.
	 * <P>
	 * The <code>iconWidth</code> and <code>iconHeight</code> properties can be used to configure width and height separately.
	 * 
	 * @param iconSize
	 *            iconSize Default value is 16
	 * @throws IllegalStateException
	 *             this property cannot be changed after the component has been created
	 * @see com.smartgwt.client.docs.ButtonIcon ButtonIcon overview and related methods
	 */
	public void setIconSize(int iconSize) throws IllegalStateException
	{
		setAttribute("iconSize", iconSize, false);
	}

	/**
	 * Size in pixels of the icon image.
	 * <P>
	 * The <code>iconWidth</code> and <code>iconHeight</code> properties can be used to configure width and height separately.
	 * 
	 * 
	 * @return int
	 * @see com.smartgwt.client.docs.ButtonIcon ButtonIcon overview and related methods
	 */
	public int getIconSize()
	{
		return getAttributeAsInt("iconSize");
	}

	/**
	 * Width in pixels of the icon image.
	 * <P>
	 * If unset, defaults to <code>iconSize</code>
	 * 
	 * @param iconWidth
	 *            iconWidth Default value is null
	 * @throws IllegalStateException
	 *             this property cannot be changed after the component has been created
	 * @see com.smartgwt.client.docs.ButtonIcon ButtonIcon overview and related methods
	 */
	public void setIconWidth(Integer iconWidth) throws IllegalStateException
	{
		setAttribute("iconWidth", iconWidth, false);
	}

	/**
	 * Width in pixels of the icon image.
	 * <P>
	 * If unset, defaults to <code>iconSize</code>
	 * 
	 * 
	 * @return Integer
	 * @see com.smartgwt.client.docs.ButtonIcon ButtonIcon overview and related methods
	 */
	public Integer getIconWidth()
	{
		return getAttributeAsInt("iconWidth");
	}

	/**
	 * Constructor class name for this widgets {@link com.smartgwt.client.widgets.StatefulCanvas#getOverCanvas overCanvas}
	 * <p>
	 * <b>Note : </b> This is an advanced setting
	 * </p>
	 * 
	 * @param overCanvasConstructor
	 *            overCanvasConstructor Default value is "Canvas"
	 */
	public void setOverCanvasConstructor(String overCanvasConstructor)
	{
		setAttribute("overCanvasConstructor", overCanvasConstructor, true);
	}

	/**
	 * Constructor class name for this widgets {@link com.smartgwt.client.widgets.StatefulCanvas#getOverCanvas overCanvas}
	 * 
	 * 
	 * @return String
	 */
	public String getOverCanvasConstructor()
	{
		return getAttributeAsString("overCanvasConstructor");
	}

	/**
	 * String identifier for this canvas's mutually exclusive selection group.
	 * <p>
	 * <b>Note : </b> This is an advanced setting
	 * </p>
	 * 
	 * @param radioGroup
	 *            radioGroup Default value is null
	 * @see com.smartgwt.client.docs.State State overview and related methods
	 */
	public void setRadioGroup(String radioGroup)
	{
		setAttribute("radioGroup", radioGroup, true);
	}

	/**
	 * String identifier for this canvas's mutually exclusive selection group.
	 * 
	 * 
	 * @return String
	 * @see com.smartgwt.client.docs.State State overview and related methods
	 */
	public String getRadioGroup()
	{
		return getAttributeAsString("radioGroup");
	}

	/**
	 * Whether this widget needs to redraw to reflect state change
	 * <p>
	 * <b>Note : </b> This is an advanced setting
	 * </p>
	 * 
	 * @param redrawOnStateChange
	 *            redrawOnStateChange Default value is false
	 * @see com.smartgwt.client.docs.State State overview and related methods
	 */
	public void setRedrawOnStateChange(Boolean redrawOnStateChange)
	{
		setAttribute("redrawOnStateChange", redrawOnStateChange, true);
	}

	/**
	 * Whether this widget needs to redraw to reflect state change
	 * 
	 * 
	 * @return Boolean
	 * @see com.smartgwt.client.docs.State State overview and related methods
	 */
	public Boolean getRedrawOnStateChange()
	{
		return getAttributeAsBoolean("redrawOnStateChange");
	}

	/**
	 * Whether this component is selected. For some components, selection affects appearance.
	 * 
	 * <br>
	 * <br>
	 * If this method is called after the component has been drawn/initialized: Set this object to be selected or deselected.
	 * 
	 * @param selected
	 *            new boolean value of whether or not the object is selected.. Default value is false
	 * @see com.smartgwt.client.docs.State State overview and related methods
	 */
	public void setSelected(Boolean selected)
	{
		setAttribute("selected", selected, true);
	}

	/**
	 * Whether this component is selected. For some components, selection affects appearance.
	 * 
	 * 
	 * @return Boolean
	 * @see com.smartgwt.client.docs.State State overview and related methods
	 */
	public Boolean getSelected()
	{
		return getAttributeAsBoolean("selected");
	}

	/**
	 * Should we visibly change state when disabled?
	 * 
	 * @param showDisabled
	 *            showDisabled Default value is true
	 * @see com.smartgwt.client.docs.State State overview and related methods
	 */
	public void setShowDisabled(Boolean showDisabled)
	{
		setAttribute("showDisabled", showDisabled, true);
	}

	/**
	 * Should we visibly change state when disabled?
	 * 
	 * 
	 * @return Boolean
	 * @see com.smartgwt.client.docs.State State overview and related methods
	 */
	public Boolean getShowDisabled()
	{
		return getAttributeAsBoolean("showDisabled");
	}

	/**
	 * If using an icon for this button, whether to switch the icon image if the button becomes disabled.
	 * 
	 * @param showDisabledIcon
	 *            showDisabledIcon Default value is true
	 * @throws IllegalStateException
	 *             this property cannot be changed after the component has been created
	 * @see com.smartgwt.client.docs.ButtonIcon ButtonIcon overview and related methods
	 */
	public void setShowDisabledIcon(Boolean showDisabledIcon) throws IllegalStateException
	{
		setAttribute("showDisabledIcon", showDisabledIcon, false);
	}

	/**
	 * If using an icon for this button, whether to switch the icon image if the button becomes disabled.
	 * 
	 * 
	 * @return Boolean
	 * @see com.smartgwt.client.docs.ButtonIcon ButtonIcon overview and related methods
	 */
	public Boolean getShowDisabledIcon()
	{
		return getAttributeAsBoolean("showDisabledIcon");
	}

	/**
	 * Should we visibly change state when the mouse goes down in this object?
	 * 
	 * @param showDown
	 *            showDown Default value is false
	 * @see com.smartgwt.client.docs.State State overview and related methods
	 */
	public void setShowDown(Boolean showDown)
	{
		setAttribute("showDown", showDown, true);
	}

	/**
	 * Should we visibly change state when the mouse goes down in this object?
	 * 
	 * 
	 * @return Boolean
	 * @see com.smartgwt.client.docs.State State overview and related methods
	 */
	public Boolean getShowDown()
	{
		return getAttributeAsBoolean("showDown");
	}

	/**
	 * If using an icon for this button, whether to switch the icon image when the mouse goes down on the button.
	 * 
	 * @param showDownIcon
	 *            showDownIcon Default value is false
	 * @throws IllegalStateException
	 *             this property cannot be changed after the component has been created
	 * @see com.smartgwt.client.docs.ButtonIcon ButtonIcon overview and related methods
	 */
	public void setShowDownIcon(Boolean showDownIcon) throws IllegalStateException
	{
		setAttribute("showDownIcon", showDownIcon, false);
	}

	/**
	 * If using an icon for this button, whether to switch the icon image when the mouse goes down on the button.
	 * 
	 * 
	 * @return Boolean
	 * @see com.smartgwt.client.docs.ButtonIcon ButtonIcon overview and related methods
	 */
	public Boolean getShowDownIcon()
	{
		return getAttributeAsBoolean("showDownIcon");
	}

	/**
	 * Should we visibly change state when the canvas receives focus? If {@link com.smartgwt.client.widgets.StatefulCanvas#getShowFocusedAsOver
	 * showFocusedAsOver} is <code>true</code>, the <b><code>"over"</code></b> will be used to indicate focus. Otherwise a separate <b><code>"focused"</code>
	 * </b> state will be used.
	 * 
	 * @param showFocused
	 *            showFocused Default value is false
	 * @see com.smartgwt.client.docs.State State overview and related methods
	 */
	public void setShowFocused(Boolean showFocused)
	{
		setAttribute("showFocused", showFocused, true);
	}

	/**
	 * Should we visibly change state when the canvas receives focus? If {@link com.smartgwt.client.widgets.StatefulCanvas#getShowFocusedAsOver
	 * showFocusedAsOver} is <code>true</code>, the <b><code>"over"</code></b> will be used to indicate focus. Otherwise a separate <b><code>"focused"</code>
	 * </b> state will be used.
	 * 
	 * 
	 * @return Boolean
	 * @see com.smartgwt.client.docs.State State overview and related methods
	 */
	public Boolean getShowFocused()
	{
		return getAttributeAsBoolean("showFocused");
	}

	/**
	 * If {@link com.smartgwt.client.widgets.StatefulCanvas#getShowFocused showFocused} is true for this widget, should the <code>"over"</code> state be used to
	 * indicate the widget as focused. If set to false, a separate <code>"focused"</code> state will be used.
	 * 
	 * @param showFocusedAsOver
	 *            showFocusedAsOver Default value is true
	 * @see com.smartgwt.client.docs.State State overview and related methods
	 */
	public void setShowFocusedAsOver(Boolean showFocusedAsOver)
	{
		setAttribute("showFocusedAsOver", showFocusedAsOver, true);
	}

	/**
	 * If {@link com.smartgwt.client.widgets.StatefulCanvas#getShowFocused showFocused} is true for this widget, should the <code>"over"</code> state be used to
	 * indicate the widget as focused. If set to false, a separate <code>"focused"</code> state will be used.
	 * 
	 * 
	 * @return Boolean
	 * @see com.smartgwt.client.docs.State State overview and related methods
	 */
	public Boolean getShowFocusedAsOver()
	{
		return getAttributeAsBoolean("showFocusedAsOver");
	}

	/**
	 * If using an icon for this button, whether to switch the icon image when the button receives focus.
	 * <P>
	 * If {@link com.smartgwt.client.widgets.StatefulCanvas#getShowFocusedAsOver showFocusedAsOver} is true, the <code>"Over"</code> icon will be displayed when
	 * the canvas has focus, otherwise a separate <code>"Focused"</code> icon will be displayed
	 * 
	 * @param showFocusedIcon
	 *            showFocusedIcon Default value is false
	 * @throws IllegalStateException
	 *             this property cannot be changed after the component has been created
	 * @see com.smartgwt.client.docs.ButtonIcon ButtonIcon overview and related methods
	 */
	public void setShowFocusedIcon(Boolean showFocusedIcon) throws IllegalStateException
	{
		setAttribute("showFocusedIcon", showFocusedIcon, false);
	}

	/**
	 * If using an icon for this button, whether to switch the icon image when the button receives focus.
	 * <P>
	 * If {@link com.smartgwt.client.widgets.StatefulCanvas#getShowFocusedAsOver showFocusedAsOver} is true, the <code>"Over"</code> icon will be displayed when
	 * the canvas has focus, otherwise a separate <code>"Focused"</code> icon will be displayed
	 * 
	 * 
	 * @return Boolean
	 * @see com.smartgwt.client.docs.ButtonIcon ButtonIcon overview and related methods
	 */
	public Boolean getShowFocusedIcon()
	{
		return getAttributeAsBoolean("showFocusedIcon");
	}

	/**
	 * When this property is set to true, this widget will create and show the {@link com.smartgwt.client.widgets.StatefulCanvas#getOverCanvas overCanvas} on
	 * user rollover.
	 * <p>
	 * <b>Note : </b> This is an advanced setting
	 * </p>
	 * 
	 * @param showOverCanvas
	 *            showOverCanvas Default value is false
	 */
	public void setShowOverCanvas(Boolean showOverCanvas)
	{
		setAttribute("showOverCanvas", showOverCanvas, true);
	}

	/**
	 * When this property is set to true, this widget will create and show the {@link com.smartgwt.client.widgets.StatefulCanvas#getOverCanvas overCanvas} on
	 * user rollover.
	 * 
	 * 
	 * @return Boolean
	 */
	public Boolean getShowOverCanvas()
	{
		return getAttributeAsBoolean("showOverCanvas");
	}

	/**
	 * Should we visibly change state when the mouse goes over this object?
	 * 
	 * @param showRollOver
	 *            showRollOver Default value is false
	 * @see com.smartgwt.client.docs.State State overview and related methods
	 */
	public void setShowRollOver(Boolean showRollOver)
	{
		setAttribute("showRollOver", showRollOver, true);
	}

	/**
	 * Should we visibly change state when the mouse goes over this object?
	 * 
	 * 
	 * @return Boolean
	 * @see com.smartgwt.client.docs.State State overview and related methods
	 */
	public Boolean getShowRollOver()
	{
		return getAttributeAsBoolean("showRollOver");
	}

	/**
	 * If using an icon for this button, whether to switch the icon image on mouse rollover.
	 * 
	 * @param showRollOverIcon
	 *            showRollOverIcon Default value is false
	 * @throws IllegalStateException
	 *             this property cannot be changed after the component has been created
	 * @see com.smartgwt.client.docs.ButtonIcon ButtonIcon overview and related methods
	 */
	public void setShowRollOverIcon(Boolean showRollOverIcon) throws IllegalStateException
	{
		setAttribute("showRollOverIcon", showRollOverIcon, false);
	}

	/**
	 * If using an icon for this button, whether to switch the icon image on mouse rollover.
	 * 
	 * 
	 * @return Boolean
	 * @see com.smartgwt.client.docs.ButtonIcon ButtonIcon overview and related methods
	 */
	public Boolean getShowRollOverIcon()
	{
		return getAttributeAsBoolean("showRollOverIcon");
	}

	/**
	 * If using an icon for this button, whether to switch the icon image when the button becomes selected.
	 * 
	 * @param showSelectedIcon
	 *            showSelectedIcon Default value is false
	 * @throws IllegalStateException
	 *             this property cannot be changed after the component has been created
	 * @see com.smartgwt.client.docs.ButtonIcon ButtonIcon overview and related methods
	 */
	public void setShowSelectedIcon(Boolean showSelectedIcon) throws IllegalStateException
	{
		setAttribute("showSelectedIcon", showSelectedIcon, false);
	}

	/**
	 * If using an icon for this button, whether to switch the icon image when the button becomes selected.
	 * 
	 * 
	 * @return Boolean
	 * @see com.smartgwt.client.docs.ButtonIcon ButtonIcon overview and related methods
	 */
	public Boolean getShowSelectedIcon()
	{
		return getAttributeAsBoolean("showSelectedIcon");
	}

	/**
	 * Current "state" of this widget. StatefulCanvases will have a different appearance based on their current state. By default this is handled by changing
	 * the css className applied to the StatefulCanvas - see {@link com.smartgwt.client.widgets.StatefulCanvas#getBaseStyle baseStyle} for a description of how
	 * this is done.
	 * <P>
	 * For {@link com.smartgwt.client.widgets.Img} or {@link com.smartgwt.client.widgets.StretchImg} based subclasses of StatefulCanvas, the appearance may also
	 * be updated by changing the src of the rendered image. See {@link com.smartgwt.client.widgets.Img#getSrc src} and
	 * {@link com.smartgwt.client.widgets.StretchImgButton#getSrc src} for a description of how the URL is modified to reflect the state of the widget in this
	 * case.
	 * 
	 * <br>
	 * <br>
	 * If this method is called after the component has been drawn/initialized: Set the 'state' of this object, this changes it's appearance.
	 * <p>
	 * <b>Note : </b> This is an advanced setting
	 * </p>
	 * 
	 * @param state
	 *            new state. Default value is ""
	 * @see com.smartgwt.client.types.State
	 * @see com.smartgwt.client.docs.State State overview and related methods
	 */
	public void setState(State state)
	{
		setAttribute("state", state.getValue(), true);
	}

	/**
	 * Current "state" of this widget. StatefulCanvases will have a different appearance based on their current state. By default this is handled by changing
	 * the css className applied to the StatefulCanvas - see {@link com.smartgwt.client.widgets.StatefulCanvas#getBaseStyle baseStyle} for a description of how
	 * this is done.
	 * <P>
	 * For {@link com.smartgwt.client.widgets.Img} or {@link com.smartgwt.client.widgets.StretchImg} based subclasses of StatefulCanvas, the appearance may also
	 * be updated by changing the src of the rendered image. See {@link com.smartgwt.client.widgets.Img#getSrc src} and
	 * {@link com.smartgwt.client.widgets.StretchImgButton#getSrc src} for a description of how the URL is modified to reflect the state of the widget in this
	 * case.
	 * 
	 * 
	 * @return Return the state of this StatefulCanvas
	 * @see com.smartgwt.client.types.State
	 * @see com.smartgwt.client.docs.State State overview and related methods
	 */
	public State getState()
	{
		return EnumUtil.getEnum(State.values(), getAttribute("state"));
	}

	/**
	 * The text title to display in this button.
	 * 
	 * <br>
	 * <br>
	 * If this method is called after the component has been drawn/initialized: Set the title.
	 * 
	 * @param title
	 *            new title. Default value is varies
	 * @see com.smartgwt.client.docs.Basics Basics overview and related methods
	 */
	@Override
	public void setTitle(String title)
	{
		setAttribute("title", title, true);
	}

	/**
	 * The text title to display in this button.
	 * 
	 * 
	 * @return Return the title - text/HTML drawn inside the component.
	 *         <p>
	 *         Default is to simply return this.title.
	 * @see com.smartgwt.client.docs.Basics Basics overview and related methods
	 */
	@Override
	public String getTitle()
	{
		return getAttributeAsString("title");
	}

	/**
	 * Vertical alignment of this component's title.
	 * 
	 * @param valign
	 *            valign Default value is Canvas.CENTER
	 * @see com.smartgwt.client.docs.Appearance Appearance overview and related methods
	 */
	public void setValign(VerticalAlignment valign)
	{
		setAttribute("valign", valign.getValue(), true);
	}

	/**
	 * Vertical alignment of this component's title.
	 * 
	 * 
	 * @return VerticalAlignment
	 * @see com.smartgwt.client.docs.Appearance Appearance overview and related methods
	 */
	public VerticalAlignment getValign()
	{
		return EnumUtil.getEnum(VerticalAlignment.values(), getAttribute("valign"));
	}

	public void setTitleStyle(String titleStyle)
	{
		setAttribute("titleStyle", titleStyle, true);
	}

}

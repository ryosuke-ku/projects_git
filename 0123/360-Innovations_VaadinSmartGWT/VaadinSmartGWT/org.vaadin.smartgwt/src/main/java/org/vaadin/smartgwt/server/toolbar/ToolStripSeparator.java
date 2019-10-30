package org.vaadin.smartgwt.server.toolbar;

import org.vaadin.smartgwt.client.ui.toolbar.VToolStripSeparator;
import org.vaadin.smartgwt.server.Img;

import com.vaadin.ui.ClientWidget;


/*
 * Smart GWT (GWT for SmartClient)
 * Copyright 2008 and beyond, Isomorphic Software, Inc.
 *
 * Smart GWT is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.  Smart GWT is also
 * available under typical commercial license terms - see
 * http://smartclient.com/license
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */
 
/**
 * Simple subclass of Img with appearance appropriate for a ToolStrip separator
 */
@ClientWidget(value=VToolStripSeparator.class)
public class ToolStripSeparator extends Img {

//    public static ToolStripSeparator getOrCreateRef(JavaScriptObject jsObj) {
//        if(jsObj == null) return null;
//        BaseWidget obj = BaseWidget.getRef(jsObj);
//        if(obj != null) {
//            return (ToolStripSeparator) obj;
//        } else {
//            return new ToolStripSeparator(jsObj);
//        }
//    }

    public ToolStripSeparator(){
        scClassName = "ToolStripSeparator";
    }

//    public ToolStripSeparator(JavaScriptObject jsObj){
//        super(jsObj);
//    }

//    protected native JavaScriptObject create()/*-{
//        var config = this.@com.smartgwt.client.widgets.BaseWidget::getConfig()();
//        var scClassName = this.@com.smartgwt.client.widgets.BaseWidget::scClassName;
//        var widget = $wnd.isc[scClassName].create(config);
//        this.@com.smartgwt.client.widgets.BaseWidget::doInit()();
//        return widget;
//    }-*/;
    // ********************* Properties / Attributes ***********************

    /**
     * Image for horizontally oriented separator (for vertical toolstrips).
     *
     * @param hSrc hSrc Default value is "[SKIN]hseparator.png"
     * @throws IllegalStateException this property cannot be changed after the component has been created
     */
    public void setHSrc(String hSrc)  throws IllegalStateException {
        setAttribute("hSrc", hSrc, false);
    }

    /**
     * Image for horizontally oriented separator (for vertical toolstrips).
     *
     *
     * @return String
     */
    public String getHSrc()  {
        return getAttributeAsString("hSrc");
    }

    /**
     * Path to separator image.
     *
     * @param skinImgDir skinImgDir Default value is "images/ToolStrip/"
     * @throws IllegalStateException this property cannot be changed after the component has been created
     */
    public void setSkinImgDir(String skinImgDir)  throws IllegalStateException {
        setAttribute("skinImgDir", skinImgDir, false);
    }

    /**
     * Path to separator image.
     *
     *
     * @return String
     */
    public String getSkinImgDir()  {
        return getAttributeAsString("skinImgDir");
    }

    /**
     * Image for vertically oriented separator (for horizontal toolstrips).
     *
     * @param vSrc vSrc Default value is "[SKIN]separator.png"
     * @throws IllegalStateException this property cannot be changed after the component has been created
     */
    public void setVSrc(String vSrc)  throws IllegalStateException {
        setAttribute("vSrc", vSrc, false);
    }

    /**
     * Image for vertically oriented separator (for horizontal toolstrips).
     *
     *
     * @return String
     */
    public String getVSrc()  {
        return getAttributeAsString("vSrc");
    }

    // ********************* Methods ***********************

    // ********************* Static Methods ***********************
    /**
     * Class level method to set the default properties of this class. If set, then all subsequent instances of this
     * class will automatically have the default properties that were set when this method was called. This is a powerful
     * feature that eliminates the need for users to create a separate hierarchy of subclasses that only alter the default
     * properties of this class. Can also be used for skinning / styling purposes.
     * <P>
     * <b>Note:</b> This method is intended for setting default attributes only and will effect all instances of the
     * underlying class (including those automatically generated in JavaScript). 
     * This method should not be used to apply standard EventHandlers or override methods for
     * a class - use a custom subclass instead.
     *
     * @param toolStripSeparatorProperties properties that should be used as new defaults when instances of this class are created
     */
//    public static native void setDefaultProperties(ToolStripSeparator toolStripSeparatorProperties) /*-{
//    	var properties = $wnd.isc.addProperties({},toolStripSeparatorProperties.@com.smartgwt.client.widgets.BaseWidget::getConfig()());
//    	delete properties.ID;
//        $wnd.isc.ToolStripSeparator.addProperties(properties);
//    }-*/;
        
    // ***********************************************************        

}




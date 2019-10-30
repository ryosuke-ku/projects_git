/*
 * SmartGWT (GWT for SmartClient)
 * Copyright 2008 and beyond, Isomorphic Software, Inc.
 *
 * SmartGWT is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.  SmartGWT is also
 * available under typical commercial license terms - see
 * http://smartclient.com/license
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */

package org.vaadin.smartgwt.server.types;

/**
 * Determines sizing model when sizing / positioning a canvas relative to its&#010
 * {@link com.smartgwt.client.widgets.Canvas#setPercentBox(PercentBoxModel) percentBox}.
 */

public enum PercentBoxModel implements ValueEnum {
    /**
     * use coordinates relative to the {@link com.smartgwt.client.widgets.Canvas#getVisibleHeight() visibleHeight} and
     * width of the other canvas
     */
    VISIBLE("visible"),
    /**
     * use coordinates relative to the {@link com.smartgwt.client.widgets.Canvas#getViewportHeight() viewportHeight}
     * and width of the other canvas
     */
    VIEWPORT("viewport");
    private String value;

    PercentBoxModel(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}


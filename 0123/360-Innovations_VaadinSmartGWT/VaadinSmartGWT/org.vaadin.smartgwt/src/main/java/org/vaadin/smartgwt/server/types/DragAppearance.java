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
 * Different types of effects for showing dragging behavior.
 */

public enum DragAppearance implements ValueEnum {
    /**
     * No default drag appearance is indicated. Your custom dragging routines should&#010 implement some behavior that
     * indicates that the user is in a dragging situation,&#010      and where the mouse is.
     */
    NONE("none"),
    /**
     * A "drag tracker" object is automatically shown and moved around with the&#010 mouse. This is generally set to an
     * icon that represents what is being dragged. The&#010      default tracker is a 10 pixel black square, but you can
     * customize this icon. This&#010      dragAppearance is not recommended for use with drag resizing or drag moving.
     */
    TRACKER("tracker"),
    /**
     * The target object is actually moved, resized, etc. in real time. This is&#010 recommended for drag repositioning,
     * but not for drag resizing of complex objects.
     */
    TARGET("target"),
    /**
     * An outline the size of the target object is moved, resized, etc. with the&#010 mouse. This is recommended for
     * drag resizing, especially for objects that take a&#010      significant amount of time to draw.
     */
    OUTLINE("outline");
    private String value;

    DragAppearance(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}


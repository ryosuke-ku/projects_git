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
 */

public enum SortDirection implements ValueEnum {
    /**
     * Sort in ascending order (eg: A-Z, larger items later in the list)
     */
    ASCENDING("ascending"),
    /**
     * Sort in descending order (eg: Z-A, larger items earlier in the list)
     */
    DESCENDING("descending");
    private String value;

    SortDirection(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}


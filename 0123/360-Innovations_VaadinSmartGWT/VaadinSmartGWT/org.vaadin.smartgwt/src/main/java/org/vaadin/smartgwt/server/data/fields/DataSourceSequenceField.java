/*
 * SmartGWT (GWT for SmartClient)
 * Copyright 2008 and beyond, Isomorphic Software, Inc.
 *
 * SmartGWT is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.  SmartGWT is also
 * available under typical commercial license terms - see
 * smartclient.com/license.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */

package org.vaadin.smartgwt.server.data.fields;

import org.vaadin.smartgwt.client.data.fields.VDataSourceField;
import org.vaadin.smartgwt.server.data.DataSourceField;
import org.vaadin.smartgwt.server.types.FieldType;

import com.vaadin.ui.ClientWidget;


@ClientWidget(value=VDataSourceField.class)
public class DataSourceSequenceField extends DataSourceField {

    public DataSourceSequenceField() {
        setType(FieldType.SEQUENCE);
    }

    public DataSourceSequenceField(String name) {
        super(name, FieldType.SEQUENCE);
    }

    public DataSourceSequenceField(String name, String title) {
        super(name, FieldType.SEQUENCE, title);
    }

    public DataSourceSequenceField(String name, String title, int length) {
        super(name, FieldType.SEQUENCE, title, length);
    }

    public DataSourceSequenceField(String name, String title, int length, boolean required) {
        super(name, FieldType.SEQUENCE, title, length, required);
    }
}

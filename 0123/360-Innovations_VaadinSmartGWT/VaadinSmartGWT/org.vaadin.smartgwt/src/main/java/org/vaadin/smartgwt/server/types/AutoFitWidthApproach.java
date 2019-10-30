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
 * How should field width be determined when {@link com.smartgwt.client.widgets.grid.ListGridField#getAutoFitWidth} is true?
 */
public enum AutoFitWidthApproach implements ValueEnum
{
	/**
	 * Size field to fit to the data value(s) contained in the field.
	 */
	VALUE("value"),

	/**
	 * Size field to fit the field title
	 */
	TITLE("title"),

	/**
	 * Size field to fit either the field title or the data values in the field
	 */
	BOTH("both");

	private String value;

	AutoFitWidthApproach(String value)
	{
		this.value = value;
	}

	@Override
	public String getValue()
	{
		return this.value;
	}
}

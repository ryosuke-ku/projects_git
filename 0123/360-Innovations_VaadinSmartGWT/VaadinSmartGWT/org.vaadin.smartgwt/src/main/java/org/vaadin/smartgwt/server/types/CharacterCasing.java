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
public enum CharacterCasing implements ValueEnum
{
	/**
	 * No character translation
	 */
	DEFAULT("default"),

	/**
	 * Map characters to uppercase
	 */
	UPPER("upper"),

	/**
	 * Map characters to lowercase
	 */
	LOWER("lower");

	private String value;

	CharacterCasing(String value)
	{
		this.value = value;
	}

	@Override
	public String getValue()
	{
		return this.value;
	}
}

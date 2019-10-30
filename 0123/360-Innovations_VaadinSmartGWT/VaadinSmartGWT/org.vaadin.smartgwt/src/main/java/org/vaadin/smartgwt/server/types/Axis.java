package org.vaadin.smartgwt.server.types;

/**
 * An axis or "side" of a table
 * 
 */
public enum Axis implements ValueEnum
{

	/**
	 * Row axis
	 */
	ROW("row"),

	/**
	 * Column axis
	 */
	COLUMN("column");

	private String value;

	Axis(String value)
	{
		this.value = value;
	}

	@Override
	public String getValue()
	{
		return value;
	}
}

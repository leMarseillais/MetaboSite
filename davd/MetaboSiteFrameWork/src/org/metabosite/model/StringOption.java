package org.metabosite.model;

public class StringOption extends Option {
	
	private String value;

	@Override
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public StringOption(String name, String value) {
		super(name);
		this.value = value;
	}

}

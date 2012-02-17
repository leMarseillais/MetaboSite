package org.metabosite.model;

public class BooleanOption extends Option {
	
	private Boolean value;

	@Override
	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}

	public BooleanOption(String name, Boolean value) {
		super(name);
		this.value = value;
	}

}

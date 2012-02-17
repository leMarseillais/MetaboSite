package org.msmodule.controllers;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.metabosite.model.BooleanOption;
import org.metabosite.model.OptionList;
import org.metabosite.model.StringOption;

@ManagedBean(name="moduleController")
@ViewScoped
public class Module implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private OptionList mesOptions;
	
	public OptionList getMesOptions() {
		if (mesOptions == null) {
			mesOptions = new OptionList();
			mesOptions.addOption(new BooleanOption("MOD_ACTIVE", true));
			mesOptions.addOption(new StringOption("DB_NAME", "BioCyc"));
		}
		return mesOptions;
	}

	public void setMesOptions(OptionList mesOptions) {
		this.mesOptions = mesOptions;
	}

	public Module() {
	}
	
}

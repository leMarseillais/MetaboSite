package org.metabosite.module.controllers;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.metabosite.model.BooleanOption;
import org.metabosite.model.Item;
import org.metabosite.model.ItemList;
import org.metabosite.model.OptionList;
import org.metabosite.model.StringOption;

@ManagedBean(name="moduleController")
@ViewScoped
public class Module implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private OptionList mesOptions;
	private ItemList menuItems;
	
	public ItemList getMenuItems() {
		if (menuItems == null) {
			menuItems = new ItemList();
			menuItems.addItem(new Item("SdZ", "http://www.siteduzero.com/"));
		}
		return menuItems;
	}

	public void setMenuItems(ItemList menuItems) {
		this.menuItems = menuItems;
	}

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

package org.metabosite.module.controllers;
import java.io.File;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.metabosite.model.BooleanOption;
import org.metabosite.model.Item;
import org.metabosite.model.ItemList;
import org.metabosite.model.OptionList;
import org.metabosite.model.StringOption;
import org.metabosite.module.model.ToLaunch;
import org.metabosite.task.management.TaskManager;

import src.entities.Siteuser;
import src.services.SiteUserFacadeLocal;

@ManagedBean(name="moduleController")
@ViewScoped
public class Module implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private OptionList mesOptions;
	private ItemList menuItems;
	@EJB
	private SiteUserFacadeLocal ejbFacadeLocal;
	
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
	
	public void calcul(File file){
		Siteuser siteuser=ejbFacadeLocal.findById("ghf");
		ToLaunch launcher= new ToLaunch(file,siteuser);
		TaskManager taskManager=TaskManager.getInstance();
		taskManager.addTask(launcher, "ghf");
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "passage dans la méthode calcul");
		System.out.println("passage dans la méthode calcul");
	}
	
}

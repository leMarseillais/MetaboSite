package org.metabosite.module.controllers;

import java.io.File;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.metabosite.model.BooleanOption;
import org.metabosite.model.Item;
import org.metabosite.model.ItemList;
import org.metabosite.model.OptionList;
import org.metabosite.model.StringOption;
import org.metabosite.module.model.ToLaunch;
import org.metabosite.module.utils.Bundle;
import org.metabosite.module.utils.Global;
import org.metabosite.module.utils.JsfUtil;
import org.metabosite.task.management.Task;
import org.metabosite.task.management.TaskManager;

import src.entities.Siteuser;
import src.services.FileFacadeLocal;

@ManagedBean(name = "moduleController")
@SessionScoped
public class Module implements Serializable {

	private static final long serialVersionUID = 1L;

	private OptionList mesOptions;
	private ItemList menuItems;
	private StringBuilder fileToUse = new StringBuilder("test");
	private ToLaunch launcher;
	private boolean runned = false;
	private String taskID = "VisuTask";
	private String resultScript;
	
	@EJB
	FileFacadeLocal fFacade;

	public StringBuilder getFileToUse() {
		System.out.println(fileToUse);
		return fileToUse;
	}

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

	public void calcul() {
		File theFile = new File(fileToUse.substring(0));
		if (fileToUse != null && theFile.exists() && theFile.getAbsolutePath().endsWith(".xml")) {
			Siteuser siteuser = Global.getUser();
			launcher = new ToLaunch(taskID, theFile, siteuser, fFacade);
			TaskManager taskManager = TaskManager.getInstance();
			taskManager.addTask(launcher, siteuser.getLogin());
			runned = true;
		} else {
			JsfUtil.addErrorMessage(Bundle.Err.getBundle().getString("no_file_selected"));
		}
	}

	public boolean isTerminated() {
		Task t = TaskManager.getInstance().getTask(Global.getUser().getLogin(), taskID);
		if (t != null && t.isTerminated()) {
			return true;
		}
		return false;
	}

	public String getFileOut() {
		if (isTerminated() && resultScript == null && launcher != null) {
			runned = false;
			resultScript = new String(launcher.getFileout());
		}
		return resultScript;
	}
	
	public boolean isTaskLaunched() {
		return runned;
	}

}

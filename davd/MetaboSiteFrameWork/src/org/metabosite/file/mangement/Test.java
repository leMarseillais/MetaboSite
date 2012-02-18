package org.metabosite.file.mangement;

import src.entities.Siteuser;
import src.services.SiteUserFacade;
import src.services.SiteUserFacadeLocal;

public class Test {

	/**
	 * @param args
	 */
	public Test() {
		SiteUserFacadeLocal userFacadeLocal = new SiteUserFacade();
		Siteuser entity = new Siteuser("pp", true, "nlkn", 30L, "hk@hj.fr", "mm");
		userFacadeLocal.create(entity);
		FilesManage filesManage =  new FilesManage("plop", "pp", "ll", entity, "test", "nisbsnjbbbbbbbbbbb");
		filesManage.saveFile();
	}

}

package org.metabosite.file.management;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.metabosite.module.utils.Bundle;
import org.metabosite.module.utils.Global;

import src.entities.Files;
import src.entities.Siteuser;
import src.services.FileFacadeLocal;

public class FilesManage {

	private volatile static FileFacadeLocal ejbFacade;
	
	public FilesManage(FileFacadeLocal ejbFacade) {
		FilesManage.ejbFacade = ejbFacade;
	}

	public String saveFile(String fileDescription, String mime, String extention,
			Siteuser siteuser, String modulename, String stringFile) {
		String fileName = modulename + "_" + Global.currentTime() + "."
				+ extention;
		String fileLocation = Bundle.Files.getBundle().getString("filesPath") + fileName;
		Long creationDate = Global.currentTime();
		Long modifDate = Global.currentTime();
		Files fileEntiti = new Files(fileLocation, creationDate, modifDate,
				fileName, fileDescription, mime, extention, siteuser);
		File file = new File(fileLocation);
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(stringFile.getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ejbFacade.create(fileEntiti);
		return fileName;
	}

	public File getFile(Files files) {
		return new File(files.getFileLocation());
	}

}

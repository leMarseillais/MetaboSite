package file.management;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import src.entities.Files;
import src.entities.Siteuser;
import src.services.FileFacadeLocal;
import utils.Bundle;
import utils.Global;

public class FilesManage {

		private Files fileEntiti;
		private File file;
		private volatile static FileFacadeLocal ejbFacade;
		
		public FilesManage(String fileDescription, String mime,
				String extention, Siteuser siteuser,String modulename,String file) {
			String fileName = modulename+"_"+Global.currentTime()+"."+extention;
			String fileLocation=Bundle.Files.getBundle().getString("Location")+"/"+fileName;
			Long creationDate=Global.currentTime();
			Long modifDate=Global.currentTime();
			fileEntiti=new Files(fileLocation, creationDate, modifDate, fileName, fileDescription, mime, extention, siteuser);
			this.file=new File(fileLocation);
			try {
				FileOutputStream fileOutputStream =new FileOutputStream(this.file);
				fileOutputStream.write(file.getBytes());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void saveFile(){
			ejbFacade.create(fileEntiti);
		}
		
}

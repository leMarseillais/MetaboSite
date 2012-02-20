package org.metabosite.module.controllers.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.metabosite.module.controllers.exception.EntityAlreadyExistsKException;
import org.metabosite.module.utils.Bundle;
import org.metabosite.module.utils.Global;
import org.metabosite.module.utils.JsfUtil;
import org.metabosite.module.utils.Reinitialize;
import org.metabosite.module.utils.Session;
import org.richfaces.component.UITree;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.event.TreeSelectionChangeEvent;
import org.richfaces.event.TreeToggleEvent;
import org.richfaces.model.UploadedFile;

import src.EJBKException;
import src.entities.Files;
import src.entities.Siteuser;
import src.services.FileFacadeLocal;
import src.services.SiteUserFacadeLocal;

@ManagedBean(name = "fileSystemController")
@ViewScoped
public class FileSystemController implements Serializable, Reinitialize {

	private static final long serialVersionUID = 1L;

	private Files current;
	private List<FileSystemNode> roots;
	private List<FileSystemNode> oldSelection = null;
	private List<FileSystemNode> selection = null;
	private String dirName = null;

	private Map<String, KFile> fichiers = new HashMap<String, KFile>();
	private DataModel<KFile> fichiersModel;
	private Map<String, Boolean> renderedDesc = new HashMap<String, Boolean>();

	private UITree filesTree;

	@EJB
	private FileFacadeLocal fichierFacade;
	@EJB
	private SiteUserFacadeLocal utilisateurFacade;
	
	private StringBuilder bindingValue;
	
	public StringBuilder getBindingValue() {
		return bindingValue;
	}

	public void setBindingValue(StringBuilder bindingValue) {
		this.bindingValue = bindingValue;
	}

	public boolean existsOldSelection() {
		if (oldSelection != null) {
			return !oldSelection.isEmpty();
		}
		return false;
	}

	public UITree getFilesTree() {
		return filesTree;
	}

	public void setFilesTree(UITree filesTree) {
		this.filesTree = filesTree;
	}

	public DataModel<KFile> getFichiersModel() {
		Collection<KFile> files = fichiers.values();
		List<KFile> filesList = new ArrayList<KFile>();
		for (Iterator<KFile> kFiles = files.iterator(); kFiles.hasNext();) {
			KFile kFile = kFiles.next();
			filesList.add(kFile);
		}

		fichiersModel = new ListDataModel<KFile>(filesList);
		return fichiersModel;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	public synchronized FileSystemNode getFirstSelection() {
		if (getSelection().isEmpty()) {
			selection.add(new FileSystemNode(Global.getFILES_PATH(), null, fichierFacade,
					FileSystemNode.Type.DIR).getFirstChild());
		}
		return getSelection().get(0);
	}

	public FileSystemNode getFirstNotRootSelection() {
		FileSystemNode result = getFirstSelection();

		if (result.isRoot()) {
			result.getFirstChild();
		}

		return result;
	}

	public synchronized List<FileSystemNode> getSelection() {
		if (selection == null) {
			FileSystemNode fsn = new FileSystemNode(Global.getFILES_PATH(), null,
					fichierFacade, FileSystemNode.Type.DIR);
			selection = new ArrayList<FileSystemNode>();
			if (fsn.getChildCount() > 0) {
				selection.add(fsn.getFirstChild());
			} else {
				selection.add(fsn);
			}
		}
		return selection;
	}

	public void setSelection(List<FileSystemNode> selection) {
		if (oldSelection != null) {
			oldSelection.clear();
		} else {
			oldSelection = new ArrayList<FileSystemNode>();
		}

		for (FileSystemNode fsn : getSelection()) {
			oldSelection.add(fsn);
		}

		this.selection.clear();
		if (selection != null) {
			this.selection.addAll(selection);
		}
	}

	public void setSelection(FileSystemNode selection) {
		if (oldSelection != null) {
			oldSelection.clear();
		} else {
			oldSelection = new ArrayList<FileSystemNode>();
		}

		for (FileSystemNode fsn : getSelection()) {
			oldSelection.add(fsn);
		}

		this.selection.clear();

		if (selection != null) {
			this.selection.add(selection);
		}
	}

	public void toggleListener(TreeToggleEvent event) {
	}

	public synchronized List<FileSystemNode> getRoots() {
			ResourceBundle err_rb = Bundle.Err.getBundle();

			try {
				FileSystemNode fsn = new FileSystemNode(Global.getFILES_PATH(), null,
						fichierFacade, FileSystemNode.Type.DIR);
				roots = fsn.getDirectories();
				for (FileSystemNode node : roots) {
					node.setParent(null);
				}
			} catch (EJBKException ex) {
				roots = new ArrayList<FileSystemNode>();

				JsfUtil.addErrorMessage(err_rb
						.getString("noResultException_file"));
				Logger.getLogger(FileSystemController.class.getName()).log(
						Level.SEVERE, null, ex);
			}

		// Modifiable -- nécessaire !
		rootSelection();
		return roots;
	}

	/** Creates a new instance of FileSystemController */
	public FileSystemController() {
	}

	public void resetRoots() {
		roots = null;
	}

	private void resetCurrent() {
		dirName = null;
	}

	public void rootSelection() {
		if (!getFirstSelection().getPath().equals(Global.getFILES_PATH())) {
			FileSystemNode fsn = new FileSystemNode(Global.getFILES_PATH(), null,
					fichierFacade, FileSystemNode.Type.DIR);
			setSelection(fsn);
		}
	}

	public void resetSelection() {
		if (getFirstSelection().getPath().equals(Global.getFILES_PATH())
				&& oldSelection != null) {
			getSelection().clear();
			for (FileSystemNode fsn : oldSelection) {
				selection.add(fsn);
			}
		}
	}

	public void resetSelections() {
		oldSelection = null;
		selection = null;
	}

	public void reloadAll() {
		String tmp = getFirstSelection().getPath();
		resetSelections();
		rootSelection();
		FileSystemNode newSelection = search(tmp, getFirstSelection());
		if (newSelection != null) {
			setSelection(newSelection);
		} else if (getFirstSelection().getChildCount() > 0) {
			setSelection(getFirstSelection().getFirstChild());
		}

		roots = null;
	}

	public void removeSelection(ActionEvent event) {
		String parentPath = null;
		if (!getFirstSelection().isRoot()) {
			parentPath = getFirstSelection().getParent().getPath();
		}

		roots = null;
		resetSelections();

		if (parentPath != null) {
			FileSystemNode node = search(parentPath, getFirstSelection());
			if (node != null) {
				setSelection(node);
			}
		}
	}

	public void createDir(ActionEvent event) {
		ResourceBundle err_rb = Bundle.Err.getBundle();
		if (dirName != null && !dirName.isEmpty()) {

			KDir kf = new KDir(dirName);
			try {
				Siteuser siteuser = findConnectedUser();
				getFirstSelection().newFile(kf, siteuser);
			} catch (EntityAlreadyExistsKException ex) {
				JsfUtil.addErrorMessage(ex.getMessage());
			} catch (FileNotFoundException ex) {
				JsfUtil.addErrorMessage(err_rb
						.getString("FileNotFoundException_dir"));
				Logger.getLogger(FileSystemController.class.getName()).log(
						Level.SEVERE, null, ex);
			} catch (IOException ex) {
				JsfUtil.addErrorMessage(err_rb.getString("IOException_dir"));
				Logger.getLogger(FileSystemController.class.getName()).log(
						Level.SEVERE, null, ex);
			}

			roots = null;
			String newPath = new File(Global.getFILES_PATH()).getAbsolutePath() + "/"
					+ kf.getName();

			if (getFirstSelection().getPath().equals(Global.getFILES_PATH())) {
				setSelection(new FileSystemNode(newPath, null, fichierFacade,

				FileSystemNode.Type.DIR));

				try {
					getFirstSelection().setDetails(
							fichierFacade.sOneChemin(newPath));
				} catch (EJBKException ex) {
					Logger.getLogger(FileSystemController.class.getName()).log(
							Level.SEVERE, "Files non trouvé ! ({0})", newPath);
				}

			}
		}
		resetCurrent();
	}

	private Siteuser findConnectedUser() {
		if (getUserCo() != null && getUserCo().getLogin() != null) {
			return utilisateurFacade.findById(getUserCo().getLogin());
		}
		return null;
	}

	public Siteuser getUserCo() {
		return (Siteuser) Global.getSession(Session.UserCo);
	}

	public void saveFiles() {
		ResourceBundle err_rb = Bundle.Err.getBundle();
		ResourceBundle rb = Bundle.MBFE.getBundle();

		if (fichiers.isEmpty()) {
			JsfUtil.addErrorMessage(rb.getString("CreateFilesError_noFiles"));
			return;
		}

		for (Iterator<KFile> kFiles = getFichiersModel().iterator(); kFiles
				.hasNext();) {
			KFile kFile = kFiles.next();

			current = new Files();
			current.setMime(kFile.getMime());
			current.setExtention(kFile.getExt());
			current.setFileLocation(kFile.getName());
			current.setFileName(kFile.getName());
			current.setFileDescription(kFile.getDesc());
			current.setModifDate(Global.currentTime());

			try {
				getFirstSelection().newFile(kFile, findConnectedUser());
			} catch (EntityAlreadyExistsKException ex) {
				JsfUtil.addErrorMessage(ex.getMessage());
			} catch (FileNotFoundException ex) {
				JsfUtil.addErrorMessage(err_rb
						.getString("FileNotFoundException_dir"));
				Logger.getLogger(FileSystemController.class.getName()).log(
						Level.SEVERE, null, ex);
			} catch (IOException ex) {
				JsfUtil.addErrorMessage(err_rb.getString("IOException_dir"));
				Logger.getLogger(FileSystemController.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}

		fichiers.clear();
		renderedDesc.clear();
		roots = null;

		return;
	}

	private List<FileSystemNode> selectionNode(Collection<Object> selection,
			Object source) {
		List<Object> selList = new ArrayList<Object>(selection);
		UITree tree = (UITree) source;
		List<FileSystemNode> nodeList = new ArrayList<FileSystemNode>();
		if (!selList.isEmpty()) {
			for (Object o : selList) {
				Object selKey = o;

				Object storedKey = tree.getRowKey();
				tree.setRowKey(selKey);
				nodeList.add((FileSystemNode) tree.getRowData());
				tree.setRowKey(storedKey);
			}

			return nodeList;
		}
		return null;
	}
	
	public void setBinding(StringBuilder bindingValue) {
		this.bindingValue = bindingValue;
	}

	public void selectionChanged(TreeSelectionChangeEvent event) {
		setSelection(selectionNode(event.getNewSelection(), event.getSource()));
		bindingValue.delete(0, bindingValue.length());
		bindingValue.append(getFirstSelection().getDetails().getFileLocation());
	}

	private String genFileKey(UploadedFile file) {
		return file.getName() + "_" + file.getSize() + "_"
				+ Global.currentTime();
	}

	public String acceptedTypes() {
		String acceptedTypes = "";
		if (!KFile.getAuthorizedMimes().isEmpty()) {
			Collection<String[]> types = KFile.getAuthorizedMimes().values();
			for (Iterator<String[]> typesIt = types.iterator(); typesIt
					.hasNext();) {
				String[] exts = typesIt.next();
				for (int i = 0; i < exts.length; i++) {
					if (typesIt.hasNext() || ((i + 1) < exts.length)) {
						acceptedTypes += exts[i] + ", ";
					} else {
						acceptedTypes += exts[i];
					}
				}
			}
		}

		return acceptedTypes;
	}

	public void upload(FileUploadEvent event) {
		UploadedFile eFile = event.getUploadedFile();
		KFile file;
		ResourceBundle err_rb = Bundle.Err.getBundle();
		try {
			String name = eFile.getName();
			String ext = KFile.findExt(name);
			String mime = eFile.getContentType();
			System.out.println(mime);

			if (!KFile.equivalents(mime, ext)) {
				mime = KFile.getAuthorizedMime(ext);
			}

			if (ext != null) {
				Pattern p = Pattern.compile("." + ext + "$",
						Pattern.CASE_INSENSITIVE);
				name = p.matcher(name).replaceAll("");
			}

			file = new KFile(genFileKey(eFile), name, mime, ext,
					eFile.getData());

			fichiers.put(file.getName(), file);
			renderedDesc.put(file.getName(), Boolean.FALSE);
		} catch (FileNotFoundException ex) {
			JsfUtil.addErrorMessage(err_rb
					.getString("FileNotFoundException_kfile"));
		} catch (IOException ex) {
			JsfUtil.addErrorMessage(err_rb.getString("IOException"));
		} catch (Exception ex) {
			// Type MIME non accepté
			JsfUtil.addErrorMessage(ex.getMessage());
		}
	}

	public boolean renderDesc() {
		if (renderedDesc.isEmpty()) {
			return false;
		}
		KFile file = fichiersModel.getRowData();
		String key = file.getName();
		if (renderedDesc.containsKey(key)) {
			return renderedDesc.get(key);
		}
		return false;
	}

	public void deleteListener() {
		KFile file = fichiersModel.getRowData();
		fichiers.remove(file.getName());
		renderedDesc.remove(file.getName());
	}

	public void changeDescListener() {
		Files f = getFirstSelection().getDetails();
		fichierFacade.edit(f);

	}

	private FileSystemNode search(String path, FileSystemNode root) {
		FileSystemNode result = null;
		for (FileSystemNode node : root.getChilds()) {
			if (node.getPath().equals(path)) {
				return node;
			} else {
				result = search(path, node);
				if (result != null) {
					return result;
				}
			}
		}
		return result;
	}

	public String iconLeaf(FileSystemNode fsn) {
		String iconPath = null;
		String type = fsn.getDetails().getMime();

		try {
			if (KFile.mimeExists(type)) {
				type = type.replaceAll("/", "-");
				iconPath = resourcePath(type);
			} else {
				throw new NullPointerException();
			}
		} catch (NullPointerException ex) {
			iconPath = resourcePath("file");
		}

		return iconPath;
	}

	public String mimeIcon(String mime) {
		String iconPath = null;

		try {
			if (KFile.mimeExists(mime)) {
				mime = mime.replaceAll("/", "-");
				iconPath = mime;
			} else {
				throw new NullPointerException();
			}
		} catch (NullPointerException ex) {
			iconPath = "file";
		}

		return iconPath;
	}

	public String dirIcon(boolean root) {
		return root ? resourcePath("home") : resourcePath("inode-directory");
	}

	private String resourcePath(String type) {
		FacesContext fc = FacesContext.getCurrentInstance();
		ResourceHandler rh = fc.getApplication().getResourceHandler();
		Resource r = rh.createResource("img/" + type + ".png");
		return r.getURL()
				.getPath()
				.replaceFirst("/server/[a-zA-Z-]+",
						Global.contextRoot() + Global.servletPath());
	}

	public int nFiles() {
		return fichiers.size();
	}

	public String convertDateTime(String longValue) {
		Long value = Long.parseLong(longValue);
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy",
				Global.getLocale());
		Date date = new Date(value);
		return sdf.format(date);
	}

	public String rootName(String path) {
		path = path.replace(Global.getFILES_PATH(), "");
		path = path.replaceAll("/.*$", "");
		return path;
	}

	@Override
	public void reinitialize() {
		roots = null;
		selection = null;
		oldSelection = null;
	}

}

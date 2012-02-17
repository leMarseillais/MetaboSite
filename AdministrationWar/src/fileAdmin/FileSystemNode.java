/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fileAdmin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.tree.TreeNode;

import src.EJBKException;
import src.entities.Files;
import src.entities.Siteuser;
import src.services.FileFacadeLocal;
import bean.util.Global;

import com.google.common.collect.Iterators;

import exception.EntityAlreadyExistsKException;

public class FileSystemNode implements TreeNode {

	private String path;
	private FileSystemNode parent;
	private List<FileSystemNode> childs;
	private Files details;
	private String type;

	private volatile static FileFacadeLocal ejbFacade;

	public String getSize() {
		File f = new File(path);
		long size = f.length();
		return (size > 1000000) ? String.valueOf(size / 1000000) + " Mo"
				: String.valueOf(size / 1000) + " Ko";
	}

	public String getType() {
		return type;
	}

	public String getAppPath() {
		if (path == null) {
			return "";
		}
		return path.replace(Global.getFILES_PATH(), "");
	}

	public List<FileSystemNode> getChilds() {
		return Collections.unmodifiableList(childs);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setParent(FileSystemNode parent) {
		this.parent = parent;
	}

	public synchronized Files getDetails() {
		if (details == null) {
			details = new Files();
		}
		return details;
	}

	public void setDetails(Files details) {
		this.details = details;
	}

	public synchronized String getShortPath() {
		String[] tmp = path.split("/");
		String shortPath = tmp[tmp.length - 1];
		return shortPath;
	}

	public synchronized List<FileSystemNode> getDirectories()
			throws EJBKException {
		List<FileSystemNode> directories = new ArrayList<FileSystemNode>();

		for (Iterator<FileSystemNode> it = childs.iterator(); it.hasNext();) {
			FileSystemNode child = it.next();
			directories.add(child);
		}

		return directories;
	}

	public synchronized List<FileSystemNode> getFiles() {
		List<FileSystemNode> files = new ArrayList<FileSystemNode>();

		File file = new File(path);
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				if (!f.isDirectory()) {
					files.add(new FileSystemNode(f.getName(), this, ejbFacade,
							Type.FILE));
				}
			}
		}

		return files;
	}

	public FileSystemNode() {
	}

	public enum Type {
		FILE, DIR;
	}

	public FileSystemNode(String path, FileSystemNode parent,
			FileFacadeLocal ejbFacade, Type type) {

		this.type = type.name();
		this.path = path;
		this.parent = parent;
		if (FileSystemNode.ejbFacade == null) {
			FileSystemNode.ejbFacade = ejbFacade;
		}

		childs = new ArrayList<FileSystemNode>();
		if (path != null) {
			File file = new File(path);
			if (file.isDirectory()) {
				for (File f : file.listFiles()) {
					FileSystemNode fsn = null;
					if (f.isDirectory()) {
						fsn = new FileSystemNode(f.getAbsolutePath(), this,
								ejbFacade, Type.DIR);
					} else {
						fsn = new FileSystemNode(f.getAbsolutePath(), this,
								ejbFacade, Type.FILE);
					}

					try {
						fsn.setDetails(ejbFacade.sOneChemin(f.getAbsolutePath()));
						childs.add(fsn);
					} catch (EJBKException ex) {
						String mess = "Le fichier '" + f.getAbsolutePath()
								+ "' n'existe pas dans la db !"
								+ " Il sera supprimé !";
						deleteFile(f);
						Logger.getLogger(FileSystemNode.class.getName()).log(
								Level.INFO, mess);
					}
				}
			}
		}
	}

	private void deleteFile(File file) {
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				deleteFile(f);
			}
		}

		if (ejbFacade.takenChemin(file.getAbsolutePath())) {

			Files detailF;
			try {
				detailF = ejbFacade.sOneChemin(file.getAbsolutePath());

				ejbFacade.remove(detailF);
			} catch (EJBKException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		file.delete();
	}

	public void newFile(KDir kf, Siteuser userCo)
			throws EntityAlreadyExistsKException, FileNotFoundException,
			IOException {
		if (userCo != null) {
			String newPath = new File(path).getAbsolutePath() + "/"
					+ kf.getName();

			FileSystemNode node = null;
			if (kf.mime.equals("inode/directory")) {
				node = new FileSystemNode(newPath, this, ejbFacade, Type.DIR);
			} else {
				node = new FileSystemNode(newPath, this, ejbFacade, Type.FILE);
			}

			Files detailsNode = new Files();
			detailsNode.setSiteuser(userCo);
			detailsNode.setFileLocation(node.getPath());
			detailsNode.setFileDescription(kf.getDesc());
			detailsNode.setFileName(kf.getName());
			detailsNode.setCreationDate(Global.currentTime());
			detailsNode.setModifDate(Global.currentTime());

			if (kf.getClass().getName().equals(KFile.class.getName())) {
				detailsNode.setExtention(((KFile) kf).getExt());
			}

			if (!path.equals(Global.getFILES_PATH())) {
				try {
					Files f = ejbFacade.sOneChemin(path);

				} catch (EJBKException ex) {
					Logger.getLogger(FileSystemNode.class.getName()).log(
							Level.SEVERE,
							"Fichier non trouvé !" + " (" + path + ")", ex);
				}
			}

			if (!ejbFacade.takenChemin(newPath)) {

				ejbFacade.create(detailsNode);

			} else {
				throw new EntityAlreadyExistsKException(
						"Ce nom est déjà utilisé.");
			}

			kf.write(newPath);

			node.setDetails(detailsNode);
			childs.add(node);
		}
	}

	public void removeChild(int i) {
		if (i < childs.size() && i >= 0) {
			FileSystemNode child = childs.get(i);

			ejbFacade.remove(child.getDetails());
			File file = new File(child.getPath());
			deleteFile(file);
		}
	}

	public void remove() {
		ejbFacade.remove(this.getDetails());
		File file = new File(this.getPath());
		deleteFile(file);
	}

	@Override
	public boolean isLeaf() {
		return childs.isEmpty();
	}

	@Override
	public TreeNode getChildAt(int i) {
		return childs.get(i);
	}

	@Override
	public int getChildCount() {
		return childs.size();
	}

	@Override
	public FileSystemNode getParent() {
		return parent;
	}

	@Override
	public int getIndex(TreeNode tn) {
		return childs.indexOf(tn);
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public Enumeration<FileSystemNode> children() {
		return Iterators.asEnumeration(childs.iterator());
	}

	public FileSystemNode getFirstChild() {
		if (childs.isEmpty()) {
			return null;
		}
		return this.childs.get(0);
	}

	public boolean isRoot() {
		if (parent == null) {
			return true;
		} else {
			return false;
		}
	}
}

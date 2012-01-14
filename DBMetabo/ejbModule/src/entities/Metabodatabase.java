package src.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the metabodatabase database table.
 * 
 */
@Entity
public class Metabodatabase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer idfichier;

	private String filelocation;

	private String filetype;

	private String keyword;

	private String organism;

    public Metabodatabase() {
    }

	public Integer getIdfichier() {
		return this.idfichier;
	}

	public void setIdfichier(Integer idfichier) {
		this.idfichier = idfichier;
	}

	public String getFilelocation() {
		return this.filelocation;
	}

	public void setFilelocation(String filelocation) {
		this.filelocation = filelocation;
	}

	public String getFiletype() {
		return this.filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getOrganism() {
		return this.organism;
	}

	public void setOrganism(String organism) {
		this.organism = organism;
	}

}
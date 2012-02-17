package src.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
    @NamedQuery(name="OneChemin", query="SELECT f FROM Files f WHERE f.fileLocation LIKE :chemin"),
    })
@Entity
public class Files implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="file_location")
	private String fileLocation;
	
	@Column(name="creation_date")
	private Long creationDate;
	
	@Column(name="last_modif_date")
	private Long modifDate;
	
	@Column(name="file_name",unique=true)
	private String fileName;
	
	@Column(name="file_description")
	private String fileDescription;
	
	@Column(name="mime")
	private String mime;
	
	@Column(name="extention")
	private String extention;

	//bi-directional many-to-one association to Project
    @ManyToOne
	@JoinColumn(name="login")
	private Siteuser siteuser;

    public Files() {
    }

	public String getFileLocation() {
		return this.fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public String getFileDescription() {
		return this.fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Siteuser getSiteuser() {
		return siteuser;
	}

	public void setSiteuser(Siteuser siteuser) {
		this.siteuser = siteuser;
	}

	public Long getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Long creationDate) {
		this.creationDate = creationDate;
	}

	public Long getModifDate() {
		return modifDate;
	}

	public void setModifDate(Long modifDate) {
		this.modifDate = modifDate;
	}

	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
	}

	public String getExtention() {
		return extention;
	}

	public void setExtention(String extention) {
		this.extention = extention;
	}

	public Files(String fileLocation, Long creationDate, Long modifDate,
			String fileName, String fileDescription, String mime,
			String extention, Siteuser siteuser) {
		this.fileLocation = fileLocation;
		this.creationDate = creationDate;
		this.modifDate = modifDate;
		this.fileName = fileName;
		this.fileDescription = fileDescription;
		this.mime = mime;
		this.extention = extention;
		this.siteuser = siteuser;
	}
	
	
	
}
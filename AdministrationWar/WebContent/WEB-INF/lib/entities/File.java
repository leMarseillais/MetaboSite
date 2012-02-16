package src.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the file database table.
 * 
 */
@Entity
public class File implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="file_location")
	private String fileLocation;

	@Column(name="file_description")
	private String fileDescription;

	//bi-directional many-to-one association to Project
    @ManyToOne
	@JoinColumn(name="project_id")
	private Project project;

    public File() {
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

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	public String getId() {
		return this.fileLocation;
	}
}
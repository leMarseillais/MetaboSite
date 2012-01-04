package src.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the project database table.
 * 
 */
@Entity
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="project_id")
	private Integer projectId;

	@Column(name="project_description")
	private String projectDescription;

	@Column(name="project_name")
	private String projectName;

	//bi-directional many-to-one association to File
	@OneToMany(mappedBy="project")
	private Set<File> files;

	//bi-directional many-to-one association to Siteuser
    @ManyToOne
	@JoinColumn(name="login")
	private Siteuser siteuser;

    public Project() {
    }

	public Integer getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectDescription() {
		return this.projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Set<File> getFiles() {
		return this.files;
	}

	public void setFiles(Set<File> files) {
		this.files = files;
	}
	
	public Siteuser getSiteuser() {
		return this.siteuser;
	}

	public void setSiteuser(Siteuser siteuser) {
		this.siteuser = siteuser;
	}
	
}
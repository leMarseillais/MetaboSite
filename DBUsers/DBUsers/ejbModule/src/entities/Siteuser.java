package src.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


@NamedQueries({
	@NamedQuery(name = "OneLogin", query = "SELECT u FROM Siteuser u "
			+ "WHERE u.login LIKE :login"),
	@NamedQuery(name = "OneLoginConnected", query = "SELECT u FROM Siteuser u "
			+ "WHERE u.login LIKE :login AND u.connected = TRUE")

})

/**
 * The persistent class for the siteuser database table.
 * 
 */
@Entity
public class Siteuser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String login;

	private Boolean connected;

	private String corporation;

	private String email;

	private String laboratory;

	private String password;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="siteuser")
	private Set<Project> projects;

    public Siteuser() {
    }

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Boolean getConnected() {
		return this.connected;
	}

	public void setConnected(Boolean connected) {
		this.connected = connected;
	}

	public String getCorporation() {
		return this.corporation;
	}

	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLaboratory() {
		return this.laboratory;
	}

	public void setLaboratory(String laboratory) {
		this.laboratory = laboratory;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
	
}
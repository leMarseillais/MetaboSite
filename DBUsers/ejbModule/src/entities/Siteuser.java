package src.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@NamedQueries({
    @NamedQuery(
        name="OnePseudoPasswd", 
        query="SELECT u FROM Siteuser u "
        + "WHERE u.password LIKE :password AND u.login LIKE :login"),
        @NamedQuery(
                name="OnePseudo",
                query="SELECT u FROM Siteuser u "
                + "WHERE u.login LIKE :login")
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

	private Integer droit;

	private String email;

	private String laboratory;

	private String password;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="siteuser")
	private Set<Files> files;

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

	public Long getDroit() {
		Long droit = Long.parseLong(this.droit.toString());
		return droit;
	}

	public void setDroit(Long droit) {
		Integer droits=Integer.parseInt(droit.toString());
		this.droit = droits;
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

	public Set<Files> getFiles() {
		return this.files;
	}

	public void setProjects(Set<Files> projects) {
		this.files = projects;
	}
	
	public boolean is(Long right){
		Integer droits=Integer.parseInt(right.toString());
		return this.droit==droits;
	}
	
    public boolean has(Long droit) {
    	Integer droits=Integer.parseInt(droit.toString());
        return (this.droit & droits) == droits;
    }
    
    public boolean has(Long droit, int compareType) {
    	Integer droits=Integer.parseInt(droit.toString());
        if (compareType == AT_LEAST_ONE) {
            return (this.droit & droits) != 0;
        } else if (compareType == MAX) {
            return (this.droit | droits) == droits;
        } else if (compareType == INF) {
            return has(droit, MAX) && this.droit < droits;
        }
        
        return has(droit);
    }

	public void setDroit(Integer droit) {
		this.droit = droit;
	}

	public void setFiles(Set<Files> files) {
		this.files = files;
	}



	public static final int AT_LEAST_ONE = 0;
    public static final int COMPARE_ALL = 1;
    public static final int MAX = 2;
    public static final int INF = 3;

	public Siteuser(String login, Boolean connected, String corporation,
			Long droit, String email, String password) {
		Integer droits=Integer.parseInt(droit.toString());
		this.login = login;
		this.connected = connected;
		this.corporation = corporation;
		this.droit = droits;
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Siteuser [login=" + login + login.getClass()+"\n connected=" + connected
				+ connected.getClass()+"\n droit=" + droit+droit.getClass()
				+ "\n email=" + email + email.getClass()+"]";
	}
    
    
    
}
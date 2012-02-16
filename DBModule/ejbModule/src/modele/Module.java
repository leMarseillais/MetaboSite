package src.modele;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the modules database table.
 * 
 */
@Entity
@Table(name="modules")
public class Module implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String idmodule;

	private String deploymentlocation;

	private String description;

	private String subjet;

    public Module() {
    }

	public String getIdmodule() {
		return this.idmodule;
	}

	public void setIdmodule(String idmodule) {
		this.idmodule = idmodule;
	}

	public String getDeploymentlocation() {
		return this.deploymentlocation;
	}

	public void setDeploymentlocation(String deploymentlocation) {
		this.deploymentlocation = deploymentlocation;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubjet() {
		return this.subjet;
	}

	public void setSubjet(String subjet) {
		this.subjet = subjet;
	}

}
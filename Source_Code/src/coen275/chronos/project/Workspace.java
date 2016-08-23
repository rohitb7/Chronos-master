package coen275.chronos.project;

import java.sql.Date;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * 
 */
@Entity
public class Workspace extends GenericEntity{

	/**
	 * Default constructor
	 */
	public Workspace() {
		projects = new HashSet<>();
		name="default";
		admins = new HashSet<>();
	}

	/**
	 * 
	 */
	@OneToMany(mappedBy="parent")
	private Set<Project> projects;
	
	private String name;

	private String displayName;
	
	private String description;
	
	/**
	 * 
	 */
	@OneToMany(mappedBy="adminFor")
	private Set<User> admins;

	private int workspaceID;
	
	private Date createDate;
	
	private Date modifiedDate;
	/**
	 * @return 
	 * 
	 */
	public Set<Project> getProjects() {
		return projects;
	}

	/**
	 * @return 
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return 
	 * 
	 */
	public Set<User> getAdmins() {
		return admins;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the workspaceID
	 */
	public int getWorkspaceID() {
		return workspaceID;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
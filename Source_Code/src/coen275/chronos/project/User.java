package coen275.chronos.project;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * Models a project user 
 */
@Entity
public class User extends GenericEntity{

	@Column(unique=true, nullable=false)
	protected String username;

	protected String firstName;

	protected String lastName;

	protected String email;
	
	@ElementCollection(targetClass=Role.class)
	protected Set<Role> roles;

	private String password;

	@ManyToMany(mappedBy="teamMembers",
			fetch = FetchType.EAGER)
	private Set<Project> projects;

	@OneToOne(optional=true,mappedBy="owner")
	private Project ownsProject;
	
	@ManyToOne
	@JoinColumn(name="workspace_admin")
	private Workspace adminFor;
	
	@OneToMany(mappedBy="owner")
	private Set<Task> taskOwner;
	
	@OneToOne(optional=true, mappedBy="assignee")
	private Task taskAssignee;
	
	/**
	 * Default constructor
	 */
	public User() {
		projects = new HashSet<>();
		roles = new HashSet<>();
	}
	
	
	/**
	 * @param username
	 * @param firstName
	 * @param lastName
	 * @param password
	 */
	public User(String username, String firstName, String lastName, String password) {
		this();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}
	/**
	 * @return
	 */
	public String getUsername() {
		// TODO implement here
		return username;
	}

	/**
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * @return
	 */
	public String getLastName() {
		// TODO implement here
		return lastName;
	}

	/**
	 * @return
	 */
	public Set<Role> getRoles() {
		// TODO implement here
		return roles;
	}

	/**
	 * @return the projects
	 */
	public Set<Project> getProjects() {
		return projects;
	}

	/**
	 * @param projects the projects to set
	 */
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	public boolean isValid(String u, String p) {
		return this.username.equalsIgnoreCase(u) && this.password.equalsIgnoreCase(p);
	}

	public boolean isAdmin() {
		return this.roles.contains(Role.ADMIN);
	}
	public boolean isManager() {
		return this.roles.contains(Role.MANAGER);
	}
	public boolean isTeamMember() {
		return this.roles.contains(Role.TEAM_MEMBER);
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}
}
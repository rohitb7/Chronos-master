package coen275.chronos.project;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 */
@Entity
public class Project extends GenericEntity {

	@Column(name="projectName")
	private String name;

	@Column(name="project_key")
	private String key;
	
	private String description;

	@ManyToMany
	@JoinTable(
		name="project_users",
		joinColumns=@JoinColumn(name="project_id"),
		inverseJoinColumns=@JoinColumn(name="user_id")
			)
	private Set<User> teamMembers;

	private Date startDate;

	private Date endDate;

	private boolean isActive;

	private Status status;

	@OneToOne
	@JoinColumn(name="projects_owners")
	private User owner;

	@ManyToOne
	@JoinColumn(name="workspace_id", nullable=false)
	private Workspace parent;

	@OneToMany(cascade= CascadeType.ALL,mappedBy="project")
	private Set<Task> tasks;

	/**
	 * Default constructor
	 */
	public Project() {
		tasks = new HashSet<>();
		teamMembers = new HashSet<>();
	}
	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the teamMembers
	 */
	public Set<User> getTeamMembers() {
		return teamMembers;
	}

	/**
	 * @param teamMembers the teamMembers to set
	 */
	public void setTeamMembers(Set<User> teamMembers) {
		this.teamMembers = teamMembers;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * @return the parent
	 */
	public Workspace getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Workspace parent) {
		this.parent = parent;
	}

	public boolean addTask(Task t) {
		return tasks.add(t);
	}

	public boolean removeTask(Task t) {
		return tasks.remove(t);
	}

	/**
	 * @return the tasks
	 */
	public Set<Task> getTasks() {
		return tasks;
	}

	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	
}
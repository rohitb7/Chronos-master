package coen275.chronos.project;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 * 
 */
@Entity
public class Task extends GenericEntity{

	/**
	 * Default constructor
	 */
	public Task() {
		attachments = new HashSet<>();
		status = Status.UNASSIGNED;
	}

	
	/**
	 * 
	 */
	@ManyToOne
	@JoinColumn(name="project_tasks", nullable=false)
	private Project project;

	private Date dueDate;
	
	private Date asignedDate;

	@ManyToOne(optional=false)
	@JoinColumn(name="task_owner",nullable=false, updatable=false)
	private User owner;

	@OneToOne
	@JoinColumn(name="task_assignee",unique=false, nullable=true, updatable=true)
	private User assignee;

	private String description;

	private String summary;

	private Status status;
	
	private boolean privateTask;
	
	@Transient
	private Set<Attachment> attachments;

	@OneToMany
	private Set<User> watchers;
	
	@Column(name="task_key")
	private String key;
	
	
	
	public Task(Date dueDate, User owner, User assignee, String description, String summary,
			Status status) {
		super();
		this.dueDate = dueDate;
		this.asignedDate=new Date(System.currentTimeMillis());
		this.owner = owner;
		this.assignee = assignee;
		this.description = description;
		this.summary = summary;
		this.status = status;
		if(assignee!=null) {
			if(this.status!=null) {
				this.status = Status.ASSIGNED;
			}
		}
	}

	/**
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
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
	 * @return the assignee
	 */
	public User getAssignee() {
		return assignee;
	}

	/**
	 * @param assignee the assignee to set
	 */
	public void setAssignee(User assignee) {
		this.assignee = assignee;
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
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
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
	 * @return the privateTask
	 */
	public boolean isPrivateTask() {
		return privateTask;
	}

	/**
	 * @param privateTask the privateTask to set
	 */
	public void setPrivateTask(boolean privateTask) {
		this.privateTask = privateTask;
	}

	/**
	 * @return the attachments
	 */
	public Set<Attachment> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments the attachments to set
	 */
	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}

	public void addAttachment(Attachment a ) {
		this.attachments.add(a);
	}
	
	public void removeAttachment(Attachment a ) {
		this.attachments.remove(a);
	}

	/**
	 * @return the watchers
	 */
	public Set<User> getWatchers() {
		return watchers;
	}

	/**
	 * @param watchers the watchers to set
	 */
	public void setWatchers(Set<User> watchers) {
		this.watchers = watchers;
	}
	
	public void addWatcher(User user) {
		this.watchers.add(user);
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

	public Date getAsignedDate() {
		return asignedDate;
	}

	public void setAsignedDate(Date asignedDate) {
		this.asignedDate = asignedDate;
	}
	
	
	
	
	
}
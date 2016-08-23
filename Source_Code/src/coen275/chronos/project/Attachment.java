package coen275.chronos.project;

import javax.persistence.Lob;
import javax.persistence.OneToOne;

/**
 * 
 */
//@Entity
public class Attachment extends GenericEntity {

	/**
	 * Default constructor
	 */
	public Attachment() {
	}

	/**
	 * 
	 */
	private String name;

	/**
	 * 
	 */
	@Lob
	private Object attachment;

	@OneToOne
	private User createdBy;
	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public Object getAttachment() {
		return attachment;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param attachment the attachment to set
	 */
	public void setAttachment(Object attachment) {
		this.attachment = attachment;
	}

	/**
	 * @return the createdBy
	 */
	public User getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	
}
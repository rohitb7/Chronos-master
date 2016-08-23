package coen275.chronos.team;

import java.sql.Date;

import coen275.chronos.project.GenericEntity;

/**
 * 
 */
public class Post {

	/**
	 * Default constructor
	 */
	public Post() {
	}

	/**
	 * 
	 */
	private int postID;

	/**
	 * 
	 */
	private String title;

	/**
	 * 
	 */
	private String summary;

	/**
	 * 
	 */
	private Date timestamp;
	
	private GenericEntity createdBy;

	/**
	 * @return the postID
	 */
	public int getPostID() {
		return postID;
	}

	/**
	 * @param postID the postID to set
	 */
	public void setPostID(int postID) {
		this.postID = postID;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the createdBy
	 */
	public GenericEntity getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(GenericEntity createdBy) {
		this.createdBy = createdBy;
	}

}
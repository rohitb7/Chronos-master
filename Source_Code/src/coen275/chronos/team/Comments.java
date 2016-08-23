package coen275.chronos.team;

import coen275.chronos.project.GenericEntity;

/**
 * 
 */
public class Comments {

	/**
	 * Default constructor
	 */
	public Comments() {
	}

	/**
	 * 
	 */
	private java.sql.Date timeStamp;

	/**
	 * 
	 */
	private GenericEntity postedBy;

	/**
	 * 
	 */
	private String comment;

	/**
	 * 
	 */
	private int replyTo;

	/**
	 * 
	 */
	private int commentID;

	/**
	 * @return the timeStamp
	 */
	public java.sql.Date getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(java.sql.Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the postedBy
	 */
	public GenericEntity getPostedBy() {
		return postedBy;
	}

	/**
	 * @param postedBy the postedBy to set
	 */
	public void setPostedBy(GenericEntity postedBy) {
		this.postedBy = postedBy;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the replyTo
	 */
	public int getReplyTo() {
		return replyTo;
	}

	/**
	 * @param replyTo the replyTo to set
	 */
	public void setReplyTo(int replyTo) {
		this.replyTo = replyTo;
	}

	/**
	 * @return the commentID
	 */
	public int getCommentID() {
		return commentID;
	}

	/**
	 * @param commentID the commentID to set
	 */
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}



}
package coen275.chronos.notification;

import java.util.*;

import coen275.chronos.project.GenericEntity;

/**
 * 
 */
public class Notification {

	/**
	 * Default constructor
	 */
	public Notification() {
	}

	/**
	 * 
	 */
	private java.sql.Date timestamp;

	/**
	 * 
	 */
	private ChangeType type;

	/**
	 * 
	 */
	private SourceType source;

	/**
	 * 
	 */
	private GenericEntity initiator;

	/**
	 * 
	 */
	private Set<ChangeItem> changeSet;

	/**
	 * @return the timestamp
	 */
	public java.sql.Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(java.sql.Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the type
	 */
	public ChangeType getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ChangeType type) {
		this.type = type;
	}

	/**
	 * @return the source
	 */
	public SourceType getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(SourceType source) {
		this.source = source;
	}

	/**
	 * @return the initiator
	 */
	public GenericEntity getInitiator() {
		return initiator;
	}

	/**
	 * @param initiator the initiator to set
	 */
	public void setInitiator(GenericEntity initiator) {
		this.initiator = initiator;
	}

	/**
	 * @return the changeSet
	 */
	public Set<ChangeItem> getChangeSet() {
		return changeSet;
	}

	/**
	 * @param changeSet the changeSet to set
	 */
	public void setChangeSet(Set<ChangeItem> changeSet) {
		this.changeSet = changeSet;
	}

}
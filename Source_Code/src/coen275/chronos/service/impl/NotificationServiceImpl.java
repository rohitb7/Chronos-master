/**
 * 
 */
package coen275.chronos.service.impl;

import coen275.chronos.notification.Notification;
import coen275.chronos.project.Project;
import coen275.chronos.project.Task;
import coen275.chronos.service.NotificationService;

/**
 * @author kaushik
 *
 */
public class NotificationServiceImpl implements NotificationService {

	/* (non-Javadoc)
	 * @see coen275.chronos.service.NotificationService#notifyChanges(coen275.chronos.project.Task, coen275.chronos.notification.Notification)
	 */
	@Override
	public boolean notifyChanges(Task t, Notification n) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see coen275.chronos.service.NotificationService#notifyChanges(coen275.chronos.project.Project, coen275.chronos.notification.Notification)
	 */
	@Override
	public boolean notifyChanges(Project p, Notification n) {
		// TODO Auto-generated method stub
		return false;
	}

}

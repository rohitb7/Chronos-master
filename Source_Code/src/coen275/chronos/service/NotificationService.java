package coen275.chronos.service;

import coen275.chronos.notification.Notification;
import coen275.chronos.project.Project;
import coen275.chronos.project.Task;

public interface NotificationService {
	boolean notifyChanges(Task t, Notification n);
	boolean notifyChanges(Project p, Notification n);
}

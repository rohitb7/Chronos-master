/**
 * 
 */
package coen275.chronos.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import coen275.chronos.service.impl.AuthenticationServiceImpl;
import coen275.chronos.service.impl.CollaborationServiceImpl;
import coen275.chronos.service.impl.LoggingImpl;
import coen275.chronos.service.impl.NotificationServiceImpl;
import coen275.chronos.service.impl.ProjectServiceImpl;
import coen275.chronos.service.impl.SystemConfigServiceImpl;
import coen275.chronos.service.impl.UserManagementImpl;

/**
 * @author kaushik
 *
 */
public class Services {
	protected  AuthenticationService authService;
	protected  CollaborationService collaboration;
	protected  LoggingService loggingService;
	protected  SystemConfigurationService systemConfigService;
	protected  ProjectService projectService;
	protected  UserManagementService userMgmtService;
	protected NotificationService notificationService;
	
	protected static Services instance;
	
	protected static EntityManagerFactory emf;
	
	protected Services() {
		init();
	}

	protected void init() {
		authService = new AuthenticationServiceImpl();
		collaboration = new CollaborationServiceImpl();
		loggingService = new LoggingImpl();
		userMgmtService = new UserManagementImpl();
		systemConfigService = new SystemConfigServiceImpl(userMgmtService);
		projectService = new ProjectServiceImpl(systemConfigService, userMgmtService);
		notificationService = new NotificationServiceImpl();
	}
	
	public static Services getInstance() {
		if(instance==null) {
			instance = new Services();
		}
		return instance;
	}
	/**
	 * @return the authService
	 */
	public  AuthenticationService getAuthService() {
		return authService;
	}
	/**
	 * @return the collaboration
	 */
	public  CollaborationService getCollaboration() {
		return collaboration;
	}
	/**
	 * @return the loggingService
	 */
	public  LoggingService getLoggingService() {
		return loggingService;
	}
	/**
	 * @return the systemConfigService
	 */
	public  SystemConfigurationService getSystemConfigService() {
		return systemConfigService;
	}
	/**
	 * @return the projectService
	 */
	public  ProjectService getProjectService() {
		return projectService;
	}
	/**
	 * @return the userMgmtService
	 */
	public  UserManagementService getUserMgmtService() {
		return userMgmtService;
	}

	/**
	 * @return the notificationService
	 */
	public NotificationService getNotificationService() {
		return notificationService;
	}

	public static  void setEntityManagerFactory(EntityManagerFactory emf2) {
		emf = emf2;
		
	}
	
	public static EntityManager createEntityManager() {
		return emf.createEntityManager();
	}
}

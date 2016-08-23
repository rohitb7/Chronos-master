/**
 *
 */
package coen275.chronos.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import coen275.chronos.project.Project;
import coen275.chronos.project.Task;
import coen275.chronos.project.User;
import coen275.chronos.service.ProjectService;
import coen275.chronos.service.Services;
import coen275.chronos.service.SystemConfigurationService;
import coen275.chronos.service.UserManagementService;

/**
 * @author kaushik
 *
 */
public class ProjectServiceImpl implements ProjectService {

	private SystemConfigServiceImpl configSvc;
	private HashMap<Integer, Project> projects;
	
	public ProjectServiceImpl(SystemConfigurationService systemSvc, UserManagementService userSvc) {
		//		projects = new HashMap<>();
		//		this.configSvc = (SystemConfigServiceImpl) systemSvc;
		//		Set<Project> projects = this.configSvc.getProjects("default");
		//		for(Project p: projects) {
		//			this.projects.put(p.getId(),p);
		//		}
	}
	/* (non-Javadoc)
	 * @see coen275.chronos.service.ProjectService#createTask(int, coen275.chronos.project.Task)
	 */
	@Override
	public Task createTask(int projectID, Task t) {
		EntityManager em = Services.createEntityManager();
		em.getTransaction().begin();
		Project p = em.find(Project.class, projectID);
//		p.addTask(t);
		t.setProject(p);
		em.persist(t);
		em.getTransaction().commit();
		em.close();
		return t;
	}

	/* (non-Javadoc)
	 * @see coen275.chronos.service.ProjectService#updateTask(coen275.chronos.project.Task)
	 */
	@Override
	public boolean updateTask(Task t) {
		EntityManager em = Services.createEntityManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	/* (non-Javadoc)
	 * @see coen275.chronos.service.ProjectService#deleteTask(coen275.chronos.project.Task)
	 */
	@Override
	public boolean deleteTask(Task t) {
		throw new UnsupportedOperationException("Task Delete not implemented");
	}

	/* (non-Javadoc)
	 * @see coen275.chronos.service.ProjectService#addTeamMember(int, java.lang.String)
	 */
	@Override
	public boolean addTeamMember(int projectID, String username) {
		EntityManager em = Services.createEntityManager();
		em.getTransaction().begin();
		Project p = em.find(Project.class, projectID);
		User u = Services.getInstance().getUserMgmtService().getUser(username);
		if(!p.getTeamMembers().contains(u)) {
		p.getTeamMembers().add(u);
		}
		em.persist(p);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	/* (non-Javadoc)
	 * @see coen275.chronos.service.ProjectService#removeTeamMember(int, java.lang.String)
	 */
	@Override
	public boolean removeTeamMember(int projectID, String username) {
		EntityManager em = Services.createEntityManager();
		em.getTransaction().begin();
		Project p = em.find(Project.class, projectID);
		User u = Services.getInstance().getUserMgmtService().getUser(username);
		p.getTeamMembers().remove(u);
		em.persist(p);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	/* (non-Javadoc)
	 * @see coen275.chronos.service.ProjectService#createProjectClone(coen275.chronos.project.Project)
	 */
	@Override
	public Project createProjectClone(Project project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Project> getProjects(String username) {
//		UserManagementService svc = Services.getInstance().getUserMgmtService();
//		User u = svc.getUser(username);
//		if(u!=null) {
//			return new ArrayList<>(u.getProjects());
//		}
//		return null;
		EntityManager em = Services.createEntityManager();
		try {
			TypedQuery<Project> q = em
					.createQuery("Select p from Project p join p.teamMembers u where u.username= :name", Project.class);
			q.setParameter("name", username);
			return q.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Task> getTasks(int projectID) {
		EntityManager em = Services.createEntityManager();
		try {
			TypedQuery<Task> q = em.createQuery("Select t from Task t where t.project.id= :id", Task.class);
			q.setParameter("id", projectID);
			return q.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public boolean updateProject(Project p) {
		EntityManager em = Services.createEntityManager();
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
		em.close();
		return true;
	}
	@Override
	public Task getTask(int taskId) {
		EntityManager em = Services.createEntityManager();
		Task t = em.find(Task.class, taskId);
		em.close();
		return t;
	}
	@Override
	public Project getProject(int projectID) {
		EntityManager em = Services.createEntityManager();
		Project p = em.find(Project.class, projectID);
		return p;
	}
}

/**
 *
 */
package coen275.chronos.service.impl;

import static coen275.chronos.service.Services.createEntityManager;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import coen275.chronos.project.Project;
import coen275.chronos.project.User;
import coen275.chronos.project.Workspace;
import coen275.chronos.service.SystemConfigurationService;
import coen275.chronos.service.UserManagementService;
/**
 * @author kaushik
 *
 */
public class SystemConfigServiceImpl implements SystemConfigurationService {
	private HashMap<String,Workspace> workspaces = new HashMap<>();
	private List<User> users;
	private UserManagementService userSvc;

	public SystemConfigServiceImpl(UserManagementService usermgmt) {
		super();
		this.userSvc = usermgmt;
		Workspace temp_ws=new Workspace();
		Project prj=new Project();
		this.users=userSvc.getAllUsers();
		prj.setParent(temp_ws);
		prj.setTeamMembers(new HashSet<>(users));
		prj.setName("Test Project");
		prj.setDescription("this is a test project");
		prj.setKey("P1");
		prj.setStartDate(new Date(System.currentTimeMillis()));
		prj.setOwner(userSvc.getUser("manager"));
		temp_ws.getProjects().add(prj);
		this.workspaces.put(temp_ws.getName(),temp_ws);
	}

	/* (non-Javadoc)
	 * @see coen275.chronos.service.SystemConfigurationService#createWorkspace(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Workspace createWorkspace(String name, String displayName, String description) throws WorkspaceException {
		Workspace w = new Workspace();
		w.setName(name);
		w.setDisplayName(displayName);
		w.setDescription(description);
		EntityManager em = createEntityManager();
		em.getTransaction().begin();
		em.persist(w);
		em.getTransaction().commit();
		em.close();
		return w;
	}

	/* (non-Javadoc)
	 * @see coen275.chronos.service.SystemConfigurationService#updateWorkspace(coen275.chronos.project.Workspace)
	 */
	@Override
	public Workspace updateWorkspace(Workspace w) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Workspace wspc = em.find(Workspace.class, w.getId());
		wspc.setDescription(w.getDescription());
		wspc.setDisplayName(w.getDisplayName());
		wspc.getAdmins().addAll(w.getAdmins());
		wspc.getProjects().addAll(w.getProjects());
		em.persist(wspc);
		tx.commit();
		return wspc;
	}

	/* (non-Javadoc)
	 * @see coen275.chronos.service.SystemConfigurationService#createProject(int, coen275.chronos.project.Project)
	 */
	@Override
	public Project createProject(int workspaceId, Project p) {
		EntityManager em = createEntityManager();
		Workspace w = em.find(Workspace.class, new Integer(workspaceId));
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p.setParent(w);
		em.persist(p);
		w.getProjects().add(p);
		em.persist(w);
//		for(User u: p.getTeamMembers()) {
//			u.getProjects().add(p);
//			em.persist(u);
//		}
		tx.commit();
		return p;
	}

	@Override
	public Workspace getWorkspace(String name) {
		EntityManager em = createEntityManager();
		TypedQuery<Workspace> q = em.createQuery("Select w from Workspace w where name = :name", Workspace.class);
		q.setParameter("name", name);
		q.setMaxResults(1);
		List<Workspace> list = q.getResultList();
		if(list==null || list.size()==0) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * @return the workspaces
	 */
	public List<Workspace> getWorkspaces() {
		EntityManager em = createEntityManager();
		TypedQuery<Workspace> q = em.createQuery("Select w from Workspace w", Workspace.class);
		return q.getResultList();
	}

	@Override
	public Project createProject(String workspaceName, Project p) {
		Workspace w = workspaces.get(workspaceName);
		boolean b = w.getProjects().add(p);
		p.setParent(w);
		saveProject(p);
		return p;
	}

	/**
	 * Save project to database
	 */
	private void saveProject(Project p) {

	}

	public List<Project> getProjects(String s) {
		EntityManager em = createEntityManager();
		TypedQuery<Project> q = em.createQuery("Select p from Project p where p.parent.name=:name", Project.class);
		q.setParameter("name", s);
		return q.getResultList();
	}
}

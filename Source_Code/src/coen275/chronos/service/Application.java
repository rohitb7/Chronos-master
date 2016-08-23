package coen275.chronos.service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import coen275.chronos.project.Project;
import coen275.chronos.project.Role;
import coen275.chronos.project.Status;
import coen275.chronos.project.Task;
import coen275.chronos.project.User;
import coen275.chronos.project.Workspace;
import coen275.chronos.service.impl.WorkspaceException;

/**
 * Application Lifecycle Listener implementation class Application
 *
 */
@WebListener
public class Application implements ServletContextListener {

	/**
	 * Default constructor. 
	 */
	public Application() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-mysql");
		Services.setEntityManagerFactory(emf);
		Services svc = Services.getInstance();
		try {
			checkWorkspace(svc);
			
			System.out.println("*******************************");
			System.out.println("Chronos INTIALIZED Successfully");
			System.out.println("*******************************");
		} catch (Exception e) {
			throw new IllegalStateException("Error initializing workspace");
		}

	}


	public void checkWorkspace(Services svc) throws WorkspaceException {
		SystemConfigurationService sysCfg = svc.getSystemConfigService();
		Workspace ws = sysCfg.getWorkspace("default");
		if(ws==null||ws.getId()<=0) {
			UserManagementService usvc = svc.getUserMgmtService();
			User u = new User("admin","Administrator","Administrator","admin");
			u.getRoles().add(Role.ADMIN);
			usvc.createUser(u);
			Workspace w = sysCfg.createWorkspace("default", "Default Workspace", "This is default Workspace");
			//add admin user to workspace
			w.getAdmins().add(u);
			sysCfg.updateWorkspace(w);
			
			//create a dummy project
			Project p = new Project();
			p.setKey("CHRONOS");
			p.setName("Chronos Project Management");
			p.setDescription("This is a sample project");
			//set owner as admin
			p.setOwner(u);
			p.setParent(w);
			User u1 = new User("kaushik","Kaushik","K","admin");
			User u2 = new User("himanshu","Himanshu","Gupta","admin");
			User u3 = new User("aparna","Aparna","Arunkumar","admin");
			User u4 = new User("rohit","Rohit","Borade","admin");
			User u5 = new User("ruchika","Ruchika","Sharma","admin");
			usvc.createUser(u1);
			usvc.createUser(u2);
			usvc.createUser(u3);
			usvc.createUser(u4);
			usvc.createUser(u5);
			p.getTeamMembers().add(u1);
			p.getTeamMembers().add(u2);
			p.getTeamMembers().add(u3);
			p.getTeamMembers().add(u4);
			p.getTeamMembers().add(u5);
			sysCfg.createProject(w.getId(), p);
			p = new Project();
			p.setName("test project");
			p.setKey("TEST1");
			p.setDescription("desc");
			p.setOwner(u1);
			p.setParent(w);
			sysCfg.createProject(w.getId(), p);
			
			p=new Project();
			p.setName("second test project");
			p.setKey("TEST2");
			p.setDescription("desc2");
			p.setOwner(u);
			p.setParent(w);
			sysCfg.createProject(w.getId(), p);
			
			createTasks(p);
		}
		
	}

	private void createTasks(Project p) {
		UserManagementService uSvc = Services.getInstance().getUserMgmtService();
		Task t1 = new Task();
		
		t1.setSummary("User interface");
		t1.setDescription("Create User interface");
		t1.setOwner(uSvc.getUser("kaushik"));
		t1.setAssignee(uSvc.getUser("himanshu"));
		t1.setStatus(Status.IN_PROGRESS);
		ProjectService pSvc = Services.getInstance().getProjectService();
		pSvc.createTask(p.getId(), t1);
	}
}


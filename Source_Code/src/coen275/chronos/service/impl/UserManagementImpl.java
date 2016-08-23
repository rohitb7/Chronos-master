package coen275.chronos.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import coen275.chronos.project.Project;
import coen275.chronos.project.Role;
import coen275.chronos.project.User;
import coen275.chronos.service.Services;
import coen275.chronos.service.UserManagementService;
import static coen275.chronos.service.Services.createEntityManager;
/**
 *
 */
public class UserManagementImpl implements UserManagementService {
	private HashMap<String, User> users;
	/**
	 * Default constructor
	 */
	public UserManagementImpl() {
//		this.users=new HashMap<>();
//		User owner = new User();
//		User manager = new User();
//		User teamMember1 = new User();
//		User teamMember2 = new User();
//		User admin = new User();
//
//		owner.setFirstName("OwnerFirstName");
//		owner.setLastName("OwnerLastName");
//		owner.setUsername("owner");
//
//		teamMember1.setFirstName("Member1 FirstName");
//		teamMember1.setLastName("Member1 LastName");
//		teamMember1.setUsername("teamMember1");
//		teamMember2.setFirstName("Member2 FirstName");
//		teamMember2.setLastName("Member2 LastName");
//		teamMember2.setUsername("teamMember2");
//
//		manager.setFirstName("Manager FirstName");
//		manager.setLastName("Manager LastName");
//		manager.setUsername("manager");
//
//		admin.setFirstName("Admin FirstName");
//		admin.setLastName("Admin LastName");
//		admin.setUsername("admin");
//
//		this.createUser(admin);
//		this.createUser(teamMember1);
//		this.createUser(teamMember2);
//		this.createUser(owner);
//		this.createUser(manager);
	}


	@Override
	public User createUser(User u) {
		EntityManager em = Services.createEntityManager();
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
		em.close();
		return u;
	}


	@Override
	public User getUser(String username) {
		EntityManager em = createEntityManager();
		List<User> r = getUser(username, em);
		if(r==null || r.isEmpty()) {
			return null;
		}
		return r.get(0);
	}


	protected List<User> getUser(String username, EntityManager em) {
		Query q = em.createQuery("Select u from User u where username = :name",User.class);
		q.setParameter("name", username);
		List<User> r = q.getResultList();
		return r;
	}



	@Override
	public boolean updateUser(User u) {
		EntityManager em = createEntityManager();
		em.getTransaction().begin();
		User user = em.find(User.class, u.getId());
		user.setFirstName(u.getFirstName());
		user.setLastName(u.getLastName());
		user.setEmail(u.getEmail());
		for(Project p: u.getProjects()) {
			if(!user.getProjects().contains(p)) {
				user.getProjects().add(p);
			}
		}
		for(Role r: u.getRoles()) {
			if(!user.getRoles().contains(r)) {
				user.getRoles().add(r);
			}
		}
		em.merge(user);
		em.getTransaction().commit();
		return false;
	}


	@Override
	public boolean isUserAvailable(String username) {
		EntityManager em = createEntityManager();
		List<User> r = getUser(username, em);
		return r!=null && r.size()==1;
	}


	@Override
	public boolean addRole(String username, Role r) {
		EntityManager em = createEntityManager();
		List<User> list = getUser(username,em);
		if(list!=null && list.size()==1) {
			list.get(0).getRoles().add(r);
			return true;
		}
		return false;
	}


	@Override
	public boolean addRoles(String username, Set<Role> roles) {
		EntityManager em = createEntityManager();
		List<User> list = getUser(username,em);
		if(list!=null && list.size()==1) {
			list.get(0).getRoles().addAll(roles);
			return true;
		}
		return false;
	}


	@Override
	public User signup(String username, String firstName, String lastname, String password) {
		EntityManager em = Services.createEntityManager();
		User u = new User(username,firstName,lastname,password);
		em.persist(u);
		em.getTransaction().commit();
		em.close();
		return u;
	}


	@Override
	public List<User> getAllUsers() {
		EntityManager em = createEntityManager();
		TypedQuery<User> q = em.createQuery("Select u from User u",User.class);
		return q.getResultList();
	}


	@Override
	public User getUser(int userId) {
		EntityManager em = createEntityManager();
		User u = em.find(User.class, userId);
		return u;
	}
	
}
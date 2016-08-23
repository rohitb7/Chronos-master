package coen275.chronos.service;

import java.util.List;
import java.util.Set;

import coen275.chronos.project.Role;
import coen275.chronos.project.User;

/**
 *
 */
public interface UserManagementService {
	User signup(String username, String firstName, String lastname, String password);
	User createUser(User u);
	User getUser(String username);
	boolean updateUser(User u);
	boolean isUserAvailable(String username);
	boolean addRole(String username, Role r);
	boolean addRoles(String username, Set<Role> roles);
	List<User> getAllUsers();
	User getUser(int userId);
}
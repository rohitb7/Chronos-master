package coen275.chronos.service;

import coen275.chronos.project.User;

public interface AuthenticationService {
	boolean isLoggedIn(String username);
	User login(String username, String password);
	boolean logout(String username);
}

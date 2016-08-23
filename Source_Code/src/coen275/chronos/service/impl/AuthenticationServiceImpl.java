/**
 * 
 */
package coen275.chronos.service.impl;

import coen275.chronos.project.User;
import coen275.chronos.service.AuthenticationService;
import coen275.chronos.service.Services;
import coen275.chronos.service.UserManagementService;

/**
 * @author kaushik
 *
 */
public class AuthenticationServiceImpl implements AuthenticationService {

	/* (non-Javadoc)
	 * @see coen275.chronos.service.AuthenticationService#isLoggedIn(java.lang.String)
	 */
	@Override
	public boolean isLoggedIn(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see coen275.chronos.service.AuthenticationService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public User login(String username, String password) {
		UserManagementService usvc = Services.getInstance().getUserMgmtService();
		User u = usvc.getUser(username);
		if(u!=null && u.isValid(username,password)) {
			return u;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see coen275.chronos.service.AuthenticationService#logout(java.lang.String)
	 */
	@Override
	public boolean logout(String username) {
		// TODO Auto-generated method stub
		return false;
	}

}

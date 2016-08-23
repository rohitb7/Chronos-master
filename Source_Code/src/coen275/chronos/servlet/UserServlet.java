package coen275.chronos.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import coen275.chronos.project.Project;
import coen275.chronos.project.Role;
import coen275.chronos.project.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/users")
public class UserServlet extends ChronosServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see ChronosServlet#ChronosServlet()
     */
    public UserServlet() {
        super();
    }

    @Override
    protected void defaultHandler(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	String action = request.getParameter("action");
    	if("signup".equalsIgnoreCase(action)) {
    		create(request,response);
    	}
    	return;
    }
	@Override
	public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String firstName=request.getParameter("fname");
		String lastName=request.getParameter("lname");
		String email=request.getParameter("email");
		User u = new User(username, firstName, lastName, password);
		u.setEmail(email);
		u.getRoles().add(Role.TEAM_MEMBER);
		userSvc.createUser(u);
		forward("index.jsp",request,response);
		return;
	}

	@Override
	public void editRecord(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		try {
			if(id!=null) {
				int i = Integer.parseInt(id);
				User u = userSvc.getUser(i);
				if(u!=null) {
					String firstName=request.getParameter("fname");
					String lastName=request.getParameter("lname");
					String email=request.getParameter("email");
					u.setFirstName(firstName);
					u.setLastName(lastName);
					u.setEmail(email);
					String[] projects = request.getParameterValues("projects");
					userSvc.updateUser(u);
					if(projects!=null) {
						for(String pi: projects) {
							if(pi!=null && pi.trim().length()>0) {
								try {
									int pid = Integer.parseInt(pi);
									projectSvc.addTeamMember(pid, u.getUsername());
								} catch (Exception e) {
									//Dont worry about this
									e.printStackTrace();
								}
							}
						}
					}
					response.sendRedirect("/chronos/users?action=view");
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void viewOne(String id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int userId = Integer.parseInt(id);
		User u = userSvc.getUser(userId);
		String type=request.getParameter("type");
		if("json".equalsIgnoreCase(type)) {
			sendJson(u,request,response);
		}else {
			request.setAttribute("item", u);
			forward("view-user.jsp",request,response);
		}
	}

	private void sendJson(User u, HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		JsonObject obj = new JsonObject();
		obj.addProperty("id", u.getId());
		obj.addProperty("username", u.getUsername());
		obj.addProperty("fname", u.getFirstName());
		obj.addProperty("lname", u.getLastName());
		obj.addProperty("email", u.getEmail());
		JsonArray projects = new JsonArray();
		if(u.getProjects()!=null) {
			for(Project p: u.getProjects()) {
				JsonObject o = new JsonObject();
				o.addProperty("id", p.getId());
				o.addProperty("prjName", p.getName());
				o.addProperty("prjKey", p.getName());
				projects.add(o);
			}
		}
		obj.add("projects", projects);
		JsonArray roles = new JsonArray();
		if(u.getRoles()!=null) {
			for(Role r: u.getRoles()) {
				roles.add(r.toString());
			}
		}
		//all projects
		
		obj.add("roles",roles);
		Gson g = new Gson();
		g.toJson(obj,response.getWriter());
		response.flushBuffer();
		return;
	}

	@Override
	public void viewAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = getLoggedInUser(request);
		List<User> users= userSvc.getAllUsers();
		request.setAttribute("users", users);
		forward("users.jsp",request,response);
		return;
	}

	 @Override
	protected boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 String action=request.getParameter("action");
		 if("signup".equalsIgnoreCase(action)) {
			 return true;			 
		 }else {
			 return super.checkLogin(request, response);
		 }
	}
}

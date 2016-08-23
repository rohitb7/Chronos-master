package coen275.chronos.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coen275.chronos.project.Role;
import coen275.chronos.project.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends ChronosServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		HttpSession session = request.getSession(true);
		log("login servlet....");
		User user = (User) session.getAttribute("user");
		if(session.isNew()||user==null) {
			log("login::new login.....");
			request.removeAttribute("message");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {
			log("login::index page");
			request.setAttribute("message", "Welcome!!! "+user.getFirstName());
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if("login".equalsIgnoreCase(action)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User u = authSvc.login(username, password);
			if(u!=null) {
				HttpSession s = request.getSession(true);
				s.setAttribute("user", u);
				s.setAttribute("adminUser", u.getRoles().contains(Role.ADMIN));
				request.setAttribute("error",false);
				request.setAttribute("message","Login Successful! Welcome "+u.getFirstName());
				response.sendRedirect("projects?action=view");
			}else {
				request.getSession().invalidate();
				request.setAttribute("error",true);
				request.setAttribute("message", "Incorrect Login Credentials");
				forward("login.jsp",request,response);
			}
		}else {
		
		}
	}

	@Override
	public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editRecord(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewOne(String id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}

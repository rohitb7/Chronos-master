package coen275.chronos.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coen275.chronos.project.User;
import coen275.chronos.service.AuthenticationService;
import coen275.chronos.service.CollaborationService;
import coen275.chronos.service.LoggingService;
import coen275.chronos.service.NotificationService;
import coen275.chronos.service.ProjectService;
import coen275.chronos.service.Services;
import coen275.chronos.service.SystemConfigurationService;
import coen275.chronos.service.UserManagementService;

public abstract class ChronosServlet extends HttpServlet implements CRUDServlet{

	protected static final SimpleDateFormat dateFormatter = new SimpleDateFormat("y-M-d");
	protected ProjectService projectSvc;
	protected SystemConfigurationService systemConfigSvc;
	protected UserManagementService userSvc;
	protected AuthenticationService authSvc;
	protected CollaborationService collaborationSvc;
	protected NotificationService notificationSvc;
	protected LoggingService loggingSvc;

	public ChronosServlet() {
		super();
        Services svc = Services.getInstance();
        this.projectSvc = svc.getProjectService();
        this.systemConfigSvc = svc.getSystemConfigService();
        this.userSvc = svc.getUserMgmtService();
        this.authSvc = svc.getAuthService();
        this.collaborationSvc = svc.getCollaboration();
        this.notificationSvc = svc.getNotificationService();
        this.loggingSvc  = svc.getLoggingService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean valid = checkLogin(request, response);
		if(!valid) {
			return ;
		}
		String action = request.getParameter("action");
		SimpleDateFormat formater = new SimpleDateFormat("y-MM-dd");
		request.setAttribute("dateFormat", formater);
		if("view".equalsIgnoreCase(action)) {
			view(request,response);
		}else if("edit".equalsIgnoreCase(action)) {
			edit(request,response);
		}else if("create".equalsIgnoreCase(action)) {
			create(request,response);
		}else {
			defaultHandler(request,response);
		}
		return;
	}

	protected void defaultHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		view(request,response);
	}

	protected boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(!isLoggedIn(request.getSession()) ){
			response.sendRedirect("login.jsp");
			return false;
		}
		return true;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("application/json");
		editRecord(request,response);
	}

	protected void view(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		if(id==null) {
			viewAll(request,response);
		}else {
			viewOne(id, request, response);
		}
		return;
	}

	protected User getLoggedInUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute("user");
	}
	/**
	 * check if a user is logged in.
	 * @return
	 */
	private boolean isLoggedIn(HttpSession session) {
		if(session==null || session.getAttribute("user")==null)  {
			return false;
		}
		return true;
	}

	/**
	 * Forward to login page
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void showLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			forward("login.jsp",request,response);
			return;
	}
	
	protected void forward(String page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(page).forward(request, response);
	}
	protected void redirect(String page, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(page);;
	}
	
}
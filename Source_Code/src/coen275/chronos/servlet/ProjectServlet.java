package coen275.chronos.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.taglibs.standard.tag.common.core.RemoveTag;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import coen275.chronos.project.GenericEntity;
import coen275.chronos.project.Project;
import coen275.chronos.project.Role;
import coen275.chronos.project.Status;
import coen275.chronos.project.User;
import coen275.chronos.project.Workspace;

/**
 * Servlet implementation class ProjectServlet
 */
public class ProjectServlet extends ChronosServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void defaultHandler(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if("addMember".equalsIgnoreCase(action)){
			if(addTeamMembers(request,response))
				request.setAttribute("addMemberResp", true);
			else
				request.setAttribute("addMemberResp", false);
		}
		else if("removeMember".equals(action)){
			if(removeTeamMember(request,response))
				request.setAttribute("removeMemberResp", true);
			else
				request.setAttribute("removeMemberResp", false);
		}
		forward("projects.jsp", request, response);
	} 

	@Override
	public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Project p =new Project();
		if(request.getParameter("prjKey")!=null){
			String prjKey = request.getParameter("prjKey");
			p.setKey(prjKey);
		}
		if(request.getParameter("prjName")!=null){
			String prjName = request.getParameter("prjName");
			p.setName(prjName);
		}
		if(request.getParameter("prjDesc")!=null){
			String prjDescription = request.getParameter("prjDesc");
			p.setDescription(prjDescription);
		}
		else{
			System.out.println("ProjectServlet Create: parameter 'prjDesc' is Null");
			forward("projects.jsp", request, response);
			return;
		}
		if(request.getParameter("prjOwner")!=null){
			User prjOwner = userSvc.getUser(request.getParameter("prjOwner"));   //Here getUser is by username and not userID
			p.setOwner(prjOwner);
		}
		boolean isActive;
		Date startDate=null;
		Date endDate =null;
		try {
			if(request.getParameter("prjStartDate")!=null){
				startDate = dateFormatter.parse(request.getParameter("prjStartDate"));
				p.setStartDate(startDate);
			}
			if(request.getParameter("prjEndDate")!=null){
				endDate = dateFormatter.parse(request.getParameter("prjEndDate"));
				p.setEndDate(endDate);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(request.getParameter("isActive")!=null)
			isActive=Boolean.parseBoolean(request.getParameter("isActive"));
		else
			isActive= false;
		
		Status prjStatus = Status.NOT_STARTED;
		Workspace parentWS = systemConfigSvc.getWorkspace("default");
		p.setStartDate(startDate);
		p.setEndDate(endDate);
		p.setStatus(prjStatus);
		p.setParent(parentWS);
		p.setActive(isActive);
		systemConfigSvc.createProject(parentWS.getId(), p);
		 HttpSession session = request.getSession();
		session.setAttribute("message", "Project - "+p.getKey()+" created");
		redirect("/chronos/projects?action=view", request, response);	

	}

	@Override
	public void editRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		if(id!=null){
			Project p = projectSvc.getProject(Integer.parseInt(id));
			if(p!=null){
				if(request.getParameter("prjName")!=null){
					String prjName = request.getParameter("prjName");
					p.setName(prjName);
				}
				if(request.getParameter("prjDesc")!=null){
					String prjDescription = request.getParameter("prjDesc");
					p.setDescription(prjDescription);
				}
				String status = request.getParameter("prjStatus");
				if(status!=null && !status.contains("undefined")){
					Status prjStatus = Status.valueOf(status);
					p.setStatus(prjStatus);
				}
				if(request.getParameter("prjOwner")!=null){
					User prjOwner = userSvc.getUser(request.getParameter("prjOwner"));   //Here getUser is by username and not userID
					p.setOwner(prjOwner);
				}
				boolean isActive;
				Date startDate=null;
				Date endDate =null;
				try {
					if(request.getParameter("prjStartDate")!=null){
						startDate = dateFormatter.parse(request.getParameter("prjStartDate"));
						p.setStartDate(startDate);
					}
					if(request.getParameter("prjEndDate")!=null){
						endDate = dateFormatter.parse(request.getParameter("prjEndDate"));
						p.setEndDate(endDate);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if(request.getParameter("isActive")!=null){
					isActive=Boolean.parseBoolean(request.getParameter("isActive"));
					p.setActive(isActive);
					System.out.println("ISACTIVE: "+request.getParameter("isActive"));
				}
				projectSvc.updateProject(p);
				request.getSession().setAttribute("message", "Project - "+p.getKey()+" saved");
				response.sendRedirect("/chronos/projects?action=view");
			}
		}
		
	}

	private boolean removeTeamMember(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		if(request.getParameter("prjId")!=null){
			int prjId = Integer.parseInt(request.getParameter("prjId"));
			if(request.getParameterValues("teamMembers")!=null){
				String[] memberList = request.getParameterValues("teamMembers");
				try{
					for(int i = 0; i<memberList.length;i++)
						projectSvc.removeTeamMember(prjId, memberList[i]);
					return true;
				}catch(Exception e){
					e.printStackTrace();
					return false;
				}
			}
			System.out.println("ProjectServlet removeTeamMember: Parameter 'teamMembers' is Null");
			return false;
		}
		System.out.println("ProjectServlet removeTeamMember: Parameter 'prjId' is Null");
		return false;
	}

	private boolean addTeamMembers(HttpServletRequest request, HttpServletResponse response) {
		if(request.getParameter("prjId")!=null){
			int prjId = Integer.parseInt(request.getParameter("prjId"));
			if(request.getParameterValues("teamMembers")!=null){
				String[] membersList = request.getParameterValues("teamMembers");
				try{
					for(int i = 0;i<membersList.length;i++){
						projectSvc.addTeamMember(prjId, membersList[i]);
					}
					return true;
				}catch (Exception e){
					e.printStackTrace();
					return false;
				}
			}
			System.out.println("ProjectServlet addTeamMember: Parameter 'teamMemebers' is Null");
			return false;
		}
		System.out.println("ProjectServlet addTeamMember: Parameter 'projectId' is Null");
		return false;
	}

	@Override
	public void viewOne(String id, HttpServletRequest request, HttpServletResponse response) 
			throws JsonIOException, IOException, ServletException {
		// TODO Auto-generated method stub
		int prjId = Integer.parseInt(id);
		Project p = projectSvc.getProject(prjId);
		String type = request.getParameter("type");
		if("json".equalsIgnoreCase(type)){ 
			toJson(p, request, response);
		}else{
			request.setAttribute("item", p);
			forward("view-project.jsp",request,response);
		}
	}

	@Override
	public void viewAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String scope = request.getParameter("scope");
		String scopeId = request.getParameter("scopeId");
		
		List<Project> projects;
		if("workspace".equalsIgnoreCase(scope)) {
			projects = systemConfigSvc.getProjects("default");
		}else {
			User user = getLoggedInUser(request);
			if(user.getRoles().contains(Role.ADMIN)) {
				projects = systemConfigSvc.getProjects("default");
			}else {
				projects = projectSvc.getProjects(user.getUsername());
			}
		}
		String type = request.getParameter("type");
		if("json".equalsIgnoreCase(type)) {
			toJson(projects, request,response);
		}else {
			request.setAttribute("projects", projects);
//			request.setAttribute("content", "projects-table.jsp");
			forward("projects.jsp",request,response);
		}
	}

	private void toJson(List<Project> projects, HttpServletRequest request, HttpServletResponse response) throws JsonIOException, IOException {
		response.setContentType("application/json");
		JsonArray arr = new JsonArray();
		if(projects!=null) {
			for(Project p: projects) {
				JsonObject o = new JsonObject();
				o.addProperty("id", p.getId());
				o.addProperty("prjName", p.getName());
				o.addProperty("prjKey", p.getKey());
				o.addProperty("prjDesc", p.getDescription());
				if(p.getStatus()!=null)
					o.addProperty("prjStatus", p.getStatus().toString());
				if(p.getOwner()!=null)
					o.addProperty("prjOwner", p.getOwner().getUsername());
				if(p.getStartDate()!=null)
					o.addProperty("prjStartDate", p.getStartDate().toString());
				if(p.getEndDate()!=null)
					o.addProperty("prjEndDate", p.getEndDate().toString());
				o.addProperty("isActive", p.isActive());
//				o.addProperty("prjTeamMembers", p.getTeamMembers().);
				arr.add(o);
			}
		}
		Gson g = new Gson();
		JsonObject obj = new JsonObject();
		obj.add("projects", arr);
		g.toJson(obj,response.getWriter());
		response.flushBuffer();
		return;
	}
	
	private void toJson(Project p, HttpServletRequest request, HttpServletResponse response) throws JsonIOException, IOException {
		response.setContentType("application/json");
		JsonObject o = new JsonObject();
		o.addProperty("id", p.getId());
		o.addProperty("prjName", p.getName());
		o.addProperty("prjKey", p.getKey());
		o.addProperty("prjDesc", p.getDescription());
		if(p.getStatus()!=null)
			o.addProperty("prjStatus", p.getStatus().toString());
		if(p.getOwner()!=null)
			o.addProperty("prjOwner", p.getOwner().getUsername());
		if(p.getStartDate()!=null)
			o.addProperty("prjStartDate", p.getStartDate().toString());
		if(p.getEndDate()!=null)
			o.addProperty("prjEndDate", p.getEndDate().toString());
		o.addProperty("isActive", p.isActive());
		//				o.addProperty("prjTeamMembers", p.getTeamMembers().);
		Gson g = new Gson();
		g.toJson(o,response.getWriter());
		response.flushBuffer();
		return;
	}
	
}

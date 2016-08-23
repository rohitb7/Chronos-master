package coen275.chronos.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import coen275.chronos.project.Project;
import coen275.chronos.project.Role;
import coen275.chronos.project.Status;
import coen275.chronos.project.Task;
import coen275.chronos.project.User;
import javassist.bytecode.stackmap.BasicBlock.Catch;

/**
 * Servlet implementation class TaskServlet
 */
@WebServlet("/tasks")
public class TaskServlet extends ChronosServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	@Override
	public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String projectID = (String) session.getAttribute("projectID");		
		String taskSummary = request.getParameter("taskSummary");
		String taskDescription = request.getParameter("taskDesc");
		Status taskStatus = Status.NOT_STARTED;
		User taskAssignee = userSvc.getUser(request.getParameter("taskAssignee"));
		User taskOwner = (User) session.getAttribute("user");
		Date dueDate = null;
		try {
			dueDate = dateFormatter.parse(request.getParameter("dueDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Due Date for task is not set");
		}
		Task t = new Task(dueDate,taskOwner,taskAssignee,taskDescription,taskSummary,taskStatus);
		t = projectSvc.createTask(Integer.parseInt(projectID), t);
		session.setAttribute("message", "Task# "+t.getId()+"created.");
		List<Task> tasks = projectSvc.getTasks(Integer.parseInt(projectID));
		request.setAttribute("tasks", tasks);
		request.getSession().setAttribute("projectID", projectID);
		redirect("/chronos/tasks?action=view&projectId="+projectID,request,response);
	}

	@Override
	public void editRecord(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String projectID = (String) session.getAttribute("projectID");		
		String id = request.getParameter("id");
		if(id!=null){
			int i = Integer.parseInt(id);
			Task t = projectSvc.getTask(i);
			String type = request.getParameter("type");
			
			if(t!=null){	
				if("status".equalsIgnoreCase(type)) {
					String status = request.getParameter("taskStatus");
					Status taskStatus = Status.valueOf(status);
					t.setStatus(taskStatus);
					projectSvc.updateTask(t);
					sendJson(t, request, response);
					return;
				}
				String taskSummary = request.getParameter("taskSummary");
				String taskDescription = request.getParameter("taskDesc");
				String status = request.getParameter("taskStatus");
				if(status!=null && !status.contains("string")) {
					Status taskStatus = Status.valueOf(status);
					t.setStatus(taskStatus);
				}
				String assignee = request.getParameter("taskAssignee");
				User taskAssignee;
				if(assignee!=null) {
					taskAssignee = userSvc.getUser(assignee);
					t.setAssignee(taskAssignee);
				}
				Date dueDate = null;
				try {
					dueDate = dateFormatter.parse(request.getParameter("dueDate"));
					t.setDueDate(dueDate);
				} catch (ParseException e) {
					
				}
				t.setSummary(taskSummary);
				t.setDescription(taskDescription);
				
				
				projectSvc.updateTask(t);
				session.setAttribute("message", "Task# "+t.getId()+"updated");
				redirect("/chronos/tasks?action=view&projectId="+projectID,request,response);
			}
		}			
	}

	@Override
	public void viewOne(String id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter("type");
		Task task = projectSvc.getTask(Integer.parseInt(id));
		if("json".equalsIgnoreCase(type)) {
			sendJson(task,request,response);
		}else {
			request.setAttribute("item", task);
			forward("view-task.jsp",request,response);
		}
		
	}

	private void sendJson(Task task, HttpServletRequest request, HttpServletResponse response) throws JsonIOException, IOException {
		response.setContentType("application/json");
		JsonObject obj = new JsonObject();
		obj.addProperty("action", "view");
		obj.addProperty("id", task.getId());
		obj.addProperty("taskSummary", task.getSummary());
		obj.addProperty("taskDesc", task.getDescription());
		if(task.getStatus()!=null)
			obj.addProperty("taskStatus", task.getStatus().toString());
		User assignee = task.getAssignee();
		if(assignee!=null)
			obj.addProperty("taskAssignee", assignee.getUsername());
		if(task.getOwner()!=null)
			obj.addProperty("taskOwner", task.getOwner().getUsername());
		if(task.getDueDate()!=null)
			obj.addProperty("dueDate", task.getDueDate().toString());
		Gson g = new Gson();
		g.toJson(obj,response.getWriter());
		response.flushBuffer();
		return;
	}

	@Override
	public void viewAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String projectID = request.getParameter("projectId");
		List<Task> tasks = projectSvc.getTasks(Integer.parseInt(projectID));
		request.setAttribute("tasks", tasks);
		request.getSession().setAttribute("projectID", projectID);
		forward("tasks.jsp",request,response);
	}

}

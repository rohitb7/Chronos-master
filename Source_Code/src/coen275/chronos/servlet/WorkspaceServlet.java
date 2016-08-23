/**
 * 
 */
package coen275.chronos.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coen275.chronos.project.Workspace;
import coen275.chronos.service.Services;
import coen275.chronos.service.SystemConfigurationService;

/**
 * @author kaushik
 *
 */
public class WorkspaceServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Services svc = Services.getInstance();
		SystemConfigurationService systemConfig = svc.getSystemConfigService();
		String action = req.getParameter("action") ;
		String id = req.getParameter("id");
		if("view".equalsIgnoreCase(action)) {
			if(id==null) {
				//get all workspaces
				List<Workspace> ws = systemConfig.getWorkspaces();
				Workspace w = new Workspace();
				w.setName("default workspace");
				w.setDescription("This is a demo workspace");
				req.setAttribute("ws", ws);
				req.getRequestDispatcher("workspace.jsp").forward(req, resp);
			}else {
				//get workspace by ID
				//return JSON of Workspace
				Workspace ws = systemConfig.getWorkspace(id);
			
			}
		}else if("edit".equalsIgnoreCase(action)){
			edit(req,resp);
			return;
		}
	}
	
	public void edit(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String name=null;
		String desc=null;
		if(req.getParameter("name")!=null)
			name = req.getParameter("name");
		if(req.getParameter("description")!=null)
			desc = req.getParameter("description");
		
		System.out.println("name: "+name);
		System.out.println("desc: "+desc);
		Services svc = Services.getInstance();
		SystemConfigurationService sysConfSvc = svc.getSystemConfigService();
		Workspace ws = sysConfSvc.getWorkspace(name);
		if(name!=null)
			ws.setDisplayName(name);
		if(desc!=null)
			ws.setDescription(desc);
		sysConfSvc.updateWorkspace(ws);
		resp.sendRedirect("workspaces?action=view");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
}

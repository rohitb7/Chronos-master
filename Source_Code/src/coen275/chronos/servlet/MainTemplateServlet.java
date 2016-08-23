/**
 * 
 */
package coen275.chronos.servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import coen275.chronos.project.Workspace;
import coen275.chronos.service.Services;
import coen275.chronos.service.SystemConfigurationService;

/**
 * @author ruchika
 *
 */
public class MainTemplateServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("mainTemplate.jsp").forward(req, resp);
			
		super.doGet(req, resp);
	}
}

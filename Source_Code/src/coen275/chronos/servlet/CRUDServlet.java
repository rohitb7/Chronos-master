package coen275.chronos.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CRUDServlet {

	 abstract void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	 abstract void editRecord(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	 abstract void viewOne(String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	 abstract void viewAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}

package com.auth.servlets;

import com.auth.helper.LoginHelper;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		    doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		    response.setContentType("text/html;charset=UTF-8");
		    PrintWriter out = response.getWriter();
		

		if (request.getParameter("register").equals("false")) {
			String user = request.getParameter("user");
			// String pass = request.getParameter("pass");
			String notNull="true";
			
			if(user.equals(null)||user.equals("")){
				notNull="false";
				out.println("<h3> Enter user name</h3>");
				RequestDispatcher rs = request.getRequestDispatcher("index.html");
				rs.include(request, response);
			}

			if (LoginHelper.validateUser(user)) {
				RequestDispatcher rs = request.getRequestDispatcher("welcome.jsp");
				rs.forward(request, response);
			} 
			if(!LoginHelper.validateUser(user)&&notNull.equals("true")) {
				out.println("<h2>Invalid user: "+ user+ " </h2>");
				RequestDispatcher rs = request.getRequestDispatcher("index.html");
				rs.include(request, response);
			}
		}
		
		if (request.getParameter("register").equals("true")) {
			RequestDispatcher rs = request.getRequestDispatcher("createUser.jsp");		
			rs.include(request, response);

		}
		
		if(request.getParameter("register").equals("create")){
			
			String user = request.getParameter("user");
			String pass = request.getParameter("pass");
			String notNull="true";
			
			if(user.equals(null)||user.equals("")||pass.equals(null)||pass.equals("")){
				notNull="false";
				out.println("<h3> Enter both username and password </h3>");
				RequestDispatcher rs = request.getRequestDispatcher("createUser.jsp");
				rs.include(request, response);
			}
			if(LoginHelper.validateUser(user)&&(!pass.equals(null)&&!pass.equals(""))){
				out.println("<h2> " + user+ " : Username already registered </h2>");
				RequestDispatcher rs = request.getRequestDispatcher("index.html");
				rs.include(request, response);
			
			} if(!LoginHelper.validateUser(user)&&notNull.equals("true")){
				
				LoginHelper.createUser(user,pass);
				out.println("<h2>User created: " + user+"</h2>");
				RequestDispatcher rs = request.getRequestDispatcher("index.html");
		        rs.include(request, response);
			}
		}
	}
}

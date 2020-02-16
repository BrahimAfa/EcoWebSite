package com.eco.Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eco.DAO.Userimpl;
import com.eco.Models.User;
import com.eco.Utils.Helpers;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
//@SuppressWarnings("unused")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Userimpl userImpl;
	public Login() {
		super();
		userImpl=new Userimpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Hello with JRebel").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		if(!Helpers.LoginValidation(email, pass)) {
			response.sendRedirect("Login.html");
			return;
		}
		try {
			User u = userImpl.getUser(email,pass);
			if(u==null) {
				response.sendRedirect("Login.html");
				return;
			}else{
				response.getWriter().append("hello"+u.getLastName());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

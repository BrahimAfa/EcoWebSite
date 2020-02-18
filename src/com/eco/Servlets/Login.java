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

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Userimpl userImpl;
	public Login() {
		super();
		userImpl=new Userimpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/Login.html").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		if(!Helpers.LoginValidation(email, pass)) {
			response.sendRedirect("Login");
			return;
		}
		try {
			User user = userImpl.getUser(email,pass);
			if(user==null) {
				getServletContext().getRequestDispatcher("/Login.html").forward(request, response);
				return;
			}else{
				//response.getWriter().append("hello Mr."+user.getLastName());
				request.getSession().setAttribute("user", user);
				response.sendRedirect("U/Store");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			log("Loging Servlete", e);
		}
	}

}

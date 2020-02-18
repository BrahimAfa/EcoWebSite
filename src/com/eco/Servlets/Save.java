package com.eco.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eco.DAO.IDAO;
import com.eco.Models.Product;
import com.eco.Models.User;
import com.eco.Utils.Helpers;

@WebServlet("/U/Save")
public class Save extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User user;
	public Save() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> products = (List<Product>)request.getSession().getAttribute("orders");
		if(products==null) {
			response.sendRedirect("U/Store");
			return;
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String cssTag = "<link rel='stylesheet' type='text/css' href='../css/bootstrap.min.css'>";
		user = (User)request.getSession().getAttribute("user");
		String title = String.format("This is you Order Mr. %s %s", user.getLastName(),user.getFirstName());
		out.println("<HTML><HEAD>"+cssTag+"</HEAD><BODY>");
		out.println("<Center><H1>" + title + "</H1></Center>");
		out.println("<div class='container'");
		out.println("<P>This is output from Store Servlete .");
		out.print("<table class='table table-hover'>");
		out.print("<thead class='thead-dark'>");
		out.print("<tr><th scope='col'>#</th><th scope='col'>Product Name</th><th scope='col'>Price</th></tr>");
		out.print("</thead>");
		out.print("<tbody>"+Helpers.getProductAsHtmlTableRow(products)+"</tbody></table>");
		out.println("</div></BODY></HTML>");
		out.close();	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().print("Hello froom SAEVE");
		
		doGet(request,response);
	}

}

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
import com.eco.DAO.Productimpl;
import com.eco.Models.Product;
import com.eco.Models.User;
import com.eco.Utils.Helpers;

@WebServlet("/U/Order")
public class Command extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User user;
	IDAO<Product> ProductDao;
	public Command() {
		super();

	}
	@Override
	public void init() throws ServletException {
		super.init();
		ProductDao= new Productimpl();


	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id;
		Product p;
		List<Product> products = (List<Product>)request.getSession().getAttribute("orders");
		if(products==null) products = new ArrayList<Product>();

		try {
			id= Integer.parseInt(request.getParameter("id")); 
			p= ProductDao.getOne(id);

		} catch (Exception e) {
			response.getWriter().print("Parametre Not Valid");
			return;
		}
		products.add(p);
		request.getSession().setAttribute("orders",products);
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
		out.print("<a href='Store'class='btn btn-primary btn-lg btn-block' style ='color: white;'>Add new Product</a>");
		out.print("<form action='../U/Save' method='POST'>");
		out.print("<Button type='Submit' class='btn btn-success btn-lg btn-block' style ='color: white;'>Save You're Order</a>");
		out.println("</form></div></BODY></HTML>");
		out.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}



}

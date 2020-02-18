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
import com.eco.DAO.Orderimpl;
import com.eco.Models.Order;
import com.eco.Models.Product;
import com.eco.Models.User;
import com.eco.Utils.Helpers;

@WebServlet("/U/Save")
public class Save extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User user;
	IDAO<Order> orderDao;
	public Save() {
		super();
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Product> ShoppingCartproducts = (List<Product>)request.getSession().getAttribute("orders");
		String cssTag = "<link rel='stylesheet' type='text/css' href='../css/bootstrap.min.css'>";
		List<Product> ordered_products = new ArrayList<Product>();
		orderDao = new Orderimpl();
		PrintWriter out = response.getWriter();
		if(ShoppingCartproducts==null) {
			ShoppingCartproducts = new ArrayList<Product>();
			request.getSession().setAttribute("orders",ShoppingCartproducts);
		}
		if (user==null) {
			user = (User)request.getSession().getAttribute("user");
		}
		user = (User)request.getSession().getAttribute("user");
		try {
			//getting orders For the current Logged in USER
			orderDao.getAll().stream().filter(O->O.getUser().getId()==user.getId()).forEach(x->ordered_products.add(x.getProduct()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String title = String.format("This is you Orders/Shopping Cart Mr. %s %s", user.getLastName(),user.getFirstName());
		String ShoppingCartTableBody= ShoppingCartproducts.size()>0 ?
				Helpers.getProductAsHtmlTableRow(ordered_products):
				"<tr><td colspan='3' style='text-align: center;'>There is no Products In your Shopping Cart</td></tr>";
		String OrderTableBody = ordered_products.size()>0 ?
						Helpers.getProductAsHtmlTableRow(ordered_products):
						"<td colspan='3' style='text-align: center;'>You didnt Make any Orders</td>";
				
						response.setContentType("text/html");
		out.println("<HTML><HEAD>"+cssTag+"</HEAD><BODY>");
		out.println("<Center><H1>" + title + "</H1></Center>");
		out.println("<div class='container'");

		
		out.print("<br/>");
		out.print("<br/>");
		out.print("<br/>");
		//SEPERATOR
		
		out.print("<table class='table table-hover table-striped table-dark'>");
		out.print("<thead class='thead-dark'>");
		out.print("<tr><th scope='col'>#</th><th scope='col'>Product Name</th><th scope='col'>Price</th></tr>");
		out.print("</thead>");
		out.print("<tbody>");
		out.print("<td colspan='3' style='text-align: center;'><strong>Your Orders</strong></td>");
		out.print(OrderTableBody);
		out.print("</tbody></table>");
		
		
		out.print("<table class='table'>");
		out.print("<thead class='thead-light'>");
		out.print("<tr><th scope='col'>#</th><th scope='col'>Product Name</th><th scope='col'>Price</th></tr>");
		out.print("</thead>");
		out.print("<tbody>");
		out.print("<td colspan='3' style='text-align: center;'><strong>Your Shopping Cart</strong></td>");
		out.print(ShoppingCartTableBody);
		out.print("</tbody></table>");
		
		out.println("</div></BODY></HTML>");
		out.close();	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		orderDao = new Orderimpl();
		response.getWriter().print("Hello froom SAEVE");
		user = (User)request.getSession().getAttribute("user");
		List<Product> products = (List<Product>)request.getSession().getAttribute("orders");
		for (Product p : products) {
				System.out.println("°++++===>"+products.size());
			try {
				orderDao.Insert(new Order(-1, user, p));
			} catch (SQLException e) {
				e.printStackTrace();
				log("Error while Inserting",e);
			}
		}
		request.getSession().setAttribute("orders",new ArrayList<Product>());
		
		doGet(request,response);
	}

}

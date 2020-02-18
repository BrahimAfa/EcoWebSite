package com.eco.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eco.DAO.IDAO;
import com.eco.DAO.Productimpl;
import com.eco.Models.Product;
import com.eco.Models.User;

@WebServlet("/U/Store")
public class Store extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User user;
	IDAO<Product> ProductDao;
    public Store() {
        super();
    }
    @Override
    public void init() throws ServletException {
    	super.init();
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		ProductDao = new Productimpl();
		PrintWriter out = response.getWriter();
		String cssTag = "<link rel='stylesheet' type='text/css' href='../css/bootstrap.min.css'>";
		User user = (User)request.getSession().getAttribute("user");
		String title = String.format("Welcome To Store Mr. %s %s", user.getLastName(),user.getFirstName());
		 out.println("<HTML><HEAD>"+cssTag+"</HEAD><BODY>");
		 out.println("<Center><H1>" + title + "</H1></Center>");
		 out.println("<div class='container'");
		 out.println("<P>This is output from Store Servlete .");
		 out.print("<table class='table table-hover'>");
		 out.print("<thead class='thead-dark'>");
		 out.print("<tr><th scope='col'>#</th><th scope='col'>Product Name</th><th scope='col'>Price</th><th scope='col'></th></tr>");
		 out.print("</thead>");
		 try {
			out.print("<tbody>"+getListOfProducts()+"</tbody></table>");
		} catch (SQLException e) {
			e.printStackTrace();
			log("Failed to load Products",e);
		}
		 out.println("</div></BODY></HTML>");
		 out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private String getListOfProducts() throws SQLException {
		StringBuilder str = new StringBuilder();
		ProductDao.getAll().forEach(p-> 
			str.append(String.format(
				"<tr><th scope='row'>%d</th> <td>%s</td> <td>%d</td><td><a href='Order?id=%d' class='badge badge-dark' >Buy</a></td></tr>", 
				p.getId(),p.getName(),p.getPrice(),p.getId()))
				);
		return str.toString();
	}

}

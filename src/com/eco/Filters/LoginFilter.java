package com.eco.Filters;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eco.Models.User;
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain next) throws IOException, ServletException {
		System.out.println("Hellooo from FIlter");
		User user = (User)((HttpServletRequest) req).getSession().getAttribute("user");
		if(user==null) {
			((HttpServletResponse) res).sendRedirect("/EcoWebSite/Login");
			return;
		}
		next.doFilter(req,res);
	}
	

}

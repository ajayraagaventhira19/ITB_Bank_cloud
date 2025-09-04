package com.ITB.bank;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/logout")
public class LogoutServlet extends HttpFilter implements Filter {
     
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		HttpSession s=req.getSession();
		if(s.getAttribute("username")!=null) {
			s.removeAttribute("username");
			s.invalidate();
			 res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
	         res.setHeader("Pragma", "no-cache"); 
	         res.setDateHeader("Expires", 0);
			res.sendRedirect("home.jsp");
			return;
		}
		chain.doFilter(request, response);
	}


}

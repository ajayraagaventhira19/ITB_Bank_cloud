package com.ITB.bank;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		ITBdao user=new ITBdao();
		Account a=user.getUser(username, password);
		if(a!=null) {
			HttpSession session=request.getSession(false);
			session.setAttribute("username",a);
			response.sendRedirect("SuccessHome.jsp");
		}else {
			response.setContentType("text/html");
		    response.getWriter().println("<script type='text/javascript'>");
		    response.getWriter().println("alert('Login failed! Please try again.');");
		    response.getWriter().println("window.location.href='login.jsp';"); 
		    response.getWriter().println("</script>");
		}
		
	}

}

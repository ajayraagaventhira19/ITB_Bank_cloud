package com.ITB.bank;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fullname=request.getParameter("name");
		String dob=request.getParameter("dob");
		String gender=request.getParameter("gender");
		String marital=request.getParameter("marital");
		String accounttype=request.getParameter("accounttype");
		String state=request.getParameter("state");
		String city=request.getParameter("city");
		int pincode=Integer.parseInt(request.getParameter("pincode"));
		double amount=Double.parseDouble(request.getParameter("amount"));
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		ITBdao new_user=new ITBdao();
		Account a=new_user.addUser(fullname, dob, gender, marital, accounttype, state,
				city, pincode, amount, username, password);
		if(a!=null) {
			HttpSession session=request.getSession();
			session.setAttribute("username", a);
			response.sendRedirect("SuccessHome.jsp");
		}else {
			response.setContentType("text/html");
		    response.getWriter().println("<script type='text/javascript'>");
		    response.getWriter().println("alert('Signup failed! Please try again.');");
		    response.getWriter().println("window.location.href='signup.jsp';"); 
		    response.getWriter().println("</script>");
		}
	}

}

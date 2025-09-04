package com.ITB.bank;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ViewBalance")
public class ViewBalance extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s=request.getSession(false);
		ITBdao db=new ITBdao();
		Account a=(Account)s.getAttribute("username");
		Account user=db.get_data(a.getUsername());
		s.removeAttribute("username");
		s.setAttribute("username", user);
		response.sendRedirect("viewbalance.jsp");
	}
}

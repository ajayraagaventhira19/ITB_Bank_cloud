package com.ITB.bank;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DepositMoney")
public class DepositMoney extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s=request.getSession(false);
		try {
		Account user=(Account)s.getAttribute("username");
		double amount=Double.parseDouble(request.getParameter("amount"));
		
		ITBdao db=new ITBdao();
		
			if(user!=null) {
					if(db.deposit_money(user, amount)) {
						response.sendRedirect("depositedSuccessfull.jsp");
					}else {
						response.sendRedirect("error2.jsp");
					}
			}else {
				response.sendRedirect("error2.jsp");
			}
		}catch(Exception e) {
			response.sendRedirect("error2.jsp");
		}
	}

}

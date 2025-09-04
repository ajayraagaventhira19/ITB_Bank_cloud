package com.ITB.bank;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/WithdrawMoney")
public class WithdrawMoney extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s=request.getSession(false);
		try {
			Account a=(Account)s.getAttribute("username");
			double amount=Double.parseDouble(request.getParameter("amount"));
			String pass=request.getParameter("password");
			ITBdao db=new ITBdao();
			
				if(a.getAmount()>=amount && pass.equals(a.getPassword())) {
						if(db.withdraw_money(a, amount)) {
							Account user=db.get_data(a.getUsername());
								s.removeAttribute("username");
								s.setAttribute("username", user);
							response.sendRedirect("withdrawnSuccessfull.jsp");
						}else {
							response.sendRedirect("error3.jsp");
						}
				}else {
					response.sendRedirect("error3.jsp");
				}
			}catch(Exception e) {
				response.sendRedirect("error3.jsp");
			}
	}

}

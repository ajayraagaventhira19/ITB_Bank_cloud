package com.ITB.bank;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SendMoney")
public class SendMoney extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s=request.getSession(false);
		try {
		Account sender=(Account)s.getAttribute("username");
		String sendername=sender.getUsername();
		double amount=Double.parseDouble(request.getParameter("amount"));
		
		String receivername=request.getParameter("username");
		ITBdao db=new ITBdao();
		Account receiver=db.get_data(receivername);
			if(receiver!=null) {
				if(sender.getAmount()>amount && !receivername.equals(sendername)) {
					if(db.update_balance(sender, receiver, amount)) {
						Account user=db.get_data(sender.getUsername());
						s.removeAttribute("username");
						s.setAttribute("username", user);
						response.sendRedirect("paymentdone.jsp");
					}else {
						response.sendRedirect("error.jsp");
					}
				}else {
					response.sendRedirect("error.jsp");
				}
			}else {
				response.sendRedirect("error.jsp");
			}
		}catch(Exception e) {
			response.sendRedirect("error.jsp");
		}
	}

}

package com.ITB.bank;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/TransactionHistory")
public class TranactionHistory extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s=request.getSession(false);
		ITBdao db=new ITBdao();
		Account a=(Account)s.getAttribute("username");
		List<Transaction> transaction=db.getTransaction_history(a.getUsername());
		s.setAttribute("transaction", transaction);
		request.getRequestDispatcher("transactionhistory.jsp").forward(request, response);
	}

}

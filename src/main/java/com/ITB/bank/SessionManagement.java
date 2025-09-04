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

@WebFilter({"/SuccessHome.jsp","/user.jsp","/ViewBalance","/SendMoney.jsp" ,"/SendMoney","/paymentdone.jsp","/Deposit.jsp","/DepositMoney","/depositedSuccessfull.jsp","/withdraw.jsp","/WithdrawMoney","/withdrawnSuccessfull.jsp","/TransactionHistory","/transactionhistory.jsp"})
public class SessionManagement extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		HttpSession session=req.getSession(false);
		
		if(session==null|| session.getAttribute("username") == null) {
			res.sendRedirect("login.jsp");
		}else {
		chain.doFilter(request, response);
		}
	}


}

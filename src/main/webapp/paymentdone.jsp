<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.ITB.bank.Account" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>IBT</title>
    <link rel="icon" type="image/png" href="logo.png">
    <link rel="stylesheet" href="paymentdone.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Libertinus+Sans:ital,wght@0,400;0,700;1,400&family=Libre+Caslon+Text:ital,wght@0,400;0,700;1,400&family=Saira+Condensed:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
</head>
<meta charset="UTF-8">
<title>ITB</title>
<body>
<nav class="navbar">
        <div class="logo">
            <img src="logo.png" alt="Logo" width="50" height="100">
            <h2><a href="#" class="tab2">ITB</a></h2>
        </div>
        <h1><a href="#" class="tab1">Indian Tesla Bank</a></h1>
        <ul class="list">
        	<%
           	if(session==null||session.getAttribute("username")==null){
           	%>
            	<li><a href="home.jsp" class="tab">Home</a></li>
           	<% 
           	}else{
           	%>
           		 <li><a href="SuccessHome.jsp" class="tab">Home</a></li>
            <%
           	}
           	%>
            <li><a href="About.jsp" class="tab">About Us</a></li>
            <li><a href="contact.jsp" class="tab">Contact</a></li>
            <li><a href="insurance.jsp" class="tab">Insurance</a></li>
           	<%
           	if(session==null||session.getAttribute("username")==null){
           	%>
           		<li><a href="login.jsp" class="log">Login</a></li>
            	<li><a href="signup.jsp" class="log">Sign Up</a></li>
           	<% 
           	}else{
           		Account user = (Account) session.getAttribute("username");
           	%>
           		<div><span class="material-symbols-outlined">account_circle</span>
           		<li class="un"><%= user.getUsername() %> </li>
           		</div>
           		<li><a href="logout" class="log">Logout</a></li>
            <%
           	}
           	%>
           	
        </ul>
    </nav>
	
	<div class="payment-container">
        <span class="material-symbols-outlined price_check">price_check</span>
        <h2>  Payment Done</h2><br>
        <div class="back-btn">
        <a href="SuccessHome.jsp" class="chevron_backward">
            <span class="material-symbols-outlined">chevron_left</span> Back
        </a>
    </div>
    </div>
	
	<footer>
    <div class="footer-container">
      <p class="foot">&copy; 2025 Indian Tesla Bank (IBT). All Rights Reserved.</p>
      <p class="contact">Contact: info@ibtbank.com | Phone: +91 12345 67890</p>
      <p class="follow">
        Follow us:
        <a href="#">Facebook</a> |
        <a href="#">Twitter</a> |
        <a href="#">Instagram</a>
      </p>
    </div>
</footer>
</body>
</html>
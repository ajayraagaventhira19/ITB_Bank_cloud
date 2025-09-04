<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="com.ITB.bank.Account" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ITB</title>
    <link rel="icon" type="image/png" href="logo.png">
    <link rel="stylesheet" href="aboutcss.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Libertinus+Sans:ital,wght@0,400;0,700;1,400&family=Libre+Caslon+Text:ital,wght@0,400;0,700;1,400&family=Saira+Condensed:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
</head>
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
           		<a href="user.jsp" style="text-decoration:none;"><div><span class="material-symbols-outlined">account_circle</span>
           		<li class="un"><%= user.getUsername() %> </li>
           		</div></a>
           		<li><a href="logout" class="log">Logout</a></li>
            <%
           	}
           	%>
           	
        </ul>
    </nav>
      <div class="about-container">
    <h1>About Us</h1>
    <p>
      Welcome to <strong>Indian Tesla Bank (IBT)</strong>, one of the most trusted and customer-friendly financial institutions. 
      Since our founding, we have been committed to delivering secure, innovative, and reliable banking solutions that empower individuals, businesses, and communities.
    </p>
    <p>
      At IBT, we believe that banking is more than just managing money—it’s about building trust, supporting dreams, and shaping a brighter financial future.
    </p>

    <h2>Our Mission</h2>
    <p>To provide <strong>safe, transparent, and technology-driven banking services</strong> that make financial management easy and accessible for everyone.</p>

    <h2>Our Vision</h2>
    <p>To become the most <strong>innovative and customer-centric bank</strong>, setting new standards in financial excellence and digital banking across India and beyond.</p>

    <h2>What We Offer</h2>
    <ul>
      <li><strong>Personal Banking</strong> – Savings accounts, current accounts, deposits, and cards.</li>
      <li><strong>Loans & Credit</strong> – Home loans, education loans, business loans, and more.</li>
      <li><strong>Insurance & Investments</strong> – Protecting your future while growing your wealth.</li>
      <li><strong>Digital Banking</strong> – Easy, fast, and secure access through net banking & mobile apps.</li>
    </ul>

    <h2>Why Choose IBT?</h2>
    <ul>
      <li>Trusted by thousands of customers.</li>
      <li>Strong focus on <strong>security, transparency, and customer care</strong>.</li>
      <li>24/7 digital access with personalized support.</li>
      <li>Nationwide network of branches & ATMs.</li>
    </ul>

    <p>
      📌 At <strong>Indian Tesla Bank</strong>, your growth is our priority. 
      Together, let’s build a stronger, smarter financial future.
    </p>
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
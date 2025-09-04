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
    <link rel="stylesheet" href="insurance.css">
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
           		<a href="user.jsp" method="post" class="user_data"><div><span class="material-symbols-outlined">account_circle</span>
           		<li class="un"><%= user.getUsername() %> </li>
           		</div></a>
           		<li><a href="logout" class="log">Logout</a></li>
           		
            <%
           	}
           	%>
           	
        </ul>
    </nav>
      <div class="insurance-container">
    <h1>Insurance Services</h1>
    <p>
      At <strong>Indian Tesla Bank (IBT)</strong>, we understand that life is full of uncertainties.  
      That’s why we provide a wide range of <strong>insurance solutions</strong> designed to protect you, your loved ones, and your future.
    </p>

    <h2>Types of Insurance We Offer</h2>
    <ul>
      <li><strong>Life Insurance</strong> – Secure your family’s financial future with flexible plans.</li>
      <li><strong>Health Insurance</strong> – Comprehensive coverage for medical emergencies & hospital expenses.</li>
      <li><strong>Motor Insurance</strong> – Protection for your car, bike, or commercial vehicles against accidents & theft.</li>
      <li><strong>Travel Insurance</strong> – Peace of mind while traveling, covering medical & unexpected events abroad.</li>
      <li><strong>Home Insurance</strong> – Safeguard your home and valuable assets from damage or loss.</li>
    </ul>

    <h2>Why Choose IBT Insurance?</h2>
    <ul>
      <li>Affordable premiums with maximum coverage.</li>
      <li>Hassle-free claims process with quick settlements.</li>
      <li>24/7 customer support for guidance & assistance.</li>
      <li>Trusted by thousands of customers across India.</li>
    </ul>

    <p>
      🛡️ With <strong>IBT Insurance</strong>, you don’t just protect your assets—you secure peace of mind.  
      Let us be your partner in building a safer tomorrow.
    </p>

    <h2>Get Started Today</h2>
    <p>
      Interested in our insurance services?  
      <a href="contact.jsp">📩 Contact us</a> today or visit your nearest IBT branch to learn more.
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
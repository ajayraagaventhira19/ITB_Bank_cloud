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
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css2?family=Libertinus+Sans:ital,wght@0,400;0,700;1,400&family=Libre+Caslon+Text:ital,wght@0,400;0,700;1,400&family=Saira+Condensed:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="SendMoney.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
</head>
<body>
   <nav class="navbar">
        <div class="logo">
            <img src="logo.png" alt="Logo" width="50" height="100">
            <h2><a href="#" class="tab2">ITB</a></h2>
        </div>
        <h1><a href="#" class="tab1">Indian Tesla Bank</a></h1>
        <ul class="list">
            <li><a href="SuccessHome.jsp" class="tab">Home</a></li>
            <li><a href="About.jsp" class="tab">About Us</a></li>
            <li><a href="contact.jsp" class="tab">Contact</a></li>
            <li><a href="insurance.jsp" class="tab">Insurance</a></li>
           <%
           		Account user = (Account) session.getAttribute("username");
           	%>
           		<a href="user.jsp" style="text-decoration:none;"><div><span class="material-symbols-outlined">account_circle</span>
           		<li class="un"><%= user.getUsername() %> </li>
           		</div></a>
           		<li><a href="logout" class="log">Logout</a></li>
           	
        </ul>
    </nav>

  <div class="send-container">
    <h1>Deposit Money</h1>
    <form id="deposit" action="DepositMoney" method="POST">
      <label >Enter Amount To Deposit:</label>
      <input type="number" id="amount" name="amount" class="box" required>
      <button type="submit">Deposit</button>
    </form>
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

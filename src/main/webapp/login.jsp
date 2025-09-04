<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="login.css">
  <link rel="icon" type="image/png" href="logo.png">
  <link href="https://fonts.googleapis.com/css2?family=Libertinus+Sans:ital,wght@0,400;0,700;1,400&family=Libre+Caslon+Text:ital,wght@0,400;0,700;1,400&family=Saira+Condensed:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
  <title>ITB</title>
</head>
<body>
  <nav class="navbar">
    <div class="logo">
      <img src="logo.png" alt="Logo" width="50" height="100">
      <h2><a href="#" class="tab2">ITB</a></h2>
    </div>
    <h1><a href="#" class="tab1">Indian Tesla Bank</a></h1>
    <ul class="list">
      <li><a href="home.jsp" class="tab">Home</a></li>
      <li><a href="About.jsp" class="tab">About Us</a></li>
      <li><a href="Contact.jsp" class="tab">Contact</a></li>
      <li><a href="insurance.jsp" class="tab">Insurance</a></li>
    </ul>
  </nav>

  <div class="login-container">
    <h1>Login</h1>
    <form id="loginForm" action="login" method="POST">
      <label for="username">Username:</label>
      <input type="text" id="username" name="username" required>
      <br>
      <label for="password">Password:</label>
      <input type="password" id="password" name="password" required>
      <br>
      <button type="submit">Login</button>
    </form>
    <p>Don't have an account? <a href="signup.html">Sign Up</a></p>
  </div>

  <script>
    document.getElementById("loginForm").addEventListener("submit", function(event) {
      let username = document.getElementById("username").value.trim();
      let password = document.getElementById("password").value.trim();
      if (username.length < 3) {
        alert("Username must be at least 3 characters long.");
        event.preventDefault();
        return;
      }

      if (password.length < 6) {
        alert("Password must be at least 6 characters long.");
        event.preventDefault();
        return;
      }
    });
  </script>
</body>
</html>

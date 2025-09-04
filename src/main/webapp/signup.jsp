<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="signup.css">
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
      <li><a href="contact.jsp" class="tab">Contact</a></li>
      <li><a href="insurance.jsp" class="tab">Insurance</a></li>
    </ul>
  </nav>

  <div class="signup-container">
    <h1>Signup</h1>
    <form id="signupForm" action="signup" method="POST">
      <label for="name">Full Name:</label>
      <input type="text" id="name" name="name" class="box" required>
      <br>

      <label for="dob">Date Of Birth:</label>
      <input type="date" id="dob" name="dob" class="box" required>
      <br>

        <label>Gender:</label>
        <div class="radio-group">
        <input type="radio" id="male" name="gender" value="M" required>
        <label for="male">Male</label>

        <input type="radio" id="female" name="gender" value="F">
        <label for="female">Female</label>

        <input type="radio" id="other" name="gender" value="O">
        <label for="other">Other</label>
        </div>

        <label>Marital Status:</label>
        <div class="radio-group">
        <input type="radio" id="yes" name="marital" value="Y" required>
        <label for="yes">Yes</label>

        <input type="radio" id="no" name="marital" value="N">
        <label for="no">No</label>
        </div>

        <label>Account Type:</label>
        <div class="radio-group">
        <input type="radio" id="saving" name="accounttype" value="Savings" required>
        <label for="saving">Savings Account</label>

        <input type="radio" id="currentaccount" name="accounttype" value="Current">
        <label for="currentaccount">Current Account</label>
        </div>


      <label for="state">State:</label>
      <select id="state" name="state" style="width:190px; height: 30px;" required>
        <option value="">--Select State--</option>
        <option value="TN">Tamil Nadu</option>
        <option value="KA">Karnataka</option>
        <option value="KL">Kerala</option>
        <option value="AP">Andhra Pradesh</option>
        <option value="TS">Telangana</option>
      </select>
      <br>

      <label for="city">City:</label>
      <input class="box" type="text" id="city" name="city" required>
      <br>

      <label for="pincode">Pincode:</label>
      <input type="number" id="pincode" name="pincode" class="box" required>
      <br>

      <label for="amount">Initial Amount:</label>
      <input type="number" id="amount" name="amount" class="box" required>
      <br>

      <label for="username">Set Username:</label>
      <input type="text" id="username" name="username" class="box" required>
      <br>

      <label for="password">Set Password:</label>
      <input type="password" id="password" name="password" class="box" required>
      <br>

      <div class="terms">
        <input type="checkbox" id="terms" name="terms" required>
        <label for="terms">I agree to the Terms & Conditions</label>
      </div>

      <button type="submit">Signup</button>
    </form>
  </div>

  <script>
    document.getElementById("signupForm").addEventListener("submit", function(event) {
      let name = document.getElementById("name").value.trim();
      let dob = document.getElementById("dob").value.trim();
      let city = document.getElementById("city").value.trim();
      let pincode = document.getElementById("pincode").value;
      let amount = parseInt(document.getElementById("amount").value);
      let username = document.getElementById("username").value.trim();
      let password = document.getElementById("password").value.trim();
      let terms = document.getElementById("terms").checked;

      if (!name || !dob || !city || !pincode || !amount || !username || !password) {
        alert("⚠️ Please fill all the fields.");
        event.preventDefault();
        return;
      }
      if (isNaN(amount) || amount < 500) {
        alert("⚠️ Initial amount must be at least ₹500.");
        event.preventDefault();
        return;
      }

      if (username.length < 3) {
        alert("⚠️ Username must be at least 3 characters long.");
        event.preventDefault();
        return;
      }

      if (password.length < 6) {
        alert("⚠️ Password must be at least 6 characters long.");
        event.preventDefault();
        return;
      }

      if (!terms) {
        alert("⚠️ You must agree to the Terms & Conditions.");
        event.preventDefault();
        return;
      }

    });
  </script>
</body>
</html>

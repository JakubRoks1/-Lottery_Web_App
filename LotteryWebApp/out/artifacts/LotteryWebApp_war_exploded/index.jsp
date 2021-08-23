<%--
  Created by IntelliJ IDEA.
  User: johnmace
  Date: 21/10/2020
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Home</title>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
  </head>
  <body>

  <h1>Home Page</h1>

  <form action="CreateAccount" method="post">
      <label for="firstname">First name:</label><br>
      <input type="text" id="firstname" name="firstname"><br>
      <label for="lastname">Last name:</label><br>
      <input type="text" id="lastname" name="lastname"><br>
      <label for="username">Username:</label><br>
      <input type="text" id="username" name="username"><br>
      <label for="phone">Phone:</label><br>
      <input type="text" id="phone" name="phone"  value="44-0191-1234567"><br>
      <label for="email">Email:</label><br>
      <input type="text" id="email" name="email"  value="joebloggs@email.com"><br>
      <label for="password">Password:</label><br>
      <input type="password" id="password" name="password"  value="Password123"><br>
      <input type="submit" id="submit" value="Submit">
  </form>

  <div>
      <a href="login.jsp">UserLogin</a>
  </div>

  <script type="text/javascript">
      $('#submit').on('click',function(e, options){
          options = options || {};
          if ( !options.isFormValid ) {
              e.preventDefault();
              let isEmailValid = false;
              let isPasswordValid = false;
              let isPhoneNumberValid = false;
              console.log("Test");
              const email = $("#email").val();
              console.log(email);
              const password = $("#password").val();
              console.log(password);
              const phonenumber = $("#phone").val();
              console.log(phonenumber);
              if (validateEmail(email)) {
                  isEmailValid = true
              }

              if (validatePhoneNumber(phonenumber)) {
                  isPhoneNumberValid = true;
                  console.log("phone number valid");
              }

              if (validatePassword(password)) {
                  isPasswordValid = true;
                  console.log("password valid");
              }

              if (isPasswordValid && isPhoneNumberValid && isEmailValid) {
                  $(this).trigger('click',{ 'isFormValid': true });
              } else {
                  alert("input Values Incorrect")
              }
          }







      });

      function validateEmail(email) {
          const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
          return re.test(String(email).toLowerCase());
      }

      function validatePhoneNumber(phonenumber) {
          const re = /\d{2}-\d{4}-\d{7}/;
          return re.test(String(phonenumber).toLowerCase());
      }

      function validatePassword(password) {
          let isLengthValid = false;
          let containsUpperCase = false;
          let containsLowerCase = false;
          let containsDigit = false;
          isLengthValid = password.length >= 8 && password.length <= 15;
          if(password.match(/[a-z]/g)) {
              containsLowerCase = true;
          }

          if(password.match(/[A-Z]/g)) {
              containsUpperCase = true;
          }

          if(password.match(/[0-9]/g)) {
              containsDigit = true;
          }

          return containsLowerCase && containsUpperCase && containsDigit && isLengthValid;



      }

  </script>

  </body>
</html>

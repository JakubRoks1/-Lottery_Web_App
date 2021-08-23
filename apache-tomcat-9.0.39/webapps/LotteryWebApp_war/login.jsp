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
    <title>UserLogin</title>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
  </head>
  <body>

  <h1>UserLogin Page</h1>
  <p><%= request.getAttribute("message") %></p>


  <form action="UserLogin" method="post">
      <label for="username">Username:</label><br>
      <input type="text" id="username" name="username"><br>
      <label for="password">Password:</label><br>
      <input type="text" id="password" name="password"  value="Password123"><br>
      <input type="hidden" name="failedLoginCounter" value="<%=request.getAttribute("failedLoginCounter")==null?"0":request.getAttribute("failedLoginCounter") %>">
<%--      <% if ("3".equals(request.getAttribute("failedLoginCounter"))) {%>--%>
      <% if (request.getAttribute("failedLoginCounter") != null && (Integer)request.getAttribute("failedLoginCounter") ==3) {%>
      <input type="submit" id="submit" value="Submit" disabled>
      <% } else { %>
      <input type="submit" id="submit" value="Submit">
      <% } %>
  </form>

  <script type="text/javascript">
      $('#submit').on('click',function(e, options){
          options = options || {};
          if ( !options.isFormValid ) {
              e.preventDefault();
              const password = $("#password").val();

              if (validatePassword(password)) {
                  $(this).trigger('click',{ 'isFormValid': true });
              } else {
                  alert("input Values Incorrect")
              }
          }
      });

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

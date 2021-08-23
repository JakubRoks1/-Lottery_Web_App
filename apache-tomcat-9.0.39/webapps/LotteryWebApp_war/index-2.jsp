<%--
  Created by IntelliJ IDEA.
  User:
  Date: 21/10/2020
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>

<h1>Home Page</h1>

<form action="CreateAccount" method="post">
    <label for="firstname">First name:</label><br>
    <input type="text" id="firstname" name="firstname"><br>
    <label for="lastname">Last name:</label><br>
    <input type="text" id="lastname" name="lastname"><br>
    <label for="email">Email:</label><br>
    <input type="text" id="email" name="email"><br>
    <label for="phone number">Phone number:</label><br>
    <input type="text" id="phone number" name="phonenumber"><br>
    <label for="username">Username:</label><br>
    <input type="text" id="username" name="username"><br><br>
    <label for="password">Password:</label><br>
    <input type="text" id="password" name="password"><br><br>
    <input type="hidden" id="phone" name="phone"  value="44-0191-1234567">
    <input type="hidden" id="/email" name="email"  value="joebloggs@email.com">
    <input type="hidden" id="/password" name="password"  value="Password123">
    <input type="submit" value="Register">
</form>

<form action="Login" method="post">
    <label for="Username">Username:</label><br>
    <input type="text" id="usern" name="Username"><br>
    <label for="Password">Password:</label><br>
    <input type="text" id="pass" name="Password"><br>
    <input type="submit" value="Login">
</form>

var email = document.registration.email;

<script>
    function validEmail() {
        var firstname = document.index.firstname.name;
        if (firstname== "") {
            alert("Please enter your first name")
            document.index.firstname.focus();
            return false;

        }
    }

</script>





</body>
</html>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: johnmace
  Date: 21/10/2020
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
</head>
<body>
<h1>User Account</h1>

<p><%= request.getAttribute("message") %></p>
<p>firstname: ${firstname}</p>
<p>lastname: ${lastname}</p>
<p>email: ${email}</p>
<p>username: ${username}</p>

<h1>Please ,enter 6 numbers between 0 and 60 inclusive</h1>

<form action="AddUserNumbers" method="post">
    <label for="number1">Number 1:</label><br>
    <input type="number" id="number1" name="number1" min="0" max="60"><br>
    <label for="number2">Number 2:</label><br>
    <input type="number" id="number2" name="number2" min="0" max="60"><br>
    <label for="number3">Number 3:</label><br>
    <input type="number" id="number3" name="number3" min="0" max="60"><br>
    <label for="number4">Number 4:</label><br>
    <input type="number" id="number4" name="number4" min="0" max="60"><br>
    <label for="number5">Number 5:</label><br>
    <input type="number" id="number5" name="number5" min="0" max="60"><br>
    <label for="number6">Number 6:</label><br>
    <input type="number" id="number6" name="number6" min="0" max="60"><br>
    <button type="button" id="randomnumbers" >Generate random numbers</button>
    <input type="submit" id="submit" value="Submit">
</form>

<form action="GetUserNumbers" method="post">
    <input type="submit" id="submitUserNumbers" value="Get Draws"/>
</form>

<form action="CheckUserNumbers" method="post">
    <input type="submit" id="submitCheckUserNumbers" value="Check Numbers"/>
</form>

<%--pass: ${password}--%>
<%--message: ${message}--%>

<% List<String> d = (ArrayList)request.getAttribute("decodedNumbers"); %>
<% if (d != null) { %>
<% for(int i = 0; i < d.size(); i+=1) { %>

<p><%=d.get(i)%></p>

<% } %>
<% } %>

<%--<form action="GetAllUsers" method="post">--%>
<%--    <input type="submit" value="Get All Data">--%>
<%--</form>--%>

<form action="AdminHome" method="post">
    <input type="submit" value="admin panel">
</form>

<a href="index.jsp">Home Page</a>

<script type="text/javascript">
    $('#randomnumbers').on('click',function(e){
        console.log("clicked")
        $("#number1").val(randomInt(0,60));
        $("#number2").val(randomInt(0,60));
        $("#number3").val(randomInt(0,60));
        $("#number4").val(randomInt(0,60));
        $("#number5").val(randomInt(0,60));
        $("#number6").val(randomInt(0,60));


    });
    function randomInt(min, max) {
        return min + Math.floor((max - min) * Math.random());
    }

</script>

</body>
</html>

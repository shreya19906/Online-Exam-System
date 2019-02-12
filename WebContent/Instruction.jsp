<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Instructions</title>

</head>
<form action="exam.jsp">
<h2>Multiple Choice Question (MCQ) instructions:</h2>
<body bgcolor="#E6E6FA">
<p>Please read the following instructions carefully before taking the test: </p>

<ol>
<li>All the questions are of MCQ type.</li>
<li>Time duration for the test will be 10 minutes.</li>
<li>There are 10 multiple choice questions only.</li>
<li>For every correct answer +4 marks will be given and for any wrong answer 1 mark will be deducted.</li>
<li>You can edit your opted option as many times you want.</li>
</ol>
<h1><center>All the Best!!</center></h1>
<%
session.setAttribute("QUESTION_LIST",request.getAttribute("QUESTION_LIST"));
%>
<a href="exam.jsp">START TEST</a>
</form>
</body>

</html>
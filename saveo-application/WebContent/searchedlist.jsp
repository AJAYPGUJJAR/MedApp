<%@page import="com.saveo.model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Searched Result</h1>
	<br>
	<br>
	
	<% out.println(session.getAttribute("al")); %>
</body>
</html>
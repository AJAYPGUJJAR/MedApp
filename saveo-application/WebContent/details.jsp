<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Medicine Details</h1>
	<label>Name : </label>				<% out.print(session.getAttribute("c_name")); %>
 	<br><br>
	<label>Batch Number : </label> 		<% out.print(session.getAttribute("c_batch_no")); %>
	<br><br>
	<label>Expiry Date : </label>		<% out.print(session.getAttribute("d_expiry_date")); %>
	<br><br>
	<label>Balance Quantity : </label>	<% out.print(session.getAttribute("n_balance_qty")); %>
	<br><br>
	<label>Packaging : </label>  		<% out.print(session.getAttribute("c_packaging")); %>
	<br><br>
	<label>Unique code : </label>  		<% out.print(session.getAttribute("c_unique_code")); %>
	<br><br>
	<label>Schemes : </label>  			<% out.print(session.getAttribute("c_schemes")); %>
	<br><br>
	<label>MRP : </label>  				<% out.print(session.getAttribute("n_mrp")); %>
	<br><br>
	<label>Manufacturer : </label>  	<% out.print(session.getAttribute("c_manufacturer")); %>
	<br><br>
	<label>HSN Code : </label>  		<% out.print(session.getAttribute("hsn_code")); %>

</body>
</html>
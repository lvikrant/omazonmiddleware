<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link rel="stylesheet" type="text/css" href="commonstyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Omazon Error Page</title>
</head>
<body>
<table  border="0" width="75%" align="center">
<tr><td align="center"><h3><%=request.getParameter("errorMsg")%></h3></td></tr>
</table>
<h3 align="center"><a href="index.jsp">Home</a></h3>
</body>
</html>

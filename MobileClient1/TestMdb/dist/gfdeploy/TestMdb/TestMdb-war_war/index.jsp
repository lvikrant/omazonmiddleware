<%-- 
    Document   : index
    Created on : 11 Feb, 2013, 2:49:52 AM
    Author     : dell
--%>

<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Omazon Home</title>
    </head>
    <body>
        <form name="f1" action="Tracking.jsp" method=post >
            <table align="center">
                <tr align="center"><td><h1>Omazon</h1></td></tr>
                <tr align="center"><td><h3><a href="addProduct.html">Add New Product</a></h3></td></tr>
                <tr align="center"><td><h3><a href="OrderPage.jsp">Buy It Now</a></h3></td></tr>
                <tr align="center"><td><h3><a href="DeliveryForm.jsp">Update Order Status</a></h3></td></tr>
                <tr align="center"><td><h2>Track Your Order</h2></td></tr>
                <tr align="center"><td><input type="text" name="orderId" maxlength="10" size="15"></td></tr>
                <tr align="center"><td><input type="SUBMIT" name="b1" value="SUBMIT"> <INPUT TYPE="RESET" VALUE="RESET"></td></tr>
            </table> 
        </form>
    </body>
</html>

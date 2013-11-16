<%-- 
    Document   : addProduct
    Created on : 26 Jan, 2013, 5:14:53 PM
    Author     : dell
--%>

<%@page import="java.util.Random"%>
<%@page import="com.test.OmazonProduct"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.util.logging.Level"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.naming.Context"%>
<%@page import="com.test.OmazonProductEJBRemote"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product</title>
    </head>
    <body>
        <a href="Logout.jsp">Logout</a>
<%!
    OmazonProductEJBRemote omazonProductEJB = lookupOmazonProductEJBRemote();
    
    private OmazonProductEJBRemote lookupOmazonProductEJBRemote() {
        try {
            Context c = new InitialContext();
            return (OmazonProductEJBRemote) c.lookup("java:global/TestMdb/TestMdb-ejb/OmazonProductEJB!com.test.OmazonProductEJBRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
%>
<%
    //int randomNumber = new Random().nextInt(9000) + 1000;
    OmazonProduct omazonProduct = new OmazonProduct();
    //omazonProduct.setProductId(randomNumber);
    System.out.println(omazonProduct.getProductId());
    omazonProduct.setProductName(request.getParameter("prodName"));
    omazonProduct.setProductType(request.getParameter("prodType"));
    omazonProduct.setProductPrice(Integer.parseInt(request.getParameter("prodPrice")));
    int productId = omazonProductEJB.addProduct(omazonProduct);
%>
<jsp:forward page="InfoPage.jsp">
<jsp:param value="New Product Id is" name="message"></jsp:param>
<jsp:param value="<%=productId%>" name="newId"></jsp:param>
</jsp:forward>
    </body>
</html>

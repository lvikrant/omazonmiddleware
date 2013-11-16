<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.test.OmazonProductEJBRemote"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.test.OmazonProduct"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.Context"%>
<%@ page errorPage="ErrorPage.jsp"%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="commonstyle.css">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Product JSP Page</title>
    </head>
    <body>
        <%!            OmazonProductEJBRemote omazonProductEJB = lookupOmazonProductEJBRemote();

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
            int prod_id = Integer.parseInt(request.getParameter("prodId"));
            String prod_name = request.getParameter("prodName");
            String prod_type = request.getParameter("prodType");
            String prod_price = request.getParameter("prodPrice");
            
            OmazonProduct prod = new OmazonProduct();
            prod.setProductId(prod_id);
            prod.setProductName(prod_name);
            prod.setProductPrice(Integer.parseInt(prod_price));
            prod.setProductType(prod_type);
            omazonProductEJB.updateProductInfo(prod);
        %>                    
        <jsp:forward page="ErrorPage.jsp">
            <jsp:param value="Successfully Updated" name="errorMsg"></jsp:param>
        </jsp:forward>
    </body>
</html>
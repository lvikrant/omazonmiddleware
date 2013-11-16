<%@page import="com.test.OmazonCustomer"%>
<%@page import="com.test.OmazonCustomerEJBRemote"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.test.OmazonLocation"%>
<%@page import="com.test.OmazonLocationEJBRemote"%>
<%@page import="com.test.OmazonDeliveryEJBRemote"%>
<%@page import="com.test.OmazonDelivery"%>
<%@page import="com.test.OmazonOrder"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.Context"%>
<%@page import="com.test.OmazonOrderEJBRemote"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.ParseException"%>


<%@ page errorPage="ErrorPage.jsp"%>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="commonstyle.css">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Delivery JSP Page</title>
    </head>
    <body>
        <%! OmazonCustomerEJBRemote omazonCustEJB = lookupOmazonCustomerEJBRemote();

    private OmazonCustomerEJBRemote lookupOmazonCustomerEJBRemote() {
        try {
            Context c = new InitialContext();
            return (OmazonCustomerEJBRemote) c.lookup("java:global/TestMdb/TestMdb-ejb/OmazonCustomerEJB!com.test.OmazonCustomerEJBRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
        %>

        <%
            OmazonCustomer cust = new OmazonCustomer();
            cust.setCustomerId(Integer.parseInt(request.getParameter("custId")));
            cust.setCustomerName(request.getParameter("text_name"));
            cust.setCustomerAddr(request.getParameter("Address"));
            cust.setCustomerEmail(request.getParameter("text_Email"));
            cust.setCustomerPhone(Integer.parseInt(request.getParameter("text_Ph")));
            //omazonOrderEJB.updateOrderInfo(order);
            omazonCustEJB.updateCustomerInfo(cust);
            
            %>
        <jsp:forward page="ErrorPage.jsp">
            <jsp:param value="Successfully Updated" name="errorMsg"></jsp:param>
        </jsp:forward>
        
    </body>
</html>
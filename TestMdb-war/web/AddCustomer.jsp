<%@page import="com.test.OmazonCustomer"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.Context"%>
<%@page import="com.test.OmazonCustomerEJBRemote"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%@ page errorPage = "ErrorPage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="commonstyle.css">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Omazon Add Customer</title>
    </head>
    <body>
        <a href="Logout.jsp">Logout</a>
        <%!    OmazonCustomerEJBRemote omazonCustEJB = lookupOmazonCustomerEJBRemote();

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
            cust.setCustomerName(request.getParameter("text_Name"));
            cust.setCustomerAddr(request.getParameter("txtadd"));
            cust.setCustomerPhone(Integer.parseInt(request.getParameter("text_Ph")));
            cust.setCustomerEmail(request.getParameter("text_Email"));
            int custId = omazonCustEJB.addCustomer(cust);
            if (custId == 0) {
        %>
        <jsp:forward page="ErrorPage.jsp">
            <jsp:param value="Adding Customer Failed" name="errorMsg"></jsp:param>
        </jsp:forward>
        <%} else {
        %>

        <jsp:forward page="InfoPage.jsp">
            <jsp:param value="New Customer Id is" name="message"></jsp:param>
            <jsp:param value="<%=custId%>" name="newId"></jsp:param>
        </jsp:forward>
        <%
            }
        %>
    </body>
</html>
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
        <%!public static Calendar DateToCalendar(Date date) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                return cal;
            }
            OmazonOrderEJBRemote omazonOrderEJB = lookupOmazonOrderEJBRemote();
            OmazonDeliveryEJBRemote omazonDeliveryEJB = lookupOmazonDeliveryEJBRemote();
            OmazonLocationEJBRemote omazonLocationEJB = lookupOmazonLocationEJBRemote();

            private OmazonOrderEJBRemote lookupOmazonOrderEJBRemote() {
                try {
                    Context c = new InitialContext();
                    return (OmazonOrderEJBRemote) c.lookup("java:global/TestMdb/TestMdb-ejb/OmazonOrderEJB!com.test.OmazonOrderEJBRemote");
                } catch (NamingException ne) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
                    throw new RuntimeException(ne);
                }
            }

            private OmazonLocationEJBRemote lookupOmazonLocationEJBRemote() {
                try {
                    Context c = new InitialContext();
                    return (OmazonLocationEJBRemote) c.lookup("java:global/TestMdb/TestMdb-ejb/OmazonLocationEJB!com.test.OmazonLocationEJBRemote");
                } catch (NamingException ne) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
                    throw new RuntimeException(ne);
                }
            }

            private OmazonDeliveryEJBRemote lookupOmazonDeliveryEJBRemote() {
                try {
                    Context c = new InitialContext();
                    return (OmazonDeliveryEJBRemote) c.lookup("java:global/TestMdb/TestMdb-ejb/OmazonDeliveryEJB!com.test.OmazonDeliveryEJBRemote");
                } catch (NamingException ne) {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
                    throw new RuntimeException(ne);
                }
            }
        %>

        <%
            int flag = 0;
            List<OmazonLocation> location_list = new ArrayList<OmazonLocation>();
            String status = request.getParameter("rad");
            DateFormat formatter = null;
            Date convertedDate = null;
            formatter = new SimpleDateFormat("dd-MM-yyyy");
            char charstatus = status.charAt(0);
            OmazonOrder order = new OmazonOrder();
            order.setOrderStatus(charstatus);
            int ord_id = Integer.parseInt(request.getParameter("orderId"));
            order.setOrderId(ord_id);
            String upd_loc = request.getParameter("ucity");
            //==============================================================
            location_list = omazonLocationEJB.findAllLocationByCity(upd_loc);
            BigDecimal start_lat = location_list.get(0).getLocationLat();
            BigDecimal start_long = location_list.get(0).getLocationLong();
            //==================================================================
            order.setOrderStatusLat(start_lat);
            order.setOrderStatusLong(start_long);
            String del_date = request.getParameter("cdate");
            convertedDate = (Date) formatter.parse(del_date);
            order.setOrderExpectedDeldate(convertedDate);
            omazonOrderEJB.updateOrderInfo(order);
            if (charstatus == 'D') {
                OmazonDelivery delivery = new OmazonDelivery();
                delivery.setOrderId(ord_id);
                delivery.setDeliveryDate(convertedDate);
                delivery.setDeliveryRemarks(request.getParameter("Remarks"));
                int del_id = omazonDeliveryEJB.addOrder(delivery);
                if (del_id == 0) {
                    flag = 1;
                }
            } else {
                if (flag == 1) {%>
        <jsp:forward page="ErrorPage.jsp">
            <jsp:param value="Updating Order Failed" name="errorMsg"></jsp:param>
        </jsp:forward>
        <%                    } else {
            System.out.println("Successfully updated");
        %>
        <jsp:forward page="ErrorPage.jsp">
            <jsp:param value="Successfully Updated" name="errorMsg"></jsp:param>
        </jsp:forward>
        <%
                }
            }
        %>
    </body>
</html>
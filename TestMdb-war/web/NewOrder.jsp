<%@page import="java.math.BigDecimal"%>
<%@page import="com.test.OmazonLocation"%>
<%@page import="com.test.OmazonLocationEJBRemote"%>
<%@page import="com.test.OmazonProduct"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.test.OmazonOrder"%>
<!-- %@page import="com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException"%> -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.logging.Logger"%>
<%@ page import="java.util.logging.Level"%>
<%@ page import="javax.naming.NamingException"%>
<%@ page import="javax.naming.InitialContext"%>
<%@ page import="javax.naming.Context"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.ParseException"%> 
<%@ page errorPage="ErrorPage.jsp"%>
<%@ page import="com.test.OmazonOrderEJBRemote"%>
<%@ page import="com.test.OmazonProductEJBRemote"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="commonstyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Make New Order</title>
</head>
<body>
	<a href="Logout.jsp">Logout</a>
<%!
public static Calendar DateToCalendar(Date date)
{ 
	  Calendar cal = Calendar.getInstance();
	  cal.setTime(date);
	  return cal;
	}
private Date parseDate(String date, String format) throws ParseException
{
    SimpleDateFormat formatter = new SimpleDateFormat(format);
    return formatter.parse(date);
}
private int getCarrierId(String carrier)
               {
    //int c_id = 0;
    if(carrier.equals("Air"))
               {
        return 111;
        
    }
       else if(carrier.equals("Water"))
                     {
           return 222;
       }
       else{
        return 333;
       }
}
OmazonOrderEJBRemote omazonOrderEJB = lookupOmazonOrderEJBRemote();
OmazonProductEJBRemote omazonProductEJB = lookupOmazonProductEJBRemote();
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

    private OmazonProductEJBRemote lookupOmazonProductEJBRemote() {
        try {
            Context c = new InitialContext();
            return (OmazonProductEJBRemote) c.lookup("java:global/TestMdb/TestMdb-ejb/OmazonProductEJB!com.test.OmazonProductEJBRemote");
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
%>
<%
//StringBuilder sb = new StringBuilder();
List<OmazonProduct> product_list = new ArrayList<OmazonProduct>();
DateFormat formatter = null;
Date convertedDate = null;
Date expectedDate = null;
formatter = new SimpleDateFormat("dd-MM-yyyy");
OmazonOrder order = new OmazonOrder();
String productName = request.getParameter("productName");
order.setProductName(productName);
//==============================================================
product_list = omazonProductEJB.findAllProductNames(productName);
int prod_id = product_list.get(0).getProductId();
order.setProductId(prod_id);
order.setCustomerId(Integer.parseInt(request.getParameter("text_custid")));
String orddate  = request.getParameter("text_Date");
System.out.println("HTML date "+orddate);
convertedDate = (Date) formatter.parse(orddate);
//======================================================
SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
Calendar c = Calendar.getInstance();
c.setTime(sdf.parse(orddate));
c.add(Calendar.DATE, 4);  // number of days to add
String expDate = sdf.format(c.getTime());  // dt is now the new date
expectedDate = (Date) formatter.parse(expDate);
//============================================================
order.setOrderDate(convertedDate);
order.setOrderExpectedDeldate(expectedDate);
String start = request.getParameter("city");
String dest = request.getParameter("city2");
if(start.equalsIgnoreCase(dest))
{
	System.out.println("Cities cannot be same!");
	%>
	<jsp:forward page="ErrorPage.jsp">
	<jsp:param value="Cities cannot be same!" name="errorMsg"></jsp:param>
	</jsp:forward>
	<%
}
else
{
//==================================================
List<OmazonLocation> location_list = new ArrayList<OmazonLocation>();
location_list = omazonLocationEJB.findAllLocationByCity(start);
BigDecimal start_lat = location_list.get(0).getLocationLat();
BigDecimal start_long = location_list.get(0).getLocationLong();
location_list = omazonLocationEJB.findAllLocationByCity(dest);
BigDecimal dest_lat = location_list.get(0).getLocationLat();
BigDecimal dest_long = location_list.get(0).getLocationLong();
order.setOrderStartLat(start_lat);
order.setOrderStartLong(start_long);
order.setOrderEndLat(dest_lat);
order.setOrderEndLong(dest_long);
//====================
order.setOrderStatusLat(start_lat);
order.setOrderStatusLong(start_long);
//=======================================================
String carrier = request.getParameter("carrier");
order.setOrderCarrier(carrier);
int c_id = getCarrierId(carrier);
order.setOrderCarrierId(c_id);
order.setOrderStatus('A');
order.setOrderExceptionDesc("NA");
int orderId = omazonOrderEJB.addOrder(order);
out.println(orderId);
out.println("Order added");

if(orderId == 0)
{
%>
<jsp:forward page="ErrorPage.jsp">
<jsp:param value="Adding Order Failed" name="errorMsg"></jsp:param>
</jsp:forward>
<%
}
else
{
	String displayOrderId = String.valueOf(orderId);
	//System.out.println(displayEmployeeId+employeeId);
%>
<jsp:forward page="InfoPage.jsp">
<jsp:param value="Your order Id is" name="message"></jsp:param>
<jsp:param value="<%=displayOrderId%>" name="newId"></jsp:param>
</jsp:forward>
<%
}
}
%>
</body>
</html>
<%@page import="com.test.OmazonDeliveryEJBRemote"%>
<%@page import="com.test.OmazonDelivery"%>
<%@page import="com.test.OmazonLocation"%>
<%@page import="com.test.OmazonLocationEJBRemote"%>
<%@page import="com.test.OmazonLocationEJBRemote"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.test.OmazonOrder"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="com.test.OmazonOrderEJBRemote"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="commonstyle.css">
<title>Omazon Tracking</title>
</head>
<body>
    <%!
   
OmazonOrderEJBRemote omazonOrderEJB = lookupOmazonOrderEJBRemote();   
OmazonLocationEJBRemote omazonLocationEJB = lookupOmazonLocationEJBRemote();
OmazonDeliveryEJBRemote omazonDeliveryEJB  = lookupOmazonDeliveryEJBRemote();
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
<table class ="layout" border="0" width="90%" align="center">
	<tr>
		<td align="center">
	    <p class="heading2">Omazon</p>
	    </td>                                
	</tr>
</table>
<%
try 
{
     List<OmazonOrder> order_list = new ArrayList<OmazonOrder>();
      List<OmazonLocation> location_list = new ArrayList<OmazonLocation>();
  int order_id = Integer.parseInt(request.getParameter("orderId"));
  order_list = omazonOrderEJB.getOrderStatus(order_id);
  char locSta = 0;
                        String locStatus = null;
			String locLocation = null;
     OmazonOrder order = new OmazonOrder();
    if((request.getParameter("orderId")==null)||((request.getParameter("orderId")).length()==0))
    {
%>

<%-- Forward the request to error page if field is left blank --%>
<jsp:forward page="ErrorPage.jsp" >
<jsp:param value="UserId and Password (or) Order Id is mandatory" name="errorMsg"></jsp:param>
</jsp:forward>

<%
    }
    else
        {		
			
                        locSta  = order_list.get(0).getOrderStatus();                        
			BigDecimal lat = order_list.get(0).getOrderStatusLat();
                        location_list = omazonLocationEJB.findAllLocationByLat(lat);
                        locLocation = location_list.get(0).getLocationCity();
                        if((locSta=='A'))
			{
				locStatus = "Active";
			}
			else if((locSta=='P'))
			{
				locStatus = "Pending";
			}
			else if((locSta=='R'))
			{
				locStatus = "Rejected";
			}
			else if((locSta=='C'))
			{
				locStatus = "Cancelled";
				locLocation = "NA";
			}
                        else if((locSta=='D'))
			{
				locStatus = "Delivered";
				//locLocation = "NA";
			}
                     }                                             
%>
<table  border="0" width="50%" align="center">
	<tr align="center"><td colspan=2><h1 class="heading1" >Status Information</h1></td></tr>
	<tr><td align="center">Location</td> <td align="center"><%=locLocation%></td></tr>
	<tr><td align="center">Status</td> <td align="center"><%=locStatus%></td></tr>
</table>
<%
   }
catch(Exception e)
{
    System.out.println(e);
}
%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
<%@ page import="java.util.Date"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="com.test.OmazonProductEJBRemote"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="commonstyle.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Make New Order </title>

</head>
<body>
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
<a href="Logout.jsp">Logout</a>
<p class="heading2" align="center"> Omazon Courier</p>
<form name="frm1" action="NewOrder.jsp" method=post>
<table border="0" align="center" width="50%">
<tr><td align="center" colspan="2">
	<p class="heading3">Make New Order</p>
</td></tr>
<tr>
	<td>Product Name<span class="mandatory">*</span></td>
        <%
        try
        {
        List listobj=omazonProductEJB.findProductNames();
	Iterator iteratorobj=listobj.iterator();
        %>
        <td><select name="productName" size="1"><option value="">select</option>
        <%
        while(iteratorobj.hasNext())    
        {
   	String obj;
    	obj=(String)iteratorobj.next();
        %>
        <option value="<%=obj%>"><%=obj%></option>
        <%
        }
        }
        catch(NullPointerException e)
        {
        %>
        <jsp:forward page="ErrorPage.jsp">
		<jsp:param name="errorMsg" value="Order not assigned to this Employee ID" />
	</jsp:forward>
        <%	
        }
        %>          
        </select></td>
</tr>
<tr>
<td>Customer ID</td><td><input type="text" name="text_custid" ></td>
</tr>

<tr>
<td>Order Date </td><td><input type="text" name="text_Date" ></td><td><h5>(DD-MM-YYYY)</h5></td>
</tr>
<tr>
<td>Current Location<span class="mandatory">(*)</span></td><td><select name="city"> 
											  <option value="0">--Select-- 
											  <option value="Frankfurt">Frankfurt 
											  <option value="Munich">Munich
											  <option value="Hamburg">Hamburg
                                                                                          <option value="Berlin">Berlin
											  <option value="Cologne">Cologne
											  <option value="Bonn">Bonn
											</select></td>
<tr>
<td>Destination Location<span class="mandatory">(*)</span></td><td><select name="city2"> 
											  <option value="0">--Select-- 
											  <option value="Frankfurt">Frankfurt 
											  <option value="Munich">Munich
											  <option value="Hamburg">Hamburg
					          				  <option value="Berlin">Berlin
											  <option value="Cologne">Cologne
											  <option value="Bonn">Bonn
											</select></td>
<tr>                                                                                        
<td>Carrier<span class="mandatory">(*)</span></td><td><select name="carrier"> 
											  <option value="0">--Select-- 
											  <option value="Air">Air 
											  <option value="Water">Water
											  <option value="Land">Land
											</select></td>

<tr><td><td><input type="SUBMIT" name="b1" value="SUBMIT" >
<!--onClick="return validate()"-->
 <INPUT TYPE="RESET" VALUE="RESET">
			          				   
</table>
</form>
</body>
</html>
<%@page import="com.test.OmazonOrder"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Level"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="com.test.OmazonOrderEJBRemote"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
 <%!
 OmazonOrderEJBRemote omazonOrderEJB = lookupOmazonOrderEJBRemote();
 private OmazonOrderEJBRemote lookupOmazonOrderEJBRemote() {
        try {
            Context c = new InitialContext();
            return (OmazonOrderEJBRemote) c.lookup("java:global/TestMdb/TestMdb-ejb/OmazonOrderEJB!com.test.OmazonOrderEJBRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

 %>

<html>
<head>
<title>Omazon</title>
<!-- To include the style sheet -->
<link rel="stylesheet" type="text/css" href="commonstyle.css">
<script language="JavaScript" src="Validate.js"></script>
</head>
<body>
<form name="courierForm" action="Delivery.jsp" OnClick="return fnCheck();">
<h1 class="heading1" align="center">Omazon Courier</h1>
<center>
	<h3 class="heading3">Courier Delivery Form</h3>
</center>
<table border="0" align="center" width="70%">
<tr>
	<td>Order Id<span class="mandatory">*</span></td>
<%
try
{        List<String> order_list = new ArrayList<String>();
         order_list = omazonOrderEJB.findAllOrderIds();
         Iterator iteratorobj=order_list.iterator();
%>
	<td><select name="orderId" size="1"><option value="">select</option>
<%
    while(iteratorobj.hasNext())    
    {
    	Integer obj;
    	obj=(Integer)iteratorobj.next();
%>
		<option value="<%=obj.intValue() %>"><%=obj.intValue() %></option>
<%
    }
}
catch(NullPointerException e)
{
%>
	<%-- Forwards the control to Error Page. --%>
	<jsp:forward page="ErrorPage.jsp">
		<jsp:param name="errorMsg" value="Order not assigned to this Employee ID" />
	</jsp:forward>
<%	
}
%>
	</select></td>
</tr>
<tr>
	<td>Delivery Date<span class="mandatory">*</span>(dd-mm-yyyy)</td>
	<td><input type="text" name="cdate" maxlength="11"></td>
</tr>
<tr>
		<td>Status<span class="mandatory">*</span></td>
		<td><input type="radio" name="rad" value="P">Pending<br>
		<input type="radio" name="rad" value="D">Delivered<br>
		<input type="radio" name="rad" value="R">Rejected</td>
</tr>
<tr>
<td>Update Location<span class="mandatory">(*)</span></td><td><select name="ucity"> 
											  <option value="0">--Select-- 
											  <option value="Frankfurt">Frankfurt 
											  <option value="Munich">Munich
											  <option value="Hamburg">Hamburg
                                                                                            <option value="Berlin">Berlin
											  <option value="Cologne">Cologne
											  <option value="Bonn">Bonn
											</select></td>
<tr>
<tr>
	<td>Remarks</td>
	<td><textarea rows="3" cols="25" name="Remarks"></textarea></td>
</tr>
<tr></tr>
<tr>
	<td colspan="2"><font color="red"> * Mandatory Fields</font></td>
</tr>

<tr>
	<td></td>
	<td><input type="submit" name="Submit" value="Submit" onClick="return fnValidate()">
	 <input type="reset" name="Reset" value="Reset"></td>
</tr>
</table>
</form>
</body>
</html>

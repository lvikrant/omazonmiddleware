<%@page import="com.test.OmazonOrder"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Level"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="com.test.OmazonProductEJBRemote"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Iterator"%>
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

<html>
<head>
<title>Omazon</title>
<!-- To include the style sheet -->
<link rel="stylesheet" type="text/css" href="commonstyle.css">
<script language="JavaScript" src="Validate.js"></script>
</head>
<body>
<form name="courierForm" action="UpdateProduct.jsp" OnClick="return fnCheck();">
<h1 class="heading1" align="center">Omazon Inc.</h1>
<center>
	<h3 class="heading3">Update Product Details</h3>
</center>
<table border="0" align="center" width="70%">
    <tr>
            <td>Product ID<span class="mandatory">(*)</span></td><td><input type="text" name="prodId"></td>
        </tr>
<tr>
<tr>
            <td>Product Name<span class="mandatory">(*)</span></td><td><input type="text" name="prodName"></td>
        </tr>
<tr>
    
<td>Update Product type<span class="mandatory">(*)</span></td><td><select name="prodType"> 
											  <option value="ST">--Select-- 
											  <option value="PE">Perishable 
											  <option value="NP">Non-perishable
											</select></td>
</tr>                                                                                        
<tr>
 <tr>
            <td>Product Price<span class="mandatory">(*)</span></td><td><input type="text" name="prodPrice"></td>
        </tr>
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

<%@page import="com.test.OmazonCustomerEJBRemote"%>
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

    OmazonCustomerEJBRemote omazonCustEJB = lookupOmazonCustomerEJBRemote();

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

<html>
    <head>
        <title>Omazon</title>
        <!-- To include the style sheet -->
        <link rel="stylesheet" type="text/css" href="commonstyle.css">
        <script language="JavaScript" src="Validate.js"></script>
    </head>
    <body>
        <form name="courierForm" action="UpdateCustomer.jsp" OnClick="return fnCheck();">
            <h1 class="heading1" align="center">Omazon Courier</h1>
            <center>
                <h3 class="heading3">Update Customer Form</h3>
            </center>
            <table border="0" align="center" width="70%">
                <tr>
                    <td>Customer Id<span class="mandatory">*</span></td>
                    <%
                        try {
                            List<String> cust_list = new ArrayList<String>();
                            cust_list = omazonCustEJB.getAllCustomerId();
                            Iterator iteratorobj = cust_list.iterator();
                    %>
                    <td><select name="custId" size="1"><option value="">select</option>
                            <%
                                while (iteratorobj.hasNext()) {
                                    Integer obj;
                                    obj = (Integer) iteratorobj.next();
                            %>
                            <option value="<%=obj.intValue()%>"><%=obj.intValue()%></option>
                            <%
                                }
                            } catch (NullPointerException e) {
                            %>
                            <%-- Forwards the control to Error Page. --%>
                            <jsp:forward page="ErrorPage.jsp">
                                <jsp:param name="errorMsg" value="Customer ID has a problem" />
                            </jsp:forward>
                            <%    }
                            %>
                        </select></td>
                </tr>
                <tr> 
                    <td>Customer Name <span class="mandatory">(*)</span></td><td><input type="text" name="text_name"></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td><textarea rows="3" cols="25" name="Address"></textarea></td>
                </tr>
                <tr> 
                    <td>Telephone No<span class="mandatory">(*)</span></td><td><input type="text" name="text_Ph"></td>
                </tr>
                <tr>
                    <td>Email<span class="mandatory">(*)</span></td><td><input type="text" name="text_Email"></td>
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

<%-- 
    Document   : InfoPage
    Created on : 26 Jan, 2013, 6:16:20 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <table  border="0" width="25%" align="center">
            <tr align="center">
                <td colspan=2><h1 class="heading1" >Information</h1></td>
            </tr>
            <tr>
                <td><%=request.getParameter("message")%></td> <td><%=request.getParameter("newId")%></td>
            </tr>
        </table>
        <h3 align="center"><a href="index.jsp">Back to Home Page.</a></h3>
    </body>
</html>
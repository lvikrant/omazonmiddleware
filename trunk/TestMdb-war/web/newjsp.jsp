<%-- 
    Document   : newjsp
    Created on : Jan 24, 2013, 4:56:07 PM
    Author     : vikaram
--%>

<%@page import="java.util.logging.Level"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.NamingException"%>
<%@page import="javax.naming.Context"%>
<%@page import="com.test.OmazonOrderEJBRemote"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%! 
        private OmazonOrderEJBRemote lookupOmazonOrderEJBRemote() {
        try {
            Context c = new InitialContext();
            return (OmazonOrderEJBRemote) c.lookup("java:global/Omazon/Omazon-ejb/OmazonOrderEJB!com.omazon.OmazonOrderEJBRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
%>
        <h1>Hello World!</h1>
    </body>
</html>

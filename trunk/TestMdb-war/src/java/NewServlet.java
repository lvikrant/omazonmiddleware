/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.test.OmazonCustomerEJBRemote;
import com.test.OmazonDeliveryEJBRemote;
import com.test.OmazonLocationEJBRemote;
import com.test.OmazonOrderEJBRemote;
import com.test.OmazonProductEJBRemote;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vikaram
 */
@WebServlet(name = "NewServlet", urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {
    OmazonCustomerEJBRemote omazonCustomerEJB = lookupOmazonCustomerEJBRemote();

    @Resource(mappedName = "QueueConnectionFactory")
    private ConnectionFactory connectionFactory;
    //@Resource(mappedName = "mqTest")
    //private Queue queue;
    OmazonDeliveryEJBRemote omazonDeliveryEJB = lookupOmazonDeliveryEJBRemote();
    OmazonLocationEJBRemote omazonLocationEJB = lookupOmazonLocationEJBRemote();
    OmazonProductEJBRemote omazonProductEJB = lookupOmazonProductEJBRemote();
    OmazonOrderEJBRemote omazonOrderEJB = lookupOmazonOrderEJBRemote();
    @Resource(mappedName = "jms/dest")
    private Queue dest;
    @Resource(mappedName = "jms/queue")
    private ConnectionFactory queue;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String str = request.getParameter("msg");
        try {
            sendJMSMessageToDest(str);
        } catch (JMSException ex) {
            Logger.getLogger(NewServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet NewServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//           out.println("<h1>Message Sent!"+str+"</h1>");
//            out.println("</body>");
//            out.println("</html>");

//            Connection conn = connectionFactory.createConnection();
//            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
//            MessageProducer messageProducer = session.createProducer(dest);

      //      Person student = new Person("Greg", "Bowie");

        //    ObjectMessage msg = session.createObjectMessage(student);
            //messageProducer.send(msg);
          //  System.out.println("Message Sent for Person: " + student.getFirstName()
            //        + " " + student.getLastName());

//            messageProducer.close();
//            conn.close();
        } catch (Exception e) {
            System.out.println("Exception raised: " + e.toString());
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private Message createJMSMessageForjmsDest(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    private void sendJMSMessageToDest(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = queue.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(dest);
            messageProducer.send(createJMSMessageForjmsDest(session, messageData));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

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

    private OmazonDeliveryEJBRemote lookupOmazonDeliveryEJBRemote() {
        try {
            Context c = new InitialContext();
            return (OmazonDeliveryEJBRemote) c.lookup("java:global/TestMdb/TestMdb-ejb/OmazonDeliveryEJB!com.test.OmazonDeliveryEJBRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private OmazonCustomerEJBRemote lookupOmazonCustomerEJBRemote() {
        try {
            Context c = new InitialContext();
            return (OmazonCustomerEJBRemote) c.lookup("java:global/TestMdb/TestMdb-ejb/OmazonCustomerEJB!com.test.OmazonCustomerEJBRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}

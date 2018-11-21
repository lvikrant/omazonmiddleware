# omazonmiddleware
The implementation project combined a variety of middleware technologies Java 6 EE ecosystem with an application server, database server (via ORM mappers) and a webserver contributing to a large
online retail application. The application also uses asynchronous communication with a queuing and publish/subscribe model. JSP and HTML were used for front end and MySQL for handling the DB
part.

Features implemented
======================
0. Customer and Product Data Management 
0. Order Process Support 
0. Shipment tracking
0. Shipment tracking via JMS messages
0. Automated shipment status updates
0. Shipment position updates
0. Notification on exceptional events
0. On shipment status page By email

0.Mobile Clients
The swing based client now supports mobility; a local dedicated DB has been associated with the client. Upon every event in the client the connection with the main server will be checked for using the following code
HttpURLConnection.setFollowRedirects(false);
HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
con.setRequestMethod("GET");
con.setConnectTimeout(70000);
isUp = (con.getResponseCode() == HttpURLConnection.HTTP_ACCEPTED);
con.disconnect();
Based on the value of the isUp variable the update is rooted to global or local db. Database used is MySQL; Updates are done using standard queries after establishing the connection with local DB. A local DB was preferred over file for ease of operations and due to already existing design constraints in the client.


0. Consistency Guarantees
Aim: Refresh the local DBs and ensuring updates happen in all local DBs or none.
For this feature, we use two-phase commit protocol. Two-phase commit is a transaction protocol designed to avoid the inconsistencies that arise with distributed setup. In a two-phase commit protocol, there is one coordinator and other systems are participants.

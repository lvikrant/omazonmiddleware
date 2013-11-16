/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.omazon.client;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author vikaram
 */
public class Common {
 
  public static boolean isUrlReachable(String host) {
  String URLName="http://"+host+":4848";
  boolean isUp = false;
  try {
     HttpURLConnection.setFollowRedirects(false);
     HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
     con.setRequestMethod("GET");
     con.setConnectTimeout(5000);
     isUp = (con.getResponseCode() == HttpURLConnection.HTTP_ACCEPTED);
     con.disconnect();
  }
  catch (Exception e) {
     return isUp;
  }
  return isUp;
}


}

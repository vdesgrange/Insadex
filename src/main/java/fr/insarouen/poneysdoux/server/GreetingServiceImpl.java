package fr.insarouen.poneysdoux.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.lang.String;

import fr.insarouen.poneysdoux.client.GreetingService;
import fr.insarouen.poneysdoux.shared.FieldVerifier;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
    GreetingService {
	
	private ArrayList<String[]> users = new ArrayList<String[]>();

  public String greetServer(String input) throws IllegalArgumentException {
    // Verify that the input is valid.
    if (!FieldVerifier.isValidName(input)) {
      // If the input is not valid, throw an IllegalArgumentException back to
      // the client.
      throw new IllegalArgumentException(
          "Name must be at least 4 characters long");
    }

    String serverInfo = getServletContext().getServerInfo();
    String userAgent = getThreadLocalRequest().getHeader("User-Agent");

    // Escape data from the client to avoid cross-site script vulnerabilities.
    input = escapeHtml(input);
    userAgent = escapeHtml(userAgent);

    return "Hello, " + input + "!<br><br>I am running " + serverInfo
        + ".<br><br>It looks like you are using:<br>" + userAgent;
  }

  private String escapeHtml(String html) {

    if (html == null) {
      return null;
    }
    return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
        ">", "&gt;");
  }
 
  public ArrayList<String[]> getUsers() {
	  Information info = new Information();
	  return info.getNamesUsers();
  }
  
  public ArrayList<String[]> getInfosUsers() {
	  Information info = new Information();
	  return info.getInformationsUsers();
  }
  
}


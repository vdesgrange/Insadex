package fr.insarouen.poneysdoux.client;

import java.util.ArrayList;
import java.lang.String;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	
  String greetServer(String name) throws IllegalArgumentException;
  public ArrayList<String[]> getUsers() throws IllegalArgumentException;
  public ArrayList<String[]> getInfosUsers() throws IllegalArgumentException;
}



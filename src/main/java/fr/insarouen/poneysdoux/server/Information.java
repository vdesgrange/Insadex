package fr.insarouen.poneysdoux.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.lang.String;

import fr.insarouen.poneysdoux.server.DbFacade;

public class Information {

	private ArrayList<String[]> users = new ArrayList<String[]>();
	
	public Information() {
		// super();
		DbFacade db = new DbFacade();
		this.users = db.retrieve_users();
	}
	
	
	  public ArrayList<String[]> getNamesUsers() throws IllegalArgumentException {
		  ArrayList<String[]> users_final = new ArrayList<String[]>();

		  Iterator<String[]> it = this.users.iterator();
		  while(it.hasNext()){
			  String[] user_final = new String[2];
			  String[] user = it.next();
			  
			  user_final[0] = user[0];
			  user_final[1] = user[1];
			  users_final.add(user_final);
		  }
		  return(users_final);
		  
	  }
	  
	  public ArrayList<String[]> getInformationsUsers() throws IllegalArgumentException {
		  ArrayList<String[]> users_final = new ArrayList<String[]>();

		  Iterator<String[]> it = this.users.iterator();
		  while(it.hasNext()){
			  String[] user_final = new String[7];
			  String[] user = it.next();
			  
			  user_final[0] = user[0];
			  user_final[1] = user[1];
			  user_final[2] = user[2];
			  user_final[3] = user[3];
			  user_final[4] = user[4];
			  user_final[5] = user[5];
			  user_final[6] = user[6];
			  users_final.add(user_final);
		  }
		  return(users_final);
		  
	  }
	
}
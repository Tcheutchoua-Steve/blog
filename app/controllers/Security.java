package controllers;

import models.*;

public class Security extends Secure.Security {
	static boolean authenticate(String username, String password) {
		//return true;
		return User.connect(username, password) != null;
	}
	
	// To handle disconnection or logging out
	static void onDisconnected() {
	    Application.index2 ();
	}
	
	// To handle logging in , the method is being overriddn
	static void onAuthenticated() {
	    Admin.index();
	}
	
	//to provide supper admin authentication
	static boolean check(String profile) {
	    if("admin".equals(profile)) {
	        return User.find("byEmail", connected()).<User>first().isAdmin;
	    }
	    return false;
	}
}

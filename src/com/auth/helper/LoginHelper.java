package com.auth.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class LoginHelper
 {
	public static boolean validateUser( String vUserName)throws FileNotFoundException{
	      Scanner fileScan = new Scanner (new File("C:\\Apache_User\\userpwd.htpasswd-all"));
	      boolean valid = false; // added this variable
	      
	      while (fileScan.hasNextLine()) {
	        String input = fileScan.nextLine();
	        String Username = input.substring(0,input.indexOf(':'));
	        //String Password = input.substring(input.indexOf(':')+1,input.length());

	        if ((Username.equals(vUserName))) {
	          valid = true; 
	          System.out.println("Valid user "+vUserName);
	        
	        } 
	      }

		return valid;
	    }
	
	public static void createUser(String user, String pass) throws IOException {
		
	

		String exePath = "C:\\Apache24\\bin\\htpasswd.exe ";
		String userPwdFile = "C:\\Apache_User\\userpwd.htpasswd-all ";

		String command = exePath + "-db " + userPwdFile + user + " " + pass;

		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();

		} catch (Exception e) {
			e.printStackTrace();
		
		}
	}	
 }
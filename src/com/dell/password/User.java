package com.dell.password;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;

public class User {
	HashMap<String,String> hd = new HashMap<>(); 
	ArrayList<Webdetails> al=new ArrayList<>();

	
	

	public static void main(String[] args) {
		
    new User().login();

	}

	private void login() {
		System.out.println("Welcome to Locker Management portal");
        System.out.println("Enter from the below preferred  options");
        System.out.println("1. New user");
        System.out.println("2. Registered  user");
        System.out.println("3. Exit");
        Scanner sc=new Scanner(System.in);
        String s =sc.next();
        Integer user_input = Integer.parseInt(s);
        try{
        switch (user_input){
		case 1:
			register();
			break;
		case 2:
			checkuser();
			break;
		case 3:
			System.out.println("Thanks for Visiting Locker Management Portal. Have a Great day !!!!");
			break;
	
		default :
			System.out.println("Wrong input entered, try again ");
			login();
			break;
			}  		
        }catch(Exception e){
        	
        	System.out.println("Incorrect input entered . Please choose either option 1 or 2");
        	login();
	        	
	        }

	}

	
	private void checkuser() {
		
		Scanner sc=new Scanner(System.in);
		String user_name,pwd;
		System.out.println("Enter your user_name");
		user_name=sc.nextLine();
		getuser();                
        if(hd.containsKey(user_name)==true){
        	System.out.println("Enter your password");
    		pwd=sc.next(); 	
        	if((hd.get(user_name)).equals(pwd)){
        		System.out.println("Logged in Successfully");

        		login_Webdetail(user_name,pwd);
        	}
        	else{
        		System.out.println("Incorrect password .. Try once again");
        		 login();
        	}
        }else{
        	System.out.println("User not registered. Please register before logging in");
        	 login();
        }
      
	}

	public void register() {
		try{
			
		String user_name,pwd;
		System.out.println("Enter  user name you required to keep");
		Scanner sn=new Scanner(System.in);
		user_name=sn.nextLine();
		
        getuser();
        if(hd.containsKey(user_name)==true){
        		System.out.println("same user already exists. Try new user name or login to the existing account ");
        		login();
        	}
        	else{
        		System.out.println("Enter Password you wish to keep");
                pwd=sn.next();
        		validating_user(user_name,pwd);}
        	               
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	
	}

	private void validating_user(String user_name, String pwd) {
		try{
			Userdetails ud=new Userdetails(user_name,pwd);
			FileOutputStream os = new FileOutputStream(new File("locker.txt"), true);
			ObjectOutputStream objectOut = new ObjectOutputStream(os);
			objectOut.writeObject(ud);
//			objectOut.writeObject("\n");
			objectOut.flush();
			objectOut.close();
			System.out.println("Successfully Registered");
			login();
			
			}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}

	private void add_webdetails(String user_name,String pwd) {
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Please enter Website name");
		String Web_name, web_uname,web_pwd,u_name,u_ip;
		Web_name=sc.next();
		System.out.println("Please enter user_name");
		web_uname=sc.next();
		System.out.println("Please enter password");
		web_pwd=sc.next();
		Userdetails ud =new Userdetails(user_name,pwd);
		
		Validating_webdetails(user_name,Web_name,web_uname,web_pwd);
		System.out.println("Do you wish to add more website passwords");
		System.out.println("Press y or N ");
		u_ip=sc.next();
		if (u_ip .equalsIgnoreCase("y")||u_ip.equalsIgnoreCase("yes")){
			add_webdetails(user_name, pwd);
		}
		else{
			System.out.println("Thanks. All the provided details are added.");
			login_Webdetail(user_name, pwd);
		}
		
		
		
	}
	private void Validating_webdetails(String uname ,String web_name, 
			String web_uname, String web_pwd) {
		try{
		Webdetails wd= new Webdetails(uname,web_name,web_uname,web_pwd);
		FileOutputStream os = new FileOutputStream(new File("Webdetails.txt"), true);
		ObjectOutputStream objectOut = new ObjectOutputStream(os);
		objectOut.writeObject(wd);
		objectOut.flush();
		objectOut.close();
		System.out.println("Successfully added web details");
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		
	}

	private void login_Webdetail(String user_name,String pwd) {
		System.out.println("Welcome to Locker Management portal");
        System.out.println("Enter from the below preferred  options");
        System.out.println("1.Adding details");
        System.out.println("2.Viewing details");
        System.out.println("3.Exit");
        Scanner sc=new Scanner(System.in);
        String s =sc.next();
        Integer user_ip = Integer.parseInt(s);
        try{
        switch (user_ip){
		case 1:
			add_webdetails(user_name, pwd);
			break;
		case 2:
			webdetails_Check(user_name, pwd);
			break;
		
		case 3:
			System.out.println("Thanks for Visiting Locker Management Portal. Have a Great day!!!!!!!!");
			login();
			break;
		default :
			System.out.println("Wrong input entered, try again ");
			login_Webdetail(user_name, pwd);
			break;
			}  		
        }catch(Exception e){
        	
        	System.out.println("Incorrect input entered . Please choose either option 1 or 2");
        	login_Webdetail(user_name, pwd);
	        	
	        }
		
		
	}



	
	private void view_webdetails(String user_name,String pwd) {
		try{
			
			
			FileInputStream fin=new FileInputStream(new File("webdetails.txt"));

			boolean cont = true;
        	try{
        	   	  while(cont){
        	   		
        		   ObjectInputStream input = new ObjectInputStream(fin);
        	       Object obj = input.readObject();
        	       if(obj != null)
        	       {
        	    	   Webdetails wl=(Webdetails) obj;
       			       al.add(wl);
       			       
       			          			             			      
       			       	    	   }  
        	       else
        	        cont = false;
        	      
        	       }

			fin.close();
			
			        	
			}catch(Exception e){
				e.getLocalizedMessage();
	}
	
		}catch(Exception e){System.out.println(e.getMessage());}
	}
	

	private void webdetails_Check(String user_name,String pwd) {
		try{
			
			view_webdetails(user_name,pwd);
			
			String web_name,web_pwd,web_uname;
			System.out.println("\n Please find the URL details provided with Password ");
			
			
		for (Webdetails wx: al)
		{
			if(wx.getUname().equals(user_name))
			{
			  web_uname=wx.getWeb_user_name();
			  web_name=wx.getWebsite_name();
			  web_pwd=wx.getWeb_pwd();
			  
			  System.out.println("\n Website name :"+web_name);
			  System.out.println("\n User_name:" + web_uname);
			  System.out.println("\n Website_password:"+web_pwd);
			}
		}
		
		login_Webdetail(user_name,pwd);
			

		
			  }
			catch(Exception e){
				System.out.println(e.getMessage());
			}
	}

	private void getuser() {
		try{
			
		
			FileInputStream fin=new FileInputStream(new File("locker.txt"));

			boolean cont = true;
        	try{
        	   	  while(cont){
        	   		
        		   ObjectInputStream input = new ObjectInputStream(fin);
        	       Object obj = input.readObject();
        	       if(obj != null)
        	       {
        	    	   Userdetails ul = (Userdetails) obj;
       			       String uName=ul.getUser_name();
       			       String uPass=ul.getPwd();
     			       hd.put(uName, uPass);
        	    	          	    	   }  
        	       else
        	        cont = false;
        	      
        	       }

			fin.close();
			
			        	
			}catch(Exception e){
				e.getLocalizedMessage();
	}
	
		}catch(Exception e){System.out.println(e.getMessage());}
	}
}




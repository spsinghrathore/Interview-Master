package com.interviewmaster;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
	
	public static void menu() {
		System.out.println("-----WELCOME!-----");
		System.out.println("1. 👔 Take Interview (Already a User)");
		System.out.println("2. 🚀 Signup (New User)");
		//System.out.println("3. See Records");    this feature is in Admin part
		System.out.println("3. Exit");
		Scanner sc = new Scanner(System.in);
		int op = sc.nextInt();
		switch(op) {
			case 1 : Interview.takeInterview(); //first it checks user present or not than it redirect to interview
			  break;
			case 2: signup();
       		  break;
			case 3: Admin.exit();
			  break;
			  
			default: System.out.println("Select option");
			         menu();
		}
	
	}
	
	//--------------------------user first time
	public static void signup() {
		Connection conn = null;
		PreparedStatement pstmt= null;
		Scanner obj = new Scanner(System.in);
		System.out.println("-----(SignUp)-----");
		 System.out.println("Enter Unique username: ");
		 String username = obj.next();
		 //checking username already present or not!
		 if(isPresent(username)==false) {
			 System.out.println("Enter Your Phone: ");
			 String phone = obj.next();
			 System.out.println("Enter Your email: ");
			 String mail = obj.next();
			 try {
					String qry ="INSERT INTO user(name,email,phone) VALUES(?,?,?)";
					conn = DatabaseConnection.getConnection();
					pstmt = conn.prepareStatement(qry);
					pstmt.setString(1, username);
					pstmt.setString(2, mail);
					pstmt.setString(3, phone);
					int res = pstmt.executeUpdate();
					if(res>0) {
						System.out.println("Interview Started!");
						
					}
					
				} catch(SQLException e) {
					e.printStackTrace();
				}
				finally {
					DatabaseConnection.closeOnExit();
					Interview.takeInterview();
				}
			}
		 else {
			 System.out.println("username already present");
			 signup();
		 }
			 
		 }
		
		 
		 
		
		
		
	//------------------is present 
	public static boolean isPresent(String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet res = null;
		
		try {
			String qry = "SELECT * FROM user WHERE name=?";
			conn = DatabaseConnection.getConnection();
			pstmt = conn.prepareStatement(qry);
			pstmt.setString(1, name);
			res = pstmt.executeQuery();
			if(res.next()) {
				DatabaseConnection.closeOnExit();
				return true;
			}
			else {
				
				
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			//System.out.println("sql query not found");
		}
		
		return false;
		
	}

	
	
	

}

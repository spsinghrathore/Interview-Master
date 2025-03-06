package com.interviewmaster;
import java.sql.*;
import java.util.Scanner;


public class Admin {
	
	//---------------------------------------------------------authentication
	public static void  authenticate_admin() {
		
		Connection conn= null;
		PreparedStatement pstmt = null;
		ResultSet res= null;
		
		System.out.println("-----WELCOME!-----");
		System.out.println("     (login)     ");
		Scanner obj = new Scanner(System.in);
		System.out.println("  Username:");
		String user = obj.next();
	    System.out.println("  Password:");
	    String pass = obj.next();
	    
	    try {
	    conn = DatabaseConnection.getConnection();
	    String qry = "SELECT * FROM Admin WHERE username=? AND password=?";
	    
	    pstmt = conn.prepareStatement(qry);
	    pstmt.setString(1, user);
	    pstmt.setString(2, pass);
	    res = pstmt.executeQuery();
	    
	    if(res.next()) {
	    	System.out.println("Namaste " + user);
	    	
	    	
	    }
	    else {
	    	System.out.println("Invalid Credentials");
			authenticate_admin();
	    }
	    
	    
	    
	    
	    
	    
	    } catch( SQLException e) {
	    	e.printStackTrace();
	    }
	    finally {
	    	DatabaseConnection.closeOnExit();
	    	menu();
	    }
	    
	    
	    
	    
	    
	    
	    
	  
	    
	}
	

	//------------------------------------menu
	private static void menu() {
		System.out.println("---Select and option---");
		System.out.println("1:Add admin");
		System.out.println("2:Delete admin");
		System.out.println("3: Update admin");
		System.out.println("4:View all admin");
		System.out.println("5:View all Candidate");
		System.out.println("6:Exit");
		
		Scanner obj = new Scanner(System.in);
		int op = obj.nextInt();
		
		switch(op) {
		case 1: addAdmin();
		        break;
		case 2: deleteAdmin();
		        break;
		case 3: updateAdmin();
		        break;
		case 4: viewAdmin();
        break;
		case 5: viewUser();
        break;
		case 6: exit();
        break;
        default: System.out.println("Enter correct choice");
        menu();
		}
		
	}
	
    //	-----------------------------------------add admin
	public static void addAdmin() {
		PreparedStatement pstmt= null;
		Connection conn= null;
		
		Scanner obj = new Scanner(System.in);
		System.out.println("New username:");
		String user = obj.next();
		System.out.println("New Password:");
		String pass = obj.next();
		
		try {
	    
			String qry = "INSERT INTO admin (username,password) VALUES(?,?)";
			conn = DatabaseConnection.getConnection();
		    pstmt = conn.prepareStatement(qry);
		    pstmt.setString(1, user);
		    pstmt.setString(2, pass);
		    
		    pstmt.executeUpdate();
		    
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		finally { 
			DatabaseConnection.closeOnExit();
			menu();
		}
		
	}
	
	
	//----------------------------------------delete admin
	public static void deleteAdmin() {
		Scanner obj  = new Scanner(System.in);
		Connection conn= null;
		PreparedStatement pstmt = null;
		
		System.out.println("Enter name of admin to delete");
		String name = obj.next();
		
		
		try {
			String qry = "DELETE FROM admin WHERE username=?";
			conn = DatabaseConnection.getConnection();
			pstmt = conn.prepareStatement(qry);
			pstmt.setString(1, name);
			int res = pstmt.executeUpdate();
			if(res >0) {
				System.out.println("Admin deleted successfully!");
				menu();
				
			}
			
			else {
				System.out.println("Admin Not Found");
				menu();
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		finally {
			DatabaseConnection.closeOnExit();
		}
	}
	
	//-------------------------view all admin
	public static void viewAdmin() {
		Connection conn= null;
		ResultSet res = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DatabaseConnection.getConnection();
			String qry = "SELECT * FROM admin";
			pstmt = conn.prepareStatement(qry);
			res = pstmt.executeQuery();
			int i=1;
			while(res.next()) {
				
			    String admin = res.getString(2);
				System.out.println("|" + i + " | " + admin);
				i++;
			}
	} catch(SQLException e) {
		e.printStackTrace();
	}
		finally {
			DatabaseConnection.closeOnExit();
			menu();
		}
	}
	//-------------------------view all candidate
		public static void viewUser() {
			Connection conn= null;
			ResultSet res = null;
			PreparedStatement pstmt = null;
			
			try {
				conn = DatabaseConnection.getConnection();
				String qry = "SELECT * FROM user";
				pstmt = conn.prepareStatement(qry);
				res = pstmt.executeQuery();
				int i=1;
				while(res.next()) {
					
				    String admin = res.getString(2);
				    String phone = res.getString(4);
					System.out.println("|" + i + " | " + admin  + " " + phone + "|");
					i++;
				}
		} catch(SQLException e) {
			e.printStackTrace();
		}
			finally {
				DatabaseConnection.closeOnExit();
				menu();
			}
		}
	
	//-------------------------update admin
	public static void updateAdmin(){
		Connection conn = null;
		PreparedStatement pstmt =null;
		Scanner obj = new Scanner(System.in);
		
		try {
			String qry = "UPDATE admin SET username=? , password=? WHERE admin_id=?";
			conn = DatabaseConnection.getConnection();
			pstmt = conn.prepareStatement(qry);
			System.out.println("Enter admin id to update");
			int id= obj.nextInt();
			System.out.println("Change username: ");
			String newUser = obj.next();
			System.out.println("Change password: ");
			String newPass = obj.next();
			
			
			//setting the values
			pstmt.setString(1, newUser);
			pstmt.setString(2,newPass);
			pstmt.setInt(3, id);
			
			pstmt.executeUpdate();
			
			
			
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DatabaseConnection.closeOnExit();
			menu();
		}
	}
	//------------------------------exit
	public static void exit() {
		System.out.println("--------x Signed Out! x-------");
		
	}
	
	

}

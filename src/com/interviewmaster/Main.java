package com.interviewmaster;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		//Admin.authenticate_admin();
		menu();
	}

	
	
	
	//--------------------menu
	private static void menu() {
		System.out.println("---Select and option---");
		System.out.println("1:Interview Master");
		System.out.println("2:Admin Panel");
		System.out.println("3:Exit");
		
		Scanner obj = new Scanner(System.in);
		int op = obj.nextInt();
		
		switch(op) {
		case 1: User.menu();
		        break;
		case 2:  Admin.authenticate_admin();
		        break;
		case 3: Admin.exit();
        break;
        default: System.out.println("Enter a right choice");
        menu();
		}
		
	}
}

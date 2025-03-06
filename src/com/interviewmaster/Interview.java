package com.interviewmaster;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;


public class Interview {
	
	//---------------------------------------------The Interview
	
 public static void takeInterview() {
	    Scanner sc = new Scanner(System.in);
		System.out.println("Enter Name:");
		String name = sc.next();
		
	 if(User.isPresent(name)) {
		 System.out.println("Select a Profile!👩🏻‍💻");
		 System.out.println("1. Java Developer");
		 System.out.println("2. Salesforce Developer(Coming soon)");
		 System.out.println("3. Front-end Developer(Coming soon)");
		 System.out.println("4. Graphic Designer(Coming soon)");
		 System.out.println("5. Data Analyst(Coming soon)");
		 int op = sc.nextInt();
		 switch(op) {
		 case 1: interview(name);
		 break;
		 default: System.out.println("These option are under development 🛠️");
		 takeInterview();
		 }
		 

		 
		 
	 System.out.println("------------");
	 }
	 else  {
		 System.out.println("User not found Sign up to take Interview");
		 System.out.println("1. Retry");
		 System.out.println("2. SignUp");
		 int op = sc.nextInt();
		 switch(op) {
		 case 1: takeInterview();
		 break;
		 case 2:User.signup();
		 break;
		 default: System.out.println("Invalid Choice!");
		          takeInterview();
		 }
		 
			
	 }

	 
	 
	 
	 
 }
 
 public static void retakeInterview() {
	 //take a name and email and checks it previous data
	 Scanner sc = new Scanner(System.in);
	 System.out.println("Enter your name:  ");
	 System.out.println("Email: ");
	 System.out.println("Fetching.....");
	 takeInterview();
	 
	 
 }
 
 //-------------------------the interview
 public static void interview(String name) {
	 System.out.println("Entered in Interview");
	 Scanner obj = new Scanner(System.in);
	 Connection conn = null;
	 PreparedStatement pstmt = null;
	 PreparedStatement pstmt2 = null;
	 ResultSet res = null;
	 //code to get the user id by it's name!!!
	 try {
		//query to get the user id by it's name!!!
		 String qry1 = "SELECT * FROM user WHERE name=?";
		 String qry2 = "INSERT INTO interview(q_one,q_two,q_three,q_four,user_id) VALUES(?,?,?,?,?)";
		 conn = DatabaseConnection.getConnection();
		 pstmt = conn.prepareStatement(qry1);
		 pstmt.setString(1, name);
		 res = pstmt.executeQuery();
		 int user_id =0;
		if(res.next()) {
		   user_id = res.getInt(1);
		
		 
		}
		  pstmt2 = conn.prepareStatement(qry2);
			 
			 
			 //executing the question query
			 System.out.println("Q.1 Tell me about yourself?");
			 String ans1 = getAnswer(obj);
			 
			 System.out.println("Q.2 " + getQuestion());
			 String ans2 = getAnswer(obj);
			 
			 System.out.println("Q.3 " + getQuestion());
			 String ans3 = getAnswer(obj);
			 
			 System.out.println("Q.4 Do you have any Questions?");
			 String ans4 = getAnswer(obj);
			 
			 pstmt2.setString(1,ans1);
			 pstmt2.setString(2,ans2);
			 pstmt2.setString(3,ans3);
			 pstmt2.setString(4,ans4);
			 pstmt2.setInt(5,user_id);
			 
			 
			 
			 pstmt2.executeUpdate();
			 System.out.println("---Thank You For The Interview---");
			 User.menu();
			 
		 
		 

		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 	 } catch(SQLException e) {
		 e.printStackTrace();
	 }
	 
	 
	 
 }
 
 //-------------------------method to give random Java questions
 public static String getQuestion() {
	 
	 String[] javaQuestions = {
	            "What are the main features of Java?",
	            "Explain OOP principles in Java.",
	            "What is the difference between JDK, JRE, and JVM?",
	            "What is the significance of the `main` method in Java?",
	            "How does Java achieve platform independence?",
	            "Explain the difference between `==` and `equals()` in Java.",
	            "What are access modifiers in Java?",
	            "What is polymorphism in Java, and how is it implemented?",
	            "What is an exception in Java? Explain the types of exceptions.",
	            "What is the purpose of garbage collection in Java?",
	            "How does multithreading work in Java?",
	            "What is the difference between `ArrayList` and `LinkedList`?",
	            "What are `static` and `final` keywords in Java?",
	            "Explain the concept of inner classes in Java.",
	            "What is the difference between `throw` and `throws`?",
	            "How does Java manage memory?",
	            "What are the key differences between an `interface` and an `abstract class`?",
	            "Explain the use of `super` and `this` keywords in Java.",
	            "What is the role of the `hashCode()` and `equals()` methods?",
	            "How does the `synchronized` keyword work in Java?"
	        };

     Random random = new Random();
         int index = random.nextInt(javaQuestions.length);
         
     
     return javaQuestions[index];
 }
 
 
//-----------------------Method to give random Salesforce Developer questions
public static String getQuestionSalesforce() {
  String[] salesforceQuestions = {
      "What is Salesforce, and why is it used?",
      "Explain the difference between a Profile and a Role in Salesforce.",
      "What are the key components of Salesforce architecture?",
      "What is a Workflow in Salesforce, and how is it used?",
      "What are Triggers in Salesforce, and how are they implemented?",
      "What is the difference between SOQL and SOSL?",
      "What is an Apex class in Salesforce?",
      "Explain the concept of Governor Limits in Salesforce.",
      "What is a Visualforce page, and how does it differ from Lightning Components?",
      "What are the different types of relationships in Salesforce?",
      "How do you create and deploy custom objects in Salesforce?",
      "What is the purpose of the AppExchange in Salesforce?",
      "What is the significance of Test Classes in Salesforce?",
      "Explain the difference between Salesforce Classic and Lightning Experience.",
      "What are the key features of Salesforce Reports and Dashboards?"
  };
  
  Random random = new Random();
  int index = random.nextInt(salesforceQuestions.length);
  
  return salesforceQuestions[index];
}

//-----------------Method to give random Graphic Designer questions
public static String getQuestionGraphicDesigner() {
  String[] graphicDesignerQuestions = {
      "What design tools and software are you proficient in?",
      "Explain the difference between vector and raster graphics.",
      "What is the importance of color theory in graphic design?",
      "How do you approach designing for a brand identity?",
      "What is your process for creating a logo design?",
      "What are the key principles of design?",
      "How do you ensure accessibility in your designs?",
      "What is the importance of typography in design?",
      "Can you explain the term 'white space' in design?",
      "What is responsive design, and why is it important?",
      "How do you handle client feedback or revisions?",
      "What is your experience with creating UI/UX designs?",
      "Explain the difference between Photoshop and Illustrator.",
      "What are your favorite design trends currently?",
      "How do you stay updated with new design tools and technologies?"
  };

  Random random = new Random();
  int index = random.nextInt(graphicDesignerQuestions.length);
  
  return graphicDesignerQuestions[index];
}

//-------------------------Method to give random Data Analyst questions
public static String getQuestionDataAnalyst() {
  String[] dataAnalystQuestions = {
      "What is data cleansing, and why is it important?",
      "What is the difference between data mining and data analysis?",
      "What tools and programming languages are commonly used by data analysts?",
      "Explain the difference between structured and unstructured data.",
      "What is the significance of SQL in data analysis?",
      "What are some common data visualization tools you’ve used?",
      "How do you handle missing or inconsistent data in a dataset?",
      "What is regression analysis, and when is it used?",
      "Explain the difference between a database and a data warehouse.",
      "What is the importance of statistical analysis in data analytics?",
      "What are some common metrics used to measure data quality?",
      "How do you approach creating a dashboard for data visualization?",
      "Explain the difference between correlation and causation.",
      "What is the importance of machine learning in data analytics?",
      "Can you describe your experience with big data technologies like Hadoop or Spark?"
  };

  Random random = new Random();
  int index = random.nextInt(dataAnalystQuestions.length);
  
  return dataAnalystQuestions[index];
}

//-------------------------------Method to give random Front-end Developer questions
public static String getQuestionFrontEndDeveloper() {
  String[] frontEndQuestions = {
      "What are the key responsibilities of a front-end developer?",
      "Explain the difference between `id` and `class` selectors in CSS.",
      "What is the Document Object Model (DOM)?",
      "What is the purpose of responsive web design?",
      "What are the key differences between HTML5 and previous versions?",
      "Explain the box model in CSS.",
      "What are the most common JavaScript frameworks used in front-end development?",
      "What is the difference between relative, absolute, and fixed positioning in CSS?",
      "How does CSS Flexbox work?",
      "What is the purpose of Webpack in front-end development?",
      "What is a single-page application (SPA)?",
      "Explain the differences between React and Angular.",
      "What are the common accessibility guidelines for web development?",
      "What is the importance of performance optimization in front-end development?",
      "How do you ensure cross-browser compatibility?"
  };

  Random random = new Random();
  int index = random.nextInt(frontEndQuestions.length);
  
  return frontEndQuestions[index];
}



 
 //-------------------------take valid answer
//Method to get a valid answer from the user (non-empty input)
 public static String getAnswer(Scanner scanner) {
     String answer = "";
     while (answer.trim().isEmpty()) {
         answer = scanner.nextLine();
         if (answer.trim().isEmpty()) {
             System.out.println("xxx-Answer cannot be empty.-xxx");
         }
     }
     return answer;
 }
}

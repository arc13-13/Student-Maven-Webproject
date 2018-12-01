package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	
	static final String dbClassName = "com.mysql.jdbc.Driver";

	  static final String CONNECTION =
	                          "jdbc:mysql://127.0.0.1/iiitb_student";
	
	public String insertintodb(String fname,String lname,String roll,String dob,Long pmarks,Long cmarks,Long mmarks,String Path,String gender,String pass) throws Exception {
		
		  

		    Class.forName(dbClassName);

		    // Properties for user and password. Here the user and password are both 'paulr'
		    Properties p = new Properties();

		    p.put("user","root");
		    p.put("password","");
		    Long avg = (pmarks+cmarks+mmarks)/3;
		    String Grade;
		    if(avg>80)
		    	Grade = "A";
		    else if(avg<=80 && avg>65)
		    	Grade="B";
		    else
		    	Grade="C";
		    // Now try to connect
		    try {
			    Connection connect = DriverManager.getConnection(CONNECTION,p);
			    //Statement statement = connect.createStatement();
			    PreparedStatement ps = connect.prepareStatement("insert into stu values(?,?,?,?,?,?,?,?,?,?,?)");
			    System.out.println("1");
			    System.out.println(Path);
			    System.out.println(dob);
			    ps.setString(1, fname);
			    ps.setString(2, lname);
			    ps.setString(3, roll);
			    ps.setString(4, dob);
			    ps.setLong(5, pmarks);
			    ps.setLong(6, cmarks);
			    System.out.println("2");
			    ps.setLong(7, mmarks);
			    ps.setString(8, Grade);
			    ps.setString(9,Path);
			    System.out.println("3");
			    ps.setString(10,gender);
			    ps.setString(11, pass);
			    System.out.println("4");
	
			    ps.executeUpdate();
	
			    System.out.println("It works !");
			    connect.close();
		    }
		    catch (SQLException e) {

		    	throw new Exception("Same Roll Number already exists");

			}
		
		return "Success";
	}
	
	 public static String retrieve(String rollnum) throws
		     Exception
		{
		System.out.println(dbClassName);
		// Class.forName(xxx) loads the jdbc classes and
		// creates a drivermanager class factory
		Class.forName(dbClassName);
		
		// Properties for user and password. Here the user and password are both 'paulr'
		Properties p = new Properties();
		p.put("user","root");
		p.put("password","");
		
		// Now try to connect
		try {
			Connection c = DriverManager.getConnection(CONNECTION,p);
			Statement st = c.createStatement();
			
			System.out.println("printing roll num " + rollnum);
			ResultSet rs = st.executeQuery("Select * from stu where roll =" + "'" + rollnum + "'");
			System.out.println(rs+"    1");
			while (rs.next()) {
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				//String email = rs.getString("email");
				String dob = rs.getString("dob");
				//Long num = rs.getLong("num");
				String gender = rs.getString("gender");
				Long marks = rs.getLong("marks");
				String pic = rs.getString("path");
				//String gender = rs.getString("gender");
				String res = (fname + "@@" + lname+ "@@" + dob+ "@@" +gender+ "@@" + marks + "@@" + pic);
				return res;
				}
			
			System.out.println("It works !");
			c.close();
			return "1";
		}
		catch(SQLException e){
			throw new Exception("Error");
		}
	}
	 
	 
	 public static String login(String roll,String Cpass) throws Exception
			{
			System.out.println(dbClassName);
			// Class.forName(xxx) loads the jdbc classes and
			// creates a drivermanager class factory
			Class.forName(dbClassName);
			
			// Properties for user and password. Here the user and password are both 'paulr'
			Properties p = new Properties();
			p.put("user","root");
			p.put("password","");
			
			// Now try to connect
			try {
				Connection c = DriverManager.getConnection(CONNECTION,p);
				Statement st = c.createStatement();
				
				System.out.println("printing roll num " + roll);
				ResultSet rs = st.executeQuery("Select pass from stu where roll =" + "'" + roll + "'");
				System.out.println(rs+"    1");
				while (rs.next()) {
					System.out.println(rs.getString("pass"));
					System.out.println(Cpass);
					String pass = rs.getString("pass");
					if(Cpass.equals(pass)) {
						return "Success";
					}
					else {
						return "Wrong Password";
					}
				}
				
				System.out.println("It works !");
				c.close();
			}
			catch(SQLException e){
				throw new Exception("Error");
			}
			return "Error";
			}
	 
	
	 public static String update(String rollnum,Long pmarks,Long cmarks,Long mmarks) throws
		     Exception
		{
		System.out.println(dbClassName);
		// Class.forName(xxx) loads the jdbc classes and
		// creates a drivermanager class factory
		Class.forName(dbClassName);
		
		// Properties for user and password. Here the user and password are both 'paulr'
		Properties p = new Properties();
		p.put("user","root");
		p.put("password","");
		
		// Now try to connect
		try {
			Connection c = DriverManager.getConnection(CONNECTION,p);
			Statement st = c.createStatement();
			
			System.out.println("printing roll num " + rollnum);
			PreparedStatement ps = null;
			if(pmarks!=0 && cmarks!=0 && mmarks!=0) {
				String updateTableSQL = "update stu set pmarks=?,cmarks = ?,mmarks=? where roll = ?";
				ps = c.prepareStatement(updateTableSQL);
				ps.setLong(1, pmarks);
				ps.setLong(2, cmarks);
				ps.setLong(3, mmarks);
				ps.setString(4, rollnum);
			}
			else if(pmarks==0 && cmarks!=0 && mmarks!=0) {
				String updateTableSQL = "update stu set cmarks = ?,mmarks=? where roll = ?";
				ps = c.prepareStatement(updateTableSQL);
				ps.setLong(1, cmarks);
				ps.setLong(2, mmarks);
				ps.setString(3, rollnum);
			}
			else if(pmarks==0 && cmarks==0 && mmarks!=0) {
				String updateTableSQL = "update stu set mmarks=? where roll = ?";
				ps = c.prepareStatement(updateTableSQL);
				ps.setLong(1, mmarks);
				ps.setString(2, rollnum);
			}
			else if(pmarks!=0 && cmarks==0 && mmarks==0) {
				String updateTableSQL = "update stu set pmarks=? where roll = ?";
				ps = c.prepareStatement(updateTableSQL);
				ps.setLong(1, pmarks);
				ps.setString(2, rollnum);
			}
			else if(pmarks!=0 && cmarks!=0 && mmarks==0) {
				String updateTableSQL = "update stu set pmarks=?,cmarks=? where roll = ?";
				ps = c.prepareStatement(updateTableSQL);
				ps.setLong(1, pmarks);
				ps.setLong(2, cmarks);
				ps.setString(3, rollnum);
			}
			else if(pmarks==0 && cmarks!=0 && mmarks==0) {
				String updateTableSQL = "update stu set cmarks=? where roll = ?";
				ps = c.prepareStatement(updateTableSQL);
				ps.setLong(1, cmarks);
				ps.setString(2, rollnum);
			}
			else if(pmarks!=0 && cmarks==0 && mmarks!=0) {
				String updateTableSQL = "update stu set pmarks=?,mmarks=? where roll = ?";
				ps = c.prepareStatement(updateTableSQL);
				ps.setLong(1, pmarks);
				ps.setLong(2, mmarks);
				ps.setString(3, rollnum);
			}
			
			
			// execute update SQL stetement
			if(ps==null) {
				ResultSet rs = st.executeQuery("Select * from stu where roll =" + "'" + rollnum + "'");
				
				while (rs.next()) {
					String fname = rs.getString("fname");
					String lname = rs.getString("lname");
					//String email = rs.getString("email");
					String dob = rs.getString("dob");
					String gender = rs.getString("gender");
					//Long num = rs.getLong("num");
					Long pmark = rs.getLong("pmarks");
					Long cmark = rs.getLong("cmarks");
					Long mmark = rs.getLong("mmarks");
					String grade = rs.getString("grade");
					String pic = rs.getString("path");
					//String gender = rs.getString("gender");
					String res = (fname + "@@" + lname+ "@@" + dob+ "@@" +gender+ "@@" + pmark + "@@" + cmark + "@@" + mmark + "@@" +  grade +"@@" + pic);
					System.out.println("Record is updated to DBUSER table!");
					System.out.println("It works !");
					c.close();
					return res;
					}
				System.out.println("aa");
				return "1";
			}
			else {
				int rows = ps.executeUpdate();
				if(rows == 0) {
					throw new Exception("Roll number doesn't exist");
				}
				
				ResultSet rs = st.executeQuery("Select * from stu where roll =" + "'" + rollnum + "'");
				
				while (rs.next()) {
					String fname = rs.getString("fname");
					String lname = rs.getString("lname");
					//String email = rs.getString("email");
					String dob = rs.getString("dob");
					String gender = rs.getString("gender");
					//Long num = rs.getLong("num");
					Long pmark = rs.getLong("pmarks");
					Long cmark = rs.getLong("cmarks");
					Long mmark = rs.getLong("mmarks");
					String grade = rs.getString("grade");
					String pic = rs.getString("path");
					//String gender = rs.getString("gender");
					String res = (fname + "@@" + lname+ "@@" + dob+ "@@" +gender+ "@@" + pmark + "@@" + cmark + "@@" + mmark +"@@" +  grade + "@@" + pic);
					System.out.println("Record is updated to DBUSER table!");
					System.out.println("It works !");
					c.close();
					return res;
					}		
				return "1";
			}
			/*int rows = ps.executeUpdate();
			if(rows == 0) {
				throw new Exception("Roll number doesn't exist");
			}
			
			ResultSet rs = st.executeQuery("Select * from stu where roll =" + "'" + rollnum + "'");
			
			while (rs.next()) {
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				//String email = rs.getString("email");
				String dob = rs.getString("dob");
				String gender = rs.getString("gender");
				//Long num = rs.getLong("num");
				Long pmark = rs.getLong("pmarks");
				Long cmark = rs.getLong("cmarks");
				Long mmark = rs.getLong("mmarks");
				String pic = rs.getString("path");
				//String gender = rs.getString("gender");
				String res = (fname + "@@" + lname+ "@@" + dob+ "@@" +gender+ "@@" + pmark + "@@" + cmark + "@@" + mmark + "@@" + pic);
				System.out.println("Record is updated to DBUSER table!");
				System.out.println("It works !");
				c.close();
				return res;
				}		
			return "1";*/
		}
		catch (SQLException e) {

			System.out.println(e.getMessage());

		}
		
		
		return "success";
		} 
	 


}

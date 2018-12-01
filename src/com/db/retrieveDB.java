package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import org.json.simple.JSONObject;

import com.student.bean.StudentDetails;



public class retrieveDB {
	
	static final String dbClassName = "com.mysql.jdbc.Driver";

	static final String CONNECTION =
	                          "jdbc:mysql://127.0.0.1/iiitb_student";
	
	public static String selectrecordSQL = "SELECT fname,lname,roll,dob,pmarks,cmarks,mmarks,grade,path FROM stu";
	
	public static ArrayList<StudentDetails> retrieveRollNo(String fromRoll, String toRoll,String sort) throws Exception{
		
		Class.forName(dbClassName);

	    // Properties for user and password. Here the user and password are both 'paulr'
	    Properties p = new Properties();
	    p.put("user","root");
	    p.put("password","");
		
	    try {
	    	System.out.println("from : " + fromRoll);
	    	System.out.println("to : " + toRoll);
	    	Connection connect = DriverManager.getConnection(CONNECTION,p);
	    	PreparedStatement ps = null;
	    	System.out.println(sort);
	    	if(sort.equals("pmarks")) {
	    		ps = connect.prepareStatement("select * from stu where roll between ? and ? order by pmarks");
				ps.setString(1,fromRoll);
				ps.setString(2, toRoll);
				System.out.println(ps.toString());
	    	}
	    	else if(sort.equals("cmarks")) {
	    		ps = connect.prepareStatement("select * from stu where roll between ? and ? order by cmarks");
				ps.setString(1,fromRoll);
				ps.setString(2, toRoll);
				System.out.println(ps.toString());
	    	}
	    	if(sort.equals("mmarks")) {
	    		ps = connect.prepareStatement("select * from stu where roll between ? and ? order by mmarks");
				ps.setString(1,fromRoll);
				ps.setString(2, toRoll);
				System.out.println(ps.toString());
	    	}
	    	if(sort.equals("grade")) {
	    		ps = connect.prepareStatement("select * from stu where roll between ? and ? order by grade");
				ps.setString(1,fromRoll);
				ps.setString(2, toRoll);
				System.out.println(ps.toString());
	    	}
	    	else if(sort.equals("roll")) {
	    		ps = connect.prepareStatement("select * from stu where roll between ? and ? order by roll");
				ps.setString(1,fromRoll);
				ps.setString(2, toRoll);
				System.out.println(ps.toString());
	    	}
	    	else if(sort.equals("gender")) {
	    		ps = connect.prepareStatement("select * from stu where roll between ? and ? order by gender");
				ps.setString(1,fromRoll);
				ps.setString(2, toRoll);
				System.out.println(ps.toString());
	    	}
	    	else if(sort.equals("dob")) {
	    		ps = connect.prepareStatement("select * from stu where roll between ? and ? order by dob");
				ps.setString(1,fromRoll);
				ps.setString(2, toRoll);
				System.out.println(ps.toString());
	    	}
	    	else {
	    		ps = connect.prepareStatement("select * from stu where roll between ? and ? order by fname,lname");
				ps.setString(1,fromRoll);
				ps.setString(2, toRoll);
	    	}
			ResultSet rs = ps.executeQuery();
			ArrayList<StudentDetails> als = new ArrayList<StudentDetails>();
			while(rs.next()) {
				StudentDetails sd = new StudentDetails();
				sd.setFname(rs.getString("fname"));
				sd.setLname(rs.getString("lname"));
				sd.setPMarks(rs.getLong("pmarks"));
				sd.setCMarks(rs.getLong("cmarks"));
				sd.setMMarks(rs.getLong("mmarks"));
				sd.setGrade(rs.getString("grade"));
				sd.setDob(rs.getString("dob"));
				sd.setGender(rs.getString("gender"));
				sd.setPic(rs.getString("path"));
				sd.setRollNo(rs.getString("roll"));
				als.add(sd);
			}
	    	System.out.println(als);
	    	return als;
	    }
		catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	    
	}
	
	public ArrayList<String> retrieveimages() throws Exception {
		Properties p = new Properties();
	    p.put("user","root");
	    p.put("password","");
	    Connection c = DriverManager.getConnection(CONNECTION,p);
	    Statement st = c.createStatement();
	    ResultSet rs = st.executeQuery("Select path from stu");
		System.out.println(rs+"    1");
		ArrayList<String> al = new ArrayList<String>();
		while (rs.next()) {
			al.add(rs.getString(1));
		}
		System.out.println(al);
		return al;
	}
	
	public static ArrayList<StudentDetails> getAllStudent(ResultSet rs) throws SQLException{
		ArrayList<StudentDetails> stu = new ArrayList<StudentDetails>();
		    while(rs.next()) {  
		    	StudentDetails Student=new StudentDetails();
		        Student.setRollNo(rs.getString(3));
		        Student.setFname(rs.getString(1));
		        Student.setLname(rs.getString(2));
		        Student.setDob(rs.getString(4));
		        Student.setPMarks(rs.getLong(5));
		        Student.setCMarks(rs.getLong(6));
		        Student.setMMarks(rs.getLong(7));
		        Student.setGrade(rs.getString(8));
		        Student.setPic(rs.getString(9));
		        //Student.setGender(rs.getString(10));
		        stu.add(Student);
		    }
		return stu;
	}
	
	 public ResultSet DisplayRecords(String query) throws SQLException, ClassNotFoundException {
		 	Class.forName(dbClassName);

		    // Properties for user and password. Here the user and password are both 'paulr'
		    Properties p = new Properties();
		    p.put("user","root");
		    p.put("password","");
		  	ResultSet resultSet = null;
		  	Connection connect = DriverManager.getConnection(CONNECTION,p);
	        try {
	             PreparedStatement statement = connect.prepareStatement(query);
	             resultSet = statement.executeQuery(query);
	        } catch (SQLException ex) {
	            System.out.println("The following error has occurred: " + ex.getMessage());
	        }     
			return resultSet;
	 }
	
}

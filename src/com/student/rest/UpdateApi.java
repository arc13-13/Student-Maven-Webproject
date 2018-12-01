package com.student.rest;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.db.retrieveDB;
import com.student.bean.*;


@Path("/retrieve")
public class UpdateApi {
	
	@POST
	@Path("/information")
	@Consumes(MediaType.APPLICATION_JSON)
	public String getStudentInformation() throws SQLException, ParseException, ClassNotFoundException
	{
		ArrayList<StudentDetails> stud=new ArrayList<StudentDetails>();
		
		stud=retrieveDB.getAllStudent(new retrieveDB().DisplayRecords("SELECT fname,lname,roll,dob,pmarks,cmarks,mmarks,grade,path FROM stu"));
		//new dataSource().closeConnection();
		String Head = "";
		String Body = "";
		String Foot = "";

		
				Head = "<div id='amazingcarousel-container-1'>" +
						"<div id='amazingcarousel-1' style='display:none;position:relative;width:100%;max-width:1024px;margin:0px auto 0px;'>"+
						"<div class='amazingcarousel-list-container'><ul class='amazingcarousel-list'>";
		        for (int i = 0; i < stud.size(); i++) {
		
		        	
			        Body=Body+"<li class='amazingcarousel-item'>" +
			                        "<div class='amazingcarousel-item-container'>" +
			                        	"<div class='amazingcarousel-image'>"+
			                        	"<img id='studentImg' src='" +stud.get(i).getPic().substring(52,stud.get(i).getPic().length()) + "' ' height=300' width='220' alt='"+stud.get(i).getFname()+"' /></div>" +
			                        		"<div class='amazingcarousel-title'><b>"+stud.get(i).getFname()+"</b></div>" +
			                        		"<table><tr>"+
			                        			"<td>Roll No : </td><td> "+stud.get(i).getRollNo() +"</td></tr>" +
			                        			"<td>Grade : </td><td>"+stud.get(i).getGrade() +"</td></tr></table>" +
			                        			
			                        	"</div>" + 
			                    "</li>";
		        	
               	}
		        Foot=Foot+"</ul></div><div class='amazingcarousel-prev'></div>" + 
		        		"<div class='amazingcarousel-next'></div><div class='amazingcarousel-nav'></div>" +
		            "</div></div>" ;
		
		return Head+Body+Foot;
	}
	
	@POST
	@Path("/rollNo")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response retrieveRollNo (String req) throws Exception {
		String res;
		System.out.println("bbbb");
		try {
		System.out.println("aaa");
		JSONParser jp = new JSONParser();
		JSONObject jo = (JSONObject) jp.parse(req);
		String fromRoll = (String)jo.get("from");
		String toRoll = (String)jo.get("to");
		String sort = (String)jo.get("sort");
		retrieveDB rdb = new retrieveDB();
		ArrayList<StudentDetails> al = rdb.retrieveRollNo(fromRoll, toRoll,sort);
		
		JSONObject obj = new JSONObject();
		JSONArray ja = new JSONArray();
		for(StudentDetails sd : al) {
			JSONObject ob = new JSONObject();
			ob.put("fname", sd.getFname());
			ob.put("lname", sd.getLname());
			ob.put("pmarks", sd.getPMarks());
			ob.put("cmarks", sd.getCMarks());
			ob.put("mmarks", sd.getCMarks());
			ob.put("grade", sd.getGrade());
			ob.put("gender", sd.getGender());
			System.out.println(sd.getPic().length() + " new");
			String p = sd.getPic().substring(52,sd.getPic().length());
			
			ob.put("path", p);
			ob.put("rollNo", sd.getRollNo());
			ob.put("dob", sd.getDob());
			ja.add(ob);
		}
		obj.put("roll",ja);
		System.out.println(obj);
		//System.out.println(res  + " 2 ");
		
		return Response.status(200).entity(obj.toJSONString()).build();
		}
		catch (Exception e) {
			String returnValue = "{\"message\":\"" + "Roll Number doesn't exist" + "\"}";
	        System.out.println(returnValue + "Error");
			return Response.status(500).entity(returnValue).build();
		}
		
	}
}

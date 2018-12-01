package com.student.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.db.DB;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;


@Path("/json")
public class JsonService {
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response retlogin(String rn) throws Exception {
		System.out.println(rn);
		try {
		System.out.println(rn);
		
		JSONParser jp = new JSONParser();
		JSONObject ob = (JSONObject) jp.parse(rn);
		System.out.println((String)ob.get("roll"));

		
		JSONObject obj = new JSONObject();	
		DB db1 = new DB();
		String ans = (db1.login((String) ob.get("roll"),(String)ob.get("password")));
		System.out.println(ans);
	
		String returnValue = "{\"message\":\"" + ans + "\"}";
	    System.out.println(returnValue + "Response");
	        
	    return Response.status(200).entity(returnValue).build();
		
		}
		catch (Exception e) {
			String returnValue = "{\"message\":\"" + e.getMessage() + "\"}";
	        System.out.println(returnValue + "Error");
	        
			return Response.status(500).entity(returnValue).build();
		}
	
	}
	
	
	@POST
	@Path("/abc")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response func(@FormDataParam("otherDetails") String qe,
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) throws Exception {
			System.out.println(qe);
		
		try {
			String uploadedFileLocation = "C:\\Users\\admin\\Desktop\\workspace\\Student\\WebContent\\images\\" + fileDetail.getFileName();
			JSONParser jp = new JSONParser();
			JSONObject ob = (JSONObject) jp.parse(qe);
			System.out.println((String)ob.get("fn"));
			
			JSONObject obj = new JSONObject();
			//System.out.println(x);
			obj.put("id", "name");
			String res = obj.toJSONString();
			System.out.println(res);
			
			DB db1 = new DB();
			String ans = db1.insertintodb((String)ob.get("fn"),(String)ob.get("ln"),(String) ob.get("roll"),(String) ob.get("dob"),(Long) ob.get("pmarks"),(Long) ob.get("cmarks"),(Long) ob.get("mmarks"),uploadedFileLocation,(String) ob.get("gender"),(String)ob.get("pass"));
			System.out.println("new here");
			writeToFile(uploadedInputStream, uploadedFileLocation);
			System.out.println("new here 1");
			String returnValue = "{\"message\":\"" + ans + "\"}";
	        System.out.println(returnValue + "Response");
	        
			return Response.status(200).entity(returnValue).build();
		}
		catch (Exception e) {
			String returnValue = "{\"message\":\"" + e.getMessage() + "\"}";
	        System.out.println(returnValue + "Error");
	        
			return Response.status(500).entity(returnValue).build();
		}
		
		
	}
	
	
	@POST
	@Path("/def")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ret(String rn) throws Exception {
		System.out.println(rn);
		
		JSONParser jp = new JSONParser();
		JSONObject ob = (JSONObject) jp.parse(rn);
		System.out.println((String)ob.get("roll"));

		
		JSONObject obj = new JSONObject();
		//System.out.println(x);
		//obj.put("id", "name");
		//String res = obj.toJSONString();
		//System.out.println(res);
		
		DB db1 = new DB();
		String ans = (db1.retrieve((String) ob.get("roll")));
		System.out.println(ans);
		if(!ans.equals("1")) {
			String ar[] = ans.split("@@");
			String path = ar[5].substring(49,ar[5].length());
			for(int i=0;i<ar.length;i++)
				System.out.println(ar[i]);
			String name = ar[0] + " " + ar[1];
			obj.put("name", name);
			obj.put("dob", ar[2]);
			obj.put("gender",ar[3]);
			obj.put("marks", ar[4]);
			obj.put("pic", path);
			String res = obj.toJSONString();
			
			//System.out.println(ans + "api");
			return Response.status(200).entity(res).build();
		}
		else {
			String returnValue = "{\"message\":\"" + "Roll Number doesn't exist" + "\"}";
	        System.out.println(returnValue + "Error");
			return Response.status(500).entity(returnValue).build();
		}
		
		
	}
	
	@POST
	@Path("/upd")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(String qe) throws Exception{
		System.out.println(qe);
		try {
			JSONParser jp = new JSONParser();
			JSONObject ob = (JSONObject) jp.parse(qe);
			System.out.println((String)ob.get("roll"));
			
			JSONObject obj = new JSONObject();
			
			DB db1 = new DB();
			String ans = db1.update((String) ob.get("roll"),(Long) ob.get("pmarks"),(Long) ob.get("cmarks"),(Long) ob.get("mmarks"));
			System.out.println(ans);
			if(!ans.equals("1")) {
				System.out.println("inside if");
				String ar[] = ans.split("@@");
				String path = ar[8].substring(52,ar[8].length());
				for(int i=0;i<ar.length;i++)
					System.out.println(ar[i]);
				String name = ar[0] + " " + ar[1];
				obj.put("name", name);
				obj.put("dob", ar[2]);
				obj.put("gender",ar[3]);
				obj.put("pmark", ar[4]);
				obj.put("cmark", ar[5]);
				obj.put("mmark", ar[6]);
				obj.put("grade", ar[7]);
				obj.put("pic", path);
				String res = obj.toJSONString();
				
				//System.out.println(ans + "api");
				return Response.status(200).entity(res).build();
			}
			else {
				String returnValue = "{\"message\":\"" + "Roll Number doesn't exist" + "\"}";
		        System.out.println(returnValue + "Response");
		        
				return Response.status(500).entity(returnValue).build();
			}
		}
		catch (Exception e) {
			String returnValue = "{\"message\":\"" + e.getMessage() + "\"}";
	        System.out.println(returnValue + "Error");
	        
			return Response.status(500).entity(returnValue).build();
		}
		
	}
	
	//not used
	@POST
	@Path("/xyz")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public Response downloadFile(String rollNo) throws Exception {
		
		try {		
			    File file = new File("C:\\Users\\admin\\Desktop\\workspace\\Student\\WebContent\\images\\q.png");
			    ResponseBuilder response = Response.ok((Object) file);
			    response.header("Content-Disposition", "attachment;filename=classes.jar");
			    return response.build();

		}
		catch (Exception e) {
			String returnValue = "{\"message\":\"" + e.getMessage() + "\"}";
	        System.out.println(returnValue + "Error");
	        
			return Response.status(500).entity(returnValue).build();
		}
		
		
	}
	
		private void writeToFile(InputStream uploadedInputStream,
			String uploadedFileLocation) {

			try {
				OutputStream out = new FileOutputStream(new File(
						uploadedFileLocation));
				int read = 0;
				byte[] bytes = new byte[1024];

				//out = new FileOutputStream(new File(uploadedFileLocation));
				while ((read = uploadedInputStream.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				out.flush();
				out.close();
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
}

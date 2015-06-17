package com.rest273.assignment1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.rest273.data.MysqlClient;
import com.rest273.data.SchemaMySql;
import com.rest273.util.ToJSON;


@Path("/V1/server/")
public class V1_server {

	static JSONArray jsonarray = new JSONArray();

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTrackInJSON() throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		String returnString = null;
		Response rb = null;
		
		try{
			conn = MysqlClient.MySqlCom2Conn().getConnection();
			query = conn.prepareStatement("select * from wearable.clientinfo");
			ResultSet rs = query.executeQuery();
			
			ToJSON converter = new ToJSON();
			JSONArray json = new JSONArray();
			
			json = converter.toJSONArray(rs);
			query.close(); //close connection
			
			returnString = json.toString();
			rb = Response.ok(returnString).build();
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if (conn != null) conn.close();
		}
		
		return rb;
	}
	

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(String incomingData) throws Exception {

		String returnString = null;
		JSONArray json = new JSONArray();
		SchemaMySql dao = new SchemaMySql();
		
		try {
			System.out.println("incomingData: " + incomingData);
			
		
			
			ObjectMapper mapper = new ObjectMapper();  
			ItemEntry itemEntry = mapper.readValue(incomingData, ItemEntry.class);
			
			int http_code = dao.insertIntoPC_PARTS(itemEntry.clientID, itemEntry.times);
			
			if( http_code == 200 ) {
				json = dao.queryReturnbrandParts(itemEntry.clientID);
				returnString = json.toString();
				
			} else {
				return Response.status(500).entity("Unable to process Item").build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		
		return Response.status(201).entity(returnString).build();
	}
}

class ItemEntry{
	public String clientID;
	public String times;
}

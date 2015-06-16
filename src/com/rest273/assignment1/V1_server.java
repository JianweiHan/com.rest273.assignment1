package com.rest273.assignment1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/V1/server/")
public class V1_server {
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.TEXT_PLAIN)
	public JSONObject getTrackInJSON() throws JSONException {
		/*
		 * Track track = new Track(); track.setTitle("Enter Sandman");
		 * track.setSinger("Metallica");
		 * 
		 * return track;
		 */
		JSONObject obj = new JSONObject();
		try {
			
			String key_name = "Hello JSON";
			int data = 10;
			obj.put(key_name, 10);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return obj; // return JSON object
	}

}

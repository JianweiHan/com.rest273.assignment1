package com.rest273.assignment1;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


@Path("/V1/server/")
public class V1_server {

	JSONArray jsonarray = new JSONArray();

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject getTrackInJSON() throws JSONException {
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

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTrackInJSON(JSONObject obj) {
		/*
		 * String result = "Track saved : " + track; return
		 * Response.status(201).entity(result).build();
		 */
		
		JSONObject respondObj = new JSONObject();
		jsonarray.put(obj);
		try {
			for (int i = 0; i <jsonarray.length(); i++) {
				JSONObject row = jsonarray.getJSONObject(i);
				if (row.getString("clientID") == obj.getString("clientID")) {
					respondObj = row;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(201).entity(respondObj).build();
	}
}

package com.feeds.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;

@Path("/feed")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FeedsRestResource {
	
	@POST
	@Path("/{topic}")
	public void postMessage(@PathParam("topic") String topic, String message)
	{
		try {
			System.out.println("About to broadcast message " + message + "to topic " + topic); 
			Broadcaster b = BroadcasterFactory.getDefault().lookup(topic);
			b.broadcast(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GET
	public String sayHello()
	{
		return "test";
	}

}

package com.feeds.atmosphere.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.atmosphere.annotation.Suspend;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterFactory;
import org.atmosphere.jersey.Broadcastable;

import com.feeds.atmosphere.filters.SampleBroadcastFilter;
import com.feeds.atmosphere.listeners.EventsLogger;
import com.sun.jersey.spi.resource.Singleton;

@Path("/feed")
@Singleton
public class FeedsAtmosphereResource {
	
	@Suspend(contentType = "application/json",  listeners = { EventsLogger.class })
	@GET
	@Path("/{topic}")
	public Broadcastable suspendRequests(@PathParam("topic") String topic) {
		Broadcaster broadcaster = BroadcasterFactory.getDefault().lookup(topic,	true);
		broadcaster.getBroadcasterConfig().addFilter(new SampleBroadcastFilter());
		return new Broadcastable("", broadcaster);
	}
}

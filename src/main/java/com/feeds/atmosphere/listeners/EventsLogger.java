package com.feeds.atmosphere.listeners;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.atmosphere.cpr.AtmosphereRequest;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.cpr.AtmosphereResourceEventListener;
import org.atmosphere.cpr.AtmosphereResponse;

public class EventsLogger implements AtmosphereResourceEventListener {
	public EventsLogger() {
	}

	@Override
	public void onPreSuspend(AtmosphereResourceEvent event) {
		AtmosphereRequest request = event.getResource().getRequest();
		AtmosphereResponse response = event.getResource().getResponse();
		String token = request.getHeader("token");
		System.out.println("Token {}:::::::::: " + token);
		if(null!=token && !token.equalsIgnoreCase("token"))
		{
			try 
			{
				System.out.println("in try block");
				//response.write("responnnnnnnnnnnnnnnnssssssssssse");
				//event.getResource().close();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void onSuspend(final AtmosphereResourceEvent event) {
		StringBuilder builder = new StringBuilder();
		builder.append("request::").append(event.getResource().getRequest()).append(" remoted addr:: ").append(event.getResource().getRequest().getRemoteAddr())
		.append(" port:::").append(event.getResource().getRequest().getRemotePort());
		System.out.println("onSuspend(): {}:{} " + builder.toString());
	}

	public void onResume(AtmosphereResourceEvent event) {
		System.out.println("onResume(): {}:{}" + event.getResource().getRequest().getRemoteAddr() + event.getResource().getRequest().getRemotePort());
	}

	@Override
	public void onDisconnect(final AtmosphereResourceEvent event) {
		try {
			if (event.isCancelled() || event.isClosedByClient()) {
				System.out.println("onDisconnect:: cancelled/closed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Override
	public void onBroadcast(AtmosphereResourceEvent event) {
		System.out.println("onBroadcast(): {}" +  event.getMessage());
	}
	
	@Override
	public void onThrowable(AtmosphereResourceEvent event) {
		System.out.println("onThrowable(): {}" + event);
	}
}

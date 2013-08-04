package com.feeds.atmosphere.interceptors;

import org.atmosphere.cpr.Action;
import org.atmosphere.cpr.AtmosphereConfig;
import org.atmosphere.cpr.AtmosphereInterceptor;
import org.atmosphere.cpr.AtmosphereRequest;
import org.atmosphere.cpr.AtmosphereResource;

public class FeedRequestInterceptor implements AtmosphereInterceptor {

	@Override
	public void configure(AtmosphereConfig config) {
		System.out.println("Configure method.........................");
	}

	@Override
	public Action inspect(AtmosphereResource r) {
		Action ret= null;
		try
		{
			System.out.println("inspect method.........................");
			AtmosphereRequest request = r.getRequest();
			String token = request.getHeader("token");
			System.out.println(token);
			if (null == token || !token.equalsIgnoreCase("token")) {
	           // r.close();
				//ret = Action.CANCELLED;                   
	        } else {
	            //ret = Action.CONTINUE;                   
	        }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public void postInspect(AtmosphereResource r) {
		System.out.println("post inspect handler............");
	}

}

package com.feeds.atmosphere.filters;

import org.atmosphere.cpr.BroadcastFilter;

public class SampleBroadcastFilter implements BroadcastFilter {

	@Override
	public BroadcastAction filter(Object originalMessage, Object message) {
		if(originalMessage instanceof String)
		{
			System.out.println("In Filter class....Original Message " + originalMessage);
			String msgStr = (String) originalMessage;
			if(null!=msgStr && !msgStr.contains("sports"))
			{
				return new BroadcastAction(BroadcastAction.ACTION.CONTINUE, msgStr);
			}
			else
			{
				return new BroadcastAction(BroadcastAction.ACTION.ABORT);
			}
		}
		else
		{
			return new BroadcastAction(BroadcastAction.ACTION.CONTINUE);
		}
	}

}

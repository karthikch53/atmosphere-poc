package com.feeds.rest.client;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;

import com.feeds.model.Message;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class FeedRestResourceClient {
	
	private static final String path = "http://localhost:8080/sample/rest/feed/";
	int messageNo = 1;
	static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	public static void main(String[] args) {
		
		try
		{
			for(int i=1;i<=21;i++)
			{
				if(i%2==0)
				{
					postFeeds("ct",i);
				}
				else
				{
					//postFeeds("sports",i);
				}
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	private static void postFeeds(String topic, int count) throws Exception
	{
		Client client = Client.create();
		String p = path + topic;
		WebResource webResource = client.resource(p);
		Message message = new Message();
		message.setId(topic + "-" + count);
		message.setMessage("Posting a message under the topic -" + topic + count);
		message.setPostedBy("test_user");
		message.setPostedDate(formatter.format(new Date()));
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, new ObjectMapper().writeValueAsString(message));
		System.out.println(response);
	}
}

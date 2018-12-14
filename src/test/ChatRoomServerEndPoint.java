package test;

import java.io.StringWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/ChatRoomServerEndPoint", configurator=ChatRoomServerConfigurator.class)
public class ChatRoomServerEndPoint 
{
	static Set<Session> chatroomUsers=Collections.synchronizedSet(new HashSet<Session>());
	@OnOpen
	public void handleOpen(EndpointConfig endpointConfig,Session userSession)
	{
		userSession.getUserProperties().put("name", endpointConfig.getUserProperties().get("name"));
		chatroomUsers.add(userSession);
	}
	@OnMessage
	public void handleMessage(String message,Session userSession)
	{
		String username=(String) userSession.getUserProperties().get("name");
		if(username!=null)
		{
			chatroomUsers.stream().forEach(x -> {
			    try
			    {
			    	x.getBasicRemote().sendText(buildJsonData(username,message));
			    	
			    }
			    catch(Exception e)
			    {
			    	e.printStackTrace();
			    }
	       	});
     	}
		
     }
	
	@OnClose
	public void handleClose(Session userSession)
	{
		chatroomUsers.remove(userSession);
	}
	@OnError
	public void handleError(Throwable t)
	{
		
	}
	private String buildJsonData(String username,String message)
	{
		JsonObject jo=Json.createObjectBuilder().add("message", username+":"+message).build();
		StringWriter sw=new StringWriter();
		try ( JsonWriter jw=Json.createWriter(sw)) 
		{
			jw.write(jo);
		}
		return sw.toString();
	}

}

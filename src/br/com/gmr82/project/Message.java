package br.com.gmr82.project;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable
{
	private static final long serialVersionUID = -1743765117837430179L;
	private User emitter;
	private User receiver;
	private String body;
	
	
	Message (User emitter, User receiver, String body)
	{
		this.emitter = emitter;
		this.receiver = receiver;
		this.body = body;
	}

	User getEmitter ()
	{
		return emitter;
	}

	User getReceiver ()
	{
		return receiver;
	}

	String getBody ()
	{
		return body;
	}
	
	boolean isRelated (User user)
	{
		return user.equals(emitter) || user.equals(receiver);
	}

	
	@Override
	public String toString ()
	{
		return "[@" + getEmitter().getUsername() +
			   "→@" + getReceiver().getUsername() +
			   "]{" + getBody() + "}";
	}
	
	static void showMessagesIn (ArrayList <Message> messages)
	{
		messages.forEach(message -> System.out.println("    " + message.toString()));
	}
	
}
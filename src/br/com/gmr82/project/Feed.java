package br.com.gmr82.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class Feed implements Serializable
{
	private static final long serialVersionUID = -6750577679227507930L;
	private User owner;
	private ArrayList <Message> messages;
	//ArrayList <New> news;
	
	
	Feed (User owner)
	{
		this.owner = owner;
		this.messages = new ArrayList<Message>();
	}
	
	void visualize ()
	{
		System.out.println("    # Feed de @" + owner.getUsername() + " #");
		System.out.println("    Notícias:\n?");
		
		System.out.println("    Mensagens:");
		Message.showMessagesIn(messages);
	}
	
	
	void receiveMessage (Message message)
	{
		if (messages.contains(message))
			return;
		messages.add(message);
	}
	
	
	void removeMessage (Message message)
	{
		messages.remove(message);
	}
	
	
	static ArrayList <Message> draft (ArrayList <Message> messages, User user)
	{
		ArrayList <Message> filteredMessages = (ArrayList <Message>) messages.stream()
				.filter(message-> message.isRelated(user))
				.collect(Collectors.toList());
		return filteredMessages;
	}
	
	
	
}
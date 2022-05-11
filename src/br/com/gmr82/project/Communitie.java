package br.com.gmr82.project; 

import java.io.Serializable;
import java.util.ArrayList;


public class Communitie extends Entity implements Serializable
{
	private static final long serialVersionUID = 4998339587315452403L;
	private User owner;
	ArrayList <User> members;
	
	Communitie (String name, User owner)
	{
		super(name);
		this.setOwner(owner);
		this.members = new ArrayList<User>();
	}

	public User getOwner ()
	{
		return owner;
	}

	public void setOwner (User owner)
	{
		this.owner = owner;
	}

	
	
}
package br.com.gmr82.project;

import java.io.Serializable;
import java.util.ArrayList;


public class User implements Serializable
{	
	private String id;
	private String password;
	
	private Profile profile;
	
	private ArrayList <String> friends;
	private ArrayList <String> communities;
	private Feed feed;

	User (String username)
	{
		this.id = username;
	}
	
	String getUsername ()
	{
		return this.id;
	}

	void setUsername (String username)
	{
		this.id = username;
	}

	void setPassword (String password)
	{
		this.password = password;			
	}
	
	boolean checkPassword (String password)
	{
		return (this.password.equals(password));			
	}
	
	boolean addTo (ArrayList <User> users)
	{
		if (users.contains(this))
		{
			System.out.println("  Username indisponível!");
			return false;
		}
		return users.add(this);
	}
	
	@Override
	public boolean equals (Object object)
	{
		if (!(object instanceof User))
			return false;

		final User other = (User) object;
		return this.id.equals(other.id);
	}

	@Override
	public String toString ()
	{
		return "@" + this.id + " [Senha: " + this.password + ", ...]";
	}


	
}
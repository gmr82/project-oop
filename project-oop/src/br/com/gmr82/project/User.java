package br.com.gmr82.project;

import java.util.ArrayList;

public class User
{
	
	String id;
	String password;
	Profile profile;
	
	ArrayList <String> friends;
	ArrayList <String> communities;
	Feed feed;

	User (String username)
	{
		this.id = username;
	}

	void edit ()
	{
		return;
	}

	boolean addTo (ArrayList <User> users)
	{
		if (users.contains(this))
		{
			System.out.println("Username indisponível!");
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

}
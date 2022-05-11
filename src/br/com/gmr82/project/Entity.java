package br.com.gmr82.project;

import java.io.Serializable;


public /**/abstract/**/ class Entity implements Serializable
{
	
	private static final long serialVersionUID = -1340302184916324599L;
	protected String id;
	protected Profile profile;
	protected Feed feed;
	
	
	Entity (String id)
	{
		this.id = id;
		this.profile = new Profile(id);
		this.feed = new Feed(this);
	}

	public String getId ()
	{
		return id;		
	}
	
		public Profile getProfile ()
	{
		return profile;
	}

	public Feed getFeed ()
	{
		return feed;
	}


}
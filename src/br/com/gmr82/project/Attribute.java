package br.com.gmr82.project;

import java.io.Serializable;
import java.util.ArrayList;

public class Attribute implements Serializable
{
	private String key;
	private String value;
	
	Attribute (String key)
	{
		this.key = key;
	}
	
	public String getKey ()
	{
		return key;
	}
	
	public void setKey (String key)
	{
		this.key = key;
	}
	
	public String getValue ()
	{
		return value;
	}
	
	public void setValue (String value)
	{
		this.value = value;
	}


	public boolean isConstained (ArrayList <Attribute> attributes)
	{
		return attributes.contains(this);
	}
	
	boolean addTo (ArrayList <Attribute> attributes)
	{
		return attributes.add(this);
	}
	
	@Override
	public boolean equals (Object object)
	{
		if (!(object instanceof Attribute))
			return false;

		final Attribute other = (Attribute) object;
		return this.key.equals(other.key);
	}
	
	
}
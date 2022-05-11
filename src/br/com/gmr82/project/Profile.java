package br.com.gmr82.project;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Profile implements Serializable
{
	private static final long serialVersionUID = -5167574187842926423L;
	private ArrayList <Attribute> attributes;
	
	
	Profile (String name)
	{
		this.attributes = new ArrayList <Attribute> ();
		Attribute attribute = new Attribute("Nome");
		attribute.setValue(name);
		attributes.add(attribute);
	}

	boolean access ()
	{
		System.out.println("    [Perfil]");
		System.out.print("    Selecione:" +
				"\n      1) Mostrar atributos;" +
				"\n      2) Adicionar atributo;" +
				"\n      3) Alterar atributo;" +
				"\n      4) Remover atributo;" +
				"\n      0) Voltar." +
				"\n      >> ");
		try 
		{
			switch(Integer.parseInt(Main.read.nextLine()))
			{
				case 0: return false;
				case 1: show(); return true;
				case 2: createAttribute(); return true;
				case 3: changeAttribute(); return true;
				case 4: deleteAttribute(); return true;
				default: System.out.println("      Opção inexistente!"); return true;
			}
		}
		catch (Exception exception)
		{
			System.out.println("      Valor inválido!");
			return true;
		}
	}	

	@Override
	public String toString ()
	{
		if (attributes.isEmpty())
		{
			return "{}";
		}
		
		String text = "{";
		
		Iterator <Attribute> iterator = attributes.iterator();

		Attribute attribute;
	    while (iterator.hasNext())
	    {
	    	attribute = iterator.next();
	    	text += attribute.getKey() + ": " + attribute.getValue() + ", ";
	    }
		return text.substring(0, text.length() - 2) + "}";
	}

	private void createAttribute ()
	{
		System.out.print("        Atributo: ");
		Attribute attribute = new Attribute(Main.read.nextLine());
		System.out.print("        Valor: ");
		attribute.setValue(Main.read.nextLine());
		attributes.add(attribute);
		System.out.println("        Atributo adicionado!");
	}
	
	private void changeAttribute ()
	{
		System.out.print("        Atributo: ");
		Attribute attribute = searchAttribute(Main.read.nextLine());
		if (attribute == null)
		{
			System.out.println("        Atributo inexistente!");
			return;
		}
		System.out.print("        Novo valor: ");
		attribute.setValue(Main.read.nextLine());
		System.out.println("        Atributo alterado!");
	}
	
	private Attribute searchAttribute (String key)
	{
		Iterator <Attribute> iterator = attributes.iterator();
		Attribute attribute;
	    
	    while (iterator.hasNext())
	    {
	    	attribute = iterator.next();
	    	if (key.equals(attribute.getKey())) return attribute;
	    }
		return null;
	}
	
	private void deleteAttribute ()
	{
		System.out.print("        Atributo: ");
		Attribute attribute = searchAttribute(Main.read.nextLine());
		if (attribute == null)
		{
			System.out.println("        Atributo inexistente!");
			return;
		}
		
		Iterator <Attribute> iterator = attributes.iterator();
	    
	    while (iterator.hasNext())
	    {
	    	if (attribute.equals(iterator.next())) 
	    	{
	    		iterator.remove();
	    		System.out.println("        Atributo removido!");
	    		return;
	    	}
	    }
	}

	private void show ()
	{
		System.out.println("      " + this.toString());
	}
	
	
}
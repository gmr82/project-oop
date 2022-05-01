package br.com.gmr82.project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main
{
	static Scanner read;
	static ArrayList <User> users = new ArrayList<User>();
	ArrayList <Communitie> communities;

	public static void main (String[] args)
	{	
		
		System.out.println("#------------TESTE------------#");
		read = new Scanner(System.in);
		
		while(menu());
		
		read.close();
		System.out.println("-------------------------------");

	}

	private static boolean menu ()
	{
		System.out.print("Selecione:" +
						"\n1) Criar conta de usuário;" +
						"\n2) Listar usuários;" +
						"\n3) Pesquisar conta de usuário;" + 
						"\n4) Editar conta de usuário;" +
						"\n5) Remover conta de usuário;" +
						"\n0) Sair." +
						"\n>> ");
		
		try 
		{
			switch(Integer.parseInt(read.nextLine()))
			{
				case 0: return false;
				case 1: createUserAccount(); return true;
				case 2: listAllUsers(); return true;
				case 3: searchUser(); return true;
				case 4: editUserAccount(); return true;
				case 5: deleteUserAccount(); return true;
				default: System.out.println("Opção inexistente!"); return true;
			}
		}
		catch (Exception exception)
		{
			System.out.println("Valor inválido!");
			return true;
		}
	}

	
	private static void createUserAccount ()
	{
		System.out.print("# Criar conta de usuário #" +
					   "\n\tUsername: ");
		User user = new User(read.nextLine());
		user.addTo(users);
	}
	
	private static void listAllUsers ()
	{
		System.out.println("# Listar usuários #");
		
	    Iterator <User> iterator = users.iterator();
	    User user;
	    
	    while (iterator.hasNext())
	    {
	    	user = iterator.next();
	    	System.out.println("\t[" + user.id + "]");
	        //System.out.println("\t[" + user.toString() + "]");
	    }
	}
	
	private static void searchUser ()
	{
		System.out.print("# Pesquisar conta de usuário #" +
			   "\n\tUsername: ");
		User user = searchUser(read.nextLine());
		if (user == null)
		{
			System.out.println("Usuário não encontrado!");
			return;
		}
		System.out.println("\t[" + user.id + "]");
	}
	
	private static User searchUser (String username)
	{
		Iterator <User> iterator = users.iterator();
	    User user;
	    
	    while (iterator.hasNext())
	    {
	    	user = iterator.next();
	    	if (username.equals(user.id)) return user;
	    }
		return null;
	}
	
	private static void editUserAccount ()
	{
		System.out.print("# Editar conta de usuário #" +
				   "\n\tUsername: ");
		User user = searchUser(read.nextLine());
		if (user == null)
		{
			System.out.println("Usuário não encontrado!");
			return;
		}
		editUserAccount(user);
	}
	
	private static boolean editUserAccount (User user)
	{
		return false;
	}
		
	private static void deleteUserAccount ()
	{
		System.out.print("# Remover conta de usuário #" +
				   "\n\tUsername: ");
		User user = searchUser(read.nextLine());
		if (user == null)
		{
			System.out.println("Usuário não encontrado!");
			return;
		}
		deleteUserAccount(user);
		System.out.println("Conta de usuário removida!");
	}
	
	private static boolean deleteUserAccount (User user)
	{
		Iterator <User> iterator = users.iterator();
	    	    
	    while (iterator.hasNext())
	    {
	    	if (user.equals(iterator.next())) 
	    	{
	    		iterator.remove();
	    		return true;
	    	}
	    }
		return false;
	}


}
package br.com.gmr82.project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Main
{
	private String filePath = "project-oop/others/users";
	private FileInputStream fis;
	private ObjectInputStream ois;
	private FileOutputStream fos;
	private ObjectOutputStream oos;
	
	private Scanner read;
	
	private ArrayList <User> users;
	
	
	public static void main (String[] args)
	{
		System.out.println("#-------------------TESTE-------------------#");
		new Main().doMain(args); // turn the static method into an instance of Main
		System.out.println("#-------------------------------------------#");
	}

	
	@SuppressWarnings("unchecked")
	private void doMain (String[] args)
	{	
		try
		{
			if (new File(filePath).exists())
			{
				fis = new FileInputStream(filePath);
				ois = new ObjectInputStream(fis);
				users = (ArrayList<User>) ois.readObject();
				ois.close();
				fis.close();
			}
			else
			{
				users = new ArrayList<User>();
			}

		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		read = new Scanner(System.in);
		while(menu());
		read.close();
		
		
		try
		{
			fos = new FileOutputStream(filePath);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(users);
			oos.close();
			fos.close();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
	}

	boolean menu ()
	{
		System.out.print("Selecione:" +
						"\n  1) Criar conta de usuário;" +
						"\n  2) Listar usuários;" +
						"\n  3) Pesquisar conta de usuário;" + 
						"\n  4) Editar conta de usuário;" +
						"\n  5) Remover conta de usuário;" +
						"\n  0) Sair." +
						"\n  >> ");
		
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
				default: System.out.println("  Opção inexistente!"); return true;
			}
		}
		catch (Exception exception)
		{
			System.out.println("  Valor inválido!");
			return true;
		}
	}

	
	private void createUserAccount ()
	{
		System.out.print("# Criar conta de usuário #" +
					   "\n  Username: ");
		User user = new User(read.nextLine());
		System.out.print("  Senha: ");
		user.setPassword(read.nextLine());
		user.addTo(users);
	}
	
	private void listAllUsers ()
	{
		System.out.println("# Listar usuários #");
		System.out.println("                    Total: " + users.size() + " usuário(s).");
	    Iterator <User> iterator = users.iterator();
	    User user;
	    
	    while (iterator.hasNext())
	    {
	    	user = iterator.next();
	    	System.out.println("  " + user.toString());
	    }
	}
	
	private void searchUser ()
	{
		System.out.print("# Pesquisar conta de usuário #" +
			   "\n  Username: ");
		User user = searchUser(read.nextLine());
		if (user == null)
		{
			System.out.println("  Usuário não encontrado!");
			return;
		}
		System.out.println("  " + user.toString());
	}
	
	private User searchUser (String username)
	{
		Iterator <User> iterator = users.iterator();
	    User user;
	    
	    while (iterator.hasNext())
	    {
	    	user = iterator.next();
	    	if (username.equals(user.getUsername())) return user;
	    }
		return null;
	}
	
	private void editUserAccount ()
	{
		System.out.print("# Editar conta de usuário #" +
				   "\n  Username: ");
		User user = searchUser(read.nextLine());
		if (user == null)
		{
			System.out.println("  Usuário não encontrado!");
			return;
		}
		
		System.out.print("  Senha de " + user.getUsername() + ": ");	
		if (!user.checkPassword(read.nextLine()))
		{
			System.out.println("  Senha inválida!");
			return;
		}
		
		boolean control = true;
		while (control)
		{
			System.out.print("  Selecione:" +
					"\n    1) Editar username;" +
					"\n    2) Alterar senha;" +
					"\n    3) Editar perfil;" +
					"\n    0) Sair." +
					"\n    >> ");
			try 
			{
				switch(Integer.parseInt(read.nextLine()))
				{
					case 0: control = false; break;
					case 1:
						System.out.print("    Novo username: ");
						String newUsername = read.nextLine();
						if (users.contains(searchUser(newUsername)))
						{
							System.out.println("    Username indisponível!");
							break;
						}
						user.setUsername(newUsername);
						System.out.println("    Username alterado!");
						break;
					case 2:
						System.out.print("    Nova senha: ");
						user.setPassword(read.nextLine());
						System.out.println("    Senha alterada!");
						break;
					case 3:
						// editar perfil
						break;
					default: System.out.println("    Opção inexistente!"); break;
				}
			}
			catch (Exception exception)
			{
				System.out.println("    Valor inválido!");
			}
		}

	}
			
	private void deleteUserAccount ()
	{
		System.out.print("# Remover conta de usuário #" +
				   "\n  Username: ");
		User user = searchUser(read.nextLine());
		if (user == null)
		{
			System.out.println("  Usuário não encontrado!");
			return;
		}
		
		System.out.print("  Senha de " + user.getUsername() + ": ");	
		if (!user.checkPassword(read.nextLine()))
		{
			System.out.println("  Senha inválida!");
			return;
		}

		deleteUserAccount(user);
		System.out.println("  Conta de usuário removida!");
	}
	
	private boolean deleteUserAccount (User user)
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
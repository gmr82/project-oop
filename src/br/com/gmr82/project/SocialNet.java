package br.com.gmr82.project;

import java.io.Serializable;
import java.util.ArrayList;


class SocialNet implements Serializable
{
	
	private static final long serialVersionUID = -6331624503758271469L;
	private ArrayList <User> users;
	private ArrayList <Message> messages;
	
	
	SocialNet ()
	{
		this.users = new ArrayList<User>();
		this.messages = new ArrayList<Message>();
	}
	
	boolean menu ()
	{
		System.out.println("[Rede Social]");
		System.out.print("Selecione:" +
						"\n  1) Acessar conta de usuário;" +
						"\n  2) Listar usuários;" +
						"\n  3) Criar conta de usuário;" +
						"\n  4) Pesquisar conta de usuário;" + 
						"\n  5) Listar mensagens;" +
						"\n  0) Sair." +
						"\n  >> ");
		
		try 
		{
			switch(Integer.parseInt(Main.read.nextLine()))
			{
				case 0: exit(); return false;
				case 1: accessUserAccount(); return true;
				case 2: listUsers(); return true;
				case 3: createUserAccount(); return true;
				case 4: searchUser(); return true;
				case 5: listMessages(); return true;
				default: System.out.println("  Opção inexistente!"); return true;
			}
		}
		catch (Exception exception)
		{
			System.out.println("  Valor inválido!");
			return true;
		}
	}
	
	private void exit ()
	{
		//System.out.println("Saindo...");
	}

	private void createUserAccount ()
	{
		System.out.print("# Criar conta de usuário #" +
					   "\n  Username: ");
		String username = Main.read.nextLine();
		
		if (User.searchUser(username, users) != null)
		{
			System.out.println("  Username indisponível!");
			return;
		}
		
		User user = new User(username, "");
		
		users.add(user);
		
		System.out.println("  Conta de usuário criada!");
		
		while(user.access(users, messages));
	}
	
	private void listUsers ()
	{
		System.out.println("# Listar usuários #" +
						 "\n                    Total: " + users.size() + " usuário(s).");
		User.showUsersIn(users);
		
		/*
		Iterator <User> iterator = users.iterator();
	    User user;
	    
	    while (iterator.hasNext())
	    {
	    	user = iterator.next();
	    	System.out.println("  @" + user.getUsername());
	    }
	    */
	}
	
	private void listMessages ()
	{
		System.out.println("# Listar mensagens #" + 
						 "\n                    Total: " + messages.size() + " mensagem(ns).");
		
		Message.showMessagesIn(messages);
	}
    
	private void searchUser ()
	{
		System.out.print("# Pesquisar conta de usuário #" +
			   "\n  Username: ");
		User user = searchUser(Main.read.nextLine());
		if (user == null)
		{
			System.out.println("  Usuário não encontrado!");
			return;
		}
		System.out.println("  " + user.toString());
	}

	private User searchUser (String username)
	{
		return User.searchUser(username, users);
	}
	
	private void accessUserAccount ()
	{
		System.out.print("# Acessar conta de usuário #" +
				   "\n  Username: ");
		User user = User.searchUser(Main.read.nextLine(), users);
		if (user == null)
		{
			System.out.println("  Usuário não encontrado!");
			return;
		}
		
		System.out.print("  Senha de " + user.getUsername() + ": ");	
		if (!user.checkPassword(Main.read.nextLine()))
		{
			System.out.println("  Senha inválida!");
			return;
		}
		
		while(user.access(users, messages));
	}


}
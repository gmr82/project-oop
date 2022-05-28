package br.com.gmr82.project;

import java.util.ArrayList;
import java.util.Iterator;


public class User extends Entity
{	
	//private static final long serialVersionUID = -7376693414257860736L;
	private String password;
	private ArrayList <User> friends;
	private ArrayList <User> invitations;
	//private ArrayList <Communitie> communities;

	
	User (String username, String password)
	{
		super(username);
		this.password = password;
		this.friends = new ArrayList<User>();
		this.invitations = new ArrayList<User>();
		this.feed = new Feed(this);
	}

	boolean access (ArrayList <User> users, ArrayList <Message> messages)
	{
		System.out.println("  [Usuário: " + this.getId() + "]");
		System.out.print("  Selecione:" +
					   "\n    1) Mostrar;" +			
					   "\n    2) Vizualizar \"feed\";" +
					   "\n    3) Enviar mensagem;" +
					   "\n    4) Listar amigos;" +
					   "\n    5) Enviar convite;" +
					   "\n    6) Responder convites;" + (invitations.size() > 0 ? "*": "") +
					   "\n    7) Acessar perfil;" +
					   "\n    8) Alterar username;" +
					   "\n    9) Alterar senha;" +
					   "\n    10) Remover conta;" +
					   "\n    0) Sair." +
					   "\n    >> ");
		try 
		{
			switch(Integer.parseInt(Main.read.nextLine()))
			{
				case 0: return false;
				case 1: show (); return true;
				case 2: feed.visualize(); return true;
				case 3: sendMessage(users, messages); return true;
				case 4: listFriends (); return true;
				case 5: sendInvitation(users); return true;
				case 6: answerInvitations(); return true;
				case 7: accessProfile(); return true;
				case 8: changeUsername(users); return true;
				case 9: changePassword(); return false;	
				case 10: remove(users, messages); return false;
				default: System.out.println("    Opção inexistente!"); return true;
			}
		}
		catch (Exception exception)
		{
				System.out.println("    Valor inválido!");
		}
		return true;
	}

	boolean checkPassword (String password)
	{
		return (this.password.equals(password));			
	}
	
	ArrayList <User> getFriends ()
	{
		return friends;
	}
	
	static User searchUser (String username, ArrayList <User> users)
	{
		Iterator <User> iterator = users.iterator();
	    User user;
	    
	    while (iterator.hasNext())
	    {
	    	user = iterator.next();
	    	if (username.equals(user.getId())) return user;
	    }
		return null;
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
		return "@" + id +
		" Amigos[" + friends.size() + "] " +
					 profile.toString();
	}
	
	boolean addTo (ArrayList <User> users)
	{
		return users.add(this);
	}

	private void changeUsername (ArrayList <User> users)
	{
		System.out.print("    Novo username: ");
		String username = Main.read.nextLine();
		if (searchUser(username, users) != null)
		{
			System.out.println("    Username indisponível!");
			return;
		}
		this.id = username;
		System.out.println("    Username alterado!");
	}
	
	private void changePassword ()
	{
		System.out.print("    Nova senha: ");
		this.password = Main.read.nextLine();
		System.out.println("    Senha alterada!");
	}
	
	private void accessProfile ()
	{
		while(this.profile.access());
	}
	
	void remove (ArrayList <User> users, ArrayList <Message> messages)
	{
		System.out.print("    Confirme digitando \"" + this.getId() + "\" novamente: ");
		if (!this.getId().equals(Main.read.nextLine()))
		{
			System.out.println("    Conta de usuário não removida!");
			return;
		}
		users.forEach(user ->
		{
			user.removeFriend(this);
			user.removeInvitation(this);
		});

		Iterator <Message> iterator = messages.iterator();
		
		while (iterator.hasNext())
		{
			Message message = iterator.next();
			if (message.isRelated(this))
			{
				users.forEach(user -> { user.getFeed().removeMessage(message); });
				iterator.remove();
			}
				
		}
		
		users.remove(this);
		System.out.println("    Conta de usuário removida!");
	}
	
	private void sendInvitation (ArrayList<User> users)
	{
		System.out.print("    Username do convidado: ");
		String username = Main.read.nextLine();
		if (username.equals(this.getId()))
		{
			System.out.println("    Operação inválida!");
			return;
		}
		
		User user = searchUser(username, users);
		if (user == null)
		{
			System.out.println("    Usuário não encontrado!");
			return;
		}
		if (friends.contains(user))
		{
			System.out.println("    @" + username + " já é seu amigo!");
			return;
		}
		user.receiveInvitation(this);
		System.out.println("    Convite enviado!");
	}
	
	void receiveInvitation (User user)
	{
		if (invitations.contains(user))
			return;
		invitations.add(user);
	}
	
	void addFriend (User user)
	{
		if (!friends.contains(user))
			friends.add(user);
	}
	
	void removeFriend (User user)
	{
		friends.remove(user);
	}
	
	void removeInvitation (User user)
	{
		invitations.remove(user);
	}
	
	void answerInvitations ()
	{
		if (invitations.size() == 0)
		{
			System.out.println("    Sem convites por ora!");
			return;
		}
	    Iterator <User> iterator = invitations.iterator();
	    User user;
	    char answer;
	    while (iterator.hasNext())
	    {
	    	user = iterator.next();
	    	System.out.print("    Aceitar convite de " + user.getId() + "? >> ");
    		answer = Main.read.nextLine().toLowerCase().charAt(0);
    		if (answer == 's')
    		{
    			this.addFriend(user);
    			user.addFriend(this);
    			System.out.println("    Convite aceito!");
    		}
    		else
    		{
    			System.out.println("    Convite rejeitado!");
    		}
    		
    		iterator.remove();
	    }
	}
	
	static void showUsersIn (ArrayList <User> users)
	{
		users.forEach(user -> System.out.println("  @" + user.getId()));
	}
	
	private void sendMessage (ArrayList <User> users, ArrayList <Message> messages)
	{
		System.out.print("    Destinatário: ");
		User user = searchUser(Main.read.nextLine(), users);
		if (user == null)
		{
			System.out.println("    Usuário não encontrado!");
			return;
		}
		System.out.print("    Mensagem: ");
		Message message = new Message(this, user, Main.read.nextLine());
		
		messages.add(message);
		user.getFeed().receiveMessage(message);
		System.out.println("    Mensagem enviada!");
	}

	private void listFriends ()
	{
		System.out.println("                      Total: " + friends.size() + " amigo(s).");
	    Iterator <User> iterator = friends.iterator();
	    User user;
	    
	    while (iterator.hasNext())
	    {
	    	user = iterator.next();
	    	System.out.println("    @" + user.getId());
	    }
	}

	private void show ()
	{
		System.out.println("    " + this.toString());
	}

	
}
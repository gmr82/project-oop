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
		this.profile = new Profile(username);
	}
	
	String getUsername ()
	{
		return id;
	}

	void setUsername (String username)
	{
		this.id = username;
	}

	Profile getProfile ()
	{
		return profile;
	}

	void setProfile (Profile profile)
	{
		this.profile = profile;
	}

	boolean checkPassword (String password)
	{
		return (this.password.equals(password));			
	}
	
	void setPassword (String password)
	{
		this.password = password;			
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
		return "@" + id;
	}

	
	boolean addTo (ArrayList <User> users)
	{
		return users.add(this);
	}

	boolean edit ()
	{
		System.out.print("  Selecione:                            [Edição de usuário: @" + this.getUsername() + "]" +
					   "\n    1) Alterar username;" +
					   "\n    2) Alterar senha;" +
					   "\n    3) Editar perfil;" +
					   "\n    4) Remover conta;" +
					   "\n    0) Sair." +
					   "\n    >> ");
		try 
		{
			switch(Integer.parseInt(Main.read.nextLine()))
			{
				case 0: return false;
				case 1: changeUsername(); return true;
				case 2: changePassword(); return true;	
				case 3: editProfile(); return true;
				case 4: remove(); return false;
				default: System.out.println("    Opção inexistente!"); return true;
			}
		}
		catch (Exception exception)
		{
				System.out.println("    Valor inválido!");
		}
		return true;
	}
	
	void changeUsername ()
	{
		System.out.print("    Novo username: ");
		String username = Main.read.nextLine();
		if (Main.searchUser(username) != null)
		{
			System.out.println("    Username indisponível!");
			return;
		}
		this.setUsername(username);
		System.out.println("    Username alterado!");
	}
	
	void changePassword ()
	{
		System.out.print("    Nova senha: ");
		this.setPassword(Main.read.nextLine());
		System.out.println("    Senha alterada!");
	}
	
	void editProfile ()
	{
		Profile profile = this.getProfile();
		while(profile.edit());
	}
	
	void remove ()
	{
		System.out.print("    Confirme digitando \"" + this.getUsername() + "\" novamente: ");
		if (this.getUsername().equals(Main.read.nextLine()))
		{
			Main.deleteUserAccount(this);
			System.out.println("    Conta de usuário removida!");
		}
	}
	
	
}
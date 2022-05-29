package br.com.gmr82.project;

import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Main
{
	private String filePath = "others/bkp";
	private FileInputStream fis;
	private ObjectInputStream ois;
	private FileOutputStream fos;
	private ObjectOutputStream oos;

	static Scanner read;
	private static SocialNet socialNet;
	
	public static void main (String[] args)
	{
		new Main().doMain(args); // turn the static method into an instance of Main
	}
	
	private void doMain (String[] args)
	{	
		try
		{
			if (new File(filePath).exists())
			{
				System.out.println("Lendo...");
				fis = new FileInputStream(filePath);
				ois = new ObjectInputStream(fis);
				socialNet = (SocialNet) ois.readObject();
				ois.close();
				fis.close();
			}
			else
			{
				System.out.println("Criando...");
				socialNet = new SocialNet();
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
		
		read = new Scanner(System.in);
		while(socialNet.menu());
		read.close();
		
		try
		{
			System.out.println("Escrevendo...");
			fos = new FileOutputStream(filePath);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(socialNet);
			oos.close();
			fos.close();
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}	
	}
	
	
}
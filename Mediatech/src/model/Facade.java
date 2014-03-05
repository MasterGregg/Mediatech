package model;

public class Facade 
{	
	private User user;
	
	public Facade()
	{
		user = null;
	}
	
	public boolean connection(String login, String pwd)
	{
		// REQ SQL ET RECUPERATION ID
		return true;
	}
	
	public boolean deconnection()
	{
		return true;
	}
	
	public boolean launchAction(Action a)
	{
		return true;
	}
	
	public boolean isConnected(){return (user != null);}
	
	public static void Main(String [] args)
	{
		Facade f = new Facade();
		
	}
}

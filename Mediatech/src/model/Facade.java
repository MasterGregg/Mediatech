package model;

public class Facade implements IFacade
{	
	private SQLManager connection;
	
	public Facade()
	{
		connection = new SQLManager();
	}
	
	public boolean userExist(String login, String pwd)
	{
		return (connection.userID(login, pwd) != -1);
	}
	
	public int userID(String login, String pwd)
	{
		return connection.userID(login, pwd);
	}
	
	public boolean userCanLaunchAction(int userID, Action a)
	{
		return connection.userCanLaunch(userID, a.getName());
	}
}

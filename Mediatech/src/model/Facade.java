package model;

import java.io.File;
import java.util.HashMap;

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
	
	public HashMap<Integer, String> listMedias()
	{
		return connection.listMedias();
	}
	
	public boolean getMediaOnDisk(Media m)
	{
		if(!m.isOnDisk())
		{
			File f= connection.getRessourceFile(m.getID());
			if(f != null)
			{
				m.setFile(f);
				return true;
			}
		}
		
		return false;
	}
	
	public void executeAction(Action a)
	{
		a.start(this.connection);
	}
}

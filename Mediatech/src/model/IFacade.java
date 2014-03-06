package model;

import java.io.File;
import java.util.HashMap;

public interface IFacade 
{
	public boolean userExist(String login, String pwd);	
	public int userID(String login, String pwd);
	public boolean userCanLaunchAction(int userID, Action a);
	public HashMap<Integer, String> listMedias();
	public boolean getMediaOnDisk(Media m);
	public void executeAction(Action a);
}

package model;

public interface IFacade 
{
	public boolean userExist(String login, String pwd);	
	public int userID(String login, String pwd);
	public boolean userCanLaunchAction(int userID, Action a);

}

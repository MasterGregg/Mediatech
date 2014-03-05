package model;

public abstract class Action {
	private String name;
	
	public Action(String a)
	{
		this.name = a;
	}
	
	public String getName()
	{
		return name;
	}
	
	public abstract void start();
}

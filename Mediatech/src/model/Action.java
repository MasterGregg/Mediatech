package model;

public abstract class Action {
	private String name;
	
	public Action(String a)
	{
		this.name = a;
	}
	
	public abstract void start();
}

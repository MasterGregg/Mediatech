package model;

public abstract class Action {
	protected String name;
	
	public Action(String a)
	{
		this.name = a;
	}
	
	public String getName()
	{
		return name;
	}
	
	protected abstract void start(SQLManager connector);
}

package model;

public abstract class Media 
{
	private String name;
	
	public Media(String name)
	{
		this.name = name;
	}
	
	public String toString()
	{
		return "I am a Media. My name is "+name;
	}
}

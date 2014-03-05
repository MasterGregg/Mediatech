package model;

public class Document extends Media {

	public Document(String name) 
	{
		super(name);
	}
	
	public String toString()
	{
		return super.toString()+" and my type is Document";
	}
}

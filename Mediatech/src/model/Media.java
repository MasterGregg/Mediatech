package model;

import java.io.File;

public abstract class Media 
{
	private String name;
	private int id;
	protected File f;
	
	public Media(int id, String name)
	{
		this.id = id;
		f = null;
		this.name = name;
	}
	
	public Media(File f)
	{
		this.id = -1;
		this.name = f.getName();
		this.f = f;
	}
	
	protected File getLocalFile(){return f;}
	public int getID(){return id;}
	public String getName(){return name;}
	protected void setID(int id) {this.id = id;}
	
	public String toString()
	{
		return "I am a Media. My name is "+name;
	}
	
	protected void setFile(File f){this.f = f;}
	
	public boolean isOnDisk() { return (f != null);}
	
	public abstract void print();
}

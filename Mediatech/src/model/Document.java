package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Document extends Media {

	public Document(int id, String name)
	{
		super(id, name);
	}
	
	public Document(File f)
	{
		super(f);
	}
	
	public String toString()
	{
		return super.toString()+" and my type is Document";
	}

	@Override
	public void print() 
	{
		try 
		{
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String r;
			while((r = reader.readLine()) != null)
			{
				System.out.println(r);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

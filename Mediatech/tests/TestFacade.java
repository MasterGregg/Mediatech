package testUnit;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import model.AddRessource;
import model.Document;
import model.Facade;
import model.IFacade;
import model.RemoveRessource;

public class TestFacade 
{
	private IFacade facade;
	private final String USER_NAME="toto", PWD="TOTO";
	private HashMap<Integer, String>  medias;

	@Before
	public void setUp() throws Exception 
	{
		facade = new Facade();
	}

	@Test
	public void testUser() 
	{
		/* Utilisateur présent en base */
		assertTrue(facade.userExist(USER_NAME, PWD));
		
		/* Utilisateur non présent en base */
		assertFalse(facade.userExist("none", ""));	
	}
	
	@Test
	public void testUserAction() 
	{
		int idUser = facade.userID(USER_NAME, PWD);
		
		System.out.println("TEST USER ACTION\n\n");
		
		/* LIST des medias */
		printMedias();
		System.out.println("\n\n");
		
		/* LECTURE */
		System.out.println("Contenu du fichier test.txt :");
		Document doc = new Document(1, medias.get(1));
		assertFalse(doc.isOnDisk());
		assertTrue(facade.getMediaOnDisk(doc));
		assertTrue(doc.isOnDisk());
		doc.print();
		System.out.println("\n\n");
		
		/* AJOUT DOCUMENT */
		System.out.println("Creation et Ajout du fichier newFile.txt dans la BDD");
		File f = new File(System.getProperty("java.io.tmpdir")+File.separator+"newFile");	
		try 
		{
			f.createNewFile();
		} catch (IOException e) {
			fail();
			e.printStackTrace();
		}
		Document newDoc = new Document(f);
		AddRessource action = new AddRessource(newDoc);
		facade.executeAction(action);	
		printMedias();
		System.out.println("\n\n");
		
		
		/* SUPPRESION */
		System.out.println("Suppression du dernier media ajouté");
		RemoveRessource remove = new RemoveRessource(newDoc);
		facade.executeAction(remove);
		printMedias();
	}
	
	public void printMedias()
	{
		updateListMedia();
		System.out.println("Liste des medias :");
		for(int mediaID : medias.keySet())
		{
			String mediaName = medias.get(mediaID);
			System.out.println("\t- "+"id:"+mediaID+" name:"+mediaName);
		}
	}
	
	public void updateListMedia()
	{
		/* LIST des medias */
		medias = facade.listMedias();
		assertFalse(medias == null);
		assertFalse(medias.size() == 0);
	}
}

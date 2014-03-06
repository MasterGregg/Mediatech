package model;

public class AddRessource extends Action
{
	public final static String NAME = "AddRessource";
	
	protected Media media;
	protected boolean ok;
	
	public AddRessource(Media media)
	{
		super(NAME);
		this.media = media;
		ok = false;
	}

	protected void start(SQLManager connector) 
	{
		if(media.isOnDisk())
		{
			ok = connector.addMedia(media);	
		}
		if(ok) System.out.println("Ressource "+media.getName()+" was Added on BDD");
	}
	
	

}

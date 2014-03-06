package model;

public class RemoveRessource extends Action
{
public final static String NAME = "RemoveRessource";
	
	protected Media media;
	protected boolean ok;
	
	public RemoveRessource(Media media)
	{
		super(NAME);
		this.media = media;
		ok = false;
	}

	protected void start(SQLManager connector) 
	{
		if(media.isOnDisk()) media.getLocalFile().delete();
		ok = connector.removeMedia(media);
		if(ok) System.out.println("Ressource "+media+" was removed");
	}

}

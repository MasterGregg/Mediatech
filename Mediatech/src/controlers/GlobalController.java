package controlers;

import java.awt.EventQueue;

import model.Facade;
import views.ConsoleView;

public class GlobalController 
{
	private ConsoleView view;
	private Facade facade;
	
	public GlobalController()
	{
		facade = new Facade();
		final GlobalController c = this;
		
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try { view = new ConsoleView(c);	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

}

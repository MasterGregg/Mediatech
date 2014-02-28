package views;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainFrame extends JFrame 
{	
	private final static int WIDTH = 1024;
	private final static int HEIGHT = 768;
	
	public MainFrame()
	{
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Mediatech");
        
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();  
        int screenWidth = dim.width;  
        int screenHeight = dim.height;    
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLocation((screenWidth-WIDTH)/2,(screenHeight-HEIGHT)/2);  
     
        this.pack();
        this.setVisible(true);
	}
}

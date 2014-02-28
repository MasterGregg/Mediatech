package views;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame 
{
	public MainFrame()
	{
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("This is a Mediatech !");
        this.getContentPane().add(label);
        
        this.pack();
        this.setVisible(true);
	}
}

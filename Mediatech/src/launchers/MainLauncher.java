package launchers;

import java.awt.EventQueue;

import views.MainFrame;

public class MainLauncher {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

package main.java.main;

import java.awt.EventQueue;

public class Main
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				MainMenu mainmenu = new MainMenu();
				mainmenu.setVisible(true);
			}
		});
	}
}

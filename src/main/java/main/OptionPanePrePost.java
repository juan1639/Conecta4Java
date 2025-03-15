package main.java.main;

import java.awt.Toolkit;

import javax.swing.JOptionPane;

import main.java.tablero.Board;

public class OptionPanePrePost
{
	public static void prePostJuegoDialog()
	{
		int newGamePane;
		int gameoverPane;

		if (!Settings.isPreJuego() && !Settings.isPuzzleResuelto())
		{
			return;
		}

		if (Settings.isPreJuego())
		{
			/*
			 * newGamePane = JOptionPane.showConfirmDialog(Board.panel,
			 * " COMENZAR PARTIDA ", "Play", JOptionPane.CLOSED_OPTION);
			 * 
			 * if (newGamePane == JOptionPane.OK_OPTION) { Settings.setPreJuego(false);
			 * Settings.setEnJuego(true); }
			 */

		} else if (Settings.isPuzzleResuelto())
		{
			gameoverPane = JOptionPane.showConfirmDialog(Board.panel, " VOLVER A JUGAR ? ",
					" ENHORABUENA! Puzzle Resuelto! ", JOptionPane.YES_NO_CANCEL_OPTION);

			if (gameoverPane == JOptionPane.NO_OPTION || gameoverPane == JOptionPane.CANCEL_OPTION)
			{
				Toolkit.getDefaultToolkit().beep();
				System.exit(0);

			} else if (gameoverPane == JOptionPane.YES_OPTION)
			{
				Settings.setPuzzleResuelto(false);
				Board.iniciarComponentesSwing();
			}
		}
	}
}

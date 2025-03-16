package main.java.logica;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.main.Settings;
import main.java.tablero.Board;

public class RealizarJugadaIA
{
	private static Integer[][] copiaArrayInt;
	private static Boolean banderaCheckPrimeraVacia = false;

	public static Integer[] checkSiIA4Raya(Integer ID_JUGADOR)
	{
		for (int columna = 0; columna < Settings.COLUMNAS; columna++)
		{
			banderaCheckPrimeraVacia = false;
			Integer FILAS = Settings.FILAS - 1;

			for (Integer i = FILAS; i >= 0; i--)
			{
				if (Board.getArrayInt()[i][columna] == Settings.INIT_TO_ZERO && !banderaCheckPrimeraVacia)
				{
					copiaArrayInt = Board.getArrayInt();
					copiaArrayInt[i][columna] = ID_JUGADOR;
					Board.setArrayInt(copiaArrayInt);

					if (Checks.checkHorVer(ID_JUGADOR, 0, 1)
							|| Checks.checkHorVer(ID_JUGADOR, 1, 0)
							|| Checks.checkDiagonales(ID_JUGADOR))
					{
						copiaArrayInt[i][columna] = Settings.INIT_TO_ZERO;
						Board.setArrayInt(copiaArrayInt);
						
						return new Integer[]
						{ i, columna };
					}
					
					copiaArrayInt[i][columna] = Settings.INIT_TO_ZERO;
					Board.setArrayInt(copiaArrayInt);
					
					banderaCheckPrimeraVacia = true;
				}
			}
		}

		return new Integer[]
		{ -9, -9 };
	}
	
	public static int jugadaRandom()
	{
		return (int) Math.floor(Math.random() * Settings.COLUMNAS);
	}

	public static JDialog crearMsgInfoIAPensando()
	{
		JDialog dialog = new JDialog();
		dialog.setTitle("INFO | Turno IA");
		dialog.setSize(400, 100); // Tamaño del cuadro de diálogo
		dialog.setLocationRelativeTo(null); // Centrado en la pantalla
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		JPanel panelDialog = new JPanel();
		JLabel labelDialog = new JLabel();
		labelDialog.setText("IA Pensando...");
		Font fuente = new Font("verdana", Font.BOLD, 24);
		labelDialog.setForeground(Settings.Colores.AMARILLO);
		labelDialog.setFont(fuente);
		labelDialog.setBounds(100, 100, 300, 100);
		panelDialog.add(labelDialog);

		dialog.add(panelDialog);
		dialog.setVisible(true);

		return dialog;
	}
}

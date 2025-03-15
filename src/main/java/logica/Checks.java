package main.java.logica;

import javax.swing.JButton;

import main.java.entidades.CasillaSwing;
import main.java.main.Settings;
import main.java.tablero.Board;

public class Checks
{
	public static Integer[] CheckPrimeraCasillaVacia(Integer columna)
	{
		Integer FILAS = Settings.FILAS - 1;

		for (Integer i = FILAS; i >= 0; i--)
		{
			if (Board.getArrayInt()[i][columna] == Settings.INIT_TO_ZERO)
			{
				return new Integer[]
				{ i, columna };
			}
		}

		return new Integer[]
		{ -9, -9 };
	}
	
	public static Boolean checkEmpate()
	{
		for (int fila = 0; fila < Settings.FILAS; fila++)
		{
			for (int col = 0; col < Settings.COLUMNAS; col++)
			{
				if (Board.getArrayInt()[fila][col] == Settings.INIT_TO_ZERO)
				{
					return false;
				}
			}
		}
		return true;
	}

	public static Integer[] get2dPosicion(Integer indice)
	{
		Integer fila = (int) indice / Settings.COLUMNAS;
		Integer columna = (int) indice % Settings.COLUMNAS;

		return new Integer[]
		{ fila, columna };
	}
	
	public static Integer getIndicePosicion(Integer[] pos2D)
	{
		Integer fila = pos2D[0];
		Integer columna = pos2D[1];
		
		return fila * Settings.COLUMNAS + columna;
	}
}

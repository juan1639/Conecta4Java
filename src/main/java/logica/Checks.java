package main.java.logica;

import main.java.main.Settings;
import main.java.tablero.Board;

public class Checks
{
	private static final int CHECK_4 = 4;

	public static Integer[] checkBuscarPrimeraCasillaVacia(Integer columna)
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

	public static Boolean checkHorVer(Integer idJugador, int checkVer, int checkHor)
	{
		for (int fila = 0; fila < Settings.FILAS; fila++)
		{
			for (int col = 0; col < Settings.COLUMNAS; col++)
			{
				int contador = 0;

				for (int i = 0; i < CHECK_4; i++)
				{
					if ((checkVer > 0 && fila + i >= Settings.FILAS) || (checkHor > 0 && col + i >= Settings.COLUMNAS))
					{
						break;
					}

					if (Board.getArrayInt()[fila + i * checkVer][col + i * checkHor] == idJugador)
					{
						contador++;
					}
				}

				if (contador == CHECK_4)
				{
					return true;
				}
			}
		}

		return false;
	}

	public static Boolean checkDiagonales(Integer idJugador)
	{
		// Diagonal principal (\) - de arriba izquierda a abajo derecha
		for (int fila = 0; fila <= Settings.FILAS - CHECK_4; fila++)
		{
			for (int col = 0; col <= Settings.COLUMNAS - CHECK_4; col++)
			{
				int contador = 0;
				
				for (int i = 0; i < CHECK_4; i++)
				{
					if (Board.getArrayInt()[fila + i][col + i] == idJugador)
					{
						contador++;
					}
				}
				if (contador == CHECK_4)
				{
					return true;
				}
			}
		}

		// Diagonal secundaria (/) - de abajo izquierda a arriba derecha
		for (int fila = CHECK_4 - 1; fila < Settings.FILAS; fila++)
		{
			for (int col = 0; col <= Settings.COLUMNAS - CHECK_4; col++)
			{
				int contador = 0;
				
				for (int i = 0; i < CHECK_4; i++)
				{
					if (Board.getArrayInt()[fila - i][col + i] == idJugador)
					{
						contador++;
					}
				}
				if (contador == CHECK_4)
				{
					return true;
				}
			}
		}

		return false;
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

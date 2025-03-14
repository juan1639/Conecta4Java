package main.java.main;

import java.awt.Color;

public class Settings
{
	// Size de cada casilla (X, O) = 180px
	public static final Integer TILE_X = 100;
	public static final Integer TILE_Y = 100;

	public static final Integer FILAS = 6;
	public static final Integer COLUMNAS = 7;

	public static final Integer NUMERO_CASILLAS = COLUMNAS * FILAS;
	
	public static final Integer INIT_TO_ZERO = 0;
	
	private static Boolean preJuego = true;
	private static Boolean enJuego = false;
	private static Boolean puzzleResuelto = false;
	
	public class Colores
	{
		public static final Color NEGRO = new Color(1, 1, 1);
		public static final Color AMARILLO = new Color(245, 235, 1);
		public static final Color BLANCO = new Color(247, 247, 247);
		public static final Color AZUL = new Color(9, 115, 190);
		public static final Color AZUL_CLARO = new Color(9, 215, 190);
		public static final Color VERDE = new Color(50, 195, 9);
		public static final Color VERDE_CLARO = new Color(175, 235, 9);
	}
	
	public class Fichas
	{
		public static final String VACIA = "media/tablero-4enRaya.png";
		public static final String ROJA = "media/tablero-4enRaya-roja.png";
		public static final String AMARILLA = "media/tablero-4enRaya-amarilla.png";
	}
	
	// ***********************************************************************
	// Getters & Setters
	// 
	// ***********************************************************************
	public static Boolean isPreJuego()
	{
		return preJuego;
	}

	public static void setPreJuego(Boolean preGame)
	{
		preJuego = preGame;
	}

	public static Boolean isEnJuego()
	{
		return enJuego;
	}

	public static void setEnJuego(Boolean inGame)
	{
		enJuego = inGame;
	}

	public static Boolean isPuzzleResuelto()
	{
		return puzzleResuelto;
	}

	public static void setPuzzleResuelto(Boolean puzzleResolve)
	{
		puzzleResuelto = puzzleResolve;
	}
}

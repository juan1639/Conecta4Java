package main.java.logica;

import main.java.entidades.CasillaSwing;
import main.java.tablero.Board;

public class RealizarJugada
{
	private Integer valor;
	private Integer indice;
	private Integer fila;
	private Integer columna;
	
	public RealizarJugada(Integer valor, Integer indice, Integer fila, Integer columna)
	{
		super();
		this.valor = valor;
		this.indice = indice;
		this.fila = fila;
		this.columna = columna;
		
		Board.ActualizarBoardConNuevaFicha(this.columna);
	}

	public RealizarJugada()
	{
	}
	
	public static void MostrarBoardValoresEnConsola()
	{
		Integer contador = 0;
		
		for (CasillaSwing casilla : Board.getArrayCasillas())
		{
			contador ++;
			
			if (contador.equals(7))
			{
				System.out.println(casilla.getValor() + " ");
				contador = 0;
			}
			else
			{
				System.out.print(casilla.getValor() + " ");
			}
		}
	}
}

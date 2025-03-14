package main.java.entidades;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import main.java.logica.RealizarJugada;
import main.java.main.Settings;

public class CasillaSwing
{
	private Integer valor, indice;
	private Integer fila, columna;
	private JButton casillaBoton;

	public CasillaSwing(Integer valor, Integer indice, Integer fila, Integer columna)
	{
		this.valor = valor;
		this.indice = indice;
		this.fila = fila;
		this.columna = columna;

		this.casillaBoton = crearCasillaBoton();
		this.casillaBoton.addMouseListener(eventoRaton());
	}

	// Cada casilla es un JButton para que sea "clickable" (o cualquier elemento
	// Swing)
	private JButton crearCasillaBoton()
	{
		casillaBoton = new JButton(this.valor.toString());
		configCasillaBoton(casillaBoton);
		
		return casillaBoton;
	}
	
	public void configCasillaBoton(JButton casillaBoton)
	{
		int ancho = Settings.TILE_X;
		int alto = Settings.TILE_Y;
		
		casillaBoton.setEnabled(true);
		casillaBoton.setBounds(this.columna * ancho, this.fila * alto, ancho, alto);
		
		casillaBoton.setForeground(new Color(0, 0, 0, 0)); // Texto transparente
		casillaBoton.setContentAreaFilled(false);
        casillaBoton.setBorderPainted(false);
        casillaBoton.setFocusPainted(false);
        casillaBoton.setOpaque(false);
        
        ImageIcon icono = new ImageIcon(Settings.Fichas.VACIA);
		Image imagen = icono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		
        casillaBoton.setIcon(new ImageIcon(imagen));
        casillaBoton.setHorizontalTextPosition(SwingConstants.CENTER);
        casillaBoton.setVerticalTextPosition(SwingConstants.CENTER);
	}

	private MouseListener eventoRaton()
	{
		MouseListener oyenteRaton = new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				// System.out.println(e.getSource());
				if (Settings.isEnJuego())
				{
					realizarJugadaClick(valor, indice, fila, columna);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				//casillaBoton.setForeground(Settings.Colores.VERDE_CLARO);
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				//casillaBoton.setForeground(Settings.Colores.VERDE);
			}
		};

		return oyenteRaton;
	}

	private void realizarJugadaClick(Integer valor, Integer indice, Integer fila, Integer columna)
	{
		RealizarJugada jugada = new RealizarJugada(valor, indice, fila, columna);
	}

	// ***********************************************************************
	// Getters & Setters
	// 
	// ***********************************************************************
	public Integer getValor()
	{
		return valor;
	}

	public void setValor(Integer valor)
	{
		this.valor = valor;
	}

	public int getIndice()
	{
		return indice;
	}

	public void setIndice(int indice)
	{
		this.indice = indice;
	}

	public int getFila()
	{
		return fila;
	}

	public void setFila(int fila)
	{
		this.fila = fila;
	}

	public int getColumna()
	{
		return columna;
	}

	public void setColumna(int columna)
	{
		this.columna = columna;
	}

	public JButton getCasillaBoton()
	{
		return casillaBoton;
	}

	public void setCasillaBoton(JButton casillaBoton)
	{
		this.casillaBoton = casillaBoton;
	}
}

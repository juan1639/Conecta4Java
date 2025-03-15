package main.java.tablero;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import main.java.entidades.CasillaSwing;
import main.java.logica.Checks;
import main.java.logica.RealizarJugada;
import main.java.logica.RealizarJugadaIA;
import main.java.main.OptionPanePrePost;
import main.java.main.Settings;

public class Board extends JFrame implements ActionListener
{
	private static final long serialVersionUID = -7876218819488481472L;

	private static final Integer ANCHO_JFRAME = Settings.TILE_X * Settings.COLUMNAS;
	private static final Integer ALTO_JFRAME = Settings.TILE_Y * Settings.FILAS;

	private static final Integer NUMERO_CASILLAS = Settings.NUMERO_CASILLAS;

	private static CasillaSwing[] arrayCasillas = new CasillaSwing[NUMERO_CASILLAS];
	private static Integer[][] arrayInt = new Integer[Settings.FILAS][Settings.COLUMNAS];

	public static JPanel panel;
	private Timer timer;

	private static Boolean turno = true;// TRUE=turnoJugador1 | FALSE=turnoIA/Jugador2

	public Board(Boolean quienComienza)
	{
		turno = quienComienza;
		String idComenzar = turno ? "Jugador" : "AI";
		System.out.println("Comienza: " + idComenzar);

		settingsJFrame();
		crearPanel();
		iniciarComponentesSwing();

		timer = new Timer(1000, this);
		timer.start();
		timer.setRepeats(false);
	}

	private void settingsJFrame()
	{
		int ajusteX = 12, ajusteY = 34;

		setSize(ANCHO_JFRAME + ajusteX, ALTO_JFRAME + ajusteY);
		setTitle(" CONECTA-4  | Jugador, cuando sea su turno, haga CLICK en una columna para tirar ficha...");
		setLocationRelativeTo(null);
		setResizable(false);
		setMinimumSize(new Dimension(300, 300));
		// this.getContentPane().setBackground(Color.BLUE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void crearPanel()
	{
		panel = new JPanel();
		panel.setLayout(null);

		// panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
		panel.setBackground(Color.DARK_GRAY);
		this.getContentPane().add(panel);
	}

	public static void iniciarComponentesSwing()
	{
		// Reseteamos SIEMPRE (rejugar y de paso siempre)
		Settings.setPreJuego(false);
		Settings.setEnJuego(true);

		for (int i = 0; i < NUMERO_CASILLAS; i++)
		{
			arrayCasillas[i] = null;
		}

		// Tambien Vaciamos el panel y lo repintamos siempre
		panel.removeAll();
		panel.repaint();

		// Dibujar el Tablero de Juego / Draw GameBoard
		int i = 0;

		for (int fila = 0; fila < Settings.FILAS; fila++)
		{
			for (int col = 0; col < Settings.COLUMNAS; col++)
			{
				arrayInt[fila][col] = Settings.INIT_TO_ZERO;

				CasillaSwing casilla = new CasillaSwing(Settings.INIT_TO_ZERO, i, fila, col);
				arrayCasillas[i] = casilla;
				JButton casillaBoton = casilla.getCasillaBoton();
				panel.add(casillaBoton);
				i++;
			}
		}
		
		if (!turno)
		{
			callBackJugadaIA();
		}
	}

	public static void ActualizarBoardConNuevaFicha(Integer columna)
	{
		System.out.println("columna: " + columna);

		if (!turno)
		{
			columna = RealizarJugadaIA.jugadaRandom();
		}

		Integer idJugador = turno ? 1 : 2;

		Integer[] posicion2D = Checks.checkBuscarPrimeraCasillaVacia(columna);

		if (posicion2D[0] <= -1 || posicion2D[0] >= Settings.COLUMNAS)
		{
			System.out.println("columna LLENA...");
			
			if (!turno)
			{
				ActualizarBoardConNuevaFicha(-999);
			}
			return;
		}

		Integer indice = Checks.getIndicePosicion(posicion2D);

		// ***********************************************************
		// Cambiar valor (Primitivos)
		//
		// ***********************************************************
		arrayCasillas[indice].setValor(idJugador);
		arrayInt[posicion2D[0]][posicion2D[1]] = idJugador;

		// ***********************************************************
		// Cambiar Componentes-Swing
		//
		// ***********************************************************
		ImageIcon icono = new ImageIcon(turno ? Settings.Fichas.ROJA : Settings.Fichas.AMARILLA);
		Image imagen = icono.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		arrayCasillas[indice].getCasillaBoton().setIcon(new ImageIcon(imagen));

		arrayCasillas[indice].getCasillaBoton().setHorizontalTextPosition(SwingConstants.CENTER);
		arrayCasillas[indice].getCasillaBoton().setVerticalTextPosition(SwingConstants.CENTER);

		panel.repaint(); // Re-renderizar el Board
		RealizarJugada.MostrarBoardValoresEnConsola();

		if (Checks.checkEmpate())
		{
			System.out.println("*** EMPATE ***");
			Settings.setEnJuego(false);
			Settings.setPuzzleResuelto(true);
			OptionPanePrePost.prePostJuegoDialog();
			return;
		}

		if (Checks.checkHorVer(idJugador, 0, 1) || Checks.checkHorVer(idJugador, 1, 0)
				|| Checks.checkDiagonales(idJugador))
		{
			System.out.println("*** CUATRO EN RAYA *** " + idJugador);
			Settings.setEnJuego(false);
			Settings.setPuzzleResuelto(true);
			OptionPanePrePost.prePostJuegoDialog();
			return;
		}

		turno = !turno;
		
		if (!turno)
		{
			callBackJugadaIA();
		}
	}
	
	private static void callBackJugadaIA()
	{
		JDialog dialog = RealizarJugadaIA.crearMsgInfoIAPensando();
		
		Timer tiempoRespuestaIA = new Timer(2000, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dialog.dispose();
				ActualizarBoardConNuevaFicha(-999);
			}
		});

		tiempoRespuestaIA.setRepeats(false);
		tiempoRespuestaIA.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// OptionPanePrePost.prePostJuegoDialog();
	}

	// ***********************************************************************
	// Getters & Setters
	//
	// ***********************************************************************
	public static CasillaSwing[] getArrayCasillas()
	{
		return arrayCasillas;
	}

	public static void setArrayCasillas(CasillaSwing[] arrayCasillas)
	{
		Board.arrayCasillas = arrayCasillas;
	}

	public static Integer[][] getArrayInt()
	{
		return arrayInt;
	}

	public static void setArrayInt(Integer[][] arrayInt)
	{
		Board.arrayInt = arrayInt;
	}

	public static Boolean getTurno()
	{
		return turno;
	}
}

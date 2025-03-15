package main.java.logica;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.main.Settings;

public class RealizarJugadaIA
{
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

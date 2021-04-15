package GOT;

import java.awt.EventQueue;

/**
 * Proyecto de implementacion para segunda instacia de examen final libre de la materia Tecnologia de Programacion
 * @author Giuliano Giannotti
 */
public class Ventana {

	GUI gui;

	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				Ventana v = new Ventana();
				v.gui.setVisible(true);

			}
		});

	}

	public Ventana() {
		gui = new GUI();
		gui.setBounds(200, 100, 650, 350);
	}


}

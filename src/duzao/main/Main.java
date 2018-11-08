package duzao.main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import duzao.view.JanelaGeral;

public class Main {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new JanelaGeral();
	}

}

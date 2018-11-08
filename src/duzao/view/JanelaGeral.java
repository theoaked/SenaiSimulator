package duzao.view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class JanelaGeral extends JFrame {
	JMenuBar barraMenu;
	JMenu menuGame, menuOther;
	JMenuItem itemNovoJogo, itemSair;

	public JanelaGeral() {
		inicializarComponentes();
		definirEventos();
	}

	private void inicializarComponentes() {
		barraMenu = new JMenuBar();

		menuGame = new JMenu("Jogo");
		menuOther = new JMenu("Other");

		barraMenu.add(menuGame);
		barraMenu.add(menuOther);

		setJMenuBar(barraMenu);
		itemSair = new JMenuItem("Sair");
		itemNovoJogo = new JMenuItem("Novo Jogo");

		menuGame.add(itemNovoJogo);
		menuGame.add(itemSair);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setResizable(true);
		setSize(320, 150);
		//ImageIcon imagemIcone = new ImageIcon(getClass().getResource("/Imagens/icon.png"));
		//setIconImage(imagemIcone.getImage());
		setLocationRelativeTo(null);
		setTitle("Project X");
		setContentPane(new Fundo());
		setVisible(true);

	}
	
	private class Fundo extends JDesktopPane {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D graph = (Graphics2D) g;
			Image imagem = new ImageIcon(getClass().getResource("/Imagens/Fundo.jpg")).getImage();
			graph.drawImage(imagem, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}

	private void definirEventos() {
		itemNovoJogo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().add(new JanelaJogo());

			}
		});
		itemSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(JanelaGeral.this, "Tem certeza que quer sair?", "Confirmação de saída",
						JOptionPane.YES_NO_OPTION) == 0) {
					System.exit(0);
				}
			}
		});
	}
}

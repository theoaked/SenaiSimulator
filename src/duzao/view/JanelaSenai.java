package duzao.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import duzao.model.GameModel;

public class JanelaSenai extends JInternalFrame {
	private Color corPadrao;
	private Color corFonte;
	private Font fontePadrao;
	private static GameModel s;
	private JButton bt01;
	private JButton bt02;
	private JButton bt03;
	private JButton bt04;

	public JanelaSenai(GameModel s) {
		JanelaSenai.s = s;
		inicializarComponentes();
		definirEventos();
	}

	private void inicializarComponentes() {
		corPadrao = new Color(180, 180, 180);

		corFonte = new Color(0, 0, 0);

		fontePadrao = new Font("System", Font.BOLD, 14);

		Set<KeyStroke> teclas = new HashSet<KeyStroke>();
		teclas.add(KeyStroke.getKeyStroke("TAB"));

		bt01 = new JButton("Aula do Glauber");

		JPanel pn01 = new JPanel(new GridLayout(1, 1));
		pn01.add(bt01);
		pn01.setBounds(10, 10, 200, 100);

		bt02 = new JButton("Comer marmita");

		JPanel pn02 = new JPanel(new GridLayout(1, 1));
		pn02.add(bt02);
		pn02.setBounds(10, 110, 200, 100);

		bt03 = new JButton("Aula do Chile");

		JPanel pn03 = new JPanel(new GridLayout(1, 1));
		pn03.add(bt03);
		pn03.setBounds(10, 210, 200, 100);

		bt04 = new JButton("Jogar CS na aula da Kellyn");

		JPanel pn04 = new JPanel(new GridLayout(1, 1));
		pn04.add(bt04);
		pn04.setBounds(10, 310, 200, 100);
		
		bt01.setBackground(Color.BLACK);
		bt01.setForeground(Color.WHITE);
		bt02.setBackground(Color.BLACK);
		bt02.setForeground(Color.WHITE);
		bt03.setBackground(Color.BLACK);
		bt03.setForeground(Color.WHITE);
		bt04.setBackground(Color.BLACK);
		bt04.setForeground(Color.WHITE);

		setSize(230, 450);
		setTitle("SENAI PORRA!");
		setClosable(true);
		setResizable(false);
		setLocation(460, 200);
		setMaximizable(false);
		getContentPane().setBackground(Color.WHITE);
		setContentPane(new Fundo());

		add(pn01);
		add(pn02);
		add(pn03);
		add(pn04);
		setVisible(true);
		for (Component c : getContentPane().getComponents()) {
			c.setFont(fontePadrao);
			if (c instanceof JLabel) {
				c.setForeground(corFonte);
			}
		}

	}

	private class Fundo extends JDesktopPane {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D graph = (Graphics2D) g;
			Image imagem = new ImageIcon(getClass().getResource("/Imagens/Fundo2.jpg")).getImage();
			graph.drawImage(imagem, 0, 0, this.getWidth(), this.getHeight(), this);
		}
	}

	private void definirEventos() {
		bt01.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				s.setLevel(s.getLevel()+1);
				s.setInteligencia(s.getInteligencia() + 4);
				s.setDepressao(s.getDepressao() + 4);
				s.setFome(s.getFome() + 1);
				JOptionPane.showMessageDialog(null, " Depressão +4, Inteligencia +4 e Fome +1");
				JanelaJogo.atualizar();
			}
		});
		bt02.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (s.getDinheiro() < 1) {
					JOptionPane.showMessageDialog(null, "Você não tem dinheiro!");
				} else {
					s.setLevel(s.getLevel()+1);
					s.setFome(s.getFome() - 4);
					s.setBanheiro(s.getBanheiro() + 1);
					s.setDinheiro(s.getDinheiro() - 1);
					JOptionPane.showMessageDialog(null, "Fome -4, Banheiro +1 e Dinheiro -1");
					JanelaJogo.atualizar();
				}
			}
		});
		bt03.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				s.setLevel(s.getLevel()+1);
				s.setDepressao(s.getDepressao() + 1);
				s.setFome(s.getFome() + 1);
				s.setInteligencia(s.getInteligencia() + 3);
				JOptionPane.showMessageDialog(null, "Inteligencia +3, Depressao +1 e Fome +1");
				JanelaJogo.atualizar();
			}
		});
		bt04.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				s.setLevel(s.getLevel()+1);
				s.setDepressao(s.getDepressao() - 2);
				s.setFome(s.getFome() + 1);
				JOptionPane.showMessageDialog(null, "Depressão -2 e Fome +1");
				JanelaJogo.atualizar();
			}
		});

	}
}

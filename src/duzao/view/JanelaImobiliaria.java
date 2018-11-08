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

public class JanelaImobiliaria extends JInternalFrame {
	private Color corPadrao;
	private Color corFonte;
	private Font fontePadrao;
	private static GameModel s;
	private JButton bt01;
	private JButton bt02;
	private JButton bt03;
	private JButton bt04;

	public JanelaImobiliaria(GameModel s) {
		JanelaImobiliaria.s = s;
		inicializarComponentes();
		definirEventos();
	}

	private void inicializarComponentes() {
		corPadrao = new Color(180, 180, 180);

		corFonte = new Color(0, 0, 0);

		fontePadrao = new Font("System", Font.BOLD, 14);

		Set<KeyStroke> teclas = new HashSet<KeyStroke>();
		teclas.add(KeyStroke.getKeyStroke("TAB"));

		bt01 = new JButton("Comprar Casa lvl LIXO");

		JPanel pn01 = new JPanel(new GridLayout(1, 1));
		pn01.add(bt01);
		pn01.setBounds(10, 10, 200, 100);

		bt02 = new JButton("Comprar Casa lvl NORMAL");

		JPanel pn02 = new JPanel(new GridLayout(1, 1));
		pn02.add(bt02);
		pn02.setBounds(10, 110, 200, 100);

		bt03 = new JButton("Comprar Casa lvl FODA");

		JPanel pn03 = new JPanel(new GridLayout(1, 1));
		pn03.add(bt03);
		pn03.setBounds(10, 210, 200, 100);

		bt04 = new JButton("Trabalhar de corretor");

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
		setTitle("Corretora de casinhas");
		setClosable(true);
		setResizable(false);
		setLocation(0, 200);
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
				if (s.getCasaAtual() == 1) {
					JOptionPane.showMessageDialog(null, "Você já mora aqui!");
				} else {
					if (s.getDinheiro() < 2) {
						JOptionPane.showMessageDialog(null, "Você não tem dinheiro!");
					} else {
						s.setLevel(s.getLevel()+1);
						s.setDinheiro(s.getDinheiro()-2);
						s.setCasaAtual(1);
						JOptionPane.showMessageDialog(null, "Agora você mora em uma casa lvl LIXO");
						JanelaJogo.atualizar();
					}
				}
			}
		});
		bt02.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (s.getCasaAtual() == 2) {
					JOptionPane.showMessageDialog(null, "Você já mora aqui!");
				} else {
					if (s.getDinheiro() < 30) {
						JOptionPane.showMessageDialog(null, "Você não tem dinheiro!");
					} else {
						s.setLevel(s.getLevel()+1);
						s.setDinheiro(s.getDinheiro()-30);
						s.setCasaAtual(2);
						JOptionPane.showMessageDialog(null, "Agora você mora em uma casa lvl NORMAL");
						JanelaJogo.atualizar();
					}
				}
			}
		});
		bt03.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (s.getCasaAtual() == 3) {
					JOptionPane.showMessageDialog(null, "Você já mora aqui!");
				} else {
					if (s.getDinheiro() < 60) {
						JOptionPane.showMessageDialog(null, "Você não tem dinheiro!");
					} else {
						s.setLevel(s.getLevel()+1);
						s.setDinheiro(s.getDinheiro()-60);
						s.setCasaAtual(3);
						JOptionPane.showMessageDialog(null, "Agora você mora em uma casa lvl FODA");
						JanelaJogo.atualizar();
					}
				}
			}
		});
		bt04.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (s.getInteligencia() < 12) {
					JOptionPane.showMessageDialog(null, "Você é muito burro!");
				} else {
					s.setLevel(s.getLevel()+1);
					s.setDepressao(s.getDepressao() + 5);
					s.setDinheiro(s.getDinheiro() + 4);
					s.setBanheiro(s.getBanheiro() - 1);
					s.setFome(s.getFome() - 1);
					s.setSede(s.getSede() - 1);
					JOptionPane.showMessageDialog(null, "Depressão +5, Dinheiro +4, Banheiro -1, Fome -1 e Sede -1");
					JanelaJogo.atualizar();
				}
			}
		});

	}
}

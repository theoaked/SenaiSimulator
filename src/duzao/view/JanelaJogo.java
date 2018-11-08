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
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import duzao.dao.DaoJogo;
import duzao.model.GameModel;

public class JanelaJogo extends JInternalFrame {
	private DaoJogo dao;
	private Color corPadrao;
	private Color corFonte;
	private Font fontePadrao;
	private JLabel lbNome;
	private static JTextField tfNome;
	private JLabel lbLevel;
	private static JTextField tfLevel;
	private JLabel lbInteligencia;
	private static JTextField tfInteligencia;
	private JLabel lbFome;
	private static JTextField tfFome;
	private Component lbSede;
	private static JTextField tfSede;
	private JLabel lbBanheiro;
	private static JTextField tfBanheiro;
	private JLabel lbDepressao;
	private static JTextField tfDepressao;
	private JLabel lbDinheiro;
	private static JTextField tfDinheiro;
	private JLabel lbForca;
	private static JTextField tfForca;
	private JLabel lbDia;
	private static JTextField tfDia;
	private JButton btEscola;
	private JButton btBoteco;
	private JButton btSenai;
	private JButton btApple;
	private JButton btAcademia;
	private JButton btLanHouse;
	private JButton btCasa;
	private JButton btImobiliaria;
	private static GameModel s;
	private static String nome;
	private static int varAux;
	private static int dia;

	public JanelaJogo() {
		inicializarComponentes();
		definirEventos();
	}

	private void inicializarComponentes() {
		try {
			dao = new DaoJogo();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, "Erro: " + e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();

		}
		s = new GameModel();
		nome = JOptionPane.showInputDialog("Digite seu nome:");
		s.setLevel(1);
		s.setBanheiro(0);
		s.setDepressao(0);
		if (nome.equals("Duzao")) {
			s.setDinheiro(9000);
		} else {
			s.setDinheiro(5);
		}
		s.setFome(0);
		s.setForca(0);
		s.setInteligencia(0);
		s.setSede(0);
		s.setNome(nome);
		s.setCasaAtual(1);
		varAux = s.getLevel() - 1;
		dia = 1;

		Set<KeyStroke> teclas = new HashSet<KeyStroke>();
		teclas.add(KeyStroke.getKeyStroke("TAB"));

		corPadrao = new Color(180, 180, 180);

		corFonte = new Color(0, 0, 0);

		fontePadrao = new Font("System", Font.BOLD, 14);

		lbNome = new JLabel("Nome:");
		lbNome.setSize(80, 25);
		lbNome.setLocation(10, 10);

		tfNome = new JTextField();
		tfNome.setBounds(100, 10, 250, 25);
		tfNome.setBackground(Color.CYAN);
		tfNome.setEditable(false);
		tfNome.setHorizontalAlignment(SwingConstants.CENTER);

		lbLevel = new JLabel("Level:");
		lbLevel.setSize(80, 25);
		lbLevel.setLocation(10, 45);

		tfLevel = new JTextField();
		tfLevel.setBounds(100, 45, 80, 25);
		tfLevel.setBackground(Color.CYAN);
		tfLevel.setEditable(false);
		tfLevel.setHorizontalAlignment(SwingConstants.CENTER);

		lbInteligencia = new JLabel("Inteligencia:             /20 ");
		lbInteligencia.setSize(1000, 25);
		lbInteligencia.setLocation(10, 80);

		tfInteligencia = new JTextField();
		tfInteligencia.setBounds(100, 80, 80, 25);
		tfInteligencia.setBackground(Color.CYAN);
		tfInteligencia.setEditable(false);
		tfInteligencia.setHorizontalAlignment(SwingConstants.CENTER);

		lbFome = new JLabel("Fome:                        /20 ");
		lbFome.setSize(800, 25);
		lbFome.setLocation(10, 115);

		tfFome = new JTextField();
		tfFome.setBounds(100, 115, 80, 25);
		tfFome.setBackground(Color.CYAN);
		tfFome.setEditable(false);
		tfFome.setHorizontalAlignment(SwingConstants.CENTER);

		lbSede = new JLabel("Sede:                         /20 ");
		lbSede.setSize(800, 25);
		lbSede.setLocation(10, 150);

		tfSede = new JTextField();
		tfSede.setBounds(100, 150, 80, 25);
		tfSede.setBackground(Color.CYAN);
		tfSede.setEditable(false);
		tfSede.setHorizontalAlignment(SwingConstants.CENTER);

		lbBanheiro = new JLabel("Banheiro:                  /20 ");
		lbBanheiro.setSize(800, 25);
		lbBanheiro.setLocation(10, 185);

		tfBanheiro = new JTextField();
		tfBanheiro.setBounds(100, 185, 80, 25);
		tfBanheiro.setBackground(Color.CYAN);
		tfBanheiro.setEditable(false);
		tfBanheiro.setHorizontalAlignment(SwingConstants.CENTER);

		lbDepressao = new JLabel("Depressão:               /20 ");
		lbDepressao.setSize(800, 25);
		lbDepressao.setLocation(10, 220);

		tfDepressao = new JTextField();
		tfDepressao.setBounds(100, 220, 80, 25);
		tfDepressao.setBackground(Color.CYAN);
		tfDepressao.setEditable(false);
		tfDepressao.setHorizontalAlignment(SwingConstants.CENTER);

		lbDinheiro = new JLabel("Dinheiro:        R$");
		lbDinheiro.setSize(800, 25);
		lbDinheiro.setLocation(10, 255);

		tfDinheiro = new JTextField();
		tfDinheiro.setBounds(100, 255, 80, 25);
		tfDinheiro.setBackground(Color.CYAN);
		tfDinheiro.setEditable(false);
		tfDinheiro.setHorizontalAlignment(SwingConstants.RIGHT);

		lbForca = new JLabel("Força:                        /20 ");
		lbForca.setSize(800, 25);
		lbForca.setLocation(10, 290);

		tfForca = new JTextField();
		tfForca.setBounds(100, 290, 80, 25);
		tfForca.setBackground(Color.CYAN);
		tfForca.setEditable(false);
		tfForca.setHorizontalAlignment(SwingConstants.CENTER);

		lbDia = new JLabel("Dia:");
		lbDia.setSize(80, 25);
		lbDia.setLocation(240, 45);

		tfDia = new JTextField();
		tfDia.setBounds(270, 45, 80, 25);
		tfDia.setBackground(Color.CYAN);
		tfDia.setEditable(false);
		tfDia.setHorizontalAlignment(SwingConstants.CENTER);

		btEscola = new JButton("Escola");

		JPanel pnEscola = new JPanel(new GridLayout(1, 1));
		pnEscola.add(btEscola);
		pnEscola.setBounds(500, 10, 200, 25);
		pnEscola.setBackground(Color.CYAN);

		btBoteco = new JButton("Boteco do Jeberson");

		JPanel pnBoteco = new JPanel(new GridLayout(1, 1));
		pnBoteco.add(btBoteco);
		pnBoteco.setBounds(500, 45, 200, 25);
		pnBoteco.setBackground(Color.CYAN);

		btSenai = new JButton("SENAI");

		JPanel pnSenai = new JPanel(new GridLayout(1, 1));
		pnSenai.add(btSenai);
		pnSenai.setBounds(500, 80, 200, 25);
		pnSenai.setBackground(Color.CYAN);

		btApple = new JButton("Apple");

		JPanel pnApple = new JPanel(new GridLayout(1, 1));
		pnApple.add(btApple);
		pnApple.setBounds(500, 115, 200, 25);
		pnApple.setBackground(Color.CYAN);

		btAcademia = new JButton("Academia");

		JPanel pnAcademia = new JPanel(new GridLayout(1, 1));
		pnAcademia.add(btAcademia);
		pnAcademia.setBounds(500, 150, 200, 25);
		pnAcademia.setBackground(Color.CYAN);

		btLanHouse = new JButton("LanHouse do Jeremias");

		JPanel pnLanHouse = new JPanel(new GridLayout(1, 1));
		pnLanHouse.add(btLanHouse);
		pnLanHouse.setBounds(500, 185, 200, 25);
		pnLanHouse.setBackground(Color.CYAN);

		btCasa = new JButton("Casa");

		JPanel pnCasa = new JPanel(new GridLayout(1, 1));
		pnCasa.add(btCasa);
		pnCasa.setBounds(500, 220, 200, 25);
		pnCasa.setBackground(Color.CYAN);

		btImobiliaria = new JButton("Imobiliária");

		JPanel pnImobiliaria = new JPanel(new GridLayout(1, 1));
		pnImobiliaria.add(btImobiliaria);
		pnImobiliaria.setBounds(500, 255, 200, 25);
		pnImobiliaria.setBackground(Color.CYAN);

		btEscola.setBackground(Color.BLACK);
		btEscola.setForeground(Color.WHITE);
		btBoteco.setBackground(Color.BLACK);
		btBoteco.setForeground(Color.WHITE);
		btSenai.setBackground(Color.BLACK);
		btSenai.setForeground(Color.WHITE);
		btApple.setBackground(Color.BLACK);
		btApple.setForeground(Color.WHITE);
		btAcademia.setBackground(Color.BLACK);
		btAcademia.setForeground(Color.WHITE);
		btLanHouse.setBackground(Color.BLACK);
		btLanHouse.setForeground(Color.WHITE);
		btCasa.setBackground(Color.BLACK);
		btCasa.setForeground(Color.WHITE);
		btImobiliaria.setBackground(Color.BLACK);
		btImobiliaria.setForeground(Color.WHITE);

		setSize(800, 360);
		setTitle("Life Simulator");
		setClosable(true);
		setResizable(false);
		setLocation(0, 0);
		setMaximizable(false);
		getContentPane().setBackground(corPadrao);
		setContentPane(new Fundo());

		add(lbNome);
		add(lbLevel);
		add(lbSede);
		add(lbBanheiro);
		add(lbDepressao);
		add(lbDinheiro);
		add(lbFome);
		add(lbForca);
		add(lbInteligencia);
		add(lbDia);
		add(tfBanheiro);
		add(tfDepressao);
		add(tfDinheiro);
		add(tfFome);
		add(tfForca);
		add(tfInteligencia);
		add(tfLevel);
		add(tfNome);
		add(tfSede);
		add(tfDia);
		add(pnEscola);
		add(pnBoteco);
		add(pnSenai);
		add(pnApple);
		add(pnAcademia);
		add(pnLanHouse);
		add(pnCasa);
		add(pnImobiliaria);
		setVisible(true);

		for (Component c : getContentPane().getComponents()) {
			c.setFont(fontePadrao);
			if (c instanceof JLabel) {
				c.setForeground(corFonte);
			}
		}
		atualizar();
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

	public static void atualizar() {
		if (s.getBanheiro() >= 20) {
			JOptionPane.showMessageDialog(null, "Muito tempo sem cagar, faleceu.");
			s.setLevel(1);
			s.setBanheiro(0);
			s.setDepressao(0);
			if (nome.equals("Duzao")) {
				s.setDinheiro(9000);
			} else {
				s.setDinheiro(5);
			}
			s.setFome(0);
			s.setForca(0);
			s.setInteligencia(0);
			s.setSede(0);
			s.setNome(nome);
			s.setCasaAtual(1);
			varAux = s.getLevel() - 1;
			dia = 1;
		}
		if (s.getDepressao() >= 20) {
			JOptionPane.showMessageDialog(null, "Muito triste, se matou.");
			s.setLevel(1);
			s.setBanheiro(0);
			s.setDepressao(0);
			if (nome.equals("Duzao")) {
				s.setDinheiro(9000);
			} else {
				s.setDinheiro(5);
			}
			s.setFome(0);
			s.setForca(0);
			s.setInteligencia(0);
			s.setSede(0);
			s.setNome(nome);
			s.setCasaAtual(1);
			varAux = s.getLevel() - 1;
			dia = 1;
		}
		if (s.getFome() >= 20) {
			JOptionPane.showMessageDialog(null, "Saco vazio não para em pé, morreu de fome parça.");
			s.setLevel(1);
			s.setBanheiro(0);
			s.setDepressao(0);
			if (nome.equals("Duzao")) {
				s.setDinheiro(9000);
			} else {
				s.setDinheiro(5);
			}
			s.setFome(0);
			s.setForca(0);
			s.setInteligencia(0);
			s.setSede(0);
			s.setNome(nome);
			s.setCasaAtual(1);
			varAux = s.getLevel() - 1;
			dia = 1;
		}
		if (s.getInteligencia() < 0) {
			JOptionPane.showMessageDialog(null, "Você é tão burro que virou um vegetal.");
			s.setLevel(1);
			s.setBanheiro(0);
			s.setDepressao(0);
			if (nome.equals("Duzao")) {
				s.setDinheiro(9000);
			} else {
				s.setDinheiro(5);
			}
			s.setFome(0);
			s.setForca(0);
			s.setInteligencia(0);
			s.setSede(0);
			s.setNome(nome);
			s.setCasaAtual(1);
			varAux = s.getLevel() - 1;
			dia = 1;
		}
		if (s.getSede() >= 20) {
			JOptionPane.showMessageDialog(null, "ÁGUA NEM É IMPORTANTE NÉ");
			s.setLevel(1);
			s.setBanheiro(0);
			s.setDepressao(0);
			if (nome.equals("Duzao")) {
				s.setDinheiro(9000);
			} else {
				s.setDinheiro(5);
			}
			s.setFome(0);
			s.setForca(0);
			s.setInteligencia(0);
			s.setSede(0);
			s.setNome(nome);
			s.setCasaAtual(1);
			varAux = s.getLevel() - 1;
			dia = 1;
		}
		tfBanheiro.setText(String.valueOf(s.getBanheiro()));
		tfDepressao.setText(String.valueOf(s.getDepressao()));
		tfDinheiro.setText(String.valueOf(s.getDinheiro()));
		tfFome.setText(String.valueOf(s.getFome()));
		tfForca.setText(String.valueOf(s.getForca()));
		tfInteligencia.setText(String.valueOf(s.getInteligencia()));
		tfLevel.setText(String.valueOf(s.getLevel()));
		tfSede.setText(String.valueOf(s.getSede()));
		tfNome.setText(s.getNome());
		if (s.getLevel() >= (varAux + 10)) {
			JOptionPane.showMessageDialog(null, "Você efetuou 10 jogadas, o dia terminou!");
			varAux = s.getLevel();
			dia++;
		}
		tfDia.setText(String.valueOf(dia));
		if (dia >= 7) {
			JOptionPane.showMessageDialog(null, "Parabéns, você sobreviveu uma fucking semana nesse jogo lixo!!!");
			JOptionPane.showMessageDialog(null,
					"Status:\n" + "Nome -> " + s.getNome() + "\n" + "Level -> " + s.getLevel() + "\n" + "Sede -> "
							+ s.getSede() + "/20" + "\n" + "Banheiro -> " + s.getBanheiro() + "/20" + "\n"
							+ "Depressão -> " + s.getDepressao() + "/20" + "\n" + "Fome -> " + s.getFome() + "/20"
							+ "\n" + "Força -> " + s.getForca() + "/20" + "\n" + "Inteligencia -> "
							+ s.getInteligencia() + "/20" + "\n" + "Dinheiro -> " + s.getDinheiro());
			if (JOptionPane.showConfirmDialog(null, "Quer continuar?", "Acabou meu filho!",
					JOptionPane.YES_NO_OPTION) == 0) {
				dia = 1;
			} else {
				JOptionPane.showMessageDialog(null, "flw tmj");
				System.exit(0);
			}
		}
	}

	private void definirEventos() {
		atualizar();

		btEscola.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getDesktopPane().add(new JanelaEscola(s));
				atualizar();
			}
		});
		btBoteco.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getDesktopPane().add(new JanelaBoteco(s));
				atualizar();
			}
		});
		btSenai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getDesktopPane().add(new JanelaSenai(s));
				atualizar();
			}
		});
		btApple.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getDesktopPane().add(new JanelaApple(s));
				atualizar();
			}
		});
		btAcademia.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getDesktopPane().add(new JanelaAcademia(s));
				atualizar();
			}
		});
		btLanHouse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getDesktopPane().add(new JanelaLanHouse(s));
				atualizar();
			}
		});
		btCasa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (s.getCasaAtual()) {
				case 1:
					getDesktopPane().add(new JanelaCasa1(s));
					break;
				case 2:
					getDesktopPane().add(new JanelaCasa2(s));
					break;
				case 3:
					getDesktopPane().add(new JanelaCasa3(s));
					break;
				default:
					break;
				}
				atualizar();
			}
		});
		btImobiliaria.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				getDesktopPane().add(new JanelaImobiliaria(s));
				atualizar();
			}
		});

	}
}

package duzao.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duzao.model.GameModel;

public class DaoJogo {
	private static int LastLog;
	static {
		File arquivo = new File("gamelog.csv");
		if (!arquivo.exists()) {
			try {
				arquivo.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public DaoJogo() throws FileNotFoundException {
		Scanner leitor = new Scanner(new FileReader("log_game.txt"));
		LastLog = leitor.nextInt();
		leitor.close();
	}
	public void inserir(GameModel jogo) throws IOException {
		jogo.setLog(++LastLog);
		FileWriter writer = new FileWriter("gamelog.csv", true);
		writer.write(jogo.exibir() + "\n");
		writer.close();
		
		writer = new FileWriter("log_game.txt");
		writer.write(LastLog + "\n");
		writer.close();
	}
	public void alterar(GameModel jogoAlterar) throws IOException {
		Scanner leitor = new Scanner(new FileReader("gamelog.csv"));
		FileWriter writer = new FileWriter("gamelog.new", true);
		while (leitor.hasNext()) {
			String linha = leitor.nextLine();
			String[] dados = linha.split(";");
			int logLinha = Integer.parseInt(dados[0]);
			if (jogoAlterar.getLog() != logLinha) {
				writer.write(linha + "\n");
			} else {
				writer.write(jogoAlterar.exibir() + "\n");
			}
		}
		writer.close();
		leitor.close();

		File fInicial = new File("gamelog.csv");
		fInicial.delete();

		File f = new File("gamelog.new");
		f.renameTo(fInicial);
	}
	public void excluir(int id) throws IOException {
		Scanner leitor = new Scanner(new FileReader("adm.csv"));
		FileWriter writer = new FileWriter("adm.new", true);
		while (leitor.hasNext()) {
			String linha = leitor.nextLine();
			String[] dados = linha.split(";");
			int idLinha = Integer.parseInt(dados[0]);
			if (id != idLinha) {
				writer.write(linha + "\n");
			}
		}
		writer.close();
		leitor.close();

		File fInicial = new File("adm.csv");
		fInicial.delete();

		File f = new File("adm.new");
		f.renameTo(fInicial);
	}
	public List<GameModel> listar() throws FileNotFoundException, ParseException {
		List<GameModel> lista = new ArrayList<GameModel>();
		Scanner leitor = new Scanner(new FileReader("gamelog.csv"));
		while (leitor.hasNextLine()) {
			String linha = leitor.nextLine();
			String dados[] = linha.split(";");
			GameModel a = new GameModel();
			a.setLog(Integer.parseInt(dados[0]));
			a.setLevel(Integer.parseInt(dados[1]));
			a.setInteligencia(Integer.parseInt(dados[2]));
			a.setFome(Integer.parseInt(dados[3]));
			a.setNome(dados[4]);
			a.setSede(Integer.parseInt(dados[5]));
			a.setBanheiro(Integer.parseInt(dados[6]));
			a.setDepressao(Integer.parseInt(dados[7]));
			a.setDinheiro(Integer.parseInt(dados[8]));
			a.setForca(Integer.parseInt(dados[9]));
			lista.add(a);
		}
		leitor.close();
		return lista;
	}
}

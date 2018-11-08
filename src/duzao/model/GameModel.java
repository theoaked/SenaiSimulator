package duzao.model;

public class GameModel {
	private int log;
	private int level;
	private int inteligencia;
	private int fome;
	private String nome;
	private int sede;
	private int banheiro;
	private int depressao;
	private int dinheiro;
	private int forca;
	private int casaAtual;
	private int empregoAtual;

	public int getEmpregoAtual() {
		return empregoAtual;
	}

	public void setEmpregoAtual(int empregoAtual) {
		this.empregoAtual = empregoAtual;
	}

	public int getCasaAtual() {
		return casaAtual;
	}

	public void setCasaAtual(int casaAtual) {
		this.casaAtual = casaAtual;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getInteligencia() {
		return inteligencia;
	}

	public void setInteligencia(int inteligencia) {
		this.inteligencia = inteligencia;
	}

	public int getFome() {
		return fome;
	}

	public void setFome(int fome) {
		this.fome = fome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getSede() {
		return sede;
	}

	public void setSede(int sede) {
		this.sede = sede;
	}

	public int getBanheiro() {
		return banheiro;
	}

	public void setBanheiro(int banheiro) {
		this.banheiro = banheiro;
	}

	public int getDepressao() {
		return depressao;
	}

	public void setDepressao(int depressao) {
		this.depressao = depressao;
	}

	public int getLog() {
		return log;
	}

	public void setLog(int log) {
		this.log = log;
	}

	public int getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(int dinheiro) {
		this.dinheiro = dinheiro;
	}

	public int getForca() {
		return forca;
	}

	public void setForca(int forca) {
		this.forca = forca;
	}

	public String exibir() {
		return (log + ";" + level + ";" + inteligencia + ";" + fome + ";" + nome + ";" + sede + ";" + banheiro + ";" + depressao
				+ ";" + dinheiro + ";" + forca + ";" + casaAtual + ";" + empregoAtual);
	}
	public String toString() {
		return nome;
	}
}

package br.robhawk.android.comunicacoes.dto;

public class EstrategiaGerenciamento {

	private int id;
	private String responsabilidade;
	private String objetivo;
	private String expectativa;
	private Poder poder;
	private Interesse interesse;
	private Adesao adesaoAtual;
	private Adesao adesaoDesejada;
	private Incentivo incentivo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResponsabilidade() {
		return responsabilidade;
	}

	public void setResponsabilidade(String responsabilidade) {
		this.responsabilidade = responsabilidade;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getExpectativa() {
		return expectativa;
	}

	public void setExpectativa(String expectativa) {
		this.expectativa = expectativa;
	}

	public Poder getPoder() {
		return poder;
	}

	public void setPoder(Poder poder) {
		this.poder = poder;
	}

	public Interesse getInteresse() {
		return interesse;
	}

	public void setInteresse(Interesse interesse) {
		this.interesse = interesse;
	}

	public Adesao getAdesaoAtual() {
		return adesaoAtual;
	}

	public void setAdesaoAtual(Adesao adesaoAtual) {
		this.adesaoAtual = adesaoAtual;
	}

	public Adesao getAdesaoDesejada() {
		return adesaoDesejada;
	}

	public void setAdesaoDesejada(Adesao adesaoDesejada) {
		this.adesaoDesejada = adesaoDesejada;
	}

	public Incentivo getIncentivo() {
		return incentivo;
	}

	public void setIncentivo(Incentivo incentivo) {
		this.incentivo = incentivo;
	}

}

package br.robhawk.android.comunicacoes.dto;

public class PlanoComunicacao {

	private int id;
	private String metrica;
	private String motivo;
	private String conteudo;
	private MeioComunicacao meio;
	private FrequenciaComunicacao frequencia;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMetrica() {
		return metrica;
	}

	public void setMetrica(String metrica) {
		this.metrica = metrica;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public MeioComunicacao getMeio() {
		return meio;
	}

	public void setMeio(MeioComunicacao meio) {
		this.meio = meio;
	}

	public FrequenciaComunicacao getFrequencia() {
		return frequencia;
	}

	public void setFrequencia(FrequenciaComunicacao frequencia) {
		this.frequencia = frequencia;
	}

}

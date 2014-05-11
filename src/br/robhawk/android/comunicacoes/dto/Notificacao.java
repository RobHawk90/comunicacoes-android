package br.robhawk.android.comunicacoes.dto;

import java.util.Date;

import br.robhawk.android.comunicacoes.json.JsonDateDeserializer;
import br.robhawk.android.comunicacoes.json.JsonDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Notificacao {

	private int id;
	private String titulo;
	private String mensagem;
	private String anexo;
	private Date dataEnvio;

	private Stakeholder stakeholder;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getAnexo() {
		return anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getDataEnvio() {
		return dataEnvio;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Stakeholder getStakeholder() {
		return stakeholder;
	}

	public void setStakeholder(Stakeholder stakeholder) {
		this.stakeholder = stakeholder;
	}

}

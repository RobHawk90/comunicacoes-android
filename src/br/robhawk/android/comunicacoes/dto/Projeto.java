package br.robhawk.android.comunicacoes.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.robhawk.android.comunicacoes.json.JsonDateDeserializer;
import br.robhawk.android.comunicacoes.json.JsonDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Projeto implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String nome;
	private Date inicio;
	private Date termino;
	private List<Stakeholder> stakeholders;

	public Projeto() {
		stakeholders = new ArrayList<Stakeholder>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getInicio() {
		return inicio;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getTermino() {
		return termino;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setTermino(Date termino) {
		this.termino = termino;
	}

	public List<Stakeholder> getStakeholders() {
		return stakeholders;
	}

	public void setStakeholders(List<Stakeholder> stakeholders) {
		this.stakeholders.addAll(stakeholders);
	}

	@Override
	public String toString() {
		return nome;
	}
}

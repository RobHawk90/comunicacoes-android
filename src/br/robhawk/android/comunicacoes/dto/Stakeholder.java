package br.robhawk.android.comunicacoes.dto;

import java.io.Serializable;

public class Stakeholder implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String nome;
	private String email;
	private String telefone;
	private String foto;
	private Usuario usuario;
	private Projeto projeto;

	public Stakeholder() {
		setUsuario(new Usuario());
		/*
		 * setUsuario(new Usuario()); setEndereco(new Endereco());
		 * setPlanoComunicacao(new PlanoComunicacao());
		 * setEstrategiaGerenciamento(new EstrategiaGerenciamento());
		 * setProjeto(new Projeto());
		 */
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	@Override
	public String toString() {
		return nome;
	}
}

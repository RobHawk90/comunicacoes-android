package br.robhawk.android.comunicacoes.dtos;

import java.io.Serializable;

public class Stakeholder implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String nome;
	private String email;
	private String telefone;
	private String foto;
	private Usuario usuario;

	public Stakeholder() {
		setUsuario(new Usuario());
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

}

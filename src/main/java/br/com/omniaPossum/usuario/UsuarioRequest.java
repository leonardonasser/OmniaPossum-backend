package br.com.omniaPossum.usuario;

import javax.validation.constraints.NotNull;

public class UsuarioRequest {
	
	@NotNull
	private String email;
	
	@NotNull
	private String senha;
	
	@NotNull
	private String confirmar;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getConfirmar() {
		return confirmar;
	}
	public void setConfirmar(String confirmar) {
		this.confirmar = confirmar;
	}
	
	
}

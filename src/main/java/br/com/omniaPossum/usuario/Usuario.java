package br.com.omniaPossum.usuario;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String senha;
	//private LocalDate nascimento;


	public Long getId(){
		return id;
	}
	
	public String getSenha() {
		return senha;
	}
	public String getEmail() {
		return email;
	}
	/*public LocalDate getNascimento() {
		return nascimento;
	}*/

	public void setId(Long id) {
		this.id = id;
	}

	public void setSenha(String nome) {
		this.senha = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", senha=" + senha + ", getId()=" + getId() + ", getSenha()="
				+ getSenha() + ", getEmail()=" + getEmail() + "]";
	}

	/*public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}*/
	
	
	
	
}

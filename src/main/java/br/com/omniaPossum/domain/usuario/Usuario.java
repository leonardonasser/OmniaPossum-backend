package br.com.omniaPossum.domain.usuario;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import br.com.omniaPossum.infra.persistence.BaseEntity;


@Entity
@Table(name = "usuario")
public class Usuario extends BaseEntity<Usuario>{

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
    @Column(name = "nome", nullable = false)
    private String nome;

    //@Email
    @Column(name = "email", nullable = false, unique = true)
	private String email;
	
 
    @Column(name = "senha_hash", nullable = false)
	private String senhaHash;


    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
    
    @Column(name = "cep", nullable = false)
    private Long cep;
    
    
    @Deprecated
    protected Usuario() {
        // Apenas para JPA
    }
    
  

	
	  public Usuario(String nome,String email, Password password, Role role, Long cep) {
		this.nome = nome;
		this.email = email;
		this.senhaHash = password.hash();
		this.role = role;
		this.cep = cep;
	}




	public void setSenha(Password password) {
	        this.senhaHash = password.hash();
	    }

	  public boolean passwordMatches(String guess) {
	        return Password.matches(this.senhaHash, guess);
	  }
	    

	public Long getId(){
		return id;
	}
	
	public String getSenha() {
		return senhaHash;
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
		this.senhaHash = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public Long getCep() {
		return cep;
	}


	public void setCep(Long cep) {
		this.cep = cep;
	}


	
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senhaHash=" + senhaHash + ", role="
				+ role + ", cep=" + cep + "]";
	}




	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return email.equals(usuario.email);
    }
	
	 @Override
	    public int hashCode() {
	        return email.hashCode();
	    }
	
	
}

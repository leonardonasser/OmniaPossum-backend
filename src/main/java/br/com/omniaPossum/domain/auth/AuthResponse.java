package br.com.omniaPossum.domain.auth;

import br.com.omniaPossum.domain.usuario.Role;
import br.com.omniaPossum.domain.usuario.Usuario;



public class AuthResponse {

    private final Long id;
    private final String nome;
    private final String email;
    private final String token;
    private final Role role;

    public AuthResponse(Usuario usuario, String token) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.token = token;
        this.role = usuario.getRole();
    }

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}


	public String getToken() {
		return token;
	}

	public Role getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "AuthResponse [id=" + id + ", nome=" + nome + ", email=" + email + ", token=" + token + ", role=" + role
				+ "]";
	}

    
    
    
    
}

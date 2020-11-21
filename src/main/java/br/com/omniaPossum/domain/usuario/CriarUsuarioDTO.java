package br.com.omniaPossum.domain.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class CriarUsuarioDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;

    @NotBlank
    private String senhaConfirma;

    @NotBlank
    private Long cep;
    
  /*  @NotNull
    private Role role;
*/
	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public String getSenhaConfirma() {
		return senhaConfirma;
	}

	public Long getCep() {
		return cep;
	}

/*	public Role getRole() {
		return role;
	}*/
    
    
    

}

package br.com.omniaPossum.domain.usuario;

import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AtualizarUsuarioDTO {
	   	@Email
	    @NotBlank
	    private String email;

	    @NotBlank
	    private String nome;

	    //@NotBlank
	    private String senha;

	    //@NotBlank
	    private String senhaConfirma;

	    private Long cep;
	    
	    public boolean temSenha() {
	        return Optional
	            .ofNullable(senha)
	            .filter(senha -> !senha.isBlank())
	            .isPresent();
	    }
}

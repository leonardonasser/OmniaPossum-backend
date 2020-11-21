package br.com.omniaPossum.domain.usuario;

import java.util.List;
import java.util.stream.Collectors;


public class UsuarioDTO {
	 private Long id;
	    private String nome;
	    private String email;
	    private Long cep;
	    private Role role;

	    private UsuarioDTO(Usuario usuario) {
	        this.id = usuario.getId();
	        this.nome = usuario.getNome();
	        this.email = usuario.getEmail();
	        this.cep = usuario.getCep();
	        this.role = usuario.getRole();
	    }

	    public static UsuarioDTO ofEntity(Usuario usuario) {
	        return new UsuarioDTO(usuario);
	    }

	    public static List<UsuarioDTO> ofEntities(List<Usuario> usuarios) {
	        return usuarios
	            .stream()
	            .map(UsuarioDTO::ofEntity)
	            .collect(Collectors.toList());
	    }
}

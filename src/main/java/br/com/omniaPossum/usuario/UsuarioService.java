package br.com.omniaPossum.usuario;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class UsuarioService {

	@Inject
	private UsuarioRepository usuarioRepository;
	
	
	public void criarUsuario(String email, String senha) {
		Usuario usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setSenha(senha);
		usuarioRepository.persist(usuario);
		
	}

	public Usuario buscarPorid(Long id) {
		return usuarioRepository.findById(id);
	}

	public List<Usuario> buscarTodos() {
		return usuarioRepository.listAll();
	}
	
	public Long contarUsuarios(){
		return usuarioRepository.count();
	}

}

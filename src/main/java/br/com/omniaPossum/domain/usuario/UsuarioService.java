package br.com.omniaPossum.domain.usuario;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.omniaPossum.domain.auth.AuthResponse;
import br.com.omniaPossum.domain.auth.TokenIssuer;
import br.com.omniaPossum.infra.exceptions.ValidationException;

@ApplicationScoped
@Transactional
public class UsuarioService {

	@Inject
	private UsuarioRepository usuarioRepository;

    @Inject
    TokenIssuer tokenIssuer;


	public Optional<Usuario> recuperarPorEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	public Usuario recuperarPorEmailOrThrow(String email) {
		return recuperarPorEmail(email).orElseThrow(() -> new ValidationException("Usuário não encontrado"));
	}

    public Optional<Usuario> recuperarPorId(long idUsuario) {
        return usuarioRepository.findByIdOptional(idUsuario);
    }

    public Usuario recuperarPorIdOrThrow(long idUsuario) {
        return recuperarPorId(idUsuario)
            .orElseThrow(() -> new ValidationException("Usuário não encontrado"));
    }
	
	public Usuario buscarPorid(Long id) {
		return usuarioRepository.findById(id);
	}

	public List<Usuario> buscarTodos() {
		return usuarioRepository.listAll();
	}

	public Long contarUsuarios() {
		return usuarioRepository.count();
	}

	
	public AuthResponse criar(CriarUsuarioDTO dados) {

		System.out.println("1"+dados.getEmail());
		
		
		/* Usuario uAux = this.recuperarPorEmail(dados.getEmail()).orElse(null);
		if (uAux != null)
			throw new ValidationException("E-mail já existente! Defina outro.");
		*/
		System.out.println("2");
		
		
		Usuario usuario = new Usuario(
				dados.getNome(),
				dados.getEmail(),
				Password.of(dados.getSenha()),
				Role.ADMIN
				,dados.getCep());

		 salvar(usuario);
		 
		 String token = tokenIssuer.issueForLogin(usuario);
         return new AuthResponse(usuario, token);
	}

	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	public Usuario alterarSenha(String email, String novaSenha) {
		Usuario usuario = recuperarPorEmailOrThrow(email);
		usuario.setSenha(Password.of(novaSenha));
		return salvar(usuario);
	}
	
	
	
	  public Usuario atualizar(long idUsuario, AtualizarUsuarioDTO dados) {
	        Usuario usuario = recuperarPorIdOrThrow(idUsuario);

	        if (dados.temSenha()) {
	            usuario.setSenha(Password.of(dados.getSenha()));
	        }

	        usuario.setNome(dados.getNome());
	        usuario.setEmail(dados.getEmail());
	        usuario.setCep(dados.getCep());
	        //usuario.setRole(dados.getRole());

	        return salvar(usuario);
	    }
	
	    public void remover(Long idUsuario) {
	        Usuario usuario = recuperarPorIdOrThrow(idUsuario);
	        usuarioRepository.delete(usuario);
	    }
	

	
}

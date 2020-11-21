package br.com.omniaPossum.domain.usuario;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import br.com.omniaPossum.infra.persistence.BaseRepository;


@ApplicationScoped
@Transactional
public class UsuarioRepository extends BaseRepository<Usuario> {
	
    public Optional<Usuario> findByEmail(String email) {
    	List<Usuario> usuarios = listAll();
    	Optional<Usuario> user = findByIdOptional(0l);
    	
    	for (Usuario usuario : usuarios) {
			if(usuario.equals(email)) {
				System.out.println("Entou, email: "+usuario.getEmail());
				user = findByIdOptional(usuario.getId());
			}
		}
    	
    	
    	return user;
    }

	
}
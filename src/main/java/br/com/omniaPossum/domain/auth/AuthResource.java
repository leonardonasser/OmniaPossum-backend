package br.com.omniaPossum.domain.auth;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.omniaPossum.usuario.Usuario;
import br.com.omniaPossum.usuario.UsuarioService;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
@PermitAll
@Transactional
@Tag(name = "Auth")
public class AuthResource {

	private static final Logger LOG = LoggerFactory.getLogger(AuthResource.class);

	@Inject
	private UsuarioService usuarioService;

	@POST
	@Path("/login")
	public AuthResponse login(@Valid AuthRequest authRequest) throws Exception {
		AuthResponse response = new AuthResponse(authRequest.getEmail(), authRequest.getSenha());
		List<Usuario> usuarioList = usuarioService.buscarTodos();
		boolean encontrou = false;
		
		for (Usuario usuario: usuarioList) {
			if (response.getEmail().equals(usuario.getEmail()) && response.getToken().equals(usuario.getSenha())) {
				System.out.print("senha ok");
				encontrou = true;
			}
		}

		if (encontrou == false) {
			throw new Exception("Usuario ou senha Invalidos!");
		}

		return response;
	}

}

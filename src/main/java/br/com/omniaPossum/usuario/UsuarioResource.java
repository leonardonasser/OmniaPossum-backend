package br.com.omniaPossum.usuario;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;



@Path("/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
@PermitAll
@Transactional
@Tag(name = "usuarios")
public class UsuarioResource {

	@Inject
	private UsuarioService usuarioService;

	@GET
	@Path("/todos")
	public List<Usuario> buscarUsuarios() {
		return usuarioService.buscarTodos();
	}

	@GET
	@Path("/buscarPorId")
	public Usuario buscarPorId(Long id) {
		return usuarioService.buscarPorid(id);
	}

	@POST
	@Path("/addUser")
	public Usuario login(@Valid UsuarioRequest usuarioRequest)throws Exception {
		List<Usuario> usuarioList = usuarioService.buscarTodos();
		
		//Anliando se o email já existe
		for (Usuario usuario : usuarioList) {
			if (usuarioRequest.getEmail().equals(usuario.getEmail())) {
				throw new Exception("Email já existente!");
			}
		}

		//Analisando se a senha é igual e criando o usuario.
		
		if (usuarioRequest.getSenha().equals(usuarioRequest.getConfirmar())) {
			System.out.println("Criando usuario...");
			usuarioService.criarUsuario(usuarioRequest.getEmail(), usuarioRequest.getSenha());
			System.out.println("Usuario criado com sucesso!");
		} else {
			throw new Exception("Senhas Diferentes!");
		}

		return usuarioService.buscarPorid(usuarioService.contarUsuarios());
	}

}

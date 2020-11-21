package br.com.omniaPossum.domain.usuario;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.com.omniaPossum.domain.auth.AuthResponse;
import br.com.omniaPossum.infra.exceptions.ValidationException;


@Path("/painel/usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
@RolesAllowed(Role.ADMIN_ROLE)
@Transactional
@Tag(name = "Usuario Painel")
public class UsuarioResource {

    @Inject
    UsuarioService usuarioService;

   /* @GET
    public List<UsuarioDTO> listar(@BeanParam @Valid UsuarioFilter filtro) {
    	List<Usuario> usuarios = usuarioService.listarPorFiltro(filtro);
        return MotoristaDTO.ofEntities(usuarios);
    }
*/
    
    @GET
    @Path("/{id}")
    public UsuarioDTO get(@PathParam("id") Long idUsuario) {
        return usuarioService.recuperarPorId(idUsuario)
            .map(UsuarioDTO::ofEntity)
            .orElseThrow(() -> new ValidationException("Usuário não encontrado"));
    }

    @POST
    @Path("/cadastrar")
    public AuthResponse  criar(@Valid CriarUsuarioDTO dados) {
    	 return usuarioService.criar(dados);
       
    }

    @PUT
    @Path("/{id}")
    public UsuarioDTO atualizar(@PathParam("id") long idUsuario, @Valid AtualizarUsuarioDTO dados) {
        Usuario usuario = usuarioService.atualizar(idUsuario, dados);
        return UsuarioDTO.ofEntity(usuario);
    }

    @DELETE
    @Path("/{id}")
    public void remover(@PathParam("id") Long idUsuario) {
        usuarioService.remover(idUsuario);
    }

}

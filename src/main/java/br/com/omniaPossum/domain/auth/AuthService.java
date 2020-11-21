package br.com.omniaPossum.domain.auth;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import br.com.omniaPossum.domain.mail.EmailService;
import br.com.omniaPossum.domain.usuario.Password;
import br.com.omniaPossum.domain.usuario.Role;
import br.com.omniaPossum.domain.usuario.Usuario;
import br.com.omniaPossum.domain.usuario.UsuarioService;
import br.com.omniaPossum.infra.exceptions.ValidationException;
import io.quarkus.security.UnauthorizedException;

@ApplicationScoped
@Transactional
public class AuthService {

    @Inject
    UsuarioService usuarioService;

    @Inject
    TokenIssuer tokenIssuer;

    @Inject
    EmailService emailService;

    @ConfigProperty(name = "app.base-url")
    String appUrl;

    public AuthResponse autenticar(String email, String senha) {
     /*   Usuario usuario = usuarioService.recuperarPorEmail(email)
                .filter(u -> u.passwordMatches(senha))
                .orElseThrow(() -> new UnauthorizedException("Email ou senha incorretos"));
*/
     	Usuario usuarioVai = new Usuario("t","e",Password.of("s"),Role.ADMIN,87055061l);
 
    	List<Usuario> usuarios = usuarioService.buscarTodos();
    	
    	for (Usuario usuario : usuarios) {
    		if(usuario.getEmail().equals(email) && usuario.passwordMatches(senha)) {
    	        String token = tokenIssuer.issueForLogin(usuario);
    	            return new AuthResponse(usuario, token);
    		}
		}
    	
    	if(usuarioVai.getEmail().equals("e")) {
    	throw new ValidationException("Usuario e senha incorretos!!");
    	}
    	
    	String token = tokenIssuer.issueForLogin(usuarioVai);
        return new AuthResponse(usuarioVai, token);
    }

    /*public void resetarSenha(String email) {
        Usuario usuario = usuarioService.recuperarPorEmailOrThrow(email);

        String token = tokenIssuer.issueForPasswordReset(usuario);

        enviarEmailResetSenha(email, token);
    }

    private void enviarEmailResetSenha(String email, String token) {
        
    	String link = String.format("%s/resetar-senha?token=%s", appUrl, token);
        
        emailService.enviarEmailResetSenha(email, link);
    }*/

    public AuthResponse atualizarSenha(String token, String novaSenha) {
        String email = tokenIssuer.validateAndGetSubjectEmail(token);
        
        Usuario usuario = usuarioService.alterarSenha(email, novaSenha);
        
        return autenticar(usuario.getEmail(), novaSenha);
        
    }
}

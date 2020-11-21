package br.com.omniaPossum.domain.mail;

import io.quarkus.mailer.MailTemplate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class EmailService {

   /* @Inject
    MailTemplate resetSenha;

    public void enviarEmailResetSenha(String email, String linkResetarSenha) {
        resetSenha
            .data("linkResetarSenha", linkResetarSenha)
            .subject("Cisspoder - Reset de senha")
            .to(email)
            .send();
    }*/
}

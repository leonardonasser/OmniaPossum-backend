package br.com.omniaPossum.domain.auth;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AtualizacaoSenha {

    @NotNull
    private String token;

    @NotBlank
    private String novaSenha;

}

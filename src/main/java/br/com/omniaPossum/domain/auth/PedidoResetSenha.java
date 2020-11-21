package br.com.omniaPossum.domain.auth;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PedidoResetSenha {

    @NotNull
    private String cpf;

}

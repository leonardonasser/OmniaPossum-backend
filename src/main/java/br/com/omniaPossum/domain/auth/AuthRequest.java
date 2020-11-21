package br.com.omniaPossum.domain.auth;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class AuthRequest {


    @NotNull
    private String email;

    @NotNull
    private String senha;

}

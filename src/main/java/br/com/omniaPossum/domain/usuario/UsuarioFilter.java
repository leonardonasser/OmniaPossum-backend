package br.com.omniaPossum.domain.usuario;

import javax.ws.rs.QueryParam;

public class UsuarioFilter {

    @QueryParam("search")
    private String search;

    @QueryParam("role")
    private Role role;
}

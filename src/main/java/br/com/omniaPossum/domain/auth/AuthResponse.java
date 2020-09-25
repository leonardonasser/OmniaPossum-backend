package br.com.omniaPossum.domain.auth;

public class AuthResponse {

    private final String email;
    private final String token;

    //public AuthResponse(Usuario usuario, String token) {
	public AuthResponse(String email, String token) {
	    this.email = email;
        this.token = token;
    }

	public String getEmail() {
		return email;
	}

	public String getToken() {
		return token;
	}
	
}

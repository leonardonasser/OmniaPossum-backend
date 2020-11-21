package br.com.omniaPossum.domain.usuario;

import java.util.Objects;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.omniaPossum.infra.util.Checks;

public class Password {

    private final String unhashedPassword;

    private Password(String unhashedPassword) {
        this.unhashedPassword = unhashedPassword;
    }

    public static Password of(String password) {
        Checks.checkNotNull(password);
        Checks.checkArgument(!password.trim().equals(""));

        return new Password(password);
    }

    public static boolean matches(String hashedPassword, String guess) {
        return BCrypt
            .verifyer()
            .verify(guess.toCharArray(), hashedPassword.toCharArray())
            .verified;
    }

    public String hash() {
        return BCrypt.withDefaults().hashToString(12, unhashedPassword.toCharArray());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password = (Password) o;
        return unhashedPassword.equals(password.unhashedPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unhashedPassword);
    }

    @Override
    public String toString() {
        return hash();
    }

}

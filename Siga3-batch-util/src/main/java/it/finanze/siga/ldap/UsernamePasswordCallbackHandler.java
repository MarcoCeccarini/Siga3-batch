package it.finanze.siga.ldap;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.IOException;

public class UsernamePasswordCallbackHandler implements CallbackHandler {
    private final String username;
    private final char[] password;

    public UsernamePasswordCallbackHandler(String username, String password) {
        this.username = username;
        this.password = password != null ? password.toCharArray() : null;
    }

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback) {
                ((NameCallback) callback).setName(username);
            } else if (callback instanceof PasswordCallback) {
                ((PasswordCallback) callback).setPassword(password);
            } else {
                throw new UnsupportedCallbackException(callback);
            }
        }
    }
}

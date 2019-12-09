package tv.codely.backoffice.auth.application.authenticate;

import tv.codely.backoffice.auth.domain.*;
import tv.codely.shared.domain.Service;

import java.util.Optional;

@Service
public final class UserAuthenticator {
    private final AuthRepository repository;

    public UserAuthenticator(AuthRepository repository) {
        this.repository = repository;
    }

    public void authenticate(AuthUsername username, AuthPassword password) {
        Optional<AuthUser> auth = repository.search(username);

        ensureUserExist(auth, username);
        ensureCredentialsAreValid(auth.get(), password);
    }

    private void ensureUserExist(Optional<AuthUser> auth, AuthUsername username) {
        if (!auth.isPresent()) {
            throw new InvalidAuthUsername(username);
        }
    }

    private void ensureCredentialsAreValid(AuthUser auth, AuthPassword password) {
        if (!auth.passwordMatches(password)) {
            throw new InvalidAuthCredentials(auth.username());
        }
    }
}

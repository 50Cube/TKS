package UIControllers;

import lombok.SneakyThrows;
import pl.lodz.p.it.Publisher;
import pl.lodz.p.it.UIModel.UserUI;

import java.util.Collections;
import java.util.HashSet;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

@ApplicationScoped
public class CustomIdentityStore implements IdentityStore {

    @Inject
    private Publisher publisher;

    @SneakyThrows
    @Override
    public CredentialValidationResult validate(Credential credential) {

        UsernamePasswordCredential login = (UsernamePasswordCredential) credential;
        UserUI caller = publisher.getUser("getOne." + login.getCaller());
        if (caller == null || !caller.getIsActive()) {
            return CredentialValidationResult.NOT_VALIDATED_RESULT;
        }
        else if (caller.getPassword().equals(login.getPasswordAsString())){
             return new CredentialValidationResult(caller.getLogin(), new HashSet<>(Collections.singletonList(caller.getType())));
        }
        return CredentialValidationResult.NOT_VALIDATED_RESULT;
    }
}
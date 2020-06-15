/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIControllers;

import lombok.SneakyThrows;
import lombok.extern.java.Log;
import pl.lodz.p.it.Publisher;
import pl.lodz.p.it.UIModel.UserUI;
import pl.lodz.p.it.UIPorts.Aggregates.UserAdapterUI;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

@Log
@ApplicationScoped
public class CustomIdentityStore implements IdentityStore {

    @Inject
    private UserAdapterUI users;

    @Inject
    private Publisher publisher;

    @SneakyThrows
    @Override
    public CredentialValidationResult validate(Credential credential) {

        UsernamePasswordCredential login = (UsernamePasswordCredential) credential;
//        UserUI caller = users.getUser(login.getCaller());
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
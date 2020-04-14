/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UIControllers;

import pl.lodz.p.it.UIModel.UserUI;
import pl.lodz.p.it.UIPorts.Aggregates.UserAdapterUI;

import java.util.Arrays;
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
    private UserAdapterUI users;

    @Override
    public CredentialValidationResult validate(Credential credential) {

        UsernamePasswordCredential login = (UsernamePasswordCredential) credential;
        UserUI caller = users.getUser(login.getCaller());
        if (caller == null || !caller.getIsActive()) {
            return CredentialValidationResult.NOT_VALIDATED_RESULT;
        }
        else if (caller.getPassword().equals(login.getPasswordAsString())){

             return new CredentialValidationResult(caller.getLogin(), new HashSet<>(Arrays.asList(caller.getType())));
        } 
        return CredentialValidationResult.NOT_VALIDATED_RESULT;         
    }
}
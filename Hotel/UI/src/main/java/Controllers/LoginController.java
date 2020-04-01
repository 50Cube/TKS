package Controllers;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import java.io.IOException;
import java.util.logging.Logger;

@Named
@RequestScoped
public class LoginController {

    @NotEmpty
    private String password;

    @NotEmpty
    private String name;

    @Inject
    private SecurityContext securityContext;

    @Inject
    private ExternalContext externalContext;

    @Inject
    private FacesContext facesContext;
    
    private final Logger log =
          Logger.getLogger(this.getClass().getName());
    
    public void login() throws IOException {
        HttpServletRequest req = (HttpServletRequest) externalContext.getRequest();
        String ip = req.getRemoteAddr();
        switch (continueAuthentication()) {
            case SEND_CONTINUE:
                facesContext.responseComplete();
                break;
            case SEND_FAILURE:
                log.warning("Login failed for login " + name +" ip: " + ip);
                externalContext.redirect(externalContext.getRequestContextPath() + "/loginFailed.xhtml");
                break;
            case SUCCESS:
                log.info("Login succeeded for login " + name +" ip: " + ip);
                externalContext.redirect(externalContext.getRequestContextPath() + "/Main/mainPage.xhtml?faces-redirect=true");
                break;
            case NOT_DONE:
        }
    }
    
    public String logout() {
        this.externalContext.invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

    private AuthenticationStatus continueAuthentication() {
        return securityContext.authenticate((HttpServletRequest) externalContext.getRequest(),
                (HttpServletResponse) externalContext.getResponse(),
                AuthenticationParameters.withParams().credential(new UsernamePasswordCredential(name, password))
        );
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
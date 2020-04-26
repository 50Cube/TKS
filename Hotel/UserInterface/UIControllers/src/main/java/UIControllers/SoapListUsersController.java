package UIControllers;

import pl.lodz.p.it.SoapUserServiceInterface;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class SoapListUsersController implements Serializable {

    @Inject
    private SoapUserServiceInterface soapInterface;

    public SoapListUsersController () {}
}

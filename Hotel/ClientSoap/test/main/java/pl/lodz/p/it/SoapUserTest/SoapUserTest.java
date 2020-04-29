package pl.lodz.p.it.SoapUserTest;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pl.lodz.p.it.AdminSoap;
import pl.lodz.p.it.ClientSoap;
import pl.lodz.p.it.UserSoap;
import pl.lodz.p.it.impl.SoapUserServiceService;

import java.util.List;
import java.util.UUID;

public class SoapUserTest {

    SoapUserServiceService serviceService = new SoapUserServiceService();


    @Test
    public void testGetAllClients(){
        final List<ClientSoap> clients = serviceService.getSoapUserServicePort().getClients();
        Assert.assertTrue(clients.size()>0);
    }

    @Test
    public void testAddAdmin(){
        UUID uuid = UUID.randomUUID();
        AdminSoap adminSoap = new AdminSoap();
        adminSoap.setIsActive(true);
        adminSoap.setLogin("adminTest" + uuid.toString());
        adminSoap.setName("admin");
        adminSoap.setPassword("admin123");
        adminSoap.setSurname("admin");
        serviceService.getSoapUserServicePort().addAdmin(adminSoap);
        UserSoap user = serviceService.getSoapUserServicePort().getUser("adminTest" + uuid.toString());

        Assert.assertEquals(user.getLogin(), adminSoap.getLogin());
    }
}

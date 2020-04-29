package pl.lodz.p.it.SoapUserTest;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import pl.lodz.p.it.AdminSoap;
import pl.lodz.p.it.ClientSoap;
import pl.lodz.p.it.ManagerSoap;
import pl.lodz.p.it.UserSoap;
import pl.lodz.p.it.impl.SoapUserServiceService;

import java.util.List;
import java.util.UUID;

public class SoapUserTest {

    SoapUserServiceService serviceService = new SoapUserServiceService();

    @Test
    public void testGetAllUsers() {
        final List<UserSoap> users = serviceService.getSoapUserServicePort().getUsers();
        Assert.assertNotNull(users);
    }

    @Test
    public void testGetAllClients(){
        final List<ClientSoap> clients = serviceService.getSoapUserServicePort().getClients();
        Assert.assertTrue(clients.size()>0);
    }

    @Test
    public void testGetUser() {
        final UserSoap user = serviceService.getSoapUserServicePort().getUser("manager");
        Assert.assertTrue(user instanceof ManagerSoap);
    }

    @Test
    public void testAddClient() {
        UUID id = UUID.randomUUID();
        ClientSoap clientSoap = new ClientSoap();
        clientSoap.setLogin(id.toString());
        clientSoap.setName("test name");
        clientSoap.setSurname("test surname");
        clientSoap.setPassword("tzwsgrp22");
        clientSoap.setIsActive(false);

        serviceService.getSoapUserServicePort().addClient(clientSoap);
        UserSoap user = serviceService.getSoapUserServicePort().getUser(id.toString());
        Assert.assertEquals(user.getName(), clientSoap.getName());
    }

    @Test
    public void testUpdateUser() {
        UserSoap user = serviceService.getSoapUserServicePort().getUser("manager");
        String tmp = user.getName();
        user.setName("test");
        serviceService.getSoapUserServicePort().updateUser(user);
        UserSoap updatedUser = serviceService.getSoapUserServicePort().getUser("manager");
        Assert.assertEquals("test", updatedUser.getName());
        user.setName(tmp);
        serviceService.getSoapUserServicePort().updateUser(user);
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

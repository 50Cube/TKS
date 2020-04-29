package pl.lodz.p.it;


import pl.lodz.p.it.impl.SoapUserServiceService;

import java.util.List;

public class Testowanko {
    public static void main(String[] args){
        SoapUserServiceService serviceService = new SoapUserServiceService();
        final List<ClientSoap> clients = serviceService.getSoapUserServicePort().getClients();
        System.out.println(clients.get(0).getName());


    }
}

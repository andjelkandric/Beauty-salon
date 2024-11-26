/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.client;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Client;
import domain.Size;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Admin
 */
public class SOAddClient extends AbstractSO{
     @Override
    public void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Client)) {
            throw new Exception("Prosleđeni objekat nije instanca klase Client.");
        }

        Client newClient = (Client) ado;

        ArrayList<Client> clients = (ArrayList<Client>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        for (Client client : clients) {
            if(client.getEmail().equals(newClient.getEmail())){
                throw new Exception("Klijent sa istim email-om već postoji.");
            }
            if(client.getPhoneNumber().equals(newClient.getPhoneNumber())){
                throw new Exception("KLijent sa istim brojem telefona već postoji.");
            }
        }
    }

    @Override
    public void execute(AbstractDomainObject ado) throws Exception {
        PreparedStatement ps = DBBroker.getInstance().insert(ado);
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long clientId = tableKeys.getLong(1);
        Client c=(Client)ado;
        c.setClientId(clientId);
        if(c.getSize().get(0).getDate()==null){
            return;
        }
        for (Size size : c.getSize()) {
            size.setClient(c);
            DBBroker.getInstance().insert(size);
        }
    }
}

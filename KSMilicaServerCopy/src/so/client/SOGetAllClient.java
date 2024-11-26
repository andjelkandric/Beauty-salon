/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.client;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Client;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Admin
 */
public class SOGetAllClient extends AbstractSO {

    private ArrayList<Client> list;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Client)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Client.");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> clients = DBBroker.getInstance().select(ado);
        list = (ArrayList<Client>) (ArrayList<?>) clients;
    }

    public ArrayList<Client> getList() {
        return list;
    }

}

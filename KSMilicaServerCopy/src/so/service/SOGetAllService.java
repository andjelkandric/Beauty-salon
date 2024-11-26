/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.service;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Service;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Admin
 */
public class SOGetAllService extends AbstractSO{
    private ArrayList<Service> list;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Service)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Service.");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> clients = DBBroker.getInstance().select(ado);
        list = (ArrayList<Service>) (ArrayList<?>) clients;
    }

    public ArrayList<Service> getList() {
        return list;
    }
}

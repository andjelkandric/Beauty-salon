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
public class SOAddService extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Service)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Service.");
        }

        Service service = (Service) ado;
        ArrayList<Service> services = (ArrayList<Service>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Service s : services) {
            if (service.getName().equals(s.getName())) {
                throw new Exception("Usluga sa istim nazivom već postoji.");
            }
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
}

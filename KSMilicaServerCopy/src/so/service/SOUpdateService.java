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
public class SOUpdateService extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Service)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Service.");
        }

        Service updatedService = (Service) ado;

        ArrayList<Service> services = (ArrayList<Service>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        for (Service service : services) {
            if (!service.getServiceId().equals(updatedService.getServiceId())) {
                if (service.getName().equals(updatedService.getName())) {
                    throw new Exception("Usluga sa istim nazivom već postoji.");
                }
            }
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }
}

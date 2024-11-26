/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.service;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Service;
import so.AbstractSO;

/**
 *
 * @author Admin
 */
public class SODeleteService extends AbstractSO{
     @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Service)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Service.");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().delete(ado);
    }
}

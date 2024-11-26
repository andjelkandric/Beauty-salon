/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.clientCard;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.ClientCard;
import so.AbstractSO;

/**
 *
 * @author Admin
 */
public class SOAddClientCard extends AbstractSO{
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof ClientCard)) {
            throw new Exception("Prosledjeni objekat nije instanca klase ClientCard.");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.size;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Client;
import domain.Size;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Admin
 */
public class SOAddSize extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Size)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Size.");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Size size=(Size)ado;
        Client client=size.getClient();
        DBBroker.getInstance().select(client);
        DBBroker.getInstance().insert(ado);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.clientCard;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.ClientCard;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Admin
 */
public class SOGetAllClientCard extends AbstractSO {

    private ArrayList<ClientCard> list;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof ClientCard)) {
            throw new Exception("Prosledjeni objekat nije instanca klase ClientCard.");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> clientCards = DBBroker.getInstance().select(ado);
        list = (ArrayList<ClientCard>) (ArrayList<?>) clientCards;
    }

    public ArrayList<ClientCard> getList() {
        return list;
    }
}

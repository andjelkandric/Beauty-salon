/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.size;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Size;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Admin
 */
public class SOGetAllSize extends AbstractSO{
    private ArrayList<Size> list;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Size)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Size!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> sizes = DBBroker.getInstance().select(ado);
        list = (ArrayList<Size>) (ArrayList<?>) sizes;
    }

    public ArrayList<Size> getList() {
        return list;
    }
}

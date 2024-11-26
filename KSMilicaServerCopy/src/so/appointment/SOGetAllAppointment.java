/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.appointment;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Appointment;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Admin
 */
public class SOGetAllAppointment extends AbstractSO{
    private ArrayList<Appointment> list;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Appointment)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Appointment.");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> appointments = DBBroker.getInstance().select(ado);
        list = (ArrayList<Appointment>) (ArrayList<?>) appointments;
    }

    public ArrayList<Appointment> getList() {
        return list;
    }
}

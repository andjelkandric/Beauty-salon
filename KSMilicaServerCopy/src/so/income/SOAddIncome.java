/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.income;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Income;
import so.AbstractSO;

/**
 *
 * @author Admin
 */
public class SOAddIncome extends AbstractSO{
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Income)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Income.");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
}

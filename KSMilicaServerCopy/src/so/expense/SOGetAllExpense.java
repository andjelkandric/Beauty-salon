/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.expense;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Expense;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Admin
 */
public class SOGetAllExpense extends AbstractSO{
    private ArrayList<Expense> list;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Expense)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Expense.");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> expenses = DBBroker.getInstance().select(ado);
        list = (ArrayList<Expense>) (ArrayList<?>) expenses;
    }

    public ArrayList<Expense> getList() {
        return list;
    }
}

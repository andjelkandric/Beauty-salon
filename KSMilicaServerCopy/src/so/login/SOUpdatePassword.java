/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.login;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Employee;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Admin
 */
public class SOUpdatePassword extends AbstractSO{
    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Employee)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Employee.");
        }

        Employee updatedEmployee = (Employee) ado;

        ArrayList<Employee> employees = (ArrayList<Employee>) (ArrayList<?>) DBBroker.getInstance().select(ado);
        for (Employee employee : employees) {
            if (!employee.getEmployeeId().equals(updatedEmployee.getEmployeeId())) {
                if (employee.getPassword().equals(updatedEmployee.getPassword())) {
                    throw new Exception("Nova lozinka mora biti drugacija od postojece.");
                }
            }
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }
}

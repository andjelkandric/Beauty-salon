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
public class SOLogin extends AbstractSO {

    Employee loggedIn;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Employee)) {
            throw new Exception("Passed object is not instance of class Employee.");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        Employee e = (Employee) ado;
        ArrayList<Employee> employees = (ArrayList<Employee>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Employee employee : employees) {
            if(employee.getUsername().equals(e.getUsername()) && employee.getPassword().equals(e.getPassword())){
                loggedIn=employee;
                return;
            }
        }
        

        throw new Exception("Ne postoji zaposleni sa tim kredencijalima.");
    }

    public Employee getLoggedIn() {
        return loggedIn;
    }
}

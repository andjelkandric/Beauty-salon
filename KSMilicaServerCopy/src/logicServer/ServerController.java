/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicServer;

import domain.AbstractDomainObject;
import domain.Appointment;
import domain.Client;
import domain.ClientCard;
import domain.Employee;
import domain.Expense;
import domain.Income;
import domain.Service;
import domain.Size;
import java.io.ObjectInputStream;

import java.util.ArrayList;
import so.appointment.SOAddAppointment;
import so.appointment.SOGetAllAppointment;
import so.client.SOAddClient;
import so.client.SOGetAllClient;
import so.clientCard.SOAddClientCard;
import so.clientCard.SODeleteClientCard;
import so.clientCard.SOGetAllClientCard;
import so.clientCard.SOUpdateClientCard;
import so.expense.SOAddExpense;
import so.expense.SODeleteExpense;
import so.expense.SOGetAllExpense;
import so.expense.SOUpdateExpense;
import so.income.SOAddIncome;
import so.income.SOGetAllIncome;
import so.login.SOLogin;
import so.login.SOUpdatePassword;
import so.service.SOAddService;
import so.service.SODeleteService;
import so.service.SOGetAllService;
import so.service.SOUpdateService;
import so.size.SOAddSize;
import so.size.SOGetAllSize;

/**
 *
 * @author Admin
 */
public class ServerController {

    private static ServerController instance;
    private ArrayList<Employee> employees;

    public ServerController() {
        employees = new ArrayList<>();
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public Employee login(Employee employee) throws Exception {
        for (Employee e : employees) {
            if (employee.getUsername().equals(e.getUsername())) {
                throw new Exception("Instruktor sa unetim kredencijalima je već ulogovan!");
            }
        }

        SOLogin so = new SOLogin();
        so.templateExecute(employee);
        employees.add(employee);
        return so.getLoggedIn();
    }

    public void logout(Employee e) {
        for (int j = 0; j < employees.size(); j++) {
            if (employees.get(j).getUsername().equals(e.getUsername())) {
                employees.remove(j);
            }
        }
    }

    public Object getAllClient() throws Exception {
        SOGetAllClient so = new SOGetAllClient();
        so.templateExecute(new Client());
        return so.getList();
    }

    public Object getAllService() throws Exception {
        SOGetAllService so = new SOGetAllService();
        so.templateExecute(new Service());
        return so.getList();
    }

    public Object getAllAppointment() throws Exception {
        SOGetAllAppointment so = new SOGetAllAppointment();
        so.templateExecute(new Appointment());
        return so.getList();
    }

    public void deleteService(Service service) throws Exception {
        (new SODeleteService()).templateExecute(service);
    }

    public void updateService(Service service) throws Exception {
        (new SOUpdateService()).templateExecute(service);
    }

    public void addService(Service service) throws Exception {
        (new SOAddService()).templateExecute(service);
    }

    public void updatePassword(Employee employee) throws Exception {
        (new SOUpdatePassword()).templateExecute(employee);
    }

    public Object getAllExpense() throws Exception {
        SOGetAllExpense so = new SOGetAllExpense();
        so.templateExecute(new Expense());
        return so.getList();
    }

    public void deleteExpense(Expense expense) throws Exception {
        (new SODeleteExpense()).templateExecute(expense);
    }

    public void addExpense(Expense expense) throws Exception {
        (new SOAddExpense()).templateExecute(expense);
    }

    public void addClient(Client client) throws Exception {
        (new SOAddClient()).templateExecute(client);
    }

    public void addIncome(Income income) throws Exception {
        (new SOAddIncome()).templateExecute(income);
    }

    public void addClientCard(ClientCard clientCard) throws Exception {
        (new SOAddClientCard()).templateExecute(clientCard);
    }

    public Object getAllClientCard(Client client) throws Exception {
        SOGetAllClientCard so = new SOGetAllClientCard();
        ClientCard clientCard = new ClientCard();
        clientCard.setClient(client);
        so.templateExecute(clientCard);
        return so.getList();
    }

    public void addAppointment(Appointment appointment) throws Exception {
        (new SOAddAppointment()).templateExecute(appointment);
    }

    public void updateClientCard(ClientCard clientCard) throws Exception {
        (new SOUpdateClientCard()).templateExecute(clientCard);
    }

    public Object getAllSize(Client client) throws Exception {
        SOGetAllSize so = new SOGetAllSize();
        Size s = new Size();
        s.setClient(client);
        so.templateExecute(s);
        return so.getList();
    }

    public void addSize(Size size) throws Exception {
        (new SOAddSize()).templateExecute(size);
    }

    public void updateExpense(Expense expense) throws Exception {
        (new SOUpdateExpense()).templateExecute(expense);
    }

    public Object getAllIncome() throws Exception {
        SOGetAllIncome so = new SOGetAllIncome();
        so.templateExecute(new Income());
        return so.getList();
    }

    public void deleteClientCard(ClientCard clientCard) throws Exception {
        (new SODeleteClientCard()).templateExecute(clientCard);
    }
}

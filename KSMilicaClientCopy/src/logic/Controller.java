/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import communication.Communication;
import communicationCommon.Operation;
import communicationCommon.Request;
import communicationCommon.Response;
import domain.Appointment;
import domain.Client;
import domain.ClientCard;
import domain.Employee;
import domain.Expense;
import domain.Income;
import domain.Service;
import domain.Size;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Controller {

    private static Controller instance;

    private Controller() {
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Employee login(Employee employee) throws Exception {
        return (Employee) sendRequest(Operation.LOGIN, employee);
    }

    public void logout(Employee e) throws Exception {
        sendRequest(Operation.LOGOUT, e);
    }

    public ArrayList<Client> getAllClients() throws Exception {
        return (ArrayList<Client>) sendRequest(Operation.GET_ALL_CLIENT, null);
    }

    public ArrayList<Service> getAllServices() throws Exception {
        return (ArrayList<Service>) sendRequest(Operation.GET_ALL_SERVICE, null);
    }

    public ArrayList<Appointment> getAllAppointment() throws Exception {
        return (ArrayList<Appointment>) sendRequest(Operation.GET_ALL_APPOINTMENT, null);
    }

    public void deleteService(Service service) throws Exception {
        sendRequest(Operation.DELETE_SERVICE, service);
    }

    public void updateService(Service service) throws Exception {
        sendRequest(Operation.UPDATE_SERVICE, service);
    }

    public void addService(Service service) throws Exception {
        sendRequest(Operation.ADD_SERVICE, service);
    }

    public void updatePassword(Employee e) throws Exception {
        sendRequest(Operation.UPDATE_PASSWORD, e);
    }

    public ArrayList<Expense> getAllExpenses() throws Exception {
        return (ArrayList<Expense>) sendRequest(Operation.GET_ALL_EXPENSE, null);
    }

    public void deleteExpense(Expense expense) throws Exception {
        sendRequest(Operation.DELETE_EXPENSE, expense);
    }

    public void addExpense(Expense e) throws Exception {
        sendRequest(Operation.ADD_EXPENSE, e);
    }

    public void addClient(Client client) throws Exception {
        sendRequest(Operation.ADD_CLIENT, client);
    }

    public void addIncome(Income income) throws Exception {
        sendRequest(Operation.ADD_INCOME, income);
    }

    public void addClientCard(ClientCard clientCard) throws Exception {
        sendRequest(Operation.ADD_CLIENT_CARD, clientCard);
    }

    public ArrayList<ClientCard> getAllClientCard() throws Exception {
        return (ArrayList<ClientCard>) sendRequest(Operation.GET_ALL_CLIENT_CARD, null);
    }

    public ArrayList<ClientCard> getAllClientCard(Client client) throws Exception {
        return (ArrayList<ClientCard>) sendRequest(Operation.GET_ALL_CLIENT_CARD, client);
    }

    public void addAppointment(Appointment appointment) throws Exception {
        sendRequest(Operation.ADD_APPOINTMENT, appointment);
    }

    public void updateClientCard(ClientCard cc) throws Exception {
        sendRequest(Operation.UPDATE_CLIENT_CARD, cc);
    }

    public ArrayList<Size> getAllSizes(Client client) throws Exception {
        return (ArrayList<Size>) sendRequest(Operation.GET_ALL_SIZE, client);
    }

    public void addSize(Size size) throws Exception {
        sendRequest(Operation.ADD_SIZE, size);
    }

    public void updateExpense(Expense expense) throws Exception {
        sendRequest(Operation.UPDATE_EXPENSE, expense);
    }

    public ArrayList<Income> getAllIncomes() throws Exception {
        return (ArrayList<Income>) sendRequest(Operation.GET_ALL_INCOME, null);
    }

    public void deleteClientCard(ClientCard cc) throws Exception {
        sendRequest(Operation.DELETE_CLIENT_CARD, cc);
    }

    private Object sendRequest(int operation, Object data) throws Exception {
        Request request = new Request(operation, data);
        Communication.getInstance().getSender().send(request);

        Response response = (Response) Communication.getInstance().getReceiver().receive();
        if (response.getException() == null) {
            return response.getResult();
        } else {
            throw response.getException();
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import communicationCommon.Operation;
import communicationCommon.Receiver;
import communicationCommon.Request;
import communicationCommon.Response;
import communicationCommon.Sender;
import domain.AbstractDomainObject;
import domain.Appointment;
import domain.Client;
import domain.ClientCard;
import domain.Employee;
import domain.Expense;
import domain.Income;
import domain.Service;
import domain.Size;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import logicServer.ServerController;

/**
 *
 * @author Admin
 */
public class ClientThread extends Thread {

    private Socket socket;
    private Sender sender;
    private Receiver receiver;

    public ClientThread(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                Request request = (Request) receiver.receive();
                Response response = handleRequest(request);
                sender.send(response);
            }
        } catch (Exception ex) {
            System.out.println("");
            // ex.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response();
        try {
            switch (request.getOperation()) {
                case Operation.LOGIN:
                    Employee employee = (Employee) request.getArgument();
                    Employee loggedIn = ServerController.getInstance().login(employee);
                    response.setResult(loggedIn);
                    break;
                case Operation.LOGOUT:
                    ServerController.getInstance().logout((Employee) request.getArgument());
                    break;
                case Operation.GET_ALL_CLIENT:
                    response.setResult(ServerController.getInstance().getAllClient());
                    break;
                case Operation.GET_ALL_SERVICE:
                    response.setResult(ServerController.getInstance().getAllService());
                    break;
                case Operation.GET_ALL_APPOINTMENT:
                    response.setResult(ServerController.getInstance().getAllAppointment());
                    break;
                case Operation.UPDATE_SERVICE:
                    ServerController.getInstance().updateService((Service) request.getArgument());
                    break;
                case Operation.DELETE_SERVICE:
                    ServerController.getInstance().deleteService((Service) request.getArgument());
                    break;
                case Operation.ADD_SERVICE:
                    ServerController.getInstance().addService((Service) request.getArgument());
                    break;
                case Operation.UPDATE_PASSWORD:
                    ServerController.getInstance().updatePassword((Employee) request.getArgument());
                    break;
                case Operation.GET_ALL_EXPENSE:
                    response.setResult(ServerController.getInstance().getAllExpense());
                    break;
                case Operation.DELETE_EXPENSE:
                    ServerController.getInstance().deleteExpense((Expense) request.getArgument());
                    break;
                case Operation.ADD_EXPENSE:
                    ServerController.getInstance().addExpense((Expense) request.getArgument());
                    break;
                case Operation.ADD_CLIENT:
                    ServerController.getInstance().addClient((Client) request.getArgument());
                    break;
                case Operation.ADD_INCOME:
                    ServerController.getInstance().addIncome((Income) request.getArgument());
                    break;
                case Operation.ADD_CLIENT_CARD:
                    ServerController.getInstance().addClientCard((ClientCard) request.getArgument());
                    break;
                case Operation.GET_ALL_CLIENT_CARD:
                    response.setResult(ServerController.getInstance().getAllClientCard((Client) request.getArgument()));
                    break;
                case Operation.ADD_APPOINTMENT:
                    ServerController.getInstance().addAppointment((Appointment) request.getArgument());
                    break;
                case Operation.UPDATE_CLIENT_CARD:
                    ServerController.getInstance().updateClientCard((ClientCard) request.getArgument());
                    break;
                case Operation.GET_ALL_SIZE:
                    response.setResult(ServerController.getInstance().getAllSize((Client) request.getArgument()));
                    break;
                case Operation.ADD_SIZE:
                    ServerController.getInstance().addSize((Size) request.getArgument());
                    break;
                case Operation.UPDATE_EXPENSE:
                    ServerController.getInstance().updateExpense((Expense) request.getArgument());
                    break;
                case Operation.GET_ALL_INCOME:
                    response.setResult(ServerController.getInstance().getAllIncome());
                    break;
                case Operation.DELETE_CLIENT_CARD:
                    ServerController.getInstance().deleteClientCard((ClientCard) request.getArgument());
                    break;
                default:
                    return null;
            }
        } catch (Exception e) {
            response.setException(e);
            System.out.println(e.getMessage());
        }
        return response;
    }

}

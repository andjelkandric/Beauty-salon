/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import communicationCommon.Receiver;
import communicationCommon.Sender;
import domain.Employee;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class Communication {

    private static Communication instance;
    private Employee loggedIn;
    Socket socket;
    Sender sender;
    Receiver receiver;

    private Communication() {
        try {
            socket = new Socket("127.0.0.1", 9000);
            sender = new Sender(socket);
            receiver = new Receiver(socket);
        } catch (IOException ex) {
            //ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Server is down.");
            System.exit(0);
        }
    }

    public static Communication getInstance() {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setLoggedIn(Employee instruktor) {
        this.loggedIn = instruktor;
    }

    public Employee getLoggedIn() {
        return loggedIn;
    }

    public Sender getSender() {
        return sender;
    }

    public Receiver getReceiver() {
        return receiver;
    }

}

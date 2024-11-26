/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;
import logicServer.ServerController;

/**
 *
 * @author Admin
 */
public class ServerThread extends Thread {

    private ServerSocket serverSocket;

    public ServerThread() {
        try {
            serverSocket = new ServerSocket(9000);

        } catch (Exception ex) {
            System.err.println("Server soket nije kreiran.");
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void run() {
        try {
            while (!serverSocket.isClosed()) {

                System.out.println("Cekanje na konekciju...");;
                Socket socket = serverSocket.accept();

                ClientThread clientThread = new ClientThread(socket);
                System.out.println("Klijent se uspesno povezao!");
                clientThread.start();
            }
        } catch (IOException e) {
            System.out.println("klijent se odvezao");
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverstarter;

import threads.ServerThread;

/**
 *
 * @author Admin
 */
public class ServerStarter {
    
    public static ServerThread serverThread;
    
    public static void main(String[] args) {
        if(serverThread==null || !serverThread.isAlive()){
            serverThread=new ServerThread();
            serverThread.start();
        } 
    }
    
}

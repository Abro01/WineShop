package server;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Server {
    static Connection conn;
    public static void main(String[] args){
        ServerConfiguration conf = new ServerConfiguration("C:/Users/andre/OneDrive/Documenti/GitHub/WineShop/WineShop/src/main/java/server/ServerConfig.json");

        System.out.println(conf + "\n");

        try {
            String url = "jdbc:mysql://" + conf.getDbHost() + ":" + conf.getDbPort() + "/" + conf.getDbName();
            String user = conf.getDbUser();
            String password = conf.getDbPassword();
            conn = DriverManager.getConnection(url, user, password);

            System.out.println("1 - Il DataBase si è connesso!\n");
            ServerSocket server = new ServerSocket(conf.getServerPort());
            server.setReuseAddress(true);       //imposta la possibilità di riutilizzare un indirizzo già in uso
            System.out.println("2 - Server in ascolto sulla porta: " + conf.getServerPort());

            while (true)
            {
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client, conf);
                System.out.println("\n3 - Client connesso: " + client.getInetAddress().getHostAddress());
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

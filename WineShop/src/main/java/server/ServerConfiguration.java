package server;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class ServerConfiguration {
    private final String dbHost = "localhost";
    private final int serverPort = 8520;
    private final String dbUser = "root";
    private final String dbPassword = "";
    private final String dbName = "wine_shop";
    private final int dbPort = 3306;

    public ServerConfiguration(String path) {
        Gson gson = new Gson();
        ServerConfiguration conf = null;

        try {
            conf = gson.fromJson(new FileReader(path), ServerConfiguration.class);
        } catch (JsonSyntaxException | JsonIOException | FileNotFoundException e) {
            e.printStackTrace();
        }

        assert conf != null;
    }

    public String getDbHost() {
        return dbHost;
    }

    public int getServerPort() {
        return serverPort;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbName() {
        return dbName;
    }

    public int getDbPort() {
        return dbPort;
    }

    @Override
    public String toString() {
        return "ServerConfiguration{" +
                "dbHost='" + dbHost + '\'' +
                ", serverPort=" + serverPort +
                ", dbUser='" + dbUser + '\'' +
                ", dbPassword='" + dbPassword + '\'' +
                ", dbName='" + dbName + '\'' +
                ", dbPort=" + dbPort +
                '}';
    }
}

package server.managedb;

import server.ServerConfiguration;

import java.sql.*;
import java.util.*;

public class DataBaseManager {
    private final String user;
    private final String psw;

    public DataBaseManager(String user, String psw) {
        this.user = user;
        this.psw = psw;
    }

    /*
        La funzione esegue il wrapping del risultato della query in una struttura più agevole da gestire,
        rappresentata da una lista di mappe, dove ogni mappa rappresenta una riga del risultato.
    */
    private List<Map<String, String>> wrapQueryResult(ResultSet rs) throws SQLException {
        List<Map<String, String>> table = new ArrayList<Map<String, String>>();
        ResultSetMetaData metaData = rs.getMetaData();

        while (rs.next())
        {
            Map<String, String> map = new HashMap<String, String>();

            for (int i = 1; i <= metaData.getColumnCount(); i++)
            {
                String key = metaData.getColumnName(i);
                String value = rs.getString(key);
                map.put(key, value);
            }
            table.add(map);
        }
        return table;
    }

    /*
        esegue una query SQL su un database MySQL utilizzando JDBC e
        restituisce il risultato sotto forma di una lista di mappe (List<Map<String, String>>)
    */
    public List<Map<String, String>> executeSQLStatement(String query) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        ServerConfiguration conf = new ServerConfiguration("C:/Users/andre/OneDrive/Documenti/GitHub/WineShop/WineShop/src/main/java/server/ServerConfig.json");
        String url = "jdbc:mysql://" + conf.getDbHost() + ":" + conf.getDbPort() + "/" + conf.getDbName() + "?allowMultiQueries=true";
        String user = conf.getDbUser();
        String password = conf.getDbPassword();
        conn = DriverManager.getConnection(url, user, password);
        Statement statement = conn.createStatement();
        boolean isResultSet = statement.execute(query);
        boolean hashToBeWrapped = true;

        if(!isResultSet)
        {
            hashToBeWrapped = statement.getMoreResults();
        }

        ResultSet rs = statement.getResultSet();

        List<Map<String, String>> wr = null;

        if(hashToBeWrapped)
        {
            wr = wrapQueryResult(rs);
            rs.close();
        }
        conn.close();

        return wr;
    }
}

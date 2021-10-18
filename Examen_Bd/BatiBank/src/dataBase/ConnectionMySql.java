package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionMySql {

    //librería mySql
//    public String driver = "com.mysql.jdbc.Driver";

    //Nombre de la base de datos
    public String dataBase = "BatiBank2";

    //Host
    public String host_name = "localhost";

    // Puerto
    public String port = "3306";

    //url
    public String url = "jdbc:mysql://"+host_name+":"+port+"/"+dataBase+"?useSSL=false";

    //Nombre usuario
    public String username = "root";

    //Clave usuario
    public String password = "";

    public Connection connectionMySql(){
        Connection conn = null;

        try{
//            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,username,password);
        }catch (Exception e){
            e.printStackTrace();
        }

        return conn;
    }
}

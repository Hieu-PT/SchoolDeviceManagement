
package hieupt.conn;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection {
    public static Connection getMyConnection() throws Exception{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=FPTUDeviceManagement","sa","hieu123");
        return conn;
    }
}

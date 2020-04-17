package server;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class DBUtils {

    static Connection con = null;
    private static String PROJECT_DIR = System.getProperty("user.dir");
    private static String PATH_CONTEXT_FILE = PROJECT_DIR +
            File.separator
            + "src"
            + File.separator
            + "main"
            + File.separator
            + "webapp"
            + File.separator
            + "META-INF"
            + File.separator
            + "context.xml";

    // Add code get connection here
    public static boolean executeQuery(String sql) {
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            //1. Connection
            if (con != null) {
                //3. Create statement
                stm = con.prepareStatement(sql);
                //4. Execute Query
                rs = stm.executeQuery();
                //5. Process
                if (rs.next()) {
                    check = true;
                }//end while rs is not null
            }//end if con is not null
        } catch (Exception e) {
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stm != null) {
                    stm.close();
                }
            } catch (Exception e) {
            }
        }
        return check;
    }

    public static void executeUpdate(String sql) {
        PreparedStatement stm = null;
        try {
            //1. Connection
            if (con != null) {
                //2. Create Sql String
                //3. Create statement
                stm = con.prepareStatement(sql);
                //4. Execute Query
                stm.executeUpdate();
                //5. Process

            }//end if con is not null
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public static void makeConnection(String url, String username, String password) {
        if (con == null && !url.equals("")) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection(url, username, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean checkMakeConnection() {
        boolean check = true;
        String connectionContextStr = "";
        connectionContextStr = readFileAsString(PATH_CONTEXT_FILE).trim();
        if (!connectionContextStr.contains("com.microsoft.sqlserver.jdbc.SQLServerDriver")) {
            check = false;
        }
        if (!connectionContextStr.contains("jdbc:sqlserver://localhost:")) {
            check = false;
        }
        if (!connectionContextStr.contains("type=\"javax.sql.DataSource\"")) {
            check = false;
        }
        return check;
    }

    private static String readFileAsString(String fileName) {
        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
        }
        return text;
    }
    public static void closeConnection(){
        try {
            if(con !=null && !con.isClosed()){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

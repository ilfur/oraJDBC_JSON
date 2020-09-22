import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.json.bind.*;
import oracle.sql.*;

import java.util.Properties;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

public class testJSONB {
    public static void main(String[] args) {

        // SODA works on top of a regular JDBC connection.

        Connection con = null;

        try {
            /*
             * Where is your code running: in the database or outside?
             */
            if (System.getProperty("oracle.jserver.version") != null)
             {
             /*
              * You are in the database, already connected, use the default connection
              *
              */
                 OracleDataSource ods = new OracleDataSource();
                 ods.setURL("jdbc:default:connection");
                 con = ods.getConnection();
              }
             else {
              /*
               * You are not in the database, you need to connect thru the client driver
               */
                // Set up the connection string: replace hostName, port, and serviceName
                // with the info for your Oracle RDBMS instance
                //String url = "jdbc:oracle:thin:@repodb_tp?TNS_ADMIN=D:/Projects/json_jdbc/Wallet_RepoDB/";
                String url = "jdbc:oracle:thin:@//130.61.214.22/json.net1.marcelsservices.oraclevcn.com";
                //Class.forName("oracle.jdbc.OracleDriver");
                con = DriverManager.getConnection(url, "javasamples", "BrunhildeZ32##");

                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT data FROM emp_json");
                rs.next();
                //Yasson can handle a JsonParser as Input, JsonB RFC374 does NOT
                //using Yasson, it's not required to convert internal OSON to String for parsing.
                org.eclipse.yasson.YassonJsonb jsonb = (org.eclipse.yasson.YassonJsonb)JsonbBuilder.create();
                javax.json.stream.JsonParser parser = rs.getObject(1, javax.json.stream.JsonParser.class);
                EmpPojo e = jsonb.fromJson(parser, EmpPojo.class);
                String jobName = e.getJob();
                System.out.println ("Job Name: "+jobName);
            }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    finally {
      if (con != null) {
        try {
          con.close();
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    }
  }
}

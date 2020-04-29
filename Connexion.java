import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Connexion {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // create jdbc connection obj and load class
        Class.forName("oracle.jdbc.driver.OracleDriver");
        int user;
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "sam", "sammp");
        // create statement obj
        Statement stmt = conn.createStatement();
        Methodesbdd test = new Methodesbdd();

        test.add_mot_cle(conn);
        //test.rdv_psy(conn);
        //test.fin_rdv(conn);
        //user = test.login(conn);

        // if (user != -1)
        //   test.patient_consultation(conn, user);
        //test.ajout_patient(conn);
        // execute query

        // print database table records

    }

}
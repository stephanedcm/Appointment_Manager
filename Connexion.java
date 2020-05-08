import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;


public class Connexion {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
        // create jdbc connection obj and load class
        Class.forName("oracle.jdbc.driver.OracleDriver");
        int user;
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "sam", "sammp");
        // create statement obj
        Statement stmt = conn.createStatement();
        Methodesbdd test = new Methodesbdd();
        //user = test.login(conn);
        //if (user != -1)
        //test.patient_consultation(conn, user);
        //test.add_mot_cle(conn);
        //test.add_comportement(conn);
        //test.add_posture(conn);
        //test.rdv_psy(conn);
        //test.fin_rdv(conn);

        //test.ajout_patient(conn);
        //test.print_patient(conn);


    }

}
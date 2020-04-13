import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Connexion {

    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        // create jdbc connection obj and load class
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "sam", "sammp");
        // create statement obj
        Statement stmt= conn.createStatement();
        Methodesbdd test = new Methodesbdd();
        test.ajout_patient(conn);
        // execute query
        ResultSet rset = stmt.executeQuery("select * from Patient");
        // print database table records
        while(rset.next()) {
            System.out.print(rset.getInt(1));
            System.out.print("                ");
            System.out.print(rset.getString(2));
            System.out.print("                ");
            System.out.print(rset.getString(3));
            System.out.print("                ");
            System.out.print(rset.getString(4));
            System.out.print("                ");
            System.out.print(rset.getString(5));
            System.out.print("                ");
            System.out.print(rset.getString(6));
            System.out.print("                ");
            System.out.print(rset.getString(7));
            System.out.print("                ");
            System.out.print(rset.getString(8));
            System.out.print("                ");
            System.out.print(rset.getString(9));
            System.out.print("                ");
            System.out.println("\n");

                            }

        rset.close();
        stmt.close();
        conn.close();

    }
}

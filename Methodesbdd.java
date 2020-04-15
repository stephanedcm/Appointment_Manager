import java.io.File;
import java.sql.*;
import java.util.Scanner;

public class Methodesbdd
{

    public void patient_consultation(Connection conn, int user) throws SQLException, ClassNotFoundException
    {
        int id = user;
        Class.forName("oracle.jdbc.driver.OracleDriver");
        try
        {
            Statement stmt= conn.createStatement();
            ResultSet rset = stmt.executeQuery("select * from consultp where Id_patient ="+id);
            System.out.println("Prénom" +  "\t" +  "Nom" + "\t" + "Date consultation" + "\t" + "Type consultation");
            while(rset.next())
            {
                System.out.print(rset.getString("Prenom_patient") + "\t");
                System.out.print(rset.getString("Nom_patient") + "\t" + "\t");
                System.out.print(rset.getString("Date_consultation")+ "\t" +  "\t");
                System.out.println(rset.getString("Type_consultation") + "\t");
            }
        }
        catch (SQLException ex)
        {
            System.out.println("\n*** ERREUR SQL ***\n");
            while (ex != null)
            {
                System.out.println("SQL Etat: " + ex.getSQLState());
                System.out.println("Message: " + ex.getMessage());
                System.out.println("Code de l'erreur: " + ex.getErrorCode());
                ex = ex.getNextException();
            }
        }
    }

    public void ajout_patient(Connection conn) throws SQLException, ClassNotFoundException {

        Class.forName("oracle.jdbc.driver.OracleDriver");
        String Prenom_patient;
        String Nom_patient;
        String Email_patient;
        String Mdp_patient;
        String Sexe_patient;
        String Date_naissance;
        String Profession_actuelle;
        String Prospection;

        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Entrez le prénom du patient : ");
            Prenom_patient = in.nextLine();
            System.out.println("Entrez le nom du patient : ");
            Nom_patient = in.nextLine();
            System.out.println("Entrez l'email du patient : ");
            Email_patient = in.nextLine();
            System.out.println("Entrez le mot de passe du patient : ");
            Mdp_patient = in.nextLine();
            System.out.println("Entrez le sexe du patient : ");
            Sexe_patient = in.nextLine();
            System.out.println("Entrez la date de naissance du patient sous la forme suivante : yyyy-mm-dd ");
            Date_naissance = in.nextLine();
            System.out.println("Entrez la profession actuelle du patient: ");
            Profession_actuelle = in.nextLine();
            System.out.println("Entrez comment le patient vous a connu : ");
            Prospection = in.nextLine();
            Statement stmt = conn.createStatement();
            String query1 = "insert into Patient (Id_patient, Prenom_patient, Nom_patient, Email, Mdp_patient, Sexe, Date_naissance, Profession_actuelle, Prospection)" + " VALUES (t1_seq.nextval, '" + Prenom_patient + "',  '" + Nom_patient + "', '" + Email_patient + "', '" + Mdp_patient + "', '" + Sexe_patient + "', to_date('" + Date_naissance + "', 'yyyy-mm-dd'), '" + Profession_actuelle + "', '" + Prospection + "')";
            stmt.executeUpdate(query1);
        } catch (SQLException ex) {
// Si une exception SQL survient, il affiche les messages d’erreurs du SGBD
            System.out.println("\n*** ERREUR SQL ***\n");
            while (ex != null) {
                System.out.println("SQL Etat: " + ex.getSQLState());
                System.out.println("Message: " + ex.getMessage());
                System.out.println("Code de l'erreur: " + ex.getErrorCode());
                ex = ex.getNextException();
            }
        }
    }

    public int login(Connection conn) throws SQLException, ClassNotFoundException
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Scanner in = new Scanner(System.in);
        String username;
        boolean identification = false;
        String mdpadmin = null;
        String mdppatient = null;
        int cpt = 0;
        String motdepassetape;
        int id_user = 0;

        while(!identification)
        {
            try {
                    System.out.println("Enter your username : ");
                    username = in.nextLine();
                    if (username.equals("admin"))
                    {
                        System.out.println("Enter your password : ");
                        mdpadmin = in.nextLine();
                        if (mdpadmin.equals("admin"))
                        {
                            System.out.println("Bienvenue psychologue");
                            identification = true;
                            return -1;
                        }

                    }
                    else
                    {
                        Statement stmt = conn.createStatement();
                        ResultSet rset = stmt.executeQuery("select Id_patient, Mdp_patient from Patient where Email = '" + username + "'");
                        while (rset.next())
                        {
                            id_user = rset.getInt("Id_patient");
                            mdppatient = rset.getString("Mdp_patient");
                            cpt++;
                        }
                        if (cpt == 0)
                        {
                            System.out.println("Cet username n'est pas reconnu");
                        }
                        else
                        {
                            System.out.println("id user : " + id_user);
                            System.out.println("Entrez votre mot de passe : ");
                            motdepassetape = in.nextLine();
                            if (motdepassetape.equals(mdppatient))
                            {
                                return id_user;
                            }
                            else
                            {
                                System.out.println("Mauvais mot de passe, recommencez l'identification");
                            }

                        }

                    }
                 }
            catch (SQLException ex)
            {
// Si une exception SQL survient, il affiche les messages d’erreurs du SGBD
                System.out.println("\n*** ERREUR SQL ***\n");
                while (ex != null)
                {
                    System.out.println("SQL Etat: " + ex.getSQLState());
                    System.out.println("Message: " + ex.getMessage());
                    System.out.println("Code de l'erreur: " + ex.getErrorCode());
                    ex = ex.getNextException();
                }
            }
        }
        return 0;
    }
}

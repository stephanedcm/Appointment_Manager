import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Scanner;


public class Connexion {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
        // create jdbc connection obj and load class
        Class.forName("oracle.jdbc.driver.OracleDriver");
        int user;
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "steph", "admin"); //veuillez entrer les login de votre base de donnée après avoir executé notre script
        // create statement obj
        Statement stmt = conn.createStatement();
        Methodesbdd test = new Methodesbdd();
        String nom_patient="";
        String prenom_patient="";
        int choix_patient=0;
        boolean fin = false;
        int choix_psy_debut = 0;
        int choix_psy_ajout_print_patient = 0;
        boolean enduser = false;
        boolean finpsy = false;
        int choix_psy_mot = 0;
        Scanner in = new Scanner(System.in);
        while(!fin)
        {
            user = test.login(conn);
            if(user != -1)
            {
                enduser = false;
                while (!enduser)
                {
                    ResultSet rset = stmt.executeQuery("Select Nom_patient, Prenom_patient from Patient where Id_patient="+user);
                    while (rset.next())
                    {
                        nom_patient = rset.getString(1);
                        prenom_patient = rset.getString(2);
                    }
                    System.out.println("Bienvenue sur votre interface patient M/MME " + nom_patient + " " + prenom_patient);
                    while (choix_patient != 1 && choix_patient != 2 && choix_patient !=3)
                    {
                        System.out.println("1 : Consulter vos rendez-vous \n2 : Se déconnecter \n3 : Quitter l'application ");
                        choix_patient = in.nextInt();
                        if (choix_patient == 1)
                            test.patient_consultation(conn, user);
                        if (choix_patient == 2)
                        {
                            System.out.println("Vous vous déconnectez");
                            enduser = true;
                        }

                        if (choix_patient == 3)
                        {
                            System.out.println("Vous quittez l'application");
                            fin = true;
                            enduser = true;
                        }

                    }
                    choix_patient = 0;
                }


            }
            else
            {
                System.out.println("Bienvenue au psychologue que voulez-vous faire ?");
                finpsy = false;
                while (!finpsy)
                {
                    while(choix_psy_debut != 1 && choix_psy_debut !=2 && choix_psy_debut != 3 && choix_psy_debut != 4 && choix_psy_debut != 5 && choix_psy_debut != 6 && choix_psy_debut != 7)
                    {
                        System.out.println("1 : Ajouter/Afficher des patients \n2 : Gérer vos consultations \n3 : Afficher consultation sur un jour ou une semaine donnée \n4 : Finaliser un rendez-vous \n5 : Ajouter/Afficher mots-clés, comportement ou posture à un patient \n6 : Se déconnecter \n7 : Quitter l'application");
                        choix_psy_debut = in.nextInt();
                    }
                    if (choix_psy_debut == 1)
                    {
                        while(choix_psy_ajout_print_patient != 1 && choix_psy_ajout_print_patient != 2 && choix_psy_ajout_print_patient != 3)
                        {
                            System.out.println("1 : Ajouter patient \n2 : Afficher les patients \n3 : Revenir au menu précédent");
                            choix_psy_ajout_print_patient = in.nextInt();
                            if (choix_psy_ajout_print_patient == 1)
                            {
                                test.ajout_patient(conn);
                            }
                            else if (choix_psy_ajout_print_patient == 2)
                            {
                                test.print_patient(conn);
                            }
                            else if (choix_psy_ajout_print_patient == 3)
                            {

                            }
                        }
                        choix_psy_ajout_print_patient = 0;


                    }
                    else if (choix_psy_debut == 2)
                    {
                        test.rdv_psy(conn);
                    }
                    else if (choix_psy_debut == 3)
                    {
                        test.display_rdv(conn);
                    }
                    else if (choix_psy_debut == 4)
                    {
                        test.fin_rdv(conn);
                    }
                    else if (choix_psy_debut == 5)
                    {
                        while (choix_psy_mot < 1 || choix_psy_mot > 7)
                        {
                            System.out.println("1 : Ajouter mot clé \n2 : Ajouter Posture \n3 : Ajouter comportement \n4 : Afficher les mots clés d'un patient \n5 : Afficher les postures d'un patient \n6 : Afficher les comportements d'un patient \n7 : Revenir au menu précédent");
                            choix_psy_mot = in.nextInt();
                        }
                        if (choix_psy_mot == 1)
                        {
                            test.add_mot_cle(conn);
                        }
                        else if (choix_psy_mot == 2)
                        {
                            test.add_posture(conn);
                        }
                        else if (choix_psy_mot == 3)
                        {
                            test.add_comportement(conn);
                        }
                        else if (choix_psy_mot == 4)
                        {
                            test.print_mot_cle(conn);
                        }
                        else if (choix_psy_mot == 5)
                        {
                            test.print_posture(conn);
                        }
                        else if (choix_psy_mot == 6)
                        {
                            test.print_comportement(conn);
                        }
                        else if (choix_psy_mot == 7)
                        {

                        }
                        choix_psy_mot = 0;

                    }
                    else if (choix_psy_debut == 6)
                    {
                        System.out.println("Vous vous déconnectez");
                        finpsy = true;
                    }
                    else if (choix_psy_debut == 7)
                    {
                        System.out.println("Vous quittez l'application");
                        finpsy = true;
                        fin = true;
                    }
                    choix_psy_debut = 0;
                }










            }
        }







    }

}
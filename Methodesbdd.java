import javax.xml.transform.Result;
import java.io.File;
import java.sql.*;
import java.util.Scanner;

public class Methodesbdd
{
    public static void rdv_psy(Connection conn) throws SQLException {
        String jour="";
        String mois="";
        String annee="";
        String m="";
        String h="";
        String type="";
        System.out.println("Vous avez sélectionné l'ajout, la modification ou l'annulation d'une consultation.");
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object

        boolean check=false;
        while(!check){
            System.out.print("Tapez 1 pour ajouter une consultation, 2 pour la modifier, 3 pour l'annuler et 4 pour retourner au menu précédent: ");
            int whichOne_1 = scanner.nextInt();  // Read user input
            switch(whichOne_1){
                case 1:
                    int cpt=0;
                    Scanner scanner1 = new Scanner(System.in);
                    System.out.println("Vous avez choisi d'ajouter une consultation");
                    System.out.print("Veuillez rentrer le Prénom du patient: ");
                    String prenom = scanner1.nextLine();
                    System.out.print("Veuillez rentrer le Nom du patient: ");
                    String nom = scanner1.nextLine();
                    System.out.print("Veuillez rentrer la date de la consultation que vous voulez ajouter dans le format suivant jj/mm/yyyy/Ho/Mi: ");
                    String date = scanner1.nextLine();
                    Scanner scan = new Scanner(date);
                    scan.useDelimiter("/");
                    jour=scan.next();
                    mois=scan.next();
                    annee=scan.next();
                    h=scan.next();
                    m=scan.next();
                    // closing the scanner stream
                    scan.close();
                    System.out.print("Veuillez maintenant sélectionner le type de consultation, tapez 1 s'il s'agit d'une consultation individuelle, 2 en couple, 3 en famille: ");
                    int whichOne_2 = scanner.nextInt();
                    boolean check_1=false;
                    while(!check_1){
                        switch(whichOne_2){
                            case 1:
                                type="individuelle";
                                check_1=true;
                                break;
                            case 2:
                                type="couple";
                                check_1=true;
                                break;
                            case 3:
                                type="famille";
                                check_1=true;
                                break;
                            default:
                                System.out.print("Mauvais choix, veuillez taper 1 s'il s'agit d'une consultation individuelle, 2 en couple, 3 en famille: ");
                                scanner.reset();
                                whichOne_2 = scanner.nextInt();
                        }
                    }
                    System.out.println(annee+mois+jour+h+m+type);
                    // create statement obj
                    Statement stmt= conn.createStatement();
                    // execute query
                    stmt.executeUpdate("INSERT INTO Consultation VALUES (t1_seq.nextval,to_timestamp('"+annee+"-"+mois+"-"+jour+" "+h+":"+m+"','yyyy-mm-dd hh24:MI'),'"+type+"')");
                    System.out.println("Consultation ajoutée!");
                    break;
                    
                case 2:
                    stmt= conn.createStatement();
                    System.out.println("Vous avez choisi de modifier une consultation.");
                    System.out.print("Veuillez rentrer l'identifiant de la consultation que vous voulez modifier: ");
                    Scanner scanner2 = new Scanner(System.in);
                    int id_Consultation = scanner2.nextInt();
                    System.out.print("Que voulez-vous modifier? Tapez 1 s'il s'agit de l'heure de la consultation, 2 s'il s'agit du type de consultation: ");
                    int whichOne_3 = scanner2.nextInt();
                    switch(whichOne_3){
                        case 1:
                            System.out.print("Veuillez rentrer la nouvelle date de la consultation dans le format suivant jj/mm/yyyy/Ho/Mi: ");
                            Scanner scanner3 = new Scanner(System.in);
                            date = scanner3.nextLine();
                            Scanner scan1 = new Scanner(date);
                            scan1.useDelimiter("/");
                            jour=scan1.next();
                            mois=scan1.next();
                            annee=scan1.next();
                            h=scan1.next();
                            m=scan1.next();
                            // closing the scanner stream
                            scan1.close();
                            stmt.executeUpdate("UPDATE Consultation SET Date_Consultation = to_timestamp('"+annee+"-"+mois+"-"+jour+" "+h+":"+m+"','yyyy-mm-dd hh24:MI') WHERE Id_Consultation="+id_Consultation);
                            System.out.println("Informations relatives à la date de la consultation mises à jour!");
                            break;
                        case 2:
                            System.out.print("Veuillez sélectionner le nouveau type de la consultation, tapez 1 s'il s'agit d'une consultation individuelle, 2 en couple, 3 en famille: ");
                            int whichOne_4 = scanner2.nextInt();
                            boolean check_2=false;
                            while(!check_2){
                                switch(whichOne_4){
                                    case 1:
                                        type="individuelle";
                                        stmt.executeUpdate("UPDATE Consultation SET Type_Consultation = '"+type+"' WHERE Id_Consultation="+id_Consultation);
                                        check_2=true;
                                        break;
                                    case 2:
                                        type="couple";
                                        stmt.executeUpdate("UPDATE Consultation SET Type_Consultation = '"+type+"' WHERE Id_Consultation="+id_Consultation);
                                        check_2=true;
                                        break;
                                    case 3:
                                        type="famille";
                                        stmt.executeUpdate("UPDATE Consultation SET Type_Consultation = '"+type+"' WHERE Id_Consultation="+id_Consultation);
                                        check_2=true;
                                        break;
                                    default:
                                        System.out.print("Mauvais choix, veuillez taper 1 s'il s'agit d'une consultation individuelle, 2 en couple, 3 en famille: ");
                                        scanner.reset();
                                        whichOne_3 = scanner.nextInt();
                                }
                            }
                            System.out.println("Informations relatives à la date de la consultation mises à jour!");

                    }break;

                case 3:
                    System.out.println("Vous avez choisi d'annuler une consultation.");
                    System.out.print("Veuillez rentrer l'identifiant de la consultation que vous voulez annuler: ");
                    scanner1 = new Scanner(System.in);
                    id_Consultation = scanner1.nextInt();
                    stmt= conn.createStatement();
                    ResultSet rset= stmt.executeQuery("SELECT * from Consultation WHERE Id_Consultation="+id_Consultation);
                    cpt=0;
                    while(rset.next()) {
                        cpt++;
                    }
                    if(cpt==0){
                        System.out.println("L'id rentré ne correspond à aucune consultation. Veuillez vérifier l'Id rentré.");
                        break;
                    }
                    else{
                        stmt.executeUpdate("DELETE FROM Consultation WHERE Id_Consultation="+id_Consultation);
                        System.out.println("Consultation "+id_Consultation+" annulée.");
                        break;
                    }

                case 4:
                    check=true;
                    break;
                default:
                    System.out.println("Veuillez rentrer un champs valide.");
                    break;
            }

        }

    }

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

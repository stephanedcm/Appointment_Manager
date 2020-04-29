import oracle.jdbc.proxy.annotation.Pre;

import javax.xml.transform.Result;
import java.io.File;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.IllegalFormatCodePointException;
import java.util.Scanner;
import java.util.Date;

public class Methodesbdd
{

    public void add_mot_cle(Connection conn) throws SQLException {
        Scanner in = new Scanner(System.in);
        int nbr_mot_cle = 0;
        String mot_cle = "";
        int id_patient = 0;
        String prenom_patient = "";
        String nom_patient = "";
        int cpt = 0;
        int cptNextVal = 0;
        int id_mot_cle = 0;
        boolean first_time_zero = false;
        boolean first_time_after_zero = false;
        Statement stmt= conn.createStatement();

        System.out.println("Vous avez choisi d'ajouter un ou plusieurs mots clés \n");
        while(cpt == 0)
        {
            System.out.println("Entrez le nom de famille du patient à qui vous souhaitez les ajouter : ");
            nom_patient = in.nextLine();

            ResultSet rset = stmt.executeQuery("select Id_patient, Nom_patient, Prenom_patient from Patient where Nom_patient='"+ nom_patient +"'");
            System.out.println("N° Patient Nom patient \t  Prenom patient");
            while(rset.next())
            {
                cpt++;
                System.out.print(rset.getInt(1) + "\t");
                System.out.print(rset.getString(2) + "\t" + "\t" + "\t");
                System.out.print(rset.getString(3) + "\t");
                System.out.println("\n");
            }
        }
        System.out.println("Entrez le numéro du patient auquel vous souhaitez les ajouter : ");

        id_patient = in.nextInt();

        System.out.println("Entrez le nombre de mots clés que vous souhaitez ajouter au patient " + nom_patient + " " + prenom_patient +" : ");
        nbr_mot_cle = in.nextInt();





        for (int i=0; i<nbr_mot_cle; i++)
        {
            System.out.println("Entrez les mots-clés un à un : ");
            mot_cle = in.next();
            ResultSet rset = stmt.executeQuery("select mots_cles_seq.nextval from mots_cles where mots_cle_id=1");
            while(rset.next()) {
                cptNextVal++;
                id_mot_cle = (rset.getInt(1)+1);
            }
            if(cptNextVal==0){
                id_mot_cle=1;
            }
            stmt.executeUpdate("INSERT INTO mots_cles VALUES ("+id_mot_cle+", '"+mot_cle+"')");
            System.out.println("Id mot cle : " + id_mot_cle);
            stmt.executeUpdate("INSERT INTO patient_mots_cles VALUES ("+id_mot_cle + ", "+id_patient + ")");
        }

        ResultSet rset = stmt.executeQuery("select patient_mots_cles.id_patient, Prenom_patient, Nom_patient, patient_mots_cles.mots_cle_id, nom_mots_cle from mots_cles join patient_mots_cles on patient_mots_cles.mots_cle_id = mots_cles.mots_cle_id join Patient on Patient.Id_patient = Patient_mots_cles.id_patient where Patient.Id_patient ="+id_patient);
        System.out.println("N° Patient  Prénom patient    nom patient       mots cle id     nom mot cle    ");
        while (rset.next())
        {
            System.out.print(rset.getInt(1) + "\t");
            System.out.print(rset.getString(2) + "\t");
            System.out.print(rset.getString(3) + "\t");
            System.out.print(rset.getInt(4)+ "\t");
            System.out.print(rset.getString(5) + "\t");
            System.out.println("\n");
        }




    }


    public static void rdv_psy(Connection conn) throws SQLException {
        String jour="";
        String mois="";
        String annee="";
        String m="";
        String h="";
        String type="";
        int idConsultation=0;
        System.out.println("Vous avez sélectionné l'ajout, la modification ou l'annulation d'une consultation.");
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        boolean check=false;
        while(!check){
            System.out.print("Tapez 1 pour ajouter une consultation, 2 pour la modifier, 3 pour l'annuler et 4 pour retourner au menu précédent: ");
            int whichOne_1 = scanner.nextInt();  // Read user input
            switch(whichOne_1){
                case 1:
                    int cptNextVal=0;
                    Statement stmt= conn.createStatement();
                    ResultSet rset = stmt.executeQuery("select Consultation_seq.nextval from Consultation where Id_Consultation=1");
                    while(rset.next()) {
                        cptNextVal++;
                        idConsultation = (rset.getInt(1)+1);
                    }
                    if(cptNextVal==0){
                        idConsultation=1;
                    }
                    System.out.println("Vous avez choisi d'ajouter une consultation");
                    System.out.print("Veuillez sélectionner le type de consultation, tapez 1 s'il s'agit d'une consultation individuelle, 2 en couple, 3 en famille: ");
                    int whichOne_2 = scanner.nextInt();
                    int nbPatient=0;
                    int prix=0;
                    boolean check_1=false;
                    while(!check_1){
                        switch(whichOne_2){
                            case 1:
                                type="individuelle";
                                prix=50;
                                nbPatient=1;
                                check_1=true;
                                break;
                            case 2:
                                type="couple";
                                prix=80;
                                nbPatient=2;
                                check_1=true;
                                break;
                            case 3:
                                type="famille";
                                prix=100;
                                nbPatient=3;
                                check_1=true;
                                break;
                            default:
                                System.out.print("Mauvais choix, veuillez taper 1 s'il s'agit d'une consultation individuelle, 2 en couple, 3 en famille: ");
                                scanner.reset();
                                whichOne_2 = scanner.nextInt();
                        }
                    }
                    int cpt=0;
                    Scanner scanner1 = new Scanner(System.in);
                    for(int i=1; i<nbPatient+1;i++){
                        int cpt_patient=0;
                        String nom="";
                        System.out.println("Id" +  "\t" +  "Nom");
                        while(cpt_patient==0){
                            System.out.print("Veuillez rentrer le Nom du patient "+i+": ");
                            nom = scanner1.nextLine();
                            stmt= conn.createStatement();
                            rset = stmt.executeQuery("select Id_Patient,Nom_patient from Patient where Nom_patient ='"+nom+"'");
                            while(rset.next())
                            {
                                cpt_patient++;
                                System.out.print(rset.getString("Id_Patient") + "\t");
                                System.out.print(rset.getString("Nom_Patient") + "\t" + "\t");
                            }
                        }
                        System.out.print("Veuillez rentrer l'id du patient voulu: ");
                        int idPatient=scanner1.nextInt();
                        stmt= conn.createStatement();
                        System.out.println("Consultation: "+idConsultation+"Patient: "+idPatient);
                        stmt.executeUpdate("INSERT INTO Patient_consultation VALUES ("+idPatient+", "+idConsultation+", NULL, NULL, NULL, NULL)");
                        System.out.println("Patient "+nom+" ajouté à la consultation "+idConsultation);
                    }
                    System.out.print("Veuillez rentrer la date de la consultation que vous voulez ajouter dans le format suivant jj/mm/yyyy/Ho/Mi: ");
                    Scanner scanner4 = new Scanner(System.in);
                    String date = scanner4.nextLine();
                    Scanner scan2 = new Scanner(date);
                    scan2.useDelimiter("/");
                    jour=scan2.next();
                    mois=scan2.next();
                    annee=scan2.next();
                    h=scan2.next();
                    m=scan2.next();
                    // closing the scanner stream
                    scan2.close();
                    System.out.println(annee+mois+jour+h+m+type);
                    // create statement obj
                    stmt= conn.createStatement();
                    // execute query
                    stmt.executeUpdate("INSERT INTO Consultation VALUES ("+idConsultation+",to_timestamp('"+annee+"-"+mois+"-"+jour+" "+h+":"+m+"','yyyy-mm-dd hh24:MI'),'"+type+"',"+prix+")");
                    rset = stmt.executeQuery("select * from Consultation");
                    System.out.println("Id_Consultation" +  "\t" +  "Date" + "\t" + "Type" + "\t" + "Prix_consultation");
                    while(rset.next())
                    {
                        System.out.print(rset.getString("Id_consultation") + "\t");
                        System.out.print(rset.getString("Date_consultation") + "\t" + "\t");
                        System.out.print(rset.getString("Type_consultation") + "\t" + "\t");
                        System.out.print(rset.getString("Prix_consultation") + "\t" + "\t");
                        System.out.println("\n");
                    }
                    System.out.println();
                    rset = stmt.executeQuery("select * from Patient_consultation");
                    System.out.println("Id_patient" +  "\t" +  "Id_Consultation" + "\t" + "Indicateur_anxiete" + "\t" + "Type_reglement" + "\t" + "Type_Patient" + "\t" + "Retard");
                    while(rset.next())
                    {
                        System.out.print(rset.getString("Id_Patient") + "\t");
                        System.out.print(rset.getString("Id_Consultation") + "\t" + "\t");
                        System.out.print(rset.getString("Indicateur_anxiete") + "\t" + "\t");
                        System.out.print(rset.getString("Type_reglement") + "\t");
                        System.out.print(rset.getString("Type_Patient") + "\t" + "\t");
                        System.out.print(rset.getString("Retard") + "\t" + "\t");
                        System.out.println("\n");
                    }

                    System.out.println("Consultation ajoutée!");
                    break;
                    
                case 2:
                    stmt= conn.createStatement();
                    System.out.println("Vous avez choisi de modifier une consultation.");
                    int cpt_patient=0;
                    while(cpt_patient==0){
                        System.out.print("Veuillez rentrer le Nom du patient: ");
                        Scanner scanner2 = new Scanner(System.in);
                        String nom = scanner2.nextLine();
                        stmt= conn.createStatement();
                        rset = stmt.executeQuery("select Patient.Id_patient, Prenom_patient, Nom_patient, Patient_consultation.Id_consultation, Date_consultation from Patient join Patient_consultation on Patient.Id_patient = Patient_consultation.Id_patient join Consultation on Patient_consultation.Id_consultation = Consultation.Id_consultation where Nom_patient='"+nom+"'");
                        while(rset.next())
                        {
                            cpt_patient++;
                            System.out.print(rset.getString("Id_consultation") + "\t");
                            System.out.print(rset.getString("Date_consultation") + "\t");
                            System.out.print(rset.getString("Prenom_patient") + "\t");
                            System.out.print(rset.getString("Nom_patient") + "\t" + "\t");
                            System.out.println();
                        }
                    }
                    System.out.print("Entrez l'ID de la consultation dont vous souhaitez modifier l'heure: ");
                    Scanner scanner5 = new Scanner(System.in);
                    int id_Consultation = scanner5.nextInt();
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

                case 3:
                    System.out.println("Vous avez choisi d'annuler une consultation.");
                    cpt_patient=0;
                    while(cpt_patient==0){
                        System.out.print("Veuillez rentrer le Nom du patient: ");
                        Scanner scanner2 = new Scanner(System.in);
                        String nom = scanner2.nextLine();
                        stmt= conn.createStatement();
                        rset = stmt.executeQuery("select Patient.Id_patient, Prenom_patient, Nom_patient, Patient_consultation.Id_consultation, Date_consultation from Patient join Patient_consultation on Patient.Id_patient = Patient_consultation.Id_patient join Consultation on Patient_consultation.Id_consultation = Consultation.Id_consultation where Nom_patient='"+nom+"'");
                        while(rset.next())
                        {
                            cpt_patient++;
                            System.out.print(rset.getString("Id_consultation") + "\t");
                            System.out.print(rset.getString("Date_consultation") + "\t");
                            System.out.print(rset.getString("Prenom_patient") + "\t");
                            System.out.print(rset.getString("Nom_patient") + "\t" + "\t");
                            System.out.println();
                        }
                    }
                    System.out.print("Veuillez rentrer l'identifiant de la consultation que vous voulez annuler: ");
                    scanner1 = new Scanner(System.in);
                    id_Consultation = scanner1.nextInt();
                    stmt= conn.createStatement();
                    rset= stmt.executeQuery("SELECT * from Consultation WHERE Id_Consultation="+id_Consultation);
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
                        stmt.executeUpdate("DELETE FROM Patient_consultation WHERE Id_Consultation="+id_Consultation);
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

    public static void fin_rdv(Connection conn) throws SQLException{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        Statement stmt= conn.createStatement();
        ResultSet rset = stmt.executeQuery("select Patient.Id_patient, Prenom_patient, Nom_patient, Patient_consultation.Id_consultation, Date_consultation, Patient_consultation.Type_reglement, Patient_consultation.Retard, Patient_consultation.Indicateur_anxiete from Patient join Patient_consultation on Patient.Id_patient = Patient_consultation.Id_patient join Consultation on Patient_consultation.Id_consultation = Consultation.Id_consultation where Date_consultation<=to_timestamp('"+dateFormat.format(date)+"','yyyy-mm-dd hh24:MI') and (Type_reglement is null or Retard is null)");
        System.out.println("Vous avez fini un RDV.");
        System.out.println("Liste des consultations passées et non traitées :");
        System.out.println("N° Consultation" +  "\t" +  "Date consultation" + "\t" + "\t" + "Prénom patient" + "\t" + "Nom patient" + "\t" +  "Indicateur anxiete" + "\t" + "Type de reglement" + "\t" + "Retard");
        while(rset.next())
        {
            System.out.println("");
            System.out.print(rset.getString("Id_consultation") + "\t" + "\t" + "\t" + "\t" );
            System.out.print(rset.getString("Date_consultation") + "\t");
            System.out.print(rset.getString("Prenom_patient") + "\t" + "\t");
            System.out.print(rset.getString("Nom_patient") + "\t" + "\t" + "\t");
            if(rset.getObject("Indicateur_anxiete") == null)
                System.out.print("X                   ");
            else
                System.out.print(rset.getString("Indicateur anxiete                   ")+ "\t");

            if(rset.getObject("Type_reglement") == null)
                System.out.print("X                    ");
            else
                System.out.print(rset.getString("Type_reglement                 ")+ "\t");

            if(rset.getObject("Retard") == null)
                System.out.print("X                    ");
            else
                System.out.print(rset.getString("Retard                       ")+ "\t");

            System.out.println();

        }
        System.out.print("Entrez l'ID de la consultation que vous souhaitez traiter: ");
        Scanner scan = new Scanner(System.in);
        int id_Patient1 = 0;
        int id_Patient2 =0;
        int id_Patient3 = 0;
        int id_Consultation = scan.nextInt();
        int nbPatient=0;
        String Retard = "";
        String Nom_Patient1="";
        String Nom_Patient2="";
        String Nom_Patient3="";
        String Prenom_patient1 = "";
        String Prenom_patient2 ="";
        String Prenom_patient3="";
        int id_patient_retard = 0;
        int id_patient_non_retard1 =0;
        int id_patient_non_retard2 = 0;
        int type_payment=0;
        int indicateur_anxiete = 0;
        boolean payment_rempli = false;
        boolean retard_rempli1 = false;
        boolean indicateur_anxiete_rempli = false;
        String Stress = "";
        String personne_retard ="";
        String type_consultation="";
        int nbre_personne_retard = 0;
        rset = stmt.executeQuery("select Type_consultation from Consultation where Id_consultation="+id_Consultation);
        while(rset.next())
        {
            type_consultation=rset.getString(1);

        }
        switch(type_consultation){
            case "individuelle":
                nbPatient=1;
                rset = stmt.executeQuery("select Patient.Id_patient, Nom_patient, Prenom_patient, Patient_consultation.Id_consultation from Patient join Patient_consultation on Patient.Id_patient = Patient_consultation.Id_patient join Consultation on Patient_consultation.Id_consultation ="+id_Consultation);
                while(rset.next())
                {
                    id_Patient1=rset.getInt(1);
                    Nom_Patient1=rset.getString(2);
                    Prenom_patient1 = rset.getString(3);
                }

                rset = stmt.executeQuery("select Type_reglement, Retard, Indicateur_anxiete from Patient_consultation where Id_patient ="+id_Patient1+ "and Id_consultation = "+id_Consultation);
                while (rset.next())
                {
                    if(rset.getObject(1) != null)
                        payment_rempli = true;
                    if (rset.getObject(2) != null)
                        retard_rempli1 = true;
                    if (rset.getObject(3) != null)
                        indicateur_anxiete_rempli = true;
                }

                if (!indicateur_anxiete_rempli)
                {
                    while(!Stress.equals("O") && !Stress.equals("N"))
                    {
                        System.out.println("La consultation était-elle relative au stress ? Entrez O pour oui N pour non ");
                        Stress = scan.next();
                    }
                    if (Stress.equals("O"))
                    {
                        while (indicateur_anxiete < 1 || indicateur_anxiete > 10)
                        {
                            System.out.println("Entre l'indicateur d'anxiété entre 1 et 10 compris pour le patient : " + Nom_Patient1 + " " + Prenom_patient1);
                            indicateur_anxiete = scan.nextInt();
                        }

                        stmt.executeUpdate("Update Patient_consultation set Indicateur_anxiete = "+indicateur_anxiete + "where Id_consultation ="+id_Consultation + "and Id_patient ="+id_Patient1);
                    }


                }

                if (!payment_rempli)
                {
                    System.out.println("Choisissez le type de règlement : ");
                    System.out.print("Entrez 1 si CB, 2 si chèque, 3 si espèce : ");
                    type_payment = scan.nextInt();
                    if (type_payment == 1)
                        stmt.executeUpdate("Update Patient_consultation set Type_reglement = 'CB' where Id_consultation ="+id_Consultation + "and Id_patient ="+id_Patient1);
                    else if (type_payment == 2)
                        stmt.executeUpdate("Update Patient_consultation set Type_reglement = 'CHEQUE' where Id_consultation ="+id_Consultation + "and Id_patient ="+id_Patient1);
                    else if (type_payment == 3)
                        stmt.executeUpdate("Update Patient_consultation set Type_reglement = 'ESPECE' where Id_consultation ="+id_Consultation + "and Id_patient ="+id_Patient1);
                }

                if (!retard_rempli1)
                {
                    while(!Retard.equals("O") && !Retard.equals("N"))
                    {
                        System.out.println("Le patient était-il en retard ? Entrez O pour oui N pour non : ");
                        Retard = scan.next();
                        System.out.println("Retard : " + Retard);
                    }
                    if (Retard.equals("O"))
                        stmt.executeUpdate("Update Patient_consultation set Retard = 'Oui' where Id_consultation ="+id_Consultation + "and Id_patient ="+id_Patient1);
                    else
                        stmt.executeUpdate("Update Patient_consultation set Retard = 'Non' where Id_consultation ="+id_Consultation + "and Id_patient ="+id_Patient1);
                }

                if (retard_rempli1 && payment_rempli)
                    System.out.println("Type de réglement et détermination du retard traité pour le patient : " + Nom_Patient1 + " " + Nom_Patient1);

                break;
            case "couple":
                nbPatient=2;
                rset = stmt.executeQuery("select Patient.Id_patient, Nom_patient, Prenom_patient, Patient_consultation.Id_consultation from Patient join Patient_consultation on Patient.Id_patient = Patient_consultation.Id_patient join Consultation on Patient_consultation.Id_consultation ="+id_Consultation);
                while(rset.next())
                {
                    if (id_Patient1 == 0)
                        id_Patient1 =rset.getInt(1);
                    else
                        id_Patient2 = rset.getInt(1);

                    if (Prenom_patient1 .equals(""))
                        Prenom_patient1 =rset.getString(2);
                    else
                        Prenom_patient2 = rset.getString(2);

                    if (Nom_Patient1.equals(""))
                        Nom_Patient1 =rset.getString(3);
                    else
                        Nom_Patient2 = rset.getString(3);
                }

                rset = stmt.executeQuery("select Type_reglement, Retard, Indicateur_anxiete from Patient_consultation where Id_patient ="+id_Patient1+ "and Id_consultation = "+id_Consultation);
                while (rset.next()) {
                    if (rset.getObject(1) != null)
                        payment_rempli = true;
                    if (rset.getObject(2) != null)
                        retard_rempli1 = true;
                    if (rset.getObject(3) != null)
                        indicateur_anxiete_rempli = true;

                }

                if (!indicateur_anxiete_rempli)
                {
                    while(!Stress.equals("O") && !Stress.equals("N"))
                    {
                        System.out.println("La consultation était-elle relative au stress ? Entrez O pour oui N pour non ");
                        Stress = scan.next();
                    }
                    if (Stress.equals("O"))
                    {

                            while (indicateur_anxiete < 1 || indicateur_anxiete > 10)
                            {
                                System.out.println("Entre l'indicateur d'anxiété entre 1 et 10 compris pour le patient : " + Nom_Patient1 + " " + Prenom_patient1);
                                indicateur_anxiete = scan.nextInt();
                            }

                            stmt.executeUpdate("Update Patient_consultation set Indicateur_anxiete = "+indicateur_anxiete + "where Id_consultation ="+id_Consultation + "and Id_patient ="+id_Patient1);

                            indicateur_anxiete = 0;

                            while (indicateur_anxiete < 1 || indicateur_anxiete > 10)
                            {
                                System.out.println("Entre l'indicateur d'anxiété entre 1 et 10 compris pour le patient : " + Nom_Patient2 + " " + Prenom_patient2);
                                indicateur_anxiete = scan.nextInt();
                            }

                            stmt.executeUpdate("Update Patient_consultation set Indicateur_anxiete = "+indicateur_anxiete + "where Id_consultation ="+id_Consultation + "and Id_patient ="+id_Patient2);

                    }


                }

                if (!payment_rempli) {
                    System.out.println("Choisissez le type de règlement pour la consultation en couple : ");
                    System.out.print("Entrez 1 si CB, 2 si chèque, 3 si espèce : ");
                    type_payment = scan.nextInt();
                    if (type_payment == 1) {
                        stmt.executeUpdate("Update Patient_consultation set Type_reglement = 'CB' where Id_consultation =" + id_Consultation + "and Id_patient =" + id_Patient1);
                        stmt.executeUpdate("Update Patient_consultation set Type_reglement = 'CB' where Id_consultation =" + id_Consultation + "and Id_patient =" + id_Patient2);
                    } else if (type_payment == 2) {
                        stmt.executeUpdate("Update Patient_consultation set Type_reglement = 'CHEQUE' where Id_consultation =" + id_Consultation + "and Id_patient =" + id_Patient1);
                        stmt.executeUpdate("Update Patient_consultation set Type_reglement = 'CHEQUE' where Id_consultation =" + id_Consultation + "and Id_patient =" + id_Patient2);
                    } else if (type_payment == 3) {
                        stmt.executeUpdate("Update Patient_consultation set Type_reglement = 'ESPECE' where Id_consultation =" + id_Consultation + "and Id_patient =" + id_Patient1);
                        stmt.executeUpdate("Update Patient_consultation set Type_reglement = 'ESPECE' where Id_consultation =" + id_Consultation + "and Id_patient =" + id_Patient3);
                    }
                }

                if (!retard_rempli1)
                {
                    while(!personne_retard.equals("O") && !personne_retard.equals("N"))
                    {
                        System.out.println("Une personne ou plus était-elle en retard ? Entrez O pour oui, N pour non");
                        personne_retard = scan.next();
                    }

                    if (personne_retard.equals("O"))
                    {
                        while(nbre_personne_retard != 1 && nbre_personne_retard != 2)
                        {
                            System.out.println("Entrez le nombre de personnes en retard : ");
                            nbre_personne_retard = scan.nextInt();
                        }
                        if (nbre_personne_retard == 2)
                        {
                            stmt.executeUpdate("Update Patient_consultation set Retard = 'Oui' where Id_consultation ="+id_Consultation + "and Id_patient ="+id_Patient1);
                            stmt.executeUpdate("Update Patient_consultation set Retard = 'Oui' where Id_consultation ="+id_Consultation + "and Id_patient ="+id_Patient2);
                        }
                        if (nbre_personne_retard != 2)
                        {
                            rset = stmt.executeQuery("select Patient.Id_patient, Nom_patient, Prenom_patient from Patient where Id_patient in (" + id_Patient1 + "," + id_Patient2 + ")");
                            System.out.println("ID Patient " + "\t" + "Nom Patient" + "\t" + "Prénom Patient");
                            while (rset.next())
                            {
                                System.out.print(rset.getInt("Id_patient") + "\t");
                                System.out.print(rset.getString("Nom_patient") + "\t");
                                System.out.print(rset.getString("Prenom_patient")+ "\t");
                                System.out.println("\n");
                            }
                            System.out.println("Entrez l'ID de la personne qui a été en retard : ");
                            id_patient_retard = scan.nextInt();
                            stmt.executeUpdate("Update Patient_consultation set Retard = 'Oui' where Id_consultation ="+id_Consultation + "and Id_patient ="+id_patient_retard);

                            rset = stmt.executeQuery("select Patient.Id_patient, Nom_patient, Prenom_patient from Patient where Id_patient in (" + id_Patient1 + "," + id_Patient2 + ")");
                            System.out.println("ID Patient " + "\t" + "Nom Patient" + "\t" + "Prénom Patient");
                            while (rset.next())
                            {
                                System.out.print(rset.getInt("Id_patient") + "\t");
                                System.out.print(rset.getString("Nom_patient") + "\t");
                                System.out.print(rset.getString("Prenom_patient")+ "\t");
                                System.out.println("\n");
                            }
                            System.out.println("Entrez l'ID de la personne qui n'a pas été en retard : ");
                            id_patient_non_retard1 = scan.nextInt();
                            stmt.executeUpdate("Update Patient_consultation set Retard = 'Non' where Id_consultation ="+id_Consultation + "and Id_patient ="+id_patient_non_retard1);
                        }
                    }
                    else
                    {
                        stmt.executeUpdate("Update Patient_consultation set Retard = 'Non' where Id_consultation ="+id_Consultation + "and Id_patient ="+id_Patient1);
                        stmt.executeUpdate("Update Patient_consultation set Retard = 'Non' where Id_consultation ="+id_Consultation + "and Id_patient ="+id_Patient2);
                    }
                }


                break;
            case "famille":
                nbPatient=3;
                rset = stmt.executeQuery("select Patient.Id_patient, Nom_patient, Prenom_patient, Patient_consultation.Id_consultation from Patient join Patient_consultation on Patient.Id_patient = Patient_consultation.Id_patient join Consultation on Patient_consultation.Id_consultation ="+id_Consultation);
                while(rset.next())
                {
                    if (id_Patient1 == 0)
                        id_Patient1 =rset.getInt(1);
                    else if (id_Patient2 == 0)
                        id_Patient2 = rset.getInt(1);
                    else
                        id_Patient3 = rset.getInt(1);

                    if (Prenom_patient1 .equals(""))
                        Prenom_patient1 =rset.getString(2);
                    else if (Prenom_patient2.equals(""))
                        Prenom_patient2 = rset.getString(2);
                    else
                        Prenom_patient3 = rset.getString(2);

                    if (Nom_Patient1.equals(""))
                        Nom_Patient1 =rset.getString(3);
                    else if (Nom_Patient2.equals(""))
                        Nom_Patient2 = rset.getString(3);
                    else
                        Nom_Patient3 = rset.getString(3);
                }

                rset = stmt.executeQuery("select Type_reglement, Retard, Indicateur_anxiete from Patient_consultation where Id_patient ="+id_Patient1+ "and Id_consultation = "+id_Consultation);
                while (rset.next()) {
                    if (rset.getObject(1) != null)
                        payment_rempli = true;
                    if (rset.getObject(2) != null)
                        retard_rempli1 = true;
                    if (rset.getObject(3) != null)
                        indicateur_anxiete_rempli = true;
                }

                if (!indicateur_anxiete_rempli)
                {
                    while(!Stress.equals("O") && !Stress.equals("N"))
                    {
                        System.out.println("La consultation était-elle relative au stress ? Entrez O pour oui N pour non ");
                        Stress = scan.next();
                    }
                    if (Stress.equals("O"))
                    {

                        while (indicateur_anxiete < 1 || indicateur_anxiete > 10)
                        {
                            System.out.println("Entre l'indicateur d'anxiété entre 1 et 10 compris pour le patient : " + Nom_Patient1 + " " + Prenom_patient1);
                            indicateur_anxiete = scan.nextInt();
                        }

                        stmt.executeUpdate("Update Patient_consultation set Indicateur_anxiete = "+indicateur_anxiete + "where Id_consultation ="+id_Consultation + "and Id_patient ="+id_Patient1);

                        indicateur_anxiete = 0;

                        while (indicateur_anxiete < 1 || indicateur_anxiete > 10)
                        {
                            System.out.println("Entre l'indicateur d'anxiété entre 1 et 10 compris pour le patient : " + Nom_Patient2 + " " + Prenom_patient2);
                            indicateur_anxiete = scan.nextInt();
                        }

                        stmt.executeUpdate("Update Patient_consultation set Indicateur_anxiete = "+indicateur_anxiete + "where Id_consultation ="+id_Consultation + "and Id_patient ="+id_Patient2);

                        indicateur_anxiete = 0;

                        while (indicateur_anxiete < 1 || indicateur_anxiete > 10)
                        {
                            System.out.println("Entre l'indicateur d'anxiété entre 1 et 10 compris pour le patient : " + Nom_Patient3 + " " + Prenom_patient3);
                            indicateur_anxiete = scan.nextInt();
                        }

                        stmt.executeUpdate("Update Patient_consultation set Indicateur_anxiete = "+indicateur_anxiete + "where Id_consultation ="+id_Consultation + "and Id_patient ="+id_Patient3);


                    }

                }

                if (!payment_rempli)
                {
                    System.out.println("Choisissez le type de règlement pour la consultation en famille : ");
                    System.out.print("Entrez 1 si CB, 2 si chèque, 3 si espèce : ");
                    type_payment = scan.nextInt();
                    if (type_payment == 1) {
                        stmt.executeUpdate("Update Patient_consultation set Type_reglement = 'CB' where Id_consultation =" + id_Consultation + "and Id_patient =" + id_Patient1);
                        stmt.executeUpdate("Update Patient_consultation set Type_reglement = 'CB' where Id_consultation =" + id_Consultation + "and Id_patient =" + id_Patient2);
                        stmt.executeUpdate("Update Patient_consultation set Type_reglement = 'CB' where Id_consultation =" + id_Consultation + "and Id_patient =" + id_Patient3);
                    } else if (type_payment == 2) {
                        stmt.executeUpdate("Update Patient_consultation set Type_reglement = 'CHEQUE' where Id_consultation =" + id_Consultation + "and Id_patient =" + id_Patient1);
                        stmt.executeUpdate("Update Patient_consultation set Type_reglement = 'CHEQUE' where Id_consultation =" + id_Consultation + "and Id_patient =" + id_Patient2);
                        stmt.executeUpdate("Update Patient_consultation set Type_reglement = 'CHEQUE' where Id_consultation =" + id_Consultation + "and Id_patient =" + id_Patient3);
                    } else if (type_payment == 3) {
                        stmt.executeUpdate("Update Patient_consultation set Type_reglement = 'ESPECE' where Id_consultation =" + id_Consultation + "and Id_patient =" + id_Patient1);
                        stmt.executeUpdate("Update Patient_consultation set Type_reglement = 'ESPECE' where Id_consultation =" + id_Consultation + "and Id_patient =" + id_Patient3);
                        stmt.executeUpdate("Update Patient_consultation set Type_reglement = 'ESPECE' where Id_consultation =" + id_Consultation + "and Id_patient =" + id_Patient3);
                    }
                }

            if (!retard_rempli1)
            {
                System.out.println("Est-ce que la famille était en retard ? Entrez O pour oui N pour non");

                Retard = scan.next();
                if (Retard.equals("O"))
                {
                    stmt.executeUpdate("Update Patient_consultation set Retard = 'Oui' where Id_consultation =" + id_Consultation + "and Id_patient =" + id_Patient1);
                    stmt.executeUpdate("Update Patient_consultation set Retard = 'Oui' where Id_consultation =" + id_Consultation + "and Id_patient =" + id_Patient2);
                    stmt.executeUpdate("Update Patient_consultation set Retard = 'Oui' where Id_consultation =" + id_Consultation + "and Id_patient =" + id_Patient3);
                }
                else
                {
                    stmt.executeUpdate("Update Patient_consultation set Retard = 'Non' where Id_consultation =" + id_Consultation + "and Id_patient =" + id_Patient1);
                    stmt.executeUpdate("Update Patient_consultation set Retard = 'Non' where Id_consultation =" + id_Consultation + "and Id_patient =" + id_Patient2);
                    stmt.executeUpdate("Update Patient_consultation set Retard = 'Non' where Id_consultation =" + id_Consultation + "and Id_patient =" + id_Patient3);
                }
            }




                break;
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

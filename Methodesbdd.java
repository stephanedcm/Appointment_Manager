import oracle.jdbc.proxy.annotation.Pre;

import javax.xml.transform.Result;
import java.io.File;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    }

    public void add_comportement(Connection conn) throws SQLException {
        Scanner in = new Scanner(System.in);
        int nbr_comportement = 0;
        String comportement = "";
        int id_patient = 0;
        String prenom_patient = "";
        String nom_patient = "";
        int cpt = 0;
        int cptNextVal = 0;
        int id_comportement = 0;
        Statement stmt= conn.createStatement();

        System.out.println("Vous avez choisi d'ajouter un ou plusieurs comportements \n");
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

        System.out.println("Entrez le nombre de Comportements que vous souhaitez ajouter au patient " + nom_patient + " " + prenom_patient +" : ");
        nbr_comportement = in.nextInt();





        for (int i=0; i<nbr_comportement; i++)
        {
            System.out.println("Entrez les comportements un à un : ");
            comportement = in.next();
            ResultSet rset = stmt.executeQuery("select Comportement_seq.nextval from Comportement where comportement_id=1");
            while(rset.next()) {
                cptNextVal++;
                id_comportement= (rset.getInt(1)+1);
            }
            if(cptNextVal==0){
                id_comportement=1;
            }
            stmt.executeUpdate("INSERT INTO Comportement VALUES ("+id_comportement+", '"+comportement+"')");
            stmt.executeUpdate("INSERT INTO patient_comportement VALUES ("+id_comportement + ", "+id_patient + ")");
        }

    }

    public void add_posture(Connection conn) throws SQLException {
        Scanner in = new Scanner(System.in);
        int nbr_posture = 0;
        String posture = "";
        int id_patient = 0;
        String prenom_patient = "";
        String nom_patient = "";
        int cpt = 0;
        int cptNextVal = 0;
        int id_posture = 0;
        Statement stmt= conn.createStatement();

        System.out.println("Vous avez choisi d'ajouter une ou plusieurs posture \n");
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

        System.out.println("Entrez le nombre de posture que vous souhaitez ajouter au patient " + nom_patient + " " + prenom_patient +" : ");
        nbr_posture = in.nextInt();





        for (int i=0; i<nbr_posture; i++)
        {
            System.out.println("Entrez les Postures une à une : ");
            posture = in.next();
            ResultSet rset = stmt.executeQuery("select Posture_seq.nextval from Posture where Posture_id=1");
            while(rset.next()) {
                cptNextVal++;
                id_posture= (rset.getInt(1)+1);
            }
            if(cptNextVal==0){
                id_posture=1;
            }
            stmt.executeUpdate("INSERT INTO Posture VALUES ("+id_posture+", '"+posture+"')");
            stmt.executeUpdate("INSERT INTO patient_posture VALUES ("+id_posture + ", "+id_patient + ")");
        }

    }

    public void display_rdv(Connection conn) throws SQLException, ParseException {
        String jour="";
        String mois="";
        String annee="";
        int annee1=0;
        int num=0;
        boolean check=true;
        while(check==true){
            System.out.println("1 : Afficher les consultations d'une journée \n2 : Afficher les consultations d'une semaine");
            Scanner scanner = new Scanner(System.in);  // Create a Scanner object
            int whichOne_1 = scanner.nextInt();
            switch(whichOne_1){
                case 1:
                    System.out.print("Veuillez entrer la date du jour voulu dont vous voulez afficher les consultations avec le format dd/mm/aaaa: ");
                    Scanner scanner4 = new Scanner(System.in);
                    String date = scanner4.nextLine();
                    Scanner scan2 = new Scanner(date);
                    scan2.useDelimiter("/");
                    jour=scan2.next();
                    mois=scan2.next();
                    annee=scan2.next();
                    SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
                    String dateInString1 = ""+jour+"-"+mois+"-"+annee+"";
                    Date date3 = formatter1.parse(dateInString1);
                    Calendar cal1 = Calendar.getInstance();
                    cal1.setTime(date3);
                    cal1.add(Calendar.DATE, 1);
                    int annee_1=cal1.get(Calendar.YEAR);
                    int mois_1=cal1.get(Calendar.MONTH)+1;
                    int jour_1=cal1.get(Calendar.DAY_OF_MONTH);

                    Statement stmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
                    ResultSet rset = stmt.executeQuery("SELECT * FROM Consultation WHERE Date_consultation>=to_timestamp('"+annee+"-"+mois+"-"+jour+"','yyyy-mm-dd') AND Date_consultation<to_timestamp('"+annee_1+"-"+mois_1+"-"+jour_1+"','yyyy-mm-dd')");
                    rset.last();
                    int count_rdv = rset.getRow();
                    rset.beforeFirst();
                    if(count_rdv==0){
                        System.out.println("Aucune consultation enregistrée ce jour-ci.");
                    }
                    else{
                        stmt= conn.createStatement();
                        rset = stmt.executeQuery("select Patient.Id_patient, Prenom_patient, Nom_patient, Patient_consultation.Id_consultation, Date_consultation, Patient_consultation.Type_reglement, Patient_consultation.Retard, Patient_consultation.Indicateur_anxiete from Patient join Patient_consultation on Patient.Id_patient = Patient_consultation.Id_patient join Consultation on Patient_consultation.Id_consultation = Consultation.Id_consultation WHERE Date_consultation>=to_timestamp('"+annee+"-"+mois+"-"+jour+"','yyyy-mm-dd') AND Date_consultation<to_timestamp('"+annee_1+"-"+mois_1+"-"+jour_1+"','yyyy-mm-dd') and (Type_reglement is null or Retard is null)");
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
                    }
                    check=false;
                    break;
                case 2:
                    System.out.print("Veuillez entrer le numéro de la semaine dont laquelle vous voulez afficher les consultations ainsi que l'année correspondante sous le format ss/aaaa: ");
                    Scanner scanner1 = new Scanner(System.in);
                    String date1 = scanner1.nextLine();
                    Scanner scan1 = new Scanner(date1);
                    scan1.useDelimiter("/");
                    num=scan1.nextInt();
                    annee1=scan1.nextInt();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    String dateInString = ""+"01"+"-"+"01"+"-"+annee1+"";
                    Date date2 = formatter.parse(dateInString);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date2);
                    cal.set(Calendar.WEEK_OF_YEAR, num);
                    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                    int annee_monday=cal.get(Calendar.YEAR);
                    int mois_monday=cal.get(Calendar.MONTH)+1;
                    int jour_monday=cal.get(Calendar.DAY_OF_MONTH);
                    //System.out.println(formatter.format(cal.getTime()));
                    //System.out.println(annee_monday+" "+ mois_monday+" "+jour_monday);
                    cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                    int annee_saturday=cal.get(Calendar.YEAR);
                    int mois_saturday=cal.get(Calendar.MONTH)+1;
                    int jour_saturday=cal.get(Calendar.DAY_OF_MONTH)+1;
                    //System.out.println(formatter.format(cal.getTime()));
                    //System.out.println(annee_saturday+" "+mois_saturday+" "+jour_saturday);
                    stmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
                    rset = stmt.executeQuery("SELECT * FROM Consultation WHERE Date_consultation>=to_timestamp('"+annee_monday+"-"+mois_monday+"-"+jour_monday+"','yyyy-mm-dd') AND Date_consultation<=to_timestamp('"+annee_saturday+"-"+mois_saturday+"-"+jour_saturday+"','yyyy-mm-dd')");
                    rset.last();
                    int count_rdv1 = rset.getRow();
                    rset.beforeFirst();
                    if(count_rdv1==0){
                        System.out.println("Aucune consultation enregistrée à cette semaine.");
                    }
                    else{
                        stmt= conn.createStatement();
                        rset = stmt.executeQuery("select Patient.Id_patient, Prenom_patient, Nom_patient, Patient_consultation.Id_consultation, Date_consultation, Patient_consultation.Type_reglement, Patient_consultation.Retard, Patient_consultation.Indicateur_anxiete from Patient join Patient_consultation on Patient.Id_patient = Patient_consultation.Id_patient join Consultation on Patient_consultation.Id_consultation = Consultation.Id_consultation WHERE Date_consultation>=to_timestamp('"+annee_monday+"-"+mois_monday+"-"+jour_monday+"','yyyy-mm-dd') AND Date_consultation<=to_timestamp('"+annee_saturday+"-"+mois_saturday+"-"+jour_saturday+"','yyyy-mm-dd')");
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
                    }
                    check=false;
                    break;
                default:
                    System.out.println("Veuillez choisir une option valide");
                    break;

            }
        }



    }

    public static int rdv_psy(Connection conn) throws SQLException, ParseException {
        String jour="";
        String mois="";
        String annee="";
        String m="";
        String h="";
        String type="";
        int idConsultation=0;
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        boolean check=false;
        while(!check){
            System.out.println("1 : Ajouter une consultation \n2 : Modifier la date d'une consultation \n3 : Annuler une consultation \n4 : Retourner au menu précédent");
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
                    System.out.println("Vous avez choisi d'ajouter une consultation.");
                    System.out.print("Veuillez sélectionner le type de consultation, tapez 1 s'il s'agit d'une consultation individuelle, 2 en couple, 3 en famille, 4 pour revenir au menu principal: ");
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
                            case 4:
                                return 0;

                            default:
                                System.out.print("Mauvais choix, veuillez taper 1 s'il s'agit d'une consultation individuelle, 2 en couple, 3 en famille, 4 pour revenir au menu principal: ");
                                scanner.reset();
                                whichOne_2 = scanner.nextInt();
                        }
                    }
                    int cpt=0;
                    Scanner scanner1 = new Scanner(System.in);
                    for(int i=1; i<nbPatient+1;i++){
                        int cpt_patient=0;
                        String nom="";
                        while(cpt_patient==0){
                            System.out.print("Veuillez rentrer le Nom du patient "+i+" ou entrez 'retour' pour revenir au menu principal: ");
                            nom = scanner1.nextLine();
                            if(nom.contains("retour")){
                                return 0;
                            }
                            stmt= conn.createStatement();
                            rset = stmt.executeQuery("select Id_Patient,Nom_patient from Patient where Nom_patient ='"+nom+"'");
                            if(cpt_patient==0){
                                System.out.println("Le nom du patient rentré est inconnu.");
                            }
                            while(rset.next()) {
                                System.out.println("Id" + "\t" + "Nom");
                                cpt_patient++;
                                System.out.print(rset.getString("Id_Patient") + "\t");
                                System.out.print(rset.getString("Nom_Patient") + "\t" + "\t");
                            }
                        }
                        System.out.print("\nVeuillez rentrer l'id du patient voulu: ");
                        int idPatient=scanner1.nextInt();
                        stmt= conn.createStatement();
                        System.out.println("Consultation: "+idConsultation+" Patient: "+idPatient);
                        stmt.executeUpdate("INSERT INTO Patient_consultation VALUES ("+idPatient+", "+idConsultation+", NULL, NULL, NULL, NULL)");
                        //System.out.println("Patient "+nom+" ajouté à la consultation "+idConsultation);
                    }
                    int check_2=0;
                    while(check_2!=6){
                        check_2=0;
                        System.out.print("Veuillez rentrer la nouvelle date de la consultation dans le format suivant jj/mm/yyyy/Ho/Mi: ");
                        Scanner scanner4 = new Scanner(System.in);
                        String date = scanner4.nextLine();
                        Scanner scan2 = new Scanner(date);
                        scan2.useDelimiter("/");
                        jour=scan2.next();
                        mois=scan2.next();
                        annee=scan2.next();
                        h=scan2.next();
                        m=scan2.next();
                        Date today = Calendar.getInstance().getTime();
                        Calendar today_calendar = Calendar.getInstance();
                        today_calendar.setTime(today);
                        int today_annee=today_calendar.get(Calendar.YEAR);
                        int today_mois=today_calendar.get(Calendar.MONTH)+1;
                        int today_jour=today_calendar.get(Calendar.DAY_OF_MONTH);
                        int today_hour=today_calendar.get(Calendar.HOUR_OF_DAY);
                        int today_minute=today_calendar.get(Calendar.MINUTE);
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                        String dateInString = ""+jour+"-"+mois+"-"+annee+"";
                        Date date1 = formatter.parse(dateInString);
                        SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                        String dateInString1 = ""+jour+"-"+mois+"-"+annee+" "+""+h+":"+m+"";
                        Date date2 = formatter1.parse(dateInString1);
                        //System.out.println(date1);
                        //System.out.println(formatter.format(date1));
                        Calendar c = Calendar.getInstance();
                        c.setTime(date1);
                        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                        c.add(Calendar.DATE, 1);
                        int annee1=c.get(Calendar.YEAR);
                        int mois1=c.get(Calendar.MONTH)+1;
                        int jour1=c.get(Calendar.DAY_OF_MONTH);
                        int hour = Integer.parseInt(h);
                        int minute = Integer.parseInt(m);
                        int year = Integer.parseInt(annee);
                        int month = Integer.parseInt(mois);
                        int dayy = Integer.parseInt(jour);
                        Calendar calendar1 = Calendar.getInstance();
                        Calendar calendar2 = Calendar.getInstance();
                        calendar1.setTime(date2);
                        calendar2.setTime(date2);
                        //System.out.println("Original = " + calendar1.getTime());
                        //System.out.println("Original = " + calendar2.getTime());
                        // Add 30 minutes to the calendar time
                        calendar1.add(Calendar.MINUTE, 30);
                        // Substract 2 hour from the current time
                        calendar2.add(Calendar.MINUTE, -30);
                        //System.out.println("Updated  = " + calendar1.getTime());
                        //System.out.println("Updated  = " + calendar2.getTime());
                        int hour_plus = calendar1.get(Calendar.HOUR_OF_DAY);     // gets the current month
                        int minute_plus = calendar1.get(Calendar.MINUTE);
                        int hour_minus = calendar2.get(Calendar.HOUR_OF_DAY);    // gets the current month
                        int minute_minus = calendar2.get(Calendar.MINUTE);
                        //System.out.println(hour_minus+" "+minute_minus+" "+hour_plus+" "+minute_plus);
                        //System.out.println(annee+" "+mois+" "+jour);
                        //System.out.println(annee1+" "+mois1+" "+jour1);
                        //System.out.println(dayOfWeek);
                        // closing the scanner stream
                        scan2.close();
                        //System.out.println(annee+mois+jour+h+m+type);
                        //System.out.println(today_annee+" "+today_mois+" "+today_jour+" "+year+" "+month+" "+dayy);
                        stmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,
                                ResultSet.CONCUR_READ_ONLY);
                        rset = stmt.executeQuery("SELECT * FROM Consultation WHERE Date_consultation>to_timestamp('"+annee+"-"+mois+"-"+jour+" "+hour_minus+":"+minute_minus+"','yyyy-mm-dd hh24:MI') AND Date_consultation<to_timestamp('"+annee+"-"+mois+"-"+jour+" "+hour_plus+":"+minute_plus+"','yyyy-mm-dd hh24:MI')");
                        rset.last();
                        int count_rdv = rset.getRow();
                        rset.beforeFirst();
                        //System.out.println(hour_minus+" "+minute_minus+" "+hour_plus+" "+minute_plus);
                        //System.out.println(count_rdv);
                        if(count_rdv==0){
                            check_2++;
                        }
                        else{
                            System.out.println("Il y a déjà une consultation sur cette tranche horaire");
                            check_2=0;
                        }

                        if(today_annee<=year){
                            if(today_mois<month){
                                check_2++;
                            }
                            else if(today_mois==month){
                                if(today_jour<dayy){
                                    check_2++;
                                }
                                else if(today_jour==dayy){
                                    if(today_hour<hour){
                                        check_2++;
                                    }
                                    else if(today_hour==hour){
                                        if(today_minute<minute){
                                            check_2++;
                                        }
                                        else{
                                            System.out.println("Veuillez rentrer une heure future.");
                                            check_2=0;
                                        }
                                    }
                                    else{
                                        System.out.println("Veuillez rentrer une heure future.");
                                        check_2=0;
                                    }
                                }
                                else{
                                    System.out.println("Veuillez rentrer une date future.");
                                    check_2=0;
                                }
                            }
                            else{
                                System.out.println("Veuillez rentrer une date future.");
                                check_2=0;
                            }
                        }
                        else{
                            System.out.println("Veuillez rentrer une date future.");
                            check_2=0;
                        }
                        if(minute>=0 && minute<60){
                            check_2++;
                        }
                        else{
                            System.out.println("Veuillez rentrer une heure valide.");
                            check_2=0;
                        }
                        if(hour>=8 && hour<=20){
                            check_2++;
                        }
                        else{
                            System.out.println("Les heures de RDV ne sont comprises qu'entre 8h et 20h.");
                            check_2=0;
                        }
                        if(dayOfWeek!=1){
                            check_2++;
                        }
                        else{
                            System.out.println("Les RDV ne sont pas possibles le dimanche.");
                            check_2=0;
                        }
                        stmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,
                                ResultSet.CONCUR_READ_ONLY);
                        rset = stmt.executeQuery("SELECT * FROM Consultation WHERE Date_consultation>=to_timestamp('"+annee+"-"+mois+"-"+jour+"','yyyy-mm-dd') AND Date_consultation<to_timestamp('"+annee1+"-"+mois1+"-"+jour1+"','yyyy-mm-dd')");
                        rset.last();
                        int count = rset.getRow();
                        rset.beforeFirst();
                        //System.out.println(count);
                        if(count<20){
                            check_2++;
                            //System.out.println(check_3);
                        }
                        else{
                            System.out.println("La psychologue ne peut travailler que 10 heures par jour.");
                            check_2=0;
                        }

                        //System.out.println(check_2);
                    }

                    // create statement obj
                    stmt= conn.createStatement();
                    // execute query
                    stmt.executeUpdate("INSERT INTO Consultation VALUES ("+idConsultation+",to_timestamp('"+annee+"-"+mois+"-"+jour+" "+h+":"+m+"','yyyy-mm-dd hh24:MI'),'"+type+"',"+prix+")");
                    /*rset = stmt.executeQuery("select * from Consultation");
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
                    }*/

                    System.out.println("Consultation ajoutée!");
                    break;

                case 2:
                    stmt= conn.createStatement();
                    System.out.println("Vous avez choisi de modifier une consultation.");
                    int cpt_patient=0;
                    while(cpt_patient==0){
                        System.out.print("Veuillez rentrer le Nom du patient ou tapez 'retour' pour revenir au menu principal: ");
                        Scanner scanner2 = new Scanner(System.in);
                        String nom = scanner2.nextLine();
                        if(nom.contains("retour")){
                            return 0;
                        }
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
                    System.out.print("Entrez l'ID de la consultation dont vous souhaitez modifier la date: ");
                    Scanner scanner5 = new Scanner(System.in);
                    int id_Consultation = scanner5.nextInt();
                    int check_3=0;
                    while(check_3!=6){
                        check_3=0;
                        System.out.print("Veuillez rentrer la nouvelle date de la consultation dans le format suivant jj/mm/yyyy/Ho/Mi: ");
                        Scanner scanner4 = new Scanner(System.in);
                        String date = scanner4.nextLine();
                        Scanner scan2 = new Scanner(date);
                        scan2.useDelimiter("/");
                        jour=scan2.next();
                        mois=scan2.next();
                        annee=scan2.next();
                        h=scan2.next();
                        m=scan2.next();
                        Date today = Calendar.getInstance().getTime();
                        Calendar today_calendar = Calendar.getInstance();
                        today_calendar.setTime(today);
                        int today_annee=today_calendar.get(Calendar.YEAR);
                        int today_mois=today_calendar.get(Calendar.MONTH)+1;
                        int today_jour=today_calendar.get(Calendar.DAY_OF_MONTH);
                        int today_hour=today_calendar.get(Calendar.HOUR_OF_DAY);
                        int today_minute=today_calendar.get(Calendar.MINUTE);
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                        String dateInString = ""+jour+"-"+mois+"-"+annee+"";
                        Date date1 = formatter.parse(dateInString);
                        SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                        String dateInString1 = ""+jour+"-"+mois+"-"+annee+" "+""+h+":"+m+"";
                        Date date2 = formatter1.parse(dateInString1);
                        //System.out.println(date1);
                        //System.out.println(formatter.format(date1));
                        Calendar c = Calendar.getInstance();
                        c.setTime(date1);
                        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
                        c.add(Calendar.DATE, 1);
                        int annee1=c.get(Calendar.YEAR);
                        int mois1=c.get(Calendar.MONTH)+1;
                        int jour1=c.get(Calendar.DAY_OF_MONTH);
                        int hour = Integer.parseInt(h);
                        int minute = Integer.parseInt(m);
                        int year = Integer.parseInt(annee);
                        int month = Integer.parseInt(mois);
                        int dayy = Integer.parseInt(jour);
                        Calendar calendar1 = Calendar.getInstance();
                        Calendar calendar2 = Calendar.getInstance();
                        calendar1.setTime(date2);
                        calendar2.setTime(date2);
                        //System.out.println("Original = " + calendar1.getTime());
                        //System.out.println("Original = " + calendar2.getTime());
                        // Add 30 minutes to the calendar time
                        calendar1.add(Calendar.MINUTE, 30);
                        // Substract 2 hour from the current time
                        calendar2.add(Calendar.MINUTE, -30);
                        //System.out.println("Updated  = " + calendar1.getTime());
                        //System.out.println("Updated  = " + calendar2.getTime());
                        int hour_plus = calendar1.get(Calendar.HOUR_OF_DAY);     // gets the current month
                        int minute_plus = calendar1.get(Calendar.MINUTE);
                        int hour_minus = calendar2.get(Calendar.HOUR_OF_DAY);    // gets the current month
                        int minute_minus = calendar2.get(Calendar.MINUTE);
                        //System.out.println(hour_minus+" "+minute_minus+" "+hour_plus+" "+minute_plus);
                        //System.out.println(annee+" "+mois+" "+jour);
                        //System.out.println(annee1+" "+mois1+" "+jour1);
                        //System.out.println(dayOfWeek);
                        // closing the scanner stream
                        scan2.close();
                        //System.out.println(annee+mois+jour+h+m+type);
                        //System.out.println(today_annee+" "+today_mois+" "+today_jour+" "+year+" "+month+" "+dayy);
                        stmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,
                                ResultSet.CONCUR_READ_ONLY);
                        rset = stmt.executeQuery("SELECT * FROM Consultation WHERE Date_consultation>to_timestamp('"+annee+"-"+mois+"-"+jour+" "+hour_minus+":"+minute_minus+"','yyyy-mm-dd hh24:MI') AND Date_consultation<to_timestamp('"+annee+"-"+mois+"-"+jour+" "+hour_plus+":"+minute_plus+"','yyyy-mm-dd hh24:MI')");
                        rset.last();
                        int count_rdv = rset.getRow();
                        rset.beforeFirst();
                        //System.out.println(hour_minus+" "+minute_minus+" "+hour_plus+" "+minute_plus);
                        //System.out.println(count_rdv);
                        if(count_rdv==0){
                            check_3++;
                        }
                        else{
                            System.out.println("Il y a déjà une consultation sur cette tranche horaire");
                            check_3=0;
                        }

                        if(today_annee<=year){
                            if(today_mois<month){
                                check_3++;
                            }
                            else if(today_mois==month){
                                if(today_jour<dayy){
                                    check_3++;
                                }
                                else if(today_jour==dayy){
                                    if(today_hour<hour){
                                        check_3++;
                                    }
                                    else if(today_hour==hour){
                                        if(today_minute<minute){
                                            check_3++;
                                        }
                                        else{
                                            System.out.println("Veuillez rentrer une heure future.");
                                            check_3=0;
                                        }
                                    }
                                    else{
                                        System.out.println("Veuillez rentrer une heure future.");
                                        check_3=0;
                                    }
                                }
                                else{
                                    System.out.println("Veuillez rentrer une date future.");
                                    check_3=0;
                                }
                            }
                            else{
                                System.out.println("Veuillez rentrer une date future.");
                                check_3=0;
                            }
                        }
                        else{
                            System.out.println("Veuillez rentrer une date future.");
                            check_3=0;
                        }
                        if(minute>=0 && minute<60){
                            check_3++;
                        }
                        else{
                            System.out.println("Veuillez rentrer une heure valide.");
                            check_3=0;
                        }
                        if(hour>=8 && hour<=20){
                            check_3++;
                        }
                        else{
                            System.out.println("Les heures de RDV ne sont comprises qu'entre 8h et 20h.");
                            check_3=0;
                        }
                        if(dayOfWeek!=1){
                            check_3++;
                        }
                        else{
                            System.out.println("Les RDV ne sont pas possibles le dimanche.");
                            check_3=0;
                        }
                        stmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,
                                ResultSet.CONCUR_READ_ONLY);
                        rset = stmt.executeQuery("SELECT * FROM Consultation WHERE Date_consultation>=to_timestamp('"+annee+"-"+mois+"-"+jour+"','yyyy-mm-dd') AND Date_consultation<to_timestamp('"+annee1+"-"+mois1+"-"+jour1+"','yyyy-mm-dd')");
                        rset.last();
                        int count = rset.getRow();
                        rset.beforeFirst();
                        //System.out.println(count);
                        if(count<20){
                            check_3++;
                            //System.out.println(check_3);
                        }
                        else{
                            System.out.println("La psychologue ne peut travailler que 10 heures par jour.");
                            check_3=0;
                        }

                        //System.out.println(check_2);
                    }
                    stmt.executeUpdate("UPDATE Consultation SET Date_Consultation = to_timestamp('"+annee+"-"+mois+"-"+jour+" "+h+":"+m+"','yyyy-mm-dd hh24:MI') WHERE Id_Consultation="+id_Consultation);
                    System.out.println("Informations relatives à la date de la consultation mises à jour!");
                    break;

                case 3:
                    System.out.println("Vous avez choisi d'annuler une consultation.");
                    cpt_patient=0;
                    while(cpt_patient==0){
                        System.out.print("Veuillez rentrer le Nom du patient ou tapez 'retour' pour revenir au menu principal: ");
                        Scanner scanner2 = new Scanner(System.in);
                        String nom = scanner2.nextLine();
                        if(nom.contains("retour")){
                            return 0;
                        }
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
        return 0;

    }

    public static int fin_rdv(Connection conn) throws SQLException{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        System.out.println("Vous avez fini un RDV.");
        Statement stmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet rset = stmt.executeQuery("select Patient.Id_patient, Prenom_patient, Nom_patient, Patient_consultation.Id_consultation, Date_consultation, Patient_consultation.Type_reglement, Patient_consultation.Retard, Patient_consultation.Indicateur_anxiete from Patient join Patient_consultation on Patient.Id_patient = Patient_consultation.Id_patient join Consultation on Patient_consultation.Id_consultation = Consultation.Id_consultation where Date_consultation<=to_timestamp('"+dateFormat.format(date)+"','yyyy-mm-dd hh24:MI') and (Type_reglement is null or Retard is null)");
        rset.last();
        int count_rdv = rset.getRow();
        rset.beforeFirst();
        if(count_rdv==0){
            System.out.println("Aucune consultation à traiter");
            return 0;
        }
        stmt= conn.createStatement();
        rset = stmt.executeQuery("select Patient.Id_patient, Prenom_patient, Nom_patient, Patient_consultation.Id_consultation, Date_consultation, Patient_consultation.Type_reglement, Patient_consultation.Retard, Patient_consultation.Indicateur_anxiete from Patient join Patient_consultation on Patient.Id_patient = Patient_consultation.Id_patient join Consultation on Patient_consultation.Id_consultation = Consultation.Id_consultation where Date_consultation<=to_timestamp('"+dateFormat.format(date)+"','yyyy-mm-dd hh24:MI') and (Type_reglement is null or Retard is null)");
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
        return 0;

    }

    public void patient_consultation(Connection conn, int user) throws SQLException, ClassNotFoundException
    {
                    int id = user;
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    try
                    {
                        Statement stmt= conn.createStatement();
                        ResultSet rset = stmt.executeQuery("select * from consultp where Id_patient ="+id);
                        System.out.println("Prénom" +  "\t" +  "Nom" + "\t\t\t" + "Date consultation" + "\t\t\t" + "Type consultation");
                        while(rset.next())
                        {
                            System.out.print(rset.getString(2) + "\t");
                            System.out.print(rset.getString(3) + "\t" + "\t");
                            System.out.print(rset.getString(4)+ "\t" +  "\t");
                            System.out.print(rset.getString(5) + "\t" + "\t"+ "\t");
                        }
                        System.out.println("\n");
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
                    String Profession_actuelle = "";
                    String Prospection;
                    String ouinonprofessionanterieur;
                    String Profession_anterieur;
                    int nbprofession = 0;
                    int cptNextVal = 0;
                    int id_profession = 0;
                    int id_patient = 0;

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
                        System.out.println("Entrez comment le patient vous a connu : ");
                        Prospection = in.nextLine();
                        if (Prospection.equals(""))
                            Prospection = "NULL";
                        System.out.println("Entrez la profession actuelle du patient : ");
                        Profession_actuelle = in.nextLine();
                        if (Profession_actuelle.equals(""))
                            Profession_actuelle = "NULL";
                        Statement stmt = conn.createStatement();
                        String query1 = "insert into Patient (Id_patient, Prenom_patient, Nom_patient, Email, Mdp_patient, Sexe, Date_naissance, Profession_id, Prospection)" + " VALUES (Patient_seq.nextval, '" + Prenom_patient + "',  '" + Nom_patient + "', '" + Email_patient + "', '" + Mdp_patient + "', '" + Sexe_patient + "', to_date('" + Date_naissance + "', 'yyyy-mm-dd'), '" + Profession_actuelle + "', '" + Prospection + "')";
                        stmt.executeUpdate(query1);
                        ResultSet rsett = stmt.executeQuery("select Id_patient from Patient where Nom_patient ="+"'" + Nom_patient +"'" + "and Prenom_patient ='"+ Prenom_patient+"'");
                        while (rsett.next())
                        {
                            id_patient= rsett.getInt(1);
                        }
                        System.out.println("Le patient a-t-il des profession antérieures ? Entrez O pour oui N pour non");
                        ouinonprofessionanterieur = in.nextLine();
                        if (ouinonprofessionanterieur.equals("O"));
                        {
                            System.out.println("Entrez le nombre de profession que vous souhaitez ajouter au patient " + Nom_patient + " " + Prenom_patient + " : ");
                            nbprofession = in.nextInt();
                            for (int i = 0; i < nbprofession; i++) {
                                System.out.println("Entrez les professions une à une : ");
                                Profession_anterieur = in.next();
                                ResultSet rset = stmt.executeQuery("select Profession_seq.nextval from Profession where Id_profession=1");
                                while (rset.next()) {
                                    cptNextVal++;
                                    id_profession = (rset.getInt(1) + 1);
                                }
                                if (cptNextVal == 0) {
                                    id_profession = 1;
                                }
                                stmt.executeUpdate("INSERT INTO Profession VALUES (" + id_profession + ", '" + Profession_anterieur + "')");
                                System.out.println("Id profession : " + id_profession);
                                stmt.executeUpdate("INSERT INTO Patient_profession_anterieure VALUES (" + id_patient + ", " + id_profession + ")");
                                System.out.println("Patient ajouté !");
                            }
                        }

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

public void print_patient(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rset = stmt.executeQuery("select Id_patient, Sexe, Nom_patient, Prenom_patient, Mdp_patient, Email, Date_naissance, Prospection, Profession_id from Patient ");
        System.out.println("N° Patient \t  Sexe \t \t Nom Patient \t \t Prenom Patient \t\t Patient mot de passe \t\t Email \t\t\t\t\t Date de naissance \t\t\t Prospection \t\t\t Profession  ");
        while(rset.next())
        {
        System.out.print(rset.getInt(1) + "\t" + "\t" + "\t");
        System.out.print(rset.getString(2)+ "\t" + "\t" + "\t");
        System.out.print(rset.getString(3)+ "\t" + "\t" + "\t" + "\t");
        System.out.print(rset.getString(4)+ "\t" + "\t" + "\t" + "\t" + "\t" + "\t");
        System.out.print(rset.getString(5)+ "\t" + "\t" + "\t" + "\t" + "\t");
        System.out.print(rset.getString(6)+ "\t" + "\t");
        System.out.print(rset.getString(7)+ "\t" + "\t") ;
        if (rset.getObject(8) == null)
        System.out.print("X         ");
        else
        System.out.print(rset.getString(8)+ "\t" + "\t");
        if (rset.getObject(9) == null)
        System.out.print("X         ");
        else
        System.out.print(rset.getString(9)+ "\t" + "\t");
        System.out.println("\n");
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
        System.out.print("Entrez votre ID : ");
        username = in.nextLine();
        if (username.equals("admin"))
        {
        System.out.print("Entrez votre mot de passe : ");
        mdpadmin = in.nextLine();
        if (mdpadmin.equals("admin"))
        {
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
        System.out.print("Entrez votre mot de passe : ");
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

    public void print_mot_cle(Connection conn) throws SQLException
    {
        Scanner in = new Scanner(System.in);
        int nbr_mot_cle = 0;
        String mot_cle = "";
        int id_patient = 0;
        String prenom_patient = "";
        String nom_patient = "";
        int cpt = 0;
        int cptNextVal = 0;
        int id_mot_cle = 0;
        Statement stmt= conn.createStatement();

        System.out.println("Vous avez choisi de consulter un ou plusieurs mots clés d'un patient\n");
        while(cpt == 0)
        {
            System.out.println("Entrez le nom de famille du patient à qui vous souhaitez les consulter : ");
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
        System.out.println("Entrez le numéro du patient auquel vous souhaitez les consulter : ");

        id_patient = in.nextInt();
        ResultSet rset = stmt.executeQuery("select patient_mots_cles.id_patient, Prenom_patient, Nom_patient, patient_mots_cles.mots_cle_id, nom_mots_cle from mots_cles join patient_mots_cles on patient_mots_cles.mots_cle_id = mots_cles.mots_cle_id join Patient on Patient.Id_patient = Patient_mots_cles.id_patient where Patient.Id_patient ="+id_patient);
        System.out.println("nom mot cle pour le patient :" + nom_patient + " " + prenom_patient) ;
        while (rset.next())
        {
            System.out.print(rset.getString(5));
            System.out.println("\n");
        }
    }

    public void print_comportement(Connection conn) throws SQLException
    {
        Scanner in = new Scanner(System.in);
        int nbr_comportement = 0;
        String comportement = "";
        int id_patient = 0;
        String prenom_patient = "";
        String nom_patient = "";
        int cpt = 0;
        int cptNextVal = 0;
        int id_comportement = 0;
        Statement stmt= conn.createStatement();

        System.out.println("Vous avez choisi de consulter un ou plusieurs comportements \n");
        while(cpt == 0)
        {
            System.out.println("Entrez le nom de famille du patient à qui vous souhaitez les consulter : ");
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
        System.out.println("Entrez le numéro du patient pour lequel vous souhaitez les consulter : ");

        id_patient = in.nextInt();
        ResultSet rset = stmt.executeQuery("select patient_comportement.id_patient, Prenom_patient, Nom_patient, patient_comportement.comportement_id, nom_comportement from Comportement join patient_comportement on patient_comportement.comportement_id = Comportement.comportement_id join Patient on Patient.Id_patient = Patient_comportement.id_patient where Patient.Id_patient ="+id_patient);
        System.out.println("nom comportement du patient " + nom_patient + "" + prenom_patient);
        while (rset.next())
        {
            System.out.print(rset.getString(5));
            System.out.println("\n");
        }
    }

    public void print_posture(Connection conn) throws SQLException
    {
        Scanner in = new Scanner(System.in);
        int nbr_posture = 0;
        String posture = "";
        int id_patient = 0;
        String prenom_patient = "";
        String nom_patient = "";
        int cpt = 0;
        int cptNextVal = 0;
        int id_posture = 0;
        Statement stmt= conn.createStatement();

        System.out.println("Vous avez choisi de consulter une ou plusieurs posture \n");
        while(cpt == 0)
        {
            System.out.println("Entrez le nom de famille du patient à qui vous souhaitez les consulter : ");
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
        System.out.println("Entrez le numéro du patient auquel vous souhaitez les consulter : ");

        id_patient = in.nextInt();

        ResultSet rset = stmt.executeQuery("select patient_posture.patient_id, Prenom_patient, Nom_patient, patient_posture.posture_id, nom_posture from Posture join patient_posture on patient_posture.posture_id = Posture.posture_id join Patient on Patient.Id_patient = patient_posture.patient_id where Patient.Id_patient ="+id_patient);
        System.out.println("nom posture pour le patient " + nom_patient + " " + prenom_patient);
        while (rset.next())
        {

            System.out.print(rset.getString(5));
            System.out.println("\n");
        }
    }
}


DROP TABLE Patient;
DROP TABLE Consultation;
DROP TABLE Patient_consultation;
DROP SEQUENCE t1_seq;
DROP VIEW consultp;


CREATE TABLE Patient(
        Id_patient          NUMBER,
		Prenom_patient		Varchar (50) NOT NULL ,
		Nom_patient			Varchar (50) NOT NULL ,
        Email               Varchar (50) NOT NULL ,
        Mdp_patient         Varchar (50) NOT NULL ,
        Sexe                Varchar (50) NOT NULL ,
        Date_naissance      Date NOT NULL ,
        Profession_actuelle Varchar (50) NOT NULL ,
        Prospection         Varchar (50) NOT NULL
	,CONSTRAINT Patient_PK PRIMARY KEY (Id_patient)
);

create sequence
   t1_seq
  increment by 1
  start with 1;


INSERT INTO Patient VALUES (t1_seq.nextval, 'Samuel', 'Creteur', 'Samuel@gmail.com', 123, 'Homme', to_date('1999-05-27', 'yyyy-mm-dd'), 'Etudiant', 'Bouche a oreille');
INSERT INTO Patient VALUES (t1_seq.nextval, 'Stephane', 'Duchemin', 'Stephane@gmail.com', 123, 'Homme', to_date('1999-01-05', 'yyyy-mm-dd'), 'Vendeur chez Darty', 'Internet');
INSERT INTO Patient VALUES (t1_seq.nextval, 'Lucas', 'Pech', 'Lucasp@gmail.com', 123, 'Homme', to_date('2000-11-08', 'yyyy-mm-dd'), 'Boulanger', 'Message');


CREATE TABLE Consultation(
        Id_consultation   Varchar (50) NOT NULL ,
        Date_consultation Date NOT NULL ,
        Type_consultation Varchar (50) NOT NULL
	,CONSTRAINT Consultation_PK PRIMARY KEY (Id_consultation)
);

INSERT INTO Consultation VALUES (1,to_timestamp('2020-04-13 14:00','yyyy-mm-dd hh24:MI'),'individuel');
INSERT INTO Consultation VALUES (2,to_timestamp('2020-04-14 15:00','yyyy-mm-dd hh24:MI'),'couple');
INSERT INTO Consultation VALUES (3,to_timestamp('2020-04-15 16:00','yyyy-mm-dd hh24:MI'),'famille');


CREATE TABLE Patient_consultation(
        Id_patient         Varchar (50) NOT NULL ,
        Id_consultation    Varchar (50) NOT NULL ,
        Indicateur_anxiete Varchar (50) NOT NULL ,
		Type_reglement    Varchar (50) NOT NULL ,
        Mot_cles           Varchar (50) NOT NULL
	,CONSTRAINT Patient_consultation_PK PRIMARY KEY (Id_patient,Id_consultation)
);

INSERT INTO Patient_consultation VALUES (1, 1, 2, 'Argent', 'PAPA, MAMAN, ZEBI');
INSERT INTO Patient_consultation VALUES (2, 2, 4, 'Cheque', 'ZEBI');
INSERT INTO Patient_consultation VALUES (3, 2, 5, 'Cheque', 'PEUR, ANGOISSE');

CREATE VIEW consultp AS
SELECT p.Id_patient, p.Prenom_patient, p.Nom_patient, c.Date_consultation, c.Type_consultation
FROM Patient p, Patient_consultation a, Consultation c
WHERE p.Id_patient = a.Id_patient and c.Id_consultation = a.Id_consultation;

COMMIT;



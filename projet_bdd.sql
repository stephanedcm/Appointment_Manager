
DROP TABLE Patient;
DROP TABLE Consultation;
DROP TABLE Patient_consultation;
DROP TABLE Profession;
DROP TABLE Patient_profession_anterieure;
DROP TABLE Posture;
DROP TABLE patient_posture;
DROP TABLE Comportement;
DROP TABLE patient_comportement;
DROP TABLE mots_cles;
DROP TABLE patient_mots_cles;

DROP SEQUENCE Patient_seq;
DROP SEQUENCE Consultation_seq;
DROP SEQUENCE Profession_seq;
DROP SEQUENCE Posture_seq;
DROP SEQUENCE Comportement_seq;
DROP SEQUENCE mots_cles_seq;
DROP VIEW consultp;


CREATE TABLE Patient(
        Id_patient          Varchar (50) NOT NULL ,
		Prenom_patient		Varchar (50) NOT NULL ,
		Nom_patient			Varchar (50) NOT NULL ,
        Email               Varchar (50) NOT NULL ,
        Mdp_patient         Varchar (50) NOT NULL ,
        Sexe                Varchar (50) NOT NULL ,
        Date_naissance      Date NOT NULL ,
        Profession_id		Varchar (50),
        Prospection         Varchar (50)
	,CONSTRAINT Patient_PK PRIMARY KEY (Id_patient)
);

create sequence
   Patient_seq
  increment by 1
  start with 1;

  create sequence
   Consultation_seq
  increment by 1
  start with 1;

  create sequence
   Profession_seq
  increment by 1
  start with 1;

  create sequence
   Posture_seq
  increment by 1
  start with 1;

  create sequence
   Comportement_seq
  increment by 1
  start with 1;

  create sequence
   mots_cles_seq
  increment by 1
  start with 1;


INSERT INTO Patient VALUES (patient_seq.nextval, 'Samuel', 'Creteur', 'Samuel@gmail.com', 123, 'Homme', to_date('1999-05-27', 'yyyy-mm-dd'), 1, 'Bouche a oreille');
INSERT INTO Patient VALUES (patient_seq.nextval, 'Stephane', 'Duchemin', 'Stephane@gmail.com', 123, 'Homme', to_date('1999-01-05', 'yyyy-mm-dd'), 1, 'Internet');
INSERT INTO Patient VALUES (patient_seq.nextval, 'Lucas', 'Pech', 'Lucasp@gmail.com', 123, 'Homme', to_date('2000-11-08', 'yyyy-mm-dd'), 2, 'Message');


CREATE TABLE Consultation(
        Id_consultation   Varchar (50) NOT NULL ,
        Date_consultation Date NOT NULL ,
        Type_consultation Varchar (50) NOT NULL,
	Prix_consultation Varchar (50) NOT NULL
	,CONSTRAINT Consultation_PK PRIMARY KEY (Id_consultation)
);


CREATE TABLE Patient_consultation(
        Id_patient         Varchar (50) NOT NULL ,
        Id_consultation    Varchar (50) NOT NULL ,
        Indicateur_anxiete Varchar (50)			 ,
        Type_reglement     Varchar (50)			 ,
        Type_patient       Varchar (50)			 ,
        Retard             Varchar (50) 
	,CONSTRAINT Patient_consultation_PK PRIMARY KEY (Id_patient,Id_consultation)
);


CREATE TABLE Profession(
        Id_profession		Varchar (50) NOT NULL ,
		Nom_profession		Varchar (50) NOT NULL 
	,CONSTRAINT Profession_PK PRIMARY KEY (Id_profession)
);

INSERT INTO Profession VALUES (Profession_seq.nextval, 'Boulanger');
INSERT INTO Profession VALUES (Profession_seq.nextval, 'Boucher');
INSERT INTO Profession VALUES (Profession_seq.nextval, 'Etudiant');

CREATE TABLE Patient_profession_anterieure(
        Id_patient    Varchar (50) NOT NULL ,
        Id_profession Varchar (50) NOT NULL
	,CONSTRAINT Patient_profession_PK PRIMARY KEY (Id_patient,Id_profession)
);

INSERT INTO Patient_profession_anterieure VALUES (1, 2);
INSERT INTO Patient_profession_anterieure VALUES (1, 3);
INSERT INTO Patient_profession_anterieure VALUES (2, 2);


CREATE TABLE Posture(
        Posture_id  Varchar (50) NOT NULL ,
        Nom_posture Varchar (50) NOT NULL
	,CONSTRAINT Posture_PK PRIMARY KEY (Posture_id)
);

INSERT INTO Posture VALUES (Posture_seq.nextval, 'Debout');
INSERT INTO Posture VALUES (Posture_seq.nextval, 'Assis');
INSERT INTO Posture VALUES (Posture_seq.nextval, 'Legendaire');

CREATE TABLE patient_posture(
        posture_id Varchar (50) NOT NULL ,
        patient_id Varchar (50) NOT NULL
	,CONSTRAINT patient_posture_PK PRIMARY KEY (posture_id,patient_id)
);

INSERT INTO patient_posture VALUES (1, 1);
INSERT INTO Patient_posture VALUES (1, 2);
INSERT INTO patient_posture VALUES (2, 1);

CREATE TABLE Comportement(
		comportement_id Varchar (50) NOT NULL ,
		nom_comportement Varchar (50) NOT NULL 
		, CONSTRAINT Comportement_PK PRIMARY KEY (comportement_id)
);

INSERT INTO Comportement VALUES (Comportement_seq.nextval, 'Pas content');
INSERT INTO Comportement VALUES (Comportement_seq.nextval, 'Heureux');
INSERT INTO Comportement VALUES (Comportement_seq.nextval, 'Déprimé');

CREATE TABLE patient_comportement(
        comportement_id Varchar (50) NOT NULL ,
        id_patient      Varchar (50) NOT NULL
	,CONSTRAINT patient_comportement_PK PRIMARY KEY (comportement_id,id_patient)
);

INSERT INTO patient_comportement VALUES (1, 2);
INSERT INTO patient_comportement VALUES (2, 1);
INSERT INTO patient_comportement VALUES (3, 2);

CREATE TABLE mots_cles(
		mots_cle_id		VARCHAR (50) NOT NULL ,
		nom_mots_cle	VARCHAR (50) NOT NULL
		,CONSTRAINT mots_cles_PK PRIMARY KEY (mots_cle_id)
		);



CREATE TABLE patient_mots_cles(
        mots_cle_id Varchar (50) NOT NULL ,
        id_patient  Varchar (50) NOT NULL
	,CONSTRAINT patient_mots_cles_PK PRIMARY KEY (mots_cle_id,id_patient)
);



CREATE VIEW consultp AS
SELECT p.Id_patient, p.Prenom_patient, p.Nom_patient, c.Date_consultation, c.Type_consultation
FROM Patient p, Patient_consultation a, Consultation c
WHERE p.Id_patient = a.Id_patient and c.Id_consultation = a.Id_consultation;

COMMIT;



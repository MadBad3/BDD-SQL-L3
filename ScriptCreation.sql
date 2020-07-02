#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Groupe
#------------------------------------------------------------

CREATE TABLE Groupe(
        id  Int  Auto_increment  NOT NULL ,
        nom Varchar (150)
	,CONSTRAINT Groupe_PK PRIMARY KEY (id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Eleve
#------------------------------------------------------------

CREATE TABLE Eleve(
        matricule          Int  Auto_increment  NOT NULL ,
        nom                Varchar (50) NOT NULL ,
        prenom             Varchar (50) NOT NULL ,
        annee              Int NOT NULL ,
        mois               Int NOT NULL ,
        jour               Int NOT NULL ,
        ville_de_naissance Varchar (50) NOT NULL ,
        sexe               Bool NOT NULL ,
        numero             Int NOT NULL ,
        nom_de_la_rue      Varchar (50) NOT NULL ,
        cp                 Int NOT NULL ,
        ville              Varchar (50) NOT NULL ,
        promotion          Varchar (20) NOT NULL ,
        id                 Int NOT NULL
	,CONSTRAINT Eleve_PK PRIMARY KEY (matricule)

	,CONSTRAINT Eleve_Groupe_FK FOREIGN KEY (id) REFERENCES Groupe(id)
)ENGINE=InnoDB, AUTO_INCREMENT = 20190001;


#------------------------------------------------------------
# Table: Professeur
#------------------------------------------------------------

CREATE TABLE Professeur(
        matricule          Int  Auto_increment  NOT NULL ,
        nom                Varchar (50) NOT NULL ,
        prenom             Varchar (50) NOT NULL ,
        annee              Int NOT NULL ,
        mois               Int NOT NULL ,
        jour               Int NOT NULL ,
        ville_de_naissance Varchar (50) NOT NULL ,
        sexe               Bool NOT NULL ,
        numero             Int NOT NULL ,
        nom_de_la_rue      Varchar (50) NOT NULL ,
        cp                 Int NOT NULL ,
        ville              Varchar (50) NOT NULL ,
        telephone          Int NOT NULL
	,CONSTRAINT Professeur_PK PRIMARY KEY (matricule)
)ENGINE=InnoDB, AUTO_INCREMENT = 20050001;


#------------------------------------------------------------
# Table: Cours
#------------------------------------------------------------

CREATE TABLE Cours(
        code               Int  Auto_increment  NOT NULL ,
        nom                Varchar (50) NOT NULL ,
        description        Varchar (500) NOT NULL ,
        annee              Int NOT NULL ,
        coef_du_cours      Float NOT NULL ,
        Pourcentage_DE     Int NOT NULL ,
        Pourcentage_TP     Int NOT NULL ,
        Pourcentage_Projet Int NOT NULL ,
        matricule          Int NOT NULL
	,CONSTRAINT Cours_PK PRIMARY KEY (code)

	,CONSTRAINT Cours_Professeur_FK FOREIGN KEY (matricule) REFERENCES Professeur(matricule)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Personne_responsable
#------------------------------------------------------------

CREATE TABLE Personne_responsable(
        id            Int  Auto_increment  NOT NULL ,
        nom           Varchar (50) NOT NULL ,
        prenom        Varchar (50) NOT NULL ,
        annee         Int NOT NULL ,
        mois          Int NOT NULL ,
        jour          Int NOT NULL ,
        sexe          Bool NOT NULL ,
        numero        Int NOT NULL ,
        nom_de_la_rue Varchar (50) NOT NULL ,
        cp            Int NOT NULL ,
        ville         Varchar (50) NOT NULL ,
        telephone     Int NOT NULL ,
        matricule     Int NOT NULL
	,CONSTRAINT Personne_responsable_PK PRIMARY KEY (id)

	,CONSTRAINT Personne_responsable_Eleve_FK FOREIGN KEY (matricule) REFERENCES Eleve(matricule)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: suit un
#------------------------------------------------------------

CREATE TABLE suit_un(
        id   Int NOT NULL ,
        code Int NOT NULL
	,CONSTRAINT suit_un_PK PRIMARY KEY (id,code)

	,CONSTRAINT suit_un_Groupe_FK FOREIGN KEY (id) REFERENCES Groupe(id)
	,CONSTRAINT suit_un_Cours0_FK FOREIGN KEY (code) REFERENCES Cours(code)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Evaluation
#------------------------------------------------------------

CREATE TABLE Evaluation(
        code        Int NOT NULL ,
        matricule   Int NOT NULL ,
        Note_DE     Decimal (20) NOT NULL ,
        Note_TP     Decimal (20) NOT NULL ,
        Note_Projet Decimal (20) NOT NULL
	,CONSTRAINT Evaluation_PK PRIMARY KEY (code,matricule)

	,CONSTRAINT Evaluation_Cours_FK FOREIGN KEY (code) REFERENCES Cours(code)
	,CONSTRAINT Evaluation_Eleve0_FK FOREIGN KEY (matricule) REFERENCES Eleve(matricule)
)ENGINE=InnoDB;

/*drop table Evaluation;
drop table Personne_Responsable;
drop table Eleve;
drop table suit_un;
drop table Cours;
drop table Professeur;
drop table Groupe;
*/
INSERT INTO Groupe (nom)
VALUES('A'),('B'),('C');

INSERT INTO Eleve (nom,prenom,annee,mois,jour,ville_de_naissance,sexe,numero,nom_de_la_rue,cp,ville,promotion,id)
VALUES (' Hamdi','Badr',1997,06,06,'Paris',1,45,'rue_le_marois',75016,'Paris','L1', 1),
('Jin','Edouard',1998,09,02,'Paris',1,213,'rue_marie',75018,'Paris','L1', 2),
('Taccola','Alessandro',1997,06,06,'Paris',1,130,'boulevard_du_saint',75009,'Paris','L1', 1),
('Fillon','Gilbert',1994,12,15,'Troyes',1,45,'Avenue du Marechal',94800,'Villejuif','L3', 3),
('Durand','Anna',1998,11,08,'Paris',0,72,'Avenue de Versailles',75016,'Paris','L2', 1),
('Dupuis','Sarah',1997,01,27,'Grenoble',0,126,'Rue le marais',75001,'Paris','L3', 3);

INSERT INTO Professeur (nom,prenom,annee,mois,jour,ville_de_naissance,sexe,numero,nom_de_la_rue,cp,ville,telephone)
VALUES ('Delanoe','Jack',1985,10,25,'Rouen',1,15,'Boulevard Lefebre',75015,'Paris',0687697598),
('Bizet','Francoise',1976,07,19,'Laval',0,112,'Rue Bovard',53000,'Laval',0792378215),
('Denin','Marie',1963,03,02,'Saint-Denis',0,53,'Rue nouvelle',93200,'Saint-Denis',0632087038);

INSERT INTO Cours (nom,description,annee,coef_du_cours,Pourcentage_DE,Pourcentage_TP,Pourcentage_Projet, matricule)
VALUES ('Maths','Application des theories calculatoires',2019,4,50,25,25,20050001),
('Physique','Application des theories physiques',2019,4,60,40,0,20050002),
('Communication','Apprendre a sexprimer a loral',2019,2,50,20,30,20050003),
('BDD','Application des theories calculatoires',2019,5,50,10,40,20050001),
('Theorie des groupes','Application des theories concernant les groupes',2018,5,50,30,20,20050002);

INSERT INTO Personne_Responsable (nom,prenom,annee,mois,jour,sexe,numero,nom_de_la_rue,cp,ville,telephone,matricule)
VALUES ('Brognard','Arya',1978,12,29,0,36,'Boulevard Saint-Germain',75014,'Paris',0679240682,20190001),
('More','Maxime',1984,05,30,1,79,'Avenue de Versailles',94800,'Villejuif-Louis Aragon',0792034351, 20190002),
('Denis','Ali',1978,12,29,0,36,'Rue de la chapelle',75014,'Paris',0679240682, 20190003),
('Jean','Christophe',1984,05,30,1,79,'Avenue Washington',94800,'Villejuif',0792034351, 20190004),
('Birla','Alia',1978,12,29,0,36,'Rue de la paix',75014,'Paris',0679240682, 20190005),
('Jeanet','Anne',1984,05,30,1,79,'Avenue Mordand',94800,'Villepinte',0792034351, 20190006);

INSERT INTO Evaluation (code, matricule, Note_DE,Note_TP,Note_Projet)
VALUES (1, 20190001, 13,15,11),
(2, 20190001, 12,5,18),
(3, 20190001, 10,16,13),
(4, 20190001, 9,6,20),
(3, 20190002, 13,15,11),
(4, 20190002, 12,5,18),
(1, 20190002, 10,16,13),
(2, 20190003, 9,6,20),
(3, 20190003, 11,15,8),
(4, 20190003, 12,5,18),
(1, 20190003, 8,16,13),
(3, 20190004, 13,15,11),
(4, 20190004, 12,5,18),
(1, 20190004, 10,16,13),
(3, 20190005, 13,15,11),
(4, 20190005, 12,5,18),
(1, 20190005, 10,16,13),
(2, 20190006, 9,6,20),
(3, 20190006, 13,15,2),
(4, 20190006, 6,19,18);
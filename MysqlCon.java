/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdd2;
import static java.lang.Integer.parseInt;
import java.sql.*;  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author bhamd
 */
public class MysqlCon {

    /**
     * @param args the command line arguments
     */
        private static HashMap<Integer,String> login = new HashMap<Integer, String>();//Identifiant/Niveau
        private static HashMap<Integer, String> login2 = new HashMap<Integer, String>();//Identifiant/Mdp
        private static int nb;
        private static List<String> list = new ArrayList<String>();
        
        public static void main(String args[]) {
            
            try{  
                Class.forName("com.mysql.jdbc.Driver");  
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Project","root","2237");  
                //here Project is database name, root is username and password  
                
                initialisationLogin();//de tout les comptes (Admin/Prof/Eleve)
                menu();//Connection
                
                con.close();
            }catch(Exception e){
                System.out.println(e);}
        }
        
        public static void eleve(int nb)
        {
            try{  
                Class.forName("com.mysql.jdbc.Driver");  
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Project","root","2237");  
                //here Project is database name, root is username and password  
                
                Statement stmt=con.createStatement();
                ResultSet rs;
                PreparedStatement pstmt;
                String saisie;
                do{
                System.out.println("\nQue voulez-vous faire ? Tapez le numero de votre choix\n0)Se deconnecter !\n1)Voir mes notes !\n2)Voir mon profil");
                Scanner s = new Scanner(System.in);
                
                do{
                    saisie = s.nextLine();
                }while(saisie.hashCode() != "0".hashCode() && saisie.hashCode() != "1".hashCode() && saisie.hashCode() != "2".hashCode());

                if(saisie.hashCode() == "1".hashCode())
                {
                    pstmt = con.prepareStatement("select nom, Note_DE, Note_TP, Note_Projet from Evaluation JOIN Cours ON Evaluation.matricule=? && Evaluation.code = cours.code;");//car j'ai ajoute +100 pour
                    pstmt.setInt(1, nb);
                    rs = pstmt.executeQuery();
                    while(rs.next())
                        System.out.println("Cours: "+rs.getString(1)+"   Note_DE: "+rs.getString(2)+"   Note_TP: "+rs.getString(3)+"   Note_Projet: "+rs.getString(4));
                }else if(saisie.hashCode() == "2".hashCode())
                {
                    String sexe;
                    pstmt = con.prepareStatement("select * from Eleve where matricule = ?");//car j'ai ajoute +100
                    pstmt.setInt(1, nb);
                    rs = pstmt.executeQuery();
                    
                    while(rs.next())
                    {
                        if(rs.getBoolean(8))
                        {
                            sexe = "homme";
                        }else
                            sexe = "femme";
                        System.out.println("\n\nProfil:\n\nMatricule: "+rs.getInt(1)+"   Nom: "+rs.getString(2)+"   Prenom: "+rs.getString(3)+"   Date de naissance: "+rs.getString(6)+"/"+rs.getString(5)+"/"+rs.getString(4)+"   Ville de naissance: "+rs.getString(7)+"   Sexe: "+sexe+"\nAdresse: "+rs.getInt(9)+" "+rs.getString(10)+"   Code postal: "+rs.getInt(11)+"   Ville: "+rs.getString(12)+"   Promotion: "+rs.getString(13));
                    }
                }
                }while(saisie.hashCode()!="0".hashCode());
                System.out.println("Vous etes deconnecte !");
                stmt.close();
                con.close();
            }catch(Exception e){
                System.out.println(e);}
        }
        
        public static void prof(int nb)
        {
            try{
                Class.forName("com.mysql.jdbc.Driver");  
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Project","root","2237");  
                //here Project is database name, root is username and password  
                
                int codeCours;
                ResultSet rs;
                PreparedStatement pstmt;
                String saisie;
                do{
                System.out.println("\nQue voulez-vous faire ? Tapez le numero de votre choix\n0)Se deconnecter\n1)Voir les notes de mes eleves!\n2)Saisir des notes\n3)Modifier des notes\n4)Recherche d'eleves\n5)Voir mon profil");
                Scanner s = new Scanner(System.in);
                
                do{
                    saisie = s.nextLine();
                }while(saisie.hashCode() != "0".hashCode() && saisie.hashCode() != "1".hashCode() && saisie.hashCode() != "2".hashCode() && saisie.hashCode() != "3".hashCode() && saisie.hashCode() != "4".hashCode() && saisie.hashCode() != "5".hashCode());
                
                if(saisie.hashCode() == "1".hashCode())
                {
                    pstmt = con.prepareStatement("select Eleve.nom, Eleve.prenom, Groupe.nom, Cours.nom, Note_DE, Note_TP, Note_Projet from Evaluation JOIN Cours ON Cours.matricule=? && Evaluation.code = cours.code JOIN Eleve ON Eleve.matricule=Evaluation.matricule JOIN Groupe ON Eleve.id=Groupe.id ORDER BY Eleve.nom;");
                    pstmt.setInt(1, nb);
                    rs = pstmt.executeQuery();
                    while(rs.next())
                        System.out.println("Nom: "+rs.getString(1)+"   Prenom: "+rs.getString(2)+"   Groupe: "+rs.getString(3)+"   Cours: "+rs.getString(4)+"   Note_DE: "+rs.getInt(5)+"   Note_TP: "+rs.getInt(6)+"   Note_Projet: "+rs.getInt(7));
                    
                }else if(saisie.hashCode() == "2".hashCode())
                {
                    System.out.println("Veuillez saisir le code du cours pour lequel vous voulez saisir des notes !");
                    Scanner sa = new Scanner(System.in);
                    boolean a=false;
                    
                    do{
                        saisie = sa.nextLine();
                        pstmt = con.prepareStatement("select code, nom from Cours where Cours.matricule = ?");
                        pstmt.setInt(1, nb);
                        rs = pstmt.executeQuery();
                        while(rs.next())//Si le cours est bien donne par le bon prof
                            if(saisie.hashCode() ==  Integer.toString(rs.getInt(1)).hashCode() || saisie.hashCode() ==  rs.getString(2).hashCode())
                                a = true;
                    }while(a==false);//Tant que le cours saisie n'est pas celui du prof

                    //Afficher tous les eleves suivants le cours du meme
                    pstmt = con.prepareStatement("select Eleve.matricule, Eleve.nom, Eleve.prenom, Groupe.nom, Cours.nom from Cours JOIN Eleve ON Cours.matricule = ? JOIN Groupe ON Groupe.id=Eleve.id && Cours.code = ? ORDER BY matricule;");
                    pstmt.setInt(1, nb);
                    pstmt.setInt(2, parseInt(saisie));
                    rs = pstmt.executeQuery();
                    while(rs.next())//On affiche tous les etudiants corriges par le prof
                    {
                        System.out.println("Matricule: "+rs.getInt(1)+"   Nom: "+rs.getString(2)+"   Prenom: "+rs.getString(3)+"   Groupe: "+rs.getString(4)+"   Cours: "+rs.getString(5));
                        list.add(Integer.toString(rs.getInt(1)));
                    }
                    //Demander le code de l'etudiant auquel on veut ajouter la note
                    pstmt = con.prepareStatement("select count(*) from Cours JOIN Eleve ON Cours.matricule = ? JOIN Groupe ON Groupe.id=Eleve.id && Cours.code=? JOIN Evaluation ON Evaluation.matricule=Eleve.matricule && Evaluation.code=? && Eleve.matricule=?;");
                    System.out.println("Veuillez saisir le code de l'eleve auquel vous voulez ajouter une note !");
                    Scanner sai = new Scanner(System.in);
                    String choix;
                    do{
                        choix = sai.nextLine();
                    }while(!list.contains(choix));
                    
                    pstmt.setInt(1, nb);
                    pstmt.setInt(2, parseInt(saisie));
                    pstmt.setInt(3, parseInt(saisie));
                    pstmt.setInt(4, parseInt(choix));
                    rs = pstmt.executeQuery();
                    int resultat=2;
                    while(rs.next())
                        resultat=rs.getInt(1);
                    System.out.println(resultat);
                    if(resultat==0)
                    {
                        pstmt = con.prepareStatement("INSERT INTO Evaluation (code, matricule, Note_DE, Note_TP, Note_Projet) VALUES (?,?,?,?,?)");
                        int note_de, note_tp, note_projet;
                        System.out.println("Saisir par ordre la note du DE, puis la note du TP, et enfin la note du projet");
                        Scanner note = new Scanner(System.in);
                        note_de = note.nextInt();
                        note_tp = note.nextInt();
                        note_projet = note.nextInt();
                        
                        pstmt.setInt(1, parseInt(saisie));
                        pstmt.setInt(2, parseInt(choix));
                        pstmt.setInt(3, note_de);
                        pstmt.setInt(4, note_tp);
                        pstmt.setInt(5, note_projet);
                        pstmt.executeUpdate();
                    }
                    list.removeAll(list);
                    
                }else if(saisie.hashCode() == "3".hashCode())
                {
                    System.out.println("Veuillez saisir le code du cours pour lequel vous voulez modifier une ou plusieurs notes !");
                    Scanner sa = new Scanner(System.in);
                    boolean a=false;
                    
                    do{
                        saisie = sa.nextLine();
                        pstmt = con.prepareStatement("select code, nom from Cours where Cours.matricule = ?");
                        pstmt.setInt(1, nb);
                        rs = pstmt.executeQuery();
                        while(rs.next())//Si le cours est bien donne par le bon prof
                            if(saisie.hashCode() ==  Integer.toString(rs.getInt(1)).hashCode() || saisie.hashCode() ==  rs.getString(2).hashCode())
                                a = true;
                    }while(a==false);//Tant que le cours saisie n'est pas celui du prof

                    //Afficher tous les eleves suivants le cours du meme
                    pstmt = con.prepareStatement("select Eleve.matricule, Eleve.nom, Eleve.prenom, Groupe.nom, Cours.nom from Cours JOIN Eleve ON Cours.matricule = ? JOIN Groupe ON Groupe.id=Eleve.id && Cours.code = ? ORDER BY matricule;");
                    pstmt.setInt(1, nb);
                    pstmt.setInt(2, parseInt(saisie));
                    rs = pstmt.executeQuery();
                    while(rs.next())//On affiche tous les etudiants corriges par le prof
                    {
                        System.out.println("Matricule: "+rs.getInt(1)+"   Nom: "+rs.getString(2)+"   Prenom: "+rs.getString(3)+"   Groupe: "+rs.getString(4)+"   Cours: "+rs.getString(5));
                        list.add(Integer.toString(rs.getInt(1)));
                    }
                    //Demander le code de l'etudiant auquel on veut ajouter la note
                    pstmt = con.prepareStatement("select count(*) from Cours JOIN Eleve ON Cours.matricule = ? JOIN Groupe ON Groupe.id=Eleve.id && Cours.code=? JOIN Evaluation ON Evaluation.matricule=Eleve.matricule && Evaluation.code=? && Eleve.matricule=?;");
                    System.out.println("Veuillez saisir le code de l'eleve auquel vous voulez modifier une note !");
                    Scanner sai = new Scanner(System.in);
                    String choix;
                    do{
                        choix = sai.nextLine();
                    }while(!list.contains(choix));
                    
                    pstmt.setInt(1, nb);
                    pstmt.setInt(2, parseInt(saisie));
                    pstmt.setInt(3, parseInt(saisie));
                    pstmt.setInt(4, parseInt(choix));
                    rs = pstmt.executeQuery();
                    int resultat=2;
                    while(rs.next())
                        resultat=rs.getInt(1);
                    System.out.println(resultat);
                    String choices;
                    Scanner sc = new Scanner(System.in);
                    if(resultat==1)
                    {
                        System.out.println("Que voulez vous modifier ?\n1)Note DE\n2)Note TP\n3)Note Projet");
                        Scanner choice = new Scanner(System.in);
                        do{
                            choices = choice.nextLine();
                        }while(choices.hashCode() != "1".hashCode() && choices.hashCode() != "2".hashCode() && choices.hashCode() != "3".hashCode());
                        System.out.println("choices: "+choices);
                        System.out.println("choix: "+choix);
                        System.out.println("saisie: "+saisie);
                        if(choices.hashCode()=="1".hashCode())
                        {
                            int note = sc.nextInt();
                            pstmt = con.prepareStatement("UPDATE Evaluation set Note_DE = ? where matricule=? && code=?;");
                            pstmt.setInt(1, note);
                            pstmt.setInt(2, parseInt(choix));
                            pstmt.setInt(3, parseInt(saisie));
                            pstmt.executeUpdate();
                        }else if(choices.hashCode()=="2".hashCode())
                        {
                            int note = sc.nextInt();
                            pstmt = con.prepareStatement("UPDATE Evaluation set Note_TP = ? where matricule=? && code=?;");
                            pstmt.setInt(1, note);
                            pstmt.setInt(2, parseInt(choix));
                            pstmt.setInt(3, parseInt(saisie));
                            pstmt.executeUpdate();
                        }else if(choices.hashCode()=="3".hashCode())
                        {
                            int note = sc.nextInt();
                            pstmt = con.prepareStatement("UPDATE Evaluation set Note_Projet = ? where matricule=? && code=?;");
                            pstmt.setInt(1, note);
                            pstmt.setInt(2, parseInt(choix));
                            pstmt.setInt(3, parseInt(saisie));
                            pstmt.executeUpdate();
                        }
                    }
                    
                }else if(saisie.hashCode() == "4".hashCode())
                {
                    String choix;
                    System.out.println("Que voulez-vous chercher ? Tapez le numero de votre choix !\n1)Une liste d'eleves par groupe\n2)Une liste d'eleves par promotion");
                    Scanner sc = new Scanner(System.in);
                    do{
                        choix = sc.nextLine();
                    }while(choix.hashCode() != "1".hashCode() && choix.hashCode() != "2".hashCode());
                    if(choix.hashCode() == "1".hashCode())
                    {
                        System.out.println("Saisir le nom du groupe que vous voulez chercher !");
                        choix = sc.nextLine();
                        pstmt = con.prepareStatement("select matricule, Eleve.nom, prenom, Groupe.nom from Eleve JOIN Groupe ON groupe.nom = ? && Eleve.id=Groupe.id;");
                        pstmt.setString(1, choix);
                        rs = pstmt.executeQuery();
                        while(rs.next())
                            System.out.println("Matricule: "+rs.getInt(1)+"   nom: "+rs.getString(2)+"   prenom: "+rs.getString(3)+"   Groupe: "+rs.getString(4));
                    
                    }else{
                        System.out.println("Saisir le nom de la promotion que vous voulez chercher !");
                        choix = sc.nextLine();
                        pstmt = con.prepareStatement("select matricule, nom, prenom, promotion from Eleve where promotion = ?;");
                        pstmt.setString(1, choix);
                        rs = pstmt.executeQuery();
                        while(rs.next())
                            System.out.println("Matricule: "+rs.getInt(1)+"   nom: "+rs.getString(2)+"   prenom: "+rs.getString(3)+"   Promotion: "+rs.getString(4));
                    }
                }else if(saisie.hashCode() == "5".hashCode())
                {
                    String sexe;
                    pstmt = con.prepareStatement("select * from Professeur where matricule = ?");
                    pstmt.setInt(1, nb);
                    rs = pstmt.executeQuery();
                    
                    while(rs.next())
                    {
                        if(rs.getBoolean(8))
                        {
                            sexe = "homme";
                        }else
                            sexe = "femme";
                        System.out.println("\n\nProfil:\n\nMatricule: "+rs.getString(1)+"   Nom: "+rs.getString(2)+"   Prenom: "+rs.getString(3)+"   Date de naissance: "+rs.getString(6)+"/"+rs.getString(5)+"/"+rs.getString(4)+"   Ville de naissance: "+rs.getString(7)+"   Sexe: "+sexe+"\nAdresse: "+rs.getInt(9)+" "+rs.getString(10)+"   Code postal: "+rs.getInt(11)+"   Ville: "+rs.getString(12)+"   Telephone: 0"+rs.getString(13));
                    }
                }
                }while(saisie.hashCode()!="0".hashCode());
                System.out.println("Vous etes deconnecte !");
                con.close();
            }catch(Exception e){
                System.out.println(e);}
        }
        
        public static void admin(int nb)
        {
            try{  
                Class.forName("com.mysql.jdbc.Driver");  
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Project","root","2237");  
                //here Project is database name, root is username and password  
                
                ResultSet rs;
                PreparedStatement pstmt;
                String saisie;
                Scanner s = new Scanner(System.in);
                
                do{
                    System.out.println("\nQue voulez-vous faire ? Tapez le numero de votre choix\n0)Se deconnecter !\n1)Ajouter un etudiant !\n2)Ajouter un prof\n3)Supprimer un etudiant\n4)Supprimer un prof");

                    do{
                        saisie = s.nextLine();
                    }while(saisie.hashCode() != "0".hashCode() && saisie.hashCode() != "1".hashCode() && saisie.hashCode() != "2".hashCode() && saisie.hashCode() != "3".hashCode() && saisie.hashCode() != "4".hashCode());

                    if(saisie.hashCode() == "1".hashCode())
                    {
                        pstmt = con.prepareStatement("INSERT INTO Eleve (nom,prenom,annee,mois,jour,ville_de_naissance,sexe,numero,nom_de_la_rue,cp,ville,promotion,id)\n" +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");//car j'ai ajoute +100 pour
                        System.out.println("Saisir dans l'ordre respectif: nom,prenom,annee,mois,jour,ville_de_naissance,sexe,numero,nom_de_la_rue,cp,ville,promotion,id\nPour le sexe si c'est homme, saisir 1 sinon 0");

                        String nom = s.next();
                        String prenom = s.next();
                        int annee = s.nextInt();
                        int mois = s.nextInt();
                        int jour = s.nextInt();
                        String ville_de_naissance = s.next();
                        int sexe = s.nextInt();
                        int numero = s.nextInt();
                        String nom_de_la_rue = s.next();
                        int cp = s.nextInt();
                        String ville = s.next();
                        int promotion = s.nextInt();
                        int id = s.nextInt();
                        
                        pstmt.setString(1, nom);
                        pstmt.setString(2, prenom);
                        pstmt.setInt(3, annee);
                        pstmt.setInt(4, mois);
                        pstmt.setInt(5, jour);
                        pstmt.setString(6, ville_de_naissance);
                        pstmt.setInt(7, sexe);
                        pstmt.setInt(8, numero);
                        pstmt.setString(9, nom_de_la_rue);
                        pstmt.setInt(10, cp);
                        pstmt.setString(11, ville);
                        pstmt.setInt(12, promotion);
                        pstmt.setInt(13, id);
                        
                        pstmt.executeUpdate();
                        System.out.println("L'etudiant a ete ajoute !");
                        
                    }else if(saisie.hashCode() == "2".hashCode()){
                        pstmt = con.prepareStatement("INSERT INTO Professeur (nom,prenom,annee,mois,jour,ville_de_naissance,sexe,numero,nom_de_la_rue,cp,ville,telephone)\n" +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
                        System.out.println("Saisir dans l'ordre respectif: nom,prenom,annee,mois,jour,ville_de_naissance,sexe,numero,nom_de_la_rue,cp,ville,telephone\nPour le sexe si c'est homme, saisir 1 sinon 0");

                        String nom = s.next();
                        String prenom = s.next();
                        int annee = s.nextInt();
                        int mois = s.nextInt();
                        int jour = s.nextInt();
                        String ville_de_naissance = s.next();
                        int sexe = s.nextInt();
                        int numero = s.nextInt();
                        String nom_de_la_rue = s.next();
                        int cp = s.nextInt();
                        String ville = s.next();
                        int telephone = s.nextInt();

                        pstmt.setString(1, nom);
                        pstmt.setString(2, prenom);
                        pstmt.setInt(3, annee);
                        pstmt.setInt(4, mois);
                        pstmt.setInt(5, jour);
                        pstmt.setString(6, ville_de_naissance);
                        pstmt.setInt(7, sexe);
                        pstmt.setInt(8, numero);
                        pstmt.setString(9, nom_de_la_rue);
                        pstmt.setInt(10, cp);
                        pstmt.setString(11, ville);
                        pstmt.setInt(12, telephone);
                        
                        pstmt.executeUpdate();
                        System.out.println("Le prof a ete ajoute !");

                    }else if(saisie.hashCode() == "3".hashCode()){
                        
                        boolean a = false;
                        int matricule;
                        pstmt = con.prepareStatement("Delete from ELeve where matricule=?;");
                        System.out.println("Saisir le matricule de l'eleve que vous voulez supprimer");
                        System.out.println("Alors:"+login.values());
                        
                        do{
                            matricule = s.nextInt();
                        }while(login.get(s)=="Eleve");
                        
                        pstmt.setInt(1, matricule);
                        pstmt.executeUpdate();
                        System.out.println("L'eleve a ete supprime !");

                    }else if(saisie.hashCode() == "4".hashCode()){

                        int matricule;
                        pstmt = con.prepareStatement("Delete from Professeur where matricule=?;");
                        System.out.println("Saisir le matricule du prof que vous voulez supprimer");
                        
                        do{
                            matricule = s.nextInt();
                        }while(login.get(s)=="Prof");
                        
                        pstmt.setInt(1, matricule);
                        pstmt.executeUpdate();
                        System.out.println("Le prof a ete supprime !");

                    }
                
                }while(saisie.hashCode() != "0".hashCode());
                System.out.println("Vous etes deconnecte !");
                con.close();  
            }catch(Exception e){
                System.out.println(e);}
        }
        
        public static void initialisationLogin()
        {
            login.put(0, "Administrateur");
            login2.put(0, "0000");
            
            try{  
                Class.forName("com.mysql.jdbc.Driver");  
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Project","root","2237");  
                //here Project is database name, root is username and password  
                
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery("select count(*) from Professeur");
                int taille = 0;
                while(rs.next())
                {
                    taille = rs.getInt(1);//nbe de professeurs
                }
                    
                rs=stmt.executeQuery("select * from Professeur");
                while(rs.next())
                {
                    login.put(rs.getInt(1), "Professeur");
                    login2.put(rs.getInt(1), "1234");
                }
                rs=stmt.executeQuery("select * from Eleve");
                while(rs.next())
                {
                    login.put(rs.getInt(1), "Eleve");
                    login2.put(rs.getInt(1), "12345");
                }

                rs.close();
                stmt.close();
                con.close();  
            }catch(Exception e){
                System.out.println(e);}
            
        }
        
        public static void menu()
        {
            String mdp;
            System.out.println("Bienvenue sur le nouveau portail de gestion des eleves !\nVeuillez vous connectez, en saisissant votre identifiant et ensuite votre mot de passe");
            Scanner sc = new Scanner(System.in);
            Scanner sc1 = new Scanner(System.in);
            boolean test=false;
            do{        
                nb = sc.nextInt();
                mdp = sc1.nextLine();
                if(login2.containsKey(nb)==true && login2.get(nb).hashCode() == mdp.hashCode())
                    test=true;
            }while(test==false);
                
            System.out.println("Vous etes desormais connecte en tant que: " + login.get(nb));
            if(login.get(nb).hashCode() == "Administrateur".hashCode())
            {
                admin(nb);
            }else if(login.get(nb).hashCode() == "Eleve".hashCode())
            {
                eleve(nb);
            }else if(login.get(nb).hashCode() == "Professeur".hashCode())
            {
                prof(nb);
            }
                
        }
}

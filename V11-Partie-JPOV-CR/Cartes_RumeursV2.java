package frr.utt.lo02.projet;

import java.util.Scanner;
import java.util.Random;

public class Cartes_RumeursV2 {
    public String nom;

    private boolean effetHunt;

    public boolean utilisee;

    public int idDetenteur;

  //  public EffetWitch ;

//    public EffetHunt ;
    //Constructeur
    public Cartes_RumeursV2(String nom,boolean hunt, boolean util, int iddet) {
    	this.nom=nom;
    	this.effetHunt=hunt;
    	this.utilisee=util;
    	this.idDetenteur=iddet;
    }
    //Affichage des cartes
    public String toString() {
    	String str = new String ("nom : "+this.nom);
    	return str;
    }
    public boolean jouerCarte(final String nom, final boolean hunt, int idajoué, int idaAccusé) {
    	Scanner clavier = new Scanner(System.in);
    	String angrymob = "Angry Mob";
    	if (angrymob.equalsIgnoreCase(nom) && hunt==false) {
    		System.out.println("Vous utilisez l'effet Witch de Angry Mob");
    		PartieV2.donnerTour(idajoué);
    		return true;
    	}
    	if (angrymob.equalsIgnoreCase(nom) && hunt==true) {
    		System.out.println("Vous utilisez l'effet Hunt de Angry Mob");
    		return true;
    	}
    	String theinquisition = "The Inquisition";
    	if (theinquisition.equalsIgnoreCase(nom) && hunt==false) {
    		String nomcarte="0";
    		System.out.println("Vous utilisez l'effet Witch de The Inquisition");
    		boolean app= false;
    		while (app ==false) {
    		System.out.println("Veuillez indiquer le nom de la carte que vous souhaitez supprimer");
    		String nomc = clavier.nextLine();
    		app = PartieV2.appartient(nomc, idajoué);
    		if (app==false || nomc.equalsIgnoreCase("The Inquisition")==true) {
			System.out.println("Cette carte n'est pas en votre possession ou n'est pas supprimable");
			app=false;
    		}
    		nomcarte=nomc;
    	}
    		int id = PartieV2.trouveIndexMain(idajoué, nomcarte);
    		PartieV2.enleverCarte(id, idajoué);
    		PartieV2.donnerTour(idajoué);
    		return true;
    	}
    	if (theinquisition.equalsIgnoreCase(nom) && hunt==true) {
    		System.out.println("Vous utilisez l'effet Hunt de The Inquisition");
    		return true;
    	}
    	
    	String pointedhat = "Pointed Hat";
    	if (pointedhat.equalsIgnoreCase(nom) && hunt==false) {
    		System.out.println("Vous utilisez l'effet Witch de Pointed Hat");
    		PartieV2.choisirCarteDef(idajoué);
    		PartieV2.donnerTour(idajoué);
    		return true;
    	}
    	if (pointedhat.equalsIgnoreCase(nom) && hunt==true) {
    		System.out.println("Vous utilisez l'effet Hunt de Pointed Hat");
    		return true;
    	}
    	String hookednose = "Hooked Nose";
    	if (hookednose.equalsIgnoreCase(nom) && hunt==false) {
    		System.out.println("Vous utilisez l'effet Witch de Hooked Nose");
    		PartieV2.afficherMain(idaAccusé);
    		boolean app=false;
    		String nomc ="0";
    		String nomchoisie = "0";
    		while (app==false) {
    			System.out.println("\nVeuillez entrer le nom de la carte que vous souhaitez prendre à ce joueur");
    			nomc = clavier.nextLine();
    			app=PartieV2.appartient(nomc, idaAccusé);
    			if (app==false) {
    				System.out.println("\nLe joueur"+idaAccusé+" ne possède pas cette carte");
    			}
    			nomchoisie=nomc;
    		}
    		int idc = PartieV2.trouveIndexMain(idaAccusé, nomchoisie);
    		PartieV2.donnerCarte(idc, idaAccusé, idajoué);
    		PartieV2.donnerTour(idajoué);
    		return true;
    	}
    	if (hookednose.equalsIgnoreCase(nom) && hunt==true) {
    		System.out.println("Vous utilisez l'effet Hunt de Hooked Nose");
    		return true;
    	}
    	String broomstick = "Broomstick";
    	if (broomstick.equalsIgnoreCase(nom) && hunt==false) {
    		System.out.println("Vous utilisez l'effet Witch de Broomstick");
    		PartieV2.donnerTour(idajoué);
    		return true;
    	}
    	if (broomstick.equalsIgnoreCase(nom) && hunt==true) {
    		System.out.println("Vous utilisez l'effet Hunt de Broomstick");
    		return true;
    	}
    	String wart = "Wart";
    	if (wart.equalsIgnoreCase(nom) && hunt==false) {
    		System.out.println("Vous utilisez l'effet Witch de Wart");
    		PartieV2.donnerTour(idajoué);
    		return true;
    	}
    	if (wart.equalsIgnoreCase(nom) && hunt==true) {
    		System.out.println("Vous utilisez l'effet Hunt de Wart");
    		return true;
    	}
    	String duckingstool = "Ducking Stool";
    	if (duckingstool.equalsIgnoreCase(nom) && hunt==false) {
    		System.out.println("Vous utilisez l'effet Witch de Ducking Stool");
    		int idj = -1;
    		while (idj<0 || idj>PartieV2.getnbBots()+PartieV2.getnbJoueursPhys()-1 || PartieV2.enJeu(idj)==false || idj==idajoué) {
    			System.out.println("\nA qui voulez-vous donner le tour ?");
    			idj = clavier.nextInt();
    			if(idj<0 || idj>PartieV2.getnbBots()+PartieV2.getnbJoueursPhys()-1 || PartieV2.enJeu(idj)==false || idj==idajoué) {
    				System.out.println("\nVous ne pouvez pas choisir ce joueur");
    			}
    		}
    		PartieV2.donnerTour(idj);
    		System.out.println("Joueur " + idj + " le joueur "+ idajoué + " vous a donné le tour");
    		return true;
    	}
    	if (duckingstool.equalsIgnoreCase(nom) && hunt==true) {
    		System.out.println("Vous utilisez l'effet Hunt de Ducking Stool");
    		return true;
    	}
    	String cauldron = "Cauldron";
    	if (cauldron.equalsIgnoreCase(nom) && hunt==false) {
    		System.out.println("Vous utilisez l'effet Witch de Cauldron");
    		int taille = PartieV2.donnerTailleMain(idaAccusé);
    		Random random = new Random();
    		int nb;
    		nb = random.nextInt(taille);
    		PartieV2.enleverCarte(nb, idaAccusé);
    		PartieV2.donnerTour(idajoué);
    		return true;
    	}
    	if (cauldron.equalsIgnoreCase(nom) && hunt==true) {
    		System.out.println("Vous utilisez l'effet Hunt de Cauldron");
    		return true;
    	}
    	String evileye = "Evil Eye";
    	if (evileye.equalsIgnoreCase(nom) && hunt==false) {
    		System.out.println("Vous utilisez l'effet Witch de Evil Eye");
        		int idj = -1;
        		while (idj<0 || idj>PartieV2.getnbBots()+PartieV2.getnbJoueursPhys()-1 || PartieV2.enJeu(idj)==false || idj==idajoué) {
        			System.out.println("\nA qui voulez-vous donner le tour ?");
        			idj = clavier.nextInt();
        			if(idj<0 || idj>PartieV2.getnbBots()+PartieV2.getnbJoueursPhys()-1 || PartieV2.enJeu(idj)==false || idj==idajoué) {
        				System.out.println("\nVous ne pouvez pas choisir ce joueur");
        			}
        		}
        		PartieV2.donnerTour(idj);
    		System.out.println("Joueur " + idj + " le joueur "+ idajoué + " vous a donné le tour");
    		if (PartieV2.choixJoueur()) {
    			PartieV2.protegerJoueur(idajoué);
    		}
    		return true;
    	}
    	if (evileye.equalsIgnoreCase(nom) && hunt==true) {
    		System.out.println("Vous utilisez l'effet Hunt de Evil Eye");
    		int idprochaintour = -1;
    		while(idprochaintour <0 || idprochaintour>PartieV2.getnbBots()+PartieV2.getnbJoueursPhys()-1 || idprochaintour == idajoué) {
    		System.out.println("A qui voulez vous donner le tour ?"); 
    		idprochaintour = clavier.nextInt();
    		}
    		PartieV2.donnerTour(idprochaintour);
    		System.out.println("Joueur " + idprochaintour + " le joueur "+ idajoué + " vous a donné le tour");
    		return true;
    	}
    	String toad = "Toad";
    	if (toad.equalsIgnoreCase(nom) && hunt==false) {
    		System.out.println("Vous utilisez l'effet Witch de Toad");
    		PartieV2.donnerTour(idajoué);
    		return true;
    	}
    	if (toad.equalsIgnoreCase(nom) && hunt==true) {
    		System.out.println("Vous utilisez l'effet Hunt de Toad");
    		return true;
    	}
    	String blackcat = "Black Cat";
    	if (blackcat.equalsIgnoreCase(nom) && hunt==false) {
    		System.out.println("Vous utilisez l'effet Witch de Black Cat");
    		PartieV2.donnerTour(idajoué);
    		return true;
    	}
    	if (blackcat.equalsIgnoreCase(nom) && hunt==true) {
    		System.out.println("Vous utilisez l'effet Hunt de Black Cat");
    		return true;
    	}
    	String petnewt = "Pet Newt";
    	if (petnewt.equalsIgnoreCase(nom) && hunt==false) {
    		System.out.println("Vous utilisez l'effet Witch de Pet Newt");
    		PartieV2.donnerTour(idajoué);
    		return true;
    	}
    	if (petnewt.equalsIgnoreCase(nom) && hunt==true) {
    		System.out.println("Vous utilisez l'effet Hunt de Pet Newt");
    		return true;
    	}
    	return false;
    }

    public void afficherDescription(final String nom) {
    	if (nom=="Angry Mob") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Seulement utilisable si votre identité a été révélée et que vous êtes un villageois\nRévelez l'identité d'un autre joueur, si c'est une sorcière vous gagnez 2 points et prenez le prochain tour\n sinon vous perdez 2 points et la personne désignée prend le tour\n\nWitch : Prenez le tour");
    	}
    	else if (nom=="Pointed Hat") {
    		System.out.println("\n\nCarte : "+nom+"\nCette carte n'est jouable que si vous avez déjà une carte rumeur révélée\nHunt : Récupérez une de vos cartes rumeurs révélées et choisissez le prochain joueur\nWitch : Récupérez une de vos cartes rumeurs révélées et prenez le prochain tour");
    	}
    	else if (nom=="The Inquisition") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Seulement utilisable si votre identité a été révélée et que vous êtes un villageois\nChoississez un joueur, il prend le prochain tour mais avant cela, vous pouvez regarder son identité\nWitch : Supprimez une carte de votre main et prenez le prochain tour");	
    	}
    	else if (nom=="Hooked Nose") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Choisissez le prochain joueur, avant son tour prener une carte aléatoire de sa main et mettez la dans votre main\nWitch : Prenez une carte de la main du joueur qui vous a accusé, prenez le prochain tour");
    	}
    	else if (nom=="Broomstick") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Choisir le prochain joueur \nEffet Witch : Prendre le prochain tour");
    	}
    	else if (nom=="Wart") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Choisir le prochain joueur \nEffet Witch : Prendre le prochain tour ");
    	}
    	else if (nom=="Ducking Stool") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Choisir un joueur. Il doit révéler son identité ou se défausser d'une carte de sa main\nSi c'est une Witch : Vous gagnez 1 point et prenez le tour\nSi c'est un villageois : vous perdez 1 point et il prend le tour\n Si il se défausse d'une carte : il prend le tour\n\nEffet Witch : Choisissez le prochain joueur");
    	}
    	else if (nom=="Cauldron") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Révelez votre identité\nSi vous êtes une sorcière : le joueur à votre gauche (id+1) prend le prochain tour\nSi vous êtes un villageois : Choisissez le prochain joueur\n\nEffet Witch : Le joueur qui vous a accusé se défausse d'une carte de sa main au hasard et vous prenez le prochain tour");
    	}
    	else if (nom=="Evil Eye") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Choisissez le prochain joueur. Pendant son tour il ne doit pas vous accusez si c'est possible\nEffet Witch : Choisissez le prochain joueur. Pendant son tour il ne doit pas vous accusez si c'est possible");
    	}
    	else if (nom=="Toad") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Révelez votre identité\nVous êtes une sorcière : le joueur à votre gauche (id+1) prend le prochain tour\nVous êtes un villageois : vous choisissez le prochain joueur\n\nEffet Witch : Prenez le prochain tour");
    	}
    	else if (nom=="Black Cat") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Ajoutez une carte défaussée à votre main et défaussez vous de cette carte puis prenez le prochain tour\nEffet Witch : Prenez le prochain tour");
    	}
    	else if (nom=="Pet Newt") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Prenez une carte rumeur révélée de n'importe quel autre joueur et placez la dans votre main puis choisissez le prochain joueur\nEffet Witch : Prenez le prochain tour");
    	}
    }

}
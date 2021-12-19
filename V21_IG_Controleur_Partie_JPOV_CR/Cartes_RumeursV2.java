package frr.utt.lo02.projet.Modele;

import java.util.Scanner;
import java.util.Observable;
import java.util.Iterator;
import java.util.Random;

public class Cartes_RumeursV2 extends Observable{
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
    		int idj=-1;
    		boolean choisissable = false;
    		while(choisissable==false) {
    			choisissable = true;
    			System.out.println("\nVeuillez entrer l'id du joueur dont vous voulez révéler l'identité");
    			idj=clavier.nextInt();
    			if(idj<0 || idj>PartieV2.getnbBots()+PartieV2.getnbJoueursPhys()-1) {
    				System.out.println("\nCe joueur n'existe pas ");
    				choisissable=false;
    			}
    			else if (PartieV2.getJouerJoueur().get(idj).révélé==true) {
    				System.out.println("\nL'identité de ce joueur a déjà été révélée");
    				choisissable=false;
    			}
    			String broomstick = "Broomstick";
    			for(int i=0;i<PartieV2.getJouerJoueur().get(idj).cartesjouees.size();i++) {
    				if(PartieV2.getJouerJoueur().get(idj).cartesjouees.get(i).nom.equalsIgnoreCase(broomstick)) {
    					choisissable=false;
    					System.out.println("Ce joueur a utilisé la carte Broomstick, vous ne pouvez pas utiliser Angry Mob contre lui");
    				}
    			}
    		}
    		boolean role = PartieV2.revelerRole(idj);
    		if (role == true) {
    			PartieV2.ajoutPoint(idaAccusé);
    			PartieV2.ajoutPoint(idaAccusé);
    			PartieV2.donnerTour(idaAccusé);
    			PartieV2.getJouerJoueur().get(idj).enJeu=false;
    		}
    		else {
    			PartieV2.enleverPoint(idaAccusé);
    			PartieV2.enleverPoint(idaAccusé);
    			PartieV2.donnerTour(idj);
    		}
    		return true;
    	}
    	String theinquisition = "The Inquisition";
    	if (theinquisition.equalsIgnoreCase(nom) && hunt==false) {
    		String nomcarte="0";
    		System.out.println("Vous utilisez l'effet Witch de The Inquisition");
    		if(PartieV2.getJouerJoueur().get(idajoué).getBot()==false) {
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
    		}
    		else {
				int taille = PartieV2.getJouerJoueur().get(idajoué).main.size();
        		boolean app= false;
        		int idchoisie = -1;
        		while (app ==false) {
	        	Random random = new Random();
	        	int nb;
	        	nb = random.nextInt(taille);
	        	idchoisie = nb;
	        	nomcarte = PartieV2.getJouerJoueur().get(idajoué).main.get(nb).nom;
        		app = PartieV2.appartient(nomcarte, idajoué);
    		}
        		int id = PartieV2.trouveIndexMain(idajoué, nomcarte);
        		System.out.println("Le bot n°"+idajoué+" a supprimé la carte "+PartieV2.getJouerJoueur().get(idajoué).main.get(id).nom);
        		PartieV2.enleverCarte(id, idajoué);
        		PartieV2.donnerTour(idajoué);
    		}
    		return true;
    	}
    	if (theinquisition.equalsIgnoreCase(nom) && hunt==true) {
    		System.out.println("Vous utilisez l'effet Hunt de The Inquisition");
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
    		System.out.println("\nIl peut regarder votre identité");
    		System.out.println("\n!!!Seulement pour le joueur "+idajoué);
    		PartieV2.revelerRole(idj);
    		return true;
    	}
    	
    	String pointedhat = "Pointed Hat";
    	if (pointedhat.equalsIgnoreCase(nom) && hunt==false) {
    		System.out.println("Vous utilisez l'effet Witch de Pointed Hat");
    		if(PartieV2.getJouerJoueur().get(idajoué).getBot()==false) {
    		PartieV2.choisirCarteDef(idajoué);
    		PartieV2.donnerTour(idajoué);
    		}
    		else {
    			int num = -1;
    			Random random = new Random();
    			num = random.nextInt(PartieV2.getJouerJoueur().get(idajoué).getCartesJouees().size());
    			while(num<0 || num>(PartieV2.getJouerJoueur().get(idajoué).getCartesJouees().size())) {
    			}
    			
    			PartieV2.getJouerJoueur().get(idajoué).main.add(PartieV2.getJouerJoueur().get(idajoué).cartesjouees.get(num));
    			for (int i=0;i<PartieV2.getCartesDefaussées().size();i++) {
    				if ((PartieV2.getCartesDefaussées().get(i).nom).equalsIgnoreCase(PartieV2.getJouerJoueur().get(idajoué).cartesjouees.get(num).nom)) {
    					PartieV2.getCartesDefaussées().remove(PartieV2.getCartesDefaussées().get(i));
    				}
    	    }
    			PartieV2.getJouerJoueur().get(idajoué).cartesjouees.remove(PartieV2.getJouerJoueur().get(idajoué).cartesjouees.get(num)); 
    			}
    		return true;
    	}
    	if (pointedhat.equalsIgnoreCase(nom) && hunt==true) {
    		System.out.println("Vous utilisez l'effet Hunt de Pointed Hat");
    		PartieV2.choisirCarteDef(idajoué);
    		int idj = -1;
    		while (idj<0 || idj>PartieV2.getnbBots()+PartieV2.getnbJoueursPhys()-1 || PartieV2.enJeu(idj)==false || idj==idajoué) {
    			System.out.println("\nA qui voulez-vous donner le tour ?");
    			idj = clavier.nextInt();
    			if(idj<0 || idj>PartieV2.getnbBots()+PartieV2.getnbJoueursPhys()-1 || PartieV2.enJeu(idj)==false || idj==idajoué) {
    				System.out.println("\nVous ne pouvez pas choisir ce joueur");
    			}
    		}
    		PartieV2.donnerTour(idj);
    		
    		return true;
    	}
    	String hookednose = "Hooked Nose";
    	if (hookednose.equalsIgnoreCase(nom) && hunt==false) {
    		System.out.println("Vous utilisez l'effet Witch de Hooked Nose");
    		if(PartieV2.getJouerJoueur().get(idajoué).getBot()==false) {
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
    		}
    		else {
    			boolean app=false;
        		String nomc ="0";
        		String nomchoisie = "0";
        		int taille = PartieV2.getJouerCarte().size();
        		while (app==false) {
            		Random random = new Random();
    	        	int nb;
    	        	nb = random.nextInt(taille);
    	        	nomc = PartieV2.getJouerCarte().get(nb).nom;
        			app=PartieV2.appartient(nomc, idaAccusé);
        			nomchoisie=nomc;
        		}
        		int idc = PartieV2.trouveIndexMain(idaAccusé, nomchoisie);
        		PartieV2.donnerCarte(idc, idaAccusé, idajoué);
        		PartieV2.donnerTour(idajoué);
    		}
    		return true;
    	}
    	if (hookednose.equalsIgnoreCase(nom) && hunt==true) {
    		System.out.println("Vous utilisez l'effet Hunt de Hooked Nose");
    		int idj = -1;
    		while (idj<0 || idj>PartieV2.getnbBots()+PartieV2.getnbJoueursPhys()-1 || PartieV2.enJeu(idj)==false || idj==idajoué) {
    			System.out.println("\nA qui voulez-vous donner le tour ?");
    			idj = clavier.nextInt();
    			if(idj<0 || idj>PartieV2.getnbBots()+PartieV2.getnbJoueursPhys()-1 || PartieV2.enJeu(idj)==false || idj==idajoué || PartieV2.donnerTailleMain(idj)==0) {
    				System.out.println("\nVous ne pouvez pas choisir ce joueur");
    			}
    		}
    		PartieV2.donnerTour(idj);
    		System.out.println("Le joueur "+idaAccusé+" donne le tour au joueur "+idj);
    		int taille = PartieV2.donnerTailleMain(idj);
    		Random random = new Random();
    		int nb;
    		nb = random.nextInt(taille);
    		PartieV2.getJouerJoueur().get(idaAccusé).main.add(PartieV2.getJouerJoueur().get(idj).main.get(nb));
    		PartieV2.getJouerJoueur().get(idj).main.remove(PartieV2.getJouerJoueur().get(idj).main.get(nb));
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
    		int idj = -1;
    		while (idj<0 || idj>PartieV2.getnbBots()+PartieV2.getnbJoueursPhys()-1 || PartieV2.enJeu(idj)==false || idj==idajoué) {
    			System.out.println("\nA qui voulez-vous donner le tour ?");
    			idj = clavier.nextInt();
    			if(idj<0 || idj>PartieV2.getnbBots()+PartieV2.getnbJoueursPhys()-1 || PartieV2.enJeu(idj)==false || idj==idajoué) {
    				System.out.println("\nVous ne pouvez pas choisir ce joueur");
    			}
    		}
    		PartieV2.donnerTour(idj);
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
    		int idj = -1;
    		while (idj<0 || idj>PartieV2.getnbBots()+PartieV2.getnbJoueursPhys()-1 || PartieV2.enJeu(idj)==false || idj==idajoué) {
    			System.out.println("\nA qui voulez-vous donner le tour ?");
    			idj = clavier.nextInt();
    			if(idj<0 || idj>PartieV2.getnbBots()+PartieV2.getnbJoueursPhys()-1 || PartieV2.enJeu(idj)==false || idj==idajoué) {
    				System.out.println("\nVous ne pouvez pas choisir ce joueur");
    			}
    		}
    		PartieV2.donnerTour(idj);
    		return true;
    	}
    	String duckingstool = "Ducking Stool";
    	if (duckingstool.equalsIgnoreCase(nom) && hunt==false) {
    		System.out.println("Vous utilisez l'effet Witch de Ducking Stool");
    		if(PartieV2.getJouerJoueur().get(idajoué).getBot()==false) {
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
    		}
    		else {
    			int taille = PartieV2.getJouerJoueur().size();
    			Random random = new Random();
	        	int idj=-1;
	        	boolean joueurvalide=false;
	        	while(joueurvalide==false) {
	        	idj = random.nextInt(taille);
        		if(idj>=0 && idj<(PartieV2.getnbBots()+PartieV2.getnbJoueursPhys()) && idj != idajoué  && PartieV2.getJouerJoueur().get(idj).enJeu==true) {
        		joueurvalide=true;
        		}
	        	}PartieV2.donnerTour(idj);
    		}
    		return true;
    	}
    	if (duckingstool.equalsIgnoreCase(nom) && hunt==true) {
    		System.out.println("Vous utilisez l'effet Hunt de Ducking Stool");
    		int idj = -1;
    		while (idj<0 || idj>PartieV2.getnbBots()+PartieV2.getnbJoueursPhys()-1 || PartieV2.enJeu(idj)==false || idj==idajoué) {
    			System.out.println("\nQui voulez-vous choisir ?");
    			idj = clavier.nextInt();
    			if(idj<0 || idj>PartieV2.getnbBots()+PartieV2.getnbJoueursPhys()-1 || PartieV2.enJeu(idj)==false || idj==idajoué) {
    				System.out.println("\nVous ne pouvez pas choisir ce joueur");
    			}
    			String wart2 = "Wart";
    			for(int i=0;i<PartieV2.getJouerJoueur().get(idj).cartesjouees.size();i++) {
    				if(PartieV2.getJouerJoueur().get(idj).cartesjouees.get(i).nom.equalsIgnoreCase(wart2)) {
    					idj=-1;
    					System.out.println("Ce joueur a utilisé la carte Wart, vous ne pouvez pas utiliser Ducking Stool contre lui");
    				}
    			}
    		}
    		System.out.println("\nJoueur "+idj+" voulez-vous supprimer une carte(0) ou révéler votre identité(1) ?");
    		int rep = clavier.nextInt();
    		if(rep==1 || PartieV2.getJouerJoueur().get(idj).main.size()==0) {
    			if (PartieV2.getJouerJoueur().get(idj).main.size()==0) {
    				System.out.println("Joueur "+idj+" vous n'avez plus de cartes en main, vous devez révéler votre identité");
    			}
    			boolean witch = PartieV2.revelerRole(idj);
    			if(witch==true) {
    				PartieV2.donnerTour(idajoué);
    				PartieV2.ajoutPoint(idajoué);
    			}
    			else {
    				PartieV2.donnerTour(idj);
    				PartieV2.enleverPoint(idajoué);
    			}
    		}
    		else {
    			System.out.println("Vous avez décidé de supprimer une carte");
    			for (int i=0;i<PartieV2.getJouerJoueur().get(idj).main.size();i++) {
    				PartieV2.getJouerJoueur().get(idj).main.get(i).afficherDescription(PartieV2.getJouerJoueur().get(idj).main.get(i).nom);
    		}
    			String nomcar ="0"; 
    			boolean appartient = false;
    			short bufferClavier=0;
    			if(bufferClavier==0) {  // Vide la premiere ligne 1 fois seulement pour ne pas sauter la ligne cartech = clavier.nextLine();
    	    		clavier.nextLine();
    	    		bufferClavier=1;
    	    		}
    			while (appartient==false) {
    				System.out.println("\nVeuillez choisir une carte à supprimer");
    				String cartech=clavier.nextLine();
    				appartient=PartieV2.appartient(cartech, idj);
    				if(appartient==false) {
    					System.out.println("\nCette carte de vous appartient pas");
    				}
    				nomcar=cartech;
    			}
    			int idc = PartieV2.trouveIndexMain(idj, nomcar);
    			PartieV2.enleverCarte(idc, idj);
    			PartieV2.donnerTour(idj);
    			
    		return true;
    	}
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
    		boolean witch = PartieV2.revelerRole(idajoué);
    		if(witch==true) {
    			//On considère que le joueur à gauche est le joueur avec l'id d'après
    			int i=idajoué;
    			while(PartieV2.getJouerJoueur().get(i).enJeu==false) {
    				if(i+1>=(PartieV2.getnbBots()+PartieV2.getnbJoueursPhys())){
    					i=0;
    				}
    				else {
    					i++;
    				}
    			}
    			PartieV2.donnerTour(i);
    		}
    		else {
        		int idj = -1;
        		while (idj<0 || idj>PartieV2.getnbBots()+PartieV2.getnbJoueursPhys()-1 || PartieV2.enJeu(idj)==false || idj==idajoué) {
        			System.out.println("\nA qui voulez-vous donner le tour ?");
        			idj = clavier.nextInt();
        			if(idj<0 || idj>PartieV2.getnbBots()+PartieV2.getnbJoueursPhys()-1 || PartieV2.enJeu(idj)==false || idj==idajoué) {
        				System.out.println("\nVous ne pouvez pas choisir ce joueur");
        			}
        		}
        		PartieV2.donnerTour(idj);
    		}
    		return true;
    	}
    	String evileye = "Evil Eye";
    	if (evileye.equalsIgnoreCase(nom) && hunt==false) {
    		System.out.println("Vous utilisez l'effet Witch de Evil Eye");
    		if(PartieV2.getJouerJoueur().get(idajoué).getBot()==false) {
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
    		}
    		else {
    			int taille = PartieV2.getJouerJoueur().size();
    			Random random = new Random();
	        	int idj=-1;
	        	boolean joueurvalide=false;
	        	while(joueurvalide==false) {
	        	idj = random.nextInt(taille);
        		if(idj>=0 && idj<(PartieV2.getnbBots()+PartieV2.getnbJoueursPhys()) && idj != idajoué  && PartieV2.getJouerJoueur().get(idj).enJeu==true) {
        		joueurvalide=true;
        		}
	        	}
	        	PartieV2.donnerTour(idj);
	        	System.out.println("Joueur " + idj + " le joueur "+ idajoué + " vous a donné le tour");
	    		if (PartieV2.choixJoueur()) {
	    			PartieV2.protegerJoueur(idajoué);
	    		}
    		}
    		return true;
    	}
    	if (evileye.equalsIgnoreCase(nom) && hunt==true) {
    		System.out.println("Vous utilisez l'effet Hunt de Evil Eye");
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
    	String toad = "Toad";
    	if (toad.equalsIgnoreCase(nom) && hunt==false) {
    		System.out.println("Vous utilisez l'effet Witch de Toad");
    		PartieV2.donnerTour(idajoué);
    		return true;
    	}
    	if (toad.equalsIgnoreCase(nom) && hunt==true) {
    		System.out.println("Vous utilisez l'effet Hunt de Toad");
    		boolean witch = PartieV2.revelerRole(idajoué);
    		if(witch==true) {
    			//On considère que le joueur à gauche est le joueur avec l'id d'après
    			int i=idajoué;
    			while(PartieV2.getJouerJoueur().get(i).enJeu==false) {
    				if(i+1>=(PartieV2.getnbBots()+PartieV2.getnbJoueursPhys())){
    					i=0;
    				}
    				else {
    					i++;
    				}
    			}
    			PartieV2.donnerTour(i);
    		}
    		else {
        		int idj = -1;
        		while (idj<0 || idj>PartieV2.getnbBots()+PartieV2.getnbJoueursPhys()-1 || PartieV2.enJeu(idj)==false || idj==idajoué) {
        			System.out.println("\nA qui voulez-vous donner le tour ?");
        			idj = clavier.nextInt();
        			if(idj<0 || idj>PartieV2.getnbBots()+PartieV2.getnbJoueursPhys()-1 || PartieV2.enJeu(idj)==false || idj==idajoué) {
        				System.out.println("\nVous ne pouvez pas choisir ce joueur");
        			}
        		}
        		PartieV2.donnerTour(idj);
    		}
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
    		System.out.println("\nVoici les cartes supprimées :");
    		int i=0;
    		Iterator<Cartes_RumeursV2> itcr = PartieV2.getCartesDefaussées().iterator();
    		while(itcr.hasNext()) {
    			String nomcar=itcr.next().nom;
    			PartieV2.getCartesDefaussées().get(i).afficherDescription(nomcar);
    			i ++;
    		}
    		String nomajouté = "0";//nom de la carte qui va être récupérée
    		boolean recuperer =false;
    		while(recuperer==false) {
    			System.out.println("\nQuelle carte voulez-vous ?");
    			String choisie = clavier.nextLine();
    			for(i=0;i<PartieV2.getCartesDefaussées().size();i++) {
    				if(PartieV2.getCartesDefaussées().get(i).nom.equalsIgnoreCase(choisie)) {
    					recuperer=true;
    					PartieV2.getJouerJoueur().get(idajoué).main.add(PartieV2.getCartesDefaussées().get(i));
    					PartieV2.getCartesDefaussées().remove(PartieV2.getCartesDefaussées().get(i));
    					nomajouté=choisie;
    				}
    			}
    		}
    		int indexcarte = PartieV2.trouveIndexMain(idajoué,nomajouté);
    		PartieV2.enleverCarte(indexcarte, idajoué);
    		PartieV2.donnerTour(idajoué);
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
    		System.out.println("\nVoici les cartes utilisées :");
    		int i=0;
    		//affichage des cartes utilisées
    		Iterator<Cartes_RumeursV2> itcr = PartieV2.getCartesDefaussées().iterator();
    		while(itcr.hasNext()) {
    			String nomcar=itcr.next().nom;
    			PartieV2.getCartesDefaussées().get(i).afficherDescription(nomcar);
    			i ++;
    		}
    		//String nomajouté = "0";nom de la carte qui va être récupérée
    		boolean aJoué = false;
    		boolean recuperer =false;
    		while(recuperer==false) {
    			System.out.println("\nQuelle carte voulez-vous ?");
    			String choisie = clavier.nextLine();
    			for(i=0;i<PartieV2.getCartesDefaussées().size();i++) {
    				if(PartieV2.getCartesDefaussées().get(i).nom.equalsIgnoreCase(choisie)) {
    					for(int j=0;j<PartieV2.getJouerJoueur().get(idajoué).cartesjouees.size();j++) {
    						if(PartieV2.getJouerJoueur().get(idajoué).cartesjouees.get(j).nom==PartieV2.getCartesDefaussées().get(i).nom) {
    							aJoué=true;
    							System.out.println("C'est vous qui avez joué cette carte, vous ne pouvez pas la récupérer");
    						}
    					}
    					if(aJoué==false) {
    					recuperer=true;
    					PartieV2.getJouerJoueur().get(idajoué).main.add(PartieV2.getCartesDefaussées().get(i));
    					PartieV2.getCartesDefaussées().remove(PartieV2.getCartesDefaussées().get(i));
    					//nomajouté=choisie;
    					}
    					aJoué=false;
    				}
    				if(frr.utt.lo02.projet.Modele.PartieV2.cartesup1.nom.equalsIgnoreCase(choisie)|| frr.utt.lo02.projet.Modele.PartieV2.cartesup2.nom.equalsIgnoreCase(choisie)) {
    					recuperer=false;
    					System.out.println("\nCette carte n'a pas été révélée par un joueur, vous ne pouvez pas la récupérer");
    				}
    			}
    		}
    		int idj = -1;
    		while (idj<0 || idj>PartieV2.getnbBots()+PartieV2.getnbJoueursPhys()-1 || PartieV2.enJeu(idj)==false || idj==idajoué) {
    			System.out.println("\nA qui voulez-vous donner le tour ?");
    			idj = clavier.nextInt();
    			if(idj<0 || idj>PartieV2.getnbBots()+PartieV2.getnbJoueursPhys()-1 || PartieV2.enJeu(idj)==false || idj==idajoué) {
    				System.out.println("\nVous ne pouvez pas choisir ce joueur");
    			}
    		}
    		PartieV2.donnerTour(idj);
    		return true;
    	}
    	return false;
    }

    public String afficherDescription(final String nom) {
    	if (nom=="Angry Mob") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Seulement utilisable si votre identité a été révélée et que vous êtes un villageois\nRévelez l'identité d'un autre joueur, si c'est une sorcière vous gagnez 2 points et prenez le prochain tour\n sinon vous perdez 2 points et la personne désignée prend le tour\n\nWitch : Prenez le tour");
    		return "\nEffet Hunt : Seulement utilisable si votre identité a été révélée et que vous êtes un villageois\nRévelez l'identité d'un autre joueur, si c'est une sorcière vous gagnez 2 points et prenez le prochain tour\n sinon vous perdez 2 points et la personne désignée prend le tour\n\\nWitch : Prenez le tour";
    	}
    	else if (nom=="Pointed Hat") {
    		System.out.println("\n\nCarte : "+nom+"\nCette carte n'est jouable que si vous avez déjà une carte rumeur révélée\nHunt : Récupérez une de vos cartes rumeurs révélées et choisissez le prochain joueur\nWitch : Récupérez une de vos cartes rumeurs révélées et prenez le prochain tour");
    		return "\nCette carte n'est jouable que si vous avez déjà une carte rumeur révélée\nHunt : Récupérez une de vos cartes rumeurs révélées et choisissez le prochain joueur\nWitch : Récupérez une de vos cartes rumeurs révélées et prenez le prochain tour";
    	}
    	else if (nom=="The Inquisition") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Seulement utilisable si votre identité a été révélée et que vous êtes un villageois\nChoississez un joueur, il prend le prochain tour mais avant cela, vous pouvez regarder son identité\nWitch : Supprimez une carte de votre main et prenez le prochain tour");	
    		return "\nEffet Hunt : Seulement utilisable si votre identité a été révélée et que vous êtes un villageois\nChoississez un joueur, il prend le prochain tour mais avant cela, vous pouvez regarder son identité\nWitch : Supprimez une carte de votre main et prenez le prochain tour";
    	}
    	else if (nom=="Hooked Nose") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Choisissez le prochain joueur, avant son tour prener une carte aléatoire de sa main et mettez la dans votre main\nWitch : Prenez une carte de la main du joueur qui vous a accusé, prenez le prochain tour");
    		return "\nEffet Hunt : Choisissez le prochain joueur, avant son tour prener une carte aléatoire de sa main et mettez la dans votre main\nWitch : Prenez une carte de la main du joueur qui vous a accusé, prenez le prochain tour";
    	}
    	else if (nom=="Broomstick") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Choisir le prochain joueur \nEffet Witch : Prendre le prochain tour");
    		return "\nEffet Hunt : Choisir le prochain joueur \nEffet Witch : Prendre le prochain tour";
    	}
    	else if (nom=="Wart") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Choisir le prochain joueur \nEffet Witch : Prendre le prochain tour ");
    		return "\nEffet Hunt : Choisir le prochain joueur \nEffet Witch : Prendre le prochain tour ";
    	}
    	else if (nom=="Ducking Stool") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Choisir un joueur. Il doit révéler son identité ou se défausser d'une carte de sa main\nSi c'est une Witch : Vous gagnez 1 point et prenez le tour\nSi c'est un villageois : vous perdez 1 point et il prend le tour\n Si il se défausse d'une carte : il prend le tour\n\nEffet Witch : Choisissez le prochain joueur");
    		return "\nEffet Hunt : Choisir un joueur. Il doit révéler son identité ou se défausser d'une carte de sa main\nSi c'est une Witch : Vous gagnez 1 point et prenez le tour\nSi c'est un villageois : vous perdez 1 point et il prend le tour\n Si il se défausse d'une carte : il prend le tour\n\nEffet Witch : Choisissez le prochain joueur";
    	}
    	else if (nom=="Cauldron") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Révelez votre identité\nSi vous êtes une sorcière : le joueur à votre gauche (id+1) prend le prochain tour\nSi vous êtes un villageois : Choisissez le prochain joueur\n\nEffet Witch : Le joueur qui vous a accusé se défausse d'une carte de sa main au hasard et vous prenez le prochain tour");
    		return "\nEffet Hunt : Révelez votre identité\nSi vous êtes une sorcière : le joueur à votre gauche (id+1) prend le prochain tour\nSi vous êtes un villageois : Choisissez le prochain joueur\n\nEffet Witch : Le joueur qui vous a accusé se défausse d'une carte de sa main au hasard et vous prenez le prochain tour";
    	}
    	else if (nom=="Evil Eye") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Choisissez le prochain joueur. Pendant son tour il ne doit pas vous accusez si c'est possible\nEffet Witch : Choisissez le prochain joueur. Pendant son tour il ne doit pas vous accusez si c'est possible");
    		return "\nEffet Hunt : Choisissez le prochain joueur. Pendant son tour il ne doit pas vous accusez si c'est possible\nEffet Witch : Choisissez le prochain joueur. Pendant son tour il ne doit pas vous accusez si c'est possible";
    	}
    	else if (nom=="Toad") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Révelez votre identité\nVous êtes une sorcière : le joueur à votre gauche (id+1) prend le prochain tour\nVous êtes un villageois : vous choisissez le prochain joueur\n\nEffet Witch : Prenez le prochain tour");
    		return "\nEffet Hunt : Révelez votre identité\nVous êtes une sorcière : le joueur à votre gauche (id+1) prend le prochain tour\nVous êtes un villageois : vous choisissez le prochain joueur\n\nEffet Witch : Prenez le prochain tour";
    	}
    	else if (nom=="Black Cat") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Ajoutez une carte défaussée à votre main et défaussez vous de cette carte puis prenez le prochain tour\nEffet Witch : Prenez le prochain tour");
    		return "\nEffet Hunt : Ajoutez une carte défaussée à votre main et défaussez vous de cette carte puis prenez le prochain tour\nEffet Witch : Prenez le prochain tour";
    	}
    	else if (nom=="Pet Newt") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Prenez une carte rumeur révélée de n'importe quel autre joueur et placez la dans votre main puis choisissez le prochain joueur\nEffet Witch : Prenez le prochain tour");
    		return "\nEffet Hunt : Prenez une carte rumeur révélée de n'importe quel autre joueur et placez la dans votre main puis choisissez le prochain joueur\nEffet Witch : Prenez le prochain tour";
    	}
    	else {
    		return "Cette carte est inconnue";
    	}
    }
    public String afficherEffetHunt(String nom) {
    	if (nom=="Angry Mob") {
    		return "Seulement utilisable si votre identité a été révélée et que vous êtes un villageois\nRévelez l'identité d'un autre joueur, si c'est une sorcière vous gagnez 2 points et prenez le prochain tour\n sinon vous perdez 2 points et la personne désignée prend le tour";
    	}
    	else if (nom=="Pointed Hat") {
    		return "Cette carte n'est jouable que si vous avez déjà une carte rumeur révélée\nHunt : Récupérez une de vos cartes rumeurs révélées et choisissez le prochain joueur";
    	}
    	else if (nom=="The Inquisition") {
    		return "Seulement utilisable si votre identité a été révélée et que vous êtes un villageois\nChoississez un joueur, il prend le prochain tour mais avant cela, vous pouvez regarder son identité";
    	}
    	else if (nom=="Hooked Nose") {
    		return "Choisissez le prochain joueur, avant son tour prener une carte aléatoire de sa main et mettez la dans votre main";
    	}
    	else if (nom=="Broomstick") {
    		return "Choisir le prochain joueur";
    	}
    	else if (nom=="Wart") {
    		return "Choisir le prochain joueur";
    	}
    	else if (nom=="Ducking Stool") {
    		return "Choisir un joueur. Il doit révéler son identité ou se défausser d'une carte de sa main\nSi c'est une Witch : Vous gagnez 1 point et prenez le tour\nSi c'est un villageois : vous perdez 1 point et il prend le tour\n Si il se défausse d'une carte : il prend le tour";
    	}
    	else if (nom=="Cauldron") {
    		return "Révelez votre identité\nSi vous êtes une sorcière : le joueur à votre gauche (id+1) prend le prochain tour\nSi vous êtes un villageois : Choisissez le prochain joueur";
    	}
    	else if (nom=="Evil Eye") {
    		return "Choisissez le prochain joueur. Pendant son tour il ne doit pas vous accusez si c'est possible";
    	}
    	else if (nom=="Toad") {
    		return "Révelez votre identité\nVous êtes une sorcière : le joueur à votre gauche (id+1) prend le prochain tour\nVous êtes un villageois : vous choisissez le prochain joueur";
    	}
    	else if (nom=="Black Cat") {
    		return "Ajoutez une carte défaussée à votre main et défaussez vous de cette carte puis prenez le prochain tour";
    	}
    	else if (nom=="Pet Newt") {
    		return "Prenez une carte rumeur révélée de n'importe quel autre joueur et placez la dans votre main puis choisissez le prochain joueur";
    	}
    	else {
    		return "Cette carte est inconnue";
    	}    	
    }
    public String afficherEffetWitch(String nom) {
    	if (nom=="Angry Mob") {
    		return "Prenez le tour";
    	}
    	else if (nom=="Pointed Hat") {
    		return "\nCette carte n'est jouable que si vous avez déjà une carte rumeur révélée. Récupérez une de vos cartes rumeurs révélées et prenez le prochain tour";
    	}
    	else if (nom=="The Inquisition") {
    		return "Supprimez une carte de votre main et prenez le prochain tour";
    	}
    	else if (nom=="Hooked Nose") {
    		return "Prenez une carte de la main du joueur qui vous a accusé, prenez le prochain tour";
    	}
    	else if (nom=="Broomstick") {
    		return "Prendre le prochain tour";
    	}
    	else if (nom=="Wart") {
    		return "Prendre le prochain tour ";
    	}
    	else if (nom=="Ducking Stool") {
    		return "Choisissez le prochain joueur";
    	}
    	else if (nom=="Cauldron") {
    		return "Le joueur qui vous a accusé se défausse d'une carte de sa main au hasard et vous prenez le prochain tour";
    	}
    	else if (nom=="Evil Eye") {
    		return "Choisissez le prochain joueur. Pendant son tour il ne doit pas vous accusez si c'est possible";
    	}
    	else if (nom=="Toad") { 
    		return "Prenez le prochain tour";
    	}
    	else if (nom=="Black Cat") {
    		return "Prenez le prochain tour";
    	}
    	else if (nom=="Pet Newt") {
    		return "Prenez le prochain tour";
    	}
    	else {
    		return "Cette carte est inconnue";
    	}   	
    }

}
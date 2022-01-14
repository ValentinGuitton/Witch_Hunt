package frr.utt.lo02.projet.Modele;

import java.util.Iterator;
import java.util.Observable;
import java.util.Scanner;
import java.util.Random;
/**
 * 
 * @author lou prevost, valentin guitton
 *
 */
/**
 * Cette classe impl�mente la strat�gie agressive des bots.
 * Elle h�rite de la classe Observable pour que si des bots agissent, cela ait une cons�quence sur l'interface graphique.
 * Elle implemente �galement l'interface Strat�gie, bien que cette derni�re ne soit pas utilis�e car la strat�gie de bot Agressive est la seule que nous avons eu le temps d'impl�menter.
 */
public abstract class Agressive extends Observable implements Strat�gie  {
	/**
	 * Permet de pr�venir l'interface graphique qu'il y a eu un changement
	 */
	public void prevenirChange() {;
		this.setChanged();
		this.notifyObservers();
	}
	/**
	 * 
	 * @param joueur
	 * @param accuse
	 */
	/**
	 * Contient l'ensemble des s�quences de jeu concernant un bot agressif.
	 * Si ce bot est accus�, si il poss�de des cartes rumeurs il joue l'une d'entre elles en utilisant un effet Witch, sinon il d�voile son r�le.
	 * Si c'est au tour du bot de jouer, celui-ci �tant agressif, il va automatiquement accuser un joueur.
	 */
    public static void jouerBot(Joueur_Physique_ou_VirtuelV2 joueur,Joueur_Physique_ou_VirtuelV2 accuse) {
   	Scanner clavier = new Scanner(System.in);
    	if(joueur.getTour()==true && joueur.getBot()==true) {
    		PartieV2.enleverTour(joueur.getId());
    		if(joueur.getEstAccus�()==true) {
    			System.out.println("Le bot n�" + joueur.getId() + " est accus�"); // ACCUSATION SUR LE BOT
    			if(joueur.main.size()==0) {
    				System.out.println("\nBot n�"+joueur.getId()+" vous n'avez plus de cartes rumeur, votre identit� va �tre r�v�l�e");
    				PartieV2.getJouerJoueur().get(joueur.getId()).r�v�l�=true;
    				PartieV2.comptr�v�l�s++;
    				if(joueur.revelerRole()=="Sorciere") {
    					String role = "Sorciere";
    					System.out.println("\nLe r�le du bot n�"+joueur.getId()+" est : " + role);
    					PartieV2.ajoutPoint(accuse.getId());
    					PartieV2.donnerTour(accuse.getId());
    				}
    				else {
    					String role = "Villageois";
    					System.out.println("\nLe r�le du bot n�"+joueur.getId()+" est : " + role);
    					PartieV2.ajoutPoint(joueur.getId());
    					PartieV2.donnerTour(joueur.getId());
    				}
    				
    				
    			}
    			else {
    					int compt=0;
    					int taille = PartieV2.getJouerCarte().size();
    	        		boolean app= false;
    	        		String nomcarte ="0";
    	        		int idchoisie = -1;
    	        		while (app ==false && compt<50) {
        	        	Random random = new Random();
        	        	int nb;
        	        	nb = random.nextInt(taille);
        	        	idchoisie = nb;
        	        	nomcarte = PartieV2.getJouerCarte().get(nb).nom;
    	        		app = PartieV2.appartient(nomcarte, joueur.getId());
    	        		String theinquisitionw = "The Inquisition";
    	        		if (theinquisitionw.equalsIgnoreCase(nomcarte) && joueur.main.size()<2) {
    	    				app=false;
    	    			}
    	        		String hookednose = "Hooked Nose";
    	        		if (hookednose.equalsIgnoreCase(nomcarte) && accuse.main.size()==0) {
    	    				app=false;
    	    			}
    	        		String pointedhat = "Pointed Hat";
    	        		if (pointedhat.equalsIgnoreCase(nomcarte)&& joueur.cartesjouees.size()==0) {
    	        			app=false;
    	        		}
    	        		if(app==false) {
    	        			compt++;
    	        		}
    	        		if(app==true) {
    	        		//si on utilise la carte evil eye, on stocke en m�moire le nom du joueur qui l'a utilis�
    	        		String evileye = "Evil Eye";
    	        		/*if (evileye.equalsIgnoreCase(nomcarte)){
    	        		System.out.println("Je suis pass� ici");
    	        		joueur.getId();
    	        		}*/
    	        		//si la carte evil eye a �t� d�fauss�es (on n'entre dans cette boucle que lors du tour qui suit celui o� a �t� jou� la carte), le joueur ayant jou� cette carte peut-�tre accus�
    	        		//cette boucle intervenant apr�s le choix du joueur, celui qui a jou� cette carte est donc prot�g� pendant 1 tour
    	        		Iterator<Cartes_RumeursV2> it4 = PartieV2.getCartesDefauss�es().iterator();
    	        		while(it4.hasNext()) {
    	        		if ((it4.next().nom).equalsIgnoreCase(evileye)) {
    	        			for (int i=0;i<PartieV2.getnbBots()+PartieV2.getnbJoueursPhys();i++) {
    	        				Iterator<Cartes_RumeursV2> it5 = PartieV2.getJouerJoueur().get(i).getCartesJouees().iterator();
    	        				while ((it5.hasNext())) {
    	        					if((it5.next().nom).equalsIgnoreCase(evileye)){
    	        						PartieV2.getJouerJoueur().get(i).peutEtreAccus�=true;
    	        				}
    	        				}
    	        		}
    	        		}
    	        	}
    	        		System.out.println("Le bot "+joueur.getId()+" joue la carte "+nomcarte);
    	        		idchoisie = PartieV2.trouveIndexMain(joueur.getId(),nomcarte);
    	        		joueur.main.get(idchoisie).jouerCarte(nomcarte,false,joueur.getId(),accuse.getId());
    	        		PartieV2.enleverCarte(idchoisie,joueur.getId());
    	        		}
    	        	}
    	        		if(compt>=50) {
    	        			PartieV2.comptr�v�l�s++;
    	        			PartieV2.getJouerJoueur().get(joueur.getId()).r�v�l�=true;
    	        			if(joueur.revelerRole()=="Sorciere") {
    	    					String role = "Sorciere";
    	    					System.out.println("\nLe r�le du bot n�"+joueur.getId()+" est : " + role);
    	    					PartieV2.ajoutPoint(joueur.getId());
    	    					PartieV2.donnerTour(accuse.getId());
    	    				//  AJOUT POINT joueur qui a accus� + sortie du round. 
    	    				}
    	    				else {
    	    					String role = "Villageois";
    	    					System.out.println("\nLe r�le du bot n�"+joueur.getId()+" est : " + role);
    	    					//  AJOUT POINT joueur qui a accus�
    	    					PartieV2.ajoutPoint(joueur.getId());
    	    					PartieV2.donnerTour(joueur.getId());
    	    				}
    	        		}
    	        	}
    		}
    		else {
    			//ici on g�re le cas o� c'est le tour du bot, �tant agressif, il ne fait qu'accuser
    			System.out.println("\n\nLe bot, joueur "+joueur.getId()+" a d�cid� de lancer une accusation");
    			int taille = PartieV2.getJouerJoueur().size();
    			Random random = new Random();
	        	int idac;
	        	boolean joueurvalide=false;
	        	while(joueurvalide==false) {
	        	idac = random.nextInt(taille);
	        	System.out.println("\nId accus� "+idac);
        		if(idac>=0 && idac<(PartieV2.getnbBots()+PartieV2.getnbJoueursPhys())) {
        		if(idac != joueur.getId() && PartieV2.getJouerJoueur().get(idac).r�v�l�==false && PartieV2.getJouerJoueur().get(idac).peutEtreAccus�==true && PartieV2.getJouerJoueur().get(idac).enJeu==true) {
        		joueurvalide=true;
        		if(PartieV2.getJouerJoueur().get(idac).getBot()==true && idac != joueur.getId() && PartieV2.getJouerJoueur().get(idac).r�v�l�==false && PartieV2.getJouerJoueur().get(idac).peutEtreAccus�==true && PartieV2.getJouerJoueur().get(idac).enJeu==true)
        		{
        			PartieV2.getJouerJoueur().get(idac).setEstAccus�(true);
        			PartieV2.getJouerJoueur().get(idac).sonTour=true;
        			Agressive.jouerBot(PartieV2.getJouerJoueur().get(idac),PartieV2.getJouerJoueur().get(joueur.getId())); 
        		}
        		else {
        		//On annule l'effet d'Evil Eye car ce dernier s'est termin�
	        		String evileye = "Evil Eye";
	        		Iterator<Cartes_RumeursV2> it4 = PartieV2.getCartesDefauss�es().iterator();
	        		while(it4.hasNext()) {
	        		if ((it4.next().nom).equalsIgnoreCase(evileye)) {
	        			for (int i=0;i<PartieV2.getnbBots()+PartieV2.getnbJoueursPhys();i++) {
	        				Iterator<Cartes_RumeursV2> it5 = PartieV2.getJouerJoueur().get(i).getCartesJouees().iterator();
	        				while ((it5.hasNext())) {
	        					if((it5.next().nom).equalsIgnoreCase(evileye)){
	        						PartieV2.getJouerJoueur().get(i).peutEtreAccus�=true;
	        				}
	        				}
	        		}
	        		}
	        	}
        		System.out.println("Joueur "+idac+" vous �tes accus� !\nVoici vos cartes :");
        		Iterator<Cartes_RumeursV2> itac = PartieV2.getJouerJoueur().get(idac).main.iterator();
        		while(itac.hasNext()) {
        			String nom = itac.next().nom;
        			PartieV2.getJouerCarte().get(0).afficherDescription(nom);
        	}
        		if (PartieV2.getJouerJoueur().get(idac).main.size()==0) {
        			System.out.println("\nJoueur "+idac+" vous n'avez plus de cartes rumeur, votre identit� va �tre r�v�l�e");
        			System.out.println("\nLe r�le du joueur "+idac+" est : ");
            		PartieV2.getJouerJoueur().get(idac).r�v�l�=true;
            		PartieV2.comptr�v�l�s++;
            		if(PartieV2.getJouerJoueur().get(idac).witch==true) {
            			System.out.println("Sorci�re");
            			PartieV2.getJouerJoueur().get(idac).enJeu=false;
            			PartieV2.ajoutPoint(idac);
            			System.out.println("\nJoueur "+idac+" vous avez maintenant "+PartieV2.getJouerJoueur().get(idac).points+" points");
            			PartieV2.donnerTour(joueur.getId());
            		}
            		else {
            			System.out.println("Villageois");
            			System.out.println("\nLe joueur "+idac+" ne gagne pas de points.");
            			System.out.println("\nJoueur "+idac+" c'est votre tour");
            			PartieV2.enleverTour(joueur.getId());
            			PartieV2.donnerTour(idac);
            		}
    		}
        		else {
                	boolean choixsur = false;
                	while(choixsur==false) {
                	choixsur=false;
            		System.out.println("\nQue souhaitez vous faire ? Utiliser un effet Witch(w) ou r�v�ler votre identit�(i) ?");
            		char r�ponse = clavier.next().charAt(0); // saisie du choix , soit w soit i
                	r�ponse = PartieV2.conversion_majuscule(r�ponse);
                	if (r�ponse=='W') {
                		System.out.println("\nVous avez choisi d'utiliser l'effet Witch d'une de vos cartes");
                		String nomcarte=null; 
                		int idchoisie=-1;
                		String debug=clavier.nextLine();//cet �l�ment n'est pas � proprement utile pour le code mais il y a un bug avec ue sorte de 
        				//"ligne vide" qui est lue au premier clavier.nextLine() de cette boucle while
        				//sans debug on passe une fois dans le for et certains if et des affichages faux � l'�cran
        				//peuvent d�concerter les joueurs
                		boolean app= false;
                		String nom ="0";
                		while (app ==false) {
                		String debugw = clavier.nextLine();
                		System.out.println("Veuillez indiquer le nom de la carte que vous souhaitez utiliser");
                		String nomc = clavier.nextLine();
                		nomcarte=nomc;
                		app = PartieV2.appartient(nomc, idac);
                		String theinquisitionw = "The Inquisition";
                		if (theinquisitionw.equalsIgnoreCase(nomc) && PartieV2.getJouerJoueur().get(idac).main.size()<2) {
            				app=false;
            				System.out.println("\nCette carte n'est pas jouable car son effet Witch n�cessite au minimum 2 cartes");
            			}
                		String hookednose = "Hooked Nose";
                		if (hookednose.equalsIgnoreCase(nomc) && PartieV2.getJouerJoueur().get(joueur.getId()).main.size()==0) {
            				app=false;
            				System.out.println("\nCette carte n'est pas jouable car son effet Witch n�cessite que celui qui vous a accus� ait au moins 1 carte");
            			}
                		String pointedhat = "Pointed Hat";
                		if (pointedhat.equalsIgnoreCase(nom)&& PartieV2.getJouerJoueur().get(joueur.getId()).cartesjouees.size()==0) {
                			app=false;
                			System.out.println("\nCette carte n'est pas jouable car vous n'avez r�v�l� aucune carte rumeur");
                		}
                		nom=nomc;
                		int decision=-2;
                		if(app==false) {
            				System.out.println("Cette carte n'est pas en votre possession");
            				System.out.println("\nVoulez vous quitter le choix des cartes ? (0 : oui/1 : non)");
            				int decis = clavier.nextInt();
            				decision=decis;
                		}
                		if(decision==0) {
            				System.out.println("Vous avez d�cider de revenir au choix entre r�v�ler son identit� et effet witch");
            				app=true;
            			}
                		if(app==true && decision!=0) {
                		//si on utilise la carte evil eye, on stocke en m�moire le nom du joueur qui l'a utilis�
                	
                		/*if (evileye.equalsIgnoreCase(nom)){
                		ancien=idac;
                		}*/
                		//si la carte evil eye a �t� d�fauss�es (on n'entre dans cette boucle que lors du tour qui suit celui o� a �t� jou� la carte), le joueur ayant jou� cette carte peut-�tre accus�
                		//cette boucle intervenant apr�s le choix du joueur, celui qui a jou� cette carte est donc prot�g� pendant 1 tour
                		Iterator<Cartes_RumeursV2> it4_2 = PartieV2.getCartesDefauss�es().iterator();
                		while(it4_2.hasNext()) {
                		if ((it4_2.next().nom).equalsIgnoreCase(evileye)) {
                			for (int i=0;i<PartieV2.getnbBots()+PartieV2.getnbJoueursPhys();i++) {
    	        				Iterator<Cartes_RumeursV2> it5 = PartieV2.getJouerJoueur().get(i).getCartesJouees().iterator();
    	        				while ((it5.hasNext())) {
    	        					if((it5.next().nom).equalsIgnoreCase(evileye)){
    	        						PartieV2.getJouerJoueur().get(i).peutEtreAccus�=true;
    	        				}
    	        				}
                		}
                	}
                		}
                		System.out.println("Ici accus� vaut : "+idac+" et actuel vaut : "+joueur.getId());
                		idchoisie = PartieV2.trouveIndexMain(idac,nom);
                		PartieV2.getJouerJoueur().get(idac).main.get(idchoisie).jouerCarte(nomcarte,false,idac,joueur.getId());
                		choixsur = true;
                		PartieV2.enleverCarte(idchoisie,idac);
                		}
                	}
                	}
                	else {
                		System.out.println("Vous avez choisi de r�v�ler votre identit�");
                		choixsur=true;
                		System.out.println("Le r�le du joueur "+idac+" est : ");
                		PartieV2.getJouerJoueur().get(idac).r�v�l�=true;
                		PartieV2.comptr�v�l�s++;
                		if(PartieV2.getJouerJoueur().get(idac).witch==true) {
                			System.out.println("Sorci�re");
                			PartieV2.getJouerJoueur().get(idac).enJeu=false;
                			PartieV2.ajoutPoint(joueur.getId());
                			System.out.println("\nJoueur "+joueur.getId()+" vous avez maintenant "+PartieV2.getJouerJoueur().get(joueur.getId()).points+" points");
                			PartieV2.donnerTour(joueur.getId());
                		}
                		else {
                			System.out.println("Villageois");
                			System.out.println("\nLe joueur "+joueur.getId()+" ne gagne pas de points.");
                			System.out.println("\nJoueur "+idac+" c'est votre tour");
                			PartieV2.enleverTour(joueur.getId());
                			PartieV2.donnerTour(idac);
                			//actuel=accus�;
                		}
                	}
            		}
                	}
            		}
            		
    	}
    		}
	        	}
	        	}
    		}
    	joueur.setEstAccus�(false);
    }
}

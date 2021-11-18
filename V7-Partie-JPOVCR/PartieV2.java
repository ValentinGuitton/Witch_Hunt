package frr.utt.lo02.projet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import java.util.Random;

public class PartieV2 {
    private final int nbJoueursPhys;

    private final int nbBots;

    private static PartieV2 partieUnique = null;

    private int nbCartesParJoueur;
    
    public List<Joueur_Physique_ou_VirtuelV2> jouerjoueur;
    
    private List<Cartes_RumeursV2> jouercarte;
    
    private List<Cartes_RumeursV2> cartesd�fauss�es;
//constructeur 
    private PartieV2(int nbjp,int nbbot, int nbcartes) {
    	this.nbJoueursPhys=nbjp;
    	this.nbBots=nbbot;
    	this.nbCartesParJoueur=nbcartes;
    	jouerjoueur = new ArrayList<Joueur_Physique_ou_VirtuelV2> ();
    	jouercarte = new ArrayList<Cartes_RumeursV2> ();
    	cartesd�fauss�es = new ArrayList<Cartes_RumeursV2> ();
    	
    }
  //permet de n'avoir qu'une instanciation de Partie
    public static PartieV2 getInstance(int nbjp, int nbbot, int nbcartes) {
    	if (PartieV2.partieUnique == null) {
    		PartieV2.partieUnique = new PartieV2(nbjp, nbbot, nbcartes);
    	}
    	return PartieV2.partieUnique;
    }
	// ajout d'un joueur � la liste des joueurs
	public void ajouterUnJoueur(Joueur_Physique_ou_VirtuelV2 joueur){
		this.jouerjoueur.add(joueur);
	}
//distribution d'une carte � un joueur
    public void distribuerCartes(Joueur_Physique_ou_VirtuelV2 joueur, Cartes_RumeursV2 carte) {
    	joueur.main.add(carte);
    }
//m�lange des cartes
	public void melanger(){
		Collections.shuffle(this.jouercarte);
	}
	public static boolean appartient(String nomcarte, int id) {
		boolean app =false;
		for (int i=0;i<PartieV2.partieUnique.jouerjoueur.get(id).main.size();i++) {
			if ((PartieV2.partieUnique.jouerjoueur.get(id).main.get(i).nom).equalsIgnoreCase(nomcarte)) {
				app = true;
			}
		}
		return app;
	}
	public static int trouveIndexMain(int idj, String nomc) {
		int idchoisie = -1;
		for (int i=0;i<PartieV2.partieUnique.jouerjoueur.get(idj).main.size();i++) {
			if ((PartieV2.partieUnique.jouerjoueur.get(idj).main.get(i).nom).equalsIgnoreCase(nomc)) {
				idchoisie = i;
			}
		}
		return idchoisie;
	}
    public void finirPartie() {
    }
    
    public void ajoutPoint(int id) {
    	PartieV2.partieUnique.jouerjoueur.get(id).points++;
    }

    public void enleverPoint() {
    }

    public void donnerCarte(final String nom, final int id) {
    }

    public static void enleverCarte(final int carte, final int id) {
   		PartieV2.partieUnique.cartesd�fauss�es.add(PartieV2.partieUnique.jouerjoueur.get(id).main.get(carte));
		PartieV2.partieUnique.jouerjoueur.get(id).main.remove(PartieV2.partieUnique.jouerjoueur.get(id).main.get(carte));
		Iterator<Cartes_RumeursV2> itest = PartieV2.partieUnique.cartesd�fauss�es.iterator();
		while (itest.hasNext()) {
			System.out.println(itest.next());
		}
    }

    public static void donnerTour(final int id) {
    	PartieV2.partieUnique.jouerjoueur.get(id).sonTour = true;
    }
    public void enleverTour (int id) {
    	jouerjoueur.get(id).sonTour = false;
    }

    public void recupererCarte(final String nom) {
    }
    public static char conversion_majuscule(char choix){  //Fonction pour convertir en majuscule
        choix = Character.toUpperCase(choix); // convertion de choix en majuscule
        return(choix); // retourne choix
    }
    public static void main(String[] args) {
    //On initialise des variables pour le nombre de joueurs physiques et virtuels, on utilise un scanner pour r�cup�rer les nombres entr�s par l'utilisateur
    //tout en respectant les contraintes max 6 joueurs, au moins un joueur physique et en calculant le nombre de cartes par joueur
    	int nbbot=0;
    	int nbjp=0;
    	int nbcartes=0;
    	Scanner clavier = new Scanner(System.in);
//    	while (nbjp<1 || nbjp>6) {
//    		System.out.print("Saisir le nombre de joueurs physiques (max 6) : ");
//    		nbjp = clavier.nextInt();
//    	};
    //choix d'avoir des bots ou non
    	char bot = 'A';
    	while(bot!='O' && bot!='N') {
    	System.out.println("Voulez vous des bots ? (Oui ou Non)");
    	bot = clavier.next().charAt(0); // saisi du choix , soit o soit n
    	bot = conversion_majuscule(bot); // utilisation de la fonction pour convertir le choix en majuscule
    	}
    	
    	
    	
        	if (bot == 'O') {
        		while ((nbbot+nbjp>6) || nbbot<1 || nbbot>5 || nbjp<1) {
        			while(nbbot<1 || nbbot>5) {
        				System.out.println("Bots activ�s \nSaisir le nombre de joueurs virtuels (max 6 joueurs en tout | max 5 bots) : ");
        				nbbot = clavier.nextInt();
        			}
        			System.out.println("Saisir le nombre de joueurs physiques (max 6 (bots+joueurs) : ");
        			nbjp = clavier.nextInt();
        		}
        	}
            else {
            	while (nbjp>=7 || nbjp<3) {
            	System.out.println("Bots d�sactiv�s \nSaisir le nombre de joueurs physiques (max 6) : ");
        		nbjp = clavier.nextInt();
            	}
            }
           
    	
    	if (nbbot+nbjp==3) {
    		nbcartes=4;
    	}
    		else if (nbbot+nbjp==4) {
    			nbcartes=3;
    		}
    		else if (nbbot+nbjp==5) {
    			nbcartes=2;
    			//ajouter la mise � l'�cart de 2 cartes
    		}
    		else if (nbbot+nbjp==6) {
    			nbcartes=2;
    		}
    	//Cr�ation de la partie
        System.out.println("Cr�ation d'une partie avec "+ nbjp +" joueur(s) physique(s) et "+ nbbot +" joueur(s) virtuel(s)");
        System.out.println(nbcartes+" cartes vont �tre distribu�es � chaque joueur");
    	PartieV2.getInstance(nbjp, nbbot, nbcartes);
    	//Cr�ation de la liste des joueurs
    	for (int i=0; i<nbbot+nbjp ; i++) {
    		PartieV2.partieUnique.ajouterUnJoueur(new Joueur_Physique_ou_VirtuelV2(false,false,false,true,i,0));
    	};
    	//Affichage de la liste des joueurs
    	Iterator<Joueur_Physique_ou_VirtuelV2>it = PartieV2.partieUnique.jouerjoueur.iterator();
    	while (it.hasNext()){
    		System.out.println(it.next());
    	}
    	//Cr�ation de la liste de cartes
    	PartieV2.partieUnique.jouercarte.add(new Cartes_RumeursV2("Angry Mob",false,false,0));
    	PartieV2.partieUnique.jouercarte.add(new Cartes_RumeursV2("The Inquisition",false,false,0));
    	PartieV2.partieUnique.jouercarte.add(new Cartes_RumeursV2("Pointed Hat",false,false,0));
    	PartieV2.partieUnique.jouercarte.add(new Cartes_RumeursV2("Hooked Nose",false,false,0));
    	PartieV2.partieUnique.jouercarte.add(new Cartes_RumeursV2("Broomstick",false,false,0));
    	PartieV2.partieUnique.jouercarte.add(new Cartes_RumeursV2("Wart",false,false,0));
    	PartieV2.partieUnique.jouercarte.add(new Cartes_RumeursV2("Ducking Stool",false,false,0));
    	PartieV2.partieUnique.jouercarte.add(new Cartes_RumeursV2("Cauldron",false,false,0));
    	PartieV2.partieUnique.jouercarte.add(new Cartes_RumeursV2("Evil Eye",false,false,0));
    	PartieV2.partieUnique.jouercarte.add(new Cartes_RumeursV2("Toad",false,false,0));
    	PartieV2.partieUnique.jouercarte.add(new Cartes_RumeursV2("Black Cat",false,false,0));
    	PartieV2.partieUnique.jouercarte.add(new Cartes_RumeursV2("Pet Newt",false,false,0));
    	
    	PartieV2.partieUnique.melanger();
    	int compt=0;
    	for ( int j=0; j<nbbot+nbjp;j++) {
    		System.out.println("Main du joueur "+j);
    		for (int i=compt; i<nbcartes+compt; i++) {
    			PartieV2.partieUnique.jouerjoueur.get(j).main.add(PartieV2.partieUnique.jouercarte.get(i));
    		}
    		compt=compt+nbcartes;
    		Iterator<Cartes_RumeursV2> it1 = PartieV2.partieUnique.jouerjoueur.get(j).main.iterator();
    		while(it1.hasNext()) {
    			System.out.println(it1.next());
    		}
    	}
    	if (nbbot+nbjp == 5) {
    		PartieV2.partieUnique.cartesd�fauss�es.add(PartieV2.partieUnique.jouercarte.get(10));
    		PartieV2.partieUnique.cartesd�fauss�es.add(PartieV2.partieUnique.jouercarte.get(11));
    	}
    	//D�but de la partie
    	// Choix des roles 
    	for (int i=0; i<nbbot+nbjp ; i++) {
    		int r=2;
    		boolean j = true;
    		while(r<0 || r>1) {
    		System.out.println("Joueur " + i + " : Choisissez votre r�le (0 : villageois | 1 : sorci�re)");
    		r = clavier.nextInt();
    		}
    		if(r==1) j = true;
    		else j = false;
    		PartieV2.partieUnique.jouerjoueur.get(i).choisirRole(j);
    		if(j==true) {
    			System.out.println(" !!ROLE!! : Joueur num�ro " + i + " vous �tes une sorci�re ");
    			PartieV2.partieUnique.jouerjoueur.get(i).choisirRole(true);
    		}
    		else {
    			System.out.println(" !!ROLE!! : Joueur num�ro " + i + " vous �tes un villageois ");
    			PartieV2.partieUnique.jouerjoueur.get(i).choisirRole(false);
    		}
    	};
    	
    	int idj = 8;
    	while(idj>(nbbot+nbjp) || idj<0 ) {
    	System.out.println("Qui est le joueur le plus jeune ?");
    	idj = clavier.nextInt();
    	if(idj>(nbbot+nbjp)||idj<0) {System.out.println("Il n'existe pas de Joueur " + idj);}
    	}
    	PartieV2.donnerTour(idj);
    	System.out.println("Joueur "+idj+" voici vos cartes :");
		Iterator<Cartes_RumeursV2> it2 = PartieV2.partieUnique.jouerjoueur.get(idj).main.iterator();
		while(it2.hasNext()) {
			//it2.next().afficherDescription(it2.next().nom);
			String nom = it2.next().nom;
			PartieV2.partieUnique.jouercarte.get(0).afficherDescription(nom);
		}
		int comptr�v�l�s=0;
		int actuel = idj;
		while(comptr�v�l�s<(nbbot+nbjp-1)) {
		PartieV2.partieUnique.enleverTour(actuel);
		System.out.println("\nJoueur "+actuel+" voulez-vous lancer une accusation ou utiliser l'effet Hunt d'une de vos cartes ?(a ou h)");
    	char choix = clavier.next().charAt(0); // saisie du choix , soit a soit h
    	choix = conversion_majuscule(choix);
    	if (choix == 'A') {
    		System.out.println("\n\nVous avez d�cid� de lancer une accusation");
    		System.out.println("\nQui voulez-vous accusez ?");
    		int accus� = clavier.nextInt();
    		if(accus�>=0 && accus�<(nbbot+nbjp) && accus� != actuel && PartieV2.partieUnique.jouerjoueur.get(accus�).r�v�l�==false) {
    		System.out.println("Joueur "+accus�+" vous �tes accus� !\n Voici vos cartes :");
    		Iterator<Cartes_RumeursV2> itac = PartieV2.partieUnique.jouerjoueur.get(accus�).main.iterator();
    		while(itac.hasNext()) {
    			String nom = itac.next().nom;
    			PartieV2.partieUnique.jouercarte.get(0).afficherDescription(nom);
    	}
    		System.out.println("\nQue souhaitez vous faire ? Utiliser un effet Witch(w) ou r�v�ler votre identit�(i) ?");
    		char r�ponse = clavier.next().charAt(0); // saisie du choix , soit w soit i
        	r�ponse = conversion_majuscule(r�ponse);
        	if (r�ponse=='W') {
        		System.out.println("\nVous avez choisi d'utiliser l'effet Witch d'une de vos cartes");
        		/*boolean appartient = false;*/
        		String nomcarte=null; 
        		int idchoisie=-1;
        		String debug=clavier.nextLine();//cet �l�ment n'est pas � proprement utile pour le code mais il y a un bug avec ue sorte de 
				//"ligne vide" qui est lue au premier clavier.nextLine() de cette boucle while
				//sans debug on passe une fois dans le for et certains if et des affichages faux � l'�cran
				//peuvent d�concerter les joueurs
        		/*while(appartient==false) {
        			System.out.println("\nVeuillez entrer le nom de la carte que vous voulez utiliser");
        			String nomc = clavier.nextLine();
        			nomcarte=nomc;
        			for (int i=0;i<PartieV2.partieUnique.jouerjoueur.get(accus�).main.size();i++) {
        				if ((PartieV2.partieUnique.jouerjoueur.get(accus�).main.get(i).nom).equalsIgnoreCase(nomc)) {
        					appartient=true;
        					idchoisie=i;
        				}
        			if (nomc == "The Inquisition" && PartieV2.partieUnique.jouerjoueur.get(accus�).main.size()<2) {
        				appartient=false;
        				System.out.println("Cette carte n'est pas jouable car son effet Witch n�cessite au minimum 2 cartes");
        			}
        			}
        			if (appartient==false) {
        			System.out.println("Cette carte n'est pas en votre possession, veuillez en choisir une autre");
        			}
        		}*/
        		boolean app= false;
        		String nom ="0";
        		while (app ==false) {
        		System.out.println("Veuillez indiquer le nom de la carte que vous souhaitez utiliser");
        		String nomc = clavier.nextLine();
        		nomcarte=nomc;
        		app = PartieV2.appartient(nomc, accus�);
        		if (app==false) {
    			System.out.println("Cette carte n'est pas en votre possession");
        		}
        		if (nomc == "The Inquisition" && PartieV2.partieUnique.jouerjoueur.get(accus�).main.size()<2) {
    				app=false;
    				System.out.println("Cette carte n'est pas jouable car son effet Witch n�cessite au minimum 2 cartes");
    			}
        		nom=nomc;
        		}
        		idchoisie = PartieV2.trouveIndexMain(accus�,nom);
        		PartieV2.partieUnique.jouerjoueur.get(accus�).main.get(idchoisie).jouerCarte(nomcarte,false,accus�);
        		PartieV2.enleverCarte(idchoisie,accus�);       		
        	}
        	else {
        		System.out.println("Vous avez choisi de r�v�ler votre identit�");
        		System.out.println("Le r�le du joueur "+accus�+" est : ");
        		PartieV2.partieUnique.jouerjoueur.get(accus�).r�v�l�=true;
        		comptr�v�l�s++;
        		if(PartieV2.partieUnique.jouerjoueur.get(accus�).witch==true) {
        			System.out.println("Sorci�re");
        			PartieV2.partieUnique.jouerjoueur.get(accus�).enJeu=false;
        			PartieV2.partieUnique.ajoutPoint(actuel);
        			System.out.println("\nJoueur "+actuel+" vous avez maintenant "+PartieV2.partieUnique.jouerjoueur.get(actuel).points+" points");
        		}
        		else {
        			System.out.println("Villageois");
        			System.out.println("\nLe joueur "+actuel+" ne gagne pas de points.");
        			System.out.println("\nJoueur "+accus�+" c'est votre tour");
        			actuel=accus�;
        		}
        	}
    		}
    		else {
    			System.out.println("\nVeuillez choisir un autre joueur");
    		}
    	}
    	else {
    		System.out.println("\n\nVous avez d�cid� de jouer une carte rumeur");	
    	}
    	for (int i=0;i<(nbbot+nbjp);i++) {
    		if((PartieV2.partieUnique.jouerjoueur.get(i).sonTour)==true) {
    			actuel=i;
    		}
    	}
		}
		System.out.println("Round termin�");
     	//partie pour la distribution des cartes, � modifier en utilisant le td Collections
     	/*
     	//Utilisation de random pour le tirage al�atoire
  		Random rand=new Random();
  		//On initialise un tableau qui contiendra les cartes distribu�es (ne pas avoir de carte distribu�e plusieurs fois)
  		int cartestir�es[]= {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
  		int compt=0;
  		//Boucle j pour changer d'utilisateur et boucle i pour changer de carte
  		for (int j=0; j<nbbot+nbjp; j++) {
     	for (int i=0; i<nbcartes ; i++) {
     	//tirage al�atoire
     		int a = rand.nextInt(11);
     		while ( a==cartestir�es[0] || a==cartestir�es[1] || a==cartestir�es[2] || a==cartestir�es[3] || a==cartestir�es[4] || a==cartestir�es[5] || a==cartestir�es[6] || a==cartestir�es[7] || a==cartestir�es[8] || a==cartestir�es[9] || a==cartestir�es[10]) {
     			a=a+1;
     			if (a==12) {
     				a=0;
     			}
     		}
     		partieUnique.distribuerCartes(jouerjoueur.get(j),jouercarte.get(a));
     		cartestir�es[compt]=a;
     		compt++;
     	}
     	//affichage des cartes poss�d�es
  	        System.out.println("Le joueur "+jouerjoueur.get(j).getId()+" poss�de les cartes : ");
         	Iterator<Cartes_Rumeurs>main = Joueur_Physique_ou_Virtuel.jouer.iterator();
            while (main.hasNext()) {
                System.out.println(main.next());	
         	}
     	}*/
    	clavier.close();
    }

}
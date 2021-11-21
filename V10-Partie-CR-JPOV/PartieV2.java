package frr.utt.lo02.projet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class PartieV2 {
    private static int nbJoueursPhys=-2;

    private static int nbBots=-2;

    private static PartieV2 partieUnique = null;

    private int nbCartesParJoueur;
    
    public List<Joueur_Physique_ou_VirtuelV2> jouerjoueur;
    
    private List<Cartes_RumeursV2> jouercarte;
    
    private List<Cartes_RumeursV2> cartesdéfaussées;
//constructeur 
    private PartieV2(int nbjp,int nbbot, int nbcartes) {
    	this.nbJoueursPhys=nbjp;
    	this.nbBots=nbbot;
    	this.nbCartesParJoueur=nbcartes;
    	jouerjoueur = new ArrayList<Joueur_Physique_ou_VirtuelV2> ();
    	jouercarte = new ArrayList<Cartes_RumeursV2> ();
    	cartesdéfaussées = new ArrayList<Cartes_RumeursV2> ();
    	
    }
    public static  void choisirCarteDef(int id){
    	int compt2=0;
		System.out.println("\n Voici les cartes rumeurs que vous avez utilisé :");
		Iterator<Cartes_RumeursV2> itc = PartieV2.partieUnique.jouerjoueur.get(id).cartesjouees.iterator();
		while(itc.hasNext()) {
			System.out.println(compt2+" : ");
			String cartenom = itc.next().nom;
			PartieV2.partieUnique.jouerjoueur.get(id).cartesjouees.get(compt2).afficherDescription(cartenom);
			compt2++;
		}
		Scanner clavier = new Scanner(System.in);
		int num = -1;
		while(num<0 || num>(compt2-1)) {
		System.out.println("\n Quel est le numéro de la carte que vous souhaitez récupérer ?");
		num = clavier.nextInt();
			if(num<0 || num>(compt2-1)) {
				System.out.println("\n Numéro invalide");
			}
		}	
		PartieV2.partieUnique.jouerjoueur.get(id).main.add(PartieV2.partieUnique.jouerjoueur.get(id).cartesjouees.get(num));
		for (int i=0;i<PartieV2.partieUnique.cartesdéfaussées.size();i++) {
			if ((PartieV2.partieUnique.cartesdéfaussées.get(i).nom).equalsIgnoreCase(PartieV2.partieUnique.jouerjoueur.get(id).cartesjouees.get(num).nom)) {
				PartieV2.partieUnique.cartesdéfaussées.remove(PartieV2.partieUnique.cartesdéfaussées.get(i));
			}
    }
		PartieV2.partieUnique.jouerjoueur.get(id).cartesjouees.remove(PartieV2.partieUnique.jouerjoueur.get(id).cartesjouees.get(num));
    }
    
  //permet de n'avoir qu'une instanciation de Partie
    public static PartieV2 getInstance(int nbjp, int nbbot, int nbcartes) {
    	if (PartieV2.partieUnique == null) {
    		PartieV2.partieUnique = new PartieV2(nbjp, nbbot, nbcartes);
    	}
    	return PartieV2.partieUnique;
    }
    public void setnbJoueursPhys(int nbJoueurPhys) {
    	this.nbJoueursPhys=nbJoueurPhys;
    }
    public static int getnbJoueursPhys() {
    	return nbJoueursPhys;
    }
    
    public void setnbBots(int nbBots) {
    	this.nbBots=nbBots;
    }
    public static int getnbBots() {
    	return nbBots;
    }
    
	// ajout d'un joueur à la liste des joueurs
	public void ajouterUnJoueur(Joueur_Physique_ou_VirtuelV2 joueur){
		this.jouerjoueur.add(joueur);
	}
//distribution d'une carte à un joueur
    public void distribuerCartes(Joueur_Physique_ou_VirtuelV2 joueur, Cartes_RumeursV2 carte) {
    	joueur.main.add(carte);
    }
//mélange des cartes
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

    public void enleverPoint(int id) {
    	PartieV2.partieUnique.jouerjoueur.get(id).points--;
    }

    public void donnerCarte(final String nom, final int id) {
    }

    public static void enleverCarte(final int carte, final int id) {
    	System.out.println("Cartes enlevées : ");
   		PartieV2.partieUnique.cartesdéfaussées.add(PartieV2.partieUnique.jouerjoueur.get(id).main.get(carte));
   		PartieV2.partieUnique.jouerjoueur.get(id).cartesjouees.add(PartieV2.partieUnique.jouerjoueur.get(id).main.get(carte));
		Iterator<Cartes_RumeursV2> itest = PartieV2.partieUnique.cartesdéfaussées.iterator();
		while (itest.hasNext()) {
			System.out.println(itest.next());
		}
		PartieV2.partieUnique.jouerjoueur.get(id).main.remove(PartieV2.partieUnique.jouerjoueur.get(id).main.get(carte));
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
    //On initialise des variables pour le nombre de joueurs physiques et virtuels, on utilise un scanner pour récupérer les nombres entrés par l'utilisateur
    //tout en respectant les contraintes max 6 joueurs, au moins un joueur physique et en calculant le nombre de cartes par joueur
//    	int nbbot=0;
//    	int nbjp=0;
    	int nbcartes=0;
    	Scanner clavier = new Scanner(System.in);
    	PartieV2.partieUnique = new PartieV2(0, 0, 0);
    //choix d'avoir des bots ou non
    	char bot = 'A';
    	while(bot!='O' && bot!='N') {
    	System.out.println("Voulez vous des bots ? (Oui ou Non)");
    	bot = clavier.next().charAt(0); // saisi du choix , soit o soit n
    	bot = conversion_majuscule(bot); // utilisation de la fonction pour convertir le choix en majuscule
    	}
    	
    	
    	
        	if (bot == 'O') {
        		while ((PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys()>6) || PartieV2.partieUnique.getnbBots()<1 || PartieV2.partieUnique.getnbBots()>5 || PartieV2.partieUnique.getnbJoueursPhys()<1) {
        			while(PartieV2.partieUnique.getnbBots()<1 || PartieV2.partieUnique.getnbBots()>5) {
        				System.out.println("Bots activés \nSaisir le nombre de joueurs virtuels (max 6 joueurs en tout | max 5 bots) : ");
        				PartieV2.partieUnique.setnbBots(clavier.nextInt());
        			}
        			System.out.println("Saisir le nombre de joueurs physiques (max 6 (bots+joueurs) : ");
        			PartieV2.partieUnique.setnbJoueursPhys(clavier.nextInt());
        		}
        	}
            else {
            	while (PartieV2.partieUnique.getnbJoueursPhys()>=7 || PartieV2.partieUnique.getnbJoueursPhys()<3) {
            	System.out.println("Bots désactivés \nSaisir le nombre de joueurs physiques (max 6) : ");
            	PartieV2.partieUnique.setnbJoueursPhys(clavier.nextInt());
            	}
            }
           
    	
    	if (PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys()==3) {
    		nbcartes=4;
    	}
    		else if (PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys()==4) {
    			nbcartes=3;
    		}
    		else if (PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys()==5) {
    			nbcartes=2;
    			//ajouter la mise à l'écart de 2 cartes
    		}
    		else if (PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbBots()==6) {
    			nbcartes=2;
    		}
    	//Création de la partie
        System.out.println("Création d'une partie avec "+ PartieV2.partieUnique.getnbBots() +" joueur(s) physique(s) et "+ PartieV2.partieUnique.getnbBots() +" joueur(s) virtuel(s)");
        System.out.println(nbcartes+" cartes vont être distribuées à chaque joueur");
    	PartieV2.getInstance(PartieV2.partieUnique.getnbBots(), PartieV2.partieUnique.getnbBots(), nbcartes);
    	//Création de la liste des joueurs
    	for (int i=0; i<PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys() ; i++) {
    		PartieV2.partieUnique.ajouterUnJoueur(new Joueur_Physique_ou_VirtuelV2(false,false,false,true,i,0));
    	};
    	//Affichage de la liste des joueurs
    	Iterator<Joueur_Physique_ou_VirtuelV2>it = PartieV2.partieUnique.jouerjoueur.iterator();
    	while (it.hasNext()){
    		System.out.println(it.next());
    	}
    	//Création de la liste de cartes
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
    	for ( int j=0; j<PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys();j++) {
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
    	if (PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys() == 5) {
    		PartieV2.partieUnique.cartesdéfaussées.add(PartieV2.partieUnique.jouercarte.get(10));
    		PartieV2.partieUnique.cartesdéfaussées.add(PartieV2.partieUnique.jouercarte.get(11));
    	}
    	//Début de la partie
    	// Choix des roles 
    	for (int i=0; i<PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys() ; i++) {
    		int r=2;
    		boolean j = true;
    		while(r<0 || r>1) {
    		System.out.println("Joueur " + i + " : Choisissez votre rôle (0 : villageois | 1 : sorcière)");
    		r = clavier.nextInt();
    		}
    		if(r==1) j = true;
    		else j = false;
    		PartieV2.partieUnique.jouerjoueur.get(i).choisirRole(j);
    		if(j==true) {
    			System.out.println(" !!ROLE!! : Joueur numéro " + i + " vous êtes une sorcière ");
    			PartieV2.partieUnique.jouerjoueur.get(i).choisirRole(true);
    		}
    		else {
    			System.out.println(" !!ROLE!! : Joueur numéro " + i + " vous êtes un villageois ");
    			PartieV2.partieUnique.jouerjoueur.get(i).choisirRole(false);
    		}
    	};
    	
    	int idj = 8;
    	while(idj>(PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys()) || idj<0 ) {
    	System.out.println("Qui est le joueur le plus jeune ?");
    	idj = clavier.nextInt();
    	if(idj>(PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys())||idj<0) {System.out.println("Il n'existe pas de Joueur " + idj);}
    	}
    	PartieV2.donnerTour(idj);
    	System.out.println("Joueur "+idj+" voici vos cartes :");
		Iterator<Cartes_RumeursV2> it2 = PartieV2.partieUnique.jouerjoueur.get(idj).main.iterator();
		while(it2.hasNext()) {
			String nom = it2.next().nom;
			PartieV2.partieUnique.jouercarte.get(0).afficherDescription(nom);
		}
		int comptrévélés=0;
		int actuel = idj;
		while(comptrévélés<(PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys()-1)) {
		PartieV2.partieUnique.enleverTour(actuel);
		char choix = 'x';
		while(choix != 'A' && choix != 'H') {
		System.out.println("\nJoueur "+actuel+" voulez-vous lancer une accusation ou utiliser l'effet Hunt d'une de vos cartes ?(a ou h)");
    	choix = clavier.next().charAt(0); // saisie du choix , soit a soit h
    	choix = conversion_majuscule(choix);
		}
    	if (choix == 'A') {
    		System.out.println("\n\nVous avez décidé de lancer une accusation");
    		System.out.println("\nQui voulez-vous accusez ?");
    		int accusé = clavier.nextInt();
    		if(accusé>=0 && accusé<(PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys()) && accusé != actuel && PartieV2.partieUnique.jouerjoueur.get(accusé).révélé==false) {
    		System.out.println("Joueur "+accusé+" vous êtes accusé !\n Voici vos cartes :");
    		Iterator<Cartes_RumeursV2> itac = PartieV2.partieUnique.jouerjoueur.get(accusé).main.iterator();
    		while(itac.hasNext()) {
    			String nom = itac.next().nom;
    			PartieV2.partieUnique.jouercarte.get(0).afficherDescription(nom);
    	}
    		if (PartieV2.partieUnique.jouerjoueur.get(accusé).main.size()==0) {
    			System.out.println("\nJoueur "+accusé+" vous n'avez plus de cartes rumeur, votre identité va être révélée");
    			System.out.println("\nLe rôle du joueur "+accusé+" est : ");
        		PartieV2.partieUnique.jouerjoueur.get(accusé).révélé=true;
        		comptrévélés++;
        		if(PartieV2.partieUnique.jouerjoueur.get(accusé).witch==true) {
        			System.out.println("Sorcière");
        			PartieV2.partieUnique.jouerjoueur.get(accusé).enJeu=false;
        			PartieV2.partieUnique.ajoutPoint(actuel);
        			System.out.println("\nJoueur "+actuel+" vous avez maintenant "+PartieV2.partieUnique.jouerjoueur.get(actuel).points+" points");
        		}
        		else {
        			System.out.println("Villageois");
        			System.out.println("\nLe joueur "+actuel+" ne gagne pas de points.");
        			System.out.println("\nJoueur "+accusé+" c'est votre tour");
        			actuel=accusé;
        		}
    		}
    		else {
    		System.out.println("\nQue souhaitez vous faire ? Utiliser un effet Witch(w) ou révéler votre identité(i) ?");
    		char réponse = clavier.next().charAt(0); // saisie du choix , soit w soit i
        	réponse = conversion_majuscule(réponse);
        	if (réponse=='W') {
        		System.out.println("\nVous avez choisi d'utiliser l'effet Witch d'une de vos cartes");
        		String nomcarte=null; 
        		int idchoisie=-1;
        		String debug=clavier.nextLine();//cet élément n'est pas à proprement utile pour le code mais il y a un bug avec ue sorte de 
				//"ligne vide" qui est lue au premier clavier.nextLine() de cette boucle while
				//sans debug on passe une fois dans le for et certains if et des affichages faux à l'écran
				//peuvent déconcerter les joueurs
        		boolean app= false;
        		String nom ="0";
        		while (app ==false) {
        		System.out.println("Veuillez indiquer le nom de la carte que vous souhaitez utiliser");
        		String nomc = clavier.nextLine();
        		nomcarte=nomc;
        		app = PartieV2.appartient(nomc, accusé);
        		if (app==false) {
    			System.out.println("Cette carte n'est pas en votre possession");
        		}
        		if (nomc == "The Inquisition" && PartieV2.partieUnique.jouerjoueur.get(accusé).main.size()<2) {
    				app=false;
    				System.out.println("Cette carte n'est pas jouable car son effet Witch nécessite au minimum 2 cartes");
    			}
        		nom=nomc;
        		}
        		idchoisie = PartieV2.trouveIndexMain(accusé,nom);
        		PartieV2.partieUnique.jouerjoueur.get(accusé).main.get(idchoisie).jouerCarte(nomcarte,false,accusé);
        		PartieV2.enleverCarte(idchoisie,accusé);   		
        	}
        	else {
        		System.out.println("Vous avez choisi de révéler votre identité");
        		System.out.println("Le rôle du joueur "+accusé+" est : ");
        		PartieV2.partieUnique.jouerjoueur.get(accusé).révélé=true;
        		comptrévélés++;
        		if(PartieV2.partieUnique.jouerjoueur.get(accusé).witch==true) {
        			System.out.println("Sorcière");
        			PartieV2.partieUnique.jouerjoueur.get(accusé).enJeu=false;
        			PartieV2.partieUnique.ajoutPoint(actuel);
        			System.out.println("\nJoueur "+actuel+" vous avez maintenant "+PartieV2.partieUnique.jouerjoueur.get(actuel).points+" points");
        		}
        		else {
        			System.out.println("Villageois");
        			System.out.println("\nLe joueur "+actuel+" ne gagne pas de points.");
        			System.out.println("\nJoueur "+accusé+" c'est votre tour");
        			actuel=accusé;
        		}
        	}
        	}
    		}
    		else {
    			System.out.println("\nVeuillez choisir un autre joueur");
    		}
    	}
    	else {
    		System.out.println("\n\nVous avez décidé de jouer une carte rumeur");
    		String nomcarte=null;
    		String nomc = null;
    		boolean app= false;
    		String nom ="0";
    		short bufferClavier=0;
    		while (app ==false) {
    		System.out.println("Veuillez indiquer le nom de la carte que vous souhaitez utiliser");
    		
    		if(bufferClavier==0) {  // Vide la premiere ligne 1 fois seulement pour ne pas sauter la ligne nomc = clavier.nextLine();
    		clavier.nextLine();
    		bufferClavier=1;
    		}
    		nomc = clavier.nextLine();
    		nomcarte=nomc;
    		
			app = PartieV2.appartient(nomc , idj);
    		if (app==false) {
    			System.out.println("Cette carte n'est pas en votre possession");
        		}
    		else {
    			int idchoisie = PartieV2.trouveIndexMain(idj,nomc);
    			PartieV2.partieUnique.jouerjoueur.get(idj).main.get(idchoisie).jouerCarte(nomcarte,true,idj);
    		}
    		}
    		
    	}
    	for (int i=0;i<(PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys());i++) {
    		if((PartieV2.partieUnique.jouerjoueur.get(i).sonTour)==true) {
    			actuel=i;
    		}
    	}
		}
		System.out.println("Round terminé");
     	//partie pour la distribution des cartes, à modifier en utilisant le td Collections
    	clavier.close();
    }

}
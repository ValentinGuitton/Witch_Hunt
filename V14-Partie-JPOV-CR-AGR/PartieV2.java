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
    
    protected static Cartes_RumeursV2 cartesup1;
    
    protected static Cartes_RumeursV2 cartesup2;
    
    static List<Joueur_Physique_ou_VirtuelV2> jouerjoueur;
    
    private List<Cartes_RumeursV2> jouercarte;
    
    private List<Cartes_RumeursV2> cartesd�fauss�es;
    
    private static int comptr�v�l�s;
//constructeur 
    private PartieV2(int nbjp,int nbbot, int nbcartes) {
    	this.nbJoueursPhys=nbjp;
    	this.nbBots=nbbot;
    	this.nbCartesParJoueur=nbcartes;
    	jouerjoueur = new ArrayList<Joueur_Physique_ou_VirtuelV2> ();
    	jouercarte = new ArrayList<Cartes_RumeursV2> ();
    	cartesd�fauss�es = new ArrayList<Cartes_RumeursV2> ();
    	
    }
    public static  void choisirCarteDef(int id){
    	int compt2=0;
		System.out.println("\n Voici les cartes rumeurs que vous avez utilis� :");
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
		System.out.println("\n Quel est le num�ro de la carte que vous souhaitez r�cup�rer ?");
		num = clavier.nextInt();
			if(num<0 || num>(compt2-1)) {
				System.out.println("\n Num�ro invalide");
			}
		}	
		PartieV2.partieUnique.jouerjoueur.get(id).main.add(PartieV2.partieUnique.jouerjoueur.get(id).cartesjouees.get(num));
		for (int i=0;i<PartieV2.partieUnique.cartesd�fauss�es.size();i++) {
			if ((PartieV2.partieUnique.cartesd�fauss�es.get(i).nom).equalsIgnoreCase(PartieV2.partieUnique.jouerjoueur.get(id).cartesjouees.get(num).nom)) {
				PartieV2.partieUnique.cartesd�fauss�es.remove(PartieV2.partieUnique.cartesd�fauss�es.get(i));
			}
    }
		PartieV2.partieUnique.jouerjoueur.get(id).cartesjouees.remove(PartieV2.partieUnique.jouerjoueur.get(id).cartesjouees.get(num));
    }
    
    public static void afficherMain(int id) {
    	int i=0;
    	Iterator<Cartes_RumeursV2> it1 = PartieV2.partieUnique.jouerjoueur.get(id).main.iterator();
		while(it1.hasNext()) {
			String nom = it1.next().nom;
			PartieV2.partieUnique.jouerjoueur.get(id).main.get(i).afficherDescription(nom);
			i++;
		}
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
    public static List<Joueur_Physique_ou_VirtuelV2> getJouerJoueur(){
    	return PartieV2.partieUnique.jouerjoueur;
    }
    public static List<Cartes_RumeursV2> getCartesDefauss�es(){
    	return PartieV2.partieUnique.cartesd�fauss�es;
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
	public static boolean enJeu(int id) {
		if(PartieV2.partieUnique.jouerjoueur.get(id).enJeu==true) {
			return true;
		}
		else {
			return false;
		}
	}
	public static int donnerTailleMain(int id) {
		int size=-1;
		size=PartieV2.partieUnique.jouerjoueur.get(id).main.size();
		return size;
	}
	public static void protegerJoueur(int id) {
		PartieV2.partieUnique.jouerjoueur.get(id).peutEtreAccus�=false;
		System.out.println("\nLe joueur "+id+" ne pourra pas etre accus� au prochain tour");
	}
    public void finirPartie() {
    }
    
    public static void ajoutPoint(int id) {
    	PartieV2.partieUnique.jouerjoueur.get(id).points++;
    	System.out.println("Le joueur "+id+" gagne 1 point");
    }

    public static void enleverPoint(int id) {
    	PartieV2.partieUnique.jouerjoueur.get(id).points--;
    	System.out.println("Le joueur "+id+" perd 1 point");
    }

    public static void donnerCarte(final int idc, final int idjdonne, final int idjre�oit) {
    	PartieV2.partieUnique.jouerjoueur.get(idjre�oit).main.add(PartieV2.partieUnique.jouerjoueur.get(idjdonne).main.get(idc));
    	PartieV2.partieUnique.jouerjoueur.get(idjdonne).main.remove(PartieV2.partieUnique.jouerjoueur.get(idjdonne).main.get(idc));
    }

    public static void enleverCarte(final int carte, final int id) {
    	System.out.println("Cartes enlev�es : ");
   		PartieV2.partieUnique.cartesd�fauss�es.add(PartieV2.partieUnique.jouerjoueur.get(id).main.get(carte));
   		PartieV2.partieUnique.jouerjoueur.get(id).cartesjouees.add(PartieV2.partieUnique.jouerjoueur.get(id).main.get(carte));
		Iterator<Cartes_RumeursV2> itest = PartieV2.partieUnique.cartesd�fauss�es.iterator();
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
    //v�rifier si il reste plus de 2 joueurs et que l'on peut donc prot�ger un joueur d'une accusation
    public static boolean choixJoueur(){
    	if (PartieV2.comptr�v�l�s<PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys()-2) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    public static boolean revelerRole(int id) {
    	System.out.println("\nLe r�le du joueur "+id+" est :");
    	PartieV2.getJouerJoueur().get(id).r�v�l�=true;
    	if (PartieV2.partieUnique.jouerjoueur.get(id).witch==true) {
    	System.out.println("Witch");
    	PartieV2.getJouerJoueur().get(id).enJeu=false;
    	
    	return true;
    	}
    	else {
    		System.out.println("Villageois");
    		return false;
    	}
    }
    public static char conversion_majuscule(char choix){  //Fonction pour convertir en majuscule
        choix = Character.toUpperCase(choix); // convertion de choix en majuscule
        return(choix); // retourne choix
    }
    public static void main(String[] args) {
    //On initialise des variables pour le nombre de joueurs physiques et virtuels, on utilise un scanner pour r�cup�rer les nombres entr�s par l'utilisateur
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
        				System.out.println("Bots activ�s \nSaisir le nombre de joueurs virtuels (max 6 joueurs en tout | max 5 bots) : ");
        				PartieV2.partieUnique.setnbBots(clavier.nextInt());
        			}
        			System.out.println("Saisir le nombre de joueurs physiques (max 6 (bots+joueurs) : ");
        			PartieV2.partieUnique.setnbJoueursPhys(clavier.nextInt());
        		}
        	}
            else {
            	while (PartieV2.partieUnique.getnbJoueursPhys()>=7 || PartieV2.partieUnique.getnbJoueursPhys()<3) {
            	System.out.println("Bots d�sactiv�s \nSaisir le nombre de joueurs physiques (max 6) : ");
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
    			//ajouter la mise � l'�cart de 2 cartes
    		}
    		else if (PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys()==6) {
    			nbcartes=2;
    		}
    	//Cr�ation de la partie
        System.out.println("Cr�ation d'une partie avec "+ PartieV2.partieUnique.getnbBots() +" joueur(s) physique(s) et "+ PartieV2.partieUnique.getnbBots() +" joueur(s) virtuel(s)");
        System.out.println(nbcartes+" cartes vont �tre distribu�es � chaque joueur");
    	PartieV2.getInstance(PartieV2.partieUnique.getnbBots(), PartieV2.partieUnique.getnbBots(), nbcartes);
    	//Cr�ation de la liste des joueurs
    	for (int i=0; i<PartieV2.partieUnique.getnbBots() ; i++) {
    		PartieV2.partieUnique.ajouterUnJoueur(new Joueur_Physique_ou_VirtuelV2(false,false,false,true,i,0,false,true));
    	}
    	for (int i=0; i<PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys() ; i++) {
    		PartieV2.partieUnique.ajouterUnJoueur(new Joueur_Physique_ou_VirtuelV2(false,false,false,true,i,0,false,false));
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
    		PartieV2.partieUnique.cartesd�fauss�es.add(PartieV2.partieUnique.jouercarte.get(10));
    		PartieV2.partieUnique.cartesd�fauss�es.add(PartieV2.partieUnique.jouercarte.get(11));
    		PartieV2.partieUnique.cartesup1=PartieV2.partieUnique.jouercarte.get(10);
    		PartieV2.partieUnique.cartesup2=PartieV2.partieUnique.jouercarte.get(11);
    	}
    	//D�but de la partie
    	// Choix des roles 
    	for (int i=0; i<PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys() ; i++) {
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
		PartieV2.comptr�v�l�s=0;
		int actuel = idj;
		while(PartieV2.comptr�v�l�s<(PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys()-1)) {
		PartieV2.partieUnique.enleverTour(actuel);
		char choix = 'x';
		while(choix != 'A' && choix != 'H') {
		System.out.println("\nJoueur "+actuel+" voulez-vous lancer une accusation ou utiliser l'effet Hunt d'une de vos cartes ?(a ou h)");
    	choix = clavier.next().charAt(0); // saisie du choix , soit a soit h
    	choix = conversion_majuscule(choix);
		}
    	if (choix == 'A') {
    		System.out.println("\n\nVous avez d�cid� de lancer une accusation");
    		System.out.println("\nQui voulez-vous accusez ?");
    		int accus� = clavier.nextInt();
    		if(accus�>=0 && accus�<(PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys()) && accus� != actuel && PartieV2.partieUnique.jouerjoueur.get(accus�).r�v�l�==false && PartieV2.partieUnique.jouerjoueur.get(accus�).peutEtreAccus�==true && PartieV2.partieUnique.jouerjoueur.get(accus�).enJeu==true && PartieV2.partieUnique.jouerjoueur.get(accus�).getBot()==false) {
    		System.out.println("Joueur "+accus�+" vous �tes accus� !\n Voici vos cartes :");
    		Iterator<Cartes_RumeursV2> itac = PartieV2.partieUnique.jouerjoueur.get(accus�).main.iterator();
    		while(itac.hasNext()) {
    			String nom = itac.next().nom;
    			PartieV2.partieUnique.jouercarte.get(0).afficherDescription(nom);
    	}
    		if (PartieV2.partieUnique.jouerjoueur.get(accus�).main.size()==0) {
    			System.out.println("\nJoueur "+accus�+" vous n'avez plus de cartes rumeur, votre identit� va �tre r�v�l�e");
    			System.out.println("\nLe r�le du joueur "+accus�+" est : ");
        		PartieV2.partieUnique.jouerjoueur.get(accus�).r�v�l�=true;
        		PartieV2.comptr�v�l�s++;
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
    		else {
    		System.out.println("\nQue souhaitez vous faire ? Utiliser un effet Witch(w) ou r�v�ler votre identit�(i) ?");
    		char r�ponse = clavier.next().charAt(0); // saisie du choix , soit w soit i
        	r�ponse = conversion_majuscule(r�ponse);
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
        		System.out.println("Veuillez indiquer le nom de la carte que vous souhaitez utiliser");
        		String nomc = clavier.nextLine();
        		nomcarte=nomc;
        		app = PartieV2.appartient(nomc, accus�);
        		if (app==false) {
    			System.out.println("Cette carte n'est pas en votre possession");
        		}
        		String theinquisitionw = "The Inquisition";
        		if (theinquisitionw.equalsIgnoreCase(nomc) && PartieV2.partieUnique.jouerjoueur.get(accus�).main.size()<2) {
    				app=false;
    				System.out.println("\nCette carte n'est pas jouable car son effet Witch n�cessite au minimum 2 cartes");
    			}
        		String hookednose = "Hooked Nose";
        		if (hookednose.equalsIgnoreCase(nomc) && PartieV2.partieUnique.jouerjoueur.get(actuel).main.size()==0) {
    				app=false;
    				System.out.println("\nCette carte n'est pas jouable car son effet Witch n�cessite que celui qui vous a accus� ait au moins 1 carte");
    			}
        		String pointedhat = "Pointed Hat";
        		if (pointedhat.equalsIgnoreCase(nom)&& PartieV2.getJouerJoueur().get(actuel).cartesjouees.size()==0) {
        			app=false;
        			System.out.println("\nCette carte n'est pas jouable car vous n'avez r�v�l� aucune carte rumeur");
        		}
        		nom=nomc;
        		}
        		//si on utilise la carte evil eye, on stocke en m�moire le nom du joueur qui l'a utilis�
        		int ancien = -1;
        		String evileye = "Evil Eye";
        		if (evileye.equalsIgnoreCase(nom)){
        		ancien=accus�;
        		}
        		//si la carte evil eye a �t� d�fauss�es (on n'entre dans cette boucle que lors du tour qui suit celui o� a �t� jou� la carte), le joueur ayant jou� cette carte peut-�tre accus�
        		//cette boucle intervenant apr�s le choix du joueur, celui qui a jou� cette carte est donc prot�g� pendant 1 tour
        		Iterator<Cartes_RumeursV2> it4 = PartieV2.partieUnique.cartesd�fauss�es.iterator();
        		while(it4.hasNext()) {
        		if ((it4.next().nom).equalsIgnoreCase(evileye)) {
        		PartieV2.partieUnique.jouerjoueur.get(ancien).peutEtreAccus�=true;
        		}
        	}
        		idchoisie = PartieV2.trouveIndexMain(accus�,nom);
        		PartieV2.partieUnique.jouerjoueur.get(accus�).main.get(idchoisie).jouerCarte(nomcarte,false,accus�,actuel);
        		PartieV2.enleverCarte(idchoisie,accus�);
        	}
        	else {
        		System.out.println("Vous avez choisi de r�v�ler votre identit�");
        		System.out.println("Le r�le du joueur "+accus�+" est : ");
        		PartieV2.partieUnique.jouerjoueur.get(accus�).r�v�l�=true;
        		PartieV2.comptr�v�l�s++;
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
    		}
    		if(PartieV2.partieUnique.jouerjoueur.get(accus�).getBot()==true)
    		{
    			PartieV2.partieUnique.jouerjoueur.get(accus�).setEstAccus�(true);
    			PartieV2.partieUnique.jouerjoueur.get(accus�).sonTour=true;
    			Agressive.jouerBot(PartieV2.partieUnique.jouerjoueur.get(accus�)); 
    		}
    			else {
    			System.out.println("\nVeuillez choisir un autre joueur");
    		}
    	}
    	else {
    		System.out.println("\n\nVous avez d�cid� de jouer une carte rumeur");
    		String nomcarte=null;
    		String nomc = null;
    		boolean app= false;
    		String nom ="0";
    		short bufferClavier=0;
			if(bufferClavier==0) {  // Vide la premiere ligne 1 fois seulement pour ne pas sauter la ligne nomc = clavier.nextLine();
	    		clavier.nextLine();
	    		bufferClavier=1;
	    		}
    		while (app ==false) {
    			String debug = clavier.nextLine();
    			System.out.println("Veuillez indiquer le nom de la carte que vous souhaitez utiliser");
  
    			nomc = clavier.nextLine();
    			System.out.println("\nnomc : "+nomc);
    			nomcarte=nomc;
    			app = PartieV2.appartient(nomc , actuel);
    			String angrymob = "Angry Mob";
    			String theinquisitionh = "The Inquisition";
    			if(( angrymob.equalsIgnoreCase(nomc)||theinquisitionh.equalsIgnoreCase(nomc))&& (PartieV2.partieUnique.jouerjoueur.get(actuel).r�v�l�==false || PartieV2.partieUnique.jouerjoueur.get(actuel).witch==true)){
    					app=false;
    					System.out.println("\nVous ne pouvez pas utiliser cette carte car vous n'avez pas �t� r�v�l� en tant que villageois");
    			}
    			String pointedhat = "Pointed Hat";
    			if (pointedhat.equalsIgnoreCase(nom)&& PartieV2.getJouerJoueur().get(actuel).cartesjouees.size()==0) {
    				app=false;
    				System.out.println("\nCette carte n'est pas jouable car vous n'avez r�v�l� aucune carte rumeur");
    			}
    			String cauldron ="Cauldron";
    			String toad = "Toad";
    			if((cauldron.equalsIgnoreCase(nomc)||toad.equalsIgnoreCase(nomc)) && PartieV2.getJouerJoueur().get(actuel).r�v�l�==true) {
    				System.out.println("Vous ne pouvez pas jouer cette carte, votre identit� a d�j� �t� r�v�l�e");
    				app=false;
    			}
    			int decision=-1;
    			if (app==false) {
    				System.out.println("Cette carte n'est pas en votre possession");
    				System.out.println("\nVoulez vous quitter le choix des cartes ? (0 : oui/1 : non)");
    				int decis = clavier.nextInt();
    				decision=decis;
    			}
    			if(decision==0) {
    				System.out.println("Vous avez d�cider de revenir au choix entre accusation et effet hunt");
    				app=true;
    			}
    			if(app==true && decision != 0){
    				//si on utilise la carte evil eye, on stocke en m�moire le nom du joueur qui l'a utilis�
    				int ancien = -1;
    				String evileye = "Evil Eye";
    				if(ancien!=-1) {
    					//si la carte evil eye a �t� d�fauss�es (on n'entre dans cette boucle que lors du tour qui suit celui o� a �t� jou� la carte), le joueur ayant jou� cette carte peut-�tre accus�
    					//cette boucle intervenant apr�s le choix du joueur, celui qui a jou� cette carte est donc prot�g� pendant 1 tour
    					Iterator<Cartes_RumeursV2> it4 = PartieV2.partieUnique.cartesd�fauss�es.iterator();
    					while(it4.hasNext()) {
    							if ((it4.next().nom).equalsIgnoreCase(evileye)) {
    								PartieV2.partieUnique.jouerjoueur.get(ancien).peutEtreAccus�=true;
    							}
    					}
    				}
    				if (evileye.equalsIgnoreCase(nom)){
    					ancien=actuel;
    				}
     
    				int idchoisie = PartieV2.trouveIndexMain(actuel,nomc);
    				PartieV2.partieUnique.jouerjoueur.get(actuel).main.get(idchoisie).jouerCarte(nomcarte,true,actuel,actuel);
    				PartieV2.enleverCarte(idchoisie,actuel);
    			}
    		}
    		
    	}
    	for (int i=0;i<(PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys());i++) {
    		if((PartieV2.partieUnique.jouerjoueur.get(i).sonTour)==true) {
    			actuel=i;
    		}
    	}
		}
		System.out.println("Round termin�");
     	//partie pour la distribution des cartes, � modifier en utilisant le td Collections
    	clavier.close();
    }

}
package frr.utt.lo02.projet.Modele;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
/**
 * 
 * @author valentin guitton et lou prevost
 */
/**
 * La classe partie correspond ? une instanciation d'une partie du jeu Witch Hunt
 * On y retrouve des informations sur la partie telles que les joueurs, les cartes.
 * On y trouve ?galement une boucle qui g?re les rounds et la partie, dans laquelle des entr?es clavier sont lues (sans interface graphique) pour entrainer diff?rentes actions
 * Cette classe impl?mente ?galement des m?thodes correspondant aux effets des diff?rentes cartes
 * Partie est un singleton, ce qui signifie qu'une seule instanciation de cette classe est possible, si une instance de classe existe d?j?, elle est renvoy?e quand on veut en cr?er une autre
 *
 */
public class PartieV2 extends Observable{
	/**
	 * Nombre de joueurs physiques participant ? la partie
	 */
    private static int nbJoueursPhys=-2;
    /**
     * Nombre de bots participant ? la partie
     */
    private static int nbBots=-2;
    /**
     * Id du joueur donc c'est le tour
     */
    private static int actuel=-1;
    /**
     * Id du joueur accus?
     */
    private static int accus? =-1;
    /**
     * Bool?en permettant de savoir si on fait une partie avec l'interface graphique, sert notamment ? ne pas attendre des inputs clavier
     */
    private boolean interfaceg=false;
/**
 * Attribut correspondant ? la partie en cours, param?tre obligatoire dans un singleton
 */
    private static PartieV2 partieUnique = null;
    /**
     * Nombre de cartes par joueur dans une partie
     */
    private static int nbCartesParJoueur;
    /**
     * 1?re carte automatiquement d?fauss?e quand on a 5 joueurs
     */
    protected static Cartes_RumeursV2 cartesup1;
    /**
     * 2?me carte automatiquement d?fauss?e quand on a 5 joueurs
     */    
    protected static Cartes_RumeursV2 cartesup2;
    /**
     * Liste des joueurs de la partie
     */
    static List<Joueur_Physique_ou_VirtuelV2> jouerjoueur;
    /**
     * Liste des cartes du jeu
     */
    private List<Cartes_RumeursV2> jouercarte;
    /**
     * Liste des cartes d?fauss?es au cours de la partie
     */
    private List<Cartes_RumeursV2> cartesd?fauss?es;
    /**
     * Compteur de joueurs r?v?l?s, permettant de mettre fin ? un round s'il ne reste qu'un seul joueur donc l'identit? est inconnue
     */
    protected static int comptr?v?l?s=0;
    
//constructeur 
    /**
     * 
     * @param nbjp
     * @param nbbot
     * @param nbcartes
     */
    /**
     * Constructeur de la classe Partie, on a en entr?e le nombre de joueurs physiques et virtuels ainsi que le nombre de cartes par joueur
     */
    private PartieV2(int nbjp,int nbbot, int nbcartes) {
    	this.nbJoueursPhys=nbjp;
    	this.nbBots=nbbot;
    	this.nbCartesParJoueur=nbcartes;
    	jouerjoueur = new ArrayList<Joueur_Physique_ou_VirtuelV2> ();
    	jouercarte = new ArrayList<Cartes_RumeursV2> ();
    	cartesd?fauss?es = new ArrayList<Cartes_RumeursV2> ();
    	
    }
    /**
     * 
     * @param id
     */
    /**
     * Cette m?thode re?oit en entr?e l'id d'un joueur, affiche la liste des cartes qu'il a utilis? et lui permet d'en r?cup?rer une
     */
    public static  void choisirCarteDef(int id){
    	int compt2=0;
		System.out.println("\n Voici les cartes rumeurs que vous avez utilis? :");
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
		System.out.println("\n Quel est le num?ro de la carte que vous souhaitez r?cup?rer ?");
		num = clavier.nextInt();
			if(num<0 || num>(compt2-1)) {
				System.out.println("\n Num?ro invalide");
			}
		}	
		PartieV2.partieUnique.jouerjoueur.get(id).main.add(PartieV2.partieUnique.jouerjoueur.get(id).cartesjouees.get(num));
		for (int i=0;i<PartieV2.partieUnique.cartesd?fauss?es.size();i++) {
			if ((PartieV2.partieUnique.cartesd?fauss?es.get(i).nom).equalsIgnoreCase(PartieV2.partieUnique.jouerjoueur.get(id).cartesjouees.get(num).nom)) {
				PartieV2.partieUnique.cartesd?fauss?es.remove(PartieV2.partieUnique.cartesd?fauss?es.get(i));
			}
    }
		PartieV2.partieUnique.jouerjoueur.get(id).cartesjouees.remove(PartieV2.partieUnique.jouerjoueur.get(id).cartesjouees.get(num));
    }
    /**
     * 
     * @param id
     */
    /**
     * Affiche la main du joueur dont l'id est re?u en param?tre
     */
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
    /**
     * 
     * @param nbjp
     * @param nbbot
     * @param nbcartes
     * @return Partiev2.partieUnique
     */
    /**
     * Cette m?thode permet l'int?gration du patron Singleton, on l'appelle au lieu d'appeler le constructeur de la classe, qui lui est priv?.
     * Si on a d?j? une instance de la classe Partie, cette derni?re nous est retourn?e, sinon une instance de la classe est cr??e.
     */
    public static PartieV2 getInstance(int nbjp, int nbbot, int nbcartes) {
    	if (PartieV2.partieUnique == null) {
    		PartieV2.partieUnique = new PartieV2(nbjp, nbbot, nbcartes);
    	}
    	return PartieV2.partieUnique;
    }
    /**
     * 
     * @param nbJoueurPhys
     */
    /**
     * On d?finit le nombre de joueurs physiques
     */
    public void setnbJoueursPhys(int nbJoueurPhys) {
    	this.nbJoueursPhys=nbJoueurPhys;
    	this.setChanged();
    	this.notifyObservers();
    }
    /**
     * 
     * @return nbJoueursPhys
     */
    /**
     * On obtient le nombre de joueurs physiques de la partie
     */
    public static int getnbJoueursPhys() {
    	return nbJoueursPhys;
    }
    /**
     * 
     * @param nbBots
     */
    /**
     * On d?finit le nombre de joueurs virtuels
     */
    public void setnbBots(int nbBots) {
    	this.nbBots=nbBots;
    	this.setChanged();
    	this.notifyObservers();
    }
    /**
     * 
     * @return nbBots
     */
    /**
     * On obtient le nombre de joueurs virtuels de la partie
     */
    public static int getnbBots() {
    	return nbBots;
    }
    /**
     * 
     * @param act
     */
    /**
     * On d?finit le joueur actuel
     */
    public void setActuel(int act) {
    	this.actuel=act;
    	this.setChanged();
    	this.notifyObservers();
    }
    /**
     * 
     * @return actuel
     */
    /**
     * On obtient l'id du joueur actuel
     */
    public static int getActuel() {
    	return actuel;
    }
    /**
     * 
     * @param nbcarte
     */
    /**
     * On d?finit le nombre de cartes par joueur
     */
    public void setNbCartesParJoueur(int nbcarte) {
    	this.nbCartesParJoueur=nbcarte;
    }
    /**
     * 
     * @return nbCartesParJoueur
     */
    /**
     * On obtient le nombre de cartes par joueur
     */
    public static int getNbCartesParJoueur() {
    	return nbCartesParJoueur;
    }
    /**
     * 
     * @param compt
     */
    /**
     * On d?finit le nombre de joueurs dont l'identit? a ?t? r?v?l?e
     */
    public void setComptR?v?l?s(int compt) {
    	this.comptr?v?l?s=compt;
    	this.setChanged();
    	this.notifyObservers();
    }
    /**
     * 
     * @return comptr?v?l?s
     */
    /**
     * On retourne le nombre de joueurs dont l'id a ?t? r?v?l?e
     */
    public int getComptR?v?l?s() {
    	return this.comptr?v?l?s;
    }
    /**
     * 
     * @param accus?
     */
    /**
     * On d?finit qui est accus?
     */
    public static void setAccus?(int accus?) {
    	PartieV2.accus?=accus?;
    }
    /**
     * 
     * @return accus?
     */
    /**
     * On obtient l'id du joueur accus?
     */
    public int getAccus?() {
    	return this.accus?;
    }
    /**
     * 
     * @return jouerjoueur
     */
    /**
     * On obtient la liste des joueurs
     */
    public static List<Joueur_Physique_ou_VirtuelV2> getJouerJoueur(){
    	return PartieV2.partieUnique.jouerjoueur;
    }
    /**
     * 
     * @return jouerjoueur
     */
    /**
     * Autre m?thode permettant d'obtenir les joueurs et de les rendre observables pour l'interface graphique
     */
    public List<Joueur_Physique_ou_VirtuelV2> getJouerJoueur2(){
    	this.setChanged();
    	this.notifyObservers();
    	return this.jouerjoueur;
    }
    /**
     * 
     * @return cartesd?fauss?es
     */
    /**
     * On obtient la liste des cartes d?fauss?es
     */
    public static List<Cartes_RumeursV2> getCartesDefauss?es(){
    	return PartieV2.partieUnique.cartesd?fauss?es;
    }
    /**
     * 
     * @return jouercarte
     */
    /**
     * On obtient la liste des cartes du jeu
     */
    public static List<Cartes_RumeursV2> getJouerCarte(){
    	return PartieV2.partieUnique.jouercarte;
    }
    /**
     * 
     * @return jouercarte
     */
    /**
     * Autre m?thode pour obtenir la liste des cartes du jeu et de les rendres observables pour l'interface graphique
     */
    public List<Cartes_RumeursV2> getJouerCarte2(){
    	this.setChanged();
    	this.notifyObservers();
    	return this.jouercarte;
    }
    /**
     * 
     * @param inter
     */
    /**
     * Permet de d?finir si on utilise une interface graphique ou non
     */
    public void setInterfaceg(boolean inter) {
    	PartieV2.partieUnique.interfaceg=inter;
    }
    /**
     * 
     * @return interfaceg
     */
    /**
     * Permet de savoir si on utilise une interface graphique
     */
    public static boolean getInterfaceg() {
    	return PartieV2.partieUnique.interfaceg;
    }
    /**
     * On cr?? le jeu (liste des cartes)
     */
    public static void creationJeu() {
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
    }
	// ajout d'un joueur ? la liste des joueurs
    /**
     * 
     * @param joueur
     */
    /**
     * On ajoute un joueur dans la partie
     */
	public void ajouterUnJoueur(Joueur_Physique_ou_VirtuelV2 joueur){
		this.jouerjoueur.add(joueur);
	}
//distribution d'une carte ? un joueur
	/**
	 * 
	 * @param joueur
	 * @param carte
	 */
	/**
	 * On donne une carte ? un joueur
	 */
    public void distribuerCartes(Joueur_Physique_ou_VirtuelV2 joueur, Cartes_RumeursV2 carte) {
    	joueur.main.add(carte);
    }
//m?lange des cartes
    /**
     * On m?lange le tas de cartes
     */
	public void melanger(){
		Collections.shuffle(this.jouercarte);
	}
	/**
	 * 
	 * @param nomcarte
	 * @param id
	 * @return boolean
	 */
	/**
	 * On obtient un boolean nous indiquant si la carte portant le nom nomcarte appartient au joueur dont l'id est pass? en param?tre
	 */
	public static boolean appartient(String nomcarte, int id) {
		boolean app =false;
		for (int i=0;i<PartieV2.partieUnique.jouerjoueur.get(id).main.size();i++) {
			if ((PartieV2.partieUnique.jouerjoueur.get(id).main.get(i).nom).equalsIgnoreCase(nomcarte)) {
				app = true;
			}
		}
		return app;
	}
	/**
	 * 
	 * @param idj
	 * @param nomc
	 * @return int
	 */
	/**
	 * On obtient la place de la carte de nom nomc dans la main du joueur dont l'id est pass? en param?tre
	 */
	public static int trouveIndexMain(int idj, String nomc) {
		int idchoisie = -1;
		for (int i=0;i<PartieV2.partieUnique.jouerjoueur.get(idj).main.size();i++) {
			if ((PartieV2.partieUnique.jouerjoueur.get(idj).main.get(i).nom).equalsIgnoreCase(nomc)) {
				idchoisie = i;
			}
		}
		return idchoisie;
	}
	/**
	 * 
	 * @param id
	 * @return bool?en
	 */
	/**
	 * Permet de savoir si un joueur est encore en jeu
	 */
	public static boolean enJeu(int id) {
		if(PartieV2.partieUnique.jouerjoueur.get(id).enJeu==true) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * 
	 * @param id
	 * @return int
	 */
	/**
	 * Renvoie la taille de la main du joueur dont l'id est pass? en param?tre
	 */
	public static int donnerTailleMain(int id) {
		int size=-1;
		size=PartieV2.partieUnique.jouerjoueur.get(id).main.size();
		return size;
	}
	/**
	 * 
	 * @param id
	 */
	/**
	 * Permet de prot?ger d'une accusation un joueur lorsqu'il utilise l'effet de la carte Evil Eye
	 */
	public static void protegerJoueur(int id) {
		PartieV2.partieUnique.jouerjoueur.get(id).peutEtreAccus?=false;
		System.out.println("\nLe joueur "+id+" ne pourra pas etre accus? au prochain tour");
	}
   /**
    * 
    * @param id
    */
	/**
    * On ajoute 1 point au joueur dont l'id est pass? en param?tre 
    */
    public static void ajoutPoint(int id) {
    	PartieV2.partieUnique.jouerjoueur.get(id).points++;
    	System.out.println("Le joueur "+id+" gagne 1 point");
    }
    /**
     * 
     * @param id
     */
    /**
     * On enl?ve 1 point au joueur dont l'id est pass? en param?tre 
     */
    public static void enleverPoint(int id) {
    	PartieV2.partieUnique.jouerjoueur.get(id).points--;
    	System.out.println("Le joueur "+id+" perd 1 point");
    }
    /**
     * 
     * @param idc
     * @param idjdonne
     * @param idjre?oit
     */
    /**
     * Le joueur idjdonne donne la carte qui se trouve dans sa main ? la position idc au joueur idjre?oit
     */
    public static void donnerCarte(final int idc, final int idjdonne, final int idjre?oit) {
    	PartieV2.partieUnique.jouerjoueur.get(idjre?oit).main.add(PartieV2.partieUnique.jouerjoueur.get(idjdonne).main.get(idc));
    	PartieV2.partieUnique.jouerjoueur.get(idjdonne).main.remove(PartieV2.partieUnique.jouerjoueur.get(idjdonne).main.get(idc));
    }
    /**
     * 
     * @param carteid
     * @param id
     */
    /**
     * On env?le au joueur dont l'id est pass? en param?tre la carte qui se trouve dans sa main ? la position carteid
     */
    public static void enleverCarte(int carteid, final int id) {
    	System.out.println("Cartes enlev?es : ");
    	int carte=carteid;
    	if (carteid==PartieV2.partieUnique.jouerjoueur.get(id).main.size()) {
    		carte=carteid-1;
    	}
    	PartieV2.partieUnique.jouerjoueur.get(id).cartesjouees.add(PartieV2.partieUnique.jouerjoueur.get(id).main.get(carte));
    	PartieV2.partieUnique.cartesd?fauss?es.add(PartieV2.partieUnique.jouerjoueur.get(id).main.get(carte));
		Iterator<Cartes_RumeursV2> itest = PartieV2.partieUnique.cartesd?fauss?es.iterator();
		while (itest.hasNext()) {
			System.out.println(itest.next());
		}
		PartieV2.partieUnique.jouerjoueur.get(id).main.remove(PartieV2.partieUnique.jouerjoueur.get(id).main.get(carte));
    }
    /**
     * 
     * @param id
     */
    /**
     * On donne le tour au joueur dont l'id est pass? en param?tre
     */
    public static void donnerTour(final int id) {
    	PartieV2.partieUnique.jouerjoueur.get(id).sonTour = true;
    	PartieV2.partieUnique.actuel=id;
		PartieV2.partieUnique.setChanged();
		PartieV2.partieUnique.notifyObservers();
    	
    }
    /**
     * 
     * @param id
     */
    /**
     * On enl?ve le tour au joueur dont l'id est pass? en param?tre
     */
    public static void enleverTour (int id) {
    	jouerjoueur.get(id).sonTour = false;
    }

    //v?rifier si il reste plus de 2 joueurs et que l'on peut donc prot?ger un joueur d'une accusation
    /**
     * 
     * @return bool?en
     */
    /**
     * Si il reste plus de 2 joueurs non r?v?l?s dans le round, un joueur peut ?tre prot?g? par l'effet de la carte Evil Eye, sinon cette protection est invalid?e
     */
    public static boolean choixJoueur(){
    	if (PartieV2.comptr?v?l?s<PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys()-2) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    /**
     * 
     * @param id
     * @return bool?en
     */
    /**Le joueur dont l'id est pass? en param?tre r?v?le son r?le
     */
    public static boolean revelerRole(int id) {
    	System.out.println("\nLe r?le du joueur "+id+" est :");
    	PartieV2.getJouerJoueur().get(id).r?v?l?=true;
    	PartieV2.comptr?v?l?s++;
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
    /**
     * 
     * @param choix
     * @return char
     */
    /**
     * Convertit un caract?re minuscule en majuscule, facilite l'utilisation des scanners
     */
    public static char conversion_majuscule(char choix){  //Fonction pour convertir en majuscule
        choix = Character.toUpperCase(choix); // convertion de choix en majuscule
        return(choix); // retourne choix
    }
    public static void main(String[] args) {
    //On initialise des variables pour le nombre de joueurs physiques et virtuels, on utilise un scanner pour r?cup?rer les nombres entr?s par l'utilisateur
    //tout en respectant les contraintes max 6 joueurs, au moins un joueur physique et en calculant le nombre de cartes par joueur
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
        				System.out.println("Bots activ?s \nSaisir le nombre de joueurs virtuels (max 6 joueurs en tout | max 5 bots) : ");
        				PartieV2.partieUnique.setnbBots(clavier.nextInt());
        			}
        			System.out.println("Saisir le nombre de joueurs physiques (max 6 (bots+joueurs) : ");
        			PartieV2.partieUnique.setnbJoueursPhys(clavier.nextInt());
        		}
        	}
            else {
            	while (PartieV2.partieUnique.getnbJoueursPhys()>=7 || PartieV2.partieUnique.getnbJoueursPhys()<3) {
            	System.out.println("Bots d?sactiv?s \nSaisir le nombre de joueurs physiques (max 6) : ");
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
    			//ajouter la mise ? l'?cart de 2 cartes
    		}
    		else if (PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys()==6) {
    			nbcartes=2;
    		}
    	//Cr?ation de la partie
        System.out.println("Cr?ation d'une partie avec "+ PartieV2.partieUnique.getnbJoueursPhys() +" joueur(s) physique(s) et "+ PartieV2.partieUnique.getnbBots() +" joueur(s) virtuel(s)");
        System.out.println(nbcartes+" cartes vont ?tre distribu?es ? chaque joueur");
    	PartieV2.getInstance(PartieV2.partieUnique.getnbJoueursPhys(), PartieV2.partieUnique.getnbBots(), nbcartes);
    	//Cr?ation de la liste des joueurs
    	for (int i=0; i<PartieV2.partieUnique.getnbBots() ; i++) {
    		PartieV2.partieUnique.ajouterUnJoueur(new Joueur_Physique_ou_VirtuelV2(false,false,false,true,i,0,false,true));
    	}
    	for (int i=PartieV2.partieUnique.getnbBots(); i<PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys() ; i++) {
    		PartieV2.partieUnique.ajouterUnJoueur(new Joueur_Physique_ou_VirtuelV2(false,false,false,true,i,0,false,false));
    	}
    	//Cr?ation de la liste de cartes
    	PartieV2.partieUnique.creationJeu();
    	
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
    		PartieV2.partieUnique.cartesd?fauss?es.add(PartieV2.partieUnique.jouercarte.get(10));
    		PartieV2.partieUnique.cartesd?fauss?es.add(PartieV2.partieUnique.jouercarte.get(11));
    		PartieV2.partieUnique.cartesup1=PartieV2.partieUnique.jouercarte.get(10);
    		PartieV2.partieUnique.cartesup2=PartieV2.partieUnique.jouercarte.get(11);
    	}
    	
    	
    	
    	
    	//D?but de la partie
    	// Choix des roles
    	int idj = 8;
    	while(idj>(PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys()) || idj<0 ) {
    	System.out.println("Qui est le joueur le plus jeune ? (Entrer un chiffre entre 0 et le nombre de joueurs pr?sents -1)");
    	idj = clavier.nextInt();
    	if(idj>(PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys())||idj<0) {System.out.println("Il n'existe pas de Joueur " + idj);}
    	}
    	PartieV2.donnerTour(idj);
		boolean finie =false;
		while(finie==false) {
    	for (int i=0; i<PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys() ; i++) {
    		int r=2;
    		boolean j = true;
    		while(r<0 || r>1) {
    		System.out.println("Joueur " + i + " : Choisissez votre r?le (0 : villageois | 1 : sorci?re)");
    		if(PartieV2.getJouerJoueur().get(i).getBot()==false) {
    		r = clavier.nextInt();
    		}
    		else {
    			Random random = new Random();
	        	r = random.nextInt(2);
    		}
    		}
    		if(r==1) j = true;
    		else j = false;
    		PartieV2.partieUnique.jouerjoueur.get(i).choisirRole(j);
    		if(j==true) {
    			System.out.println(" !!ROLE!! : Joueur num?ro " + i + " vous ?tes une sorci?re ");
    			PartieV2.partieUnique.jouerjoueur.get(i).choisirRole(true);
    		}
    		else {
    			System.out.println(" !!ROLE!! : Joueur num?ro " + i + " vous ?tes un villageois ");
    			PartieV2.partieUnique.jouerjoueur.get(i).choisirRole(false);
    		}
    	};
    //	System.out.println("Joueur "+idj+" voici vos cartes :");
		//Iterator<Cartes_RumeursV2> it2 = PartieV2.partieUnique.jouerjoueur.get(idj).main.iterator();
		//while(it2.hasNext()) {
			//String nom = it2.next().nom;
			//PartieV2.partieUnique.jouercarte.get(0).afficherDescription(nom);
		//}
		PartieV2.comptr?v?l?s=0;
		int actuel = idj;
		while(PartieV2.comptr?v?l?s<(PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys()-1)) {
			System.out.println("\nLe nombre de joueurs r?v?l?s est : "+PartieV2.comptr?v?l?s);
			System.out.println("C'est le tour du joueur "+actuel);
		PartieV2.partieUnique.enleverTour(actuel);
		if(PartieV2.partieUnique.jouerjoueur.get(actuel).getBot()==true) {
			PartieV2.partieUnique.jouerjoueur.get(actuel).sonTour=true;
			Agressive.jouerBot(PartieV2.partieUnique.jouerjoueur.get(actuel),PartieV2.partieUnique.jouerjoueur.get(actuel)); 
		}
		else {
		char choix = 'x';
		System.out.println("Joueur "+actuel+" voici vos cartes : ");
		Iterator<Cartes_RumeursV2> itactuel = PartieV2.partieUnique.jouerjoueur.get(actuel).main.iterator();
		while(itactuel.hasNext()) {
			String nom = itactuel.next().nom;
			PartieV2.partieUnique.jouercarte.get(0).afficherDescription(nom);
	}
		while(choix != 'A' && choix != 'H') {
		System.out.println("\nJoueur "+actuel+" voulez-vous lancer une accusation ou utiliser l'effet Hunt d'une de vos cartes ?(a ou h)");
    	choix = clavier.next().charAt(0); // saisie du choix , soit a soit h
    	choix = conversion_majuscule(choix);
		}
    	if (choix == 'A') {
    		System.out.println("\n\nVous avez d?cid? de lancer une accusation");
    		System.out.println("\nQui voulez-vous accusez ?");
    		int accus? = clavier.nextInt();
    		if(accus?>=0 && accus?<(PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys())) {
    		//
    		if(accus? != actuel && PartieV2.partieUnique.jouerjoueur.get(accus?).r?v?l?==false && PartieV2.partieUnique.jouerjoueur.get(accus?).peutEtreAccus?==true && PartieV2.partieUnique.jouerjoueur.get(accus?).enJeu==true && PartieV2.partieUnique.jouerjoueur.get(accus?).getBot()==false) {
    		System.out.println("Joueur "+accus?+" vous ?tes accus? !\nVoici vos cartes :");
    		Iterator<Cartes_RumeursV2> itac = PartieV2.partieUnique.jouerjoueur.get(accus?).main.iterator();
    		while(itac.hasNext()) {
    			String nom = itac.next().nom;
    			PartieV2.partieUnique.jouercarte.get(0).afficherDescription(nom);
    	}
    		if (PartieV2.partieUnique.jouerjoueur.get(accus?).main.size()==0) {
    			System.out.println("\nJoueur "+accus?+" vous n'avez plus de cartes rumeur, votre identit? va ?tre r?v?l?e");
    			System.out.println("\nLe r?le du joueur "+accus?+" est : ");
        		PartieV2.partieUnique.jouerjoueur.get(accus?).r?v?l?=true;
        		PartieV2.comptr?v?l?s++;
        		if(PartieV2.partieUnique.jouerjoueur.get(accus?).witch==true) {
        			System.out.println("Sorci?re");
        			PartieV2.partieUnique.jouerjoueur.get(accus?).enJeu=false;
        			PartieV2.partieUnique.ajoutPoint(actuel);
        			System.out.println("\nJoueur "+actuel+" vous avez maintenant "+PartieV2.partieUnique.jouerjoueur.get(actuel).points+" points");
        		}
        		else {
        			System.out.println("Villageois");
        			System.out.println("\nLe joueur "+actuel+" ne gagne pas de points.");
        			System.out.println("\nJoueur "+accus?+" c'est votre tour");
        			actuel=accus?;
        		}
    		}
    		else {
        	boolean choixsur = false;
        	while(choixsur==false) {
        	choixsur=false;
    		System.out.println("\nQue souhaitez vous faire ? Utiliser un effet Witch(w) ou r?v?ler votre identit?(i) ?");
    		char r?ponse = clavier.next().charAt(0); // saisie du choix , soit w soit i
        	r?ponse = conversion_majuscule(r?ponse);
        	if (r?ponse=='W') {
        		System.out.println("\nVous avez choisi d'utiliser l'effet Witch d'une de vos cartes");
        		String nomcarte=null; 
        		int idchoisie=-1;
        		String debug=clavier.nextLine();//cet ?l?ment n'est pas ? proprement utile pour le code mais il y a un bug avec ue sorte de 
				//"ligne vide" qui est lue au premier clavier.nextLine() de cette boucle while
				//sans debug on passe une fois dans le for et certains if et des affichages faux ? l'?cran
				//peuvent d?concerter les joueurs
        		boolean app= false;
        		String nom ="0";
        		while (app ==false) {
        		String debugw = clavier.nextLine();
        		System.out.println("Veuillez indiquer le nom de la carte que vous souhaitez utiliser");
        		String nomc = clavier.nextLine();
        		nomcarte=nomc;
        		app = PartieV2.appartient(nomc, accus?);
        		String theinquisitionw = "The Inquisition";
        		if (theinquisitionw.equalsIgnoreCase(nomc) && PartieV2.partieUnique.jouerjoueur.get(accus?).main.size()<2) {
    				app=false;
    				System.out.println("\nCette carte n'est pas jouable car son effet Witch n?cessite au minimum 2 cartes");
    			}
        		String hookednose = "Hooked Nose";
        		if (hookednose.equalsIgnoreCase(nomc) && PartieV2.partieUnique.jouerjoueur.get(actuel).main.size()==0) {
    				app=false;
    				System.out.println("\nCette carte n'est pas jouable car son effet Witch n?cessite que celui qui vous a accus? ait au moins 1 carte");
    			}
        		String pointedhat = "Pointed Hat";
        		if (pointedhat.equalsIgnoreCase(nom)&& PartieV2.getJouerJoueur().get(actuel).cartesjouees.size()==0) {
        			app=false;
        			System.out.println("\nCette carte n'est pas jouable car vous n'avez r?v?l? aucune carte rumeur");
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
    				System.out.println("Vous avez d?cider de revenir au choix entre r?v?ler son identit? et effet witch");
    				app=true;
    			}
        		if(app==true && decision!=0) {
        		//si on utilise la carte evil eye, on stocke en m?moire le nom du joueur qui l'a utilis?
        		int ancien = -3;
        		String evileye = "Evil Eye";
        		if (evileye.equalsIgnoreCase(nom)){
        		ancien=accus?;
        		}
        		//si la carte evil eye a ?t? d?fauss?es (on n'entre dans cette boucle que lors du tour qui suit celui o? a ?t? jou? la carte), le joueur ayant jou? cette carte peut-?tre accus?
        		//cette boucle intervenant apr?s le choix du joueur, celui qui a jou? cette carte est donc prot?g? pendant 1 tour
        		Iterator<Cartes_RumeursV2> it4 = PartieV2.partieUnique.cartesd?fauss?es.iterator();
        		while(it4.hasNext()) {
        		if ((it4.next().nom).equalsIgnoreCase(evileye)) {
        		PartieV2.partieUnique.jouerjoueur.get(ancien).peutEtreAccus?=true;
        		}
        	}
        		System.out.println("Ici accus? vaut : "+accus?+" et actuel vaut : "+actuel);
        		idchoisie = PartieV2.trouveIndexMain(accus?,nom);
        		PartieV2.partieUnique.jouerjoueur.get(accus?).main.get(idchoisie).jouerCarte(nomcarte,false,accus?,actuel);
        		choixsur = true;
        		PartieV2.enleverCarte(idchoisie,accus?);
        		}
        	}
        	}
        	else {
        		System.out.println("Vous avez choisi de r?v?ler votre identit?");
        		//On annule l'effet de Evil Eye, le joueur prot?g? ce tour ci ne le sera plus
        		String evileye = "Evil Eye";
        		Iterator<Cartes_RumeursV2> it4 = PartieV2.getCartesDefauss?es().iterator();
        		while(it4.hasNext()) {
        		if ((it4.next().nom).equalsIgnoreCase(evileye)) {
        			for (int i=0;i<PartieV2.getnbBots()+PartieV2.getnbJoueursPhys();i++) {
        				Iterator<Cartes_RumeursV2> it5 = PartieV2.getJouerJoueur().get(i).getCartesJouees().iterator();
        				while ((it5.hasNext())) {
        					if((it5.next().nom).equalsIgnoreCase(evileye)){
        						PartieV2.getJouerJoueur().get(i).peutEtreAccus?=true;
        				}
        				}
        		}
        		}
        	}
        		choixsur=true;
        		System.out.println("Le r?le du joueur "+accus?+" est : ");
        		PartieV2.partieUnique.jouerjoueur.get(accus?).r?v?l?=true;
        		PartieV2.comptr?v?l?s++;
        		if(PartieV2.partieUnique.jouerjoueur.get(accus?).witch==true) {
        			System.out.println("Sorci?re");
        			PartieV2.partieUnique.jouerjoueur.get(accus?).enJeu=false;
        			PartieV2.partieUnique.ajoutPoint(actuel);
        			System.out.println("\nJoueur "+actuel+" vous avez maintenant "+PartieV2.partieUnique.jouerjoueur.get(actuel).points+" points");
        		}
        		else {
        			System.out.println("Villageois");
        			System.out.println("\nLe joueur "+actuel+" ne gagne pas de points.");
        			System.out.println("\nJoueur "+accus?+" c'est votre tour");
        			actuel=accus?;
        		}
        	}
    		}
        	}
    		}
    		
    		else if(PartieV2.partieUnique.jouerjoueur.get(accus?).getBot()==true && accus? != actuel && PartieV2.partieUnique.jouerjoueur.get(accus?).r?v?l?==false && PartieV2.partieUnique.jouerjoueur.get(accus?).peutEtreAccus?==true && PartieV2.partieUnique.jouerjoueur.get(accus?).enJeu==true)
    		{
    			PartieV2.partieUnique.jouerjoueur.get(accus?).setEstAccus?(true);
    			PartieV2.partieUnique.jouerjoueur.get(accus?).sonTour=true;
    			Agressive.jouerBot(PartieV2.partieUnique.jouerjoueur.get(accus?),PartieV2.partieUnique.jouerjoueur.get(actuel)); 
    		}
    		else {
    			System.out.println("\nVeuillez choisir un autre joueur");
    		}
    		}
    		else {
    			System.out.println("\nVeuillez choisir un autre joueur");
    		}
    	}
    	else {
    		System.out.println("\n\nVous avez d?cid? de jouer une carte rumeur");
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
    			//System.out.println("\nnomc : "+nomc);
    			nomcarte=nomc;
    			app = PartieV2.appartient(nomc , actuel);
    			String angrymob = "Angry Mob";
    			String theinquisitionh = "The Inquisition";
    			if(( angrymob.equalsIgnoreCase(nomc)||theinquisitionh.equalsIgnoreCase(nomc))&& (PartieV2.partieUnique.jouerjoueur.get(actuel).r?v?l?==false || PartieV2.partieUnique.jouerjoueur.get(actuel).witch==true)){
    					app=false;
    					System.out.println("\nVous ne pouvez pas utiliser cette carte car vous n'avez pas ?t? r?v?l? en tant que villageois");
    			}
    			String pointedhat = "Pointed Hat";
    			if (pointedhat.equalsIgnoreCase(nom)&& PartieV2.getJouerJoueur().get(actuel).cartesjouees.size()==0) {
    				app=false;
    				System.out.println("\nCette carte n'est pas jouable car vous n'avez r?v?l? aucune carte rumeur");
    			}
    			String cauldron ="Cauldron";
    			String toad = "Toad";
    			if((cauldron.equalsIgnoreCase(nomc)||toad.equalsIgnoreCase(nomc)) && PartieV2.getJouerJoueur().get(actuel).r?v?l?==true) {
    				System.out.println("Vous ne pouvez pas jouer cette carte, votre identit? a d?j? ?t? r?v?l?e");
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
    				System.out.println("Vous avez d?cider de revenir au choix entre accusation et effet hunt");
    				app=true;
    			}
    			if(app==true && decision != 0){
    				//si on utilise la carte evil eye, on stocke en m?moire le nom du joueur qui l'a utilis?
    				int ancien = -1;
    				String evileye = "Evil Eye";
    				if(ancien!=-1) {
    					//si la carte evil eye a ?t? d?fauss?es (on n'entre dans cette boucle que lors du tour qui suit celui o? a ?t? jou? la carte), le joueur ayant jou? cette carte peut-?tre accus?
    					//cette boucle intervenant apr?s le choix du joueur, celui qui a jou? cette carte est donc prot?g? pendant 1 tour
    					Iterator<Cartes_RumeursV2> it4 = PartieV2.partieUnique.cartesd?fauss?es.iterator();
    					while(it4.hasNext()) {
    							if ((it4.next().nom).equalsIgnoreCase(evileye)) {
    								PartieV2.partieUnique.jouerjoueur.get(ancien).peutEtreAccus?=true;
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
	   	}
    	for (int i=0;i<(PartieV2.partieUnique.getnbBots()+PartieV2.partieUnique.getnbJoueursPhys());i++) {
    		if((PartieV2.partieUnique.jouerjoueur.get(i).sonTour)==true) {
    			actuel=i;
			}
    		}
		}
		System.out.println("\nRound termin?");
		PartieV2.partieUnique.comptr?v?l?s=0;
		for (int i=0;i<PartieV2.partieUnique.jouerjoueur.size();i++) {
			if(PartieV2.partieUnique.jouerjoueur.get(i).r?v?l?==false) {
				if(PartieV2.partieUnique.jouerjoueur.get(i).witch==true) {
					PartieV2.partieUnique.ajoutPoint(i);
					PartieV2.partieUnique.ajoutPoint(i);
				}
				else{
					PartieV2.partieUnique.ajoutPoint(i);
				}
				PartieV2.partieUnique.jouerjoueur.get(i).setTour(true);
				actuel=i;
			}
			else {PartieV2.partieUnique.jouerjoueur.get(i).setTour(false);
			}
			PartieV2.partieUnique.jouerjoueur.get(i).setEstAccus?(false);
			PartieV2.partieUnique.jouerjoueur.get(i).r?v?l?=false;
			PartieV2.partieUnique.jouerjoueur.get(i).enJeu=true;
			if(PartieV2.partieUnique.jouerjoueur.get(i).points>4) {
				finie = true;
			}
		}
		System.out.println("\nVoici vos scores : ");
		for(int i=0;i<PartieV2.partieUnique.jouerjoueur.size();i++) {
			System.out.println("\nJoueur "+i+" : "+PartieV2.partieUnique.jouerjoueur.get(i).points+" points");
		}
		}
		System.out.println("\nPartie termin?e !");
		System.out.println("\nVoici vos scores : ");
		int idgagnant = 0;
		for(int i=0;i<PartieV2.partieUnique.jouerjoueur.size();i++) {
		System.out.println("\nJoueur "+i+" : "+PartieV2.partieUnique.jouerjoueur.get(i).points+" points");
		if (PartieV2.partieUnique.jouerjoueur.get(i).points>PartieV2.partieUnique.jouerjoueur.get(idgagnant).points) {
			idgagnant=i;
		}
		}
		int[] gagnants = new int[6];
		gagnants[0]=idgagnant;
		int compteur=1;
		boolean egalite=false;
		for(int i=0;i<PartieV2.partieUnique.jouerjoueur.size();i++) {
			if(PartieV2.partieUnique.jouerjoueur.get(i).points==PartieV2.partieUnique.jouerjoueur.get(idgagnant).points && i!=idgagnant) {
				gagnants[compteur]=i;
				compteur++;
				egalite=true;
			}
		}
		if (egalite==true) {
			System.out.println("\nIl y a une ?galit? entre les joueurs :");
			for(int i=0;i<gagnants.length;i++) {
				System.out.println("\n"+gagnants[i]);
			}
			System.out.println("\nD?partagez-vous comme vous le pr?f?rez, pourquoi pas un pierre-feuille-ciseau ?");
		}
		else {
			System.out.println("\nLe gagnant est le joueur : "+idgagnant);
			System.out.println("\nBravo !!!");
		}
     	//partie pour la distribution des cartes, ? modifier en utilisant le td Collections
    	clavier.close();
    }

}
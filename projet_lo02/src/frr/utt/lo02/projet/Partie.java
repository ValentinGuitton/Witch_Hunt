package frr.utt.lo02.projet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Partie {
    private final int nbJoueursPhys;

    private final int nbBots;

    private static Partie partieUnique = null;

    private int nbCartesParJoueur;
//constructeur 
    private Partie(int nbjp,int nbbot, int nbcartes) {
    	this.nbJoueursPhys=nbjp;
    	this.nbBots=nbbot;
    	this.nbCartesParJoueur=nbcartes;
    	
    }
//distribution d'une carte � un joueur
    public void distribuerCartes(Joueur_Physique_ou_Virtuel joueur, Cartes_Rumeurs carte) {
    	joueur.jouer.add(carte);
    }

    public void finirPartie() {
    }
//permet de n'avoir qu'une instanciation de Partie
    public static Partie getInstance(int nbjp, int nbbot, int nbcartes) {
    	if (Partie.partieUnique == null) {
    		Partie.partieUnique = new Partie(nbjp, nbbot, nbcartes);
    	}
    	return Partie.partieUnique;
    }
    public void donnerTour() {
    }

    public void ajoutPoint() {
    }

    public void enleverPoint() {
    }

    public void donnerCarte(final String nom, final int id) {
    }

    public void enleverCarte(final String nom, final int id) {
    }

    public void donnerTour(final int id) {
    }

    public void recupererCarte(final String nom) {
    }
    public static void main(String[] args) {
    //On initialise des variables pour le nombre de joueurs physiques et virtuels, on utilise un scanner pour r�cup�rer les nombres entr�s par l'utilisateur
    //tout en respectant les contraintes max 6 joueurs, au moins un joueur physique et en calculant le nombre de cartes par joueur
    	int nbbot=0;
    	int nbjp=0;
    	int nbcartes=0;
    	Scanner clavier = new Scanner(System.in);
    	while (nbjp<1 || nbjp>6) {
    		System.out.print("Saisir le nombre de joueurs physiques (max 6) : ");
    		nbjp = clavier.nextInt();
    	};
    //choix d'avoir des bots ou non
    	System.out.println("Voulez vous des bots ? (1=OUI ou 0=NON)");
    	int bot = clavier.nextInt();
    	if (bot == 1) {
    	while (nbbot<1 || nbbot>5 || (nbbot+nbjp)>6) {
            System.out.print("Saisir le nombre de joueurs virtuels (max 6 joueurs en tout) : ");
            nbbot = clavier.nextInt();
        };
    	};
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
    	Partie.getInstance(nbjp, nbbot, nbcartes);
    	//Cr�ation de la liste des joueurs
    	List<Joueur_Physique_ou_Virtuel> jouerjoueur = new ArrayList<Joueur_Physique_ou_Virtuel> ();
    	for (int i=0; i<nbbot+nbjp ; i++) {
    		jouerjoueur.add(new Joueur_Physique_ou_Virtuel(false,false,false,i,0));
    	};
    	//Affichage de la liste des joueurs
    	Iterator<Joueur_Physique_ou_Virtuel>it = jouerjoueur.iterator();
    	while (it.hasNext()){
    		System.out.println(it.next());
    	}
    	//Cr�ation de la liste de cartes
    	List<Cartes_Rumeurs> jouercarte = new ArrayList<Cartes_Rumeurs> ();
    	jouercarte.add(new Cartes_Rumeurs("Angry Mob",false,false,0));
     	jouercarte.add(new Cartes_Rumeurs("The Inquisition",false,false,0));
     	jouercarte.add(new Cartes_Rumeurs("Pointed Hat",false,false,0));
     	jouercarte.add(new Cartes_Rumeurs("Hooked Nose",false,false,0));
     	jouercarte.add(new Cartes_Rumeurs("Broomstick",false,false,0));
     	jouercarte.add(new Cartes_Rumeurs("Wart",false,false,0));
     	jouercarte.add(new Cartes_Rumeurs("Ducking Stool",false,false,0));
     	jouercarte.add(new Cartes_Rumeurs("Cauldron",false,false,0));
     	jouercarte.add(new Cartes_Rumeurs("Evil Eye",false,false,0));
     	jouercarte.add(new Cartes_Rumeurs("Toad",false,false,0));
     	jouercarte.add(new Cartes_Rumeurs("Black Cat",false,false,0));
     	jouercarte.add(new Cartes_Rumeurs("Pet Newt",false,false,0));
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

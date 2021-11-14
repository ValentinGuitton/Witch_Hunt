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

    private Partie(int nbjp,int nbbot, int nbcartes) {
    	this.nbJoueursPhys=nbjp;
    	this.nbBots=nbbot;
    	this.nbCartesParJoueur=nbcartes;
    	
    }
    public void distribuerCartes(Joueur_Physique_ou_Virtuel joueur, Cartes_Rumeurs carte) {
    	joueur.jouer.add(carte);
    }

    public void finirPartie() {
    }

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
    	int nbbot=0;
    	int nbjp=0;
    	int nbcartes=0;
    	Scanner clavier = new Scanner(System.in);
    	while (nbjp<1 || nbjp>6) {
    		System.out.print("Saisir le nombre de joueurs physiques (max 6) : ");
    		nbjp = clavier.nextInt();
    	};
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
    			//ajouter la mise à l'écart de 2 cartes
    		}
    		else if (nbbot+nbjp==6) {
    			nbcartes=2;
    		}
        System.out.println("Création d'une partie avec "+ nbjp +" joueur(s) physique(s) et "+ nbbot +" joueur(s) virtuel(s)");
        System.out.println(nbcartes+" cartes vont être distribuées à chaque joueur");
    	Partie.getInstance(nbjp, nbbot, nbcartes);
    	List<Joueur_Physique_ou_Virtuel> jouerjoueur = new ArrayList<Joueur_Physique_ou_Virtuel> ();
    	for (int i=0; i<nbbot+nbjp ; i++) {
    		jouerjoueur.add(new Joueur_Physique_ou_Virtuel(false,false,false,i,0));
    	};
    	Iterator<Joueur_Physique_ou_Virtuel>it = jouerjoueur.iterator();
    	while (it.hasNext()){
    		System.out.println(it.next());
    	}
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
  		Random rand=new Random();
  		int cartestirées[]= {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
  		int compt=0;
  		for (int j=0; j<nbbot+nbjp; j++) {
     	for (int i=0; i<nbcartes ; i++) {
     		int a = rand.nextInt(11);
     		while ( a==cartestirées[0] || a==cartestirées[1] || a==cartestirées[2] || a==cartestirées[3] || a==cartestirées[4] || a==cartestirées[5] || a==cartestirées[6] || a==cartestirées[7] || a==cartestirées[8] || a==cartestirées[9] || a==cartestirées[10]) {
     			a=a+1;
     			if (a==12) {
     				a=0;
     			}
     		}
     		partieUnique.distribuerCartes(jouerjoueur.get(j),jouercarte.get(a));
     		cartestirées[compt]=a;
     		compt++;
     	}
     		//tirage aléatoire d'une variable mise dans get
  	        System.out.println("Le joueur "+jouerjoueur.get(j).getId()+" possède les cartes : ");
         	Iterator<Cartes_Rumeurs>main = Joueur_Physique_ou_Virtuel.jouer.iterator();
            while (main.hasNext()) {
                System.out.println(main.next());	
         	}
     	}
    	clavier.close();
    }

}

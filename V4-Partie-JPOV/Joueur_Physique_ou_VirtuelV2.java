package frr.utt.lo02.projet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Joueur_Physique_ou_VirtuelV2 {
    protected boolean r�v�l�;

    public boolean witch;

    public boolean sonTour;

    private int id;

    private int points;

    public List<Cartes_RumeursV2> main;

    public List<Strat�gie> strat�gie;
    //Constructeur
    public Joueur_Physique_ou_VirtuelV2(boolean rev, boolean witch, boolean sonTour, int id, int pts) {
    	this.r�v�l�=rev;
    	this.witch=witch;
    	this.sonTour=sonTour;
    	this.id=id;
    	this.points=pts;
    	this.main= new ArrayList<Cartes_RumeursV2> ();
    	this.strat�gie = new ArrayList<Strat�gie> ();
    }
    //Affichage des joueurs
    public String toString(){
    	String str = new String ("Num�ro :"+this.id+" Son tour : "+this.sonTour);
    	return str;
    }
    public void accuser(final int id) {
		
    }
    public void revelerRole(final int id) {
    }

    public void quitterRound(final int id) {
    }

    public void choisirRole(final boolean witch) {
    	this.witch=witch;
    }

    public void jouerBot() {
    }
    public int getId() {
    	return this.id;
    }

}
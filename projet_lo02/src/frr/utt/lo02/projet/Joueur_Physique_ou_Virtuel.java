package frr.utt.lo02.projet;
import java.util.ArrayList;
import java.util.List;

public class Joueur_Physique_ou_Virtuel {
    private boolean révélé;

    private boolean witch;

    public boolean sonTour;

    private int id;

    private int points;

    public static List<Cartes_Rumeurs> jouer = new ArrayList<Cartes_Rumeurs> ();

    public List<Stratégie> stratégie = new ArrayList<Stratégie> ();
    
    public Joueur_Physique_ou_Virtuel(boolean rev, boolean witch, boolean sonTour, int id, int pts) {
    	this.révélé=rev;
    	this.witch=witch;
    	this.sonTour=sonTour;
    	this.id=id;
    	this.points=pts;
    }
    public String toString(){
    	String str = new String ("Numéro :"+this.id);
    	return str;
    }
    public void accuser(final int id) {
    }

    public void choisirJoueur(final int id) {
    }

    public void revelerRole(final int id) {
    }

    public void quitterRound(final int id) {
    }

    public void choisirRole(final boolean witch) {
    }

    public void jouerBot() {
    }
    public int getId() {
    	return this.id;
    }

}

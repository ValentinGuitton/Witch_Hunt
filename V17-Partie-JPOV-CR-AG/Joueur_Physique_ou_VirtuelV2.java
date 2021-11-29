package frr.utt.lo02.projet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Joueur_Physique_ou_VirtuelV2 {
    protected boolean r�v�l�;

    public boolean witch;

    public boolean sonTour;
    
    private boolean bot;
    
    private boolean estAccus�;
    
    protected boolean enJeu;

    private int id;

    protected int points;
    
    private boolean gagnant;

    public List<Cartes_RumeursV2> main;
    
    public List<Cartes_RumeursV2> cartesjouees;

    public List<Strat�gie> strat�gie;
    
    public boolean peutEtreAccus� = true;
    //Constructeur
    public Joueur_Physique_ou_VirtuelV2(boolean rev, boolean witch, boolean sonTour,boolean enJeu, int id, int pts , boolean estAccus�, boolean bot) {
    	this.r�v�l�=rev;
    	this.witch=witch;
    	this.sonTour=sonTour;
    	this.enJeu=enJeu;
    	this.bot=bot;
    	this.id=id;
    	this.points=pts;
    	this.main= new ArrayList<Cartes_RumeursV2> ();
    	this.strat�gie = new ArrayList<Strat�gie> ();
    	this.cartesjouees=new ArrayList<Cartes_RumeursV2>();
    }
    //Affichage des joueurs
    public String toString(){
    	String str = new String ("Num�ro :"+this.id+" Son tour : "+this.sonTour);
    	return str;
    }
    public void accuser(final int id) {
    }
    public void setBot (boolean bot) {
    	this.bot = bot;
    }
    public boolean getBot() {
    	return this.bot;
    }
    public boolean getTour() {
    	return this.sonTour;
    }
    public void setTour(boolean tour) {
    	this.sonTour=tour;
    }
    
    public String revelerRole() {
    	this.witch=witch;
    	String role;
    	if(witch==true) role="Sorciere";
    	else role ="Villageois";
    	return role;
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
    public boolean getEstAccus�() {
    	return this.estAccus�;
    }
    public void setEstAccus�(boolean accus�) {
    	this.estAccus�=accus�;
    }
    public void setGagnant(boolean gagne) {
    	this.gagnant=gagne;
    }
    public boolean getGagnant() {
    	return this.gagnant;
    }
    public List<Cartes_RumeursV2> getCartesJouees(){
    	return this.cartesjouees;
    }
}
package frr.utt.lo02.projet.Modele;
import java.util.ArrayList;
import java.util.Observable;
import java.util.List;
import java.util.Scanner;
/**
 * 
 * @author lou prevost, valentin guitton
 *
 */
/**
 * Cette classe concerne l'ensemble des joueurs, qu'ils soient physiques ou virtuels.
 * Elle nous permet de pouvoir consulter ou modifier les différentes caractéristiques des joueurs.
 * Elle hérite de la classe Observable car les joueurs sont observés par l'interface graphique afin que cette dernière s'adapte en fonction des modifications apportées aux joueurs.
 */
public class Joueur_Physique_ou_VirtuelV2 extends Observable{
	/**
	 * Booléen indiquant si l'identité du joueur est révélée
	 */
    protected boolean révélé;
    /**
     * Booléen indiquant si le joueur est une witch ou un villager
     */
    public boolean witch;
    /**
     * Booléen qui indique si c'est au tour du joueur
     */
    public boolean sonTour;
    /**
     * Booléen qui permet de savoir si le joueur est un bot ou un joueur physique
     */
    private boolean bot;
    /**
     * Booléen qui indique si un joueur est accusé
     */
    private boolean estAccusé;
    /**
     * Booléen qui indique si un joueur est encore en jeu
     */
    protected boolean enJeu;
    /**
     * Identifiant du joueur
     */
    private int id;
    /**
     * Nombre de points du joueur
     */
    protected int points;
    /**
     * Indique si le joueur veut voir sa main(utilisé pour l'interface graphique quand un joueur accusé veut voir sa main)
     */
    private boolean regardeMain=false;
    /**
     * Liste des cartes que le joueur a dans sa main
     */
    public List<Cartes_RumeursV2> main;
    /**
     * Liste des cartes que le joueur a joué
     */
    public List<Cartes_RumeursV2> cartesjouees;
    /**
     * Booléen qui indique si un joueur peut être accusé, cet attribut est notamment changé quand l'effet de la carte Evil Eye est utilisé
     */
    public boolean peutEtreAccusé = true;
    //Constructeur
    /**
     * 
     * @param rev
     * @param witch
     * @param sonTour
     * @param enJeu
     * @param id
     * @param pts
     * @param estAccusé
     * @param bot
     * */
    /**
     * Constructeur qui assure la création d'un joueur
     */
    public Joueur_Physique_ou_VirtuelV2(boolean rev, boolean witch, boolean sonTour,boolean enJeu, int id, int pts , boolean estAccusé, boolean bot) {
    	this.révélé=rev;
    	this.witch=witch;
    	this.sonTour=sonTour;
    	this.enJeu=enJeu;
    	this.bot=bot;
    	this.id=id;
    	this.points=pts;
    	this.main= new ArrayList<Cartes_RumeursV2> ();
    	this.cartesjouees=new ArrayList<Cartes_RumeursV2>();
    }
    //Affichage des joueurs
    /**
     * Affichage des joueurs
     */
    public String toString(){
    	String str = new String ("Numéro :"+this.id+" Son tour : "+this.sonTour);
    	return str;
    }
    /**
     * 
     * @return main
     */
    /**
     * Retourne la main du joueur
     */
    public List<Cartes_RumeursV2> getMain(){
    	return this.main;
    }
    /**
     * 
     * @param reg
     */
    /**
     * Définit si un joueur veut ou non regarder sa main
     */
    public void setRegardeMain(boolean reg) {
    	this.regardeMain=reg;
    	this.setChanged();
    	this.notifyObservers();
    }
    /**
     * 
     * @return regardeMain
     */
    /**
     * Permet de savoir si un joueur accusé a décidé de regarder sa main en mode interface graphique
     */
    public boolean getRegardeMain() {
    	return this.regardeMain;
    }
    /**
     * 
     * @param rev
     */
    /**
     * Définir si un joueur a révélé son identité
     */
    public void setReveler (boolean rev) {
    	this.révélé=rev;
    }
    /**
     * 
     * @return révélé
     */
    /**
     * Permet de savoir si l'identité d'un joueur a été révélée 
     */
    public boolean getReveler() {
    	return this.révélé;
    }
    /**
     * 
     * @param enjeu
     */
    /**
     * Définir si un joueur est encore en jeu
     */
    public void setEnJeu (boolean enjeu) {
    	this.enJeu=enjeu;
    }
    /**
     * 
     * @return enJeu
     */
    /**
     * 
     * Permet de savoir si un joueur est encore en jeu
     */
    public boolean getEnJeu() {
    	return this.enJeu;
    }
    /**
     * 
     * @param bot
     */
    /**
     * 
     * Permet de définir si un joueur est un bot
     */
    public void setBot (boolean bot) {
    	this.bot = bot;
    }
    /**
     * 
     * @return bot
     */
    /**
     * 
     * Permet de savoir si un joueur est un bot
     */
    public boolean getBot() {
    	return this.bot;
    }
    /**
     * 
     * @return sonTour
     */
    /**
     * 
     * Permet de savoir si c'est le tour du joueur
     */
    public boolean getTour() {
    	return this.sonTour;
    }
    /**
     * 
     * @param tour
     */
    /**
     * Permet de définir si c'est le tour d'un joueur
     */
    public void setTour(boolean tour) {
    	this.sonTour=tour;
    }
    /**
     * 
     * @return string
     */
    /**
     * Renvoie une chaîne de caractères correspondant au rôle du joueur
     */
    public String revelerRole() {
    	this.witch=witch;
    	String role;
    	if(witch==true) role="Sorciere";
    	else role ="Villageois";
    	return role;
    }
    /**
     * 
     * @param witch
     */
    /**
     * Permet à un joueur de choisir son rôle
     */
    public void choisirRole(final boolean witch) {
    	this.witch=witch;
    }
    /**
     * 
     * @return id
     */
    /**
     * 
     * Retourne l'id d'un joueur
     */
    public int getId() {
    	return this.id;
    }
    /**
     * 
     * @return estAccusé
     */
    /**
     * 
     * Permet de savoir si un joueur est accusé
     */
    public boolean getEstAccusé() {
    	return this.estAccusé;
    }
    /**
     * 
     * @param accusé
     */
    /**
     * 
     * Permet de définir si un joueur est accusé
     */
    public void setEstAccusé(boolean accusé) {

    	this.estAccusé=accusé;
    	this.setChanged();
    	this.notifyObservers();
    }
    /**
     * 
     * @param role
     */
    /**
     * 
     * Permet de définir le rôle d'un joueur
     */
    public void setRole(boolean role) {
    	this.witch=role;
    	this.setChanged();
    	this.notifyObservers();
    }
    /**
     * 
     * @return witch
     */
    /**
     * 
     * Permet de récupérer le rôle d'un joueur
     */
    public boolean getRole() {
    	return this.witch;
    }
    /**
     * 
     * @return cartesjouees
     */
    /**
     * 
     * Permet de récupérer la liste des cartes jouées par le joueur
     */
    public List<Cartes_RumeursV2> getCartesJouees(){
    	return this.cartesjouees;
    }
    /**
     * 
     * @return points
     */
    /**
     * Permet de récupérer le nombre de points d'un joueur 
     */
    public int getPoints() {
    	return this.points;
    }
}
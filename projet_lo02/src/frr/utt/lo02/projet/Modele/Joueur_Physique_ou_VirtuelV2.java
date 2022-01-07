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
 * Elle nous permet de pouvoir consulter ou modifier les diff�rentes caract�ristiques des joueurs.
 * Elle h�rite de la classe Observable car les joueurs sont observ�s par l'interface graphique afin que cette derni�re s'adapte en fonction des modifications apport�es aux joueurs.
 */
public class Joueur_Physique_ou_VirtuelV2 extends Observable{
	/**
	 * Bool�en indiquant si l'identit� du joueur est r�v�l�e
	 */
    protected boolean r�v�l�;
    /**
     * Bool�en indiquant si le joueur est une witch ou un villager
     */
    public boolean witch;
    /**
     * Bool�en qui indique si c'est au tour du joueur
     */
    public boolean sonTour;
    /**
     * Bool�en qui permet de savoir si le joueur est un bot ou un joueur physique
     */
    private boolean bot;
    /**
     * Bool�en qui indique si un joueur est accus�
     */
    private boolean estAccus�;
    /**
     * Bool�en qui indique si un joueur est encore en jeu
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
     * Indique si le joueur veut voir sa main(utilis� pour l'interface graphique quand un joueur accus� veut voir sa main)
     */
    private boolean regardeMain=false;
    /**
     * Liste des cartes que le joueur a dans sa main
     */
    public List<Cartes_RumeursV2> main;
    /**
     * Liste des cartes que le joueur a jou�
     */
    public List<Cartes_RumeursV2> cartesjouees;
    /**
     * Bool�en qui indique si un joueur peut �tre accus�, cet attribut est notamment chang� quand l'effet de la carte Evil Eye est utilis�
     */
    public boolean peutEtreAccus� = true;
    //Constructeur
    /**
     * 
     * @param rev
     * @param witch
     * @param sonTour
     * @param enJeu
     * @param id
     * @param pts
     * @param estAccus�
     * @param bot
     * */
    /**
     * Constructeur qui assure la cr�ation d'un joueur
     */
    public Joueur_Physique_ou_VirtuelV2(boolean rev, boolean witch, boolean sonTour,boolean enJeu, int id, int pts , boolean estAccus�, boolean bot) {
    	this.r�v�l�=rev;
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
    	String str = new String ("Num�ro :"+this.id+" Son tour : "+this.sonTour);
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
     * D�finit si un joueur veut ou non regarder sa main
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
     * Permet de savoir si un joueur accus� a d�cid� de regarder sa main en mode interface graphique
     */
    public boolean getRegardeMain() {
    	return this.regardeMain;
    }
    /**
     * 
     * @param rev
     */
    /**
     * D�finir si un joueur a r�v�l� son identit�
     */
    public void setReveler (boolean rev) {
    	this.r�v�l�=rev;
    }
    /**
     * 
     * @return r�v�l�
     */
    /**
     * Permet de savoir si l'identit� d'un joueur a �t� r�v�l�e 
     */
    public boolean getReveler() {
    	return this.r�v�l�;
    }
    /**
     * 
     * @param enjeu
     */
    /**
     * D�finir si un joueur est encore en jeu
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
     * Permet de d�finir si un joueur est un bot
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
     * Permet de d�finir si c'est le tour d'un joueur
     */
    public void setTour(boolean tour) {
    	this.sonTour=tour;
    }
    /**
     * 
     * @return string
     */
    /**
     * Renvoie une cha�ne de caract�res correspondant au r�le du joueur
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
     * Permet � un joueur de choisir son r�le
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
     * @return estAccus�
     */
    /**
     * 
     * Permet de savoir si un joueur est accus�
     */
    public boolean getEstAccus�() {
    	return this.estAccus�;
    }
    /**
     * 
     * @param accus�
     */
    /**
     * 
     * Permet de d�finir si un joueur est accus�
     */
    public void setEstAccus�(boolean accus�) {

    	this.estAccus�=accus�;
    	this.setChanged();
    	this.notifyObservers();
    }
    /**
     * 
     * @param role
     */
    /**
     * 
     * Permet de d�finir le r�le d'un joueur
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
     * Permet de r�cup�rer le r�le d'un joueur
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
     * Permet de r�cup�rer la liste des cartes jou�es par le joueur
     */
    public List<Cartes_RumeursV2> getCartesJouees(){
    	return this.cartesjouees;
    }
    /**
     * 
     * @return points
     */
    /**
     * Permet de r�cup�rer le nombre de points d'un joueur 
     */
    public int getPoints() {
    	return this.points;
    }
}
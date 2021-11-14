package frr.utt.lo02.projet;
import java.util.ArrayList;
import java.util.List;

public class Partie {
    private int nbJoueursPhys;

    private int nbBots;

    private static Partie partieUnique;

    private int nbCartesParJoueur;

    public List<Joueur_Physique_ou_Virtuel> jouerjoueur = new ArrayList<Joueur_Physique_ou_Virtuel> ();

    public List<Cartes_Rumeurs> jouercarte = new ArrayList<Cartes_Rumeurs> ();

    public void mélangerCartes() {
    }

    public void distribuerCartes(final int nbCartesParJoueur) {
    }

    public void finirPartie() {
    }

    public static void getInstance() {
    }

    private void constructPartie() {
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

}

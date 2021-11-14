package frr.utt.lo02.projet;
import java.util.ArrayList;
import java.util.List;

public class Joueur_Physique_ou_Virtuel {
    private boolean révélé;

    private boolean witch;

    public boolean sonTour;

    private int id;

    private int points;

    public List<Cartes_Rumeurs> jouer = new ArrayList<Cartes_Rumeurs> ();

    public List<Stratégie> stratégie = new ArrayList<Stratégie> ();

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

}

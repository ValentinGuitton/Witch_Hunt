package frr.utt.lo02.projet.Modele;
/**
 * 
 * @author lou prevost, valentin guitton
 */
/**
 * L'interface Strat�gie n'est pas impl�ment�e car nous n'avons pas eu le temps de faire 2 strat�gies de bots diff�rentes.
 * Comme nous n'avons qu'un bot agressif, l'interface Strat�gie n'est pas n�cessaire.
 * Si nous avions plusieurs strat�gies, cette interface nous aurait permis d'attribuer diff�rentes strat�gies de jeu � diff�rents bots
 */
public interface Strat�gie {
    void jouerBot();

}

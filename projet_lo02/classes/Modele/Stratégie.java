package frr.utt.lo02.projet.Modele;
/**
 * 
 * @author lou prevost, valentin guitton
 */
/**
 * L'interface Stratégie n'est pas implémentée car nous n'avons pas eu le temps de faire 2 stratégies de bots différentes.
 * Comme nous n'avons qu'un bot agressif, l'interface Stratégie n'est pas nécessaire.
 * Si nous avions plusieurs stratégies, cette interface nous aurait permis d'attribuer différentes stratégies de jeu à différents bots
 */
public interface Stratégie {
    void jouerBot();

}

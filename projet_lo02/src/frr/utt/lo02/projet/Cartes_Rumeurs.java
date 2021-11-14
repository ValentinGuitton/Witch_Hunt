package frr.utt.lo02.projet;

public class Cartes_Rumeurs {
    public String nom;

    private boolean effetHunt;

    public boolean utilisee;

    public int idDetenteur;

  //  public EffetWitch ;

//    public EffetHunt ;
    
    public Cartes_Rumeurs(String nom,boolean hunt, boolean util, int iddet) {
    	this.nom=nom;
    	this.effetHunt=hunt;
    	this.utilisee=util;
    	this.idDetenteur=iddet;
    }
    public void jouerCarte(final String nom, final boolean hunt) {
    }

    public void afficherDescription(final String nom) {
    }

}

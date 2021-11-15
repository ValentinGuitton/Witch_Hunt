package frr.utt.lo02.projet;

public class Cartes_RumeursV2 {
    public String nom;

    private boolean effetHunt;

    public boolean utilisee;

    public int idDetenteur;

  //  public EffetWitch ;

//    public EffetHunt ;
    //Constructeur
    public Cartes_RumeursV2(String nom,boolean hunt, boolean util, int iddet) {
    	this.nom=nom;
    	this.effetHunt=hunt;
    	this.utilisee=util;
    	this.idDetenteur=iddet;
    }
    //Affichage des cartes
    public String toString() {
    	String str = new String ("nom : "+this.nom);
    	return str;
    }
    public void jouerCarte(final String nom, final boolean hunt) {
    }

    public void afficherDescription(final String nom) {
    	if (nom=="Angry Mob") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Seulement utilisable si votre identité a été révélée et que vous êtes un villageois\nRévelez l'identité d'un autre joueur, si c'est une sorcière vous gagnez 2 points et prenez le prochain tour\n sinon vous perdez 2 points et la personne désignée prend le tour\n\nWitch : Prenez le tour");
    	}
    	else if (nom=="Pointed Hat") {
    		System.out.println("\n\nCarte : "+nom+"\nCette carte n'est jouable que si vous avez déjà une carte rumeur révélée\nHunt : Récupérez une de vos cartes rumeurs révélées et choisissez le prochain joueur\nWitch : Récupérez une de vos cartes rumeurs révélées et prenez le prochain tour");
    	}
    	else if (nom=="The Inquisition") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Seulement utilisable si votre identité a été révélée et que vous êtes un villageois\nChoississez un joueur, il prend le prochain tour mais avant cela, vous pouvez regarder son identité\nWitch : Supprimez une carte de votre main et prenez le prochain tour");	
    	}
    	else if (nom=="Hooked Nose") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Choisissez le prochain joueur, avant son tour prener une carte aléatoire de sa main et mettez la dans votre main\nWitch : Prenez une carte de la main du joueur qui vous a accusé, prenez le prochain tour");
    	}
    	else if (nom=="Broomstick") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Choisir le prochain joueur \nEffet Witch : Prendre le prochain tour");
    	}
    	else if (nom=="Wart") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Choisir le prochain joueur \nEffet Witch : Prendre le prochain tour ");
    	}
    	else if (nom=="Ducking Stool") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Choisir un joueur. Il doit révéler son identité ou se défausser d'une carte de sa main\nSi c'est une Witch : Vous gagnez 1 point et prenez le tour\nSi c'est un villageois : vous perdez 1 point et il prend le tour\n Si il se défausse d'une carte : il prend le tour\n\nEffet Witch : Choisissez le prochain joueur");
    	}
    	else if (nom=="Cauldron") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Révelez votre identité\nSi vous êtes une sorcière : le joueur à votre gauche (id+1) prend le prochain tour\nSi vous êtes un villageois : Choisissez le prochain joueur\n\nEffet Witch : Le joueur qui vous a accusé se défausse d'une carte de sa main au hasard et vous prenez le prochain tour");
    	}
    	else if (nom=="Evil Eye") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Choisissez le prochain joueur. Pendant son tour il ne doit pas vous accusez si c'est possible\nEffet Witch : Choisissez le prochain joueur. Pendant son tour il ne doit pas vous accusez si c'est possible");
    	}
    	else if (nom=="Toad") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Révelez votre identité\nVous êtes une sorcière : le joueur à votre gauche (id+1) prend le prochain tour\nVous êtes un villageois : vous choisissez le prochain joueur\n\nEffet Witch : Prenez le prochain tour");
    	}
    	else if (nom=="Black Cat") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Ajoutez une carte défaussée à votre main et défaussez vous de cette carte puis prenez le prochain tour\nEffet Witch : Prenez le prochain tour");
    	}
    	else if (nom=="Pet Newt") {
    		System.out.println("\n\nCarte : "+nom+"\nEffet Hunt : Prenez une carte rumeur révélée de n'importe quel autre joueur et placez la dans votre main puis choisissez le prochain joueur\nEffet Witch : Prenez le prochain tour");
    	}
    }

}

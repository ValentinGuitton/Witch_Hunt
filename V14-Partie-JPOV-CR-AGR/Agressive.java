package frr.utt.lo02.projet;

public abstract class Agressive implements Strat�gie {
    public static void jouerBot(Joueur_Physique_ou_VirtuelV2 joueur) {
    	if(joueur.getTour()==true && joueur.getBot()==true) {
    		if(joueur.getEstAccus�()==true) {
    			System.out.println("Le bot n�" + joueur.getId() + " est accus�"); // ACCUSATION SUR LE BOT
    			if(joueur.main.size()==0) {
    				System.out.println("\nBot n�"+joueur.getId()+" vous n'avez plus de cartes rumeur, votre identit� va �tre r�v�l�e");
    				if(joueur.revelerRole()=="Sorciere") {
    					String role = "Sorciere";
    					System.out.println("\nLe r�le du bot n�"+joueur.getId()+" est : " + role);
    				//  AJOUT POINT joueur qui a accus� + sortie du round. 
    				}
    				else {
    					String role = "Villageois";
    					System.out.println("\nLe r�le du bot n�"+joueur.getId()+" est : " + role);
    					//  AJOUT POINT joueur qui a accus�
    				}
    				
    				
    			}
    			else {
    				System.out.println("\n\nVous avez d�cid� de jouer une carte rumeur");
    	    		String nomcarte=null;
    	    		String nomc = null;
    	    		boolean app= false;
    	    		String nom ="0";
    	    		short bufferClavier=0;
    				if(bufferClavier==0) {  // Vide la premiere ligne 1 fois seulement pour ne pas sauter la ligne nomc = clavier.nextLine();
    		    		
    		    		bufferClavier=1;
    		    		}
    	    		while (app ==false) {
    	    			String debug = "";
    	    		}
    				int idchoisie = PartieV2.trouveIndexMain(joueur.getId(),nomcarte);
    				PartieV2.jouerjoueur.get(joueur.getId()).main.get(idchoisie).jouerCarte(nomcarte,false,joueur.getId(),joueur.getId());
    				PartieV2.enleverCarte(idchoisie, joueur.getId());
    			}
    		}
    		
    		
    	}
    	
    }

}
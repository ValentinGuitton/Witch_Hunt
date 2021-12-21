package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import frr.utt.lo02.projet.Vue.*;
import javax.swing.*;

import frr.utt.lo02.projet.Modele.*;

public class ControleurPartie {

public ControleurPartie(IG_witch_hunt igraph,PartieV2 partie, JButton btn, JSpinner spinner1, JSpinner spinner2, JButton btn1erJ, JSpinner spinner3, JSpinner chxj, JSpinner role, JButton chxjrole, JButton btnAccuser, JSpinner spinnerAcc, JButton btnRevId, JButton btnEffWitch, JButton btnmain, JSpinner spinnerCibleWitch, JSpinner spinnerChoixCarte, JSpinner spinnerInq, JButton btnrécup, JButton btnrécuphooked){
	System.out.println("On est ds le controleur");
	btn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Il y a eu un appui");
			if((Integer)(spinner1.getValue())!=1 || (Integer)(spinner2.getValue())!=0) {
				partie.setnbJoueursPhys((Integer)(spinner1.getValue()));
				partie.setnbBots((Integer)(spinner2.getValue()));
				System.out.println("Joueurs physiques : "+partie.getnbJoueursPhys()+" Joueurs virtuels : "+partie.getnbBots());
				if (partie.getnbBots()+partie.getnbJoueursPhys()==3) {
					partie.setNbCartesParJoueur(4);
		    	}
		    		else if (partie.getnbBots()+partie.getnbJoueursPhys()==4) {
		    			partie.setNbCartesParJoueur(3);
		    		}
		    		else if (partie.getnbBots()+partie.getnbJoueursPhys()==5) {
		    			partie.setNbCartesParJoueur(2);
		    			//ajouter la mise à l'écart de 2 cartes
		    		}
		    		else if (partie.getnbBots()+partie.getnbJoueursPhys()==6) {
		    			partie.setNbCartesParJoueur(2);
		    		}
		    	//Création de la partie
		        System.out.println(partie.getNbCartesParJoueur()+" cartes vont être distribuées à chaque joueur");
		        //partie.getInstance(partie.getnbJoueursPhys(), partie.getnbBots(), partie.getNbCartesParJoueur());
		    	//Création de la liste des joueurs
		    	for (int i=0; i<partie.getnbBots() ; i++) {
		    		partie.ajouterUnJoueur(new Joueur_Physique_ou_VirtuelV2(false,false,false,true,i,0,false,true));
		    	}
		    	for (int i=partie.getnbBots(); i<partie.getnbBots()+partie.getnbJoueursPhys() ; i++) {
		    		partie.ajouterUnJoueur(new Joueur_Physique_ou_VirtuelV2(false,false,false,true,i,0,false,false));
		    	}
		    	partie.creationJeu();
		    	
		    	partie.melanger();
		    	int compt=0;
		    	for ( int j=0; j<partie.getnbBots()+partie.getnbJoueursPhys();j++) {
		    		for (int i=compt; i<partie.getNbCartesParJoueur()+compt; i++) {
		    			partie.getJouerJoueur2().get(j).main.add(partie.getJouerCarte2().get(i));
		    		}
		    		compt=compt+partie.getNbCartesParJoueur();
		    		Iterator<Cartes_RumeursV2> it1 = partie.getJouerJoueur2().get(j).main.iterator();
		    		while(it1.hasNext()) {
		    			System.out.println(it1.next());
		    		}
		    	}
		    	if (partie.getnbBots()+partie.getnbJoueursPhys() == 5) {
		    		partie.getCartesDefaussées().add(partie.getJouerCarte2().get(10));
		    		partie.getCartesDefaussées().add(partie.getJouerCarte2().get(11));
		    		/*partie.cartesup1=partie.getJouerCarte().get(10);
		    		partie.cartesup2=partie.getJouerCarte().get(11);*/
		    	}
		    	for (int i = 0; i < partie.getJouerCarte2().size(); i++) {
		    		System.out.println("On addObserver pour cartes");
					Cartes_RumeursV2 c =partie.getJouerCarte2().get(i);
					c.addObserver(igraph);
					}
		    	 for (int i = 0; i < partie.getJouerJoueur2().size(); i++) {
		 			System.out.println("Les joueurs observent");
		 			partie.getJouerJoueur2().get(i).addObserver(igraph);
		 			}
			}
		}
	});
	btn1erJ.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Il y a eu un appui sur Sélectionner");
			partie.setActuel((Integer)(spinner3.getValue()));
	}
	});
	chxjrole.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Il y a eu un appui sur choix du rôle");
			if((String)(role.getValue())=="Witch") {
				System.out.println("Vous êtes une witch");
				partie.getJouerJoueur().get((Integer)(chxj.getValue())).setRole(true);
				System.out.println("Là ça bug pas");
			}
			else {
				System.out.println("Vous êtes un villager");
				partie.getJouerJoueur().get((Integer)(chxj.getValue())).setRole(false);
			}
	}
	});
	btnAccuser.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Il y a eu un appui sur accuser");
			if((Integer)(spinnerAcc.getValue()) != partie.getActuel() && partie.getJouerJoueur().get((Integer)(spinnerAcc.getValue())).getReveler()==false && partie.getJouerJoueur().get((Integer)(spinnerAcc.getValue())).peutEtreAccusé==true && partie.getJouerJoueur().get((Integer)(spinnerAcc.getValue())).getEnJeu()==true && partie.getJouerJoueur().get((Integer)(spinnerAcc.getValue())).getBot()==false) {
				System.out.println(partie.getJouerJoueur().get((Integer)(spinnerAcc.getValue()))+" est accusé");
				for (int i=0;i<partie.getJouerJoueur().size();i++) {
					if(partie.getJouerJoueur().get(i).peutEtreAccusé==false) {
						partie.getJouerJoueur().get(i).peutEtreAccusé=true;
					}
				}
				 partie.setAccusé((Integer)(spinnerAcc.getValue()));
				 partie.getJouerJoueur().get((Integer)(spinnerAcc.getValue())).setEstAccusé(true);
				
				 System.out.println("On a rendu le joueur "+partie.getJouerJoueur().get((Integer)(spinnerAcc.getValue()))+" accusé");
				 System.out.println("Le joueur accusé est "+partie.getAccusé());
			}
	}
	});
	btnRevId.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Il y a eu un appui sur révéler id");
			partie.getJouerJoueur().get(partie.getAccusé()).setReveler(true);
			System.out.println("On est avant le if de villager");
			if(partie.getJouerJoueur().get(partie.getAccusé()).getRole()==false) {
				System.out.println("Le joueur actuel est le joueur "+partie.getActuel());
				partie.setActuel(partie.getAccusé());
				System.out.println("Le joueur actuel après la modif est le joueur "+partie.getActuel());
			}
			partie.setComptRévélés(partie.getComptRévélés()+1);
	}
	});
	btnmain.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Appui sur le bouton main");
			partie.getJouerJoueur().get(partie.getAccusé()).setRegardeMain(true);
	}
	});
	btnEffWitch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Appui sur le bouton effet witch");
			String theinquisition ="The Inquisition";
			String pointedhat = "Pointed Hat";
			String duckingstool = "Ducking Stool";
			IG_witch_hunt.valueJSpinnerInq=(Integer)spinnerInq.getValue();
			if((Integer)spinnerCibleWitch.getValue() != partie.getAccusé() && partie.getJouerJoueur().get((Integer)spinnerCibleWitch.getValue()).getEnJeu()==true) {
				if((partie.getJouerJoueur().get(partie.getAccusé()).getMain().get(((Integer)spinnerChoixCarte.getValue())).nom.equalsIgnoreCase(theinquisition) && (Integer)spinnerChoixCarte.getValue()!=(Integer)spinnerInq.getValue()) || partie.getJouerJoueur().get(partie.getAccusé()).getMain().get(((Integer)spinnerChoixCarte.getValue())).nom.equalsIgnoreCase(theinquisition)==false) {
					if(partie.getJouerJoueur().get(partie.getAccusé()).getMain().get(((Integer)spinnerChoixCarte.getValue())).nom.equalsIgnoreCase(pointedhat) && (partie.getJouerJoueur().get(partie.getAccusé()).getCartesJouees().size()!=0) || partie.getJouerJoueur().get(partie.getAccusé()).getMain().get(((Integer)spinnerChoixCarte.getValue())).nom.equalsIgnoreCase(pointedhat)==false) {
						if((partie.getJouerJoueur().get(partie.getAccusé()).getMain().get(((Integer)spinnerChoixCarte.getValue())).nom.equalsIgnoreCase(duckingstool) && partie.getAccusé()!=(Integer)spinnerCibleWitch.getValue() && partie.getJouerJoueur().get(partie.getActuel()).getMain().size()!=0) || partie.getJouerJoueur().get(partie.getAccusé()).getMain().get(((Integer)spinnerChoixCarte.getValue())).nom.equalsIgnoreCase(duckingstool)==false) {
						System.out.println("Le joueur "+partie.getAccusé()+" utilise la carte "+partie.getJouerJoueur().get(partie.getAccusé()).getMain().get(((Integer)spinnerChoixCarte.getValue())).nom);
						partie.getJouerJoueur().get(partie.getAccusé()).getMain().get(((Integer)spinnerChoixCarte.getValue())).jouerCarte(partie.getJouerJoueur().get(partie.getAccusé()).getMain().get(((Integer)spinnerChoixCarte.getValue())).nom, false, partie.getAccusé(), partie.getActuel());
						int idchoisie = partie.trouveIndexMain(partie.getAccusé(),partie.getJouerJoueur().get(partie.getAccusé()).getMain().get(((Integer)spinnerChoixCarte.getValue())).nom);
						PartieV2.enleverCarte(idchoisie,partie.getAccusé());
	}
					}
				}
			}
		}
	});
	btnrécup.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Appui sur le bouton Récup pointed");
			int num = -1;
			for (int i=0;i<partie.getJouerJoueur().get(partie.getAccusé()).getCartesJouees().size();i++) {
				if ((partie.getJouerJoueur().get(partie.getAccusé()).getCartesJouees().get(i).nom).equalsIgnoreCase((String)IG_witch_hunt.spinner_pointedh.getValue())) {
					num = i;
				}
			}
					//partie.trouveIndexMain(partie.getAccusé(), (String)IG_witch_hunt.spinner_pointedh.getValue());
			partie.getJouerJoueur().get(partie.getAccusé()).main.add(partie.getJouerJoueur().get(partie.getAccusé()).cartesjouees.get(num));
			for (int i=0;i<partie.getCartesDefaussées().size();i++) {
				if ((partie.getCartesDefaussées().get(i).nom).equalsIgnoreCase(partie.getJouerJoueur().get(partie.getAccusé()).cartesjouees.get(num).nom)) {
					partie.getCartesDefaussées().remove(partie.getCartesDefaussées().get(i));
				}
	    }
			partie.getJouerJoueur().get(partie.getAccusé()).cartesjouees.remove(partie.getJouerJoueur().get(partie.getAccusé()).cartesjouees.get(num));
			partie.donnerTour(partie.getAccusé());
			IG_witch_hunt.btnrécup.setEnabled(false);
		}
	});
	btnrécuphooked.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Appui sur le bouton Récup hooked");
			int num = -1;
			for (int i=0;i<partie.getJouerJoueur().get(partie.getActuel()).getMain().size();i++) {
				if ((partie.getJouerJoueur().get(partie.getActuel()).getMain().get(i).nom).equalsIgnoreCase((String)IG_witch_hunt.spinner_pointedh.getValue())) {
					num = i;
				}
			}
			partie.getJouerJoueur().get(partie.getAccusé()).main.add(partie.getJouerJoueur().get(partie.getActuel()).getMain().get(num));
			for (int i=0;i<partie.getCartesDefaussées().size();i++) {
				if ((partie.getCartesDefaussées().get(i).nom).equalsIgnoreCase(partie.getJouerJoueur().get(partie.getActuel()).getMain().get(num).nom)) {
					partie.getCartesDefaussées().remove(partie.getCartesDefaussées().get(i));
				}
	    }
			partie.getJouerJoueur().get(partie.getActuel()).getMain().remove(partie.getJouerJoueur().get(partie.getActuel()).getMain().get(num));
			partie.donnerTour(partie.getAccusé());
			IG_witch_hunt.btnrécuphooked.setEnabled(false);
		}
	});
}
public static void main(String[] args) {
}
}

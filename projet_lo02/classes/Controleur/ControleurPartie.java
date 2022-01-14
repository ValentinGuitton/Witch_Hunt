package frr.utt.lo02.projet.Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import frr.utt.lo02.projet.Vue.*;
import javax.swing.*;

import frr.utt.lo02.projet.Modele.*;
/**
 * 
 * @author lou prevost, valentin guitton
 *
 */
/**
 * La classe contrôleur récupère les inputs faits au niveau de l'interface graphique IG_witch_hunt et agit de façons différentes sur les modèles en fonction de l'input.
 * Il fait donc le lien entre la vue et le modèle et la modification entrainée sur le modèle entrainera une modification de la vue.
 * On retrouve dans cette classe plusieurs actionPerformed qui correspondent aux actions précises déclenchées par l'appui sur le bouton spécifié.
 */
public class ControleurPartie {
/**
 * 
 * @param igraph
 * @param partie
 * @param btn
 * @param spinner1
 * @param spinner2
 * @param btn1erJ
 * @param spinner3
 * @param chxj
 * @param role
 * @param chxjrole
 * @param btnAccuser
 * @param spinnerAcc
 * @param btnRevId
 * @param btnEffWitch
 * @param btnmain
 * @param spinnerCibleWitch
 * @param spinnerChoixCarte
 * @param spinnerInq
 * @param btnrécup
 * @param btnrécuphooked
 * @param btnEffetHunt
 * @param spinnerCibleHunt
 * @param spinnerCarteHunt
 * @param btncacher
 * @param btnRevIdDuck
 * @param btnSuppDuck
 * @param spinnerSuppDuck
 * @param btnrecupBlackCat
 * @param btnRecupPet
 */
	/**
	 * Tout ce qu'il se passe dans le controleur se passe dans ce constructeur.
	 * Le controleur reçoit en paramètres différents boutons qu'il écoute et à chaque appui sur un bouton, différentes actions précisées dans actionPerformed(ActionEvent e){} sont réalisées.
	 * On envoie aussi des spinners dans les paramètres du constructeur car les valeurs de ces spinners permettront de savoir par exemple quel joueur est visé par un effet ou quel joueur est le plus jeune, ou encore quelle carte souhaite récupérer un joueur.
	 */
public ControleurPartie(IG_witch_hunt igraph,PartieV2 partie, JButton btn, JSpinner spinner1, JSpinner spinner2, JButton btn1erJ, JSpinner spinner3, JSpinner chxj, JSpinner role, JButton chxjrole, JButton btnAccuser, JSpinner spinnerAcc, JButton btnRevId, JButton btnEffWitch, JButton btnmain, JSpinner spinnerCibleWitch, JSpinner spinnerChoixCarte, JSpinner spinnerInq, JButton btnrécup, JButton btnrécuphooked, JButton btnEffetHunt, JSpinner spinnerCibleHunt, JSpinner spinnerCarteHunt, JButton btncacher, JButton btnRevIdDuck ,JButton btnSuppDuck , JSpinner spinnerSuppDuck, JButton btnrecupBlackCat, JButton btnRecupPet){
	/**
	 * Détecte un appui sur le bouton "Commencer" et créé la partie avec le nombre de joueurs virtuels et physiques demandés et créé également le jeu de carte puis distribue les cartes.
	 */
	btn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if((Integer)(spinner1.getValue())!=1 || (Integer)(spinner2.getValue())!=0) {
				partie.setnbJoueursPhys((Integer)(spinner1.getValue()));
				partie.setnbBots((Integer)(spinner2.getValue()));
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
		    	}
		    	for (int i = 0; i < partie.getJouerCarte2().size(); i++) {
					Cartes_RumeursV2 c =partie.getJouerCarte2().get(i);
					c.addObserver(igraph);
					}
		    	 for (int i = 0; i < partie.getJouerJoueur2().size(); i++) {
		 			partie.getJouerJoueur2().get(i).addObserver(igraph);
		 			}
			}
		}
	});
	btn1erJ.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			partie.setActuel((Integer)(spinner3.getValue()));
	}
	});
	chxjrole.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if((String)(role.getValue())=="Witch") {
				partie.getJouerJoueur().get((Integer)(chxj.getValue())).setRole(true);
			}
			else {
				partie.getJouerJoueur().get((Integer)(chxj.getValue())).setRole(false);
			}
	}
	});
	btnAccuser.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			partie.setAccusé((Integer)(spinnerAcc.getValue()));
			if(partie.getAccusé()>=0 && partie.getAccusé()<(partie.getnbBots()+partie.getnbJoueursPhys())) {
				if((Integer)(spinnerAcc.getValue()) != partie.getActuel() && partie.getJouerJoueur().get((Integer)(spinnerAcc.getValue())).getReveler()==false && partie.getJouerJoueur().get((Integer)(spinnerAcc.getValue())).peutEtreAccusé==true && partie.getJouerJoueur().get((Integer)(spinnerAcc.getValue())).getEnJeu()==true && partie.getJouerJoueur().get((Integer)(spinnerAcc.getValue())).getBot()==false) {
				for (int i=0;i<partie.getJouerJoueur().size();i++) {
					if(partie.getJouerJoueur().get(i).peutEtreAccusé==false) {
						partie.getJouerJoueur().get(i).peutEtreAccusé=true;
					}
				}
				 partie.setAccusé((Integer)(spinnerAcc.getValue()));
				 partie.getJouerJoueur().get((Integer)(spinnerAcc.getValue())).setEstAccusé(true);
				
			}
			else if ((partie.getJouerJoueur().get((Integer)(spinnerAcc.getValue())).getBot()==true) && ((Integer)(spinnerAcc.getValue()) != partie.getActuel()) && (partie.getJouerJoueur().get((Integer)(spinnerAcc.getValue())).getReveler())==false && (partie.getJouerJoueur().get((Integer)(spinnerAcc.getValue())).peutEtreAccusé==true) && (partie.getJouerJoueur().get((Integer)(spinnerAcc.getValue())).getEnJeu()==true)) {
				partie.getJouerJoueur().get(partie.getAccusé()).setEstAccusé(true);
    			partie.getJouerJoueur().get(partie.getAccusé()).sonTour=true;
    			Agressive.jouerBot(partie.getJouerJoueur().get(partie.getAccusé()),partie.getJouerJoueur().get(partie.getActuel())); 
			}
	}
			else {
				partie.setAccusé(-1);
			}
		}
	});
	btnRevId.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			partie.getJouerJoueur().get(partie.getAccusé()).setReveler(true);
			if(partie.getJouerJoueur().get(partie.getAccusé()).getRole()==false) {
				partie.setActuel(partie.getAccusé());
			}
			else {
				partie.ajoutPoint(partie.getActuel());
			}
			partie.setComptRévélés(partie.getComptRévélés()+1);
	}
	});
	btnmain.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			partie.getJouerJoueur().get(partie.getAccusé()).setRegardeMain(true);
	}
	});
	btnEffWitch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String theinquisition ="The Inquisition";
			String pointedhat = "Pointed Hat";
			String duckingstool = "Ducking Stool";
			IG_witch_hunt.valueJSpinnerInq=(Integer)spinnerInq.getValue();
			if((Integer)spinnerCibleWitch.getValue() != partie.getAccusé() && partie.getJouerJoueur().get((Integer)spinnerCibleWitch.getValue()).getEnJeu()==true) {
				if((partie.getJouerJoueur().get(partie.getAccusé()).getMain().get(((Integer)spinnerChoixCarte.getValue())).nom.equalsIgnoreCase(theinquisition) && (Integer)spinnerChoixCarte.getValue()!=(Integer)spinnerInq.getValue()) || partie.getJouerJoueur().get(partie.getAccusé()).getMain().get(((Integer)spinnerChoixCarte.getValue())).nom.equalsIgnoreCase(theinquisition)==false) {
					if(partie.getJouerJoueur().get(partie.getAccusé()).getMain().get(((Integer)spinnerChoixCarte.getValue())).nom.equalsIgnoreCase(pointedhat) && (partie.getJouerJoueur().get(partie.getAccusé()).getCartesJouees().size()!=0) || partie.getJouerJoueur().get(partie.getAccusé()).getMain().get(((Integer)spinnerChoixCarte.getValue())).nom.equalsIgnoreCase(pointedhat)==false) {
						if((partie.getJouerJoueur().get(partie.getAccusé()).getMain().get(((Integer)spinnerChoixCarte.getValue())).nom.equalsIgnoreCase(duckingstool) && partie.getAccusé()!=(Integer)spinnerCibleWitch.getValue() && partie.getJouerJoueur().get(partie.getActuel()).getMain().size()!=0) || partie.getJouerJoueur().get(partie.getAccusé()).getMain().get(((Integer)spinnerChoixCarte.getValue())).nom.equalsIgnoreCase(duckingstool)==false) {
						int idchoisie = partie.trouveIndexMain(partie.getAccusé(),partie.getJouerJoueur().get(partie.getAccusé()).getMain().get(((Integer)spinnerChoixCarte.getValue())).nom);
						PartieV2.enleverCarte(idchoisie,partie.getAccusé());
						partie.getCartesDefaussées().get(partie.getCartesDefaussées().size() - 1).jouerCarte(partie.getCartesDefaussées().get(partie.getCartesDefaussées().size() - 1).nom, false, partie.getAccusé(), partie.getActuel());
	}
					}
				}
			}
		}
	});
	btnrécup.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
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
	btnEffetHunt.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String theinquisition ="The Inquisition";
			String pointedhat = "Pointed Hat";
			String duckingstool = "Ducking Stool";
			String angrymob = "Angry Mob";
			String broomstick = "Broomstick";
			String cauldron = "Cauldron";
			String toad = "Toad";
			boolean choisissableangry = true;
			for(int i=0;i<PartieV2.getJouerJoueur().get((Integer)spinnerCibleHunt.getValue()).cartesjouees.size();i++) {
				if(PartieV2.getJouerJoueur().get((Integer)spinnerCibleHunt.getValue()).cartesjouees.get(i).nom.equalsIgnoreCase(broomstick)) {
					choisissableangry=false;
				}
			}
			IG_witch_hunt.valueJSpinnerInq=(Integer)spinnerInq.getValue();
			if(partie.getJouerJoueur().get(partie.getActuel()).getMain().get(((Integer)spinnerCarteHunt.getValue())).nom.equalsIgnoreCase(angrymob) && (partie.getJouerJoueur().get(partie.getActuel()).getRole()==false && partie.getJouerJoueur().get(partie.getActuel()).getReveler()==true && choisissableangry==true) || partie.getJouerJoueur().get(partie.getActuel()).getMain().get(((Integer)spinnerCarteHunt.getValue())).nom.equalsIgnoreCase(angrymob)==false) {
			if((Integer)spinnerCibleHunt.getValue() != partie.getActuel() && partie.getJouerJoueur().get((Integer)spinnerCibleHunt.getValue()).getEnJeu()==true) {
				if(partie.getJouerJoueur().get(partie.getActuel()).getMain().get(((Integer)spinnerCarteHunt.getValue())).nom.equalsIgnoreCase(theinquisition) && (partie.getJouerJoueur().get(partie.getActuel()).getRole()==false && partie.getJouerJoueur().get(partie.getActuel()).getReveler()==true) || partie.getJouerJoueur().get(partie.getActuel()).getMain().get(((Integer)spinnerCarteHunt.getValue())).nom.equalsIgnoreCase(theinquisition)==false) {
					if(partie.getJouerJoueur().get(partie.getActuel()).getMain().get(((Integer)spinnerCarteHunt.getValue())).nom.equalsIgnoreCase(pointedhat) && (partie.getJouerJoueur().get(partie.getActuel()).getCartesJouees().size()!=0) || partie.getJouerJoueur().get(partie.getActuel()).getMain().get(((Integer)spinnerCarteHunt.getValue())).nom.equalsIgnoreCase(pointedhat)==false) {
						if((partie.getJouerJoueur().get(partie.getActuel()).getMain().get(((Integer)spinnerCarteHunt.getValue())).nom.equalsIgnoreCase(duckingstool) && partie.getActuel()!=(Integer)spinnerCibleHunt.getValue() && partie.getJouerJoueur().get(partie.getActuel()).getMain().size()!=0) || partie.getJouerJoueur().get(partie.getActuel()).getMain().get(((Integer)spinnerCarteHunt.getValue())).nom.equalsIgnoreCase(duckingstool)==false) {
							if((partie.getJouerJoueur().get(partie.getActuel()).getMain().get(((Integer)spinnerCarteHunt.getValue())).nom.equalsIgnoreCase(cauldron))|| (partie.getJouerJoueur().get(partie.getActuel()).getMain().get(((Integer)spinnerCarteHunt.getValue())).nom.equalsIgnoreCase(toad))){
								partie.getJouerJoueur().get(partie.getActuel()).setReveler(true);
								partie.setComptRévélés(partie.getComptRévélés()+1);
								partie.setAccusé(partie.getActuel());
							}
						int idchoisie = partie.trouveIndexMain(partie.getActuel(),partie.getJouerJoueur().get(partie.getActuel()).getMain().get(((Integer)spinnerCarteHunt.getValue())).nom);
						PartieV2.enleverCarte(idchoisie,partie.getActuel());
						partie.getCartesDefaussées().get(partie.getCartesDefaussées().size() - 1).jouerCarte(partie.getCartesDefaussées().get(partie.getCartesDefaussées().size() - 1).nom, true, partie.getActuel(), (Integer)spinnerCibleHunt.getValue());

	}
					}
				}
				}
			}
		}
	});
	btncacher.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			IG_witch_hunt.textField_9.setText("");
			btncacher.setEnabled(false);
	}
	});
	btnRevIdDuck.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			partie.setAccusé((Integer)spinnerCibleHunt.getValue());
			partie.getJouerJoueur().get(partie.getAccusé()).setReveler(true);
			if(partie.getJouerJoueur().get(partie.getAccusé()).getRole()==false) {
				partie.enleverPoint(partie.getActuel());
				partie.setActuel(partie.getAccusé());
			}
			else {
				partie.ajoutPoint(partie.getActuel());
			}
			partie.setComptRévélés(partie.getComptRévélés()+1);
	}
	});
	btnSuppDuck.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			partie.setAccusé((Integer)spinnerCibleHunt.getValue());
			
			int idchoisie = partie.trouveIndexMain(partie.getAccusé(),(String)spinnerSuppDuck.getValue());
			partie.enleverCarte(idchoisie,partie.getAccusé());
			partie.setActuel(partie.getAccusé());
	}
	});
	btnrecupBlackCat.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {;

			btnrecupBlackCat.setEnabled(false);
			int idcarte=0;
			for(int i=0; i<partie.getCartesDefaussées().size(); i++) {
				if(partie.getCartesDefaussées().get(i).nom.equalsIgnoreCase((String)spinnerSuppDuck.getValue())){
					idcarte=i;
				}
			}
			partie.getJouerJoueur().get(partie.getActuel()).getMain().add(partie.getCartesDefaussées().get(idcarte));
			partie.getCartesDefaussées().remove(idcarte);
			int idchoisie = partie.trouveIndexMain(partie.getActuel(),partie.getJouerJoueur().get(partie.getActuel()).getMain().get(partie.getJouerJoueur().get(partie.getActuel()).getMain().size()-1).nom);
			PartieV2.enleverCarte(idchoisie,partie.getActuel());
	}
	});
	btnRecupPet.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {

			btnRecupPet.setEnabled(false);
			int idcarte=0;
			boolean aJoué=true;
			while(aJoué) {
			for(int i=0; i<partie.getCartesDefaussées().size(); i++) {
				if(partie.getCartesDefaussées().get(i).nom.equalsIgnoreCase((String)spinnerSuppDuck.getValue())){
					idcarte=i;
					aJoué=false;
					for(int j=0; j<partie.getJouerJoueur().get(partie.getActuel()).getCartesJouees().size(); j++){
						if(partie.getJouerJoueur().get(partie.getActuel()).getCartesJouees().get(j).nom.equalsIgnoreCase((String)spinnerSuppDuck.getValue())) {
							aJoué=true;
						}
					}
				}
			}
			}
			partie.getJouerJoueur().get(partie.getActuel()).getMain().add(partie.getCartesDefaussées().get(idcarte));
			partie.getCartesDefaussées().remove(idcarte);
			partie.donnerTour((Integer)spinnerCibleHunt.getValue());
	}
	});
}
}

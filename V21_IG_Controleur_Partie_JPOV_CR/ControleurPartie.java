package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import frr.utt.lo02.projet.Vue.*;
import javax.swing.*;

import frr.utt.lo02.projet.Modele.*;

public class ControleurPartie {

public ControleurPartie(IG_witch_hunt igraph,PartieV2 partie, JButton btn, JSpinner spinner1, JSpinner spinner2, JButton btn1erJ, JSpinner spinner3, JSpinner chxj, JSpinner role, JButton chxjrole, JButton btnAccuser, JSpinner spinnerAcc, JButton btnRevId){
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
		    			//ajouter la mise � l'�cart de 2 cartes
		    		}
		    		else if (partie.getnbBots()+partie.getnbJoueursPhys()==6) {
		    			partie.setNbCartesParJoueur(2);
		    		}
		    	//Cr�ation de la partie
		        System.out.println(partie.getNbCartesParJoueur()+" cartes vont �tre distribu�es � chaque joueur");
		        //partie.getInstance(partie.getnbJoueursPhys(), partie.getnbBots(), partie.getNbCartesParJoueur());
		    	//Cr�ation de la liste des joueurs
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
		    		partie.getCartesDefauss�es().add(partie.getJouerCarte2().get(10));
		    		partie.getCartesDefauss�es().add(partie.getJouerCarte2().get(11));
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
			System.out.println("Il y a eu un appui sur S�lectionner");
			partie.setActuel((Integer)(spinner3.getValue()));
	}
	});
	chxjrole.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Il y a eu un appui sur choix du r�le");
			if((String)(role.getValue())=="Witch") {
				System.out.println("Vous �tes une witch");
				partie.getJouerJoueur().get((Integer)(chxj.getValue())).setRole(true);
				System.out.println("L� �a bug pas");
			}
			else {
				System.out.println("Vous �tes un villager");
				partie.getJouerJoueur().get((Integer)(chxj.getValue())).setRole(false);
			}
	}
	});
	btnAccuser.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Il y a eu un appui sur accuser");
			if((Integer)(spinnerAcc.getValue()) != partie.getActuel() && partie.getJouerJoueur().get((Integer)(spinnerAcc.getValue())).getReveler()==false && partie.getJouerJoueur().get((Integer)(spinnerAcc.getValue())).peutEtreAccus�==true && partie.getJouerJoueur().get((Integer)(spinnerAcc.getValue())).getEnJeu()==true && partie.getJouerJoueur().get((Integer)(spinnerAcc.getValue())).getBot()==false) {
				System.out.println(partie.getJouerJoueur().get((Integer)(spinnerAcc.getValue()))+" est accus�");
				 partie.setAccus�((Integer)(spinnerAcc.getValue()));
				 partie.getJouerJoueur().get((Integer)(spinnerAcc.getValue())).setEstAccus�(true);
				
				 System.out.println("On a rendu le joueur "+partie.getJouerJoueur().get((Integer)(spinnerAcc.getValue()))+" accus�");
				 System.out.println("Le joueur accus� est "+partie.getAccus�());
			}
	}
	});
	btnRevId.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Il y a eu un appui sur r�v�ler id");
			partie.getJouerJoueur().get(partie.getAccus�()).setReveler(true);
			System.out.println("On est avant le if de villager");
			if(partie.getJouerJoueur().get(partie.getAccus�()).getRole()==false) {
				System.out.println("Le joueur actuel est le joueur "+partie.getActuel());
				partie.setActuel(partie.getAccus�());
			}
			partie.setComptR�v�l�s(partie.getComptR�v�l�s()+1);
	}
	});
}
public static void main(String[] args) {
}
}

package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import frr.utt.lo02.projet.Modele.*;

public class ControleurPartie {

public ControleurPartie(PartieV2 partie, JButton btn, JSpinner spinner1, JSpinner spinner2){
	System.out.println("On est ds le controleur");
	System.out.println("Valeur des spinners : "+(Integer)(spinner1.getValue())+" et "+(Integer)(spinner2.getValue()));
	btn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Il y a eu un appui");
			if((Integer)(spinner1.getValue())+(Integer)(spinner2.getValue())>2 && ((Integer)(spinner1.getValue())+(Integer)(spinner2.getValue()))<7) {
				partie.setnbJoueursPhys((Integer)(spinner1.getValue()));
				partie.setnbBots((Integer)(spinner2.getValue()));
				System.out.println("Joueurs physiques : "+partie.getnbJoueursPhys()+" Joueurs virtuels : "+partie.getnbBots());
			}
		}
	});
}
public static void main(String[] args) {
}
}

package frr.utt.lo02.projet.Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import Controleur.ControleurPartie;
import frr.utt.lo02.projet.Modele.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import frr.utt.lo02.projet.Modele.*;
import java.awt.Color;
import javax.swing.JRadioButton;

public class IG_witch_hunt implements Observer{

	private JFrame frame;
	private JTextField txtCombienDeJoueurs;
	private JTextField txtCombienDeJoueurs_1;
	private JButton commencer1;
	private JButton selectJ;
	private JSpinner choix_nb_jp;
	private JSpinner choix_nb_jv;
	private JSpinner spinner3;
	private JTextPane debut_partie;
	private PartieV2 partie;
	private JTextPane txtpnTropOuPas;
	private JRadioButton j1;
	private JRadioButton j2;
	private JRadioButton j3;
	private JRadioButton j4;
	private JRadioButton j5;
	private JRadioButton j6;
	private JTextPane txtjac;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		PartieV2 partie = new PartieV2(0,0,0);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IG_witch_hunt window = new IG_witch_hunt(partie);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IG_witch_hunt(PartieV2 partie) {
		System.out.println("On entre dans le constructeur");
		initialize();
		System.out.println("On sort du initialize()");
		this.partie=partie;
		this.partie.addObserver(this);
	/*	for (int i = 0; i < this.partie.getJouerCarte().size(); i++) {
			Cartes_RumeursV2 c =this.partie.getJouerCarte().get(i);
			c.addObserver(this);
	    }*/
		for (int i = 0; i < this.partie.getJouerJoueur().size(); i++) {
			this.partie.getJouerJoueur().get(i).addObserver(this);
			System.out.println("Les joueurs observent");
	    }
		
		new ControleurPartie(this.partie, commencer1, choix_nb_jp, choix_nb_jv, selectJ, spinner3);
	
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 929, 516);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtCombienDeJoueurs = new JTextField();
		txtCombienDeJoueurs.setText("Combien de joueurs physiques ?");
		txtCombienDeJoueurs.setBounds(0, 3, 192, 43);
		frame.getContentPane().add(txtCombienDeJoueurs);
		txtCombienDeJoueurs.setColumns(10);
		
		choix_nb_jp = new JSpinner();
		choix_nb_jp.setModel(new SpinnerNumberModel(1, 1, 6, 1));
		choix_nb_jp.setBounds(192, 14, 30, 20);
		frame.getContentPane().add(choix_nb_jp);
		
		txtCombienDeJoueurs_1 = new JTextField();
		txtCombienDeJoueurs_1.setText("Combien de joueurs virtuels ?");
		txtCombienDeJoueurs_1.setColumns(10);
		txtCombienDeJoueurs_1.setBounds(220, 3, 174, 43);
		frame.getContentPane().add(txtCombienDeJoueurs_1);
		
		choix_nb_jv = new JSpinner();
		choix_nb_jv.setModel(new SpinnerNumberModel(0, null, 5, 1));
		choix_nb_jv.setBounds(392, 14, 30, 20);
		frame.getContentPane().add(choix_nb_jv);
		
		commencer1 = new JButton("Commencer");
		commencer1.setBounds(420, 4, 114, 41);
		frame.getContentPane().add(commencer1);
		
		debut_partie = new JTextPane();
		debut_partie.setText("La partie peut commencer !!! Qui est le plus jeune ?");
		debut_partie.setBounds(544, 3, 162, 43);
		debut_partie.setVisible(false);
		frame.getContentPane().add(debut_partie);
		
		txtpnTropOuPas = new JTextPane();
		txtpnTropOuPas.setForeground(Color.RED);
		txtpnTropOuPas.setText("Trop ou pas assez de joueurs");
		txtpnTropOuPas.setVisible(false);
		txtpnTropOuPas.setBounds(703, 3, 200, 43);
		frame.getContentPane().add(txtpnTropOuPas);
		
		j1 = new JRadioButton("J1");
		j1.setBounds(10, 59, 109, 23);
		frame.getContentPane().add(j1);
		
		j2 = new JRadioButton("J2");
		j2.setBounds(133, 59, 109, 23);
		frame.getContentPane().add(j2);
		
		j3 = new JRadioButton("J3");
		j3.setBounds(252, 59, 109, 23);
		frame.getContentPane().add(j3);
		
		j4 = new JRadioButton("J4");
		j4.setBounds(371, 59, 109, 23);
		frame.getContentPane().add(j4);
		
		j5 = new JRadioButton("J5");
		j5.setBounds(493, 59, 109, 23);
		frame.getContentPane().add(j5);
		
		j6 = new JRadioButton("J6");
		j6.setBounds(616, 59, 109, 23);
		frame.getContentPane().add(j6);
		
		selectJ = new JButton("Sélectionner");
		selectJ.setBounds(745, 57, 109, 23);
		frame.getContentPane().add(selectJ);
		
		spinner3 = new JSpinner();
		spinner3.setModel(new SpinnerNumberModel(1, 1, 6, 1));
		spinner3.setBounds(58, 92, 37, 29);
		frame.getContentPane().add(spinner3);
		
		txtjac = new JTextPane();
		txtjac.setBounds(143, 89, 179, 20);
		frame.getContentPane().add(txtjac);
	}
	
	public void update(Observable instanceObservable, Object arg1){
		System.out.println("On est dans le update()");
		System.out.println(arg1);
		if(instanceObservable instanceof PartieV2) {
			System.out.println("Avant le switch");
			System.out.println(((PartieV2)instanceObservable).getnbJoueursPhys()+((PartieV2)instanceObservable).getnbBots());
			switch(((PartieV2)instanceObservable).getnbJoueursPhys()+((PartieV2)instanceObservable).getnbBots()) {
			case 1:txtpnTropOuPas.setVisible(true);break;
			case 2:txtpnTropOuPas.setVisible(true);break;
			case 3:debut_partie.setVisible(true);
				txtpnTropOuPas.setVisible(false);
				j6.setEnabled(false);
				j5.setEnabled(false);
				j4.setEnabled(false);
				commencer1.setEnabled(false);
				break;
			case 4:debut_partie.setVisible(true);
				txtpnTropOuPas.setVisible(false);
				commencer1.setEnabled(false);
				j5.setEnabled(false);
				j6.setEnabled(false);
				break;
			case 5:debut_partie.setVisible(true);
				txtpnTropOuPas.setVisible(false);
				commencer1.setEnabled(false);
				j6.setEnabled(false);
				break;
			case 6:debut_partie.setVisible(true);
				txtpnTropOuPas.setVisible(false);
				commencer1.setEnabled(false);
				break;
			case 7:txtpnTropOuPas.setVisible(true);break;
			case 8:txtpnTropOuPas.setVisible(true);break;
			case 9:txtpnTropOuPas.setVisible(true);break;
			case 10:txtpnTropOuPas.setVisible(true);break;
			case 11:txtpnTropOuPas.setVisible(true);break;
			}
			
			switch(((PartieV2)instanceObservable).getActuel()) {
			case 1: txtjac.setText("Joueur actuel : J1");break;
			case 2: txtjac.setText("Joueur actuel : J2");break;
			case 3: txtjac.setText("Joueur actuel : J3");break;
			case 4: txtjac.setText("Joueur actuel : J4");break;
			case 5: txtjac.setText("Joueur actuel : J5");break;
			case 6: txtjac.setText("Joueur actuel : J6");break;
			}
		}
	}
}

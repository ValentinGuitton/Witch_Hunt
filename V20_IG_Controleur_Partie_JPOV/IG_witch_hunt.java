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
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.SpinnerListModel;

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
	private JTextPane txtjac;
	private JPanel carte1;
	private JPanel carte2;
	private JPanel carte3;
	private JPanel carte4;
	private JPanel carte5;
	private JTextField txtJoueur;
	private JTextField txtRle;
	private JSpinner chxj;
	private JSpinner role;
	private JButton chxrole;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		PartieV2 partie = PartieV2.getInstance(0,0,0);
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
		for (int i = 0; i < this.partie.getJouerCarte().size(); i++) {
			Cartes_RumeursV2 c =this.partie.getJouerCarte().get(i);
			c.addObserver(this);
			}
	    for (int i = 0; i < this.partie.getJouerJoueur().size(); i++) {
			System.out.println("Les joueurs observent");
			this.partie.getJouerJoueur().get(i).addObserver(this);
			}
	    
		new ControleurPartie(partie, commencer1, choix_nb_jp, choix_nb_jv, selectJ, spinner3, chxj, role, chxrole);
		
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
		choix_nb_jv.setBounds(393, 14, 30, 20);
		frame.getContentPane().add(choix_nb_jv);
		
		commencer1 = new JButton("Commencer");
		commencer1.setBounds(432, 4, 114, 41);
		frame.getContentPane().add(commencer1);
		
		debut_partie = new JTextPane();
		debut_partie.setText("La partie peut commencer !!! Qui est le plus jeune ?");
		debut_partie.setBounds(545, 3, 162, 43);
		debut_partie.setVisible(false);
		frame.getContentPane().add(debut_partie);
		
		txtpnTropOuPas = new JTextPane();
		txtpnTropOuPas.setForeground(Color.RED);
		txtpnTropOuPas.setText("Trop ou pas assez de joueurs");
		txtpnTropOuPas.setVisible(false);
		txtpnTropOuPas.setBounds(713, 3, 200, 43);
		frame.getContentPane().add(txtpnTropOuPas);
		
		selectJ = new JButton("Sélectionner");
		selectJ.setBounds(266, 57, 109, 23);
		frame.getContentPane().add(selectJ);
		selectJ.setEnabled(false);
		
		spinner3 = new JSpinner();
		spinner3.setModel(new SpinnerNumberModel(0, 0, 5, 1));
		spinner3.setBounds(10, 54, 37, 29);
		frame.getContentPane().add(spinner3);
		
		txtjac = new JTextPane();
		txtjac.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtjac.setBounds(57, 57, 186, 23);
		frame.getContentPane().add(txtjac);
		
		role = new JSpinner();
		role.setModel(new SpinnerListModel(new String[] {"Witch", "Villager"}));
		role.setBounds(586, 57, 99, 20);
		frame.getContentPane().add(role);
		
		chxj = new JSpinner();
		chxj.setModel(new SpinnerNumberModel(0, 0, 5, 1));
		chxj.setBounds(485, 56, 30, 20);
		frame.getContentPane().add(chxj);
		
		txtJoueur = new JTextField();
		txtJoueur.setText("Joueur :");
		txtJoueur.setBounds(389, 57, 86, 20);
		frame.getContentPane().add(txtJoueur);
		txtJoueur.setColumns(10);
		
		txtRle = new JTextField();
		txtRle.setText("R\u00F4le :");
		txtRle.setBounds(528, 56, 48, 20);
		frame.getContentPane().add(txtRle);
		txtRle.setColumns(10);
		
		chxrole = new JButton("Choisir");
		chxrole.setBounds(698, 54, 89, 23);
		frame.getContentPane().add(chxrole);
		chxrole.setEnabled(false);
		
		carte1 = new JPanel();
		carte1.setBackground(Color.LIGHT_GRAY);
		carte1.setBounds(20, 94, 114, 122);
		frame.getContentPane().add(carte1);
		
		carte2 = new JPanel();
		carte2.setBackground(Color.LIGHT_GRAY);
		carte2.setBounds(161, 94, 114, 122);
		frame.getContentPane().add(carte2);
		
		carte3 = new JPanel();
		carte3.setBackground(Color.LIGHT_GRAY);
		carte3.setBounds(302, 94, 114, 122);
		frame.getContentPane().add(carte3);
		
		carte4 = new JPanel();
		carte4.setBackground(Color.LIGHT_GRAY);
		carte4.setBounds(444, 94, 114, 122);
		frame.getContentPane().add(carte4);
		
		carte5 = new JPanel();
		carte5.setBackground(Color.LIGHT_GRAY);
		carte5.setBounds(585, 94, 114, 122);
		frame.getContentPane().add(carte5);
	}
	
	public void update(Observable instanceObservable, Object arg1){
		System.out.println("On est dans le update()");
		if(instanceObservable instanceof PartieV2) {
			System.out.println("Avant le switch");
			switch(((PartieV2)instanceObservable).getnbJoueursPhys()+((PartieV2)instanceObservable).getnbBots()) {
			case 1:txtpnTropOuPas.setVisible(true);break;
			case 2:txtpnTropOuPas.setVisible(true);break;
			case 3:debut_partie.setVisible(true);
				txtpnTropOuPas.setVisible(false);
				commencer1.setEnabled(false);
				spinner3.setModel(new SpinnerNumberModel(0, 0, 2, 1));
				chxj.setModel(new SpinnerNumberModel(0, 0, 2, 1));
				chxrole.setEnabled(true);
				selectJ.setEnabled(true);
				if ((Integer)chxj.getValue()==2) {
					chxj.setVisible(false);
					txtJoueur.setVisible(false);
					txtRle.setVisible(false);
					chxrole.setVisible(false);
				}
				break;
			case 4:debut_partie.setVisible(true);
				txtpnTropOuPas.setVisible(false);
				commencer1.setEnabled(false);
				spinner3.setModel(new SpinnerNumberModel(0, 0, 3, 1));
				chxj.setModel(new SpinnerNumberModel(0, 0, 3, 1));
				chxrole.setEnabled(true);
				selectJ.setEnabled(true);
				if ((Integer)chxj.getValue()==3) {
					chxj.setVisible(false);
					txtJoueur.setVisible(false);
					txtRle.setVisible(false);
					chxrole.setVisible(false);
				}
				break;
			case 5:debut_partie.setVisible(true);
				txtpnTropOuPas.setVisible(false);
				commencer1.setEnabled(false);
				spinner3.setModel(new SpinnerNumberModel(0, 0, 4, 1));
				chxj.setModel(new SpinnerNumberModel(0, 0, 4, 1));
				chxrole.setEnabled(true);
				selectJ.setEnabled(true);
				if ((Integer)chxj.getValue()==4) {
					chxj.setVisible(false);
					txtJoueur.setVisible(false);
					txtRle.setVisible(false);
					chxrole.setVisible(false);
				}
				break;
			case 6:debut_partie.setVisible(true);
				txtpnTropOuPas.setVisible(false);
				commencer1.setEnabled(false);
				spinner3.setModel(new SpinnerNumberModel(0, 0, 5, 1));
				chxj.setModel(new SpinnerNumberModel(0, 0, 5, 1));
				chxrole.setEnabled(true);
				selectJ.setEnabled(true);
				System.out.println("Le spinner vaut "+(Integer)chxj.getValue());
				if ((Integer)chxj.getValue()==5) {
					chxj.setVisible(false);
					txtJoueur.setVisible(false);
					txtRle.setVisible(false);
					chxrole.setVisible(false);
				}
				break;
			case 7:txtpnTropOuPas.setVisible(true);break;
			case 8:txtpnTropOuPas.setVisible(true);break;
			case 9:txtpnTropOuPas.setVisible(true);break;
			case 10:txtpnTropOuPas.setVisible(true);break;
			case 11:txtpnTropOuPas.setVisible(true);break;
			}
			
			switch(((PartieV2)instanceObservable).getActuel()) {
			case 0: txtjac.setText("Joueur actuel : J0");break;
			case 1: txtjac.setText("Joueur actuel : J1");break;
			case 2: txtjac.setText("Joueur actuel : J2");break;
			case 3: txtjac.setText("Joueur actuel : J3");break;
			case 4: txtjac.setText("Joueur actuel : J4");break;
			case 5: txtjac.setText("Joueur actuel : J5");break;
			}
		}
		else if (instanceObservable instanceof Joueur_Physique_ou_VirtuelV2) {
			
		}
	}
}

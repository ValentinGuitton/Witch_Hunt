package frr.utt.lo02.projet.Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

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
	private JSpinner spinnerAcc;
	private JTextPane debut_partie;
	private PartieV2 partie;
	private JTextPane txtpnTropOuPas;
	private JTextPane txtjac;
	private JTextPane txtpnEffetWitch;
	private JTextPane txtpnEffetHunt;
	private JPanel carte1;
	private JPanel carte2;
	private JPanel carte3;
	private JPanel carte4;
	private JPanel carte5;
	private JTextField txtJoueur;
	private JTextField txtRle;
	private JSpinner chxj;
	private JSpinner role;
	public static JSpinner spinnerCibleWitch;
	private JSpinner spinnerChoixCarte;
	private JButton chxrole;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private TextArea txtarea;
	private JButton btnAccuser;
	private JButton btnRevId;
	private JButton btnEffWitch;
	private JButton btnmain;
	private JTextField textField_6;
	private JTextPane txtpnNombreDeJoueurs;
	private JTextField textField_7;
	private JTextField textField_8;
	private JSpinner spinner;
	private JSpinner spinnerInq;
	public static int valueJSpinnerInq=-5;
	public static JSpinner spinner_pointedh;
	public static JButton btnrécup;
	private JButton btnNewButton;
	public static JButton btnrécuphooked;

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
		partie.setInterfaceg(true);
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
	    
		new ControleurPartie(this,partie, commencer1, choix_nb_jp, choix_nb_jv, selectJ, spinner3, chxj, role, chxrole, btnAccuser, spinnerAcc, btnRevId, btnEffWitch, btnmain, spinnerCibleWitch, spinnerChoixCarte, spinnerInq, btnrécup, btnrécuphooked);
		
		JTextPane txtpnCarteSupprimer = new JTextPane();
		txtpnCarteSupprimer.setText("Carte \u00E0 supprimer (\"The Inquisition\") :");
		txtpnCarteSupprimer.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnCarteSupprimer.setBounds(639, 196, 242, 20);
		frame.getContentPane().add(txtpnCarteSupprimer);
		
		
		
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
		
		spinnerAcc = new JSpinner();
		spinnerAcc.setModel(new SpinnerNumberModel(0, 0, 5, 1));
		spinnerAcc.setBounds(713, 94, 30, 20);
		frame.getContentPane().add(spinnerAcc);
		
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
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_5.setBounds(10, 250, 893, 43);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		txtarea = new TextArea();
		txtarea.setEnabled(false);
		txtarea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtarea.setBounds(10, 329, 893, 80);
		frame.getContentPane().add(txtarea);
		txtarea.setColumns(10);
		
		carte1 = new JPanel();
		carte1.setBackground(Color.LIGHT_GRAY);
		carte1.setBounds(0, 94, 114, 122);
		frame.getContentPane().add(carte1);
		
		textField = new JTextField();
		carte1.add(textField);
		textField.setColumns(10);
		carte1.addMouseListener(new MouseAdapter() 
		
	      {
	         public void mouseEntered(MouseEvent evt) 
	         {
	            if(partie.getJouerJoueur().get(partie.getActuel()).main.size()>0) {
	            textField.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(0).nom);
	            textField_5.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(0).afficherEffetWitch(partie.getJouerJoueur().get(partie.getActuel()).main.get(0).nom));
	            txtarea.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(0).afficherEffetHunt(partie.getJouerJoueur().get(partie.getActuel()).main.get(0).nom));
	            spinnerChoixCarte.setEnabled(true);
	            spinnerChoixCarte.setModel(new SpinnerNumberModel(0, 0, 0, 1));
	            }
	            else {
	            	textField.setText("Pas de carte");
	            	spinnerChoixCarte.setEnabled(false);
	            	btnEffWitch.setEnabled(false);
	            }
	         }
	         public void mouseExited(MouseEvent evt) 
	         {
	        	textField.setText("");
	        	textField_5.setText("");
	        	txtarea.setText("");
	         }
	      });
		
		carte2 = new JPanel();
		carte2.setBackground(Color.LIGHT_GRAY);
		carte2.setBounds(124, 94, 114, 122);
		frame.getContentPane().add(carte2);
		textField_1 = new JTextField();
		carte2.add(textField_1);
		textField_1.setColumns(10);
		carte2.addMouseListener(new MouseAdapter() 
	      {
	         public void mouseEntered(MouseEvent evt) 
	         {
	            if(partie.getJouerJoueur().get(partie.getActuel()).main.size()>1) {
	            	textField_1.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(1).nom);
	            	  textField_5.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(1).afficherEffetWitch(partie.getJouerJoueur().get(partie.getActuel()).main.get(1).nom));
	            	  txtarea.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(1).afficherEffetHunt(partie.getJouerJoueur().get(partie.getActuel()).main.get(1).nom));
	            	  spinnerChoixCarte.setEnabled(true);
	  	              spinnerChoixCarte.setModel(new SpinnerNumberModel(0, 0, 1, 1));
	            }
	            else {
	            	textField_1.setText("Pas de 2ème carte");
	            }
	         }
	         public void mouseExited(MouseEvent evt) 
	         {
	        	 textField_1.setText("");
	        	 textField_5.setText("");
	        	 txtarea.setText("");
	         }
	      });
		
		carte3 = new JPanel();
		carte3.setBackground(Color.LIGHT_GRAY);
		carte3.setBounds(248, 94, 114, 122);
		frame.getContentPane().add(carte3);
		
		textField_2 = new JTextField();
		carte3.add(textField_2);
		textField_2.setColumns(10);

		carte3.addMouseListener(new MouseAdapter() 
	      {
	         public void mouseEntered(MouseEvent evt) 
	         {
	            if(partie.getJouerJoueur().get(partie.getActuel()).main.size()>2) {
	            	textField_2.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(2).nom);
	            	textField_5.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(2).afficherEffetWitch(partie.getJouerJoueur().get(partie.getActuel()).main.get(2).nom));
	            	txtarea.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(2).afficherEffetHunt(partie.getJouerJoueur().get(partie.getActuel()).main.get(2).nom));
	            	spinnerChoixCarte.setEnabled(true);
	  	            spinnerChoixCarte.setModel(new SpinnerNumberModel(0, 0, 2, 1));
	            }
	            else {
	            	textField_2.setText("Pas de 3ème carte");
	            }
	         }
	         public void mouseExited(MouseEvent evt) 
	         {
	        	 textField_2.setText("");
	        	 textField_5.setText("");
	        	 txtarea.setText("");
	         }
	      });
		
		carte4 = new JPanel();
		carte4.setBackground(Color.LIGHT_GRAY);
		carte4.setBounds(372, 94, 114, 122);
		frame.getContentPane().add(carte4);
		
		textField_3 = new JTextField();
		carte4.add(textField_3);
		textField_3.setColumns(10);
		
		carte4.addMouseListener(new MouseAdapter() 
	      {
	         public void mouseEntered(MouseEvent evt) 
	         {
	            if(partie.getJouerJoueur().get(partie.getActuel()).main.size()>3) {
	            	textField_3.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(3).nom);
	            	  textField_5.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(3).afficherEffetWitch(partie.getJouerJoueur().get(partie.getActuel()).main.get(3).nom));
	            	  txtarea.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(3).afficherEffetHunt(partie.getJouerJoueur().get(partie.getActuel()).main.get(3).nom));
	            	  spinnerChoixCarte.setEnabled(true);
	  	              spinnerChoixCarte.setModel(new SpinnerNumberModel(0, 0, 3, 1));
	            }
	            else {
	            	textField_3.setText("Pas de 4ème carte");
	            }
	         }
	         public void mouseExited(MouseEvent evt) 
	         {
	        	 textField_3.setText("");
	        	 textField_5.setText("");
	        	 txtarea.setText("");
	         }
	      });
		
		carte5 = new JPanel();
		carte5.setBackground(Color.LIGHT_GRAY);
		carte5.setBounds(495, 94, 114, 122);
		frame.getContentPane().add(carte5);
		
		textField_4 = new JTextField();
		carte5.add(textField_4);
		textField_4.setColumns(10);
		
		carte5.addMouseListener(new MouseAdapter() 
	      {
	         public void mouseEntered(MouseEvent evt) 
	         {
	            if(partie.getJouerJoueur().get(partie.getActuel()).main.size()>4) {
	            	textField_4.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(4).nom);
	            	  textField_5.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(4).afficherEffetWitch(partie.getJouerJoueur().get(partie.getActuel()).main.get(4).nom));
	            	  txtarea.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(4).afficherEffetHunt(partie.getJouerJoueur().get(partie.getActuel()).main.get(4).nom));
	            }
	            else {
	            	textField_4.setText("Pas de 5ème carte");
	            }
	         }
	         public void mouseExited(MouseEvent evt) 
	         {
	        	 textField_4.setText("");
	        	 textField_5.setText("");
	        	 txtarea.setText("");
	         }
	      });
		txtpnEffetWitch = new JTextPane();
		txtpnEffetWitch.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnEffetWitch.setText("Effet Witch :");
		txtpnEffetWitch.setBounds(10, 227, 99, 20);
		frame.getContentPane().add(txtpnEffetWitch);
		
		txtpnEffetHunt = new JTextPane();
		txtpnEffetHunt.setText("Effet Hunt :");
		txtpnEffetHunt.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnEffetHunt.setBounds(10, 304, 99, 20);
		frame.getContentPane().add(txtpnEffetHunt);
		
		btnAccuser = new JButton("Accuser");
		btnAccuser.setBounds(619, 93, 89, 23);
		frame.getContentPane().add(btnAccuser);
		
		
		btnRevId = new JButton("Reveler Id");
		btnRevId.setBounds(629, 141, 114, 23);
		frame.getContentPane().add(btnRevId);
		btnRevId.setEnabled(false);
		
		btnEffWitch = new JButton("Effet Witch");
		btnEffWitch.setBounds(629, 170, 114, 23);
		frame.getContentPane().add(btnEffWitch);
		btnEffWitch.setEnabled(false);
		
		textField_6 = new JTextField();
		textField_6.setBounds(10, 415, 302, 20);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		txtpnNombreDeJoueurs = new JTextPane();
		txtpnNombreDeJoueurs.setText("Nombre de joueurs r\u00E9v\u00E9l\u00E9s :");
		txtpnNombreDeJoueurs.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnNombreDeJoueurs.setBounds(10, 446, 192, 20);
		frame.getContentPane().add(txtpnNombreDeJoueurs);
		
		textField_7 = new JTextField();
		textField_7.setBounds(213, 446, 30, 20);
		frame.getContentPane().add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(753, 94, 162, 20);
		frame.getContentPane().add(textField_8);
		textField_8.setColumns(10);
		
		btnmain = new JButton("Afficher main");
		btnmain.setBounds(779, 141, 124, 23);
		frame.getContentPane().add(btnmain);
		
		spinnerCibleWitch = new JSpinner();
		spinnerCibleWitch.setBounds(753, 171, 30, 20);
		frame.getContentPane().add(spinnerCibleWitch);
		
		spinnerChoixCarte = new JSpinner();
		spinnerChoixCarte.setBounds(789, 171, 30, 20);
		frame.getContentPane().add(spinnerChoixCarte);
	
		spinnerInq = new JSpinner();
		spinnerInq.setBounds(713, 217, 125, 20);
		spinnerInq.setModel(new SpinnerNumberModel(0, 0, 4, 1));
		frame.getContentPane().add(spinnerInq);
		
		spinner_pointedh = new JSpinner();
		spinner_pointedh.setBounds(403, 227, 143, 20);
		frame.getContentPane().add(spinner_pointedh);
		
		btnrécup = new JButton("R\u00E9cup Pointed");
		btnrécup.setBounds(269, 224, 125, 23);
		frame.getContentPane().add(btnrécup);
		btnrécup.setEnabled(false);
		
		btnrécuphooked = new JButton("Recup Hooked");
		btnrécuphooked.setBounds(548, 224, 125, 23);
		frame.getContentPane().add(btnrécuphooked);
		btnrécuphooked.setEnabled(false);
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
				spinnerAcc.setModel(new SpinnerNumberModel(0, 0, 2, 1));
				spinnerCibleWitch.setModel(new SpinnerNumberModel(0, 0, 2, 1));
				chxrole.setEnabled(true);
				selectJ.setEnabled(true);
				System.out.println("Taille du jouer carte : "+partie.getJouerCarte().size());
				System.out.println("Taille du jouer joueur : "+partie.getJouerJoueur().size());
				System.out.println("Le spinner vaut "+(Integer)chxj.getValue());
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
				spinnerAcc.setModel(new SpinnerNumberModel(0, 0, 3, 1));
				spinnerCibleWitch.setModel(new SpinnerNumberModel(0, 0, 3, 1));
				chxrole.setEnabled(true);
				selectJ.setEnabled(true);
				System.out.println("Taille du jouer carte : "+partie.getJouerCarte().size());
				System.out.println("Taille du jouer joueur : "+partie.getJouerJoueur().size());
				System.out.println("Le spinner vaut "+(Integer)chxj.getValue());
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
				spinnerAcc.setModel(new SpinnerNumberModel(0, 0, 4, 1));
				spinnerCibleWitch.setModel(new SpinnerNumberModel(0, 0, 4, 1));
				chxrole.setEnabled(true);
				selectJ.setEnabled(true);
				System.out.println("Taille du jouer carte : "+partie.getJouerCarte().size());
				System.out.println("Taille du jouer joueur : "+partie.getJouerJoueur().size());
				System.out.println("Le spinner vaut "+(Integer)chxj.getValue());
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
				spinnerAcc.setModel(new SpinnerNumberModel(0, 0, 5, 1));
				spinnerCibleWitch.setModel(new SpinnerNumberModel(0, 0, 5, 1));
				chxrole.setEnabled(true);
				selectJ.setEnabled(true);
				System.out.println("Taille du jouer carte : "+partie.getJouerCarte().size());
				System.out.println("Taille du jouer joueur : "+partie.getJouerJoueur().size());
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
			
			
			if(partie.getAccusé()!=-1) {
			if(partie.getJouerJoueur().get(partie.getAccusé()).getReveler()==true) {
				if(partie.getJouerJoueur().get(partie.getAccusé()).getRole()==true) {
					textField_6.setText("Le joueur "+partie.getAccusé()+" est une Witch");
				}
				else {
					textField_6.setText("Le joueur "+partie.getAccusé()+" est un Villager");
				}
				carte1.addMouseListener(new MouseAdapter() 
			      {
			         public void mouseEntered(MouseEvent evt) 
			         {
			            if(partie.getJouerJoueur().get(partie.getActuel()).main.size()>0) {
			            textField.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(0).nom);
			            textField_5.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(0).afficherEffetWitch(partie.getJouerJoueur().get(partie.getActuel()).main.get(0).nom));
			            txtarea.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(0).afficherEffetHunt(partie.getJouerJoueur().get(partie.getActuel()).main.get(0).nom));
			            spinnerChoixCarte.setEnabled(true);
			            spinnerChoixCarte.setModel(new SpinnerNumberModel(0, 0, 0, 1));
			            spinnerInq.setModel(new SpinnerNumberModel(0, 0, 0, 1));
			            }
			            else {
			            	textField.setText("Pas de carte");
			            	spinnerChoixCarte.setEnabled(false);
			            	btnEffWitch.setEnabled(false);
			            }
			         }
			         public void mouseExited(MouseEvent evt) 
			         {
			        	textField.setText("");
			        	textField_5.setText("");
			        	txtarea.setText("");
			         }
			      });
				carte2.addMouseListener(new MouseAdapter() 
			      {
			         public void mouseEntered(MouseEvent evt) 
			         {
			            if(partie.getJouerJoueur().get(partie.getActuel()).main.size()>1) {
			            	textField_1.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(1).nom);
			            	  textField_5.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(1).afficherEffetWitch(partie.getJouerJoueur().get(partie.getActuel()).main.get(1).nom));
			            	  txtarea.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(1).afficherEffetHunt(partie.getJouerJoueur().get(partie.getActuel()).main.get(1).nom));
					            spinnerChoixCarte.setEnabled(true);
					            spinnerChoixCarte.setModel(new SpinnerNumberModel(0, 0, 1, 1));
					            spinnerInq.setModel(new SpinnerNumberModel(0, 0, 1, 1));
			            }
			            else {
			            	textField_1.setText("Pas de 2ème carte");
			            }
			         }
			         public void mouseExited(MouseEvent evt) 
			         {
			        	 textField_1.setText("");
			        	 textField_5.setText("");
			        	 txtarea.setText("");
			         }
			      });
				
				carte3.addMouseListener(new MouseAdapter() 
			      {
			         public void mouseEntered(MouseEvent evt) 
			         {
			            if(partie.getJouerJoueur().get(partie.getActuel()).main.size()>2) {
			            	textField_2.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(2).nom);
			            	textField_5.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(2).afficherEffetWitch(partie.getJouerJoueur().get(partie.getActuel()).main.get(2).nom));
			            	txtarea.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(2).afficherEffetHunt(partie.getJouerJoueur().get(partie.getActuel()).main.get(2).nom));
				            spinnerChoixCarte.setEnabled(true);
				            spinnerChoixCarte.setModel(new SpinnerNumberModel(0, 0, 2, 1));
				            spinnerInq.setModel(new SpinnerNumberModel(0, 0, 2, 1));
			            }
			            else {
			            	textField_2.setText("Pas de 3ème carte");
			            }
			         }
			         public void mouseExited(MouseEvent evt) 
			         {
			        	 textField_2.setText("");
			        	 textField_5.setText("");
			        	 txtarea.setText("");
			         }
			      });
				
				carte4.addMouseListener(new MouseAdapter() 
			      {
			         public void mouseEntered(MouseEvent evt) 
			         {
			            if(partie.getJouerJoueur().get(partie.getActuel()).main.size()>3) {
			            	System.out.println("Il y a 4 cartes min ds sa main");
			            	textField_3.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(3).nom);
			            	  textField_5.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(3).afficherEffetWitch(partie.getJouerJoueur().get(partie.getActuel()).main.get(3).nom));
			            	  txtarea.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(3).afficherEffetHunt(partie.getJouerJoueur().get(partie.getActuel()).main.get(3).nom));
					            spinnerChoixCarte.setEnabled(true);
					            spinnerChoixCarte.setModel(new SpinnerNumberModel(0, 0, 3, 1));
					            spinnerInq.setModel(new SpinnerNumberModel(0, 0, 3, 1));
			            }
			            else {
			            	textField_3.setText("Pas de 4ème carte");
			            }
			         }
			         public void mouseExited(MouseEvent evt) 
			         {
			        	 textField_3.setText("");
			        	 textField_5.setText("");
			        	 txtarea.setText("");
			         }
			      });
				
				carte5.addMouseListener(new MouseAdapter() 
			      {
			         public void mouseEntered(MouseEvent evt) 
			         {
			            if(partie.getJouerJoueur().get(partie.getActuel()).main.size()>4) {
			            	textField_4.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(4).nom);
			            	  textField_5.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(4).afficherEffetWitch(partie.getJouerJoueur().get(partie.getActuel()).main.get(4).nom));
			            	  txtarea.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(4).afficherEffetHunt(partie.getJouerJoueur().get(partie.getActuel()).main.get(4).nom));
					           spinnerChoixCarte.setEnabled(true);
					           spinnerChoixCarte.setModel(new SpinnerNumberModel(0, 0, 4, 1));
					           spinnerInq.setModel(new SpinnerNumberModel(0, 0, 4, 1));
			            }
			            else {
			            	textField_4.setText("Pas de 5ème carte");
			            }
			         }
			         public void mouseExited(MouseEvent evt) 
			         {
			        	 textField_4.setText("");
			        	 textField_5.setText("");
			        	 txtarea.setText("");
			         }
			      });
				btnAccuser.setEnabled(true);
				btnRevId.setEnabled(false);
				btnEffWitch.setEnabled(false);
				spinnerAcc.setEnabled(true);
				textField_8.setText("");
				textField_7.setText(""+partie.getComptRévélés()+"");
			}
		}

		}
		else if (instanceObservable instanceof Joueur_Physique_ou_VirtuelV2) {
			System.out.println("On est ds joueur");
			System.out.println ("le spinner vaut : "+(Integer)chxj.getValue());
			if ((Integer)chxj.getValue()==partie.getJouerJoueur().size()-1 && partie.getJouerJoueur().size()!=0) {
				chxj.setVisible(false);
				txtJoueur.setVisible(false);
				txtRle.setVisible(false);
				chxrole.setVisible(false);
				role.setVisible(false);
			}
			System.out.println("on est avant le if");
			if(partie.getAccusé() != -1) {
				System.out.println("on est ds le  1er if");
			if(partie.getJouerJoueur().get(partie.getAccusé()).getEstAccusé()==true) {
				System.out.println("on est ds le  2ème if");
				partie.getJouerJoueur().get(partie.getAccusé()).setEstAccusé(false);
				btnRevId.setEnabled(true);
				btnEffWitch.setEnabled(true);
				spinnerAcc.setEnabled(false);
				btnAccuser.setEnabled(false);
				textField_8.setText(partie.getAccusé()+" est accusé");
			}
			System.out.println("Le joueur accusé veut voir sa main ? "+partie.getJouerJoueur().get(partie.getAccusé()).getRegardeMain());
			if(partie.getJouerJoueur().get(partie.getAccusé()).getRegardeMain()==true) {
				partie.getJouerJoueur().get(partie.getAccusé()).setRegardeMain(false);
				carte1.addMouseListener(new MouseAdapter() 
			      {
			         public void mouseEntered(MouseEvent evt) 
			         {
			            if(partie.getJouerJoueur().get(partie.getAccusé()).main.size()>0) {
			            textField.setText(partie.getJouerJoueur().get(partie.getAccusé()).main.get(0).nom);
			            textField_5.setText(partie.getJouerJoueur().get(partie.getAccusé()).main.get(0).afficherEffetWitch(partie.getJouerJoueur().get(partie.getAccusé()).main.get(0).nom));
			            txtarea.setText(partie.getJouerJoueur().get(partie.getAccusé()).main.get(0).afficherEffetHunt(partie.getJouerJoueur().get(partie.getAccusé()).main.get(0).nom));
			            }
			            else {
			            	textField.setText("Pas de carte");
			            }
			         }
			         public void mouseExited(MouseEvent evt) 
			         {
			        	textField.setText("");
			        	textField_5.setText("");
			        	txtarea.setText("");
			         }
			      });
				carte2.addMouseListener(new MouseAdapter() 
			      {
			         public void mouseEntered(MouseEvent evt) 
			         {
			            if(partie.getJouerJoueur().get(partie.getAccusé()).main.size()>1) {
			            	textField_1.setText(partie.getJouerJoueur().get(partie.getAccusé()).main.get(1).nom);
			            	  textField_5.setText(partie.getJouerJoueur().get(partie.getAccusé()).main.get(1).afficherEffetWitch(partie.getJouerJoueur().get(partie.getAccusé()).main.get(1).nom));
			            	  txtarea.setText(partie.getJouerJoueur().get(partie.getAccusé()).main.get(1).afficherEffetHunt(partie.getJouerJoueur().get(partie.getAccusé()).main.get(1).nom));
			            }
			            else {
			            	textField_1.setText("Pas de 2ème carte");
			            }
			         }
			         public void mouseExited(MouseEvent evt) 
			         {
			        	 textField_1.setText("");
			        	 textField_5.setText("");
			        	 txtarea.setText("");
			         }
			      });
				carte3.addMouseListener(new MouseAdapter() 
			      {
			         public void mouseEntered(MouseEvent evt) 
			         {
			            if(partie.getJouerJoueur().get(partie.getAccusé()).main.size()>2) {
			            	textField_2.setText(partie.getJouerJoueur().get(partie.getAccusé()).main.get(2).nom);
			            	textField_5.setText(partie.getJouerJoueur().get(partie.getAccusé()).main.get(2).afficherEffetWitch(partie.getJouerJoueur().get(partie.getAccusé()).main.get(2).nom));
			            	txtarea.setText(partie.getJouerJoueur().get(partie.getAccusé()).main.get(2).afficherEffetHunt(partie.getJouerJoueur().get(partie.getAccusé()).main.get(2).nom));
			            }
			            else {
			            	textField_2.setText("Pas de 3ème carte");
			            }
			         }
			         public void mouseExited(MouseEvent evt) 
			         {
			        	 textField_2.setText("");
			        	 textField_5.setText("");
			        	 txtarea.setText("");
			         }
			      });
				carte4.addMouseListener(new MouseAdapter() 
			      {
			         public void mouseEntered(MouseEvent evt) 
			         {
			            if(partie.getJouerJoueur().get(partie.getAccusé()).main.size()>3) {
			            	textField_3.setText(partie.getJouerJoueur().get(partie.getAccusé()).main.get(3).nom);
			            	  textField_5.setText(partie.getJouerJoueur().get(partie.getAccusé()).main.get(3).afficherEffetWitch(partie.getJouerJoueur().get(partie.getAccusé()).main.get(3).nom));
			            	  txtarea.setText(partie.getJouerJoueur().get(partie.getAccusé()).main.get(3).afficherEffetHunt(partie.getJouerJoueur().get(partie.getAccusé()).main.get(3).nom));
			            }
			            else {
			            	textField_3.setText("Pas de 4ème carte");
			            }
			         }
			         public void mouseExited(MouseEvent evt) 
			         {
			        	 textField_3.setText("");
			        	 textField_5.setText("");
			        	 txtarea.setText("");
			         }
			      });
				carte5.addMouseListener(new MouseAdapter() 
			      {
			         public void mouseEntered(MouseEvent evt) 
			         {
			            if(partie.getJouerJoueur().get(partie.getAccusé()).main.size()>4) {
			            	textField_4.setText(partie.getJouerJoueur().get(partie.getAccusé()).main.get(4).nom);
			            	  textField_5.setText(partie.getJouerJoueur().get(partie.getAccusé()).main.get(4).afficherEffetWitch(partie.getJouerJoueur().get(partie.getAccusé()).main.get(4).nom));
			            	  txtarea.setText(partie.getJouerJoueur().get(partie.getAccusé()).main.get(4).afficherEffetHunt(partie.getJouerJoueur().get(partie.getAccusé()).main.get(4).nom));
			            }
			            else {
			            	textField_4.setText("Pas de 5ème carte");
			            }
			         }
			         public void mouseExited(MouseEvent evt) 
			         {
			        	 textField_4.setText("");
			        	 textField_5.setText("");
			        	 txtarea.setText("");
			         }
			      });
			}
			}
			
		}
		else if (instanceObservable instanceof Cartes_RumeursV2) {
				btnRevId.setEnabled(false);
				btnEffWitch.setEnabled(false);
				spinnerAcc.setEnabled(true);
				btnAccuser.setEnabled(true);
				
				carte1.addMouseListener(new MouseAdapter() 
			      {
			         public void mouseEntered(MouseEvent evt) 
			         {
			            if(partie.getJouerJoueur().get(partie.getActuel()).main.size()>0) {
			            textField.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(0).nom);
			            textField_5.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(0).afficherEffetWitch(partie.getJouerJoueur().get(partie.getActuel()).main.get(0).nom));
			            txtarea.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(0).afficherEffetHunt(partie.getJouerJoueur().get(partie.getActuel()).main.get(0).nom));
			            spinnerChoixCarte.setEnabled(true);
			            spinnerChoixCarte.setModel(new SpinnerNumberModel(0, 0, 0, 1));
			            spinnerInq.setModel(new SpinnerNumberModel(0, 0, 0, 1));
			            }
			            else {
			            	textField.setText("Pas de carte");
			            	spinnerChoixCarte.setEnabled(false);
			            	btnEffWitch.setEnabled(false);
			            }
			         }
			         public void mouseExited(MouseEvent evt) 
			         {
			        	textField.setText("");
			        	textField_5.setText("");
			        	txtarea.setText("");
			         }
			      });
				carte2.addMouseListener(new MouseAdapter() 
			      {
			         public void mouseEntered(MouseEvent evt) 
			         {
			            if(partie.getJouerJoueur().get(partie.getActuel()).main.size()>1) {
			            	textField_1.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(1).nom);
			            	  textField_5.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(1).afficherEffetWitch(partie.getJouerJoueur().get(partie.getActuel()).main.get(1).nom));
			            	  txtarea.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(1).afficherEffetHunt(partie.getJouerJoueur().get(partie.getActuel()).main.get(1).nom));
					            spinnerChoixCarte.setEnabled(true);
					            spinnerChoixCarte.setModel(new SpinnerNumberModel(0, 0, 1, 1));
					            spinnerInq.setModel(new SpinnerNumberModel(0, 0, 1, 1));
			            }
			            else {
			            	textField_1.setText("Pas de 2ème carte");
			            }
			         }
			         public void mouseExited(MouseEvent evt) 
			         {
			        	 textField_1.setText("");
			        	 textField_5.setText("");
			        	 txtarea.setText("");
			         }
			      });
				
				carte3.addMouseListener(new MouseAdapter() 
			      {
			         public void mouseEntered(MouseEvent evt) 
			         {
			            if(partie.getJouerJoueur().get(partie.getActuel()).main.size()>2) {
			            	textField_2.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(2).nom);
			            	textField_5.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(2).afficherEffetWitch(partie.getJouerJoueur().get(partie.getActuel()).main.get(2).nom));
			            	txtarea.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(2).afficherEffetHunt(partie.getJouerJoueur().get(partie.getActuel()).main.get(2).nom));
				            spinnerChoixCarte.setEnabled(true);
				            spinnerChoixCarte.setModel(new SpinnerNumberModel(0, 0, 2, 1));
				            spinnerInq.setModel(new SpinnerNumberModel(0, 0, 2, 1));
			            }
			            else {
			            	textField_2.setText("Pas de 3ème carte");
			            }
			         }
			         public void mouseExited(MouseEvent evt) 
			         {
			        	 textField_2.setText("");
			        	 textField_5.setText("");
			        	 txtarea.setText("");
			         }
			      });
				
				carte4.addMouseListener(new MouseAdapter() 
			      {
			         public void mouseEntered(MouseEvent evt) 
			         {
			            if(partie.getJouerJoueur().get(partie.getActuel()).main.size()>3) {
			            	System.out.println("Il y a 4 cartes min ds sa main");
			            	textField_3.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(3).nom);
			            	  textField_5.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(3).afficherEffetWitch(partie.getJouerJoueur().get(partie.getActuel()).main.get(3).nom));
			            	  txtarea.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(3).afficherEffetHunt(partie.getJouerJoueur().get(partie.getActuel()).main.get(3).nom));
					            spinnerChoixCarte.setEnabled(true);
					            spinnerChoixCarte.setModel(new SpinnerNumberModel(0, 0, 3, 1));
					            spinnerInq.setModel(new SpinnerNumberModel(0, 0, 3, 1));
			            }
			            else {
			            	textField_3.setText("Pas de 4ème carte");
			            }
			         }
			         public void mouseExited(MouseEvent evt) 
			         {
			        	 textField_3.setText("");
			        	 textField_5.setText("");
			        	 txtarea.setText("");
			         }
			      });
				
				carte5.addMouseListener(new MouseAdapter() 
			      {
			         public void mouseEntered(MouseEvent evt) 
			         {
			            if(partie.getJouerJoueur().get(partie.getActuel()).main.size()>4) {
			            	textField_4.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(4).nom);
			            	  textField_5.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(4).afficherEffetWitch(partie.getJouerJoueur().get(partie.getActuel()).main.get(4).nom));
			            	  txtarea.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(4).afficherEffetHunt(partie.getJouerJoueur().get(partie.getActuel()).main.get(4).nom));
					           spinnerChoixCarte.setEnabled(true);
					           spinnerChoixCarte.setModel(new SpinnerNumberModel(0, 0, 4, 1));
					           spinnerInq.setModel(new SpinnerNumberModel(0, 0, 4, 1));
			            }
			            else {
			            	textField_4.setText("Pas de 5ème carte");
			            }
			         }
			         public void mouseExited(MouseEvent evt) 
			         {
			        	 textField_4.setText("");
			        	 textField_5.setText("");
			        	 txtarea.setText("");
			         }
			      });
		}
	}
}

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

import frr.utt.lo02.projet.Controleur.ControleurPartie;
import frr.utt.lo02.projet.Modele.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import java.util.Iterator;
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
/**
 * 
 * @author lou prevost, valentin guitton
 *
 */
/**
 * Cette classe est la seule vue utilis?e pour ce projet.
 * Elle correspond ? l'interface graphique qui va permettre aux joueurs de jouer ? Witch Hunt sans utiliser les inputs clavier et avec un jeu graphiquement plus attrayant que les lignes de commandes.
 * On retrouve dans cette classe diff?rents ?l?ments graphiques, un appel au controleur afin que ce dernier puisse d?tecter les actions faites sur l'interface graphique et puisse agir en cons?quence, ainsi qu'une m?thode de mise ? jour de l'interface quand un ?l?ment du mod?le est modifi?.
 * Afin que l'interface puisse op?rer un changement quand il y a une modification sur la partie, les joueurs ou encore les cartes, il faut qu'elle puisse observer ces objets, c'est pour cela qu'elle impl?mente l'interface Observer et sa m?thode update().
 * 
 */
public class IG_witch_hunt implements Observer{
	/**
	 * Cr?ation du cadre de l'interface graphique
	 */
	private JFrame frame;
	/**
	 * Texte demandant le nombre de joueurs physiques
	 */
	private JTextField txtCombienDeJoueurs;
	/**
	 * Texte demandant le nombre de joueurs virtuels
	 */
	private JTextField txtCombienDeJoueurs_1;
	/**
	 * Bouton qui lance la partie
	 */
	private JButton commencer1;
	/**
	 * Bouton permettant de donner le tour au joueur le plus jeune
	 */
	private JButton selectJ;
	/**
	 * Spinner qui permet d'indiquer le nombre de joueurs physiques
	 */
	private JSpinner choix_nb_jp;
	/**
	 * Spinner qui permet d'indiquer le nombre de joueurs virtuels
	 */
	private JSpinner choix_nb_jv;
	/**
	 * Spinner permettant d'indiquer quel joueur est le plus jeune, pour lui donner le tour
	 */
	private JSpinner spinner3;
	/**
	 * Spinner permettant d'indiquer quel joueur va ?tre accus?
	 */
	private JSpinner spinnerAcc;
	/**
	 * Affiche un message indiquant que le nombre de joueurs est correct et que la partie peut commencer
	 */
	private JTextPane debut_partie;
	/**
	 * Instance de la classe PartieV2, permettant de cr?er la partie
	 */
	private PartieV2 partie;
	/**
	 * Affiche un message indiquant que le nombre de joueurs n'est pas correct
	 */
	private JTextPane txtpnTropOuPas;
	/**
	 * Affiche un message indiquant quel est le joueur actuel, qui a le tour
	 */
	private JTextPane txtjac;
	/**
	 * Affiche un message indiquant o? agir pour utiliser un effet witch
	 */
	private JTextPane txtpnEffetWitch;
	/**
	 * Affiche un message indiquant o? agir pour utiliser un effet hunt
	 */
	private JTextPane txtpnEffetHunt;
	/**
	 * Carte 1 du joueur, quand on passe dessus le nom de la carte s'affiche ainsi que ses effets
	 */
	private JPanel carte1;
	/**
	 * Carte 2 du joueur, quand on passe dessus le nom de la carte s'affiche ainsi que ses effets
	 */
	private JPanel carte2;
	/**
	 * Carte 3 du joueur, quand on passe dessus le nom de la carte s'affiche ainsi que ses effets
	 */
	private JPanel carte3;
	/**
	 * Carte 4 du joueur, quand on passe dessus le nom de la carte s'affiche ainsi que ses effets
	 */
	private JPanel carte4;
	/**
	 * Carte 5 du joueur, quand on passe dessus le nom de la carte s'affiche ainsi que ses effets
	 */
	private JPanel carte5;
	/**
	 * Indique que le prochain spinner sert ? choisir l'id du joueur qui va choisir son r?le
	 */
	private JTextField txtJoueur;
	/**
	 * Indique que le prochain spinner sert ? choisir son r?le
	 */
	private JTextField txtRle;
	/**
	 * Spinner qui indique quel joueur va choisir son r?le
	 */
	private JSpinner chxj;
	/**
	 * Spinner qui indique quel r?le est choisi
	 */
	private JSpinner role;
	/**
	 * Spinner qui indique quel joueur est cibl? par l'effet witch
	 */
	public static JSpinner spinnerCibleWitch;
	/**
	 * Spinner qui indique quel joueur est cibl? par l'effet hunt
	 */
	public static JSpinner spinnerCibleHunt;
	/**
	 * Spinner qui indique la position dans la main du joueur de la carte dont l'effet hunt est utilis?
	 */
	private JSpinner spinnerCarteHunt;
	/**
	 * Spinner qui indique la position dans la main du joueur de la carte dont l'effet witch est utilis?
	 */
	private JSpinner spinnerChoixCarte;
	/**
	 * Permet ? un joueur de choisir son r?le
	 */
	private JButton chxrole;
	/**
	 * Affiche le nom de la 1?re carte du joueur quand il passe sur cette derni?re
	 */
	private JTextField textField;
	/**
	 * Affiche le nom de la 2?me carte du joueur quand il passe sur cette derni?re
	 */
	private JTextField textField_1;
	/**
	 * Affiche le nom de la 3?me carte du joueur quand il passe sur cette derni?re
	 */
	private JTextField textField_2;
	/**
	 * Affiche le nom de la 4?me carte du joueur quand il passe sur cette derni?re
	 */
	private JTextField textField_3;
	/**
	 * Affiche le nom de la 5?me carte du joueur quand il passe sur cette derni?re
	 */
	private JTextField textField_4;
	/**
	 * Affiche l'effet witch d'une carte du joueur quand il passe sur cette derni?re
	 */
	private JTextField textField_5;
	/**
	 * Affiche l'effet hunt d'une carte du joueur quand il passe sur cette derni?re
	 */	
	private TextArea txtarea;
	/**
	 * Bouton qui lance une accusation
	 */
	private JButton btnAccuser;
	/**
	 * Bouton qui permet de r?v?ler son r?le
	 */
	private JButton btnRevId;
	/**
	 * Bouton qui permet d'utiliser un effet witch
	 */
	private JButton btnEffWitch;
	/**
	 * Bouton qui permet ? un joueur accus? de voir sa main, sachant que celle affich?e avant appui sur ce bouton est celle du joueur actuel, qui est donc celui qui a accus?
	 */
	private JButton btnmain;
	/**
	 * Indique l'id et le r?le d'un joueur dont le r?le vient d'?tre r?v?l?
	 */
	public static JTextField textField_6;
	/**
	 * Texte indiquant que le nombre indiqu? ? sa droite correspond au nombre de joueurs dont le r?le a ?t? r?v?l?
	 */
	private JTextPane txtpnNombreDeJoueurs;
	/**
	 * Affiche le nombre de joueurs r?v?l?s
	 */
	private JTextField textField_7;
	/**
	 * Indique quel joueur est accus?
	 */
	private JTextField textField_8;
	/**
	 * Spinner permettant de d?finir quelle carte le joueur souhaite supprimer quand il utilise l'effet witch de "The Inquisition"
	 */
	private JSpinner spinnerInq;
	/**
	 * Integer permettant dans le controleur et dans la classe Cartes_Rumeurs de r?cup?rer la place dans la main du joueur de la carte qu'il souhaite supprimer via l'effet de "The Inquisition"
	 */
	public static int valueJSpinnerInq=-5;
	/**
	 * Spinner permettant d'indiquer quelle carte que le joueur a jou? il souhaite r?cup?rer (effet de Pointed Hat) ou quelle carte dans la main de celui qui l'a accus? il souhaite r?cup?rer (effet de Hooked Nose)
	 */
	public static JSpinner spinner_pointedh;
	/**
	 * Bouton permettant de r?cup?rer une carte en appliquant l'effet de Pointed Hat
	 */
	public static JButton btnr?cup;
	/**
	 * Bouton permettant de r?cup?rer une carte en appliquant l'effet de Hooked Nose
	 */
	public static JButton btnr?cuphooked;
	/**
	 * Bouton permettant d'utiliser l'effet hunt d'une carte
	 */
	private JButton btnEffetHunt;
	/**
	 * Affiche temporairement le r?le du joueur qui a ?t? cibl? par l'effet hunt de "The Inquisition"
	 */
	public static JTextField textField_9;
	/**
	 * Ce bouton permet de cacher le r?le du joueur cibl? par l'effet de hunt de "The Inquisition", cela permet de simuler l'action de "regarder discr?tement" le r?le d'un joueur
	 */
	private JButton btnCacher;
	/**
	 * Indique qu'on doit supprimer une carte ou d?voiler son r?le quand on est cibl? par l'effet hunt de Ducking Stool
	 */
	public static JTextField textField_10;
	/**
	 * Ce bouton permet ? un joueur de supprimer l'une de ses cartes s'il est cibl? par l'effet hunt de Ducking Stool
	 */
	public static JButton btnRevIdDuck;
	/**
	 * Ce bouton permet ? un joueur de r?v?ler son r?le s'il est cibl? par l'effet hunt de Ducking Stool
	 */
	public static JButton btnSuppDuck;
	/**
	 * Ce spinner permet de d?signer quelle carte un joueur cibl? par l'effet hunt de Ducking Stool souhaite supprimer
	 * Il permet ?galement de d?signer quelle carte souhaite r?cup?rer un joueur qui utilise les effets hunt de Black Cat ou Pet Newt
	 */
	public static JSpinner spinnerSuppDuck;
	/**
	 * Bouton qui permet de r?cup?rer une carte supprim?e
	 */
	public static JButton btnrecupBlackCat;
	/**
	 * Bouton qui permet de r?cup?rer une carte d?j? jou?e par un autre joueur
	 */
	public static JButton btnRecupPet;
	/**
	 * Texte qui pr?vient de la fin d'un round
	 */
	private JTextField textField_11;
	/**
	 * Affichage des scores, qui est mis ? jour ? la fin de chaque round
	 */
	private TextArea txtarea_1;
	/**
	 * Affichage du ou des vainqueur(s) ? la fin de la partie
	 */
	private TextArea txtarea_2;
	/**
	 * Indique quel spinner permet de choisir la carte ? supprimer quand on utilise l'effet witch de The Inquisition
	 */
	private JTextPane txtpnCarteSupprimer;
	/**
	 * Indique quel spinner permet de choisir la cible de l'effet hunt qui va ?tre utilis?
	 */
	private JTextPane txtpnCible;
	/**
	 * Indique quel spinner permet de choisir la cible de l'effet witch qui va ?tre utilis?
	 */
	private JTextPane txtpnCible_1;
	/**
	 * Indique quel spinner permet de choisir la carte dont l'effet hunt va ?tre utilis?
	 */
	private JTextPane txtpnCarte;
	/**
	 * Indique quel spinner permet de choisir la carte dont l'effet witch va ?tre utilis?
	 */
	private JTextPane txtpnCarte_1;
	

	/**
	 * Launch the application.
	 * La partie est cr??e
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
	 * Constructeur de la vue, o? l'on va ajouter les observateurs et cr?er le controleur en lui fournissant en param?tres l'interface, la partie, des boutons et des spinners
	 */
	public IG_witch_hunt(PartieV2 partie) {
		partie.setInterfaceg(true);
		initialize();
		this.partie=partie;
		this.partie.addObserver(this);
		for (int i = 0; i < this.partie.getJouerCarte().size(); i++) {
			Cartes_RumeursV2 c =this.partie.getJouerCarte().get(i);
			c.addObserver(this);
			}
	    for (int i = 0; i < this.partie.getJouerJoueur().size(); i++) {
			this.partie.getJouerJoueur().get(i).addObserver(this);
			}
	    
		new ControleurPartie(this,partie, commencer1, choix_nb_jp, choix_nb_jv, selectJ, spinner3, chxj, role, chxrole, btnAccuser, spinnerAcc, btnRevId, btnEffWitch, btnmain, spinnerCibleWitch, spinnerChoixCarte, spinnerInq, btnr?cup, btnr?cuphooked, btnEffetHunt,spinnerCibleHunt ,spinnerCarteHunt, btnCacher, btnRevIdDuck, btnSuppDuck, spinnerSuppDuck, btnrecupBlackCat, btnRecupPet);
				
	}

	/**
	 * Cr?ation, initialisation des diff?rents ?l?ments graphiques
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 990, 626);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtCombienDeJoueurs = new JTextField();
		txtCombienDeJoueurs.setText("Combien de joueurs physiques ?");
		txtCombienDeJoueurs.setBounds(0, 3, 192, 43);
		frame.getContentPane().add(txtCombienDeJoueurs);
		txtCombienDeJoueurs.setColumns(10);
		
		choix_nb_jp = new JSpinner();
		choix_nb_jp.setModel(new SpinnerNumberModel(1, 1, 6, 1));
		choix_nb_jp.setBounds(202, 14, 30, 20);
		frame.getContentPane().add(choix_nb_jp);
		
		txtCombienDeJoueurs_1 = new JTextField();
		txtCombienDeJoueurs_1.setText("Combien de joueurs virtuels ?");
		txtCombienDeJoueurs_1.setColumns(10);
		txtCombienDeJoueurs_1.setBounds(248, 3, 174, 43);
		frame.getContentPane().add(txtCombienDeJoueurs_1);
		
		choix_nb_jv = new JSpinner();
		choix_nb_jv.setModel(new SpinnerNumberModel(0, null, 5, 1));
		choix_nb_jv.setBounds(428, 14, 30, 20);
		frame.getContentPane().add(choix_nb_jv);
		
		commencer1 = new JButton("Commencer");
		commencer1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		commencer1.setBounds(468, 4, 114, 41);
		frame.getContentPane().add(commencer1);
		
		debut_partie = new JTextPane();
		debut_partie.setText("La partie peut commencer !!! Qui est le plus jeune ?");
		debut_partie.setBounds(602, 3, 162, 43);
		debut_partie.setVisible(false);
		frame.getContentPane().add(debut_partie);
		
		txtpnTropOuPas = new JTextPane();
		txtpnTropOuPas.setForeground(Color.RED);
		txtpnTropOuPas.setText("Trop ou pas assez de joueurs");
		txtpnTropOuPas.setVisible(false);
		txtpnTropOuPas.setBounds(774, 3, 200, 43);
		frame.getContentPane().add(txtpnTropOuPas);
		
		selectJ = new JButton("S?lectionner");
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
		textField_5.setBounds(10, 290, 893, 43);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
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
	            	textField_1.setText("Pas de 2?me carte");
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
	            	textField_2.setText("Pas de 3?me carte");
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
	            	textField_3.setText("Pas de 4?me carte");
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
	            	textField_4.setText("Pas de 5?me carte");
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
		txtpnEffetWitch.setBounds(10, 266, 99, 20);
		frame.getContentPane().add(txtpnEffetWitch);
		
		txtpnEffetHunt = new JTextPane();
		txtpnEffetHunt.setText("Effet Hunt :");
		txtpnEffetHunt.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnEffetHunt.setBounds(15, 374, 86, 20);
		frame.getContentPane().add(txtpnEffetHunt);
		
		btnAccuser = new JButton("Accuser");
		btnAccuser.setBounds(619, 93, 89, 23);
		frame.getContentPane().add(btnAccuser);
		
		
		btnRevId = new JButton("Reveler Id");
		btnRevId.setBounds(850, 193, 114, 23);
		frame.getContentPane().add(btnRevId);
		btnRevId.setEnabled(false);
		
		btnEffWitch = new JButton("Effet Witch");
		btnEffWitch.setBounds(619, 193, 114, 23);
		frame.getContentPane().add(btnEffWitch);
		btnEffWitch.setEnabled(false);
		
		textField_6 = new JTextField();
		textField_6.setBounds(10, 474, 242, 20);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		txtpnNombreDeJoueurs = new JTextPane();
		txtpnNombreDeJoueurs.setText("Nombre de joueurs r\u00E9v\u00E9l\u00E9s :");
		txtpnNombreDeJoueurs.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnNombreDeJoueurs.setBounds(0, 505, 192, 20);
		frame.getContentPane().add(txtpnNombreDeJoueurs);
		
		textField_7 = new JTextField();
		textField_7.setBounds(213, 505, 30, 20);
		frame.getContentPane().add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(753, 94, 162, 20);
		frame.getContentPane().add(textField_8);
		textField_8.setColumns(10);
		
		btnmain = new JButton("Afficher main");
		btnmain.setBounds(731, 149, 124, 23);
		frame.getContentPane().add(btnmain);
		
		spinnerCibleWitch = new JSpinner();
		spinnerCibleWitch.setBounds(757, 196, 30, 20);
		frame.getContentPane().add(spinnerCibleWitch);
		
		spinnerChoixCarte = new JSpinner();
		spinnerChoixCarte.setBounds(815, 194, 30, 20);
		frame.getContentPane().add(spinnerChoixCarte);
	
		spinnerInq = new JSpinner();
		spinnerInq.setBounds(778, 259, 125, 20);
		spinnerInq.setModel(new SpinnerNumberModel(0, 0, 4, 1));
		frame.getContentPane().add(spinnerInq);
		
		spinner_pointedh = new JSpinner();
		spinner_pointedh.setBounds(403, 259, 143, 20);
		frame.getContentPane().add(spinner_pointedh);
		
		btnr?cup = new JButton("R\u00E9cup Pointed");
		btnr?cup.setBounds(269, 256, 125, 23);
		frame.getContentPane().add(btnr?cup);
		btnr?cup.setEnabled(false);
		
		btnr?cuphooked = new JButton("Recup Hooked");
		btnr?cuphooked.setBounds(560, 256, 125, 23);
		frame.getContentPane().add(btnr?cuphooked);
		btnr?cuphooked.setEnabled(false);
		
		btnEffetHunt = new JButton("Effet Hunt");
		btnEffetHunt.setBounds(619, 117, 89, 23);
		frame.getContentPane().add(btnEffetHunt);
		
		txtpnCarteSupprimer = new JTextPane();
		txtpnCarteSupprimer.setText("Carte \u00E0 supprimer (\"The Inquisition\") :");
		txtpnCarteSupprimer.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnCarteSupprimer.setBounds(722, 238, 242, 20);
		frame.getContentPane().add(txtpnCarteSupprimer);
		
		spinnerCibleHunt = new JSpinner();
		spinnerCibleHunt.setBounds(771, 118, 30, 20);
		frame.getContentPane().add(spinnerCibleHunt);
		
		spinnerCarteHunt = new JSpinner();
		spinnerCarteHunt.setBounds(885, 118, 30, 20);
		frame.getContentPane().add(spinnerCarteHunt);
		
		textField_9 = new JTextField();
		textField_9.setBounds(266, 474, 181, 20);
		frame.getContentPane().add(textField_9);
		textField_9.setColumns(10);
		
		btnCacher = new JButton("Cacher");
		btnCacher.setBounds(315, 505, 89, 23);
		frame.getContentPane().add(btnCacher);
		
		textField_10 = new JTextField();
		textField_10.setBounds(109, 374, 225, 20);
		frame.getContentPane().add(textField_10);
		textField_10.setColumns(10);
		
		btnRevIdDuck = new JButton("R\u00E9v\u00E9ler Id");
		btnRevIdDuck.setBounds(335, 371, 101, 23);
		frame.getContentPane().add(btnRevIdDuck);
		btnRevIdDuck.setEnabled(false);
		
		btnSuppDuck = new JButton("Supprimer");
		btnSuppDuck.setBounds(437, 371, 109, 23);
		frame.getContentPane().add(btnSuppDuck);
		btnSuppDuck.setEnabled(false);
		
		spinnerSuppDuck = new JSpinner();
		spinnerSuppDuck.setBounds(593, 359, 128, 20);
		frame.getContentPane().add(spinnerSuppDuck);
		
		btnRecupPet = new JButton("R\u00E9cup\u00E9rer Pet Newt");
		btnRecupPet.setBounds(731, 371, 150, 23);
		frame.getContentPane().add(btnRecupPet);
		btnRecupPet.setEnabled(false);
		
		btnrecupBlackCat = new JButton("R\u00E9cup\u00E9rer Black Cat");
		btnrecupBlackCat.setBounds(731, 344, 150, 23);
		frame.getContentPane().add(btnrecupBlackCat);
		btnrecupBlackCat.setEnabled(false);
		
		txtarea = new TextArea();
		txtarea.setEnabled(false);
		txtarea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtarea.setBounds(10, 400, 893, 68);
		frame.getContentPane().add(txtarea);
		txtarea.setColumns(10);
		
		textField_11 = new JTextField();
		textField_11.setBounds(698, 474, 205, 29);
		frame.getContentPane().add(textField_11);
		textField_11.setColumns(10);
		
		txtarea_2 = new TextArea();
		txtarea_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txtarea_2.setEnabled(false);
		txtarea_2.setColumns(10);
		txtarea_2.setBounds(151, 228, 670, 166);
		frame.getContentPane().add(txtarea_2);
		txtarea_2.setVisible(false);
		
		txtarea_1 = new TextArea();
		txtarea_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtarea_1.setEnabled(false);
		txtarea_1.setColumns(10);
		txtarea_1.setBounds(480, 469, 205, 108);
		frame.getContentPane().add(txtarea_1);
		
		txtpnCible = new JTextPane();
		txtpnCible.setText("Cible");
		txtpnCible.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnCible.setBounds(750, 171, 37, 20);
		frame.getContentPane().add(txtpnCible);
		
		txtpnCarte = new JTextPane();
		txtpnCarte.setText("Carte");
		txtpnCarte.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnCarte.setBounds(805, 171, 40, 20);
		frame.getContentPane().add(txtpnCarte);
		
		txtpnCible_1 = new JTextPane();
		txtpnCible_1.setText("Cible");
		txtpnCible_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnCible_1.setBounds(716, 118, 48, 20);
		frame.getContentPane().add(txtpnCible_1);
		
		txtpnCarte_1 = new JTextPane();
		txtpnCarte_1.setText("Carte");
		txtpnCarte_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtpnCarte_1.setBounds(827, 118, 48, 20);
		frame.getContentPane().add(txtpnCarte_1);
	}
	/**
	 * M?thode qui contr?le la mise ? jour de la vue en fonction de modifications intervenant au niveau des mod?les
	 */
	public void update(Observable instanceObservable, Object arg1){
		if(instanceObservable instanceof PartieV2) {
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
			
			if ((Integer)chxj.getValue()==partie.getJouerJoueur().size()-1 && partie.getJouerJoueur().size()!=0) {
				chxj.setVisible(false);
				txtJoueur.setVisible(false);
				txtRle.setVisible(false);
				chxrole.setVisible(false);
				role.setVisible(false);
			}
			
			
			if(partie.getAccus?()!=-1) {
			if(partie.getJouerJoueur().get(partie.getAccus?()).getReveler()==true || partie.getJouerJoueur().get(partie.getActuel()).getReveler()==true) {
				if(partie.getJouerJoueur().get(partie.getAccus?()).getRole()==true || partie.getJouerJoueur().get(partie.getActuel()).getRole()==true) {
					IG_witch_hunt.textField_6.setText("Le joueur "+partie.getAccus?()+" est une Witch");
				}
				else {
					IG_witch_hunt.textField_6.setText("Le joueur "+partie.getAccus?()+" est un Villager");
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
			            	textField_1.setText("Pas de 2?me carte");
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
			            	textField_2.setText("Pas de 3?me carte");
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
			            	textField_3.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(3).nom);
			            	  textField_5.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(3).afficherEffetWitch(partie.getJouerJoueur().get(partie.getActuel()).main.get(3).nom));
			            	  txtarea.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(3).afficherEffetHunt(partie.getJouerJoueur().get(partie.getActuel()).main.get(3).nom));
					            spinnerChoixCarte.setEnabled(true);
					            spinnerChoixCarte.setModel(new SpinnerNumberModel(0, 0, 3, 1));
					            spinnerInq.setModel(new SpinnerNumberModel(0, 0, 3, 1));
			            }
			            else {
			            	textField_3.setText("Pas de 4?me carte");
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
			            	textField_4.setText("Pas de 5?me carte");
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
				btnEffetHunt.setEnabled(true);
				btnRevId.setEnabled(false);
				btnEffWitch.setEnabled(false);
				spinnerAcc.setEnabled(true);
				textField_8.setText("");
				textField_10.setText("");
				btnRevIdDuck.setEnabled(false);
				btnSuppDuck.setEnabled(false);
				spinnerSuppDuck.setEnabled(false);
				textField_7.setText(""+partie.getComptR?v?l?s()+"");
				if (partie.getComptR?v?l?s()==(partie.getnbBots()+partie.getnbJoueursPhys()-1)){
					partie.setComptR?v?l?s(0);
					
					
					textField_11.setText("Fin du round");
					btnCacher.setEnabled(false);
					//btnmain.setEnabled(false);
					//btnEffetHunt.setEnabled(false);
					//selectJ.setEnabled(false);
					//btnAccuser.setEnabled(false);
					commencer1.setEnabled(true);
					chxj.setVisible(true);
					txtJoueur.setVisible(true);
					txtRle.setVisible(true);
					role.setVisible(true);
					chxrole.setVisible(true);
					//on remet les attributs des joueurs ? jour et on donne le tour au joueur non r?v?l?
					int scoremax=0;
					for (int i=0; i<(partie.getnbBots()+partie.getnbJoueursPhys()); i++) {
						if(partie.getJouerJoueur().get(i).getReveler()==false) {
							partie.setActuel(i);
							if(partie.getJouerJoueur().get(i).getRole()==true) {
								partie.ajoutPoint(i);
								partie.ajoutPoint(i);
							}
							else {
								partie.ajoutPoint(i);
							}
							if(partie.getJouerJoueur().get(i).getPoints()>scoremax) {
								scoremax=partie.getJouerJoueur().get(i).getPoints();
							}
						}
						partie.getJouerJoueur().get(i).setReveler(false);
						partie.getJouerJoueur().get(i).setEnJeu(true);
					}
					
switch (partie.getnbBots()+partie.getnbJoueursPhys()) {
					
					case 3: txtarea_1.setText("SCORES : \nJ0 : "+partie.getJouerJoueur().get(0).getPoints()+"\nJ1 : "+partie.getJouerJoueur().get(1).getPoints()+"\nJ2 : "+partie.getJouerJoueur().get(2).getPoints());
						break;
					case 4: txtarea_1.setText("SCORES : \nJ0 : "+partie.getJouerJoueur().get(0).getPoints()+"\nJ1 : "+partie.getJouerJoueur().get(1).getPoints()+"\nJ2 : "+partie.getJouerJoueur().get(2).getPoints()+"\nJ3 : "+partie.getJouerJoueur().get(3).getPoints());
						break;
					case 5: txtarea_1.setText("SCORES : \nJ0 : "+partie.getJouerJoueur().get(0).getPoints()+"\nJ1 : "+partie.getJouerJoueur().get(1).getPoints()+"\nJ2 : "+partie.getJouerJoueur().get(2).getPoints()+"\nJ3 : "+partie.getJouerJoueur().get(3).getPoints()+"\nJ4 : "+partie.getJouerJoueur().get(4).getPoints());
						break;
					case 6: txtarea_1.setText("SCORES : \nJ0 : "+partie.getJouerJoueur().get(0).getPoints()+"\nJ1 : "+partie.getJouerJoueur().get(1).getPoints()+"\nJ2 : "+partie.getJouerJoueur().get(2).getPoints()+"\nJ3 : "+partie.getJouerJoueur().get(3).getPoints()+"\nJ4 : "+partie.getJouerJoueur().get(4).getPoints()+"\nJ5 : "+partie.getJouerJoueur().get(5).getPoints());
						break;
					
					}
					if(scoremax>4) {
						txtarea_2.setText("Fin de la partie : Vainqueur(s) :");
						for (int i=0; i<(partie.getnbBots()+partie.getnbJoueursPhys()); i++) {
							if(partie.getJouerJoueur().get(i).getPoints()==scoremax) {
								txtarea_2.append(" J"+i+" ");
							}
						}
						
						txtCombienDeJoueurs.setVisible(false);
						
						choix_nb_jp.setVisible(false);
						
						txtCombienDeJoueurs_1.setVisible(false);
						
						choix_nb_jv.setVisible(false);
						
						commencer1.setVisible(false);
						
						debut_partie.setVisible(false);
						debut_partie.setText("Belle partie !!!");
						
						txtpnTropOuPas.setVisible(false);
						
						selectJ.setVisible(false);
						
						spinner3.setVisible(false);
						
						spinnerAcc.setVisible(false);
						
						txtjac.setVisible(false);
						
						role.setVisible(false);
						
						chxj.setVisible(false);
						
						txtJoueur.setVisible(false);
						
						txtRle.setVisible(false);
						
						chxrole.setVisible(false);
						
						textField_5.setVisible(false);
						
						carte1.setVisible(false);
						
						textField.setVisible(false);
						
					     
						carte2.setVisible(false);
						textField_1.setVisible(false);
					    
						
						carte3.setVisible(false);
						
						textField_2.setVisible(false);
						
						carte4.setVisible(false);
						
						textField_3.setVisible(false);

						carte5.setVisible(false);
						
						textField_4.setVisible(false);
						
						txtpnEffetWitch.setVisible(false);
						
						txtpnEffetHunt.setVisible(false);
						
						btnAccuser.setVisible(false);
						
						
						btnRevId.setVisible(false);
						
						btnEffWitch.setVisible(false);
						
						textField_6.setVisible(false);
						
						txtpnNombreDeJoueurs.setVisible(false);
						
						textField_7.setVisible(false);
						
						textField_8.setVisible(false);
						
						btnmain.setVisible(false);
						
						spinnerCibleWitch.setVisible(false);
						
						spinnerChoixCarte.setVisible(false);
					
						spinnerInq.setVisible(false);
						
						spinner_pointedh.setVisible(false);
						
						btnr?cup.setVisible(false);
						
						btnr?cuphooked.setVisible(false);
						
						btnEffetHunt.setVisible(false);
						
						txtpnCarteSupprimer.setVisible(false);
						
						spinnerCibleHunt.setVisible(false);
						
						spinnerCarteHunt.setVisible(false);
						
						textField_9.setVisible(false);
						
						btnCacher.setVisible(false);
						
						textField_10.setVisible(false);
						
						btnRevIdDuck.setVisible(false);
						
						btnSuppDuck.setVisible(false);
						
						spinnerSuppDuck.setVisible(false);
						
						btnRecupPet.setVisible(false);
						
						btnrecupBlackCat.setVisible(false);
						
						txtarea.setVisible(false);
						
						textField_11.setVisible(false);
						
						txtarea_1.setVisible(false);
						
						txtarea_2.setVisible(false);

						txtarea_2.setVisible(true);
						
						txtpnCible.setVisible(false);
						
						txtpnCible_1.setVisible(false);
						
						txtpnCarte.setVisible(false);
						
						txtpnCarte_1.setVisible(false);
					}
					

					//vider les mains des joueurs et les listes cartes d?fauss?es et cartesjou?es
					partie.getCartesDefauss?es().clear();
					for (int i=0; i<(partie.getnbBots()+partie.getnbJoueursPhys()); i++) {
						partie.getJouerJoueur().get(i).getCartesJouees().clear();
						partie.getJouerJoueur().get(i).getMain().clear();
					}
					//faire les memes actions que continuer mais sans ajouter de nouveaux joueurs ni cr?er le packet de cartes
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
			    		partie.getCartesDefauss?es().add(partie.getJouerCarte2().get(10));
			    		partie.getCartesDefauss?es().add(partie.getJouerCarte2().get(11));
			    	}
			    	for (int i = 0; i < partie.getJouerCarte2().size(); i++) {
						Cartes_RumeursV2 c =partie.getJouerCarte2().get(i);
						c.addObserver(this);
						}
			    	 for (int i = 0; i < partie.getJouerJoueur2().size(); i++) {
			 			partie.getJouerJoueur2().get(i).addObserver(this);
			 			}
				}
			}
		}

		}
		else if (instanceObservable instanceof Joueur_Physique_ou_VirtuelV2) {
			if ((Integer)chxj.getValue()==partie.getJouerJoueur().size()-1 && partie.getJouerJoueur().size()!=0) {
				chxj.setVisible(false);
				txtJoueur.setVisible(false);
				txtRle.setVisible(false);
				chxrole.setVisible(false);
				role.setVisible(false);
			}
			if(partie.getAccus?() != -1) {
			if(partie.getJouerJoueur().get(partie.getAccus?()).getEstAccus?()==true) {
				partie.getJouerJoueur().get(partie.getAccus?()).setEstAccus?(false);
				btnRevId.setEnabled(true);
				btnEffWitch.setEnabled(true);
				spinnerAcc.setEnabled(false);
				btnAccuser.setEnabled(false);
				btnEffetHunt.setEnabled(false);
				textField_8.setText(partie.getAccus?()+" est accus?");
			}
			if(partie.getJouerJoueur().get(partie.getAccus?()).getRegardeMain()==true) {
				partie.getJouerJoueur().get(partie.getAccus?()).setRegardeMain(false);
				carte1.addMouseListener(new MouseAdapter() 
			      {
			         public void mouseEntered(MouseEvent evt) 
			         {
			            if(partie.getJouerJoueur().get(partie.getAccus?()).main.size()>0) {
			            textField.setText(partie.getJouerJoueur().get(partie.getAccus?()).main.get(0).nom);
			            textField_5.setText(partie.getJouerJoueur().get(partie.getAccus?()).main.get(0).afficherEffetWitch(partie.getJouerJoueur().get(partie.getAccus?()).main.get(0).nom));
			            txtarea.setText(partie.getJouerJoueur().get(partie.getAccus?()).main.get(0).afficherEffetHunt(partie.getJouerJoueur().get(partie.getAccus?()).main.get(0).nom));
			            spinnerChoixCarte.setEnabled(true);
			            spinnerChoixCarte.setModel(new SpinnerNumberModel(0, 0, 0, 1));
			            spinnerInq.setModel(new SpinnerNumberModel(0, 0, 0, 1));
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
			            if(partie.getJouerJoueur().get(partie.getAccus?()).main.size()>1) {
			            	textField_1.setText(partie.getJouerJoueur().get(partie.getAccus?()).main.get(1).nom);
			            	  textField_5.setText(partie.getJouerJoueur().get(partie.getAccus?()).main.get(1).afficherEffetWitch(partie.getJouerJoueur().get(partie.getAccus?()).main.get(1).nom));
			            	  txtarea.setText(partie.getJouerJoueur().get(partie.getAccus?()).main.get(1).afficherEffetHunt(partie.getJouerJoueur().get(partie.getAccus?()).main.get(1).nom));
			            	  spinnerChoixCarte.setEnabled(true);
					            spinnerChoixCarte.setModel(new SpinnerNumberModel(0, 0, 1, 1));
					            spinnerInq.setModel(new SpinnerNumberModel(0, 0, 1, 1));
			            }
			            else {
			            	textField_1.setText("Pas de 2?me carte");
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
			            if(partie.getJouerJoueur().get(partie.getAccus?()).main.size()>2) {
			            	textField_2.setText(partie.getJouerJoueur().get(partie.getAccus?()).main.get(2).nom);
			            	textField_5.setText(partie.getJouerJoueur().get(partie.getAccus?()).main.get(2).afficherEffetWitch(partie.getJouerJoueur().get(partie.getAccus?()).main.get(2).nom));
			            	txtarea.setText(partie.getJouerJoueur().get(partie.getAccus?()).main.get(2).afficherEffetHunt(partie.getJouerJoueur().get(partie.getAccus?()).main.get(2).nom));
			            	spinnerChoixCarte.setEnabled(true);
				            spinnerChoixCarte.setModel(new SpinnerNumberModel(0, 0, 2, 1));
				            spinnerInq.setModel(new SpinnerNumberModel(0, 0, 2, 1));
			            }
			            else {
			            	textField_2.setText("Pas de 3?me carte");
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
			            if(partie.getJouerJoueur().get(partie.getAccus?()).main.size()>3) {
			            	textField_3.setText(partie.getJouerJoueur().get(partie.getAccus?()).main.get(3).nom);
			            	  textField_5.setText(partie.getJouerJoueur().get(partie.getAccus?()).main.get(3).afficherEffetWitch(partie.getJouerJoueur().get(partie.getAccus?()).main.get(3).nom));
			            	  txtarea.setText(partie.getJouerJoueur().get(partie.getAccus?()).main.get(3).afficherEffetHunt(partie.getJouerJoueur().get(partie.getAccus?()).main.get(3).nom));
			            	  spinnerChoixCarte.setEnabled(true);
					            spinnerChoixCarte.setModel(new SpinnerNumberModel(0, 0, 3, 1));
					            spinnerInq.setModel(new SpinnerNumberModel(0, 0, 3, 1));
			            }
			            else {
			            	textField_3.setText("Pas de 4?me carte");
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
			            if(partie.getJouerJoueur().get(partie.getAccus?()).main.size()>4) {
			            	textField_4.setText(partie.getJouerJoueur().get(partie.getAccus?()).main.get(4).nom);
			            	  textField_5.setText(partie.getJouerJoueur().get(partie.getAccus?()).main.get(4).afficherEffetWitch(partie.getJouerJoueur().get(partie.getAccus?()).main.get(4).nom));
			            	  txtarea.setText(partie.getJouerJoueur().get(partie.getAccus?()).main.get(4).afficherEffetHunt(partie.getJouerJoueur().get(partie.getAccus?()).main.get(4).nom));
			            	  spinnerChoixCarte.setEnabled(true);
					           spinnerChoixCarte.setModel(new SpinnerNumberModel(0, 0, 4, 1));
					           spinnerInq.setModel(new SpinnerNumberModel(0, 0, 4, 1));
			            }
			            else {
			            	textField_4.setText("Pas de 5?me carte");
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
				btnEffetHunt.setEnabled(true);
				
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
			            	textField_1.setText("Pas de 2?me carte");
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
			            	textField_2.setText("Pas de 3?me carte");
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
			            	textField_3.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(3).nom);
			            	  textField_5.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(3).afficherEffetWitch(partie.getJouerJoueur().get(partie.getActuel()).main.get(3).nom));
			            	  txtarea.setText(partie.getJouerJoueur().get(partie.getActuel()).main.get(3).afficherEffetHunt(partie.getJouerJoueur().get(partie.getActuel()).main.get(3).nom));
					            spinnerChoixCarte.setEnabled(true);
					            spinnerChoixCarte.setModel(new SpinnerNumberModel(0, 0, 3, 1));
					            spinnerInq.setModel(new SpinnerNumberModel(0, 0, 3, 1));
			            }
			            else {
			            	textField_3.setText("Pas de 4?me carte");
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
			            	textField_4.setText("Pas de 5?me carte");
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

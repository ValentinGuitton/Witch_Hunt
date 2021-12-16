package frr.utt.lo02.projet.Vue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import Controleur.ControleurPartie;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import frr.utt.lo02.projet.Modele.*;

public class IG_witch_hunt implements Observer{

	private JFrame frame;
	private JTextField txtCombienDeJoueurs;
	private JTextField txtCombienDeJoueurs_1;
	private JButton commencer1;
	private JSpinner choix_nb_jp;
	private JSpinner choix_nb_jv;
	private JTextPane debut_partie;
	private PartieV2 partie;
	private JTextPane txtpnTropOuPas;

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
		/*for (int i = 0; i < this.partie.getJouerJoueur().size(); i++) {
			this.partie.getJouerJoueur().get(i).addObserver(this);
			System.out.println("Les joueurs observent");
	    }
		for (int i = 0; i < this.partie.getJouerCarte().size(); i++) {
			this.partie.getJouerCarte().get(i).addObserver(this);
	    }*/
		new ControleurPartie(this.partie, commencer1, choix_nb_jp, choix_nb_jv);
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
		txtCombienDeJoueurs.setBounds(10, 11, 172, 26);
		frame.getContentPane().add(txtCombienDeJoueurs);
		txtCombienDeJoueurs.setColumns(10);
		
		choix_nb_jp = new JSpinner();
		choix_nb_jp.setModel(new SpinnerNumberModel(1, 1, 6, 1));
		choix_nb_jp.setBounds(192, 14, 30, 20);
		frame.getContentPane().add(choix_nb_jp);
		
		txtCombienDeJoueurs_1 = new JTextField();
		txtCombienDeJoueurs_1.setText("Combien de joueurs virtuels ?");
		txtCombienDeJoueurs_1.setColumns(10);
		txtCombienDeJoueurs_1.setBounds(244, 11, 150, 26);
		frame.getContentPane().add(txtCombienDeJoueurs_1);
		
		choix_nb_jv = new JSpinner();
		choix_nb_jv.setModel(new SpinnerNumberModel(0, null, 5, 1));
		choix_nb_jv.setBounds(404, 14, 30, 20);
		frame.getContentPane().add(choix_nb_jv);
		
		commencer1 = new JButton("Commencer");
		commencer1.setBounds(444, 13, 89, 23);
		frame.getContentPane().add(commencer1);
		
		debut_partie = new JTextPane();
		debut_partie.setText("La partie peut commencer !!!");
		debut_partie.setBounds(543, 11, 146, 26);
		debut_partie.setVisible(false);
		frame.getContentPane().add(debut_partie);
		
		txtpnTropOuPas = new JTextPane();
		txtpnTropOuPas.setText("Trop ou pas assez de joueurs");
		txtpnTropOuPas.setVisible(false);
		txtpnTropOuPas.setBounds(715, 11, 166, 20);
		frame.getContentPane().add(txtpnTropOuPas);
	}
	
	public void update(Observable instanceObservable, Object arg1){
		System.out.println("On est dans le update()");
		if(instanceObservable instanceof PartieV2) {
			System.out.println("Avant le switch");
			switch(((PartieV2)instanceObservable).getnbJoueursPhys()+((PartieV2)instanceObservable).getnbBots()) {
			case 1:txtpnTropOuPas.setVisible(true);break;
			case 2:txtpnTropOuPas.setVisible(true);break;
			case 3:debut_partie.setVisible(true);break;
			case 4:debut_partie.setVisible(true);break;
			case 5:debut_partie.setVisible(true);break;
			case 6:debut_partie.setVisible(true);break;
			case 7:txtpnTropOuPas.setVisible(true);break;
			case 8:txtpnTropOuPas.setVisible(true);break;
			case 9:txtpnTropOuPas.setVisible(true);break;
			case 10:txtpnTropOuPas.setVisible(true);break;
			case 11:txtpnTropOuPas.setVisible(true);break;
			}
		}
	};
}

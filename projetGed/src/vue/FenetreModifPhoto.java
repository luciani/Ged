package vue;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

import modele.Document;
//TODO fenetre a finir le informations actuelles sont celles de FenetreInfo
public class FenetreModifPhoto extends JDialog{
	
	private JPanel container = new JPanel();
	private JPanel noms = new JPanel();
	
	public FenetreModifPhoto(Document doc){
		this.setTitle("WamS");
		this.setSize(800, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		//this.setBackground(Color.blue);
		this.setModal(true);
		
		container.setLayout(new BorderLayout());

		this.setContentPane(container);
	}
}

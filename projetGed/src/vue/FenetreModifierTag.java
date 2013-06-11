package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FenetreModifierTag extends JFrame{
	private JButton ok = new JButton("OK");
	private JButton annuler = new JButton("Annuler");
	
	private JPanel zoneBouton = new JPanel();
	private JPanel zoneTexte = new JPanel();
	
	private JTextField nouveauTag = new JTextField();
	private JTextField tagAModifier = new JTextField();
	
	public FenetreModifierTag(){
		this.setTitle("Modifier un tag");
		this.setMinimumSize(new Dimension(300,200));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		
		this.setLayout(new BorderLayout());
		
		this.add(zoneBouton, BorderLayout.SOUTH);
		this.add(zoneTexte, BorderLayout.CENTER);
		
		zoneTexte.setLayout(new GridLayout(2,1));
		
		zoneTexte.add(nouveauTag);
		zoneTexte.add(tagAModifier);
		
		zoneBouton.add(ok);
		zoneBouton.add(annuler);
		
		this.setVisible(true);
	}
}
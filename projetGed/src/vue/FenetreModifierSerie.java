package vue;

import java.awt.Dimension;

import javax.swing.JFrame;

public class FenetreModifierSerie extends JFrame{
	
	public FenetreModifierSerie(){
		this.setTitle("Modifier une série");
		this.setMinimumSize(new Dimension(300,200));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		
		this.setVisible(true);
	}
}

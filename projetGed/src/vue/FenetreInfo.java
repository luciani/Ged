package vue;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FenetreInfo extends JDialog{

	private JPanel container = new JPanel();
	private JPanel noms = new JPanel();
	public FenetreInfo(){
		
		this.setTitle("WamS");
		this.setSize(400, 200);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setBackground(Color.blue);
		this.setModal(true);
		
		container.setLayout(new BorderLayout());
		
		container.add(new JLabel("WamS est un logiciel créé par:",JLabel.CENTER),BorderLayout.NORTH);
		container.add(noms);
		noms.add(new JLabel("Arnaud Luciani,"));
		noms.add(new JLabel(" Myriam Naha,"));
		noms.add(new JLabel(" Syrine Garbaya,"));
		noms.add(new JLabel(" Victor Ros,"));
		noms.add(new JLabel(" Vincent Vassout"));
		
		this.setContentPane(container);
	}
	
}

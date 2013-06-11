/*package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import modele.FenetreModel;
import modele.Serie;

public class FenetreAjouterDocAUneSerie extends JFrame {
	private JButton valider = new JButton("Valider");
	private JPanel container = new JPanel();
	private JComboBox combo = new JComboBox();
	private JLabel label = new JLabel("Liste des séries :");
	
	public FenetreAjouterDocAUneSerie(final FenetreModel fmodel,final JPanel tableauImage){
		this.setTitle("Ajouter un document à une série");
		this.setMinimumSize(new Dimension(300,200));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		
		container.setLayout(new BorderLayout());
		combo.setPreferredSize(new Dimension(100, 20));
		JPanel top = new JPanel();
		top.add(label);
		top.add(combo);
		container.add(top, BorderLayout.NORTH);
		container.add(valider,BorderLayout.SOUTH);
		
		this.setContentPane(container);
		this.setVisible(true);
	
		valider.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				TableModel t= (DefaultTableModel) tableauImage.getModel();
				int ligneSel = tableauImage.getSelectedRow();
				Serie s = new Serie((String)combo.getSelectedItem());
				
				for(int i =0; i<fmodel.getDocs().size(); i++){
					if(fmodel.getDocs().get(i).equals(fmodel.getDocs().get(ligneSel))){
						fmodel.getDocs().get(i).AjoutSerie(s);
						for(int j= 0; j<fmodel.getSeries().size(); j++){
							if(fmodel.getSeries().get(i).equals(s)){
								fmodel.getSeries().get(i).addDocument(fmodel.getDocs().get(i));
							}
						}
					}
				}
				
			
			}
		);
	
	}
}*/

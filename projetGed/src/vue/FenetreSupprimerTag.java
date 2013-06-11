package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import modele.Document;
import modele.FenetreModel;

public class FenetreSupprimerTag extends JDialog{
	
	private JButton ok = new JButton("OK");
	private JButton annuler = new JButton("Annuler");
	
	private JPanel zoneBouton = new JPanel();
	private JPanel zoneTexte = new JPanel();
	
	private JTextField TagASupprimer = new JTextField();
	
	public FenetreSupprimerTag(final FenetreModel fmodel,final JTable tableauImage){
		this.setTitle("Supprimer un tag");
		this.setMinimumSize(new Dimension(300,200));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		
		this.setLayout(new BorderLayout());
		
		this.add(zoneBouton, BorderLayout.SOUTH);
		this.add(zoneTexte, BorderLayout.CENTER);
		
		zoneTexte.setLayout(new BorderLayout());
		
		zoneTexte.add(TagASupprimer);
		
		zoneBouton.add(ok);
		zoneBouton.add(annuler);
		
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if(!(TagASupprimer.getText().equals(null) ||TagASupprimer.getText().equals(""))){
					
					String tagSup=TagASupprimer.getText();
					int ligneSelectionne = tableauImage.getSelectedRow();
					String cheminPhoto=(String)tableauImage.getValueAt(ligneSelectionne, 0);
		     
				     Document doc= fmodel.trouveParAdresse(cheminPhoto);
				     
				     //On cherche le tag
				     for(int i=0; i<doc.getTags().size();++i){
				    	 if(tagSup.equals(doc.getTags().get(i).getNom())){
				    		
				     //Suppression du doc dans la liste des tags
				    	 	 for(int j=0;j<fmodel.getTags().size();j++ ){
				    	 		 if((fmodel.getTags().get(j).getNom().equals(tagSup))){
				    	 			 for(int z=0;z<fmodel.getTags().get(j).getDocs().size();z++){
				    	 				 if(fmodel.getTags().get(j).getDocs().get(z).equals(doc)){
				    	 					fmodel.getTags().get(j).getDocs().remove(z);
				    	 				 }
				    	 			 }
				    	 		 }
				    	 	 }
				    	 	 //Suppression du tag dans la liste des docs
				    	 	 doc.getTags().remove(i);
				    	 	 
				    	 	if((fmodel.getDocs().get(i)).equals(fmodel.getDocs().get(ligneSelectionne)))
				    	 	 fmodel.getDocs().get(0).getTags().remove(0);
				    	 }
				     }
				     
				}
			}
		});
		
		annuler.addActionListener(new ActionListener(){	
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		this.setVisible(true);
	}
}
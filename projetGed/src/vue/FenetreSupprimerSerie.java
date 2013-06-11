package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import modele.Document;
import modele.FenetreModel;

public class FenetreSupprimerSerie extends JFrame{
	private JButton ok = new JButton("OK");
	private JButton annuler = new JButton("Annuler");
	
	private JPanel zoneBouton = new JPanel();
	private JPanel zoneTexte = new JPanel();
	
	private JTextField SerieASupprimer = new JTextField();
	
	public FenetreSupprimerSerie(final FenetreModel fmodel,final JTable tableauImage){
		this.setTitle("Supprimer une serie");
		this.setMinimumSize(new Dimension(300,200));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		
		this.setLayout(new BorderLayout());
		
		this.add(zoneBouton, BorderLayout.SOUTH);
		this.add(zoneTexte, BorderLayout.CENTER);
		
		zoneTexte.setLayout(new BorderLayout());
		
		zoneTexte.add(SerieASupprimer);
		
		zoneBouton.add(ok);
		zoneBouton.add(annuler);
		
		
		
		this.setVisible(true);
		
		
		 int ligneSelectionne = tableauImage.getSelectedRow();
	     String cheminPhoto=(String)tableauImage.getValueAt(ligneSelectionne, 0);
	     
	     Document doc= fmodel.trouveParAdresse(cheminPhoto);
	     String serieSup= SerieASupprimer.getText();
	     //On cherche le tag
	     for(int i=0; i<doc.getSerie().size();++i){
	    	 if(serieSup.equals(doc.getSerie().get(i).getNom())){
	    		
	     //Suppression du doc dans la liste des tags
	    	 	 for(int j=0;j<fmodel.getSeries().size();j++ ){
	    	 		 if((fmodel.getSeries().get(j).getNom().equals(serieSup))){
	    	 			 for(int z=0;z<fmodel.getSeries().get(j).getDocs().size();z++){
	    	 				 if(fmodel.getSeries().get(j).getDocs().get(z).equals(doc)){
	    	 					fmodel.getSeries().get(j).getDocs().remove(z);
	    	 				 }
	    	 			 }
	    	 		 }
	    	 	 }
	    	 	 //Suppression du tag dans la liste des docs
	    	 	 doc.getSerie().remove(i);
	    	 	 fmodel.getDocs().get(ligneSelectionne).getSerie().remove(i);
	    	 }
	 
	     }
	}
}
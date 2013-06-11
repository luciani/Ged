package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import modele.Document;
import modele.FenetreModel;
import modele.Serie;
import modele.Tags;
import modele.FenetreModel.Filter;


public class Panneau extends JPanel implements Observer{
	
	private FenetreModel fmodel;
	
	private int width  = 1024;
	private int height = 768;
	private int x = 0;
	private int y = 0;
	
	private JLabel labelRecherche = new JLabel("Rechercher par");
	private ButtonGroup bgRecherche = new ButtonGroup();
	
	private JRadioButton boutonNom = new JRadioButton("Nom"),
		boutonAuteur = new JRadioButton("Auteur"),
		boutonDate = new JRadioButton("Date"),
		boutonSerie = new JRadioButton("Serie"),
		boutonTag = new JRadioButton("Tag"),
		boutonNote = new JRadioButton("Note");
	
	private Font police = new Font("Arial", Font.BOLD, 14);
	private JTextField jtf = new JTextField();
	private JButton ok = new JButton("OK");
	
	private JTable tableau;
	
	public Panneau(FenetreModel model){
		fmodel = model;
		fmodel.addObserver(this);
	}
	
	public void barreDeRecherche(final JTable tab) {
		this.tableau = tab;
		// Recherche
		this.add(labelRecherche);
		// Ajout des boutons à un même groupe. On ne peut en sélectionner qu'un seul à la fois
		bgRecherche.add(boutonNom);
		bgRecherche.add(boutonAuteur);
		bgRecherche.add(boutonDate);
		bgRecherche.add(boutonSerie);
		bgRecherche.add(boutonTag);
		bgRecherche.add(boutonNote);
		boutonNom.setSelected(true);
		
		// Ajout des boutons sur le JPanel affichage
		this.add(boutonNom);		
		this.add(boutonAuteur);	
		this.add(boutonDate);	
		this.add(boutonSerie);	
		this.add(boutonTag);	
		this.add(boutonNote);
	    jtf.setFont(police);
	    jtf.setPreferredSize(new Dimension(250, 30));
	    jtf.setForeground(Color.black);
	    this.add(jtf);
	    this.add(ok);
	    
	    ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {					
				String recherche=null;
				 for (Enumeration<AbstractButton> buttons = bgRecherche.getElements(); buttons.hasMoreElements();) {
			            AbstractButton button = buttons.nextElement();

			            if (button.isSelected()) {
			                recherche=button.getText();
			            }
			      }
				 
				 DefaultTableModel model = (DefaultTableModel) tableau.getModel();
				 int len = model.getRowCount();
				 for(int j=len-1;j >= 0;j--){
					 model.removeRow(j);
				 }
				 
				 switch(Filter.valueOf(recherche.toUpperCase())){
					case NOM:
						for(Document d : fmodel.getDocs()){
							if(d.getTitre().contains(jtf.getText())){
								Object[] row = new Object[] { d.getFile(), d.getTitre(), d.getAuteur(), d.getTaille(), d.getDate(), d.getNote() };
								System.out.println("Adding row: " + row[0] + " " + row[1]);
								model = (DefaultTableModel) tab.getModel();
								model.addRow(row);
							}
						}
						break;
					case AUTEUR:
						for(Document d : fmodel.getDocs()){
							if(d.getAuteur().contains(jtf.getText())){
								Object[] row = new Object[] { d.getFile(), d.getTitre(), d.getAuteur(), d.getTaille(), d.getDate(), d.getNote() };
								System.out.println("Adding row: " + row[0] + " " + row[1]);
								model = (DefaultTableModel) tab.getModel();
								model.addRow(row);
							}
						}
						break;
					case DATE:
						for(Document d : fmodel.getDocs()){
							if(d.getDate().contains(jtf.getText())){
								Object[] row = new Object[] { d.getFile(), d.getTitre(), d.getAuteur(), d.getTaille(), d.getDate(), d.getNote() };
								System.out.println("Adding row: " + row[0] + " " + row[1]);
								model = (DefaultTableModel) tab.getModel();
								model.addRow(row);
							}
						}
						break;
					case NOTE:
						for(Document d : fmodel.getDocs()){
							if(d.getNote().contains(jtf.getText())){
								Object[] row = new Object[] { d.getFile(), d.getTitre(), d.getAuteur(), d.getTaille(), d.getDate(), d.getNote() };
								System.out.println("Adding row: " + row[0] + " " + row[1]);
								model = (DefaultTableModel) tab.getModel();
								model.addRow(row);
							}
						}
						break;
					case TAG:
						for(Tags t : fmodel.getTags()){
							if(t.getNom().contains(jtf.getText())){
								for(int i =0; i<t.getDocs().size(); i++){
									Object[] row = new Object[] { t.getDocs().get(i).getFile(), t.getDocs().get(i).getTitre(), t.getDocs().get(i).getAuteur(), t.getDocs().get(i).getTaille(), t.getDocs().get(i).getDate(), t.getDocs().get(i).getNote() };
									System.out.println("Adding row: " + row[0] + " " + row[1]);
									model = (DefaultTableModel) tab.getModel();
									model.addRow(row);
								}
							}
						}
						break;
					case SERIE:
						for(Serie s : fmodel.getSeries()){
							if(s.getNom().contains(jtf.getText())){
								for(int i =0; i<s.getDocs().size(); i++){
									Object[] row = new Object[] { s.getDocs().get(i).getFile(), s.getDocs().get(i).getTitre(), s.getDocs().get(i).getAuteur(), s.getDocs().get(i).getTaille(), s.getDocs().get(i).getDate(), s.getDocs().get(i).getNote() };
									System.out.println("Adding row: " + row[0] + " " + row[1]);
									model = (DefaultTableModel) tab.getModel();
									model.addRow(row);
								}
							}
						}
						break;
				
				}
				 
			}
		});
	    
	    
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
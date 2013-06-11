package vue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import modele.Document;
import modele.FenetreModel;
import modele.Serie;
import modele.Tags;



public class Fenetre extends JFrame implements Observer {

	////////changer////////////////
	Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	int height = (int)tailleEcran.getHeight();
	int width = (int)tailleEcran.getWidth();
	
	private FenetreModel fmodel;
	
	
	private JPanel container = new JPanel();
	
	//private Menu menu = new Menu();
	
	// JPanel visible sur la fenêtre
	private JPanel zoneImage = new JPanel();
	
	private JLabel image= new JLabel(new ImageIcon(super.getClass().getResource("/ressources/WAMS.JPG")));
	private JLabel ecritTag= new JLabel();
	private JPanel zoneTags = new JPanel();

	private Panneau zoneRecherche;

			
	// JPanels intermédiaires, invisible sur la fenêtre
	private JPanel zoneInformation = new JPanel();
	private JPanel zoneAffichage = new JPanel();
	private JPanel tableau = new JPanel();
	
	// private DefaultTableModel tableauImage = new DefaultTableModel();
	// private JTable  tableI = new JTable(tableauImage); 

	//private Tableau tableauImage= new Tableau();
	
	
	private JTable tableauImage = new JTable(new DefaultTableModel(new Object[]{"Adresse","Nom", "Auteur", "Taille", "Date de modification", "Note"},0)){
		public boolean isCellEditable(int row,int column){ return false;}
	};

	private JTable tableauSerie = new JTable(new DefaultTableModel(new Object[]{"Séries"},0)){
		public boolean isCellEditable(int row,int column){ return false;}
	};
	
	private JMenuBar menuBar = new JMenuBar();
	
	private JMenu fichier = new JMenu("Fichier"),
			edition = 		new JMenu("Edition"),
			serie =		    new JMenu("Serie"),
			tag = 			new JMenu("Tags"),
			affichage =	    new JMenu("Affichage"),
			aPropos = 		new JMenu("À propos");
	
	private JMenuItem quitter = new JMenuItem("Quitter"),
			ajouterImage = new JMenuItem("Ajouter une image"),
			creerSerie = new JMenuItem("Créer une série"),
			supprimerImage = new JMenuItem("Supprimer une image"),
			
			ajouterUneImage= new JMenuItem("Ajouter un document"),
			modifierSerie = new JMenuItem("Modifier"),
			supprimerSerie = new JMenuItem("Supprimer"),
			
			ajouterTag = new JMenuItem("Ajouter"),
			modifierTag = new JMenuItem("Modifier"),
			supprimerTag = new JMenuItem("Supprimer"),
			
			afficherImage = new JMenuItem("Toutes les images"),
			afficherSerie = new JMenuItem("Séries existantes"),
			
			information = new JMenuItem("Informations");
	
	private CardLayout cl = new CardLayout();
	
	
	
	
	public Fenetre(FenetreModel model){
		fmodel = model;
		fmodel.addObserver(this);
		
		zoneRecherche = new Panneau(fmodel);
		
		//initialise la frame
		this.setTitle("WamS");
		
		////////////changer///////////////////////
		this.setMinimumSize(new Dimension(width-200,height-300));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
				
		////////////changer//////////////
		// Type de JPanel et ajout de JPanel dans les JPanel intermédiaires
		container.setLayout(new GridLayout(1,2));
		
		container.add(zoneInformation);
		container.add(zoneAffichage);
		
		//Les contours de chaque zone en noir
		zoneImage.setBorder(BorderFactory.createLineBorder(Color.black));
		zoneTags.setBorder(BorderFactory.createLineBorder(Color.black));
		zoneAffichage.setBorder(BorderFactory.createLineBorder(Color.black));
		zoneInformation.setBorder(BorderFactory.createLineBorder(Color.black));
		zoneRecherche.setBorder(BorderFactory.createLineBorder(Color.black));
		
		
		///////////Pour rajouter une image dans un panel///////////////
		zoneImage.setLayout(new BorderLayout());
		zoneImage.add(image, BorderLayout.CENTER);
		
		
		/////////////////////////
		
		
		zoneInformation.setLayout(new GridLayout(2,1));
		zoneInformation.add(zoneImage);
		zoneInformation.add(zoneTags);
		
		
		zoneAffichage.setLayout(new BorderLayout());
		zoneAffichage.add(zoneRecherche, BorderLayout.NORTH);
		zoneAffichage.add(tableau, BorderLayout.SOUTH);
		
		zoneRecherche.barreDeRecherche(tableauImage);
		
		tableau.add(new JScrollPane(tableauImage));//,BorderLayout);//.WEST);
		
		
		
		this.afficheFenetre();
		this.setContentPane(container);
		//////////////changer///////////////////////
		//this.pack();
		this.setVisible(true);
	}


	private void afficheFenetre(){
		
		serie.add(ajouterUneImage);
		serie.add(modifierSerie);
		serie.add(supprimerSerie);
		
		edition.add(tag);
		ajouterTag.setEnabled(false);
		modifierTag.setEnabled(false);
		supprimerTag.setEnabled(false);
		tag.add(ajouterTag);
		tag.add(modifierTag);
		tag.add(supprimerTag);
		
		//Ajout des menus dans la barre de menus
		menuBar.add(fichier);
		menuBar.add(edition);
		menuBar.add(affichage);
		menuBar.add(aPropos);
			   
		tableau.setLayout(cl);
		
		
		zoneAffichage.add(tableau);
			    
		
		   
		
		//Ajout de la barre de menus sur la fenêtre
		this.setJMenuBar(menuBar);
			    
		
		//Listeners//
		
		//Appuie sur ajouter image -> fenetre d ajout image
		ajouterImage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				new FenetreAjoutImage(tableauImage,fmodel).setVisible(true);	
			
				
			}
		});
		fichier.add(ajouterImage);
	
		//Appuie sur creer serie -> fenetre de creation de series
		creerSerie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				new FenetreAjoutSerie(fmodel).setVisible(true);
				activerSerie();
			}
		});	
		fichier.add(creerSerie);
		
		supprimerImage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
			Document docSuppr;
			int ligneSel = tableauImage.getSelectedRow();

			for(Serie serie : fmodel.getDocs().get(ligneSel).getSerie()){
				for(int i =0; i<serie.getDocs().size(); i++){
					if((serie.getDocs().get(i)).equals(fmodel.getDocs().get(ligneSel)))
						serie.getDocs().remove(i);
				}
			}
			fmodel.supprimerDocument(ligneSel);
			DefaultTableModel model = (DefaultTableModel) tableauImage.getModel();
			model.removeRow(ligneSel);
			}
		});
		fichier.add(supprimerImage);
			
		//Pour quitter l'application
		quitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				//TODO +stockage de docs
				enregistrer();
				System.exit(0);
			}
		});
		fichier.add(quitter);
	    
		//Menu Edition
		edition.add(serie);
		
		//Modifier serie indisponible tant que l'on en a pas
		modifierSerie.setEnabled(false);
		
		/*ajouterUneImage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				new FenetreAjouterDocAUneSerie(fmodel, tableauImage);
			}
		});*/
		
		modifierSerie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
			}
		});
		
		supprimerSerie.setEnabled(false);
		supprimerSerie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
				new FenetreSupprimerSerie(fmodel, tableauImage);
				
				
			}
		});
		
		modifierTag.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
			}
		});
		
		supprimerTag.setEnabled(false);
		supprimerTag.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				new FenetreSupprimerTag(fmodel,tableauImage);
				//A mettre dans fenetre
				/*
				String coucou="test";
				 int ligneSelectionne = tableauImage.getSelectedRow();
			     String cheminPhoto=(String)tableauImage.getValueAt(ligneSelectionne, 0);
			     
			     Document doc= fmodel.trouveParAdresse(cheminPhoto);
			     
			     //On cherche le tag
			     for(int i=0; i<doc.getTags().size();++i){
			    	 if(coucou.equals(doc.getTags().get(i).getNom())){
			    		
			     //Suppression du doc dans la liste des tags
			    	 	 for(int j=0;j<fmodel.getTags().size();j++ ){
			    	 		 if((fmodel.getTags().get(j).getNom().equals(coucou))){
			    	 			 for(int z=0;z<fmodel.getTags().get(j).getDocs().size();z++){
			    	 				 if(fmodel.getTags().get(j).getDocs().get(z).equals(doc)){
			    	 					fmodel.getTags().get(j).getDocs().remove(z);
			    	 				 }
			    	 			 }
			    	 		 }
			    	 	 }
			    	 	 //Suppression du tag dans la liste des docs
			    	 	 doc.getTags().remove(i);
			    	 	 fmodel.getDocs().get(ligneSelectionne).getTags().remove(i);
			    	 }
			     }
			     
			    // doc.getTags().remove(Tag(coucou));
			     
			   //  }
			   			 */    
				
			}
		});
		
		
		//Affichage du tableau Image
		afficherImage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				tableau.add(new JScrollPane(tableauImage));
				cl.next(tableau);
				desactiverSerie();
			}
		});
		affichage.add(afficherImage);
		
		//Affichage du tableau série
		afficherSerie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				tableau.add(new JScrollPane(tableauSerie));
				cl.next(tableau);
				supprimerImage.setEnabled(false);
				desactiverTag();
			}
		});
		affichage.add(afficherSerie);
		
		// Menu A propos + actions appuie sur "informations"
		
		information.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				new FenetreInfo().setVisible(true);
			}
		});
		aPropos.add(information);
		
		
	    //Action lors de la sortie du programme
	    this.addWindowListener(new WindowAdapter(){
	    	public void windowClosing(WindowEvent e){
	    		//faire le stockage des documents modifiés 
	    		
	    		enregistrer();
	    		dispose();
	    	}
	    	});
	    
	    zoneImage.addMouseListener(new MouseAdapter() {
	         public void mousePressed(MouseEvent e) {		        
	            if (e.getClickCount() == 2) {
	              int ligneSelectionne = tableauImage.getSelectedRow();
			      String cheminPhoto=(String)tableauImage.getValueAt(ligneSelectionne, 0);
			   			      
			      //test d ouverture 
			     
			      File photo=new File(cheminPhoto);
			      try {
					Desktop.getDesktop().open(photo);
			      } catch (IOException e1) {
					e1.printStackTrace();
			      }
	            }
	           
	          }
	        });
	    
	    tableauImage.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	          if (e.getClickCount() == 2) {
	            //new FenetreModifPhoto();
	          }
	        }
	      });
	    
	    tableauImage.addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP)
				{
					  int ligneSelectionne = tableauImage.getSelectedRow();
				      
					  String chemin=(String)tableauImage.getValueAt(ligneSelectionne, 0);
					  
					  //Redimensionnement image
				      image.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(chemin).getScaledInstance(image.getWidth(), -1, Image.SCALE_DEFAULT)));
				      
				      Document doc = fmodel.trouveParAdresse(chemin);
				      activerTag();
				      String listeTag="";
				      String listeSerie="";

				      supprimerImage.setEnabled(true);
				      for(Tags t : doc.getTags()){
				    	  listeTag = listeTag + t.getNom() + " ";
				      }

				      for(Serie s : doc.getSerie()){
				    	  listeSerie = listeSerie + s.getNom() + " ";
				      }
				      
				      ecritTag.setText("Tags : " + listeTag + "Serie : " + listeSerie);

				      zoneTags.add(ecritTag);
				      
				      activerTag();
				      
				}
				
			}

	    	
	    });
	    supprimerImage.setEnabled(false);
	    tableauImage.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	          if (e.getClickCount() == 1) {
	        	  int ligneSelectionne = tableauImage.getSelectedRow();
		        //set imagicon
			      String chemin=(String)tableauImage.getValueAt(ligneSelectionne, 0);
			       
			      
			      image.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(chemin).getScaledInstance(image.getWidth(), -1, Image.SCALE_DEFAULT)));
	        	 
			      Document doc = fmodel.trouveParAdresse(chemin);
			      activerTag();
			      String listeTag="";
			      String listeSerie="";

			      supprimerImage.setEnabled(true);
			      for(Tags t : doc.getTags()){
			      listeTag = listeTag + t.getNom() + " ";
			      }

			      for(Serie s : doc.getSerie()){
			      listeSerie = listeSerie + s.getNom() + " ";
			      }
			      
			      ecritTag.setText("Tags : " + listeTag + "Serie : " + listeSerie);

			      zoneTags.add(ecritTag);
			      
			      activerTag();
	          }
	          //TODO
	        //  else if(e.getClickCount()==2)
	        	//  new FenetreModifMeta();
	        }
	      });
	    
	  
	    
	    tableauSerie.addMouseListener(new MouseAdapter() {
	        public void mousePressed(MouseEvent e) {
	          if (e.getClickCount() == 1) {
	        	  activerSerie();
	          }
	        }
	      });
	    
	    
	    ajouterTag.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				int ligneSelectionne = tableauImage.getSelectedRow();
				
				new FenetreAjoutTags(fmodel,fmodel.trouverParIndex(ligneSelectionne)).setVisible(true);
			}
		});
	    
	    editTableauImage();
	    editTableauSerie();
	   
	}
	
	
	public void activerSerie() {
		modifierSerie.setEnabled(true);
		supprimerSerie.setEnabled(true);
	}
	
	public void activerTag() {
		ajouterTag.setEnabled(true);
		modifierTag.setEnabled(true);
		supprimerTag.setEnabled(true);
	}
	
	public void desactiverSerie() {
		modifierSerie.setEnabled(false);
		supprimerSerie.setEnabled(false);
	}
	
	public void desactiverTag() {
		ajouterTag.setEnabled(false);
		modifierTag.setEnabled(false);
		supprimerTag.setEnabled(false);
	}
	
	
	
	@Override
	public void update(Observable o, Object arg){
		modifierTableauImage(fmodel.getDocs());
		modifierTableauSerie(fmodel.getSeries());
	}


	private void modifierTableauSerie(List<Serie> series) {
		fmodel.setSeries(series);
		
		repaint();
	}


	private void modifierTableauImage(List<Document> list) {
		fmodel.setDocuments(list);
		repaint();
		
	}
	
	public void editTableauImage(){
		
		for(Document d : fmodel.getDocRecherche()){
			Object[] row = new Object[] {d.getFile(), d.getTitre(),d.getAuteur(), d.getTaille(),d.getDate(),d.getNote() };
			TableModel t= tableauImage.getModel();
			((DefaultTableModel) t).addRow(row);
		}
	}
	
	public void editTableauSerie(){
		
		for(Serie s : fmodel.getSeries()){
			Object[] row = new Object[] {s.getNom() };
			TableModel t= tableauSerie.getModel();
			((DefaultTableModel) t).addRow(row);
		}
	}
	
	public void enregistrer(){
		try {
			FileWriter fw1 = new FileWriter ("image.txt");
			BufferedWriter bw1 = new BufferedWriter (fw1);
			PrintWriter fichierSortie1 = new PrintWriter (bw1);
			for(int i=0;i<fmodel.getDocs().size();i++){
				String chaine="";
				List<Tags> t = fmodel.getDocs().get(i).getTags();
				List<Serie> s = fmodel.getDocs().get(i).getSerie();
				
				//si le document ne contient pas de note pas mettre de double séparateur dans la chaine pour eviter les erreurs lors du chargement du fichier
				if(! (fmodel.getDocs().get(i).getNote().isEmpty())){
					chaine = ";;";
				}
				else
					chaine = ";";
				
				if(t.size()>0){//il y a au moins 1 tag
					chaine = chaine + "<tag>;";
				}
								
				for(int j=0; j<t.size(); j++){
					chaine = chaine + t.get(j).getNom() + ";";
				}
				
				if(s.size()>0){//il y a au moins une série
					if(t.size()==0)
						chaine = chaine + "<serie>;";
					else
						chaine = chaine + ";<serie>;";
				}
				
				for(int j=0; j<s.size(); j++){
					chaine = chaine + s.get(j).getNom() + ";";
				}
				fichierSortie1.println(fmodel.getDocs().get(i).getFile() + ";;" + fmodel.getDocs().get(i).getTitre() + ";" + fmodel.getDocs().get(i).getAuteur()
						+ ";" + fmodel.getDocs().get(i).getTaille() + ";" + fmodel.getDocs().get(i).getDate()
						+ ";" + fmodel.getDocs().get(i).getNote() + chaine);
				
			}
			fichierSortie1.close();
			
			
			
			
			FileWriter fw3 = new FileWriter ("serie.txt");
			BufferedWriter bw3 = new BufferedWriter (fw3);
			PrintWriter fichierSortie3 = new PrintWriter (bw3);
			for(int i=0;i<fmodel.getSeries().size();i++){
				String chaine="";
				List<Document> d = fmodel.getSeries().get(i).getDocs();
				
				for(int j=0; j<d.size(); j++){
					chaine = chaine + d.get(j).getTitre() + ";";
				}
				fichierSortie3.println(fmodel.getSeries().get(i).getNom() + ";;" + chaine);
			}
			fichierSortie3.close();
			
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
}

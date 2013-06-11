package vue;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import modele.Document;
import modele.FenetreModel;
import modele.Tags;


public class FenetreAjoutImage extends JDialog implements Observer {

	private FenetreModel fAjoutModel;
	
	private JPanel container= new JPanel();
	
	private JPanel panneauParcourir = new JPanel();
	private JPanel demandeTags = new JPanel();
	
	private JLabel parcourir= new JLabel("Parcourir fichiers");
	private JButton parcours = new JButton("Parcourir");
	private JLabel chemin = new JLabel("chemin de l'image");
	
	private JLabel tags= new JLabel("Associer tags");
	private JTextField tagsField= new JTextField(15);
	//private JButton tagsSup = new JButton("Associer un autre tag");
	
	private JPanel sortie= new JPanel();
	private JButton ok= new JButton("ok");
	private JButton annuler=new JButton("Annuler");
	private File aAjouter;

	
	public FenetreAjoutImage( final JTable tab, FenetreModel model){
		
		fAjoutModel = model;
		fAjoutModel.addObserver(this);
		
		this.setTitle("Ajout d'une image");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setModal(true);
		
		
		container.setLayout(new GridLayout(3,1));
		
		aAjouter=null;
		
		container.add(panneauParcourir);
		container.add(demandeTags);
		container.add(sortie);
		container.add(annuler);
		//panneauParcourir.setLayout(new BoxLayout(target, axis))
		panneauParcourir.add(parcourir);
		panneauParcourir.add(parcours);
		panneauParcourir.add(chemin);
		
		
		demandeTags.setPreferredSize(new Dimension(200,100));
		demandeTags.setLayout(new GridLayout(1,3));
		demandeTags.add(tags);
		demandeTags.add(tagsField);
		
		sortie.add(ok);
		sortie.add(annuler);
		
		//demandeTags.add(tagsSup);
		
		
		parcours.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				//serie=new Serie()
				
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter =new FileNameExtensionFilter("Jpg images", "jpg");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(null);
			
				if(returnVal== JFileChooser.APPROVE_OPTION){
					Document doc;
					aAjouter = chooser.getSelectedFile();
					
					chemin.setText(aAjouter.getAbsolutePath()); 
										
				}
			
			}
		});	
		
		annuler.addActionListener(new ActionListener(){	
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if(aAjouter!=null){
					
						Document doc;
						doc = new Document(aAjouter.getAbsolutePath(),aAjouter.getName(),"auteur", tailleFormat(aAjouter.length()),afficherDate(new Date(aAjouter.lastModified())));
					
						
						fAjoutModel.addDocument(doc);
						
						if(!(tagsField.getText().equals(null) ||tagsField.getText().equals(""))){
							
							//on récupère les tags séparé par un ;
							String[] tags = tagsField.getText().split(";");
							
							//on ajoute les tags si il n'exite pas 
							for(int i=0;i<tags.length;++i){
								int j=0;
								boolean trouve = false;
								List<Tags> listTag = fAjoutModel.getTags();
								
								while(j<fAjoutModel.getTags().size() && !trouve){
									if(fAjoutModel.getTags().get(j).ExisteDeja(tags[i])){
										trouve = true;
										fAjoutModel.getTags().get(j).addDocument(doc);
										
									}
									++j;
								}
								if(!trouve){ //si il n'exite pas alors on l'ajoute
									fAjoutModel.addTags(new Tags(tags[i]));
									
									//on ajoute le document a la liste de document de ce nouveau tag
									fAjoutModel.getTags().get(fAjoutModel.getTags().size()-1).addDocument(doc);
									
								}
							}
							doc.AjoutTags(new Tags(tagsField.getText()));
							
							
							
						}
					
					Object[] row = new Object[] { doc.getFile(), doc.getTitre(),doc.getAuteur(), doc.getTaille(),doc.getDate(),doc.getNote() };
					System.out.println("Adding row: " + row[0] + " " + row[1]);
					DefaultTableModel model = (DefaultTableModel) tab.getModel();
					model.addRow(row);
					dispose();
				}
			}
		});
		this.setContentPane(container);
		
	}
	
	public String tailleFormat(long size)
	{
	     size = (int) (aAjouter.length() / 1024) + 1;
	      if (size > 1024) {
	          return (size / 1024) + " Mo";
	      }
	      else{
	            return size + " ko";
	      }
	}
	
	public String afficherDate(Date d)
	{
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:mm:ss");
	     d = new Date(aAjouter.lastModified());
	     return sdf.format(d);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
		
}

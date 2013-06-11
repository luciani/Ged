package vue;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modele.Document;
import modele.FenetreModel;
import modele.Tags;

public class FenetreAjoutTags extends JDialog implements Observer{

private FenetreModel fAjoutTagModel;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel container= new JPanel();
	
	
	private JButton add = new JButton("Ajouter");
	private JTextField NomTag= new JTextField(15);
	
	public FenetreAjoutTags(FenetreModel model, final Document photo){
		
		fAjoutTagModel =model;
		fAjoutTagModel.addObserver(this);
		
		this.setTitle("Ajout d'un tag à"+ photo.getTitre());
		this.setSize(400, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setModal(true);
		
		container.setLayout(new GridLayout(3,1));
		container.add(new JLabel("Nom des nouveaux tags ( les séparer par un \";\"):",JLabel.CENTER));
		
		NomTag.setHorizontalAlignment(JTextField.CENTER);
		container.add(NomTag);
		container.add(add);
		
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if(!(NomTag.getText().equals(null) || NomTag.getText().equals("") )){
					String[] tags=NomTag.getText().split(";");
					//Vérifie si existe deja
					List<Tags> listeTag=fAjoutTagModel.getTags();
					
					for(int i=0;i<tags.length;++i){
						int j=0;
						boolean trouve = false;
						List<Tags> listTag = fAjoutTagModel.getTags();
						
						while(j<fAjoutTagModel.getTags().size() && !trouve){
							if(fAjoutTagModel.getTags().get(j).ExisteDeja(tags[i])){
								trouve = true;
								fAjoutTagModel.getTags().get(j).addDocument(photo);
								
							}
							++j;
						}
						
						if(!trouve){ //si il n'exite pas alors on l'ajoute
							fAjoutTagModel.addTags(new Tags(tags[i]));
							
							//on ajoute le document a la liste de document de ce nouveau tag
							fAjoutTagModel.getTags().get(fAjoutTagModel.getTags().size()-1).addDocument(photo);
							
						}
						photo.AjoutTags(new Tags(tags[i]));
					}
					
					
					
				}
					
					dispose();
			}
				//ajout au tableau?
			
		});
		
		this.setContentPane(container);

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}

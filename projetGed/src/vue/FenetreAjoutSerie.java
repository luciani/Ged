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

import modele.FenetreModel;
import modele.Serie;

public class FenetreAjoutSerie extends JDialog implements Observer {

	private FenetreModel fAjoutSerieModel;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private JPanel container= new JPanel();
	
	
	private JButton add = new JButton("Créer");
	private JTextField NomSerie= new JTextField(15);
	
	public FenetreAjoutSerie(FenetreModel model){
			
		fAjoutSerieModel =model;
		fAjoutSerieModel.addObserver(this);
		
		this.setTitle("Ajout d'une serie");
		this.setSize(400, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setModal(true);
		
		container.setLayout(new GridLayout(3,1));
		container.add(new JLabel("Nom de la nouvelle série:",JLabel.CENTER));
		
		NomSerie.setHorizontalAlignment(JTextField.CENTER);
		container.add(NomSerie);
		container.add(add);
		
		add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				
				if(!(NomSerie.getText().equals(null) || NomSerie.getText().equals("") )){
					Serie s=new Serie(NomSerie.getText());
					int i=0;
					List<Serie> listSerie = fAjoutSerieModel.getSeries();
					
					if(fAjoutSerieModel.getSeries().size() !=0){ //si il existe des series
						while(i<listSerie.size() && !listSerie.get(i).ExisteDeja(NomSerie.getText()))
							++i;
						
						if(!listSerie.get(i).ExisteDeja(NomSerie.getText())) //si la serie n'existe pas on l'a rajoute
							fAjoutSerieModel.addSerie(s);
					}
					else
						fAjoutSerieModel.addSerie(s);
					dispose();
				}
				
			}
		});
		this.setContentPane(container);

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}

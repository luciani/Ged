package controleur;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import modele.Document;
import modele.FenetreModel;
import modele.Serie;
import modele.Tags;

import vue.Fenetre;

public class GedMVC {
	
	static FenetreModel model;
	static Fenetre f;
	
	public static void run(){
		try{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			
		}catch (Exception e){}
		
		
		chargement();
		
	}
	
	private static void chargement(){
		List<Tags> t = new ArrayList<Tags>();
		List<Serie> s = new ArrayList<Serie>();
		List<Document> d = new ArrayList<Document>();

		try{
			
			InputStream ips3=new FileInputStream("image.txt"); 
			InputStreamReader ipsr3=new InputStreamReader(ips3);
			String ligne;
			BufferedReader br3=new BufferedReader(ipsr3);
			boolean trouveTag = false;
			boolean trouveSerie = false;
				
			while ((ligne=br3.readLine())!=null){
				Document docTmp;
				Tags tTmp;
				Serie sTmp;
				
				List<Tags> listeTag = new ArrayList<Tags>();
				List<Serie> listeSerie = new ArrayList<Serie>();
				
				String[] donnees = ligne.split(";;");
				String[] tags=null;
				String[] series=null;
				String[] metadonnees = donnees[1].split(";");
				
				if(metadonnees.length<5)//il n y pas de note enregistré
					docTmp = new Document(donnees[0],metadonnees[0],metadonnees[1], metadonnees[2],metadonnees[3]);
				else
					docTmp = new Document(donnees[0],metadonnees[0],metadonnees[1], metadonnees[2],metadonnees[3],metadonnees[4]);
				
				if(donnees.length == 3){//il y a des tag ou des series
					
					String[] information = donnees[2].split(";");
					if(information[0].equals("<tag>")){//la liste d'information qui suit est une liste de tag
						for(int i=1; i<information.length; ++i){//information[0] vaut "<tag>"
							tTmp = new Tags(information[i]);
							if(t.isEmpty()){//liste vide
								tTmp.addDocument(docTmp);
								t.add(tTmp);
							}
							else{
								for(int j=0; j<t.size();++j){
									if(t.get(j).ExisteDeja(information[i])){
										trouveTag=true;
										t.get(j).addDocument(docTmp);
									}
								}
								if(!trouveTag){
									tTmp.addDocument(docTmp);
									t.add(tTmp);
								}
							}
							listeTag.add(tTmp);
							trouveTag = false;
						}
					}
					else if(information[0].equals("<serie>")){//la liste d'information qui suit est une liste de serie
						for(int i=1; i<information.length; ++i){//information[0] vaut "<serie>"
							sTmp = new Serie(information[i]);
							if(s.size()==0){//liste vide
								sTmp.addDocument(docTmp);
								s.add(sTmp);
							}
							else{
								for(int j=0; j<s.size();++j){
									if(s.get(j).ExisteDeja(information[i])){
										trouveSerie=true;
										s.get(j).addDocument(docTmp);
									}
								}
								if(!trouveSerie){
									sTmp.addDocument(docTmp);
									s.add(sTmp);
								}
									
							}
							listeSerie.add(sTmp);
							trouveSerie=false;
						}
					}
				}
				else if(donnees.length == 4){ //il y a des tag et des series
					tags = donnees[2].split(";");
					
					for(int i=1; i<tags.length; ++i){ //tags[0] vaut "<tag>"
						tTmp = new Tags(tags[i]);
						if(t.isEmpty()){//liste vide
							tTmp.addDocument(docTmp);
							t.add(tTmp);
						}
						else{
							for(int j=0; j<t.size();++j){
								if(t.get(j).ExisteDeja(tags[i])){
									trouveTag=true;
									t.get(j).addDocument(docTmp);
								}
							}
							if(!trouveTag){
								tTmp.addDocument(docTmp);
								t.add(tTmp);
							}
						}
						listeTag.add(tTmp);
						trouveTag = false;
					}
					
					series = donnees[3].split(";");
					for(int i=1; i<series.length; ++i){//series[0] vaut "<serie>"
						sTmp = new Serie(series[i]);
						if(s.size()==0){//liste vide
							sTmp.addDocument(docTmp);
							s.add(sTmp);
						}
						else{
							for(int j=0; j<s.size();++j){
								if(s.get(j).ExisteDeja(series[i])){
									trouveSerie=true;
									s.get(j).addDocument(docTmp);
								}
							}
							if(!trouveSerie){
								sTmp.addDocument(docTmp);
								s.add(sTmp);
							}
								
						}
						listeSerie.add(sTmp);
						trouveSerie=false;
					}
				}
				if(metadonnees.length<5)//il n y pas de note enregistré
					d.add(new Document(donnees[0],metadonnees[0],metadonnees[1], metadonnees[2],metadonnees[3],listeTag,listeSerie));
				else
					d.add(new Document(donnees[0],metadonnees[0],metadonnees[1], metadonnees[2],metadonnees[3],metadonnees[4],listeTag,listeSerie));
			}
			br3.close();	
			
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		
		model = new FenetreModel(t, d, s);
		f = new Fenetre(model);
		
		f.setVisible(true);
	}
}

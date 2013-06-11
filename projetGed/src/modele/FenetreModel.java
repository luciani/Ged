package modele;

import java.util.List;
import java.util.Observable;

public class FenetreModel extends Observable{
	private List<Tags> tags;
	private List<Document> docs;
	private List<Serie> series;
	private List<Document> docRecherche;
	
	public FenetreModel(List<Tags> tags, List<Document> docs, List<Serie> series){
		this.tags = tags;
		this.docs = docs;
		this.series = series;
		docRecherche = docs;
	}
	
	static public enum Filter {
	    NOM, AUTEUR, DATE, NOTE, TAG, SERIE 
	}
	
	public List<Tags> getTags(){
		return tags;
	}
	
	public List<Document> getDocs(){
		return docs;
	}
	public List<Serie> getSeries(){
		return series;
	}
	
	public void setTags(List<Tags> t){
		tags = t;
		setChanged();
		notifyObservers();
	}
	
	public void setSeries(List<Serie> s){
		series = s;
		setChanged();
		notifyObservers();
	}
	
	public void setDocuments(List<Document> d){
		docRecherche = d;
		setChanged();
		notifyObservers();
	}
	
	public void addDocument(Document newDoc){
		docs.add(newDoc);

	}
	
	public void addSerie(Serie newSerie){
		series.add(newSerie);

	}
	
	public void addTags(Tags newTag) {
		tags.add(newTag);

	}

	public List<Document> getDocRecherche() {
		return docRecherche;
	}
	
	public Document trouveParAdresse(String cheminPhoto){
		for(int i=0;i<docs.size();++i){
			if(cheminPhoto.equals(docs.get(i).getFile())){
				return docs.get(i);
			}
		}
		return null;
	}
	
	public void supprimerDocument(int index){
		docs.remove(index);
	}
	
	public Document trouverParIndex(int index){
		return docs.get(index);
	}
	
}

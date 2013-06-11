package modele;

import java.util.*;

public class Tags extends Descripteur {
	
	private List<Document> docs;
	
	public Tags(String nom){
		super(nom);
		docs= new ArrayList<Document>();
	}
	
	public Tags(String nom, List<Document> d){
		super(nom);
		docs =d;
	}
	
	@Override
	public boolean ExisteDeja(String desc){
		return desc.equals(super.getNom());
	}
	
	@Override
	public void addDocument(Document newDoc) {
		docs.add(newDoc);	
	}
	
	@Override
	public List<Document> getDocs(){ return docs; }
	
	
	
}

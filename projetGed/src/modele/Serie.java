package modele;

import java.util.*;

public class Serie extends Descripteur {
	private int ordre;
	
	private List<Document> docs;
	
	public Serie(String nom){
		super(nom);
		docs = new ArrayList<Document>();
	}
	
	public Serie(String nom, List<Document> d){
		super(nom);
		docs =d;
	}
	
	
	
	@Override
	public void addDocument(Document newDoc) 
	{
		docs.add(newDoc);				
	}


	@Override
	public boolean ExisteDeja(String desc) {
		return desc.equals(super.getNom());
	}
	
	@Override
	public List<Document> getDocs(){
		return docs;
		
	}
	
}

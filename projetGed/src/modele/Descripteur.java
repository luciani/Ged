package modele;

import java.util.List;
import java.util.Observable;

public abstract class Descripteur extends Observable{
	private String nom;
	
	public Descripteur(String nom)
	{
		this.nom=nom;	
	}
	
	public Descripteur(String nom , List<Document> d) {
		this.nom= nom;
	}

	
	public abstract boolean ExisteDeja(String desc);
	
	public String getNom()
	{
		return nom;
	}

	public abstract List<Document> getDocs();

	public abstract void addDocument(Document newDoc);
	
}

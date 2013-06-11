package modele;

import java.util.*;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

public class Document extends Observable{

	
	private String photo;
	private String titre;
	private String auteur;
	private String date;
	private String note; 
	private String taille;

	private List<Tags> tags;
	private List<Serie> series;
	
	public Document(String photo,String titre,String auteur,String taille, String date){
		this.photo=photo;//ImageIO.read(photo);
		this.titre= titre;
		this.auteur= auteur;
		this.date= date;
		this.taille=taille;
		tags=new ArrayList<Tags>();	
		series=new ArrayList<Serie>();	
		note=""; 
	}
	
	public Document(String photo,String titre,String auteur,String taille,String date,String note){
		this.photo=photo;
		this.titre= titre;
		this.auteur= auteur;
		this.date= date;
		this.taille=taille;
		tags=new ArrayList<Tags>();	
		series=new ArrayList<Serie>();	
		this.note=note;
		
	}
	
	public Document(String photo,String titre,String auteur,String taille,String date,String note, List<Tags> t, List<Serie> s){
		this.photo=photo;//ImageIO.read(photo);
		this.titre= titre;
		this.auteur= auteur;
		this.date= date;
		this.taille=taille;
		tags=new ArrayList<Tags>();	
		series=new ArrayList<Serie>();	
		this.note=note;
		tags = t;
		series = s;
		
	}
	//constructeur avec des tag et des series mais sans note
	public Document(String photo,String titre,String auteur,String taille,String date,List<Tags> t, List<Serie> s){
		this.photo=photo;//ImageIO.read(photo);
		this.titre= titre;
		this.auteur= auteur;
		this.date= date;
		this.taille=taille;
		tags=new ArrayList<Tags>();	
		series=new ArrayList<Serie>();	
		this.note="";
		tags = t;
		series = s;
		
	}
	
	public String getFile(){
		return photo;
	}
	
	public String getTaille(){
		return taille;
	}
	
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public /*Date*/String getDate() {
		return date;
	}

	public void setDate(/*Date*/String date) {
		this.date = date;
	}

	public List<Tags> getTags() {
		return tags;
	}

	public void setNote(String note){
		this.note=note;
	}
	
	public String getNote(){
		return note;
	}
	
	public List<Serie> getSerie(){
		return series;
	}
	
	public void AjoutTags(Tags tag){
		tags.add(tag);
	}
	
	public void AjoutSerie(Serie serie){
		series.add(serie);
	}

	@Override
	public boolean equals(Object o){
		if(o instanceof Document){
			Document newdoc = (Document) o;
			return (titre.equals(newdoc.getTitre()) && auteur.equals(newdoc.getAuteur()) && date.equals(newdoc.getDate())
					&& photo.equals(newdoc.getFile()) && note.equals(newdoc.getNote()) && taille.equals(newdoc.getTaille())); 
		}
		else
			return false;

	}
	
}

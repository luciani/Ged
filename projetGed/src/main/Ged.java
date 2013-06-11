package main;

import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FileChooserUI;

import controleur.GedMVC;

import modele.Tags;
import modele.Document;
import modele.Serie;


public class Ged {
	private List<Tags> tags;
	private List<Document> docs;
	private List<Serie> series;
	

	public static void main(String[] args) throws IOException
	{
		GedMVC.run();		
	}
	
	public void supprimerImage(Document image){
		image=null;
		
	}
}
	
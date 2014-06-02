package fr.insarouen.poneysdoux.client;

import java.util.ArrayList;
import java.util.Iterator;

import fr.insarouen.poneysdoux.client.GreetingService;
import fr.insarouen.poneysdoux.server.GreetingServiceImpl;
import fr.insarouen.poneysdoux.server.DbFacade;


import fr.insarouen.poneysdoux.shared.FieldVerifier;
import java.lang.String;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.dom.client.Style.Unit;

public class Insadex implements EntryPoint{
	private VerticalPanel mainPanel = new VerticalPanel();
	private HorizontalPanel searchPanel = new HorizontalPanel();
	private HorizontalPanel addPanel = new HorizontalPanel();
	private Button addButton = new Button("Add");
	private Button searchButton = new Button("Search");
	private TextBox addTextBox = new TextBox();
	private TextBox searchTextBox = new TextBox();
	private FlexTable usersFlexTable = new FlexTable();
	private FlexTable profilFlexTable = new FlexTable();
	private FlexTable enteteFlexTable = new FlexTable();
	private FlexTable descriptionFlexTable = new FlexTable();
	private ArrayList<String[]> users /*= new ArrayList<String[]>()*/;
	private String[] user = new String[7];
	private Image image_profil = new Image();
	private GreetingServiceAsync greetingService = (GreetingServiceAsync) GWT.create(GreetingService.class);
	
	
	
	
	public void onModuleLoad() {
		HTMLPanel recherche = panelRecherche();
		HTMLPanel profil = panelProfil(user);
		
		TabLayoutPanel panel = new TabLayoutPanel(3, Unit.EM);
		panel.add(recherche, "Recherche");
		panel.add(profil, "Profil");
		
		RootPanel.get("panel").add(panel);
		
	}
	
	private void initialiserusersFlexTable(){
		ArrayList<String[]> liste_retour;
		
		greetingService.getInfosUsers(new AsyncCallback<ArrayList<String[]>>() {
	        	public void onFailure(Throwable caught){
	        		System.out.println("erreur !");
	        	}
	        	
				public void onSuccess(ArrayList<String[]> result) {
					Insadex.this.users = result;
					
					//#####
					
					for(int k=0;k<Insadex.this.users.size();k++){
						
						final int j=k;
						Insadex.this.usersFlexTable.setText(j+1, 0, Insadex.this.users.get(j)[0]);
						Insadex.this.usersFlexTable.setText(j+1, 1, Insadex.this.users.get(j)[1]);
						
						Button boutonProfil = new Button("Profil");
						boutonProfil.addClickHandler(new ClickHandler() {
							public void onClick(ClickEvent event) {
								completerPanelProfil(Insadex.this.users.get(j));
							}
							
						});
						
						Insadex.this.usersFlexTable.setWidget(j+1, 2,boutonProfil);
					/*
						Button boutonSupprimer = new Button("[x]");
						boutonSupprimer.addClickHandler(new ClickHandler() {
							public void onClick(ClickEvent event) {
								Insadex.this.users.remove(j);
								Insadex.this.usersFlexTable.removeRow(j+1);
							}
						});
						
						Insadex.this.usersFlexTable.setWidget(j+1, 3, boutonSupprimer);
					*/
					}
					
					//#####
					
	            }
	            
		}); 
	
	}
	
	private void ajoutPersonne(){
		final String[] user = addTextBox.getText().trim().split("_");
		if (users.contains(user)){
			return;
		} 
		else {
			users.add(user);
			final int row = usersFlexTable.getRowCount();
			usersFlexTable.setText(row, 0, user[0]);
			usersFlexTable.setText(row, 1, user[1]);
			
			Button boutonProfil = new Button("Profil");
			boutonProfil.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					completerPanelProfil(user);
				}
				
			});
			
			usersFlexTable.setWidget(row, 2, boutonProfil);
			/*
			Button boutonSupprimer = new Button("[x]");
			boutonSupprimer.addClickHandler(new ClickHandler() {
			      public void onClick(ClickEvent event) {
			    	  users.remove(row-1); // fait de la merde
			    	  usersFlexTable.removeRow(row); // removeRown(row) a l'origine
			      }
			});
			
			usersFlexTable.setWidget(row, 3, boutonSupprimer);
			*/
		}
	}
	
	private void recherchePersonne(){
		String chaine = searchTextBox.getText().trim();
		ArrayList<Integer> listeNumero = new ArrayList<Integer>();
		int colonne;
		String[] chaineDecomposee = chaine.split("_");
		
		for(Integer i=0; i<users.size(); i++){
			usersFlexTable.setText(i+1,3,"");
			String[] user = users.get(i);
			boolean trouve = true;
			for(String mot : chaineDecomposee){
				boolean trouveTmp = false;
				if(user[0].toUpperCase().contains(mot.toUpperCase()))
					trouveTmp = true;
				if(user[1].toUpperCase().contains(mot.toUpperCase()))
					trouveTmp = true;
				trouve = trouve && trouveTmp;
			}
			
			if(trouve == true){
				listeNumero.add(i);
			}
		}
		Iterator<Integer> row = listeNumero.iterator();
		System.out.println(listeNumero.size());
		while(row.hasNext()){
			colonne = row.next();
			usersFlexTable.setText(colonne+1,3,"trouve");
		}
		//return listeNumero;
	}

	private HTMLPanel panelRecherche(){
		usersFlexTable.setText(0 , 0 , "Nom");
		usersFlexTable.setText(0 , 1 , "Prenom");
		usersFlexTable.setText(0 , 2 , "Profil");
		usersFlexTable.setText(0 , 3 , "Trouve");
		
		addPanel.add(addTextBox);
		addPanel.add(addButton);
		searchPanel.add(searchTextBox);
		searchPanel.add(searchButton);
		
		mainPanel.add(addPanel);
		mainPanel.add(searchPanel);
		
		HTMLPanel usersList = new HTMLPanel("<div id='usersList'></div>");
		usersList.add(usersFlexTable, "usersList");
		mainPanel.add(usersList);
		
		
		searchButton.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		    	  recherchePersonne();
		      }
		});
		
		searchTextBox.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					recherchePersonne();
				}
			}
		});
		
		addButton.addClickHandler(new ClickHandler() {
		      public void onClick(ClickEvent event) {
		        ajoutPersonne();
		      }
		});
		
		addTextBox.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					ajoutPersonne();
				}
			}
		});
		
		searchTextBox.setFocus(true);
		initialiserusersFlexTable();
		
		HTMLPanel recherche = new HTMLPanel("<div id='insadex_search_panel'></div>");
		recherche.add(mainPanel, "insadex_search_panel");
		
		return recherche;
	}
	
	private HTMLPanel panelProfil(String[] user){
		HorizontalPanel profilPanel_haut = new HorizontalPanel();
		VerticalPanel profilPanel = new VerticalPanel();
		VerticalPanel entetePanel = new VerticalPanel();
		
		
		HTMLPanel imageProfil, descriptionProfil, informationsProfil, enteteProfil;
		enteteProfil = new HTMLPanel("<div id='insadex_enteteProfil_panel'></div>");
		imageProfil = new HTMLPanel("<div id='insadex_imageProfil_panel'></div>");
		informationsProfil = new HTMLPanel("<div id='insadex_informationsProfil_panel'></div>");
		descriptionProfil = new HTMLPanel("<div id='insadex_descriptionProfil_panel'></div>");
		
		this.image_profil.setUrl("images/");
		imageProfil.add(this.image_profil, "insadex_imageProfil_panel");
	
		completerPanelProfil(user);
		enteteProfil.add(enteteFlexTable, "insadex_enteteProfil_panel");
		informationsProfil.add(profilFlexTable, "insadex_informationsProfil_panel");
		descriptionProfil.add(descriptionFlexTable, "insadex_descriptionProfil_panel");
		
		entetePanel.add(enteteProfil);
		profilPanel_haut.add(imageProfil);
		profilPanel_haut.add(informationsProfil);
		profilPanel.add(entetePanel);
		profilPanel.add(profilPanel_haut);
		profilPanel.add(descriptionProfil);
	
		
		HTMLPanel profil = new HTMLPanel("<div id='insadex_profil_panel'></div>");
		profil.add(profilPanel, "insadex_profil_panel");
		
		return profil;
		
	}
	
	private void completerPanelProfil(String[] user){
		enteteFlexTable.setText(0, 0,"N° : xxx");
		enteteFlexTable.setText(0, 1, user[0]);
		enteteFlexTable.setText(0, 2, user[1]);
		
		this.image_profil.setUrl("images/"+user[6]);
		
		profilFlexTable.setText(0, 0, "Nom : ");
		profilFlexTable.setText(0, 1, "");
		profilFlexTable.setText(0, 1, user[0]);
		
		profilFlexTable.setText(1, 0, "Prenom : ");
		profilFlexTable.setText(1, 1, "");
		profilFlexTable.setText(1, 1, user[1]);
		
		profilFlexTable.setText(2, 0, "Departement : ");
		profilFlexTable.setText(2, 1, "");
		profilFlexTable.setText(2, 1, user[2]);
		
		profilFlexTable.setText(3, 0, "Rang : ");
		profilFlexTable.setText(3, 1, "");
		profilFlexTable.setText(3, 1, user[3]);
		
		profilFlexTable.setText(4, 0, "Date de naissance : ");
		profilFlexTable.setText(4, 1, "");
		profilFlexTable.setText(4, 1, user[4]);
		
		descriptionFlexTable.setText(0, 0, "Description : ");
		descriptionFlexTable.setText(1, 0, "");
		descriptionFlexTable.setText(1, 0, user[5]);
		
	}

	
	
}

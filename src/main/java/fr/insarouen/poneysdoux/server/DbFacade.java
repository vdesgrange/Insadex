package fr.insarouen.poneysdoux.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.lang.String;

public class DbFacade
{
		boolean is_bouchon = true ;
		private ArrayList<String[]> users = new ArrayList<String[]>();
		
		public DbFacade() {
				this.is_bouchon = true ;
		}
		
		public ArrayList<String[]> retrieve_users () {
			if ( is_bouchon )
			{
				creationUtilisateurBouchon();
			}
			return this.users;
		}

		 public void ajouterUtilisateur(String[] user) throws IllegalArgumentException{
			  this.users.add(user);
		  }
		
		// FONCTION BOUCHON
		  public void creationUtilisateurBouchon(){

			  String[] truc1 = {"ALLON", "LEVY", "ASI3","etudiant","06/05/1993","Devenir le roi de la pop, voila un truc qu’on aimerait tous faire."
			  		+ " Le succès ce n’est pas que la richesse, le bonheur ou sentir la jalousie des autres. "
			  		+ "Non le vrai succès c’est avant tout d’être un Nom. Je dis Jackson et vous pensez tous à "
			  		+ "lui et écrasez une larmette tellement il vous manque, je dis Dugland et vous vous demandez "
			  		+ "qui peut bien porter un nom aussi con. Voila d’ailleurs pourquoi Jean-Philipe Smet "
			  		+ "a préféré devenir Johnny Hallyday.","poneysdoux.jpg"};
				ajouterUtilisateur(truc1);
				
				String[] truc2 = {"BACARD", "HUGO","GM3","etudiant","22/04/1993","Apparemment, beaucoup de gens pensent que faire de"
						+ " la BD apporte richesses, voitures de luxe et femmes peu vêtues. Bon, pourquoi pas après tout, il y a pire"
						+ " comme idées communément admises malgré leur évidente stupidité, y a bien des gens qui pensent que Jean-Luc"
						+ " Mélenchon a un programme pour quand il sera président. Enfin bon, cette mauvaise interprétation du boulot "
						+ "de dessinateur a conduit bon nombre de personnes à se lancer dans le gribouillage vaguement encadré par des"
						+ " cases, mais sans vraiment rencontrer le succès espéré. ","autruche.jpg"};
				ajouterUtilisateur(truc2);
				
				String[] truc3= {"BAKER", "MATTHEW","STPI1","etudiant","18/09/1995","Macarena !Ppoipn kjfzkhjz uzeyoi "
						+ "ldjazoi lkj!lkzeolikze LOJn,lujazopipo poîpoi ezôiêpz $ô$"
						+ " rkfooi dgiuyte uyrefrttre iueyrtrtrdfsqytr iuyaz dsqyt. Eeeeh ! Macarena !","macarena.jpg"};
				ajouterUtilisateur(truc3);
				
				String[] truc4= {"BALWE", "CHETAN","CFI","enseignant","26/12/1968","Un mickey est assimilable à un être étrange, un poney "
						+ "ou un Laurent ou encore une forme évoluée du Kevin. Il est toujours en train de faire marrer tout le monde à"
						+ " cause de sa connerie naturelle ou de sa maladresse congénitale, cependant on trouve certains lieux peuplés"
						+ " par les Mickey (Exemple: Centre commerciaux, Show tuning, les boîtes à tendances bizarres, etc...)."
						+ " La patrie du mickey est disneyland-Paris","mickey.jpg"};
				ajouterUtilisateur(truc4);
				
				String[] truc5= {"BELAIR", "LUC","CFI5","etudiant","22/07/1990","Les Deux-Sèvres ont été inventées par Louis IX"
						+ " en 1634 qui y fit une étape lors de son pélerinage à La Mecque. Alors qu'il dormait tranquillement "
						+ "au pied d'un arbre, il reçu une chèvre sur la tête et s'écria 'niiiiooooh' (ce qui sera plus tard le"
								+ " nom de la capitale) avant d'être assommé par une deuxième. Il se trouvait que "
								+ "Dieu venait d'inventer la chèvre mâle puis la chèvre femelle.","cornichon.jpg"};
				ajouterUtilisateur(truc5);
				
				String[] truc6= {"BERKOVICH", "VLADIMIR","STPI2","etudiant","31/10/1993","Lorem ipsum dolor sit amet, consectetur"
						+ " adipiscing elit. Mauris imperdiet ipsum odio, at malesuada ligula commodo eget. Curabitur vitae est "
						+ "purus. In nec risus quam. Nunc ultricies aliquet diam, eu tempus velit egestas vel. Fusce tristique a "
						+ "est sed varius. Vestibulum velit sem, molestie eget ultricies rhoncus, sollicitudin eu magna. "
						+ "Sed dignissim risus eros, ","poneysdoux.jpg"};
				ajouterUtilisateur(truc6);
				
				String[] truc7= {"BERTRAND", "BENOIT","STPI1","etudiant","01/01/1996","Эуежмод адмодум экз дуо, ыт шэа клита рэктэквуэ "
						+ "майыжтатйж. Вяш ыёрмод котёдиэквюэ ты. Вяш ад кхоро ентэгры, эа хаж рыквюы чадипжкёнг котёдиэквюэ."
						+ " Клита пэркйпет дяшзынтиыт ан хёз. Ыюм дюиж нюлльа омныз экз, экз мальорум антеопам дёзсэнтёаш зыд."
						+ " Ыт мэя эррэм эрепюят долорэм, примич омйттам волуптюа хёз ан. Ат еюж зюаз аэтырно дяшзынтиыт.","poneysdoux.jpg"};
				ajouterUtilisateur(truc7);
				
				String[] truc8= {"BHOWIK", "PRASENJIT","GM","enseignant","21/11/1958","Nunc ut malesuada neque. Donec"
						+ " vitae tempor diam. Duis auctor, orci ut convallis rutrum, tellus nibh lacinia nunc, eu "
						+ "convallis quam enim a quam. Aenean tempor, elit vel ultricies sagittis, nibh diam dignissim "
						+ "enim, nec auctor tellus orci et tortor. Phasellus sodales purus pulvinar ligula venenatis aliquam. "
						+ "Sed at eros a sapien pharetra mollis. Integer placerat nunc dui, eget ornare metus aliquet pretium."
						+ " Etiam fermentum lobortis elementum.","poneysdoux.jpg"};
				ajouterUtilisateur(truc8);
				
				String[] truc9= {"BLOSSIER", "THOMAS","MECA5","etudiant","16/03/1990","pozostając praktycznie niezmienionym. Spopularyzował się"
						+ " w latach 60. XX w. wraz z publikacją arkuszy Letrasetu, zawierających fragmenty Lorem Ipsum, a ostatnio z "
						+ "zawierającym różne wersje Lorem Ipsum oprogramowaniem przeznaczonym do realizacji druków na komputerach osobistych.","poneysdoux.jpg"};
				ajouterUtilisateur(truc9);
				
				String[] truc10= {"BOUAYAD", "ALEXANDRE","ASI4","etudiant","05/04/1992","Existem muitas variações das passagens do Lorem Ipsum "
						+ "disponíveis, mas a maior parte sofreu alterações de alguma forma, pela injecção de humor, ou de palavras aleatórias "
						+ "que nem sequer parecem suficientemente credíveis. Se vai usar uma passagem do Lorem Ipsum. ","poneysdoux.jpg"};
				ajouterUtilisateur(truc10);
			  
			 
		  }

}

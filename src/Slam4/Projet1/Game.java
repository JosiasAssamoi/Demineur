package Slam4.Projet1;
import java.util.Scanner;

public class Game  {

	private static int pourcent;
	private String triche="N" ;
	public Game(int pourcent,String triche) {
	
		Game.pourcent = pourcent;
		this.triche= triche;
		
	}
	public static int getPourcent() {
		return pourcent;
	}
	
	public Game(int pourcent) {
		
		this.pourcent = pourcent;
		
	}
	
	 public void play() 
	  {
		 Grille.setColonne(5);
		 Grille.setLigne(5);
		 Grille grille=  new Grille(pourcent);
		
		  Scanner scannerr = new Scanner(System.in);
	
		  	if(triche.equals("O")) {System.out.println("mode triche activated =D ");Grille.getTriche();}
		  	
		    while(grille.getCasesLibres() > 0)
		    {
		    System.out.println("nb cases libres "+ grille.getCasesLibres());
		   
		    // boucle de jeu
		      grille.Affiche();
		      System.out.print("Veuillez entrer vos coordonnees x et y");
		      
		      String[] choix = scannerr.nextLine().toUpperCase().split(" ");
		      
		      try
		      {
		        int x = Integer.parseInt(choix[0])  ;
		        int y = Integer.parseInt(choix[1])  ;
		    
		        if(choix.length==1)
		        throw new ArrayIndexOutOfBoundsException (null); 
		        if( choix.length > 2 ) {
		        if(!choix[2].equals("P")) 
		        throw new NullPointerException (null);
		        else grille.initDrapeau(x,y) ;
		      }
		      else  grille.open(x, y);
		    }
		    catch(NullPointerException e){
		    System.out.println(" /!\\ /!\\ /!\\ Pour inserer un drapeau il faut entrer les coordonnées puis un P /!\\ /!\\ /!\\ ");}
		    catch(ArrayIndexOutOfBoundsException e){
		    System.out.println("/!\\ /!\\ /!\\  Veuillez entrer 1 coordonée x(colonne) et 1 coordonnée y(ligne) separés d'un espace /!\\ /!\\ /!\\ ") ;  }  
		      catch(NumberFormatException e){
				    System.out.println("/!\\ /!\\ /!\\  Veuillez entrer les coordonées en utilisant les chiffres proposés /!\\ /!\\ /!\\ ") ;  }
		  }

		    grille.Affiche();
		    System.out.println(grille.getCasesLibres() == 0 ? "Felicitation vous avez gagne." : "Vous avez perdu.");
	
		  
		 
	     
	    } 

}

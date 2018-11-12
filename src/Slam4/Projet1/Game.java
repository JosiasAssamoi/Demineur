package Slam4.Projet1;
import java.util.Scanner;


/**
 * 
 * @author Josias
 *
 *<p>Dans cette classe se lance une partie une surchage du constructeur a été necessaire pour utiliser ou non le mode triche, dans les deux cas chaque partie recoit
 * un pourcentage de bombes. </p>
 * <p>L'ouverture (@see {@link Grille#open(int,int) }) et le placement de drapeau ( @see {@link Grille#initDrapeau(int, int) }) s'appellent ici.</p> 
 * <p> L'affichage ( @see {@link Grille#Affiche() }) de la grille s'appelle ici aussi.</p>
 * <p> On gagne une partie si l'on ouvre une bombe (
 * ce qui renvoit -1 a la variable casesLibres @see {@link Grille#open(int, int)} </p>
 */


public class Game  {
	

	private static int pourcent;
	private String triche="N" ;
	private double startime,endtime,tempecoule;
	
	public Game(int pourcent,String triche) {
	
		Game.pourcent = pourcent;
		this.triche= triche;
		
	}
	public static int getPourcent() {
		return pourcent;
	}
	// Surchage si il n'y pas de mode Triche
	public Game(int pourcent) {
		
		Game.pourcent = pourcent;
		
	}
	
	/**
	 * Effectue les actions d'une partie ouverture placement de bombes, verification de win ou defaite etc. 
	 * 
	 */
	 public void play() 
	  {
		 
		 Grille grille=  new Grille(pourcent);
		
		  Scanner scanner = new Scanner(System.in);
		  	
		  
		  	if(triche.equals("O")) {System.out.println("mode triche activate =D ");Grille.getTriche();}
		  	
		  // Tant que qu'il y a  des cases libres on reste dans la partie/grille. 
		    while(grille.getCasesLibres() > 0){
		    	startime=System.currentTimeMillis();
			    System.out.println("Nombre de cases à déminer : "+ grille.getCasesLibres());
			    grille.Affiche();
			    System.out.print("Veuillez entrer vos coordonnees x et y "); 
			    String[] choix = scanner.nextLine().toUpperCase().split(" ");
		      
		        try{
		        	// convertit en int le choix x et y
			        int x = Integer.parseInt(choix[0])  ;
			        int y = Integer.parseInt(choix[1])  ;
			        
			        // si il n'a mis qu'une seul coordonnes erreur
			        if(choix.length==1)
			        throw new ArrayIndexOutOfBoundsException (null);
			        // Si on entre plus de 2 arguments c'est qu'on veut juste placer une bombe
			        if( choix.length > 2 ) {
			        // si le 3 choix n'est pas un P erreur
			        if(!choix[2].equals("P")) 
			        throw new NullPointerException (null);
			        else grille.initDrapeau(x,y) ;
		        }
			    // Sinon c'est qu'on ouvre juste une case avec 2 arguments
			    else  grille.open(x, y);
		        }
		        catch(NullPointerException e){
		        System.out.println(" /!\\ /!\\ /!\\ Pour inserer un drapeau il faut entrer les coordonnées puis un P /!\\ /!\\ /!\\ ");}
		        catch(ArrayIndexOutOfBoundsException e){
		        System.out.println("/!\\ /!\\ /!\\  Veuillez entrer 1 coordonée x(colonne) et 1 coordonnée y(ligne) separés d'un espace \n "
		        + "Attention a ne pas depasser "+(Grille.getColonne()-1) +" pour les colonnes et "+(Grille.getLigne()-1)+"pour les lignes /!\\ /!\\ /!\\ ") ;  }  
		        catch(NumberFormatException e){
				    System.out.println("/!\\ /!\\ /!\\  Veuillez entrer les coordonées en utilisant les chiffres proposés /!\\ /!\\ /!\\ ") ;  }
		        endtime=System.currentTimeMillis();     
		  }
		  // Des que l'on a gagné ou perdu on affiche une derniere fois la grille  
		  grille.Affiche();
		  // condition ternaire pour verifier la gagne
		  System.out.println(grille.getCasesLibres() == 0 ? "Felicitation vous avez gagne." : "Vous avez perdu.");
		  System.out.println("Vous avez joué cette partie en "+getTempsEcoule(startime,endtime)+" secondes");
	    } 
	 /**
	  * 
	  * @param startime temsps actuel en millis secondes au debut de la partie
	  * @param endtime temps actuel en millis secondes a la fin de la partie
	  * @return Retourne le temps ecoulé en secondes
	  */
	 public double getTempsEcoule(double startime, double endtime) {
		 
		 tempecoule=((endtime-startime)/1000); 
		 return tempecoule; 
	 }

}

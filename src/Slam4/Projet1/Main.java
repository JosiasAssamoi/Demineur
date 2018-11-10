package Slam4.Projet1;
import java.util.Scanner;

/**
 * <p>Cette classe represente le Menu principal du Démineur. Elle appelle a chaque premier lancement une méthode regle() qui affiche les règles.</p>
 *<p> Un menu est proposé avec differents niveaux dont un mode triche qui permet d'avoir 50 % de bombes devoilées ou encore un mode personnalisé qui 
 * permet de choisir son nombre de ligne de colonne, un pourcentage de bombe et si l'on souhaite utiliser le mode triche ou non. </p>
 *Le nombre bombes est exprimé en pourcentage pour ne pas avoir de conflit avec la taille de la supposée matrice.
 * Toutes les Exceptions en entrée non pas été gerées.. 
 * 
 *
 * 
 */


public class Main {

  static int choix = 0; 
  static int pourcent;
  static Scanner scanner = new Scanner(System.in);
  static String triche="N";

 public static void regle() {			
    System.out.println("Bienvenue dans le demineur de Josias et Calvin. \nPour jouer il vous faut rentrer des coordonnées x (colonne) et y(ligne) "
	+ "separés d'un espace. \nPour ajouter une case en tant que drapeau rajouter un espace puis P. \nLe jeu ne s'arretera "
	+ "automatiquement que lorsque vous aurez perdu ou (nous l'esperons) gagné ... =) Goog Luck!! \n");
 }
 
public static void main(String... args){
	regle();
	do { 
	System.out.println(" ***\t MENU \t***");
	System.out.println("Taper 1 pour le mode facile \nTaper 2 pour le mode normal \nTaper 3 Pour le mode Hard \n"
	+ "Taper 4 pour personnaliser votre partie \nTaper 5 pour le mode triche \nTaper 6 pour quitter");
	choix = scanner.nextInt(); 
	Game game = null;
	
	if(choix==1)game= new Game(12);
	
	else if(choix==2) game= new Game(25);
	
	else if(choix==3) game= new Game(50);
	
	else if(choix==4) { 
	System.out.println("Veuillez donner un pourcentage de bombes ");
    pourcent = scanner.nextInt(); 
    System.out.println("Un nombre de colonne?");
	Grille.setColonne(scanner.nextInt());
    System.out.println("Un nombre de ligne?");
	Grille.setLigne(scanner.nextInt());
	System.out.println("Voulez vous activer le mode triche (O / N) ?");
	scanner.nextLine();
	try {
	triche=scanner.nextLine().toUpperCase();
	if(triche.length() < 1 ||  !triche.equals("O") ||  !triche.equals("N") ) throw new Exception();
	}
	catch(Exception e) {
		System.out.println("Vous n'avez pas activé le mode triche car nous n'avons pas compris votre reponse entre O ou N ");
	}
	
    game= new Game(pourcent,triche);
    }
	
	else if(choix==5) game= new Game(75,triche="O");
	
	else if(choix==6) System.out.println("Ce n'est qu'un au revoir .....");
	else System.out.println("Votre choix n'est pas compréhensible.");
	if(choix==1 || choix==2 || choix==3 || choix==4 || choix==5)game.play();
	
	}while(choix!=6);
	scanner.close();

  }


}
package Slam4.Projet1;

import java.util.Scanner;

public class Main {

  static int choix = 0; 
  static Scanner scanner = new Scanner(System.in);
 
  
 public static void regle() 
 {
			
    System.out.println("Bienvenue dans le demineur de Josias et Calvin. \nPour jouer il vous faut rentrer des coordonnées x (colonne) et y(ligne) "
	+ "separés d'un espace. \nPour ajouter une case en tant que drapeau rajouter un espace puis P. \nLe jeu ne s'arretera "
	+ "automatiquement que lorsque vous aurez perdu ou (nous l'esperons) gagné ... =) Goog Luck!! \n");

 }
 
public static void main(String... args){
	regle();
	do {
   
	System.out.println(" ***\t MENU \t***");
	System.out.println("Taper 1 pour le mode facile \nTaper 2 pour le mode normal \nTaper 3 Pour le mode Hard \n Taper 4 pour quitter");
	choix = scanner.nextInt();
	Game game = null;
	if(choix==1)game= new Game(12);
	else if(choix==2) game= new Game(25);
	else if(choix==3) game= new Game(50);
	else if(choix==4) System.out.println("Ce n'est qu'un au revoir .....");
	else System.out.println("Votre choix n'est pas compréhensible.");
	if(choix==1 || choix==2 || choix==3)game.play();
	
	}while(choix!=4);
	scanner.close();

  }


}
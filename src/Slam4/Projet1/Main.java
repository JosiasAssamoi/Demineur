package Slam4.Projet1;
public class Main {


 public static void regle() 
 {
			
    System.out.println("Bienvenue dans le demineur de Josias et Kalvin. \nPour jouer il vous faut rentrer des coordonnées x (colonne) et y(ligne) "
	+ "separés d'un espace. \nPour ajouter une case en tant que drapeau rajouter un espace puis P. \nLe jeu ne s'arretera "
	+ "automatiquement que lorsque vous aurez perdu ou (nous l'esperons) gagné ... =) Goog Luck!! \n");

 }
 
public static void main(String... args)
  {
	regle();
   Game game=  new Game(10);
   game.play();

  }


}
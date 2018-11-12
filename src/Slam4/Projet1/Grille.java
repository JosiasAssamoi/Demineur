package Slam4.Projet1;
import java.util.Random;

/**
 * <p>Cette classe correspond a la matrice de cases.  </p>
 * 
 *
 */

public class Grille 
{
	private static  Cell[][] cases; 
	private static int colonne;
	private static int ligne ;
    private int casesLibres;
	
    /**
     * 
     * @return Le nombre de ligne
     */
	public static int getLigne() {
		return ligne;
	}
	  /**
	   * 
	   * @return Le nombre de colonne
	   */
	  public static int getColonne() {
		return colonne;
	}
	  
	  /**
	   * La fonction permet d'initialiser un nombre de ligne
	   * @param ligne le nombre de ligne souhaité
	   */
	  public  static  void setLigne(int ligne) {
			Grille.ligne = ligne;
		}
		  
	  /**
	   * La fonction permet d'initialiser un nombre de colonne
	   * @param ligne le nombre de colonne souhaité
	   */  
	public static void  setColonne(int colonne) {
			  Grille.colonne = colonne;
		}
	
	 /**
	  *  Récupere une cases avec ses coordonnées
	  * @param x coordonnée x (colonne)
	  * @param y coordonnée y (ligne)
	  * @return Retourne la case
	  */
	public static Cell getCases(int x, int y) {
		return cases[x][y];
	  }
	  
	/**
	 * Initialise une case
	 * @param x coordonnée x (colonne)
	 * @param y  coordonnée y (ligne)
	 */
	  public static void setCases(int x, int y) {
		 
		  cases[x][y]= new Cell(false,false,false,true);
	  }
	/**
	 * 
	 * @return le nombre de cases libres.
	 */
	public int getCasesLibres() {
		return casesLibres;
	}


	 /**
	  * Dans le constructeur on genère le tableau de cases  avec une boucle allant de 0 au nombre de ligne et de 0 au nombre de ligne.
	  * @param pourcent coorespond au pourcentage de bombes recu en paramètre.
	  */
	  public Grille( int pourcent)
	  {
	    cases = new Cell[colonne][ligne];
		
		for(int x = 0; x<colonne; x++)
			for(int y=0; y<ligne; y++)
				Grille.setCases(x, y);
	    generate(pourcent);
	  
	  }
	
	  /**
	   * Genere les bombes et les valeurs des cases avoisinantes. 
	   * @param bombs en reférence au pourcentage de bombe
	   
	   */
	  private void generate(int bombs)
	  {
		  
	    bombs = (int) colonne * ligne * bombs / 100; // ex  5 * 5 * 20 = 500 / 100 = 5 bombes. 
	    casesLibres = colonne* ligne - bombs;
	    Random random = new Random();

	    while(bombs != 0){
	      // On genere une case aléatoire
	      int randomx = random.nextInt(colonne);
	      int randomy = random.nextInt(ligne);
	      
	     // si cette case est une bombe on continue 
	      if(Grille.getCases(randomx,randomy).isMine()) continue;
	      // sinon on  place une bombe dessus 
	      Grille.getCases(randomx, randomy).setMine(true);
	      
	      // on va placer les valeurs autour de la case donc a x - 1 => y 1 y 0 et y - 1 , x 0 => y 1 y 0 et y - 1 , et x 1 => y 1 y 0 et y - 1
		  for(int x1 = -1; x1 < 2; x1++)
	      {
	        for(int y1 = -1; y1 < 2; y1++){
	          // on ajoute un a la valeur seulement si ce n'est pas une bombe et que la case est bien valide
	          if(Cell.isValide(randomx+x1,randomy+y1) && (!Grille.getCases((randomx+x1),(randomy+y1)).isMine())) {
	        	  Grille.getCases((randomx+x1),(randomy+y1)).setValeur(+1);
	          	  Grille.getCases((randomx+x1),(randomy+y1)).setVide(false);
	          }	          
	        }
	      } 
		  //sans oublier de decreménter le nombre de bombe a chaque fois que l'on a placé des bombes et des valeurs
		  bombs--; 
	   }
	}
	
	  /**
	   * Affiche la Grille 
	   */
	  public   void Affiche()
	  {
		// L'objet String Builder permet de concatener assez simplement une chaine de caractere pratique pour simuler un tableau  
	    StringBuilder chaineformate = new StringBuilder();

	    // on commence a  -1 car on veut afficher les reperes de la matrices et on commence par y car on veut aller de ligne en ligne 
	    for(int y = -1; y < ligne; y++)
	    {
	      chaineformate.append("[").append(y == -1 ? " " : y).append("]");
	      for(int x = 0; x < colonne ; x++)
	      {
	    	//si y == -1 on affiche les repere
	        chaineformate.append("[\t").append(y==-1 ? x
	        // sinon si c'est un drapeau P 		
	        : Grille.getCases(x,y).isDrapeau() ? "P"
	        //Sinon si decouverte => Si c'est une bombe + 
	        : Grille.getCases(x,y).isDecouverte() ? Grille.getCases(x,y).isMine() ? "+"
	        // Sinon si decouverte mais vide on ecrit vide 
	        : Grille.getCases(x,y).isVide()  ? "Vide"
	        // Sinon affiche simplement sa valeur
	        : Grille.getCases(x,y).getValeur()
	        : "?" ).append("\t]");
	      }
	      // on passe a la ligne pour chaque ligne
	      chaineformate.append("\n");
	    }
	    // on affiche cette chaine formattée avec la méthode toString
	    System.out.println(chaineformate.toString());
	  }
	  
	  /**
	   * Ouvre une case
	   * @param x coordonnée x (colonne)
	   * @param y coordonnée y (ligne)
	   */
	  public void open(int x, int y)
	  {
		// Si la case que  ouverte est un drapeau on previent que l'on ne peut pas ouvrir une case avec drapeau
	    if(Grille.getCases(x,y).isDrapeau())System.out.println("On ne peut pas ouvrir une case avec un drapeau");
		// Si la case   ouverte n'est pas un drapeau et que c'est une bombe
	    if(!Grille.getCases(x,y).isDrapeau() && Grille.getCases(x,y).isMine())
	    {
	      Grille.getCases(x,y).setDecouverte(true);
	      // donc partie perdue
	      casesLibres = -1;
	      for(int xx = 0; xx < colonne; xx++)
	      {
	        for(int yy = 0; yy < ligne; yy++)
	        {
	        // si une bombe est ouverte on etablit toutes les autres en tant que decouverte pour l'affichage	
	          if(Grille.getCases(xx,yy).isMine()) Grille.getCases(xx,yy).setDecouverte(true);
	        }
	      }
	    }
	    // sinon on deblaye cette case
	    else  deblaye(x, y);
	  }
	  
	  private void deblaye(int x, int y)
	  {
		// si pas valide ou quelle est deja decouverte ou que c'est un drapeau on n'arrete tout stop stop stop
	    if(!Cell.isValide(x, y) || Grille.getCases(x,y).isDecouverte() ||Grille.getCases(x,y).isDrapeau()) return;
	    else {  Grille.getCases(x,y).setDecouverte(true);
	    casesLibres--;}
	    
	    // on check autour des cases vides si il n'y a pas d'autres cases ouvertes
	    if(Grille.getCases(x,y).isVide()) {
	    	 deblaye(x-1, y);
	    	 deblaye(x-1, y+1);
	    	 deblaye(x+1,y-1);
	    	 deblaye(x+1, y);
	    	 deblaye(x+1, y+1);
	    	 deblaye(x, y-1);
	    	 deblaye(x, y+1);
	         deblaye(x-1, y-1);}
	    else return;
	  }
	  
	/**
	 * Place un drapeau sur la grille
	 * @param x coordonnée x(colonne)
	 * @param y coordonnée y(ligne)
	 */
	public void initDrapeau(int x, int y) {
		
		Grille.getCases(x,y).setDrapeau() ;
		
	} 
	
	/**
	 * Place des drapeaux sur 50 % des bombes sur la Grille
	 */
	public static void getTriche() {
		Random randomTriche = new Random();
		// nombres de bombe divisé par 2 
		int nbDrapeauTriche= ((colonne * ligne * Game.getPourcent() / 100)/2);
	    int compteur=0;
		do {
			 int rx = randomTriche.nextInt(colonne);
			 int ry = randomTriche.nextInt(ligne);
			 //System.out.println("cases"+rx+" "+ry+"bombe ?"+Grille.getCases(rx, ry).isMine()+" drapeau ?"+Grille.getCases(rx, ry).isDrapeau());
	          if(Grille.getCases(rx,ry).isMine() && !Grille.getCases(rx,ry).isDrapeau() ) {
	       //  System.out.println("On place un drapeau a la case"+rx+" "+ry+"compteur ="+compteur);
	         Grille.getCases(rx,ry).setDrapeau();
	         compteur++ ; }
	          else continue ;
	    }while(compteur != nbDrapeauTriche );
	}
}

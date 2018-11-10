package Slam4.Projet1;

import java.util.Random;

public class Grille 
{


	  private static  Cell[][] cases;
	  
	private  static   int colonne = 3;
	private  static int ligne = 3;
    private   int casesLibres;
	
	public static int getLigne() {
		return ligne;
	}
	  
	  public static int getColonne() {
		return colonne;
	}
		public  static  void setLigne(int ligne) {
			Grille.ligne = ligne;
		}
		  
		  public static void  setColonne(int colonne) {
			  Grille.colonne = colonne;
		}
	
	  
	public static Cell getCases(int x, int y) {
		return cases[x][y];
	  }
	  
	  public static void setCases(int x, int y) {
		 
		  cases[x][y]= new Cell(false,false,false,true);
	  }

	public int getCasesLibres() {
		return casesLibres;
	}

	// j'ai mis les lignes et colonnes en static pour tester notamment le pourcentage
	  public Grille( int pourcent)
	  {
	    cases = new Cell[colonne][ligne];
		
		for(int x = 0; x<colonne; x++)
			for(int y=0; y<ligne; y++)
				Grille.setCases(x, y);
	    generate(pourcent, new Random());
	  
	  }
	
	  private void generate(int bombs, Random random)
	  {
		
	    bombs = (int) colonne * ligne * bombs / 100;
	    casesLibres = colonne* ligne - bombs;
	    
	
	    while(bombs != 0)
	    {
	   
	      int randomx = random.nextInt(colonne);
	      int randomy = random.nextInt(ligne);
	      
	     
	      if(Grille.getCases(randomx,randomy).isMine()) continue;
	      cases[randomx][randomy].setMine(true);
		  for(int x1 = -1; x1 < 2; x1++)
	      {
			
	        for(int y1 = -1; y1 < 2; y1++){
	          if(Cell.isValide(randomx+x1,randomy+y1) && (!Grille.getCases((randomx+x1),(randomy+y1)).isMine())) {
	        	  Grille.getCases((randomx+x1),(randomy+y1)).setValeur(+1);
	          	  Grille.getCases((randomx+x1),(randomy+y1)).setVide(false);
	          }
	          
	        }
	      } 
		  bombs--; 
	   }
	}
	
	  public   void Affiche()
	  {		  
	    StringBuilder chaineformate = new StringBuilder();

	    for(int y = -1; y < ligne; y++)
	    {
	      chaineformate.append("[").append(y == -1 ? " " : y).append("]");
	      for(int x = 0; x < colonne ; x++)
	      {
	        chaineformate.append("[\t").append(y==-1 ? x
	          : Grille.getCases(x,y).isDrapeau() ? "P"
	          : Grille.getCases(x,y).isDecouverte() ? Grille.getCases(x,y).isMine() ? "+"
	          : Grille.getCases(x,y).isVide()  ? "Vide"
	          : Grille.getCases(x,y).getValeur()
	          : "?" ).append("\t]");
	      }
	      chaineformate.append("\n");
	    }
	    System.out.println(chaineformate.toString());
	  }
	  
	  public void open(int x, int y)
	  {

	    if(!Grille.getCases(x,y).isDrapeau() && Grille.getCases(x,y).isMine())
	    {
	      Grille.getCases(x,y).setDecouverte(true);
	      casesLibres = -1;
	      for(int xx = 0; xx < colonne; xx++)
	      {
	        for(int yy = 0; yy < ligne; yy++)
	        {
	          if(Grille.getCases(xx,yy).isMine()) Grille.getCases(xx,yy).setDecouverte(true);
	        }
	      }
	    }
	    else deblaye(x, y);
	  }
	  
	  private void deblaye(int x, int y)
	  {
		  
	    if(!Cell.isValide(x, y) || Grille.getCases(x,y).isDecouverte() ||Grille.getCases(x,y).isDrapeau()) return;
	    else {  Grille.getCases(x,y).setDecouverte(true);
	    casesLibres--;}
	    
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
	  
	 
	public void initDrapeau(int x, int y) {
		
		Grille.getCases(x,y).setDrapeau() ;
		
	} 
	public static void getTriche() {
		Random randomTriche = new Random();
		int drapeautriche= ((colonne * ligne * Game.getPourcent() / 100)/2);
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
	    }while(compteur != drapeautriche );
	}
}

package Slam4.Projet1;
public class Cell   {
	


	private boolean mine=false, decouverte, drapeau,vide;
	int valeur ;
	

	 public Cell(boolean mine, boolean decouverte,boolean drapeau,boolean vide) {
		this.mine =mine; 
		this.decouverte=decouverte; 
		valeur = 0 ;
		this.drapeau=drapeau;
		this.vide=vide;
		
	}

		// méthodes (mutateurs et assesseurs)
		public boolean isVide()		{  return vide; }
		public void setVide(boolean b)		{ vide= b; }
		public boolean isMine()		{  return mine; }
		public boolean isDecouverte() 	{ return decouverte; }
		public boolean isDrapeau()		{ return drapeau; }
		public int getValeur()			{ return valeur; }
		public void setMine(boolean b)		{ mine = b; valeur = -1; }
		public void setDecouverte(boolean d){ decouverte = d; }
		public void setDrapeau()
		{
			if (decouverte==true) return;
			drapeau = !drapeau;

		 }
		
	public void setValeur(int v)		{ valeur += v;}
	
	public static boolean isValide(int x, int y) {
			if (  (x >= 0 && y >=0) && ( x < Grille.getColonne() ) && ( y < Grille.getLigne() ) ) 
			return true;
			else return false;
			
		}
		



}

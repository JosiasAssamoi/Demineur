package Slam4.Projet1;
public class Cell   {
	
/**
 *<p> Cette classe Permet d'attribuer des caracteristiques à une case.</p>
 *<p> Par defaut cne  case n'a pas de bombe, n'est pas decouverte, n'a pas de drapeau, et est vide !</p>
 * Sa valeur est à zero tant qu'il n'y a pas de bombe autour. 
 * <b>Il n'y a que des getteurs et des setteurs dans cette classe ! </b>
 * 
 * 
 */

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
	
	/**
	 * 
	 * @param x Fait réference au numero de la colonne en question
	 * @param y Fait réference au numero de la ligne en question
	 * @return true si les coordonnées de la cases sont plus grand que 0 et qu'ils sont compris dans la taille de la matrice. Sinon elle retourne false.
	 
	 */
	public static boolean isValide(int x, int y) {
			if (  (x >= 0 && y >=0) && ( x < Grille.getColonne() ) && ( y < Grille.getLigne() ) ) 
			return true;
			else return false;
			
		}
		



}

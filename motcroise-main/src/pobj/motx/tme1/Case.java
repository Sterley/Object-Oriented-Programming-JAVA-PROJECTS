package pobj.motx.tme1;


public class Case {
	private int lig;
	private int col;
	private char c;
	
	/*Constructeur de la class Case qui recoit 3 parametres*/
	public Case(int lig, int col, char c) {
		this.lig = lig;
		this.col = col;
		this.c = c;
	}
	
	/* Acsesseur pour pouvoir recuperer le numero de ligne d'une case*/
	public int getLig() { 
		return this.lig; 
	}
	/* Acsesseur pour pouvoir recuperer le numero de colonne d'une case*/
	public int getCol() { 
		return this.col; 
	}
	/* Acsesseur pour pouvoir recuperer le caractere d'une case*/
	public char getChar() { 
		return this.c; 
	}
	/* Acsesseur pour pouvoir modifier le caractere d'une case*/
	public void setChar(char c) { 
		this.c = c; 
	}
	/* Methode pour verifier si une case est vide ou pas ! */
	public boolean isVide() {
		return this.c == ' ';
	}
	/* Methode pour verifier si une case est pleine ou pas ! */
	public boolean isPleine() {
		return this.c == '*';
	}
	
}

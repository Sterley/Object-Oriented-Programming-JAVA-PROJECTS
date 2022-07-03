package pobj.motx.tme1;

public class Grille {
	/* Une Grille est une matrice de case*/
    private Case[][] matrice;

    public Grille(int hauteur, int largeur) {
        this.matrice = new Case[hauteur][largeur];
        for(int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                this.matrice[i][j] = new Case(i, j, ' ');
            }
        }
    }

	/*Ascesseur de Case d'une grille*/
    public Case getCase(int lig, int col) {
        return this.matrice[lig][col];
    }

    public String toString() {
		return GrilleLoader.serialize(this, true);
    }
	
	/* Hauteur d'une grille*/
    public int nbLig() {
        return this.matrice.length;
    }
	/*Largeur de la grille*/
    public int nbCol() {
        return this.matrice[0].length;
    }
	/*Methode permettant de realiser la copie profonde d'une grille*/
    public Grille copy() {

        Grille grille = new Grille(this.matrice.length, this.matrice[0].length);

		for(int i = 0; i < grille.matrice.length; i++) {
            for(int j = 0; j < grille.matrice[0].length; j++) {
                grille.matrice[i][j].setChar(this.matrice[i][j].getChar());
            }
        }

        return grille;

    }

}

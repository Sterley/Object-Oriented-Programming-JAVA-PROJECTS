package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.Emplacement;
import pobj.motx.tme1.Grille;
import pobj.motx.tme3.CroixContrainte;
import pobj.motx.tme3.IContrainte;
import pobj.motx.tme1.Case;


public class GrillePlaces {

    private List<Emplacement> places = new ArrayList<>();
    private List<Emplacement> placesH = new ArrayList<>();
    private List<Emplacement> placesV = new ArrayList<>();
    
    private List<IContrainte> contraintes = new ArrayList<>();
    

    private Grille grille;

    private int NbHorizontal;

    public GrillePlaces (Grille grille) {

        this.grille = grille;

        /* Je parcours les lignes pour trouver les Emp Horizontaux*/
        for(int i = 0; i < this.grille.nbLig(); i++) {
            cherchePlaces(getLig(i), 0);
        }

        this.NbHorizontal = places.size();

        /* Je parcours les colonnes pour trouver les Emp Verticaux*/
        for(int j = 0; j < this.grille.nbCol(); j++) {
            cherchePlaces(getCol(j), 1);
        }   

    }

    /*retournent les cases qui constituent une ligne*/
    private List<Case> getLig (int lig) {
        List<Case> cases = new ArrayList<>();
        for(int j = 0; j < this.grille.nbCol(); j++) {
            cases.add(this.grille.getCase(lig, j));
        }
        return cases;
    }

    /*retournent les cases qui constituent une colonne*/
    private List<Case> getCol (int col) {
        List<Case> cases = new ArrayList<>();
        for(int i = 0; i < this.grille.nbLig(); i++) {
            cases.add(this.grille.getCase(i, col));
        }
        return cases;
    }

    /*cherche les emplacements dans la liste de cases fournie (une ligne ou une colonne) et qui les ajoute à la liste.
    Un emplacement de mot est défini dès que nous avons deux cases contiguës non pleines (donc
    vides ou avec une lettre déjà placée) */
    private void cherchePlaces(List<Case> cases, int hv) {

        Emplacement mot = new Emplacement();

        for(int i = 0; i < cases.size(); i++) {

            if( ! cases.get(i).isPleine() ) {
                mot.add(cases.get(i));
            }
            else{
                if(mot.size() > 1) {
                	
                    this.places.add(mot);
                    if(hv==0) {
                    	this.placesH.add(mot);
                    }
                    if(hv==1) {
                    	this.placesV.add(mot);
                    }
                    
                    mot = new Emplacement();
                }
                mot = new Emplacement();
            }

            if(i == cases.size() - 1) {
                if(mot.size() > 1) {
                	
                    this.places.add(mot);
                    if(hv==0) {
                    	this.placesH.add(mot);
                    }
                    if(hv==1) {
                    	this.placesV.add(mot);
                    }
                    
                    mot = new Emplacement();
                }
            }

        }

    }

    /*Accesseur a la liste des emplacements trouves*/
    public List<Emplacement> getPlaces() {
        return this.places;
    }
    /*Accesseur au nom d'emplacement Horizontal*/
    public int getNbHorizontal() {
        return this.NbHorizontal;
    }

    public String toString() {
        String temp = "";
        for(int i = 0; i < this.places.size(); i++){

            String temp1 = "";
            for(int j = 0; j < this.places.get(i).size(); j++){
                temp1 += this.places.get(i).getCase(j);
            }
            temp += temp1 + " ";

        }
        return temp;
    }



    public GrillePlaces fixer(int m, String soluce) {
        Grille newGrille = this.grille.copy();

        for(int i = 0; i < soluce.length(); i++) {
           newGrille.getCase(this.places.get(m).getCase(i).getLig(), this.places.get(m).getCase(i).getCol()).setChar(soluce.charAt(i));
        }
        
        return new GrillePlaces(newGrille);
    }
    
	public List<IContrainte> getContraintes() {
		return this.contraintes;
	}
    
    public void TrouverCroix() {
    	this.contraintes = new ArrayList<>();
    	for(int x=0; x<this.placesH.size(); x++) {
    		Emplacement m1 = this.placesH.get(x);
    		for(int z=0; z<this.placesV.size(); z++) {
    			Emplacement m2 = this.placesV.get(z);
    			for(int i=0; i<m1.size(); i++) {
    				for(int j=0; j<m2.size(); j++) {
    					if(m1.getCase(i).getLig() == m2.getCase(j).getLig() && m1.getCase(i).getCol() == m2.getCase(j).getCol()) {
    						if(m1.getCase(i).isVide()) {
        						CroixContrainte temp = new CroixContrainte(x, i, z + this.placesH.size(), j);
        						this.contraintes.add(temp);
    						}
    					}
    				}
    			}	
    		}
    	}
    }


}
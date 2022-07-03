package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;

public class GrillePotentiel {

    protected GrillePlaces places;
    private Dictionnaire dico;
    private List<Dictionnaire> motsPot = new ArrayList<>();

    public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet) {
        this.places = grille;
        this.dico = dicoComplet;

        for(int i=0; i < grille.getPlaces().size(); i++) {

            Dictionnaire temp = dico.copy();
            temp.filtreLongueur((grille.getPlaces().get(i)).size());
            for(int j=0; j < grille.getPlaces().get(i).size(); j++) {
                if(!grille.getPlaces().get(i).getCase(j).isVide()) {
                    temp.filtreParLettre(grille.getPlaces().get(i).getCase(j).getChar(),j);
                }
            }
            this.motsPot.add(temp);
        }

    }

    public boolean isDead() {
        for(int i=0; i < this.motsPot.size(); i++) {
            if(this.motsPot.get(i).size() == 0){
                return true;
            }
        }
        return false;
    }

    public List<Dictionnaire> getMotsPot() {
        return this.motsPot;
    }
    
    public GrillePlaces getGPlaces() {
        return this.places;
    }
    
    public void set1Dico(int i, Dictionnaire dico) {
    	this.motsPot.set(i, dico);
    }

    
    public GrillePotentiel fixer(int m, String soluce) {
        GrillePlaces grille = places.fixer(m, soluce);
        return new GrillePotentiel(grille, dico);
    }
    
    public int filtreParEnsembleLettre(int i, int c, EnsembleLettre s) {
    	Dictionnaire temp = this.motsPot.get(i).copy();
    	int cp = temp.filtreParEnsembleLettre(c, s);
    	this.motsPot.set(i, temp);
    	return cp;
    }
    
}
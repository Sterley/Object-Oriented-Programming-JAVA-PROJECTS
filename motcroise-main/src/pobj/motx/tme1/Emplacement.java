package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;

public class Emplacement { 

    /*La liste de Case determinant l'emplacement d'un mot*/
    private List<Case> cases = new ArrayList<>();

    public Emplacement() {

    }
    /*Methode pour ajouter une case a l'emplacement d'un mot*/
    public void add(Case e) {
        this.cases.add(e);
    }
    /*Methode pour determiner la taille d'un mot*/
    public int size() {
        return this.cases.size();
    }
    /*Ascesseur qui permet d'acceder a une case d'un mot*/
    public Case getCase(int i) {
        return this.cases.get(i);
    }

    public String toString() {
        String temp = "";
        for(int i = 0; i < this.cases.size(); i++){
            temp += this.cases.get(i).getChar();
        }
        return temp;
    }


}
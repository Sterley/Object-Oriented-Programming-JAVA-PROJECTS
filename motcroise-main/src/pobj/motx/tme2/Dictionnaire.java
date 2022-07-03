package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.io.*;

/**
 * Un ensemble de mots.
 *
 */
public class Dictionnaire {

	// stockage des mots
	private List<String> mots = new ArrayList<>();

	/**
	 * Ajoute un mot au Dictionnaire, en dernière position.
	 * @param mot à ajouter, il sera stocké en minuscules (lowerCase)
	 */
	public void add(String mot) {
		mots.add(mot.toLowerCase());
	}

	/**
	 * Taille du dictionnaire, c'est à dire nombre de mots qu'il contient.
	 * @return la taille
	 */
	public int size() {
		return mots.size();
	}
	
	/**
	 * Accès au i-eme mot du dictionnaire.
	 * @param i l'index du mot recherché, compris entre 0 et size-1.
	 * @return le mot à cet index
	 */
	public String get(int i) {
		return mots.get(i);
	}

	/**
	 * Rend une copie de ce Dictionnaire.
	 * @return une copie identique de ce Dictionnaire
	 */
	public Dictionnaire copy () {
		Dictionnaire copy = new Dictionnaire();
		copy.mots.addAll(mots);
		return copy;
	}

	/**
	 * Retire les mots qui ne font pas exactement "len" caractères de long.
	 * Attention cette opération modifie le Dictionnaire, utiliser copy() avant de filtrer pour ne pas perdre d'information.
	 * @param len la longueur voulue 
	 * @return le nombre de mots supprimés
	 */
	public int filtreLongueur(int len) {
		List<String> cible = new ArrayList<>();
		int cpt=0;
		for (String mot : this.mots) {
			if (mot.length() == len)
				cible.add(mot);
			else
				cpt++;
		}
		this.mots = cible;
		return cpt;
	}

    public static Dictionnaire loadDictionnaire(String path) {
		Dictionnaire d = new Dictionnaire();
        // Try-with-resource : cette syntaxe permet d’accéder au contenu du fichier ligne par ligne.
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                for (String line = br.readLine() ; line != null ; line = br.readLine() ) {
                    // Utiliser "line".
					String mot = line;
					d.add(mot);
                }
        } catch (IOException e) {
            // Problème d’accès au fichier.
            e.printStackTrace();
        }
		return d;
    }

	
	@Override
	public String toString() {
		if (size() == 1) {
			return mots.get(0);
		} else {
			return "Dico size =" + size();
		}
	}

	public int filtreParLettre(char c, int i) {
		List<String> cible = new ArrayList<>();
		int cpt=0;
		for (String mot : this.mots) {
			if (i < mot.length()) {
				if(mot.charAt(i) == c) {
					cible.add(mot);
				}
				else {
					cpt++;
				}
			}
			else
				cpt++;
		}
		this.mots = cible;
		return cpt;	
	}
	
	public EnsembleLettre getEnsembleLettre(int c) {
		EnsembleLettre temp = new EnsembleLettre();
		for(int i=0; i<this.mots.size(); i++) {
			temp.add(this.mots.get(i).charAt(c));
		}
		return temp;
	}
	
	public int filtreParEnsembleLettre(int i, EnsembleLettre lp) {
		int cp=0;
		Set<Character> temp = lp.getLettres();
		for(Character l : temp) {
			cp = cp + this.filtreParLettre(l, i);
		}
		return cp;
	}
	
}
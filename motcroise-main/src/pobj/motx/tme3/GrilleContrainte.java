package pobj.motx.tme3;
import java.util.ArrayList;
import java.util.List;
import pobj.motx.tme2.*;

public class GrilleContrainte extends GrillePotentiel {

	private List<IContrainte> contraintes = new ArrayList<>();
	
	public GrilleContrainte(GrillePlaces grille, Dictionnaire dicoComplet) {
		super(grille, dicoComplet);
		
		super.places.TrouverCroix();
		
	}
	
	public List<IContrainte> getContraintes() {
		return this.contraintes;
	}
}

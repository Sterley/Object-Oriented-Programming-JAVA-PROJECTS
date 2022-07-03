package pobj.motx.tme2;
import java.util.HashSet;
import java.util.Set;

public class EnsembleLettre {
	
	private Set<Character> lettres =  new HashSet<Character>();

	public void add(char c) { 
		this.lettres.add(c);
	}
	
	public int size(){
		return lettres.size();
	}
	
	public Set<Character> getLettres() {
		return this.lettres;
	}
	
	public EnsembleLettre intersection (EnsembleLettre lp) {
		Set<Character> temp = this.getLettres();
		temp.retainAll(lp.getLettres());
		EnsembleLettre inter = new EnsembleLettre();
		for(Character c : temp) {
			inter.add(c);
		}
		return inter;
	}

}
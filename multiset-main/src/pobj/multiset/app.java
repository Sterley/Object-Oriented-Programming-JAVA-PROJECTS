package pobj.multiset;

import java.util.Iterator;
import java.util.List;

public class app {

	public static void main(String[] args) {
		NaiveMultiSet<String> ms = new NaiveMultiSet<>();
		ms.add(" Sterley", 3);
		ms.add(" Peon", 1);
		ms.add(" Nono", 2);
		Iterator<String> itt = ms.iterator();
		while(itt.hasNext()) {
			System.out.print(itt.next());
		}
		
		List<String> elements = ms.elements();
		for(int n=1; n<elements.size(); n++){
			String cle = elements.get(n);
			int j = n-1;
			while(j>=0 && ms.count(elements.get(j)) < ms.count(cle)){
				String toAdd = elements.get(j);
				elements.set(j+1, toAdd);
				j = j-1;
			}
			elements.set(j+1, cle);
		}
		
		for(String e : elements) {
			System.out.print('\n'+e);
		}
		
	}

}


    




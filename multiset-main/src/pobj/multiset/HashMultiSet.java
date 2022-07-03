package pobj.multiset;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class HashMultiSet<T> extends AbstractCollection<T> implements MultiSet<T> {
	
	private HashMap<T, Integer> map;
	private int size;
	
	
	public HashMultiSet() {
		this.map = new HashMap<>(); // Initialisation avec une Map vide
		this.size = 0; // J'initialise donc la taille a 0
	}
	public HashMultiSet(Collection<T> coll) {
		for(T c : coll) { // Je parcours ma collection
			this.map.put(c, 1); // J'ajoute chaque element de ma collection dans mon multi-ensemble
		}
		this.size = this.UPDsize(); // Pour finir je mets la taille a jour
	}
	
	
	
	public boolean add(T e, int count) {
		// Avant de faire un ajout avec nombre d'occurences
		// Je verifie d'abord si il vaut la peine d'ajouter cad count>0
		if(count>0) {
			// si OUI
			// je verifie si mon ensemble contient deja mon objet
			if(this.map.containsKey(e)) {
				// Si OUI
				// Je calcule le nombre d'occurences de cet objet
				Integer n = this.map.get(e);
				// Je mets a jour cette valeur
				this.map.put(e, n+count);
				// Je mets a jour la taille
				this.size = this.UPDsize();
				// J'indique que l'operation a bien ete effectue
				return true;
			}
			else {
				// Si mon ensemble ne contient pas deja l'objet, je l'add avec le nbr fourni
				this.map.put(e, count);
				// Je mets a jour la taille
				this.size = this.UPDsize();
				// J'indique que l'operation a bien ete effectue
				return true;
			}	
		}
		else {
			// Si count <= 0, pas la peine d'effectuer l'operation
			return false;
		}
	}
	
	
	// Presque la meme documentation
	public boolean add(T e) {
		if(this.map.containsKey(e)) {
			Integer n = this.map.get(e);
			this.map.put(e, n+1);
			
			this.size = this.UPDsize();
			return true;
		}
		else {
			this.map.put(e, 1);
			
			this.size = this.UPDsize();
			return true;
		}
	}
	

	@SuppressWarnings("unchecked")
	public boolean remove(Object e) {
		if(this.map.containsKey(e)) {
			Integer n = this.map.get(e);
			if(n==1) {
				this.map.remove(e);
				
				this.size = this.UPDsize();
				return true;
			}
			if(n>1) {
				this.map.put((T) e, n-1);
				
				this.size = this.UPDsize();
				return true;
			}
		}
		return false;
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean remove(Object e, int count) {
		if(count>0) {
			if(this.map.containsKey(e)) {
				Integer n = this.map.get(e);
				if(n<=count) {
					this.map.remove(e);
					
					this.size = this.UPDsize();
					return true;
				}
				if(n>count) {
					this.map.put((T) e, n-count);
					
					this.size = this.UPDsize();
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	public int count(T o) {
		if(this.map.containsKey(o)) {
			return this.map.get(o);
		}
		else {
			return 0;
		}
	}
	
	
	public void clear() {
		this.map.clear();
		this.size = 0;
	}
	
	
	private int UPDsize() {
		Set<T> listeCle = this.map.keySet();
		int size = 0;
		for(T c : listeCle) {
			size = size + this.map.get(c);
		}
		return size;
	}
	
	public int size() {
		return this.size;
	}
	
	public List<T> elements(){
		Set<T> elm = this.map.keySet();
		List<T> elements = new ArrayList<>();
		for(T e : elm) {
			elements.add(e);
		}
		return elements;
	}
	




	private class HashMultiSetIterator implements Iterator<T> {
		
		private T cur;
		private int nb_occ;

		Iterator<T> it = map.keySet().iterator();
		
		public HashMultiSetIterator() {
			if(!map.isEmpty()) {
				if(this.it.hasNext()){
					this.cur = this.it.next();
					this.nb_occ = map.get(this.cur);
				}		
			}
		}


		public boolean hasNext() {
			if(map.isEmpty()) {
				return false;
			}
			else if ( !this.it.hasNext() && this.nb_occ == 0) {
				return false;
			}
			else{
				return true;
			}
		}
		
		
		public T next(){

			if(this.nb_occ>0){
				this.nb_occ--;
				return this.cur;
			}
			else {
				this.cur = this.it.next();
				this.nb_occ = map.get(this.cur);
				this.nb_occ--;
				return this.cur;
			}
			
		}


		
		
	}


	
	
	public Iterator<T> iterator() { return new HashMultiSetIterator();}
	

}
	
	
	


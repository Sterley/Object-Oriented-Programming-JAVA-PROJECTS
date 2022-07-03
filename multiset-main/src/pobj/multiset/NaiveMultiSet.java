package pobj.multiset;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class NaiveMultiSet<T> extends AbstractCollection<T> implements MultiSet<T> {
	
	private List<T> map;
	
	
	public NaiveMultiSet() {
		this.map = new ArrayList<>(); // Initialisation avec une Map vide
	}
	public NaiveMultiSet(Collection<T> coll) {
		for(T c : coll) { // Je parcours ma collection
			this.map.add(c); // J'ajoute chaque element de ma collection dans mon multi-ensemble
		}
	}
	
	
	
	public boolean add(T e, int count) {
		// Avant de faire un ajout avec nombre d'occurences
		// Je verifie d'abord si il vaut la peine d'ajouter cad count>0
		if(count>0) {
			
			for(int i=0; i<count; i++){
				this.map.add(e);
			}

				return true;
		}
		else {
			// Si count <= 0, pas la peine d'effectuer l'operation
			return false;
		}
	}
	
	
	// Presque la meme documentation
	public boolean add(T e) {
		this.map.add(e);
		return true;
	}
	

	public boolean remove(Object e) {
		if(this.map.contains(e)) {
			Integer n = this.map.indexOf(e);
			this.map.remove(n);
			return true;
		}
		return false;
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean remove(Object e, int count) {
		if(this.map.contains(e)){
			if(count>0) {
				int i=0;
				while( this.map.contains(e) && i<count ){
					this.map.remove(e);
					i++;
				}
				return true;
			}
		}
		return false;
	}
	
	
	
	public int count(T o) {
		if(this.map.contains(o)) {
			return Collections.frequency(this.map, o);
		}
		else {
			return 0;
		}
	}
	
	
	public void clear() {
		this.map.clear();
	}
	
	
	public int size() {
		return this.map.size();
	}
	
	public List<T> elements(){
		Set<T> elm = new HashSet<>();
		for(T e : this.map) {
			if(!elm.contains(e)){
				elm.add(e);
			}
		}
		List<T> elements = new ArrayList<>();
		for(T e : elm) {
			elements.add(e);
		}
		return elements;
	}
	




	private class NaiveMultiSetIterator implements Iterator<T> {
		
		private T cur;
		private int i = 0;

		
		public NaiveMultiSetIterator() {
			if(!map.isEmpty()) {
				this.cur = map.get(this.i);
			}
		}


		public boolean hasNext() {
			if(map.isEmpty()) {
				return false;
			}
			else if ( this.i<map.size()) {
				return true;
			}
			else{
				return false;
			}
		}
		
		
		public T next(){
			this.i++;
			return map.get(this.i-1);	
		}


		
		
	}


	
	
	public Iterator<T> iterator() { return new NaiveMultiSetIterator();}
	

}
	
	
	


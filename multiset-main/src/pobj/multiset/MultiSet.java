package pobj.multiset;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public interface MultiSet<T> extends Iterable<T>, Collection<T> {
	public boolean add(T e, int count);
	public boolean add(T e);
	public boolean remove(Object e);
	public boolean remove(Object e, int count);
	public int count(T o);
	public void clear();	
	public int size();
	public Iterator<T> iterator();
	public List<T> elements();
}
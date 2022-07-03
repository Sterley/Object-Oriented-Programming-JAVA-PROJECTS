package pobj.multiset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import pobj.util.Chrono;

public class WordCount {
	
	public static void main(String[] args) throws IOException {
		System.out.println("HashMultiset");
		Chrono chrono = new Chrono();
			HashMultiSet<String> m = new HashMultiSet<>();
			wordcount(m);
		chrono.stop();
		System.out.println("\nNaiveMultiset");
		Chrono chrono1 = new Chrono();
			NaiveMultiSet<String> m2 = new NaiveMultiSet<>();
			wordcount(m2);
		chrono1.stop();
	}

	public static void wordcount(MultiSet<String> ms) throws IOException {
		
		//Lecture du fichier
		String file = "data/WarAndPeace.txt";
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine())!=null) {
			for (String word : line.split("\\P{L}+")) {
				if (word.equals("")) continue; 
				ms.add(word);
			}
		}
		br.close();
		
		List<String> elements = ms.elements();
		
		//Tri de la liste
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
		
		int max = 0;
		if(elements.size()>=10) {
			max = 10;
		}
		else {
			max = elements.size();
		}
		
	}
}

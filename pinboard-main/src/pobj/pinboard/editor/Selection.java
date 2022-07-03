package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;

public class Selection {

	
	private List<Clip> listC = new ArrayList<Clip>();
	
	public void select(Board board, double x, double y) {
		listC = new ArrayList<Clip>();
		List<Clip> bdC = board.getContents();
		for(Clip c : bdC) {
			if(c.isSelected(x, y)) {
				listC.add(c);
			}
		}
	}
	
	public void toogleSelect(Board board, double x, double y) {
		List<Clip> bdC = board.getContents();
		for(Clip c : bdC) {
			if(c.isSelected(x, y)) {
				if(!listC.contains(c)) {
					listC.add(c);
				}
				else {
					listC.remove(c);
				}
			}
		}
	}
	
	public void clear() {
		listC = new ArrayList<Clip>();
	}
	
	public List<Clip> getContents(){
		return listC;
	}
	
	public void drawFeedback(GraphicsContext gc) {
		for(Clip c : listC) {
			c.draw(gc);
		}
	}
	
}

package pobj.pinboard.editor.tools;

import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class ToolSelection implements Tool {
	
	private double moveX;
	private double moveY;

	@Override
	public void press(EditorInterface i, MouseEvent e) throws FileNotFoundException {
		moveX = e.getX();
		moveY = e.getY();
		double x = e.getX();
		double y = e.getY();
		if(!e.isShiftDown()) {
			i.getSelection().select(i.getBoard(), x, y);
		} else {
			i.getSelection().toogleSelect(i.getBoard(), x, y);
		}
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		double nX = e.getX()-moveX;
		double nY = e.getY()-moveY;
		for(Clip c : i.getSelection().getContents()) {
			c.move(nX, nY);
		}
		moveX = e.getX();
		moveY = e.getY();
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		i.getSelection().drawFeedback(gc);
	}

	@Override
	public String getName(EditorInterface editor) {
		return "Selection";
	}

}

package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;

public class ToolRect implements Tool {
	
	private ClipRect clipR;
	private double left;
	private double top;
	

	@Override
	public void press(EditorInterface i, MouseEvent e) {
		this.left = e.getX();
		this.top = e.getY();
		this.clipR = new ClipRect(e.getX(), e.getY(), e.getX(), e.getY(), i.getToolColor());
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		if(e.getX() > left && e.getY() > top) {
			clipR.setGeometry(left, top, e.getX(), e.getY());
		}
		if(e.getX() < left && e.getY() > top) {
			clipR.setGeometry(e.getX(), top, left, e.getY());
		}
		if(e.getX() > left && e.getY() < top) {
			clipR.setGeometry(left, e.getY(), e.getX(), top);
		}
		if(e.getX() < left && e.getY() < top) {
			clipR.setGeometry(e.getX(), e.getY(), left, top);
		}
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		i.getBoard().addClip(clipR);
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		this.clipR.draw(gc);
	}

	@Override
	public String getName(EditorInterface editor) {
		return "ToolRect";
	}

}

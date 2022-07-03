package pobj.pinboard.editor.tools;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.ClipImage;
import pobj.pinboard.editor.EditorInterface;

public class ToolImage implements Tool {
	
	private ClipImage clipI;
	private double left;
	private double top;
	private File filename;
	
	public ToolImage(File filename) {
		this.filename = filename;
	}

	@Override
	public void press(EditorInterface i, MouseEvent e) throws FileNotFoundException {
		this.left = e.getX();
		this.top = e.getY();
		this.clipI = new ClipImage(e.getX(), e.getY(), filename);

	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		if(e.getX() > left && e.getY() > top) {
			clipI.setGeometry(left, top, e.getX(), e.getY());
		}
		if(e.getX() < left && e.getY() > top) {
			clipI.setGeometry(left, top, left, e.getY());
		}
		if(e.getX() > left && e.getY() < top) {
			clipI.setGeometry(left, top, e.getX(), top);
		}
		if(e.getX() < left && e.getY() < top) {
			clipI.setGeometry(left, top, left, top);
		}

	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		i.getBoard().addClip(clipI);

	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		this.clipI.draw(gc);
	}

	@Override
	public String getName(EditorInterface editor) {
		return "ToolImage";
	}

}

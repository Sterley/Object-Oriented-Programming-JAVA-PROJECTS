package pobj.pinboard.document;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;



public class ClipImage extends AbstractClip {
	
	private Image img;
	private File filename;
	
	public ClipImage(double left, double top, File filename) throws FileNotFoundException {
		
		super(left, top, (new Image(new FileInputStream(filename.getAbsolutePath()))).getWidth(), 
				(new Image(new FileInputStream(filename.getAbsolutePath()))).getHeight(), Color.BLACK);
		
		this.filename = filename;
		img = new Image(new FileInputStream(filename.getAbsolutePath()));
		
	}
	
	@Override
	public void draw(GraphicsContext ctx) {
		ctx.drawImage(img, super.getLeft(), super.getTop(), super.getRight()-super.getLeft(), super.getBottom()-super.getTop());
	}

	@Override
	public Clip copy() throws FileNotFoundException {
		return new ClipImage(super.getLeft(), super.getTop(), filename);
	}
	
}



package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board implements Clip {
	
	
	private List<Clip> contents = new ArrayList<>();
	
	public Board() {
		
	}
	
	public void removeClip(List<Clip> clip) {
		for(Clip c : clip) {
			if(this.contents.contains(c)) this.contents.remove(c);
		}
	}
	
	public void removeClip(Clip clip) {
		if(this.contents.contains(clip)) this.contents.remove(clip);
	}
	
	public void clear() {
		contents = new ArrayList<>();
	}

	public List<Clip> getContents(){
		return this.contents;
	}
	
	public void addClip(Clip clip) {
		this.contents.add(clip);
	}
	
	public void addClip(List<Clip> clip) {
		for(Clip c : clip) {
			this.contents.add(c);
		}
	}

	@Override
	public void draw(GraphicsContext ctx) {
		
		ClipRect rectB = new ClipRect(0, 0, ctx.getCanvas().getWidth(), ctx.getCanvas().getHeight(), Color.WHITE);
		rectB.draw(ctx);
		
		for(Clip c : this.contents) {
			c.draw(ctx);
		}
		
	}

	@Override
	public double getTop() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getLeft() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getBottom() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(double x, double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSelected(double x, double y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Clip copy() {
		// TODO Auto-generated method stub
		return null;
	}

}

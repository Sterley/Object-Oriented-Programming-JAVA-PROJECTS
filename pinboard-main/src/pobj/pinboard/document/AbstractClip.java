package pobj.pinboard.document;

import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class AbstractClip implements Clip {

	private double left;
	private double top;
	private double right;
	private double bottom;
	private Color color;
	
	public AbstractClip(double left, double top, double right, double bottom, Color color) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		this.color = color;
	}
	
	@Override
	public double getTop() {
		return this.top;
	}

	@Override
	public double getLeft() {
		return this.left;
	}

	@Override
	public double getBottom() {
		return this.bottom;
	}

	@Override
	public double getRight() {
		return this.right;
	}
	

	public void setTop(double top) {
		this.top = top;
	}


	public void setLeft(double left) {
		this.left = left;
	}


	public void setBottom(double bottom) {
		this.bottom = bottom;
	}


	public void setRight(double right) {
		this.right = right;
	}

	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
		
	}

	@Override
	public void move(double x, double y) {
		this.left = this.left + x;
		this.right = this.right + x;
		this.top = top + y;
		this.bottom = bottom + y;
	}

	@Override
	public boolean isSelected(double x, double y) {
		if(x > this.left && x < this.right && y > this.top && y < this.bottom) {
			return true;
		}
		return false;
	}

	@Override
	public void setColor(Color c) {
		this.color = c;
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public void draw(GraphicsContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Clip copy() throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}

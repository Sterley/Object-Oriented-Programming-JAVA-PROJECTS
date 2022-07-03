package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipEllipse extends AbstractClip{
	
	public ClipEllipse(double left, double top, double right, double bottom, Color color) {
		super(left, top, right, bottom, color);
	}

	@Override
	public void draw(GraphicsContext ctx) {
		ctx.setFill(super.getColor());
		ctx.fillOval(super.getLeft(),super.getTop(), super.getRight()-super.getLeft(), super.getBottom()-super.getTop());
	}

	@Override
	public boolean isSelected(double x, double y) {
		double cx = (super.getLeft() + super.getRight())/2;
		double cy = (super.getTop() + super.getBottom())/2;
		double rx = (super.getRight() - super.getLeft())/2;
		double ry = (super.getBottom() - super.getTop())/2;
		
		double p1 = (x - cx)/rx;
		p1 = p1 * p1;
		double p2 = (y - cy)/ry;
		p2 = p2 * p2;
		
		if(p1 + p2 <= 1) {
			return true;
		}
		
		return false;
	}

	@Override
	public Clip copy() {
		return new ClipEllipse(super.getLeft(), super.getTop(), super.getRight(), super.getBottom(), super.getColor());
	}
	
}

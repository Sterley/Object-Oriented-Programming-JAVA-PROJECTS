package pobj.pinboard.document;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipGroup extends AbstractClip implements Composite {
	
	private ArrayList<Clip> groupe = new ArrayList<>();

	public ClipGroup() {
		super(0, 0, 0, 0, Color.BLUE);
	}

	@Override
	public List<Clip> getClips() {
		return this.groupe;
	}

	@Override
	public void addClip(Clip toAdd) {
		this.groupe.add(toAdd);
		setGeo();
	}

	@Override
	public void removeClip(Clip toRemove) {
		if(this.groupe.size() == 1) {
			this.groupe.clear();
		}
		if(this.groupe.contains(toRemove)) {
			this.groupe.remove(toRemove);
			setGeo();
		}
	}
	
	public void setGeo() {
		double left = this.groupe.get(0).getLeft();
		double top = this.groupe.get(0).getTop();
		double right = this.groupe.get(0).getRight();
		double bottom = this.groupe.get(0).getBottom();
		for(Clip c : this.groupe) {
			if(c.getLeft() < left) {
				left = c.getLeft();
			}
			if(c.getRight() > right) {
				right = c.getRight();
			}
			if(c.getTop() < top) {
				top = c.getTop();
			}
			if(c.getBottom() > bottom) {
				bottom = c.getBottom();
			}
		}
		
		super.setLeft(left);
		super.setRight(right);
		super.setTop(top);
		super.setBottom(bottom);
	}

	@Override
	public void setGeometry(double left, double top, double right, double bottom) {
		setGeo();
	}

	@Override
	public void move(double x, double y) {
		for(Clip c : this.groupe) {
			c.move(x, y);
		}
		setGeo();
	}


	@Override
	public void draw(GraphicsContext ctx) {
		for(Clip c : this.groupe) {
			c.draw(ctx);
		}
	}

	@Override
	public Clip copy() throws FileNotFoundException {
		ClipGroup cp = new ClipGroup();
		for(Clip c : this.groupe) {
			cp.addClip(c.copy());
		}
		return cp;
	}

}

package pobj.pinboard.editor;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;

public class Clipboard {
	
	private List<Clip> clpc = new ArrayList<Clip>();
	
	private static Clipboard clb = new Clipboard();
	
	private List<ClipboardListener> lstn = new ArrayList<ClipboardListener>();
	
	private Clipboard() {
		
	}

	public void copyToClipboard(List<Clip> clips) throws FileNotFoundException {
		for(Clip c : clips) {
			this.clpc.add(c.copy());
		}
		updateListener();
	}
	
	public List<Clip> copyFromClipboard() throws FileNotFoundException {
		List<Clip> ret = new ArrayList<Clip>();
		for(Clip c : this.clpc) {
			ret.add(c.copy());
		}
		return ret;
	}
	
	public void clear() {
		this.clpc = new ArrayList<Clip>();
		updateListener();
	}
	
	public boolean isEmpty() {
		if(clpc.size() == 0) {
			return true;
		}
		return false;
	}
	
	public static Clipboard getInstance() {
		return clb;
	}
	
	public void addListener(ClipboardListener listener) {
		lstn.add(listener);
	}
	
	public void removeListener(ClipboardListener listener) {
		lstn.remove(listener);
	}
	
	private void updateListener() {
		for(ClipboardListener l : lstn) {
			l.clipboardChanged();
		}
	}
	
}

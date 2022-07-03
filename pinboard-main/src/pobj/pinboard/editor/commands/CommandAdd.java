package pobj.pinboard.editor.commands;

import java.util.List;

import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandAdd implements Command { 
	
	private EditorInterface editor;
	private Clip toAdd;
	private List<Clip> toAddList;
	private boolean multi = false;
	private Board UnBoard;
	
	public CommandAdd(EditorInterface editor, Clip toAdd) {
		this.editor = editor;
		this.toAdd = toAdd;
		this.UnBoard = editor.getBoard();
	}
	
	public CommandAdd(EditorInterface editor, List<Clip> toAdd) {
		this.editor = editor;
		this.toAddList = toAdd;
		multi = true;
		this.UnBoard = editor.getBoard();
	}
	
	@Override public void execute() {
		if(multi) {
			editor.getBoard().addClip(toAddList);
		}else {
			editor.getBoard().addClip(toAdd);
		}
		
	}
	
	@Override public void undo() {
		this.editor.setBoard(UnBoard);
	}
} 

package pobj.pinboard.editor;


import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolImage;
import pobj.pinboard.editor.tools.ToolRect;
import pobj.pinboard.editor.tools.ToolSelection;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class EditorWindow implements EditorInterface, ClipboardListener {
	
	private Board bd;
	private final Canvas canvas;
	private File file;
	
	private Selection slt = new Selection();
	
	private boolean rect = true;
	private boolean ell = false;
	private boolean img = false;
	private boolean slti = false;
	private Tool tool;
	
	MenuItem menuItem4;
	
	private Color ToolColor = Color.RED;
	
	public EditorWindow(Stage stage) {
		
		Clipboard.getInstance().addListener(getThis());
		
		Menu menu1 = new Menu("File");
		MenuItem menuItem1 = new MenuItem("New");
		MenuItem menuItem2 = new MenuItem("Close");
		menu1.getItems().add(menuItem1);
		menu1.getItems().add(menuItem2);
		
		menuItem1.setOnAction( 
				new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) { 
					new EditorWindow(new Stage()); 
				} 
			}); 

		menuItem2.setOnAction( 
				new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) { 
					stage.close(); 
				} 
			}); 
		
		Menu menu2 = new Menu("Edit");
		MenuItem menuItem3 = new MenuItem("Copy");
		menuItem4 = new MenuItem("Paste");
		MenuItem menuItem5 = new MenuItem("Delete");
		MenuItem menuItem6 = new MenuItem("Group");
		MenuItem menuItem7 = new MenuItem("UnGroup");
		menu2.getItems().add(menuItem3);
		menu2.getItems().add(menuItem4);
		menu2.getItems().add(menuItem5);
		menu2.getItems().add(menuItem6);
		menu2.getItems().add(menuItem7);
		
		clipboardChanged();
		
		menuItem3.setOnAction( 
				new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) { 
					try {
						Clipboard.getInstance().copyToClipboard(slt.getContents());
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					draw();
				} 
			}); 

		menuItem4.setOnAction( 
				new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) { 
					try {
						bd.addClip(Clipboard.getInstance().copyFromClipboard());
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					draw();
				} 
			}); 
		
		menuItem5.setOnAction( 
				new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) { 
					bd.clear();
					bd.draw(canvas.getGraphicsContext2D());
				} 
			}); 
		
		menuItem6.setOnAction( 
				new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) {
					ClipGroup tmp = new ClipGroup();
					List<Clip> lst = slt.getContents();
					for(Clip c : lst) {
						tmp.addClip(c);
					}
					bd.addClip(tmp);
					bd.draw(canvas.getGraphicsContext2D());
				} 
			}); 
		
		menuItem7.setOnAction( 
				new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) { 
					// Ungroup
				} 
			}); 
		
		Menu menu3 = new Menu("Tools");
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(menu1, menu2, menu3);
		
		Button button1 = new Button("Box");
		
		button1.setOnAction( 
				new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) { 
					rect = true;
					ell = false;
					img = false;
					slti = false;
				} 
			}); 
		
		Button button2 = new Button("Ellipse");
		
		button2.setOnAction( 
				new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) { 
					ell = true;
					rect = false;
					img = false;
					slti = false;
				} 
			}); 
		
		Button button3 = new Button("Img...");
		
		button3.setOnAction( 
			new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) { 
			
					JFileChooser select = new JFileChooser();
					select.setFileSelectionMode(JFileChooser.FILES_ONLY);
					select.setMultiSelectionEnabled(false);
					int res = select.showDialog(new JFrame(""), "Ouvrir");
				
					if(res == JFileChooser.APPROVE_OPTION) {
							
						file = select.getSelectedFile();
						ell = false;
						rect = false;
						img = true;
						slti = false;
					} 
					
				} 
			}
		); 
		
		Button button4 = new Button("Select");
		
		button4.setOnAction( 
				new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) { 
					ell = false;
					rect = false;
					img = false;
					slti = true;
				} 
			}); 
		
		ToolBar toolBar = new ToolBar();
		toolBar.getItems().addAll(button1, button2, button3, button4);
		
		
		Button button5 = new Button("      ");
		button5.setStyle("-fx-background-color: #f00020");
		
		button5.setOnAction( 
				new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) { 
					ToolColor = Color.RED;
				} 
			}); 
		
		Button button6 = new Button("      ");
		button6.setStyle("-fx-background-color: #2F3ECA");
		
		button6.setOnAction( 
				new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) { 
					ToolColor = Color.BLUE;
				} 
			}); 
		
		Button button7 = new Button("      ");
		button7.setStyle("-fx-background-color: #000000");
		
		button7.setOnAction( 
				new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) { 
					ToolColor = Color.BLACK;
				} 
			}); 
		
		Button button8 = new Button("      ");
		button8.setStyle("-fx-background-color: #FFFF00");
		
		button8.setOnAction( 
				new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) { 
					ToolColor = Color.YELLOW;
				} 
			}); 
		
		Button button9 = new Button("      ");
		button9.setStyle("-fx-background-color: #008000");
		
		button9.setOnAction( 
				new EventHandler<ActionEvent>() { 
				public void handle(ActionEvent e) { 
					ToolColor = Color.GREEN;
				} 
			}); 
		
		ToolBar toolBar2 = new ToolBar();
		toolBar2.getItems().addAll(button5, button6, button7, button8, button9);
		

		this.canvas = new Canvas(640,700);
		
		Label label = new Label("Filled rectangle tool");
		
		this.canvas.setOnMousePressed(
			new EventHandler<MouseEvent>(){
				public void handle(MouseEvent e) { 
					if(rect) {
						tool = new ToolRect();
					}
					if(ell) {
						tool = new ToolEllipse();
					}
					if(img) {
						tool = new ToolImage(file);
					}
					if(slti) {
						tool = new ToolSelection();
					}
					try {
						tool.press(getThis(), e);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					label.setText(tool.getName(getThis()));
					draw();
				} 
			});
		
		this.canvas.setOnMouseDragged(
				new EventHandler<MouseEvent>(){
					public void handle(MouseEvent e) { 
						tool.drag(getThis(), e);
						draw();
					} 
			});
		
		this.canvas.setOnMouseReleased(
				new EventHandler<MouseEvent>(){
					public void handle(MouseEvent e) { 
						tool.release(getThis(), e);
						draw();
					} 
			});
		
		this.bd = new Board();
		bd.draw(this.canvas.getGraphicsContext2D());
		
		Separator separator1 = new Separator();
		
		VBox layout = new VBox();
		layout.getChildren().addAll(menuBar, toolBar, toolBar2, this.canvas, separator1, label);
		
		Scene scene = new Scene(layout, 640, 800);
		stage.setScene(scene);
		stage.setTitle("PinBoard");
		stage.show();
	}
	
	private EditorWindow getThis() {
		return this;
	}
	
	private void draw() {
		bd.draw(canvas.getGraphicsContext2D());
		tool.drawFeedback(getThis(), canvas.getGraphicsContext2D());
	}
	

	@Override
	public Board getBoard() {
		return this.bd;
	}

	@Override
	public Selection getSelection() {
		return this.slt;
	}

	@Override
	public CommandStack getUndoStack() {
		return null;
	}

	@Override
	public void clipboardChanged() {
		if(Clipboard.getInstance().isEmpty()) {
			menuItem4.setDisable(true);
		}
		else {
			menuItem4.setDisable(false);
		}
	}

	@Override
	public Color getToolColor() {
		return this.ToolColor;
	}

	@Override
	public void setBoard(Board bd) {
		this.bd = bd;
	}
	
}

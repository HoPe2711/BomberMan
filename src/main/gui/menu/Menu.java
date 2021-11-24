package main.gui.menu;

import main.gui.Frame;
import javax.swing.JMenuBar;

public class Menu extends JMenuBar {
	
	public Menu(Frame frame) {
		add( new Game(frame) );
		add( new Options(frame) );
		add( new Help(frame) );
	}
	
}

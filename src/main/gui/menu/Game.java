package main.gui.menu;

import main.gui.CodeDialog;
import main.gui.Frame;
import main.gui.GameSound;
import main.gui.InfoDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import main.gui.MenuUI;

public class Game extends JMenu {

	public Frame frame;
	
	public Game(Frame frame) {
		super("Game");
		this.frame = frame;
		
		/*
		 * New Game
		 */
		JMenuItem newgame = new JMenuItem("New Game");
		newgame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newgame.addActionListener(new MenuActionListener(frame));
		add(newgame);
		
		/*
		 * Scores
		 */
		JMenuItem scores = new JMenuItem("Back to menu");
		scores.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
		scores.addActionListener(new MenuActionListener(frame));
		add(scores);
		
		/*
		 * Codes
		 */
		JMenuItem codes = new JMenuItem("Choose level");
		codes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		codes.addActionListener(new MenuActionListener(frame));
		add(codes);
	}
	
	class MenuActionListener implements ActionListener {
		public Frame _frame;
		public MenuActionListener(Frame frame) {
			_frame = frame;
		}
		
		  @Override
		public void actionPerformed(ActionEvent e) {
			  
			  if(e.getActionCommand().equals("New Game")) {
				  _frame.newGame();
			  }
			  
			  if(e.getActionCommand().equals("Back to menu")) {
					_frame.pauseGame();
					MenuUI menu = _frame.getMenuUI();
					GameSound.getInstance().stop();
					menu.getCardLayout().show(menu.getMenuContainer(), "MENU_PANEL");
					GameSound.getInstance().play(GameSound.BACKGROUND);
			  }
			  
			  if(e.getActionCommand().equals("Choose level")) {
				  new CodeDialog(_frame);
			  }

		  }
		}

}

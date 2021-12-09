package main.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import main.gui.CodeDialog;
import main.gui.Frame;
import main.gui.GameSound;
import main.gui.MenuUI;

public class Game extends JMenu {

  public Frame frame;

  public Game(Frame frame) {
    super("Game");
    this.frame = frame;

    JMenuItem newgame = new JMenuItem("New Game");
    newgame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
    newgame.addActionListener(new MenuActionListener(frame));
    add(newgame);

    JMenuItem scores = new JMenuItem("Back to menu");
    scores.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
    scores.addActionListener(new MenuActionListener(frame));
    add(scores);

    JMenuItem codes = new JMenuItem("Choose level");
    codes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
    codes.addActionListener(new MenuActionListener(frame));
    add(codes);
  }

  static class MenuActionListener implements ActionListener {

    public Frame _frame;

    public MenuActionListener(Frame frame) {
      _frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

      if (e.getActionCommand().equals("New Game")) {
        _frame.newGame();
      }

      if (e.getActionCommand().equals("Back to menu")) {
        _frame.pauseGame();
        GameSound.getInstance().stop();
        MenuUI menu = _frame.getMenuUI();
        menu.getCardLayout().show(menu.getMenuContainer(), "MENU_PANEL");
      }

      if (e.getActionCommand().equals("Choose level")) {
        new CodeDialog(_frame);
      }

    }
  }

}

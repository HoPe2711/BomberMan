package main.gui;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import main.Game;
import main.gui.GamePanel;
import main.gui.MenuUI;

public class MouseProcess extends MouseAdapter {

  private MenuUI menu;
  private GamePanel gamePanel;
  private Game game;

  public MouseProcess(MenuUI menu1) {
    menu = menu1;
    gamePanel = menu.getGamePanel();
    game = gamePanel.getGame();
  }

  public void mouseEntered(MouseEvent e) {
    if (e.getSource() == menu.getPlayLabel()) {
      Robot robot = null;
      try {
        robot = new Robot();
      } catch (AWTException ex) {
        ex.printStackTrace();
      }

      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_N);
      robot.keyRelease(KeyEvent.VK_CONTROL);
      robot.keyRelease(KeyEvent.VK_N);
    }
  }

  public void mousePressed(MouseEvent e) {
    if (e.getSource() == menu.getPlayLabel()) {
      menu.getMenuPane().setVisible(false);
      menu.getCardLayout().show(menu.getMenuContainer(), "GAME_PANEL");
    }

    if(e.getSource() == menu.getContinueLabel()) {
      menu.getMenuPane().setVisible(false);
      menu.getCardLayout().show(menu.getMenuContainer(), "GAME_PANEL");
    }

    if(e.getSource() == menu.getHowtoplay_mobsLabel()) {
      menu.getCardLayout().show(menu.getMenuContainer(), "HTP_MOBS");
    }

    if(e.getSource() == menu.getHowtoplay_mobsPane().getNextButton())  {
      menu.getCardLayout().show(menu.getMenuContainer(), "HTP_OBJECTS");
    }

    if(e.getSource() == menu.getHowtoplay_mobsPane().getBackButton()) {
      menu.getCardLayout().show(menu.getMenuContainer(), "MENU_PANEL");
    }

    if(e.getSource() == menu.getHowtoplay_objectsPane().getBackButton()) {
      menu.getCardLayout().show(menu.getMenuContainer(), "HTP_MOBS");
    }

    if (e.getSource() == menu.getExitLabel()) {
      int n = JOptionPane.showConfirmDialog(menu.getMenuContainer(), "BAN CÓ MUỐN THOÁT?", "Exit",
          JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
      if (n == 0) {
        System.exit(0);
      }
    }
  }

}

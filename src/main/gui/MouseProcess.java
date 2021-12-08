package main.gui;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;

public class MouseProcess extends MouseAdapter {

  private final MenuUI menu;

  public MouseProcess(MenuUI menu1) {
    menu = menu1;
  }

  public void mouseEntered(MouseEvent e) {
    if (e.getSource() == menu.getPlayLabel()) {
      Robot robot = null;
      try {
        robot = new Robot();
      } catch (AWTException ex) {
        ex.printStackTrace();
      }

      assert robot != null;
      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_N);
      robot.keyRelease(KeyEvent.VK_CONTROL);
      robot.keyRelease(KeyEvent.VK_N);
    }
  }

  public void mousePressed(MouseEvent e) {
    if (e.getSource() == menu.getPlayLabel()) {
      menu.getMenuPane().setVisible(false);
      GameSound.getInstance().play(GameSound.CLICK);
      menu.getCardLayout().show(menu.getMenuContainer(), "GAME_PANEL");
    }

    if (e.getSource() == menu.getContinueLabel()) {
      menu.getMenuPane().setVisible(false);
      GameSound.getInstance().play(GameSound.CLICK);
      menu.getCardLayout().show(menu.getMenuContainer(), "GAME_PANEL");
    }

    if (e.getSource() == menu.getHighscoreLabel()) {
      GameSound.getInstance().play(GameSound.CLICK);
      menu.getHighscorePanel().updateScore(menu.get_frame().getHighscore().getUserHighscore());
      menu.getCardLayout().show(menu.getMenuContainer(), "HS_PANEL");
    }

    if (e.getSource() == menu.getHowtoplay_mobsLabel()) {
      GameSound.getInstance().play(GameSound.CLICK);
      menu.getCardLayout().show(menu.getMenuContainer(), "HTP_MOBS");
    }

    if (e.getSource() == menu.getHowtoplay_mobsPane().getNextButton()) {
      GameSound.getInstance().play(GameSound.CLICK);
      menu.getCardLayout().show(menu.getMenuContainer(), "HTP_OBJECTS");
    }

    if (e.getSource() == menu.getHowtoplay_mobsPane().getBackButton()) {
      GameSound.getInstance().play(GameSound.CLICK);
      menu.getCardLayout().show(menu.getMenuContainer(), "MENU_PANEL");
    }

    if (e.getSource() == menu.getHighscorePanel().getBackButton()) {
      GameSound.getInstance().play(GameSound.CLICK);
      System.out.println(1);
      menu.getCardLayout().show(menu.getMenuContainer(), "MENU_PANEL");
    }

    if (e.getSource() == menu.getHowtoplay_objectsPane().getBackButton()) {
      GameSound.getInstance().play(GameSound.CLICK);
      menu.getCardLayout().show(menu.getMenuContainer(), "HTP_MOBS");
    }

    if (e.getSource() == menu.getExitLabel()) {
      GameSound.getInstance().play(GameSound.CLICK);
      int n = JOptionPane.showConfirmDialog(menu.getMenuContainer(), "BAN CÓ MUỐN THOÁT?", "Exit",
          JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
      if (n == 0) {
        System.exit(0);
      }
    }
  }

}

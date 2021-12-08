package main.gui;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TreeMap;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class MouseProcess extends MouseAdapter {

  private final MenuUI menu;

  public MouseProcess(MenuUI menu1) {
    menu = menu1;
  }

  public void mouseEntered(MouseEvent e) {
    if (e.getSource() == menu.getPlayLabel()) {
      ImageIcon iconPlay = new ImageIcon("res/textures/Play_ButtonDark.png");
      menu.getPlayLabel().setIcon(iconPlay);

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

    if(e.getSource() == menu.getContinueLabel()) {
      ImageIcon iconContinue = new ImageIcon("res/textures/Continue_ButtonDark.png");
      menu.getContinueLabel().setIcon(iconContinue);
    }

    if(e.getSource() == menu.getHighscoreLabel()) {
      ImageIcon highscoreIcon = new ImageIcon("res/textures/Highscore_ButtonDark.png");
      menu.getHighscoreLabel().setIcon(highscoreIcon);
    }

    if(e.getSource() == menu.getHowtoplay_mobsLabel()) {
      ImageIcon howtoplayIcon = new ImageIcon("res/textures/Howtoplay_ButtonDark.png");
      menu.getHowtoplay_mobsLabel().setIcon(howtoplayIcon);
    }

    if(e.getSource() == menu.getExitLabel()) {
      ImageIcon exitIcon = new ImageIcon("res/textures/Exit_ButtonDark.png");
      menu.getExitLabel().setIcon(exitIcon);
    }

    if (e.getSource() == menu.getHowtoplay_mobsPane().getNextButton()) {
      ImageIcon nextIcon = new ImageIcon("res/textures/Next_ButtonDark.png");
      menu.getHowtoplay_mobsPane().getNextButton().setIcon(nextIcon);
    }

    if(e.getSource() == menu.getHowtoplay_mobsPane().getBackButton()) {
      ImageIcon backIcon = new ImageIcon("res/textures/Back_ButtonDark.png");
      menu.getHowtoplay_mobsPane().getBackButton().setIcon(backIcon);
    }

    if(e.getSource() == menu.getHighscorePanel().getBackButton()) {
      ImageIcon backIcon = new ImageIcon("res/textures/Back_ButtonDark.png");
      menu.getHighscorePanel().getBackButton().setIcon(backIcon);
    }
  }

  public void mouseExited(MouseEvent e) {
    if (e.getSource() == menu.getPlayLabel()) {
      ImageIcon iconPlay = new ImageIcon("res/textures/Play_Button.png");
      menu.getPlayLabel().setIcon(iconPlay);
    }

    if(e.getSource() == menu.getContinueLabel()) {
      ImageIcon continueIcon = new ImageIcon("res/textures/Continue_Button.png");
      menu.getContinueLabel().setIcon(continueIcon);
    }

    if(e.getSource() == menu.getHighscoreLabel()) {
      ImageIcon highscoreIcon = new ImageIcon("res/textures/Highscore_Button.png");
      menu.getHighscoreLabel().setIcon(highscoreIcon);
    }

    if(e.getSource() == menu.getHowtoplay_mobsLabel()) {
      ImageIcon howtoplayIcon = new ImageIcon("res/textures/Howtoplay_Button.png");
      menu.getHowtoplay_mobsLabel().setIcon(howtoplayIcon);
    }

    if(e.getSource() == menu.getExitLabel()) {
      ImageIcon exitIcon = new ImageIcon("res/textures/Exit_Button.png");
      menu.getExitLabel().setIcon(exitIcon);
    }

    if (e.getSource() == menu.getHowtoplay_mobsPane().getNextButton()) {
      ImageIcon nextIcon = new ImageIcon("res/textures/Next_Button.png");
      menu.getHowtoplay_mobsPane().getNextButton().setIcon(nextIcon);
    }

    if(e.getSource() == menu.getHowtoplay_mobsPane().getBackButton()) {
      ImageIcon backIcon = new ImageIcon("res/textures/Back_Button.png");
      menu.getHowtoplay_mobsPane().getBackButton().setIcon(backIcon);
    }

    if(e.getSource() == menu.getHighscorePanel().getBackButton()) {
      ImageIcon backIcon = new ImageIcon("res/textures/Back_Button.png");
      menu.getHighscorePanel().getBackButton().setIcon(backIcon);
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
      menu.getHighscorePanel().updateScore(menu.get_frame().getHighscore().getHighscoreList());
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

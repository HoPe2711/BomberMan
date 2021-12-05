package main.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import main.Game;
import main.gui.GamePanel;
import main.gui.MenuUI;

public class MouseProcess extends MouseAdapter {
  private MenuUI menu;
  private GamePanel gamePanel;
  private Game game;
  public static boolean running = false;

  public MouseProcess(MenuUI menu1) {
    menu = menu1;
    gamePanel = menu.getGamePanel();
    game = gamePanel.getGame();
  }

  public void mousePressed(MouseEvent e) {
    if(e.getSource() == menu.getPlayLabel()) {
      menu.getCardLayout().show(menu.getMenuContainer(), "GAME_PANEL");
      running = true;
//      game.start();
    }
  }

  public static boolean isRunning() {
    return running;
  }
}

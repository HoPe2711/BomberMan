package main.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import main.Game;
import main.gui.menu.Menu;

public class Frame extends JFrame {

  public GamePanel _gamepane;
  private final InfoPanel _infopanel;
  private final Game _game;
  private final Highscore highscore;

  private final MenuUI menuUI;

  public Frame() {
    setJMenuBar(new Menu(this));

    highscore = new Highscore();
    menuUI = new MenuUI(this);

    _gamepane = menuUI.getGamePanel();
    _infopanel = menuUI.getInfoPanel();
    _game = _gamepane.getGame();

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        highscore.savePoint();
        System.exit(0);
      }
    });

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setResizable(false);
    setLocationRelativeTo(null);
    setVisible(true);

    _game.start();
  }

  public void newGame() {
    _game.getBoard().newGame();
  }

  public void pauseGame() {
    _game.getBoard().gamePause();
  }

  public void resumeGame() {
    _game.getBoard().gameResume();
  }

  public void setTime(int time) {
    _infopanel.setTime(time);
  }

  public void setLives(int lives) {
    _infopanel.setLives(lives);
  }

  public void setPoints(int points) {
    _infopanel.setPoints(points);
  }

  public boolean validCode(String str) {
    return _game.getBoard().getLevel().validCode(str) != -1;
  }

  public void changeLevelByCode(String str) {
    _game.getBoard().changeLevelByCode(str);
  }

  public MenuUI getMenuUI() {

    return menuUI;
  }

  public Highscore getHighscore() {
    return highscore;
  }
}

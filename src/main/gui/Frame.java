package main.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import main.Game;

import javax.swing.JFrame;
import javax.swing.JPanel;
import main.gui.menu.Menu;

public class Frame extends JFrame {

  public GamePanel _gamepane;
  private JPanel _containerpane;
  private InfoPanel _infopanel;
  private Game _game;
  private Highscore highscore;

  private MenuUI menuUI;

  public Frame() {
    setJMenuBar(new Menu(this));

    highscore = new Highscore();
    menuUI = new MenuUI(this);

    _gamepane = menuUI.getGamePanel();
    _infopanel = menuUI.getInfoPanel();
    _game = _gamepane.getGame();
//		add(_containerpane);

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        highscore.savePoint();
        System.exit(0);
      }
    });

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
//		setSize(720, 664);
    setResizable(false);
    setLocationRelativeTo(null);
    setVisible(true);

    _game.start();
  }

  /*
  |--------------------------------------------------------------------------
  | Game Related
  |--------------------------------------------------------------------------
   */
  public void newGame() {
    _game.getBoard().newGame();
  }

  public void changeLevel(int i) {
    _game.getBoard().changeLevel(i);
  }

  public void pauseGame() {
    _game.getBoard().gamePause();
  }

  public void resumeGame() {
    _game.getBoard().gameResume();
  }

  public boolean isRunning() {
    return _game.isRunning();
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

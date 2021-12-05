package main.gui.menu;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import main.Game;
import main.gui.Frame;
import main.gui.GamePanel;
import main.gui.InfoPanel;

public class GameInfoPanel extends JPanel {
  public GamePanel _gamepane;
  private InfoPanel _infopanel;
  private Game _game;
  private JPanel _containerpane;

  public GameInfoPanel(Frame frame) {
    _containerpane = new JPanel(new BorderLayout());
    _gamepane = new GamePanel(frame);
    _infopanel = new InfoPanel(_gamepane.getGame());
    _containerpane.add(_infopanel, BorderLayout.PAGE_START);
    _containerpane.add(_gamepane, BorderLayout.PAGE_END);

    _game = _gamepane.getGame();
//    System.out.println("Frame -> Game");
//    _game.start();
  }

  public GamePanel get_gamepane() {
    return _gamepane;
  }

  public void set_gamepane(GamePanel _gamepane) {
    this._gamepane = _gamepane;
  }

  public JPanel get_containerpane() {
    return _containerpane;
  }

  public void set_containerpane(JPanel _containerpane) {
    this._containerpane = _containerpane;
  }

  public InfoPanel get_infopanel() {
    return _infopanel;
  }

  public void set_infopanel(InfoPanel _infopanel) {
    this._infopanel = _infopanel;
  }

  public Game get_game() {
    return _game;
  }

  public void set_game(Game _game) {
    this._game = _game;
  }
}

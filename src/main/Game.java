package main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;


import main.exceptions.BombermanException;
import main.graphics.Screen;
import main.gui.Frame;
import main.input.Keyboard;

public class Game extends Canvas {

  public static final int TILES_SIZE = 16,
      WIDTH = TILES_SIZE * (31 / 2),
      HEIGHT = 13 * TILES_SIZE;

  public static int SCALE = 4;

  public static final String TITLE = "Bomberman";

  private static final int BOMBRATE = 1;
  private static final int BOMBRADIUS = 1;
  private static final double PLAYERSPEED = 1.0;

  public static final int TIME = 200;
  public static final int POINTS = 0;
  public static final int LIVES = 3;

  protected static int SCREENDELAY = 3;


  protected static int bombRate = BOMBRATE;
  protected static int bomMax = bombRate;
  protected static int bombRadius = BOMBRADIUS;
  protected static double playerSpeed = PLAYERSPEED;


  protected int _screenDelay = SCREENDELAY;

  private final Keyboard _input;
  private boolean _running = false;
  private boolean _paused = true;

  private final Board _board;
  private final Screen screen;
  private final Frame _frame;

  private final BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
  private final int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

  public Game(Frame frame) throws BombermanException {
    _frame = frame;
    _frame.setTitle(TITLE);

    screen = new Screen(WIDTH, HEIGHT);
    _input = new Keyboard();

    _board = new Board(this, _input, screen);
    addKeyListener(_input);
  }


  private void renderGame() {
    BufferStrategy bs = getBufferStrategy();
    if (bs == null) {
      createBufferStrategy(3);
      return;
    }

    screen.clear();

    _board.render(screen);

    System.arraycopy(screen._pixels, 0, pixels, 0, pixels.length);

    Graphics g = bs.getDrawGraphics();

    g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    _board.renderMessages(g);

    g.dispose();
    bs.show();
  }

  private void renderScreen() {
    BufferStrategy bs = getBufferStrategy();
    if (bs == null) {
      createBufferStrategy(3);
      return;
    }

    screen.clear();

    Graphics g = bs.getDrawGraphics();

    _board.drawScreen(g);

    g.dispose();
    bs.show();
  }

  private void update() {
    _input.update();
    _board.update();
  }

  public void start() {
    _running = true;

    long lastTime = System.nanoTime();
    long timer = System.currentTimeMillis();
    final double ns = 1000000000.0 / 60.0;
    double delta = 0;
    requestFocus();
    while (_running) {
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      while (delta >= 1) {
        update();
        delta--;
      }

      if (_paused) {
        if (_screenDelay <= 0) {
          _board.setShow(-1);
          _paused = false;
        }

        renderScreen();
      } else {
        renderGame();
      }

      if (System.currentTimeMillis() - timer > 1000) {
        _frame.setTime(_board.subtractTime());
        _frame.setPoints(_board.getPoints());
        _frame.setLives(_board.getLives());
        timer += 1000;
        _frame.setTitle(TITLE);

        if (_board.getShow() == 2) {
          --_screenDelay;
        }
      }
    }
  }

  public Frame get_frame() {
    return _frame;
  }

  public static double getPlayerSpeed() {
    return playerSpeed;
  }

  public static int getBombRate() {
    return bombRate;
  }

  public static int getBombRadius() {
    return bombRadius;
  }

  public static void addPlayerSpeed(double i) {
    playerSpeed += i;
  }

  public static void addBombRadius(int i) {
    bombRadius += i;
  }

  public static void addBombRate(int i) {
    bombRate += i;
    bomMax = Math.max(bomMax, bombRate);
  }

  public void resetScreenDelay() {
    _screenDelay = SCREENDELAY;
  }

  public Board getBoard() {
    return _board;
  }

  public void run() {
    _running = true;
    _paused = false;
  }

  public boolean isPaused() {
    return _paused;
  }

  public void pause() {
    _paused = true;
  }

}

package main.entities.mob;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import main.Board;
import main.Game;
import main.entities.Entity;
import main.entities.Message;
import main.entities.bomb.Bomb;
import main.entities.bomb.DirectionalExplosion;
import main.entities.mob.enemy.Enemy;
import main.entities.tile.powerup.Powerup;
import main.graphics.Screen;
import main.graphics.Sprite;
import main.gui.GameSound;
import main.input.Keyboard;
import main.level.Coordinates;

public class Player extends Mob {

  private final List<Bomb> _bombs;
  protected Keyboard _input;

  protected int _timeBetweenPutBombs = 0;

  public static List<Powerup> _powerups = new ArrayList<>();


  public Player(int x, int y, Board board) {
    super(x, y, board);
    _bombs = _board.getBombs();
    _input = _board.getInput();
    _sprite = Sprite.player_right;
  }

  @Override
  public void update() {
    clearBombs();
    if (!_alive) {
      afterKill();
      return;
    }

    if (_timeBetweenPutBombs < -7500) {
      _timeBetweenPutBombs = 0;
    } else {
      _timeBetweenPutBombs--;
    }

    animate();

    calculateMove();

    detectPlaceBomb();
  }

  @Override
  public void render(Screen screen) {
    calculateXOffset();

    if (_alive) {
      chooseSprite();
    } else {
      _sprite = Sprite.player_dead1;
    }

    screen.renderEntity((int) _x, (int) _y - _sprite.SIZE, this);
  }

  public void calculateXOffset() {
    int xScroll = Screen.calculateXOffset(_board, this);
    Screen.setOffset(xScroll, 0);
  }

  private void detectPlaceBomb() {
    if (_input.space && Game.getBombRate() > 0 && _timeBetweenPutBombs < 0) {

      int xt = Coordinates.pixelToTile(_x + (double) _sprite.getSize() / 2);
      int yt = Coordinates.pixelToTile((_y + (double) _sprite.getSize() / 2)
          - _sprite.getSize()); //subtract half player height and minus 1 y position
      for (Bomb a : _bombs) {
        if (a.getX() == xt && a.getY() == yt) {
          return;
        }
      }
      placeBomb(xt, yt);
      Game.addBombRate(-1);

      _timeBetweenPutBombs = 30;
    }
  }

  protected void placeBomb(int x, int y) {
    Bomb b = new Bomb(x, y, _board);
    GameSound.getInstance().play(GameSound.BOMBPLACED);
    _board.addBomb(b);
  }

  private void clearBombs() {
    Iterator<Bomb> bs = _bombs.iterator();

    Bomb b;
    while (bs.hasNext()) {
      b = bs.next();
      if (b.isRemoved()) {
        bs.remove();
        Game.addBombRate(1);
      }
    }

  }

  @Override
  public void kill() {
    if (!_alive) {
      return;
    }

    _alive = false;

    _board.addLives(-1);

    Message msg = new Message("   -1 LIVE", getXMessage(), getYMessage(), 1, Color.white, 50);
    _board.addMessage(msg);

    GameSound.getInstance().play(GameSound.DEATH);
  }

  @Override
  protected void afterKill() {
    if (_timeAfter > 0) {
      --_timeAfter;
    } else {
      if (_bombs.size() == 0) {

        if (_board.getLives() == 0) {
          _board.endGame();
        } else {
          _board.restartLevel();
        }
      }
    }
  }

  @Override
  protected void calculateMove() {
    int xa = 0, ya = 0;
    if (_input.up) {
      ya--;
    }
    if (_input.down) {
      ya++;
    }
    if (_input.left) {
      xa--;
    }
    if (_input.right) {
      xa++;
    }

    if (xa != 0 || ya != 0) {
      GameSound.getInstance().play(GameSound.WALK);
      move(xa * Game.getPlayerSpeed(), ya * Game.getPlayerSpeed());
      _moving = true;
    } else {
      _moving = false;
    }

  }

  @Override
  public boolean canMove(double x, double y) {
    for (int c = 0; c < 4; c++) {
      double xt = ((_x + x) + c % 2 * 11)
          / Game.TILES_SIZE;
      double yt = ((_y + y) + c / 2 * 12 - 13)
          / Game.TILES_SIZE;

      Entity a = _board.getEntity(xt, yt, this);

      if (!a.collide(this)) {
        return false;
      }
    }

    return true;
  }

  @Override
  public void move(double xa, double ya) {

    if (xa > 0) {
      _direction = 1;
    }
    if (xa < 0) {
      _direction = 3;
    }
    if (ya > 0) {
      _direction = 2;
    }
    if (ya < 0) {
      _direction = 0;
    }

    if (canMove(0, ya)) {
      _y += ya;
    }

    if (canMove(xa, 0)) {
      _x += xa;
    }

  }

  @Override
  public boolean collide(Entity e) {
    if (e instanceof DirectionalExplosion) {
      kill();
      return false;
    }

    if (e instanceof Enemy) {
      kill();
      return true;
    }

    return true;
  }

  public void addPowerup(Powerup p) {
    if (p.isRemoved()) {
      return;
    }

    GameSound.getInstance().play(GameSound.GETITEM);

    _powerups.add(p);

    p.setValues();

  }

  private void chooseSprite() {
    switch (_direction) {
      case 0 -> {
        _sprite = Sprite.player_up;
        if (_moving) {
          _sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, _animate, 20);
        }
      }
      case 2 -> {
        _sprite = Sprite.player_down;
        if (_moving) {
          _sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, _animate, 20);
        }
      }
      case 3 -> {
        _sprite = Sprite.player_left;
        if (_moving) {
          _sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, _animate, 20);
        }
      }
      default -> {
        _sprite = Sprite.player_right;
        if (_moving) {
          _sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
        }
      }
    }
  }
}

package main.entities.mob.enemy;

import java.awt.Color;
import main.Board;
import main.Game;
import main.entities.Entity;
import main.entities.Message;
import main.entities.bomb.DirectionalExplosion;
import main.entities.mob.Mob;
import main.entities.mob.Player;
import main.entities.mob.enemy.ai.AI;
import main.graphics.Screen;
import main.graphics.Sprite;
import main.gui.GameSound;
import main.level.Coordinates;

public abstract class Enemy extends Mob {

  protected int _points;

  protected double _speed;
  protected AI _ai;

  protected double MAX_STEPS;
  protected double rest;
  protected double _steps;

  protected int _finalAnimation = 30;
  protected Sprite _deadSprite;

  public Enemy(int x, int y, Board board, Sprite dead, double speed, int points) {
    super(x, y, board);

    _points = points;
    _speed = speed;

    MAX_STEPS = Game.TILES_SIZE / _speed;
    rest = (MAX_STEPS - (int) MAX_STEPS) / MAX_STEPS;
    _steps = MAX_STEPS;

    _timeAfter = 20;
    _deadSprite = dead;
  }

  @Override
  public void update() {
    animate();

    if (!_alive) {
      afterKill();
      return;
    }

    calculateMove();
  }

  @Override
  public void render(Screen screen) {

    if (_alive) {
      chooseSprite();
    } else {
      if (_timeAfter > 0) {
        _sprite = _deadSprite;
        _animate = 0;
      } else {
        _sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3,
            _animate, 60);
      }

    }

    screen.renderEntity((int) _x, (int) _y - _sprite.SIZE, this);
  }

  @Override
  public void calculateMove() {
    int xa = 0, ya = 0;
    if (_steps <= 0) {
      _direction = _ai.calculateDirection();
      _steps = MAX_STEPS;
    }

    if (_direction == 0) {
      ya--;
    }
    if (_direction == 2) {
      ya++;
    }
    if (_direction == 3) {
      xa--;
    }
    if (_direction == 1) {
      xa++;
    }

    if (canMove(xa, ya)) {
      _steps -= 1 + rest;
      move(xa * _speed, ya * _speed);
      _moving = true;
    } else {
      _steps = 0;
      _moving = false;
    }
  }

  @Override
  public void move(double xa, double ya) {
    if (!_alive) {
      return;
    }
    _y += ya;
    _x += xa;
  }

  @Override
  public boolean canMove(double x, double y) {

    double xr = _x, yr = _y - 16;

    if (_direction == 0) {
      yr += _sprite.getSize() - 1;
      xr += _sprite.getSize() / 2;
    }
    if (_direction == 1) {
      yr += _sprite.getSize() / 2;
      xr += 1;
    }
    if (_direction == 2) {
      xr += _sprite.getSize() / 2;
      yr += 1;
    }
    if (_direction == 3) {
      xr += _sprite.getSize() - 1;
      yr += _sprite.getSize() / 2;
    }

    int xx = Coordinates.pixelToTile(xr) + (int) x;
    int yy = Coordinates.pixelToTile(yr) + (int) y;

    Entity a = _board.getEntity(xx, yy, this);
    return a.collide(this);
  }


  @Override
  public boolean collide(Entity e) {

    if (e instanceof DirectionalExplosion) {
      kill();
    }

    if (e instanceof Player) {
      ((Player) e).kill();
      return false;
    }

    return true;
  }

  @Override
  public void kill() {
    if (!_alive) {
      return;
    }
    _alive = false;

    _board.addPoints(_points);

    Message msg = new Message("   +" + _points, getXMessage(), getYMessage(), 1, Color.white, 50);
    _board.addMessage(msg);

    GameSound.getInstance().play(GameSound.DEATH);
  }


  public void set_speed(double speed) {
    _speed = speed;

    MAX_STEPS = Game.TILES_SIZE / _speed;
    rest = (MAX_STEPS - (int) MAX_STEPS) / MAX_STEPS;
    _steps = MAX_STEPS;
  }

  @Override
  protected void afterKill() {
    if (_timeAfter > 0) {
      --_timeAfter;
    } else {

      if (_finalAnimation > 0) {
        --_finalAnimation;
      } else {
        remove();
      }
    }
  }

  protected abstract void chooseSprite();
}

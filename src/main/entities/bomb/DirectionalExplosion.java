package main.entities.bomb;

import java.util.Iterator;
import main.Board;
import main.entities.Entity;
import main.entities.mob.Mob;
import main.graphics.Screen;

public class DirectionalExplosion extends Entity {

  protected Board _board;
  protected int _direction;
  private final int _radius;
  protected int xOrigin, yOrigin;
  protected Explosion[] _explosions;

  public DirectionalExplosion(int x, int y, int direction, int radius, Board board) {
    xOrigin = x;
    yOrigin = y;
    _x = x;
    _y = y;
    _direction = direction;
    _radius = radius;
    _board = board;

    _explosions = new Explosion[calculatePermitedDistance()];
    createExplosions();
  }

  private void createExplosions() {
    boolean last;

    int x = (int) _x;
    int y = (int) _y;
    for (int i = 0; i < _explosions.length; i++) {
      last = i == _explosions.length - 1;

      switch (_direction) {
        case 0 -> y--;
        case 1 -> x++;
        case 2 -> y++;
        case 3 -> x--;
      }
      _explosions[i] = new Explosion(x, y, _direction, last, _board);
    }
  }

  private int calculatePermitedDistance() {
    int radius = 0;
    int x = (int) _x;
    int y = (int) _y;
    while (radius < _radius) {
      if (_direction == 0) {
        y--;
      }
      if (_direction == 1) {
        x++;
      }
      if (_direction == 2) {
        y++;
      }
      if (_direction == 3) {
        x--;
      }

      Entity a = _board.getEntity(x, y, null);

      if (a instanceof Mob) {
        Iterator<Mob> itr = _board._mobs.iterator();

        Mob cur;
        while (itr.hasNext()) {
          cur = itr.next();

          if (cur.getXTile() == x && cur.getYTile() == y) {
            cur.collide(this);
          }
        }
        ++radius;
      }

      if (!a.collide(this)) {
        break;
      }

      ++radius;
    }
    return radius;
  }

  public Explosion explosionAt(int x, int y) {
    for (Explosion explosion : _explosions) {
      if (explosion.getX() == x && explosion.getY() == y) {
        return explosion;
      }
    }
    return null;
  }

  @Override
  public void update() {
  }

  @Override
  public void render(Screen screen) {

    for (Explosion explosion : _explosions) {
      explosion.render(screen);
    }
  }

  @Override
  public boolean collide(Entity e) {
    return true;
  }
}

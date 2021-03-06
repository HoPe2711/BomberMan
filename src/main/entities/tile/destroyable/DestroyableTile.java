package main.entities.tile.destroyable;

import main.entities.Entity;
import main.entities.bomb.DirectionalExplosion;
import main.entities.tile.Tile;
import main.graphics.Sprite;

public class DestroyableTile extends Tile {

	private int _animate = 0;
  protected boolean _destroyed = false;
  protected int _timeToDisapear = 20;
  protected Sprite _belowSprite = Sprite.grass;

  public DestroyableTile(int x, int y, Sprite sprite) {
    super(x, y, sprite);
  }

  @Override
  public void update() {
    if (_destroyed) {
			int MAX_ANIMATE = 7500;
			if (_animate < MAX_ANIMATE) {
				_animate++;
			} else {
				_animate = 0;
			}
			if (_timeToDisapear > 0) {
				_timeToDisapear--;
			} else {
				remove();
			}
    }
  }

  public void destroy() {
    _destroyed = true;
  }

  @Override
  public boolean collide(Entity e) {

		if (e instanceof DirectionalExplosion) {
			destroy();
		}

    return false;
  }

  public void addBelowSprite(Sprite sprite) {
    _belowSprite = sprite;
  }

  protected Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2) {
    int calc = _animate % 30;

    if (calc < 10) {
      return normal;
    }

    if (calc < 20) {
      return x1;
    }

    return x2;
  }

}

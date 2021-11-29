package main.entities.tile.destroyable;


import main.entities.Entity;
import main.entities.bomb.DirectionalExplosion;
import main.graphics.Screen;
import main.graphics.Sprite;
import main.level.Coordinates;

public class BrickTile extends DestroyableTile {

  public BrickTile(int x, int y, Sprite sprite) {
    super(x, y, sprite);
  }

  @Override
  public void update() {
    super.update();
  }

  @Override
  public void render(Screen screen) {
    int x = Coordinates.tileToPixel(_x);
    int y = Coordinates.tileToPixel(_y);

    if (_destroyed) {
      _sprite = movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2);

      screen.renderEntityWithBelowSprite(x, y, this, _belowSprite);
    } else {
      screen.renderEntity(x, y, this);
    }
  }

  @Override
  public boolean collide(Entity e) {

    if (e instanceof DirectionalExplosion) {
      destroy();
    }

    return false;
  }


}

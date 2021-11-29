package main.entities.tile.powerup;

import main.entities.tile.Tile;
import main.graphics.Sprite;

public abstract class Powerup extends Tile {

  protected boolean _active = false;
  protected int _level;

  public Powerup(int x, int y, int level, Sprite sprite) {
    super(x, y, sprite);
    _level = level;
  }

  public abstract void setValues();

  public int getLevel() {
    return _level;
  }
}

package main.entities.tile;

import main.Board;
import main.entities.Entity;
import main.entities.mob.Player;
import main.graphics.Sprite;

public class PortalTile extends Tile {

  protected Board _board;

  public PortalTile(int x, int y, Board board, Sprite sprite) {
    super(x, y, sprite);
    _board = board;
  }

  @Override
  public boolean collide(Entity e) {

    if (e instanceof Player) {

      if (!_board.detectNoEnemies()) {
        return false;
      }

      if (e.getXTile() == getX() && e.getYTile() == getY()) {
        if (_board.detectNoEnemies()) {
          _board.addPoints(_board.getTime() * 5);
          _board.nextLevel();
        }
      }

      return true;
    }

    return false;
  }

}

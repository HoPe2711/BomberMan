package main.entities.mob.enemy;


import main.Board;
import main.Game;
import main.entities.mob.enemy.ai.AIMedium;
import main.graphics.Sprite;

public class Minvo extends Enemy {


  public Minvo(int x, int y, Board board) {
    super(x, y, board, Sprite.minvo_dead, 1.5, 800);

    _sprite = Sprite.minvo_right1;

    _ai = new AIMedium(_board.getPlayer(), this);
    _direction = _ai.calculateDirection();
  }

  @Override
  protected void chooseSprite() {
    switch (_direction) {
      case 0:
      case 1:
        if (_moving) {
          _sprite = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2,
              Sprite.minvo_right3, _animate, 60);
        } else {
          _sprite = Sprite.minvo_left1;
        }
        break;
      case 2:
      case 3:
        if (_moving) {
          _sprite = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3,
              _animate, 60);
        } else {
          _sprite = Sprite.minvo_left1;
        }
        break;
    }
  }
}

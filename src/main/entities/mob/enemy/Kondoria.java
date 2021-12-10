package main.entities.mob.enemy;


import main.Board;
import main.Game;
import main.entities.mob.enemy.ai.AIHigh;
import main.graphics.Sprite;

public class Kondoria extends Enemy {


  public Kondoria(int x, int y, Board board) {
    super(x, y, board, Sprite.kondoria_dead, 1, 1000);

    _sprite = Sprite.kondoria_right1;

    _ai = new AIHigh(_board.getPlayer(), this, board);
    _direction = _ai.calculateDirection();
  }

  @Override
  protected void chooseSprite() {
    switch (_direction) {
      case 0:
      case 1:
        if (_moving) {
          _sprite = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2,
              Sprite.kondoria_right3, _animate, 60);
        } else {
          _sprite = Sprite.kondoria_left1;
        }
        break;
      case 2:
      case 3:
        if (_moving) {
          _sprite = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2,
              Sprite.kondoria_left3, _animate, 60);
        } else {
          _sprite = Sprite.kondoria_left1;
        }
        break;
    }
  }
}

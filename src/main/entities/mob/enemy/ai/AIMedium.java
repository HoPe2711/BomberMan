package main.entities.mob.enemy.ai;

import main.Game;
import main.entities.mob.Player;
import main.entities.mob.enemy.Enemy;
import main.entities.mob.enemy.Oneal;

public class AIMedium extends AI {

  Player _player;
  Enemy _e;

  public AIMedium(Player player, Enemy e) {
    _player = player;
    _e = e;
  }

  @Override
  public int calculateDirection() {
    if (_e instanceof Oneal){
      double speed = (random.nextInt(4)+2)/4.0;
      _e.set_speed(speed);
    }
    if (_player == null) {
      return random.nextInt(4);
    }

    int vertical = random.nextInt(2);

    if (vertical == 1) {
      int v = calculateRowDirection();
      if (v != -1) {
        return v;
      } else {
        return calculateColDirection();
      }

    } else {
      int h = calculateColDirection();

      if (h != -1) {
        return h;
      } else {
        return calculateRowDirection();
      }
    }
  }

  protected int calculateColDirection() {
    if (_player.getXTile() < _e.getXTile()) {
      return 3;
    } else if (_player.getXTile() > _e.getXTile()) {
      return 1;
    }

    return -1;
  }

  protected int calculateRowDirection() {
    if (_player.getYTile() < _e.getYTile()) {
      return 0;
    } else if (_player.getYTile() > _e.getYTile()) {
      return 2;
    }
    return -1;
  }


}

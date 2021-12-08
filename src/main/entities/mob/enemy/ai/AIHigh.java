package main.entities.mob.enemy.ai;

import java.util.ArrayList;
import main.Board;
import main.entities.Entity;
import main.entities.LayeredEntity;
import main.entities.mob.Player;
import main.entities.mob.enemy.Enemy;
import main.entities.tile.GrassTile;

public class AIHigh extends AI {

  Player _player;
  Enemy _e;
  Board _board;
  private final int[] dx = {-1, 0, 1, 0};
  private final int[] dy = {0, 1, 0, -1};

  public AIHigh(Player player, Enemy e, Board board) {
    _player = player;
    _e = e;
    _board = board;
  }

  @Override
  public int calculateDirection() {
    int m = 11;
    int n = 29;
    int[][] a = new int[31][31];
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if (a[i][j] == 1) {
          continue;
        }
        Entity tmp = _board.getEntityAt(j, i);
        if (tmp instanceof GrassTile) {
          a[i][j] = 0;
        } else if (tmp instanceof LayeredEntity) {
          if (((LayeredEntity) tmp).getTopEntity() instanceof GrassTile) {
            a[i][j] = 0;
          } else {
            a[i][j] = 1;
          }
        } else {
          a[i][j] = 1;
        }
        tmp = _board.getBombAt(j, i);
        if (tmp != null) {
          a[i][j] = 1;
          for (int t = 0; t < 4; t++) {
            int u = i + dx[t];
            int v = j + dy[t];
            if (u >= 1 && v >= 1 && u <= m && v <= n) {
              a[u][v] = 1;
            }
          }
        }
      }
    }

    int[] queue = new int[400];
    int[] queue1 = new int[400];
    int[][] dd = new int[30][30];
    int[][] trace = new int[30][30];
    int[][] trace1 = new int[30][30];
    int f, r;
    f = 1;
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        dd[i][j] = 1000000;
      }
    }
    r = 1;
    queue[r] = _e.getYTile();
    queue1[r] = _e.getXTile();
    dd[_e.getYTile()][_e.getXTile()] = 0;
    while (f <= r) {
      int x = queue[f];
      int y = queue1[f];
      f++;
      if (x == _player.getYTile() && y == _player.getXTile()) {
        break;
      }
      for (int i = 0; i < 4; i++) {
        int u = x + dx[i];
        int v = y + dy[i];
        if (u >= 1 && v >= 1 && u <= m && v <= n) {
          if (a[u][v] == 0 && dd[u][v] > dd[x][y] + 1) {
            dd[u][v] = dd[x][y] + 1;
            trace[u][v] = x;
            trace1[u][v] = y;
            r++;
            queue[r] = u;
            queue1[r] = v;
          }
        }
      }
    }
    if (dd[_player.getYTile()][_player.getXTile()] == 1000000
        || dd[_player.getYTile()][_player.getXTile()] == 0) {
      int x = _e.getYTile();
      int y = _e.getXTile();
      ArrayList<Integer> sel = new ArrayList<>();
      for (int i = 0; i < 4; i++) {
        int u = x + dx[i];
        int v = y + dy[i];
        if (u >= 1 && v >= 1 && u <= m && v <= n)
        if (a[u][v] == 0) {
          sel.add(i);
        }
      }
      if (sel.size() == 0) {
        return random.nextInt(4);
      }
      return sel.get(random.nextInt(sel.size()));
    }

    int i = _player.getYTile();
    int j = _player.getXTile();
    while (trace[i][j] != _e.getYTile() || trace1[i][j] != _e.getXTile()) {
      int tmp = trace[i][j];
      int tmp1 = trace1[i][j];
      i = tmp;
      j = tmp1;
    }
    if (i < _e.getYTile()) {
      return 0;
    }
    if (i > _e.getYTile()) {
      return 2;
    }
    if (j < _e.getXTile()) {
      return 3;
    }
    return 1;
  }

}

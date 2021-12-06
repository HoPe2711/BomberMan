package main.gui;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Highscore {

  private TreeMap<String, Integer> highscoreList;
  private String file = "res/data/Highscore";

  public Highscore() {
    highscoreList = new TreeMap<>();
    Robot robot = null;
    try {
      robot = new Robot();
    } catch (AWTException ex) {
      ex.printStackTrace();
    }

    robot.keyPress(KeyEvent.VK_CONTROL);
    robot.keyPress(KeyEvent.VK_P);
    robot.keyRelease(KeyEvent.VK_CONTROL);
    robot.keyRelease(KeyEvent.VK_P);
    FileInputStream fileInputStream = null;
    BufferedReader bufferedReader = null;
    // file data -> Treemap
    try {
      fileInputStream = new FileInputStream(file);
      bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
      String name = bufferedReader.readLine();
      String points = bufferedReader.readLine();
      while (name != null) {
        highscoreList.put(name, Integer.parseInt(points));
        name = bufferedReader.readLine();
        points = bufferedReader.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println(getHighscoreList());
  }

  public Map<String, Integer> getHighscoreList() {
    return sortByValues(highscoreList);
  }

  public static <K, V extends Comparable<V>> Map<K, V>
  sortByValues(final Map<K, V> map) {
    Comparator<K> valueComparator =
        new Comparator<K>() {
          public int compare(K k1, K k2) {
            int compare =
                map.get(k1).compareTo(map.get(k2));
            if (compare == 0)
              return 1;
            else
              return -compare;
          }
        };

    Map<K, V> sortedByValues =
        new TreeMap<K, V>(valueComparator);
    sortedByValues.putAll(map);
    return sortedByValues;
  }

  public void addPoint(int point) {
    String name = "test"; // player name when game over

    highscoreList.put(name, point);
//    System.out.println(getHighscoreList());
  }


  // Treemap -> file data
  public void savePoint() {
//    highscoreList.put("Duc", 20000);

    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(file));
      for (String name : highscoreList.keySet()) {
        Integer point = highscoreList.get(name);
        writer.write(name);
        writer.newLine();
        writer.write(point.toString());
        writer.newLine();
      }
      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

package main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HighscorePanel extends JPanel {

  private final JLabel backButton;
  private final JLabel nameOne;
  private final JLabel nameTwo;
  private final JLabel nameThree;
  private final JLabel nameFour;
  private final JLabel nameFive;
  private final JLabel nameSix;
  private final JLabel scoreOne;
  private final JLabel scoreTwo;
  private final JLabel scoreThree;
  private final JLabel scoreFour;
  private final JLabel scoreFive;
  private final JLabel scoreSix;

  private static final Font font = new Font("Arial", Font.PLAIN, 40);

  private static final int[] ROW = {25, 100, 175, 250, 325, 400, 475};

  private static final int FIRSTCOL = 100;
  private static final int SECONDCOL = 275;
  private static final int THIRDCOL = 700;

  public HighscorePanel() {
    setLayout(new BorderLayout());

    JLabel rank = new JLabel("Rank");
    rank.setFont(font);
    rank.setForeground(Color.white);
    rank.setBounds(FIRSTCOL, ROW[0], 300, 300);

    JLabel name = new JLabel("Player's name");
    name.setFont(font);
    name.setForeground(Color.white);
    name.setBounds(SECONDCOL, ROW[0], 300, 300);

    JLabel score = new JLabel("Score");
    score.setFont(font);
    score.setForeground(Color.white);
    score.setBounds(THIRDCOL, ROW[0], 300, 300);

    JLabel one = new JLabel("1");
    one.setFont(font);
    one.setForeground(Color.white);
    one.setBounds(FIRSTCOL + 35, ROW[1], 300, 300);

    JLabel two = new JLabel("2");
    two.setFont(font);
    two.setForeground(Color.white);
    two.setBounds(FIRSTCOL + 35, ROW[2], 300, 300);

    JLabel three = new JLabel("3");
    three.setFont(font);
    three.setForeground(Color.white);
    three.setBounds(FIRSTCOL + 35, ROW[3], 300, 300);

    JLabel four = new JLabel("4");
    four.setFont(font);
    four.setForeground(Color.white);
    four.setBounds(FIRSTCOL + 35, ROW[4], 300, 300);

    JLabel five = new JLabel("5");
    five.setFont(font);
    five.setForeground(Color.white);
    five.setBounds(FIRSTCOL + 35, ROW[5], 300, 300);

    JLabel six = new JLabel("6");
    six.setFont(font);
    six.setForeground(Color.white);
    six.setBounds(FIRSTCOL + 35, ROW[6], 300, 300);

    nameOne = new JLabel("");
    nameOne.setFont(font);
    nameOne.setForeground(Color.white);
    nameOne.setBounds(SECONDCOL, ROW[1], 300, 300);

    nameTwo = new JLabel("");
    nameTwo.setFont(font);
    nameTwo.setForeground(Color.white);
    nameTwo.setBounds(SECONDCOL, ROW[2], 300, 300);

    nameThree = new JLabel("");
    nameThree.setFont(font);
    nameThree.setForeground(Color.white);
    nameThree.setBounds(SECONDCOL, ROW[3], 300, 300);

    nameFour = new JLabel("");
    nameFour.setFont(font);
    nameFour.setForeground(Color.white);
    nameFour.setBounds(SECONDCOL, ROW[4], 300, 300);

    nameFive = new JLabel("");
    nameFive.setFont(font);
    nameFive.setForeground(Color.white);
    nameFive.setBounds(SECONDCOL, ROW[5], 300, 300);

    nameSix = new JLabel("");
    nameSix.setFont(font);
    nameSix.setForeground(Color.white);
    nameSix.setBounds(SECONDCOL, ROW[6], 300, 300);

    scoreOne = new JLabel("");
    scoreOne.setFont(font);
    scoreOne.setForeground(Color.white);
    scoreOne.setBounds(THIRDCOL, ROW[1], 300, 300);

    scoreTwo = new JLabel("");
    scoreTwo.setFont(font);
    scoreTwo.setForeground(Color.white);
    scoreTwo.setBounds(THIRDCOL, ROW[2], 300, 300);

    scoreThree = new JLabel("");
    scoreThree.setFont(font);
    scoreThree.setForeground(Color.white);
    scoreThree.setBounds(THIRDCOL, ROW[3], 300, 300);

    scoreFour = new JLabel("");
    scoreFour.setFont(font);
    scoreFour.setForeground(Color.white);
    scoreFour.setBounds(THIRDCOL, ROW[4], 300, 300);

    scoreFive = new JLabel("");
    scoreFive.setFont(font);
    scoreFive.setForeground(Color.white);
    scoreFive.setBounds(THIRDCOL, ROW[5], 300, 300);

    scoreSix = new JLabel("");
    scoreSix.setFont(font);
    scoreSix.setForeground(Color.white);
    scoreSix.setBounds(THIRDCOL, ROW[6], 300, 300);

    JLabel highScoreBackGround = setImageIcon(0, 0, "res/textures/Highscore.png");
    backButton = setImageIcon(75, 725, "res/textures/Back_Button.png");

    add(scoreSix);
    add(scoreFive);
    add(scoreFour);
    add(scoreThree);
    add(scoreTwo);
    add(scoreOne);
    add(nameSix);
    add(nameFive);
    add(nameFour);
    add(nameThree);
    add(nameTwo);
    add(nameOne);
    add(six);
    add(five);
    add(four);
    add(three);
    add(two);
    add(one);
    add(score);
    add(name);
    add(rank);
    add(backButton);
    add(highScoreBackGround);
  }

  public JLabel setImageIcon(int x, int y, String source) {

    JLabel jLabel = new JLabel();
    ImageIcon imageIcon = new ImageIcon(source);

    jLabel.setBounds(x, y, imageIcon.getIconWidth(), imageIcon.getIconHeight());
    jLabel.setIcon(imageIcon);
    return jLabel;
  }

  public void updateScore(Map<String, Integer> scores) {

    ArrayList<String> names = new ArrayList<>();
    ArrayList<Integer> points = new ArrayList<>();

    for (Map.Entry<String, Integer> pair : scores.entrySet()) {
      names.add(pair.getKey());
      points.add(pair.getValue());
    }

    if (names.size() >= 1) {
      nameOne.setText(names.get(0));
      scoreOne.setText(points.get(0).toString());
    }
    if (names.size() >= 2) {
      nameTwo.setText(names.get(1));
      scoreTwo.setText(points.get(1).toString());
    }
    if (names.size() >= 3) {
      nameThree.setText(names.get(2));
      scoreThree.setText(points.get(2).toString());
    }
    if (names.size() >= 4) {
      nameFour.setText(names.get(3));
      scoreFour.setText(points.get(3).toString());
    }
    if (names.size() >= 5) {
      nameFive.setText(names.get(4));
      scoreFive.setText(points.get(4).toString());
    }
    if (names.size() >= 6) {
      nameSix.setText(names.get(5));
      scoreSix.setText(points.get(5).toString());
    }
  }

  public JLabel getBackButton() {
    return backButton;
  }
}

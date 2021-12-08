package main.gui;

import java.awt.BorderLayout;
import java.util.TreeMap;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HighscorePanel extends JPanel {

  private final JLabel highScoreBackGround;
  private final JLabel backButton;
  private JLabel user;

  public HighscorePanel() {
    setLayout(new BorderLayout());
    highScoreBackGround = setImageIcon(0, 0, "res/textures/backgroundGradient.jpg");
    backButton = setImageIcon(75, 740, "res/textures/Back_Button.png");

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

  public void updateScore(TreeMap<String, Integer> scores) {
    //render score
  }

  public JLabel getBackButton() {
    return backButton;
  }
}

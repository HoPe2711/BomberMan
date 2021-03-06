package main.gui;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Howtoplay_Objects extends JPanel {

  private final JLabel backButton;

  public Howtoplay_Objects() {
    setLayout(new BorderLayout());
    JLabel howtoplay_objectsLabel = setImageIcon(0, 0, "res/textures/Howtoplay_Objects.png");
    backButton = setImageIcon(75, 725, "res/textures/Back_Button.png");

    add(backButton);
    add(howtoplay_objectsLabel);
  }

  public JLabel setImageIcon(int x, int y, String source) {

    JLabel jLabel = new JLabel();
    ImageIcon imageIcon = new ImageIcon(source);

    jLabel.setBounds(x, y, imageIcon.getIconWidth(), imageIcon.getIconHeight());
    jLabel.setIcon(imageIcon);
    return jLabel;
  }

  public JLabel getBackButton() {
    return backButton;
  }
}

package main.gui;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Howtoplay_Mobs extends JPanel {

  private JLabel howtoplay_mobsLabel;
  private JLabel nextButton;
  private JLabel backButton;

  public Howtoplay_Mobs() {
    setLayout(new BorderLayout());
    howtoplay_mobsLabel = setImageIcon(0, 0, "res/textures/Howtoplay_Mobs.png");
    nextButton = setImageIcon(825, 740, "res/textures/Next_Button.png");
    backButton = setImageIcon(75, 740, "res/textures/Back_Button.png");

    add(nextButton);
    add(backButton);
    add(howtoplay_mobsLabel);
  }

  public JLabel setImageIcon(int x, int y, String source) {

    JLabel jLabel = new JLabel();
    ImageIcon imageIcon = new ImageIcon(source);

    jLabel.setBounds(x, y, imageIcon.getIconWidth(), imageIcon.getIconHeight());
    jLabel.setIcon(imageIcon);
    return jLabel;
  }

  public JLabel getNextButton() {
    return nextButton;
  }

  public JLabel getBackButton() {
    return backButton;
  }
}

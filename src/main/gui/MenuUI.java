package main.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.Game;

public class MenuUI {
  private JPanel menuContainer;
  private JPanel menuPane;
  private JPanel gameinfoContainer;
  private Howtoplay_Mobs howtoplay_mobsPane;

  private CardLayout cardLayout;

  private JLabel backgroundLabel;
  private JLabel playLabel;
  private JLabel continueLabel;
  private JLabel highscoreLabel;
  private JLabel howtoplayLabel;
  private JLabel exitLabel;

  private InfoPanel infoPanel;
  private GamePanel gamePanel;

  private Frame _frame;

  private static final int xLabel = 140;
  private static final int yLabel = 300;

  public MenuUI(Frame frame) {
    menuContainer = new JPanel();
    cardLayout = new CardLayout();
    menuContainer.setLayout(cardLayout);

    menuPane = new JPanel();
    menuPane.setLayout(null);

    _frame = frame;

    gamePanel = new GamePanel(_frame);
    infoPanel = new InfoPanel(gamePanel.getGame());
    howtoplay_mobsPane = new Howtoplay_Mobs(_frame);
    gameinfoContainer = new JPanel(new BorderLayout());
    gameinfoContainer.add(infoPanel, BorderLayout.PAGE_START);
    gameinfoContainer.add(gamePanel, BorderLayout.PAGE_END);

    setMenu();

    menuContainer.add(gameinfoContainer, "GAME_PANEL");
    menuContainer.add(howtoplay_mobsPane, "HTP_MOBS");

    _frame.add(menuContainer);

    setClick(new MouseProcess(this));
  }

  public void setClick(MouseAdapter click){
    playLabel.addMouseListener(click);
    highscoreLabel.addMouseListener(click);
    howtoplayLabel.addMouseListener(click);
    exitLabel.addMouseListener(click);
  }

  public void setMenu() {
    backgroundLabel = setImageIcon(0,0,"res/textures/backgroundMenu.png");
    playLabel = setImageIcon(xLabel,yLabel,"res/textures/Play_Button.png");
    continueLabel = setImageIcon(xLabel, yLabel + 100, "res/textures/Continue_Button.png");
    highscoreLabel = setImageIcon(xLabel, yLabel + 200, "res/textures/Highscore_Button.png");
    howtoplayLabel = setImageIcon(xLabel, yLabel + 300, "res/textures/Howtoplay_Button.png");
    exitLabel = setImageIcon(xLabel, yLabel + 400, "res/textures/Exit_Button.png");

    menuPane.add(playLabel);
    menuPane.add(continueLabel);
    menuPane.add(highscoreLabel);
    menuPane.add(howtoplayLabel);
    menuPane.add(exitLabel);

    menuPane.add(backgroundLabel);
    menuContainer.add(menuPane, "MENU_PANEL");
  }

  public JLabel setImageIcon(int x, int y, String source){

    JLabel jLabel = new JLabel();
    ImageIcon imageIcon = new ImageIcon(source);

    jLabel.setBounds(x,y,imageIcon.getIconWidth(),imageIcon.getIconHeight());
    jLabel.setIcon(imageIcon);
    return jLabel;
  }

  public JPanel getMenuContainer() {
    return menuContainer;
  }

  public JPanel getMenuPane() {
    return menuPane;
  }

  public JLabel getBackgroundLabel() {
    return backgroundLabel;
  }

  public JLabel getPlayLabel() {
    return playLabel;
  }

  public JLabel getHighscoreLabel() {
    return highscoreLabel;
  }

  public JLabel getExitLabel() {
    return exitLabel;
  }

  public CardLayout getCardLayout() {
    return cardLayout;
  }

  public GamePanel getGamePanel() {
    return gamePanel;
  }

  public InfoPanel getInfoPanel() {
    return infoPanel;
  }

  public JPanel getGameinfoContainer() {
    return gameinfoContainer;
  }

  public JLabel getHowtoplayLabel() {
    return howtoplayLabel;
  }

  public Frame getFrame() {
    return _frame;
  }
}

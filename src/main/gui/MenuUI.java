package main.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuUI {

  private final JPanel menuContainer;
  private final JPanel menuPane;
  private final Howtoplay_Mobs howtoplay_mobsPane;
  private final Howtoplay_Objects howtoplay_objectsPane;
  private final HighscorePanel highscorePanel;

  private final CardLayout cardLayout;

  private JLabel playLabel;
  private JLabel continueLabel;
  private JLabel highscoreLabel;
  private JLabel howtoplay_mobsLabel;
  private JLabel exitLabel;

  private final InfoPanel infoPanel;
  private final GamePanel gamePanel;

  private final Frame _frame;

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
    howtoplay_mobsPane = new Howtoplay_Mobs();
    howtoplay_objectsPane = new Howtoplay_Objects();
    highscorePanel = new HighscorePanel();
    JPanel gameinfoContainer = new JPanel(new BorderLayout());
    gameinfoContainer.add(infoPanel, BorderLayout.PAGE_START);
    gameinfoContainer.add(gamePanel, BorderLayout.PAGE_END);

    setMenu();

    menuContainer.add(gameinfoContainer, "GAME_PANEL");
    menuContainer.add(howtoplay_mobsPane, "HTP_MOBS");
    menuContainer.add(howtoplay_objectsPane, "HTP_OBJECTS");
    menuContainer.add(highscorePanel, "HS_PANEL");

    _frame.add(menuContainer);

    GameSound.getInstance().stop();
    GameSound.getInstance().play(GameSound.BACKGROUND);

    setClick(new MouseProcess(this));
  }

  public void setClick(MouseAdapter click) {
    playLabel.addMouseListener(click);
    continueLabel.addMouseListener(click);
    highscoreLabel.addMouseListener(click);
    howtoplay_mobsLabel.addMouseListener(click);
    exitLabel.addMouseListener(click);

    howtoplay_mobsPane.getNextButton().addMouseListener(click);
    howtoplay_mobsPane.getBackButton().addMouseListener(click);
    howtoplay_objectsPane.getBackButton().addMouseListener(click);
    highscorePanel.getBackButton().addMouseListener(click);
  }

  public void setMenu() {
    JLabel backgroundLabel = setImageIcon(0, 0, "res/textures/backgroundMenu.png");
    playLabel = setImageIcon(xLabel, yLabel, "res/textures/Play_Button.png");
    continueLabel = setImageIcon(xLabel, yLabel + 100, "res/textures/Continue_Button.png");
    highscoreLabel = setImageIcon(xLabel, yLabel + 200, "res/textures/Highscore_Button.png");
    howtoplay_mobsLabel = setImageIcon(xLabel, yLabel + 300, "res/textures/Howtoplay_Button.png");
    exitLabel = setImageIcon(xLabel, yLabel + 400, "res/textures/Exit_Button.png");

    menuPane.add(playLabel);
    menuPane.add(continueLabel);
    menuPane.add(highscoreLabel);
    menuPane.add(howtoplay_mobsLabel);
    menuPane.add(exitLabel);

    menuPane.add(backgroundLabel);
    menuContainer.add(menuPane, "MENU_PANEL");
  }

  public JLabel setImageIcon(int x, int y, String source) {

    JLabel jLabel = new JLabel();
    ImageIcon imageIcon = new ImageIcon(source);

    jLabel.setBounds(x, y, imageIcon.getIconWidth(), imageIcon.getIconHeight());
    jLabel.setIcon(imageIcon);
    return jLabel;
  }

  public Frame get_frame() {
    return _frame;
  }

  public JPanel getMenuContainer() {
    return menuContainer;
  }

  public JPanel getMenuPane() {
    return menuPane;
  }

  public HighscorePanel getHighscorePanel() {
    return highscorePanel;
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

  public Frame getFrame() {
    return _frame;
  }

  public Howtoplay_Mobs getHowtoplay_mobsPane() {
    return howtoplay_mobsPane;
  }

  public JLabel getContinueLabel() {
    return continueLabel;
  }

  public JLabel getHowtoplay_mobsLabel() {
    return howtoplay_mobsLabel;
  }

  public Howtoplay_Objects getHowtoplay_objectsPane() {
    return howtoplay_objectsPane;
  }
}

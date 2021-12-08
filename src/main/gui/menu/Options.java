package main.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import main.gui.Frame;

public class Options extends JMenu {

  Frame _frame;

  public Options(Frame frame) {
    super("Options");

    _frame = frame;

    JMenuItem pause = new JMenuItem("Pause");
    pause.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
    pause.addActionListener(new MenuActionListener(frame));
    add(pause);

    JMenuItem resume = new JMenuItem("Resume");
    resume.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
    resume.addActionListener(new MenuActionListener(frame));
    add(resume);
  }


  static class MenuActionListener implements ActionListener {

    public Frame _frame;

    public MenuActionListener(Frame frame) {
      _frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

      if (e.getActionCommand().equals("Pause")) {
        _frame.pauseGame();
      }

      if (e.getActionCommand().equals("Resume")) {
        _frame.resumeGame();
      }
    }
  }
}

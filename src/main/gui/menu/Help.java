package main.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import main.gui.Frame;
import main.gui.InfoDialog;

public class Help extends JMenu {

  public Help(Frame frame) {
    super("About");

    JMenuItem instructions = new JMenuItem("Author");
    instructions.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
    instructions.addActionListener(new MenuActionListener(frame));
    add(instructions);

  }

  static class MenuActionListener implements ActionListener {

    public Frame _frame;

    public MenuActionListener(Frame frame) {
      _frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

      if (e.getActionCommand().equals("Author")) {
        new InfoDialog(_frame, "Introduction",
            "<html><font face='Calibri' size='6' color='black'>Nguyễn Thái Bình<br>Github: https://github.com/HoPe2711<br><br>Nguyễn Tấn Minh<br>Github: https://github.com/20020081",
            JOptionPane.QUESTION_MESSAGE);
      }

    }
  }
}

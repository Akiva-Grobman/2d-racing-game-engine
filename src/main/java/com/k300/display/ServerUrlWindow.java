package com.k300.display;

import com.k300.Launcher;
import com.k300.states.OnlineState;
import com.k300.states.StateManager;
import com.k300.utils.configarations.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class ServerUrlWindow extends JDialog implements ActionListener {
    static JTextField serverUrlBox;
    Launcher launcher;

    public ServerUrlWindow(Launcher launcher) {
        JComponent caller = launcher.getWindowJComponent();
        this.launcher = launcher;
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);
        this.setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.setBackground(new Color(160, 160, 160));
        JLabel toastLabel = new JLabel("Enter server IP:");
        serverUrlBox = new JTextField(16);
        serverUrlBox.setText(Config.getUrl());
        JButton confirmBtn = new JButton("Confirm");
        confirmBtn.addActionListener(this);
        toastLabel.setForeground(Color.WHITE);
        panel.add(toastLabel);
        panel.add(serverUrlBox);
        panel.add(confirmBtn);
        this.add(panel);
        this.pack();

        java.awt.Window window = SwingUtilities.getWindowAncestor(caller);
        int xCoordinate = window.getLocationOnScreen().x + window.getWidth() / 2 - this.getWidth() / 2;
        int yCoordinate = window.getLocationOnScreen().y + (int)((double)window.getHeight() * 0.75) - this.getHeight() / 2;
        this.setLocation(xCoordinate, yCoordinate);
        this.setShape(new RoundRectangle2D.Double(0, 0, this.getWidth(), this.getHeight(), 30, 30));
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Config.setUrl(serverUrlBox.getText());
        this.dispose();
        StateManager.setCurrentState(new OnlineState(launcher));
    }
}

package com.k300.display;

import com.k300.Launcher;
import com.k300.graphics.Assets;
import com.k300.graphics.FontLoader;
import com.k300.states.OnlineState;
import com.k300.states.StateManager;
import com.k300.utils.configarations.Config;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import static com.k300.utils.Utils.drawStringInCenter;
import static com.k300.utils.Utils.resizeImage;

public class ServerUrlWindow extends JDialog implements ActionListener {
    static JTextField serverUrlBox;
    Launcher launcher;

    public ServerUrlWindow(Launcher launcher) {
        Font minecraft = FontLoader.loadFont("Minecraft", 40);
        Font boxFont = new Font("Courier New", Font.BOLD, 38);
        JComponent caller = launcher.getWindowJComponent();
        this.launcher = launcher;
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(50, 450, 100, 450));
        panel.setBackground(new Color(38, 2, 2));
        panel.setLayout(new GridLayout(3,1,0,60));

        JLabel windowLabel = new JLabel("ENTER SERVER URL :");
        windowLabel.setFont(minecraft);
        windowLabel.setForeground(Color.white);

        serverUrlBox = new JTextField(20);
        serverUrlBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        serverUrlBox.setFont(boxFont);
        serverUrlBox.setText(Config.getUrl());

        BufferedImage buttonImage = Assets.getImage(Assets.BUTTON_KEY);
        buttonImage = resizeImage(buttonImage, 500, 150);
        Graphics buttonGraphics = buttonImage.getGraphics();
        buttonGraphics.setFont(minecraft);
        drawStringInCenter(0, 0, buttonImage.getWidth(), buttonImage.getHeight(), buttonGraphics, "CONFIRM");
        JButton confirmBtn = new JButton(new ImageIcon(buttonImage));
        confirmBtn.setSize(10,10);
        confirmBtn.setBorder(BorderFactory.createEmptyBorder());
        confirmBtn.setContentAreaFilled(false);
        confirmBtn.addActionListener(this);

        panel.add(windowLabel);
        panel.add(serverUrlBox);
        panel.add(confirmBtn);
        this.add(panel);
        this.pack();

        java.awt.Window window = SwingUtilities.getWindowAncestor(caller);
        int xCoordinate = window.getLocationOnScreen().x + window.getWidth() / 2 - this.getWidth() / 2;
        int yCoordinate = window.getLocationOnScreen().y + window.getHeight() / 2 - this.getHeight() / 2;
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

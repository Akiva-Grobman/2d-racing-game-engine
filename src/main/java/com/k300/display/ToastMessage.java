package com.k300.display;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ToastMessage extends JDialog {

    private static final long serialVersionUID = 1L;
    private static Boolean spamProtect = false;
    private final int milliseconds = 1500;

    public ToastMessage(JComponent caller, String toastString) {
        if(spamProtect) {
            return;
        }
        setUndecorated(true);
        setAlwaysOnTop(true);
        setFocusableWindowState(false);
        setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.setBackground(new Color(160, 160, 160));
        JLabel toastLabel = new JLabel(toastString);
        toastLabel.setForeground(Color.WHITE);
        panel.add(toastLabel);
        add(panel);
        pack();

        java.awt.Window window = SwingUtilities.getWindowAncestor(caller);
        int xCoordinate = window.getLocationOnScreen().x + window.getWidth() / 2 - getWidth() / 2;
        int yCoordinate = window.getLocationOnScreen().y + (int)((double)window.getHeight() * 0.75) - getHeight() / 2;
        setLocation(xCoordinate, yCoordinate);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
        setVisible(true);

        new Thread(() -> {
            try {
                spamProtect = true;
                Thread.sleep(milliseconds);
                dispose();
                spamProtect = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
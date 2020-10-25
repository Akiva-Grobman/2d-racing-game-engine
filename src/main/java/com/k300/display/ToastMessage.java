package com.k300.display;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ToastMessage extends JDialog {

    public static final int SHORT = 1500;
    public static final int LONG = 3000;
    private static final long serialVersionUID = 1L;
    private static boolean spamProtect = false;

    // will only be initialized from the makeToast method
    private ToastMessage(){}

    public static void makeToast(JComponent caller, String toastString, int toastDuration) {
        final ToastMessage toastMessage = new ToastMessage();
        if(spamProtect) {
            return;
        }
        toastMessage.setUndecorated(true);
        toastMessage.setAlwaysOnTop(true);
        toastMessage.setFocusableWindowState(false);
        toastMessage.setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.setBackground(new Color(160, 160, 160));
        JLabel toastLabel = new JLabel(toastString);
        toastLabel.setForeground(Color.WHITE);
        panel.add(toastLabel);
        toastMessage.add(panel);
        toastMessage.pack();

        java.awt.Window window = SwingUtilities.getWindowAncestor(caller);
        int xCoordinate = window.getLocationOnScreen().x + window.getWidth() / 2 - toastMessage.getWidth() / 2;
        int yCoordinate = window.getLocationOnScreen().y + (int)((double)window.getHeight() * 0.75) - toastMessage.getHeight() / 2;
        toastMessage.setLocation(xCoordinate, yCoordinate);
        toastMessage.setShape(new RoundRectangle2D.Double(0, 0, toastMessage.getWidth(), toastMessage.getHeight(), 30, 30));
        toastMessage.setVisible(true);

        new Thread(() -> {
            try {
                spamProtect = true;
                Thread.sleep(toastDuration);
                toastMessage.dispose();
                spamProtect = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
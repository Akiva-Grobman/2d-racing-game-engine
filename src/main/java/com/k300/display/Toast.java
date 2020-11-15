package com.k300.display;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Toast extends JDialog {

    public static final int SHORT = 1500;
    public static final int LONG = 3000;
    public static final int VERY_LONG = (int) Math.floor(LONG * 1.2);
    private static final long serialVersionUID = 1L;
    private static boolean spamProtect = false;

    // will only be initialized from the makeToast method
    private Toast(){}

    public static void makeToast(JComponent caller, String toastString, int toastDuration) {
        final Toast toast = new Toast();
        if(spamProtect) {
            return;
        }
        toast.setUndecorated(true);
        toast.setAlwaysOnTop(true);
        toast.setFocusableWindowState(false);
        toast.setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.setBackground(new Color(160, 160, 160));
        JLabel toastLabel = new JLabel(toastString);
        toastLabel.setForeground(Color.WHITE);
        panel.add(toastLabel);
        toast.add(panel);
        toast.pack();

        java.awt.Window window = SwingUtilities.getWindowAncestor(caller);
        int xCoordinate = window.getLocationOnScreen().x + window.getWidth() / 2 - toast.getWidth() / 2;
        int yCoordinate = window.getLocationOnScreen().y + (int)((double)window.getHeight() * 0.75) - toast.getHeight() / 2;
        toast.setLocation(xCoordinate, yCoordinate);
        toast.setShape(new RoundRectangle2D.Double(0, 0, toast.getWidth(), toast.getHeight(), 30, 30));
        toast.setVisible(true);

        new Thread(() -> {
            try {
                spamProtect = true;
                Thread.sleep(toastDuration);
                toast.dispose();
                spamProtect = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
package com.akivaGrobman.display;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.ImageObserver;

public class Window {

    public final int WIDTH;
    public final int HEIGHT;
    private final String title;
    private JFrame frame;
    private Canvas canvas;

    public Window(String title) {
        // todo
        // fix height
        this(title, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
    }

    public Window(String title, int width, int height) {
        this.title = title;
        WIDTH = width;
        HEIGHT = height;
        createDisplay();
    }

    private void createDisplay() {
        frame = new JFrame(title);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        frame.setVisible(true);
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        canvas.setFocusable(false);
        frame.add(canvas);
        frame.pack();
    }

    public void setBufferStrategy(int sumOfBuffers) {
        if(canvas.getBufferStrategy() == null) {
            canvas.createBufferStrategy(sumOfBuffers);
        }
    }

    public Graphics getGraphics() {
        return canvas.getBufferStrategy().getDrawGraphics();
    }

    public void show() {
        canvas.getBufferStrategy().show();
        canvas.getBufferStrategy().getDrawGraphics().dispose();
    }

    public void clear() {
        canvas.getBufferStrategy().getDrawGraphics().fillRect(0, 0, WIDTH, HEIGHT);
    }

}

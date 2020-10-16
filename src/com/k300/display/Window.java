package com.k300.display;

import com.k300.graphics.Assets;
import com.k300.graphics.Zoom;
import com.k300.io.MouseListener;
import com.k300.utils.math.Converter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Window {

    private final int WIDTH;
    private final int HEIGHT;
    private final String title;
    private JFrame frame;
    private Canvas canvas;
    private BufferedImage fullHDImage;

    public Window(String title) {
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
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.toFront();
        frame.setUndecorated(true);
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

    public void setKeyListener(KeyListener keyListener) {
        frame.addKeyListener(keyListener);
    }

    public Graphics getGraphics() {
        fullHDImage = getFDHImage();
        return fullHDImage.getGraphics();
    }

    public void setVisible() {
        frame.setVisible(true);
    }

    public void show() {
        fullHDImage = Zoom.getZoomedImage(Converter.FHD_SCREEN_WIDTH/2f, Converter.FHD_SCREEN_HEIGHT/2f, Converter.FHD_SCREEN_WIDTH/2f, Converter.FHD_SCREEN_HEIGHT/2f, fullHDImage);
        drawImageRelativeToScreen(fullHDImage);
        canvas.getBufferStrategy().show();
        canvas.getBufferStrategy().getDrawGraphics().dispose();
    }

    private BufferedImage getFDHImage() {
        return new BufferedImage(Converter.FHD_SCREEN_WIDTH, Converter.FHD_SCREEN_HEIGHT, Assets.getImage(Assets.TRACK_KEY).getType());
    }

    private void drawImageRelativeToScreen(BufferedImage fullHdImage) {
        double relativeWidth = Converter.getProportionalNumber(Converter.FHD_SCREEN_WIDTH);
        double relativeHeight = Converter.getProportionalNumber(Converter.FHD_SCREEN_HEIGHT);
        double centeredY = getCenteredY(relativeHeight);
        canvas.getBufferStrategy().getDrawGraphics().drawImage(fullHdImage, 0, (int) centeredY, (int) relativeWidth, (int) relativeHeight, null);
    }

    private double getCenteredY(double relativeHeight) {
        double fullOriginalHeight = Converter.SCREEN_HEIGHT;
        return (fullOriginalHeight - relativeHeight) / 2;
    }

    public void clear() {
        Graphics graphics = canvas.getBufferStrategy().getDrawGraphics();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
    }

    public void addMouseListener(MouseListener mouseListener) {
        frame.addMouseListener(mouseListener);
        frame.addMouseMotionListener(mouseListener);
        canvas.addMouseListener(mouseListener);
        canvas.addMouseMotionListener(mouseListener);
    }

}

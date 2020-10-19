package com.k300.graphics;

import com.k300.Launcher;
import com.k300.states.State;
import com.k300.ui.listeners.FadeListener;
import com.k300.utils.math.Converter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FadeState extends State {

    private final int SECONDS;
    private final BufferedImage image;
    private final FadeListener fadeListener;
    private float alpha;
    private Long startTime;
    private boolean fadeout;

    public FadeState(Launcher launcher, BufferedImage image, FadeListener fadeListener, int fadeTime) {
        super(launcher);
        this.image = image;
        this.fadeListener = fadeListener;
        SECONDS = fadeTime;
        setIsFadingIn(true);
        alpha = 1;
    }

    public void tick() {
        if(startTime == null) {
            startTime = System.currentTimeMillis();
            fadeStarted();
        }
        long difference = System.currentTimeMillis() - startTime;
        int duration = SECONDS * 1500;
        alpha = difference / (float) duration;
        if(alpha > 1) {
            alpha = 1;
            fadeCompleted();
        }
        if(fadeout) {
            alpha = 1 - alpha;
        }
    }

    public void render(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics.create();
        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        graphics2D.drawImage(image, 0, 0, Converter.FHD_SCREEN_WIDTH , Converter.FHD_SCREEN_HEIGHT, null);
    }

    public void setIsFadingIn(boolean isFadingIn) {
        fadeout = !isFadingIn;
    }

    public void reset() {
        alpha = 0;
        startTime = null;
        setIsFadingIn(fadeout);
    }

    protected void fadeStarted() {
        if (fadeListener != null) {
            fadeListener.fadeStarted(this, launcher);
        }
    }

    protected void fadeCompleted() {
        if (fadeListener != null) {
            fadeListener.fadeCompleted(this);
        }
    }

}

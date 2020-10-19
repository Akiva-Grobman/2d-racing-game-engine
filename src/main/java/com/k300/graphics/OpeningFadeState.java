package com.k300.graphics;

import com.k300.Launcher;
import com.k300.ui.listeners.FadeListener;

import java.awt.image.BufferedImage;

public class OpeningFadeState extends FadeState {

    private int fadeCount;

    public OpeningFadeState(Launcher launcher, BufferedImage image, FadeListener fadeListener) {
        super(launcher, image, fadeListener, 2);
        fadeCount = 0;
    }

    public void fade() {
        fadeCount++;
    }

    public boolean isOver() {
        return fadeCount >= 3;
    }

}

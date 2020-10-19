package com.k300.ui.buttons;

import com.k300.Launcher;
import com.k300.graphics.Assets;
import com.k300.states.SettingsState;
import com.k300.states.StateManager;
import com.k300.ui.listeners.ClickListener;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIFourPlayersButton extends UIButton {

    private final ClickListener clickListener;

    public UIFourPlayersButton(float x, float y, int width, int height, ClickListener clickListener) {
        super(x, y, width, height);
        this.clickListener = clickListener;
    }

    @Override
    public void render(Graphics graphics) {
        BufferedImage image;
        if(isHovering) {
            image = Assets.getImage(Assets.FOUR_PLAYERS_BUTTON_HOVER_KEY);
        } else {
            image = Assets.getImage(Assets.FOUR_PLAYERS_BUTTON_KEY);
        }
        graphics.drawImage(image, (int) x, (int) y, width, height, null);
    }

    @Override
    public void onClick() {
        clickListener.onClick();
    }

}
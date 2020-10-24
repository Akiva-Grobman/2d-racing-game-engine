package com.k300.ui.buttons;

import com.k300.graphics.Assets;
import com.k300.graphics.FontLoader;
import com.k300.ui.listeners.ClickListener;
import com.k300.utils.math.Converter;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static com.k300.utils.Utils.drawStringInCenter;

public class UIMenuButton extends UIButton {

    protected final ClickListener clickListener;

    public UIMenuButton(float x, float y, int width, int height, String text, ClickListener clickListener) {
        super(x, y, width, height, text, 70);
        this.clickListener = clickListener;
    }

    public UIMenuButton(float x, float y, int width, int height, String text, int fontSize, ClickListener clickListener) {
        super(x, y, width, height, text, fontSize);
        this.clickListener = clickListener;
    }

    @Override
    public void render(Graphics graphics) {
        BufferedImage image;
        if(isHovering) {
            image = Assets.getImage(Assets.BUTTON_HOVER_KEY);
        } else {
            image = Assets.getImage(Assets.BUTTON_KEY);
        }
        graphics.drawImage(image, (int) x, (int) y, width, height, null);
        graphics.drawImage(image, (int) x, (int) y, width, height, null);

        Font currentFont = graphics.getFont();
        graphics.setFont(FontLoader.loadFont(currentFont.getFontName(), fontSize));
        drawStringInCenter(x, y, width, height, graphics, text);
    }

    @Override
    public void onClick() {
        clickListener.onClick();
    }
}

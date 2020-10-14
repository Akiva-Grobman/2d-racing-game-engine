package com.k300.ui.buttons;

import com.k300.Launcher;
import com.k300.states.MenuState;
import com.k300.states.StateManager;
import java.awt.*;

public class UIBackButton extends UIButton {

    private final Launcher launcher;

    public UIBackButton(float x, float y, int width, int height, Launcher launcher) {
        super(x, y, width, height);
        this.launcher = launcher;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics graphics) {
        Color originalColor = graphics.getColor();
        if(isHovering) {
            graphics.setColor(Color.yellow);
        } else {
            graphics.setColor(Color.white);
        }
        String back = "Back";
        graphics.setFont(new Font("Italic", Font.ITALIC, 50));
        int stringWidth = graphics.getFontMetrics().stringWidth(back);
        int stringHeight = graphics.getFontMetrics().getDescent();
        graphics.drawString(back, (int) (x + (width / 2 - stringWidth / 2)), (int) y + (height / 2 - stringHeight / 2));
        graphics.setColor(originalColor);
    }

    @Override
    public void onClick() {
        StateManager.setCurrentState(new MenuState(launcher));
    }

}

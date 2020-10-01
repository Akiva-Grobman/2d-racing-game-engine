package com.k300.states;

import com.k300.Launcher;

import java.awt.*;

import com.k300.graphics.Assets;
import com.k300.ui.UIImageButton;
import com.k300.ui.UIManager;

public class MenuState extends State{

    private final UIManager uiManager;

    public MenuState(Launcher launcher) {
        super(launcher);
        uiManager = new UIManager();
        launcher.getMouseListener().setUiManager(uiManager);
        uiManager.addUIObject(new UIImageButton(200, 200, 128, 64, Assets.getImage(Assets.PLAY_BUTTON_KEY), () -> {
            launcher.getMouseListener().setUiManager(null);
            StateManager.setCurrentState(launcher.getGameState());
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics graphics) {
        uiManager.render(graphics);
    }

}
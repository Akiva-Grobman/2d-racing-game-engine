package com.k300.states;

import com.k300.Launcher;

import java.awt.*;

import com.k300.Launcher;
import com.k300.graphics.Assets;
import com.k300.ui.ClickListener;
import com.k300.ui.UIImageButton;
import com.k300.ui.UIManager;

import java.awt.*;

public class MenuState extends State{

    private UIManager uiManager;

    public MenuState(Launcher launcher) {
        super(launcher);
        uiManager = new UIManager(launcher);
        launcher.getMouseManager().setUiManager(uiManager);
        uiManager.addUIObject(new UIImageButton(200, 200, 128, 64, Assets.startButtons, () -> {
            launcher.getMouseManager().setUiManager(null);
            StateManager.setCurrentState(launcher.getGame().gameState);
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
        launcher.getMouseManager().setUiManager(null);
        StateManager.setCurrentState(launcher.getGame().gameState);
    }

    @Override
    public void render(Graphics graphics) {
        uiManager.render(graphics);
    }

}
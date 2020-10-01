package com.k300.ui;

import com.k300.Launcher;
import com.k300.graphics.FadeState;
import com.k300.graphics.OpeningFadeState;
import com.k300.states.MenuState;
import com.k300.states.StateManager;

public class OpenFadeListener implements FadeListener {

    @Override
    public void fadeStarted(FadeState fadeState, Launcher launcher) {
        OpeningFadeState openingFadeState = (OpeningFadeState) fadeState;
        openingFadeState.fade();
        if(openingFadeState.isOver()) {
            StateManager.setCurrentState(new MenuState(launcher));
        }
    }

    @Override
    public void fadeCompleted(FadeState fadeState) {
        fadeState.reset();
    }

}

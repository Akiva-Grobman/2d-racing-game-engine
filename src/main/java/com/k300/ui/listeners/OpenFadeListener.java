package com.k300.ui.listeners;

import com.k300.Launcher;
import com.k300.graphics.FadeState;
import com.k300.display.OpeningFadeState;
import com.k300.states.MenuState;
import com.k300.states.StateManager;

/*
*       Purpose:
*           control open fade state (opening image)
*/

public class OpenFadeListener implements FadeListener {

    // this will be to start the fade state
    @Override
    public void fadeStarted(FadeState fadeState, Launcher launcher) {
        OpeningFadeState openingFadeState = (OpeningFadeState) fadeState;
        // will begin the fade
        openingFadeState.fade();
        // when the fade is over (it will fade twice before this condition should be met)
        if(openingFadeState.isOver()) {
            // switch to menu state
            StateManager.setCurrentState(new MenuState(launcher));
        }
    }

    // this will reset fade state (and eventually that will stop it)
    @Override
    public void fadeCompleted(FadeState fadeState) {
        fadeState.reset();
    }

}

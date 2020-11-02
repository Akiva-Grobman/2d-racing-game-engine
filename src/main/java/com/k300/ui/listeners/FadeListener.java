package com.k300.ui.listeners;

import com.k300.Launcher;
import com.k300.graphics.FadeState;

/*
*       Purpose:
*           so we can use an abstract fade state controller
*       Usage:
*           start fade and end fade
*/

public interface FadeListener {

    // should be called when ready to start fade
    void fadeStarted(FadeState fadeState, Launcher launcher);

    // should be called when fade is complete
    void fadeCompleted(FadeState fadeState);

}

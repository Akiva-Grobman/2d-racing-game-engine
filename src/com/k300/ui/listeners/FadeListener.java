package com.k300.ui.listeners;

import com.k300.Launcher;
import com.k300.graphics.FadeState;

public interface FadeListener {

    void fadeStarted(FadeState fadeState, Launcher launcher);
    void fadeCompleted(FadeState fadeState);

}

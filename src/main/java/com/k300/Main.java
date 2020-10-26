package com.k300;

import com.k300.utils.configarations.Config;

public class Main {

    public static void main(String[] args) {
        System.out.println(Config.getZoomInFactor());
        Launcher launcher = new Launcher();
        launcher.start();
    }

}

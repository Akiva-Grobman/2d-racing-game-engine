package com.k300.states;

public class StateManager {


    private static volatile StateManager singletonStateManagerInstance;
    private State currentState;

    private StateManager() {
        currentState = null;
    }

    private static void handleInstance() {
        if(!isInstantiated()) {
            instantiate();
        }
    }

    private static boolean isInstantiated() {
        return singletonStateManagerInstance != null;
    }

    private static void instantiate() {
        singletonStateManagerInstance = new StateManager();
    }

    public static State getCurrentState() {
        handleInstance();
        return singletonStateManagerInstance.currentState;
    }

    public static void setCurrentState(State currentState) {
        handleInstance();
        singletonStateManagerInstance.currentState = currentState;
    }

}
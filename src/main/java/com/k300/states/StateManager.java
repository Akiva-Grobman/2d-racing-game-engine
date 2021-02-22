package com.k300.states;

/*
*       Quick explanation:
*           the launcher will determine what state to render based on what this class is storing in the currentState variable.
*       Implementation Style:
*           this class implements the singleton style
*       Contains:
*           the static class instance, witch contains the current state
*/

public class StateManager {

    // the static instance of this class
    private static volatile StateManager singletonStateManagerInstance;
    // the current state
    private State currentState;

    // only initialization option (from within this class only)
    private StateManager() {
        currentState = null;
    }

    // this will initialize the static instance of the class object if it hasn't been initialized (essentially this will only happen once)
    private static void handleInstance() {
        // if static class object instance isn't initialized
        if(singletonStateManagerInstance == null) {
            // initialize class object instance
            singletonStateManagerInstance = new StateManager();
        }
    }

    // current state accessor
    public static State getCurrentState() {
        // will make sure class object is instantiated
        handleInstance();
        return singletonStateManagerInstance.currentState;
    }

    // current state modifier
    public static void setCurrentState(State currentState) {

        // will make sure class object is instantiated
        handleInstance();
        singletonStateManagerInstance.currentState = currentState;
    }

}
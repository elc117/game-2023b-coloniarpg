package com.coloniarpg.game.screens;

public class ScreenTransition {
    private static final float FADE_TIME = 0.75f;
    private float timeElapsed = 0;
    private boolean isFinished = false;

    public void update(float delta) {
        if(!isFinished) {
            timeElapsed += delta;
            if(timeElapsed >= FADE_TIME) {
                isFinished = true;
            }
        }
    }

    public float getAlpha() {
        return timeElapsed / FADE_TIME;
    }

    public boolean isFinished() {
        return isFinished;
    }
}

package com.coloniarpg.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class FadeScreen extends ScreenAdapter {
    public enum FadeType { IN, OUT}

    public static class FadeInfo {
        public final FadeType type;
        public final Color color;
        public final Interpolation interpolation;
        public final float duration;

        public FadeInfo(FadeType type, Color color, Interpolation interpolation, float duration) {
            this.type = type;
            this.color = color;
            this.interpolation = interpolation;
            this.duration = duration;
        }
    }

    private final FadeInfo fade;
    private Screen screen;
    private Screen next;
    private final Game game;
    private final ShapeRenderer shapeRenderer;
    private final Camera camera;
    private float elapsed;

    public FadeScreen(Game game, FadeInfo fade, Screen screen, Screen next) {
        this.game = game;
        this.fade = fade;
        this.screen = screen;
        this.next = next;
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth())
    }

}

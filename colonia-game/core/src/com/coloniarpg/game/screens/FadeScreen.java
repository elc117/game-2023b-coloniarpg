package com.coloniarpg.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
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
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0f);
        camera.update();
    }

    private void renderFade() {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        float f = Math.min(1f, elapsed / fade.duration);
        float opacity = fade.type == FadeType.OUT ? fade.interpolation.apply(f) : 1f - fade.interpolation.apply(f);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(fade.color.r, fade.color.g, fade.color.b, opacity);
        shapeRenderer.rect(0, 0, camera.viewportWidth, camera.viewportHeight);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    public void render (float delta) {
        if (screen != null) {
            elapsed += delta;
            if (elapsed >= fade.duration) {
                if (next != null) {
                    game.setScreen(next);
                    screen.dispose();
                    screen = null;
                } else {
                    game.setScreen(screen);
                }
            }
        }
        Gdx.gl.glClearColor(0, 0, 0, 0);
        if (screen != null) screen.render(delta);
        renderFade();
    }

}

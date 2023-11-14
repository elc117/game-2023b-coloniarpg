package com.coloniarpg.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.coloniarpg.game.AssetUtils;

public class BattleScreen1 implements Screen {
    public static float windowWidth;
    public static float windowHeight;
    
    private SpriteBatch batch;
    private Texture background;
    private Stage stage;
    private Game game;

    public BattleScreen1(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        AssetUtils.initAssets();   
        batch = new SpriteBatch();
        background = AssetUtils.backgroundBattle;
        stage = new Stage(new ScreenViewport());
        windowWidth = Gdx.graphics.getWidth();
        windowHeight = Gdx.graphics.getHeight();

        float avatarX = 120;
        float avatarY = 120;
        float enemyX = windowWidth - 220;
        float enemyY = windowHeight - 420;

        Image avatar = new Image(new TextureRegionDrawable(new TextureRegion(AssetUtils.avatar)));
        avatar.setPosition(avatarX, avatarY);
        
        Image enemy = new Image(new TextureRegionDrawable(new TextureRegion(AssetUtils.enemy)));
        enemy.setPosition(enemyX, enemyY);

        stage.addActor(avatar);
        stage.addActor(enemy);
        
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        AssetUtils.backgroundBattle.dispose();
        AssetUtils.avatar.dispose();
        AssetUtils.enemy.dispose();
        batch.dispose();
    }
}

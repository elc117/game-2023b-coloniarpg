package com.coloniarpg.game.screens;

//import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.coloniarpg.game.AssetUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;    

// Classe que cria a tela de menu principal
// Com um botão de play e O titulo do jogo no centro da tela
public class MainMenuScreen implements Screen {
    public static float windowWidth;
    public static float windowHeight;

    private SpriteBatch batch;
    private Texture background;
    private Stage stage;
    private Game game;

    public MainMenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        AssetUtils.initAssets();
        batch = new SpriteBatch();
        background = AssetUtils.backgroundMenu;
        stage = new Stage(new ScreenViewport());
        windowWidth = Gdx.graphics.getWidth();
        windowHeight = Gdx.graphics.getHeight();

        float playButtonX = windowWidth / 2 - AssetUtils.playButton.getWidth() / 2;
        float playButtonY = 25;
        float titleX = windowWidth / 2 - AssetUtils.title.getWidth() / 2;
        float titleY = windowHeight - AssetUtils.title.getHeight() - 25;

        TextureRegionDrawable playButtonDrawable = new TextureRegionDrawable(new TextureRegion(AssetUtils.playButton));
        ImageButton playButton = new ImageButton(playButtonDrawable);
        playButton.setPosition(playButtonX, playButtonY);
        playButton.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                //game.setScreen(new SelectMapScreen(game));
                return true;
            }
        });

        Image title = new Image(new TextureRegionDrawable(new TextureRegion(AssetUtils.title)));
        title.setPosition(titleX, titleY);

        stage.addActor(title);
        stage.addActor(playButton);

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
        AssetUtils.title.dispose();
        AssetUtils.playButton.dispose();
        AssetUtils.backgroundMenu.dispose();
        batch.dispose();
    }
}
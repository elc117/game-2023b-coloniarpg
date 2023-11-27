package com.coloniarpg.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.coloniarpg.game.AssetUtils;

public class TextScreen implements Screen {
    // Variáveis estáticas para armazenar a largura e altura da janela
    public static float windowWidth;
    public static float windowHeight;

    // Variáveis privadas para armazenar o batch, background, stage e game
    private SpriteBatch batch;
    private Texture background;
    private Stage stage;
    private Game game;
    private FadeScreen fadeScreen;
    private boolean dead;

    // Construtor da tela de texto
    public TextScreen(Game game, boolean dead) {
        this.game = game;
        this.dead = dead;
    }

    // Método que inicia a transição para a tela de seleção de mapa
    private void startSelectMapScreenTransition() {
        SelectMapScreen SelectMapScreenInstance = new SelectMapScreen(game);
        FadeScreen.FadeInfo fadeOut = new FadeScreen.FadeInfo(FadeScreen.FadeType.OUT, Color.BLACK, Interpolation.smoother, 1f);
        fadeScreen = new FadeScreen(game, fadeOut, this, SelectMapScreenInstance);
        game.setScreen(fadeScreen);
    }

    // Método que mostra a tela
    @Override
    public void show() {
        AssetUtils.initAssets();
        batch = new SpriteBatch();
        background = AssetUtils.backgroundMenu;
        stage = new Stage(new ScreenViewport());
        windowWidth = Gdx.graphics.getWidth();
        windowHeight = Gdx.graphics.getHeight();

        // Posições do botão de play
        float playButtonX = windowWidth / 2 - AssetUtils.playButton.getWidth() / 2;
        float playButtonY = 85;

        if (dead) { // Se o jogador morreu, cria o texto de perdeu
            Image text = new Image(new TextureRegionDrawable(AssetUtils.perdeuText));
            text.setPosition(windowWidth / 2 - AssetUtils.perdeuText.getWidth() / 2, windowHeight / 2 - AssetUtils.perdeuText.getHeight() / 2 + 110);
            text.setScale(3);
            text.setOrigin(text.getWidth() / 2, text.getHeight() / 2);
            stage.addActor(text);

        } else { // Se o jogador venceu, cria o texto de venceu
            Image text = new Image(new TextureRegionDrawable(AssetUtils.venceuText));
            text.setPosition(windowWidth / 2 - AssetUtils.venceuText.getWidth() / 2, windowHeight / 2 - AssetUtils.venceuText.getHeight() / 2 + 110);
            text.setScale(3);
            text.setOrigin(text.getWidth() / 2, text.getHeight() / 2);
            stage.addActor(text);
        }

        // Cria o botão de play
        TextureRegionDrawable playButtonDrawable = new TextureRegionDrawable(new TextureRegion(AssetUtils.playButton));
        TextureRegionDrawable playButtonHighlightDrawable = new TextureRegionDrawable(new TextureRegion(AssetUtils.playButtonHighlight));
        final ImageButton playButton = new ImageButton(playButtonDrawable);

        playButton.setPosition(playButtonX, playButtonY);
        playButton.getStyle().imageOver = playButtonHighlightDrawable;
        playButton.getStyle().imageUp = playButtonDrawable;
        playButton.addListener(new InputListener(){
            // Método que muda a escala do botão de play quando o mouse está sobre ele
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                playButton.getImage().setScale(1.15f);
                playButton.getImage().setOrigin(playButton.getImage().getWidth() / 2, playButton.getImage().getHeight() / 2);
            }
            
            // Método que muda a escala do botão de play quando o mouse sai dele
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                playButton.getImage().setScale(1f);
            }

            // Método que muda a tela para a tela de seleção de mapa quando o botão de play é clicado
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // game.setScreen(new SelectMapScreen(game));
                startSelectMapScreenTransition();

                return true;
            }
        });
        stage.addActor(playButton);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); // Limpa a tela
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Limpa o buffer de cores

        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    // Método que redimensiona a tela
    @Override
    public void resize(int width, int height) {
    }

    // Método que pausa a tela
    @Override
    public void pause() {
    }

    // Método que retoma a tela
    @Override
    public void resume() {
    }

    // Método que esconde a tela
    @Override
    public void hide() {
    }

    // Método que libera a memória dos objetos que não são mais utilizados
    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
    }
}

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

// Classe que cria a tela de menu de seleção de level
// Com um titulo no topo da tela e 3 botões de level
// Ao clicar em um dos botões, a tela de jogo é chamada
// Os elementos presentes na tela são carregados em AssetUtils.java
public class SelectMapScreen implements Screen {
    // Variáveis estáticas para armazenar a largura e altura da janela
    public static float windowWidth;
    public static float windowHeight;

    // Variáveis privadas para armazenar o batch, background, stage e game
    private SpriteBatch batch;
    private Texture background;
    private Stage stage;
    private Game game;
    private FadeScreen fadeScreen;

    // Construtor da classe
    public SelectMapScreen(Game game) {
        this.game = game;
    }

    // Método que inicia a transição de tela
    private void startScreenBattle1Transition() {
        BattleScreen1 BattleScreenInstance = new BattleScreen1(game);
        FadeScreen.FadeInfo fadeOut = new FadeScreen.FadeInfo(FadeScreen.FadeType.OUT, Color.BLACK, Interpolation.smoother, 1.5f);
        fadeScreen = new FadeScreen(game, fadeOut, this, BattleScreenInstance);
        game.setScreen(fadeScreen);
    }

    // Método que inicia a transição de tela
    private void startScreenBattle2Transition() {
        BattleScreen1 BattleScreenInstance = new BattleScreen1(game);
        FadeScreen.FadeInfo fadeOut = new FadeScreen.FadeInfo(FadeScreen.FadeType.OUT, Color.BLACK, Interpolation.smoother, 1.5f);
        fadeScreen = new FadeScreen(game, fadeOut, this, BattleScreenInstance);
        game.setScreen(fadeScreen);
    }

    // Método que inicia a transição de tela
    private void startScreenBattle3Transition() {
        BattleScreen1 BattleScreenInstance = new BattleScreen1(game);
        FadeScreen.FadeInfo fadeOut = new FadeScreen.FadeInfo(FadeScreen.FadeType.OUT, Color.BLACK, Interpolation.smoother, 1.5f);
        fadeScreen = new FadeScreen(game, fadeOut, this, BattleScreenInstance);
        game.setScreen(fadeScreen);
    }

    // Método que inicializa os elementos da tela
    @Override
    public void show() {
        AssetUtils.initAssets();
        batch = new SpriteBatch();
        background = AssetUtils.backgroundMenu;
        stage = new Stage(new ScreenViewport());
        windowWidth = Gdx.graphics.getWidth();
        windowHeight = Gdx.graphics.getHeight();

        // Calcula a posição dos elementos da tela de seleção de level
        // e atribui os valores de scale para os botões
        final float scaleGrow = 1.1f;
        final float scaleNormal = 1f;
        float textSelectLevelX = windowWidth / 2 - AssetUtils.selectLevelText.getWidth() / 2;
        float textSelectLevelY = windowHeight - AssetUtils.selectLevelText.getHeight() - 20;
        float levelButton1X = 20;
        float levelButton1Y = windowHeight / 2 - AssetUtils.levelButton1.getHeight() / 2;
        float levelButton2X = 40 + AssetUtils.levelButton1.getWidth();
        float levelButton2Y = windowHeight / 2 - AssetUtils.levelButton2.getHeight() / 2;
        float levelButton3X = 60 + AssetUtils.levelButton1.getWidth() * 2;
        float levelButton3Y = windowHeight / 2 - AssetUtils.levelButton3.getHeight() / 2;

        // Cria o botão de level 1
        TextureRegionDrawable levelButton1Drawable = new TextureRegionDrawable(new TextureRegion(AssetUtils.levelButton1));
        final ImageButton levelButton1 = new ImageButton(levelButton1Drawable);

        levelButton1.setPosition(levelButton1X, levelButton1Y);
        levelButton1.addListener(new InputListener(){
            // Método que muda a escala do botão de level 1 quando o mouse está sobre ele
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                levelButton1.getImage().setScale(scaleGrow);
                levelButton1.getImage().setOrigin(levelButton1.getImage().getWidth() / 2, levelButton1.getImage().getHeight() / 2);
            }

            // Método que muda a escala do botão de level 1 quando o mouse sai de cima dele
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                levelButton1.getImage().setScale(scaleNormal);;
            }

            // Método que muda a tela para a tela de jogo quando o botão de level 1 é clicado
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                startScreenBattle1Transition();
                return true;
            }
        });

        // Cria o botão de level 2
        TextureRegionDrawable levelButton2Drawable = new TextureRegionDrawable(new TextureRegion(AssetUtils.levelButton2));
        final ImageButton levelButton2 = new ImageButton(levelButton2Drawable);

        levelButton2.setPosition(levelButton2X, levelButton2Y);
        levelButton2.addListener(new InputListener(){
            // Método que muda a escala do botão de level 2 quando o mouse está sobre ele
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                levelButton2.getImage().setScale(scaleGrow);
                levelButton2.getImage().setOrigin(levelButton2.getImage().getWidth() / 2, levelButton2.getImage().getHeight() / 2);
            }

            // Método que muda a escala do botão de level 2 quando o mouse sai de cima dele
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                levelButton2.getImage().setScale(scaleNormal);;
            }

            // Método que muda a tela para a tela de jogo quando o botão de level 2 é clicado
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                startScreenBattle2Transition();
                return true;
            }
        });

        // Cria o botão de level 3
        TextureRegionDrawable levelButton3Drawable = new TextureRegionDrawable(new TextureRegion(AssetUtils.levelButton3));
        final ImageButton levelButton3 = new ImageButton(levelButton3Drawable);

        levelButton3.setPosition(levelButton3X, levelButton3Y);
        levelButton3.addListener(new InputListener(){
            // Método que muda a escala do botão de level 3 quando o mouse está sobre ele
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                levelButton3.getImage().setScale(scaleGrow);
                levelButton3.getImage().setOrigin(levelButton3.getImage().getWidth() / 2, levelButton3.getImage().getHeight() / 2);
            }

            // Método que muda a escala do botão de level 3 quando o mouse sai de cima dele
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                levelButton3.getImage().setScale(scaleNormal);;
            }

            // Método que muda a tela para a tela de jogo quando o botão de level 3 é clicado
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                startScreenBattle3Transition();
                return true;
            }
        });

        // Cria o texto de seleção de level
        Image textSelectLevel = new Image(new TextureRegionDrawable(AssetUtils.selectLevelText));
        textSelectLevel.setPosition(textSelectLevelX, textSelectLevelY);

        stage.addActor(textSelectLevel);
        stage.addActor(levelButton1);
        stage.addActor(levelButton2);
        stage.addActor(levelButton3);

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

    // Método que descarta os elementos da tela
    @Override
    public void dispose() {
        AssetUtils.selectLevelText.dispose();
        AssetUtils.levelButton1.dispose();
        AssetUtils.levelButton2.dispose();
        AssetUtils.levelButton3.dispose();
        AssetUtils.backgroundMenu.dispose();
    }
}

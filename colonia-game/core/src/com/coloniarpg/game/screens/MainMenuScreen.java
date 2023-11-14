package com.coloniarpg.game.screens;

//import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.coloniarpg.game.AssetUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Image;    
import com.badlogic.gdx.scenes.scene2d.Actor;

// Classe que cria a tela de menu principal
// Com um botão de play e O titulo do jogo no centro da tela
// Ao clicar no botão de play, a tela de seleção de mapa é chamada
// Os elementos presentes na tela são carregados em AssetUtils.java
public class MainMenuScreen implements Screen {
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
    public MainMenuScreen(Game game) {
        this.game = game;
    }

    private void startScreenTransition() {
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

        MainMenuScreen MainMenuScreenInstance = this;
        SelectMapScreen SelectMapScreenInstance = new SelectMapScreen(game);

        FadeScreen.FadeInfo fadeOut = new FadeScreen.FadeInfo(FadeScreen.FadeType.OUT, Color.WHITE, Interpolation.smoother, 2f);
        FadeScreen.FadeInfo fadeIn = new FadeScreen.FadeInfo(FadeScreen.FadeType.IN, Color.WHITE, Interpolation.smoother, 2f);

        fadeScreen = new FadeScreen(game, fadeOut, MainMenuScreenInstance, new FadeScreen(game, fadeIn, SelectMapScreenInstance, null));

        // Calcula a posição do botão de play e do titulo
        float playButtonX = windowWidth / 2 - AssetUtils.playButton.getWidth() / 2;
        float playButtonY = 105;
        float titleX = windowWidth / 2 - AssetUtils.title.getWidth() / 2;
        float titleY = windowHeight - AssetUtils.title.getHeight();

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
                startScreenTransition();
                return true;
            }
        });

        // Cria o titulo do jogo
        Image title = new Image(new TextureRegionDrawable(new TextureRegion(AssetUtils.title)));
        title.setPosition(titleX, titleY);
        title.setScale(0.85f);
        title.setOrigin(title.getWidth() / 2, title.getHeight() / 2);

        stage.addActor(title);
        stage.addActor(playButton);

        Gdx.input.setInputProcessor(stage);
    }


    // Método que renderiza os elementos da tela
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
    public void resize(int width, int height) { }

    // Método que pausa a tela
    @Override
    public void pause() { }

    // Método que retoma a tela
    @Override
    public void resume() { }

    // Método que esconde a tela
    @Override
    public void hide() { }

    // Método que descarta os elementos da tela
    @Override
    public void dispose() {
        AssetUtils.title.dispose();
        AssetUtils.playButton.dispose();
        AssetUtils.playButtonHighlight.dispose();
        AssetUtils.backgroundMenu.dispose();
        batch.dispose();
    }
}
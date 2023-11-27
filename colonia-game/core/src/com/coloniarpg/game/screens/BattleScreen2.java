package com.coloniarpg.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.coloniarpg.game.AssetUtils;

public class BattleScreen2 implements Screen {
    // Variáveis estáticas para armazenar a largura e altura da janela
    public static float windowWidth;
    public static float windowHeight;
    
    // Variáveis privadas para armazenar o batch, background, stage e game
    private SpriteBatch batch;
    private Texture background;
    private Stage stage;
    private Game game;
    private FadeScreen fadeScreen;

    // Variáveis estáticas para armazenar a vida do avatar e do inimigo e o index da pergunta
    public static int vidaAvatar = 3;
    public static int vidaInimigo = 3;
    public static int indexPergunta = 0;

    public BattleScreen2(Game game) {
        this.game = game;
    }

    // Método que inicia a transição de tela de questão
    private void startQuestionScreenTransition() {
        QuestionScreen1 QuestionScreenInstance = new QuestionScreen1(game, 2);
        FadeScreen.FadeInfo fadeOut = new FadeScreen.FadeInfo(FadeScreen.FadeType.OUT, Color.BLACK, Interpolation.smoother, 1f);
        fadeScreen = new FadeScreen(game, fadeOut, this, QuestionScreenInstance);
        game.setScreen(fadeScreen);
    }

    // Métodos que iniciam a transição de tela de texto de morto
    private void startTextScreenTransitionDead() {
        TextScreen TextScreenInstance = new TextScreen(game, true);
        FadeScreen.FadeInfo fadeOut = new FadeScreen.FadeInfo(FadeScreen.FadeType.OUT, Color.BLACK, Interpolation.smoother, 1f);
        fadeScreen = new FadeScreen(game, fadeOut, this, TextScreenInstance);
        game.setScreen(fadeScreen);
    }

    // Métodos que iniciam a transição de tela de texto de vivo
    private void startTextScreenTransitionAlive() {
        TextScreen TextScreenInstance = new TextScreen(game, false);
        FadeScreen.FadeInfo fadeOut = new FadeScreen.FadeInfo(FadeScreen.FadeType.OUT, Color.BLACK, Interpolation.smoother, 1f);
        fadeScreen = new FadeScreen(game, fadeOut, this, TextScreenInstance);
        game.setScreen(fadeScreen);
    }

    @Override
    public void show() {
        AssetUtils.initAssets();   
        batch = new SpriteBatch();
        background = AssetUtils.backgroundBattle2;
        stage = new Stage(new ScreenViewport());
        windowWidth = Gdx.graphics.getWidth();
        windowHeight = Gdx.graphics.getHeight();

        // Cria o estilo do texto
        BitmapFont font = new BitmapFont();
        font.getData().setScale(3);
        Color color = Color.WHITE;
        LabelStyle style = new LabelStyle(font, color);

        // Calcula a posição do avatar, inimigo e botão de ataque
        float avatarX = 0;
        float avatarY = 0;
        float enemyX = windowWidth - 380;
        float enemyY = windowHeight - 500;
        float attackButtonX = windowWidth / 2 - AssetUtils.attackButton.getWidth() / 2;
        float attackButtonY = 40;

        // Cria o botão de ataque
        TextureRegionDrawable attackButtonDrawable = new TextureRegionDrawable(new TextureRegion(AssetUtils.attackButton));
        TextureRegionDrawable attackButtonHighlightDrawable = new TextureRegionDrawable(new TextureRegion(AssetUtils.attackButtonHighlight));
        final ImageButton attackButton = new ImageButton(attackButtonDrawable);
        attackButton.setPosition(attackButtonX, attackButtonY);
        attackButton.getStyle().imageOver = attackButtonHighlightDrawable;
        attackButton.getStyle().imageUp = attackButtonDrawable;
        attackButton.addListener(new InputListener() {
            // Método que muda a escala do botão de ataque quando o mouse está sobre ele
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                attackButton.getImage().setScale(1.15f);
                attackButton.getImage().setOrigin(attackButton.getImage().getWidth() / 2, attackButton.getImage().getHeight() / 2);
            }
            
            // Método que muda a escala do botão de ataque quando o mouse sai dele
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                attackButton.getImage().setScale(1f);
            }

            // Método que inicia a transição de tela quando o botão de ataque é clicado
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                startQuestionScreenTransition();
                
                return true;
            }   
        });

        // Cria o avatar e o inimigo juntamente com sua vida
        Image heart1 = new Image(AssetUtils.heart);
        Label vidaAvatarLabel = new Label("" + vidaAvatar, style);

        Group vidaAvatarGroup = new Group();
        vidaAvatarGroup.addActor(heart1);
        vidaAvatarGroup.addActor(vidaAvatarLabel);
        
        vidaAvatarLabel.setPosition(heart1.getWidth() / 2 - vidaAvatarLabel.getWidth() / 2, heart1.getHeight() / 2 - vidaAvatarLabel.getHeight() / 2);
        vidaAvatarGroup.setPosition(avatarX + 40, avatarY);

        Image avatar = new Image(new TextureRegionDrawable(new TextureRegion(AssetUtils.avatar)));
        avatar.setPosition(avatarX, avatarY);
        avatar.setScale(2f);
        

        Image heart2 = new Image(AssetUtils.heart);
        Label vidaInimigoLabel = new Label("" + vidaInimigo, style);

        Group vidaInimigoGroup = new Group();
        vidaInimigoGroup.addActor(heart2);
        vidaInimigoGroup.addActor(vidaInimigoLabel);

        vidaInimigoLabel.setPosition(heart2.getWidth() / 2 - vidaInimigoLabel.getWidth() / 2, heart2.getHeight() / 2 - vidaInimigoLabel.getHeight() / 2);
        vidaInimigoGroup.setPosition(enemyX, enemyY);

        Image enemy = new Image(new TextureRegionDrawable(new TextureRegion(AssetUtils.enemyJacare)));
        enemy.setPosition(enemyX, enemyY);

        // Adiciona os elementos na tela
        stage.addActor(avatar);
        stage.addActor(enemy);
        stage.addActor(attackButton);
        stage.addActor(vidaAvatarGroup);
        stage.addActor(vidaInimigoGroup);
        
        Gdx.input.setInputProcessor(stage);

        // Verifica se o avatar ou o inimigo morreu
        if (vidaAvatar == 0) {
            vidaAvatar = 3;
            vidaInimigo = 3;
            indexPergunta = 0;
            startTextScreenTransitionDead();

        } else if (vidaInimigo == 0) {
            vidaAvatar = 3;
            vidaInimigo = 3;
            indexPergunta = 0;
            startTextScreenTransitionAlive();
        }
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
        stage.dispose();
        batch.dispose();
    }
}
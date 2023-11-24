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
    public static float windowWidth;
    public static float windowHeight;
    
    private SpriteBatch batch;
    private Texture background;
    private Stage stage;
    private Game game;
    private FadeScreen fadeScreen;

    public static int vidaAvatar = 3;
    public static int vidaInimigo = 3;
    public static int indexPergunta = 0;

    public BattleScreen2(Game game) {
        this.game = game;
    }

    private void startQuestionScreenTransition() {
        QuestionScreen1 QuestionScreenInstance = new QuestionScreen1(game);
        FadeScreen.FadeInfo fadeOut = new FadeScreen.FadeInfo(FadeScreen.FadeType.OUT, Color.BLACK, Interpolation.smoother, 1f);
        fadeScreen = new FadeScreen(game, fadeOut, this, QuestionScreenInstance);
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

        BitmapFont font = new BitmapFont();
        font.getData().setScale(3);
        Color color = Color.WHITE;
        LabelStyle style = new LabelStyle(font, color);

        float avatarX = 30;
        float avatarY = 30;
        float enemyX = windowWidth - 380;
        float enemyY = windowHeight - 500;
        float attackButtonX = windowWidth / 2 - AssetUtils.attackButton.getWidth() / 2;
        float attackButtonY = 40;

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

        Image heart1 = new Image(AssetUtils.heart);
        Label vidaAvatarLabel = new Label("" + vidaAvatar, style);

        Group vidaAvatarGroup = new Group();
        vidaAvatarGroup.addActor(heart1);
        vidaAvatarGroup.addActor(vidaAvatarLabel);
        
        vidaAvatarLabel.setPosition(heart1.getWidth() / 2 - vidaAvatarLabel.getWidth() / 2, heart1.getHeight() / 2 - vidaAvatarLabel.getHeight() / 2);
        vidaAvatarGroup.setPosition(avatarX + 40, avatarY);

        Image avatar = new Image(new TextureRegionDrawable(new TextureRegion(AssetUtils.avatar)));
        avatar.setPosition(avatarX, avatarY);
        

        Image heart2 = new Image(AssetUtils.heart);
        Label vidaInimigoLabel = new Label("" + vidaInimigo, style);

        Group vidaInimigoGroup = new Group();
        vidaInimigoGroup.addActor(heart2);
        vidaInimigoGroup.addActor(vidaInimigoLabel);

        vidaInimigoLabel.setPosition(heart2.getWidth() / 2 - vidaInimigoLabel.getWidth() / 2, heart2.getHeight() / 2 - vidaInimigoLabel.getHeight() / 2);
        vidaInimigoGroup.setPosition(enemyX, enemyY);

        Image enemy = new Image(new TextureRegionDrawable(new TextureRegion(AssetUtils.enemyJacare)));
        enemy.setPosition(enemyX, enemyY);

        stage.addActor(avatar);
        stage.addActor(enemy);
        stage.addActor(attackButton);
        stage.addActor(vidaAvatarGroup);
        stage.addActor(vidaInimigoGroup);
        
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
        AssetUtils.backgroundBattle1.dispose();
        //AssetUtils.avatar.dispose();
        AssetUtils.enemyJacare.dispose();
        batch.dispose();
    }
}

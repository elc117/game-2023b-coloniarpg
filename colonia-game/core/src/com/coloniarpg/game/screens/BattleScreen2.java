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
        font.getData().setScale(2);
        Color color = Color.BLACK;
        LabelStyle style = new LabelStyle(font, color);

        float avatarX = 10;
        float avatarY = 10;
        float enemyX = windowWidth - 380;
        float enemyY = windowHeight - 500;
        float battleButtonX = 150;
        float battleButtonY = windowHeight - 200;

        TextureRegionDrawable battleButtonDrawable = new TextureRegionDrawable(new TextureRegion(AssetUtils.answerButton));
        ImageButton battleButton = new ImageButton(battleButtonDrawable);
        battleButton.setPosition(battleButtonX, battleButtonY);
        battleButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                startQuestionScreenTransition();
                
                return true;
            }   
        });

        Label vidaAvatarLabel = new Label("VidaA: " + vidaAvatar, style);
        vidaAvatarLabel.setPosition(avatarX / 2 + 20, avatarY / 2 + 140);
        Image avatar = new Image(new TextureRegionDrawable(new TextureRegion(AssetUtils.avatar)));
        avatar.setPosition(avatarX, avatarY);
        
        Label vidaInimigoLabel = new Label("VidaI: " + vidaInimigo, style);
        vidaInimigoLabel.setPosition(enemyX + 55, enemyY + 290);
        Image enemy = new Image(new TextureRegionDrawable(new TextureRegion(AssetUtils.enemyJacare)));
        enemy.setPosition(enemyX, enemyY);

        stage.addActor(battleButton);
        stage.addActor(vidaAvatarLabel);
        stage.addActor(vidaInimigoLabel);
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
        AssetUtils.backgroundBattle1.dispose();
        AssetUtils.avatar.dispose();
        AssetUtils.enemyJacare.dispose();
        batch.dispose();
    }
}

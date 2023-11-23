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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.coloniarpg.game.AssetUtils;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

public class QuestionScreen1 implements Screen {
    public static float windowWidth;
    public static float windowHeight;

    private SpriteBatch batch;
    private Texture background;
    private Stage stage;
    private Game game;
    private FadeScreen fadeScreen;

    private String[] questions;
    private String[] answers;
    private String[] correctAnswers;
    int i = 0;

    public QuestionScreen1(Game game) {
        this.game = game;

        questions = new String[] {
                "Question 1",
                "Question 2",
                "Question 3",
                "Question 4",
                "Question 5",
        };

        answers = new String[] {
                "Answer 1",
                "Answer 2",
                "Answer 3",
                "Answer 4",

                "Answer 11",
                "Answer 22",
                "Answer 33",
                "Answer 44",

                "Answer 111",
                "Answer 222",
                "Answer 333",
                "Answer 444",

                "Answer 1",
                "Answer 2",
                "Answer 3",
                "Answer 4",

                "Answer 1",
                "Answer 2",
                "Answer 3",
                "Answer 4",
        };

        correctAnswers = new String[] {
                "Answer 1",
                "Answer 22",
                "Answer 333",
                "Answer 4",
                "Answer 1",
        };

    }

    private void startScreenBattle1Transition() {
        BattleScreen1 BattleScreenInstance = new BattleScreen1(game);
        FadeScreen.FadeInfo fadeOut = new FadeScreen.FadeInfo(FadeScreen.FadeType.OUT, Color.BLACK, Interpolation.smoother, 1f);
        fadeScreen = new FadeScreen(game, fadeOut, this, BattleScreenInstance);
        game.setScreen(fadeScreen);
    }

    @Override
    public void show() {
        AssetUtils.initAssets();
        batch = new SpriteBatch();
        background = AssetUtils.backgroundMenu;
        stage = new Stage(new ScreenViewport());
        windowWidth = Gdx.graphics.getWidth();
        windowHeight = Gdx.graphics.getHeight();

        BitmapFont font = new BitmapFont();
        font.getData().setScale(3);
        Color color = Color.WHITE;
        LabelStyle style = new LabelStyle(font, color);

        //Drawable upDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("answer_button.png")));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        //textButtonStyle.up = upDrawable;
        //textButtonStyle.down = upDrawable;
        textButtonStyle.font = font;

        // Label tittleLabel = new Label("Perguntas", style);
        // tittleLabel.setPosition(windowWidth / 2, windowHeight - 20);
        // stage.addActor(tittleLabel);
        final int FinalI = BattleScreen1.indexPergunta;
        Label questionLabel = new Label(questions[FinalI], style);
        questionLabel.setPosition(windowWidth / 2 - 50, windowHeight - 60);
        stage.addActor(questionLabel);

        int height = 300;
        for (int j = 0; j < 4; j++) {
            final int FinalJ = j;

            TextButton answerButton = new TextButton(answers[FinalI * 4 + FinalJ], textButtonStyle);
            answerButton.setPosition(windowWidth / 2 - 50/* AssetUtils.answerButton.getWidth() / 2*/, windowHeight - height);
            answerButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (answers[FinalI * 4 + FinalJ].equals(correctAnswers[FinalI])) {
                        System.out.println("Acertou");
                        BattleScreen1.vidaInimigo--;
                    } else {
                        System.out.println("Errou");
                        BattleScreen1.vidaAvatar--;
                    }
                    BattleScreen1.indexPergunta++;
                    startScreenBattle1Transition();
                }
            });
            stage.addActor(answerButton);

            height += 100;
        }
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
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        // AssetUtils.backgroundBattle1.dispose();
        // AssetUtils.avatar.dispose();
        // AssetUtils.enemyDino.dispose();
        // batch.dispose();
    }
}

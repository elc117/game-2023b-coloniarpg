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
    // Variáveis estáticas para armazenar a largura e altura da janela
    public static float windowWidth;
    public static float windowHeight;

    // Variáveis privadas para armazenar o batch, background, stage e game
    private SpriteBatch batch;
    private Texture background;
    private Stage stage;
    private Game game;
    private FadeScreen fadeScreen;
    private int battleScreen;

    // Variáveis privadas para armazenar as perguntas, respostas e respostas corretas
    private String[] questions;
    private String[] answers;
    private String[] correctAnswers;

    // Construtor da classe
    public QuestionScreen1(Game game, int battleScreen) {
        this.game = game;
        this.battleScreen = battleScreen;

        if (battleScreen == 1) {
            questions = new String[] {
                "Qual é o nome do rio que corta a cidade de São João do Polêsine?",
                "Em que ano São João do Polêsine foi emancipada?",
                "Qual é a principal atração turística de São João do Polêsine?",
                "Qual é o prato típico de São João do Polêsine?",
                "Qual é a principal atividade econômica de São João do Polêsine?"
            };

            answers = new String[] {
                "Rio Jacuí",
                "Rio Uruguai",
                "Rio Ibicuí",
                "Rio Paraná",

                "1991",
                "1992",
                "1993",
                "1994",

                "Igreja Matriz São João Batista",
                "Museu Municipal",
                "Parque Municipal",
                "Santuário Nossa Senhora de Fátima",

                "Pizza",
                "Xis",
                "Galeto à moda gaúcha",
                "Carne de porco assada",

                "Agricultura",
                "Pecuária",
                "Turismo",
                "Indústria",
            };

            correctAnswers = new String[] {
                "Rio Ibicuí",
                "1992",
                "Igreja Matriz São João Batista",
                "Galeto à moda gaúcha",
                "Agricultura",
            };

        } else if (battleScreen == 2) {
            questions = new String[] {
                "Em que ano Pinhal Grande foi emancipada?",
                "Qual é a principal atividade econômica de Pinhal Grande?",
                "Qual é a principal atração turística de Pinhal Grande?",
                "Qual é o nome do santo padroeiro de Pinhal Grande?",
                "Qual é o rio que forma a divisa natural de Pinhal Grande?"
            };

            answers = new String[] {
                    "1990",
                    "1991",
                    "1992",
                    "1993",

                    "Agricultura",
                    "Pecuária",
                    "Turismo",
                    "Indústria",

                    "Festa do trabalhador",
                    "Festa do Colono",
                    "Festa do Pinhão",
                    "Festa do Peão",

                    "São José",
                    "São João",
                    "São Pedro",
                    "São Paulo",

                    "Rio Ibicuí",
                    "Rio Uruguai",
                    "Rio Jacuí",
                    "Rio Paraná"
            };

            correctAnswers = new String[] {
                    "1992",
                    "Agricultura",
                    "Festa do Pinhão",
                    "São José",
                    "Rio Jacuí",
            };
        }

    }

    private void startScreenBattle1Transition() {
        BattleScreen1 BattleScreenInstance = new BattleScreen1(game);
        FadeScreen.FadeInfo fadeOut = new FadeScreen.FadeInfo(FadeScreen.FadeType.OUT, Color.BLACK, Interpolation.smoother, 1f);
        fadeScreen = new FadeScreen(game, fadeOut, this, BattleScreenInstance);
        game.setScreen(fadeScreen);
    }

    private void startScreenBattle2Transition() {
        BattleScreen2 BattleScreenInstance = new BattleScreen2(game);
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

        // Cria o estilo do texto
        BitmapFont font = new BitmapFont();
        font.getData().setScale(2);
        Color color = Color.WHITE;
        LabelStyle style = new LabelStyle(font, color);

        // Cria o estilo do botão
        Drawable upDrawable = new TextureRegionDrawable(new TextureRegion(new Texture("answer_button.png")));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = upDrawable;
        textButtonStyle.down = upDrawable;
        textButtonStyle.font = font;

        // Cria o texto da pergunta
        final int FinalI1 = BattleScreen1.indexPergunta;
        final int FinalI2 = BattleScreen2.indexPergunta;

        if (battleScreen == 1) { // Se for a primeira batalha
            Label questionLabel = new Label(questions[FinalI1], style);
            questionLabel.pack();
            questionLabel.setPosition(windowWidth / 2 - questionLabel.getWidth() / 2, windowHeight - 100);
            stage.addActor(questionLabel);

        } else if (battleScreen == 2) { // Se for a segunda batalha
            Label questionLabel = new Label(questions[FinalI2], style);
            questionLabel.pack();
            questionLabel.setPosition(windowWidth / 2 - questionLabel.getWidth() / 2, windowHeight - 100);
            stage.addActor(questionLabel);
        }

        int height = 300;
        for (int j = 0; j < 4; j++) {
            final int FinalJ = j;

            if (battleScreen == 1) { // Se for a primeira batalha
                TextButton answerButton = new TextButton(answers[FinalI1 * 4 + FinalJ], textButtonStyle);
                answerButton.setPosition(windowWidth / 2 - AssetUtils.answerButton.getWidth() / 2, windowHeight - height);
                answerButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        // Se a resposta for correta
                        if (answers[FinalI1 * 4 + FinalJ].equals(correctAnswers[FinalI1])) {
                            BattleScreen1.vidaInimigo--;
                            AssetUtils.songCorrect.play();

                        } else {
                            BattleScreen1.vidaAvatar--;
                            AssetUtils.songWrong.play();
                        }
                        
                        BattleScreen1.indexPergunta++;
                        startScreenBattle1Transition(); 
                    }
                });
                stage.addActor(answerButton);

            } else if (battleScreen == 2) { // Se for a segunda batalha
                TextButton answerButton = new TextButton(answers[FinalI2 * 4 + FinalJ], textButtonStyle);
                answerButton.setPosition(windowWidth / 2 - AssetUtils.answerButton.getWidth() / 2, windowHeight - height);
                answerButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        // Se a resposta for correta
                        if (answers[FinalI2 * 4 + FinalJ].equals(correctAnswers[FinalI2])) {
                            BattleScreen2.vidaInimigo--;
                            AssetUtils.songCorrect.play();

                        } else {
                            BattleScreen2.vidaAvatar--;
                            AssetUtils.songWrong.play();
                        }
                        if (battleScreen == 2){
                            BattleScreen2.indexPergunta++;
                            startScreenBattle2Transition();
                        }
                    }
                });
                stage.addActor(answerButton);
            }
                
            height += 120;
        }
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
        stage.dispose();
        batch.dispose();
    }
}
package com.coloniarpg.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class AssetUtils {
    public static Texture playButton;
    public static Texture playButtonHighlight;
    public static Texture title;
    public static Texture backgroundMenu;
    
    public static Texture selectLevelText;
    public static Texture levelButton1;
    public static Texture levelButton2;

    public static Texture backgroundBattle1;
    public static Texture backgroundBattle2;
    public static Texture avatar;
    public static Texture enemyDino;
    public static Texture enemyJacare;

    public static Texture answerButton;
    public static Texture attackButton;
    public static Texture attackButtonHighlight;
    public static Texture heart;

    public static Texture venceuText;
    public static Texture perdeuText;

    public static Sound songCorrect;
    public static Sound songWrong;
    public static Sound songButton;

    private AssetUtils() {
    }

    public static void initAssets() {
        loadAssets();
    }

    private static void loadAssets() {
        playButton = new Texture("play_button.png");
        playButtonHighlight = new Texture("play_button_highlighted.png");
        title = new Texture("tittle_game.png");
        backgroundMenu = new Texture("bg_mainmenu.jpg");

        selectLevelText = new Texture("select_level_text.png");
        levelButton1 = new Texture("level_button_1.png");
        levelButton2 = new Texture("level_button_2.png");

        backgroundBattle1 = new Texture("bg_battle_1.png");
        backgroundBattle2 = new Texture("bg_battle_2.png");
        avatar = new Texture("avatar.png");
        enemyDino = new Texture("enemy_dino.png");
        enemyJacare = new Texture("enemy_jacare.png");

        answerButton = new Texture("answer_button.png");
        attackButton = new Texture("attack_button.png");
        attackButtonHighlight = new Texture("attack_button_highlighted.png");
        heart = new Texture("heart.png");

        venceuText = new Texture("text_venceu.png");
        perdeuText = new Texture("text_perdeu.png");

        songCorrect = Gdx.audio.newSound(Gdx.files.internal("./assets/song_correct.mp3"));
        songWrong = Gdx.audio.newSound(Gdx.files.internal("./assets/song_wrong.mp3"));
        songButton = Gdx.audio.newSound(Gdx.files.internal("./assets/song_button.mp3"));
    }


    public static void dispose() {
        playButton.dispose();
        playButtonHighlight.dispose();
        title.dispose();
        backgroundMenu.dispose();

        selectLevelText.dispose();
        levelButton1.dispose();
        levelButton2.dispose();

        backgroundBattle1.dispose();
        backgroundBattle2.dispose();
        avatar.dispose();
        enemyDino.dispose();
        enemyJacare.dispose();

        answerButton.dispose();
        attackButton.dispose();
        attackButtonHighlight.dispose();
        heart.dispose();

        venceuText.dispose();
        perdeuText.dispose();

        songCorrect.dispose();
        songWrong.dispose();
        songButton.dispose();
    }
}
package com.coloniarpg.game;

import com.badlogic.gdx.graphics.Texture;

public class AssetUtils {
    public static Texture playButton;
    public static Texture playButtonHighlight;
    public static Texture title;
    public static Texture backgroundMenu;
    
    public static Texture selectLevelText;
    public static Texture levelButton1;
    public static Texture levelButton2;
    public static Texture levelButton3;

    public static Texture backgroundBattle;
    public static Texture avatar;
    public static Texture enemy;

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
        levelButton2 = new Texture("level_button_1.png");
        levelButton3 = new Texture("level_button_1.png");

        backgroundBattle = new Texture("bg_battle.jpg");
        avatar = new Texture("avatar.png");
        enemy = new Texture("enemy.png");
    }
}

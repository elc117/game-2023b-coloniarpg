package com.coloniarpg.game;

import com.badlogic.gdx.graphics.Texture;

public class AssetUtils {
    public static Texture playButton;
    public static Texture playButtonHighlight;
    public static Texture title;
    public static Texture backgroundMenu;

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
    }
}

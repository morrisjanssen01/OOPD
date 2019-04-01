package helloMisterPresident;

import helloMisterPresident.tiles.*;
import nl.han.ica.oopg.dashboard.Dashboard;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.TextObject;
import nl.han.ica.oopg.persistence.FilePersistence;
import nl.han.ica.oopg.persistence.IPersistence;
import nl.han.ica.oopg.sound.Sound;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.CenterFollowingViewport;
import nl.han.ica.oopg.view.View;
import processing.core.PApplet;


public class HelloMisterPresident extends GameEngine{
	
	private Sound backgroundMusic;
	private TextObject hubText;
	private Player player;
	private Flag flag;
	private button Music;
	private button Soundeffects;
	
	public static String MEDIA_URL = "src/main/java/gameProject/gameSprites/";

	public static void main(String[] args) {
		String[] processingArgs = {"helloMisterPresident.HelloMisterPresident"};
		HelloMisterPresident theHubLevel = new HelloMisterPresident();
		
		PApplet.runSketch(processingArgs, theHubLevel);
	}
	
	@Override
	public void setupGame() {
		
		int worldWidth = 1024;
		int worldHeight = 1024;
		
		initializeSound();
		initializeTileMap();
		
		createObjects();
		
		createViewWithoutViewport(worldWidth, worldHeight);
		
	}
	
	private void createViewWithoutViewport(int screenWidth, int screenHeight) {
		View view = new View(screenWidth, screenHeight);
		view.setBackground(loadImage(MEDIA_URL.concat("background-elements-redux/Backgrounds/backgroundCastles.png")));
		
		setView(view);
		size(screenWidth, screenHeight);
	}
	
//	private void createViewWithViewport(int worldWidth, int worldHeight, int screenWidth, int screenHeight, float zoomFactor) {
//		CenterFollowingViewport viewPort = new CenterFollowingViewport(player, (int) Math.ceil(screenWidth / zoomFactor), (int) Math.ceil(screenHeight / zoomFactor), 0, 0);
//		viewPort.setTolerance(50, 50, 50, 50);
//		View view = new View(viewPort, worldWidth, worldHeight);
//		setView(view);
//		size(screenWidth, screenHeight);
//		view.setBackground(loadImage(MEDIA_URL.concat("background-elements-redux/Backgrounds/backgroundCastles.png")));
//	}
	
	private void initializeSound() {
		backgroundMusic = new Sound(this, MEDIA_URL.concat("backgroundMusic.mp3"));
		backgroundMusic.loop(-1);
	}
	
	private void createObjects() {
		player = new Player(this);
		addGameObject(player, 0, 800);
//  	flag = new Flag(this);
//		addGameObject(flag,700,800);
		TextObject th = new TextObject("The Hub", 40);
		addGameObject(th, 435, 256);
	}
	
	
	
	private void initializeTileMap() {
		Sprite topGroundSprite = new Sprite(MEDIA_URL.concat("PNG/Tiles/platformPack_tile001.png"));
		Sprite bottomGroundSprite = new Sprite(MEDIA_URL.concat("PNG/Tiles/platformPack_tile004.png"));
		Sprite platformSprite = new Sprite(MEDIA_URL.concat("PNG/Tiles/platformPack_tile034.png"));
		Sprite platformEndSprite = new Sprite(MEDIA_URL.concat("PNG/Tiles/platformPack_tile034.png"));
		TileType<topGroundSprite> topGround = new TileType<>(topGroundSprite.class, topGroundSprite);
		TileType<bottomGroundSprite> bottomGround = new TileType<>(bottomGroundSprite.class, bottomGroundSprite);
		TileType<platformSprite> platform = new TileType<>(platformSprite.class, platformSprite);
		TileType<platformEndSprite> platformEnd = new TileType<>(platformEndSprite.class, platformEndSprite);
		
		TileType[] tiles = {topGround, bottomGround, platform, platformEnd};
		int tileSize = 50;
		int tilesMap[][] = {
				{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
				{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, 3, 2, 2, 2, 2, 3, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
		};
		tileMap = new TileMap(tileSize, tiles, tilesMap);
	}
	@Override
	public void update() {
		System.out.println(player.getY());
		
	}
	
	

}

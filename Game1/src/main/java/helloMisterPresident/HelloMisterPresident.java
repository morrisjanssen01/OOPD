package helloMisterPresident;

import java.util.Random;

import helloMisterPresident.tiles.*;
import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
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

@SuppressWarnings("serial")
public class HelloMisterPresident extends GameEngine implements IAlarmListener{
	
	private Sound backgroundMusic;
	private TextObject hubText;
	private Player player;
	public MusicButton musicButton;
	public SoundButton soundButton;
	private Enemies virus;
	private Enemies virusRanged;
	private Enemies security;
	private Enemies securityRanged;
	public static String MEDIA_URL = "src/main/java/gameProject/gameSprites/";
	private Alarm enemieSpawner;
	private static int HUB = 0;
	private static int LEVEL = 1;
	public int huidigLevel = HUB;

	public static void main(String[] args) {
		String[] processingArgs = {"helloMisterPresident.HelloMisterPresident"};
		HelloMisterPresident theHubLevel = new HelloMisterPresident();
		PApplet.runSketch(processingArgs, theHubLevel);
	}
	
	@Override
	public void setupGame() {
	setupLevel();
	initializeSound();
	initializeTileMap();
	createObjects();
	}
	
	public void setupLevel() {
		
		int worldWidth = 1024;
		int worldHeight = 1024;
		int screenWidth = 1024;
		int screenHeight = 1024;
		
		if(huidigLevel == HUB) {
		createViewWithoutViewport(worldWidth, worldHeight);
		}
		else if(huidigLevel == LEVEL) {
		createViewWithViewport(worldWidth, worldHeight, screenWidth, screenHeight, 1);
		this.startAlarm();
		}
	}
	
	private void createViewWithoutViewport(int screenWidth, int screenHeight) {
		View view = new View(screenWidth, screenHeight);
		view.setBackground(loadImage(MEDIA_URL.concat("background-elements-redux/Backgrounds/backgroundCastles.png")));
		setView(view);
		size(screenWidth, screenHeight);
	}
	
	private void createViewWithViewport(int worldWidth, int worldHeight, int screenWidth, int screenHeight, float zoomFactor) {
		CenterFollowingViewport viewPort = new CenterFollowingViewport(player, (int) Math.ceil(screenWidth / zoomFactor), (int) Math.ceil(screenHeight / zoomFactor), 0, 0);
		viewPort.setTolerance(50, 50, 50, 50);
		View view = new View(viewPort, worldWidth, worldHeight);
		setView(view);
		size(screenWidth, screenHeight);
		view.setBackground(loadImage(MEDIA_URL.concat("background-elements-redux/Backgrounds/backgroundCastles.png")));
	}
	
	private void initializeSound() {
		backgroundMusic = new Sound(this, MEDIA_URL.concat("backgroundMusic.mp3"));
		backgroundMusic.loop(-1);
	}
	
	private void createObjects() {
		player = new Player(this);
		addGameObject(player, 0, 800);
		TextObject th = new TextObject("The Hub", 40);
		addGameObject(th, 435, 256);
		musicButton = new MusicButton(this);
		addGameObject(musicButton, 0, 0);
		soundButton = new SoundButton();
		addGameObject(soundButton, 977, 0);
		virus = new Virus_Normal(400, 800, 1, this);
		addGameObject(virus, 400, 800);
		virusRanged = new Virus_Ranged(500, 800, this);
		addGameObject(virusRanged, 500, 800);
		security = new Security_Normal()
	}
	
	
	
	private void initializeTileMap() {
		Sprite topGroundSprite = new Sprite(MEDIA_URL.concat("PNG/Tiles/platformPack_tile001.png"));
		Sprite bottomGroundSprite = new Sprite(MEDIA_URL.concat("PNG/Tiles/platformPack_tile004.png"));
		Sprite platformSprite = new Sprite(MEDIA_URL.concat("PNG/Tiles/platformPack_tile034.png"));
		Sprite platformEndSprite = new Sprite(MEDIA_URL.concat("PNG/Tiles/platformPack_tile034.png"));
		Sprite FlagSprite = new Sprite(MEDIA_URL.concat("finish.png"));
		TileType<topGroundSprite> topGround = new TileType<>(topGroundSprite.class, topGroundSprite);
		TileType<bottomGroundSprite> bottomGround = new TileType<>(bottomGroundSprite.class, bottomGroundSprite);
		TileType<platformSprite> platform = new TileType<>(platformSprite.class, platformSprite);
		TileType<platformEndSprite> platformEnd = new TileType<>(platformEndSprite.class, platformEndSprite);
		TileType<flagSprite> Flag = new TileType<>(flagSprite.class, FlagSprite);
		
		
		TileType[] tiles = {topGround, bottomGround, platform, platformEnd, Flag};
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
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 4, -1, -1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
		};
		tileMap = new TileMap(tileSize, tiles, tilesMap);
	}
	@Override
	public void update() {		
		levelOvergang();
	}
	
	public Sound getbackgroundMusic() {
		return backgroundMusic;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void startAlarm() {
		enemieSpawner = new Alarm("Enemy", 10);
		enemieSpawner.addTarget(this); 
		enemieSpawner.start();
	}
	
	public void triggerAlarm(String alarmName) {
		if (alarmName == "Enemy") {
			Random randX = new Random(); 
			int xE = randX.nextInt(801);
			Random randE = new Random();
			int Enemie = randE.nextInt(4);
			if(Enemie == 0) {
				virus = new Virus_Normal(xE, 800, 1, this);
				addGameObject(virus, xE, 800);
			}
			else if(Enemie == 1) {
				virusRanged = new Virus_Ranged(xE, 800, this);
				addGameObject(virusRanged, xE, 800);
			}
			else if(Enemie == 2) {
				security = new Security_Normal(xE, 800, 1, this);
				addGameObject(security, xE, 800);
			}
			else if(Enemie == 3) {
				securityRanged = new Security_Ranged(xE, 800, 1, this);
				addGameObject(securityRanged, xE, 800);
			}
			this.startAlarm();
		}
	}
	
	public void levelOvergang() {
		if(player.getCenterX() >= 1000f && huidigLevel == HUB) {
			huidigLevel = LEVEL;
			setupLevel();
			System.out.println(huidigLevel);
		}
	}
}
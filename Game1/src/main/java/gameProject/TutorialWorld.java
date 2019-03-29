package gameProject;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.view.View;
import nl.han.ica.oopg.sound.Sound;

public class TutorialWorld extends GameEngine{
	private Player player;
	private Sound music;
	
	public static String MEDIA_URL = "src/main/java/gameProject/gameSprites/";

	public static void main(String[] args) {
		TutorialWorld tw = new TutorialWorld();
		tw.runSketch();
	}
	
	@Override
	public void setupGame() {
		player = new Player(this);
		addGameObject(player, 30, 350);
		player.setGravity((float)(1.0));
d		
		int worldWidth = 1016;
		int worldHeight = 508;
		
		View view = new View(worldWidth, worldHeight);
		
		setView(view);
		size(worldWidth, worldHeight);
		view.setBackground(loadImage(this.MEDIA_URL.concat("background-elements-redux/Backgrounds/backgroundCastles2.png")));
		music = new Sound(this, MEDIA_URL.concat("backgroundMusic.mp3"));
		music.loop(-1);
	}
	
	@Override
	public void update() {
		System.out.println(player.getY());
	}
	
	

}

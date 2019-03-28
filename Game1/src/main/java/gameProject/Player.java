package gameProject;

import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

public class Player extends SpriteObject{
	private TutorialWorld world;
	private int currentDirection;
	public int jumpDirection;
	
	public Player(TutorialWorld world) {
		super(new Sprite(TutorialWorld.MEDIA_URL.concat("player_idle.png")));
		this.jumpDirection = 0;
	}
	
	@Override
	public void update() {
		if(jumpDirection < 90 && jumpDirection > 0) {
			jumpDirection++;
		}
		else if(jumpDirection > 91) {
			jumpDirection--;
		}
		if(this.getY() > 350.0) {
			float newY = this.getY();
			newY = newY - 10.0f;
			this.setY((float)newY);
		}
//		if(this.getY() == 250.0) {
//			setDirectionSpeed(18, 10);
//			if(this.getY() == 350.0) {
//				setDirectionSpeed(currentDirection, 0);
//			}
//		}
		
	}
	
	@Override
	public void keyPressed(int keyCode, char key) {
		final int speed = 5;
		if(keyCode == world.LEFT) {
			setDirectionSpeed(270, speed);
			currentDirection = 270;
		}
		else if(keyCode == world.RIGHT) {
			setDirectionSpeed(90, speed);
			currentDirection = 90;
		}
		else if(keyCode == world.UP) {
			setDirectionSpeed(5, 10);
			System.out.println(this.jumpDirection);
		}
		else if(keyCode == world.UP || keyCode == world.RIGHT) {
		}
	}
	
	@Override
	public void keyReleased(int keyCode, char key) {
		final int speed = 0;
		if(keyCode == world.LEFT || keyCode == world.RIGHT) {
			setDirectionSpeed(0, speed);
		}
	}
}

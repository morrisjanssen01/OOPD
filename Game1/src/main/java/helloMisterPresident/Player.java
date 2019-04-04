package helloMisterPresident;

import helloMisterPresident.tiles.*;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import processing.core.PVector;
import nl.han.ica.oopg.sound.Sound;

import java.util.List;

public class Player extends AnimatedSpriteObject implements ICollidableWithTiles,ICollidableWithGameObjects{
	final int size = 25;
	private final HelloMisterPresident world;
	private int lives = 3;
	private int jumpHeight;
	private boolean canJump;
	public Sound jump;
	
	public Player(HelloMisterPresident world) {
		super(new Sprite(HelloMisterPresident.MEDIA_URL.concat("PNG/Characters/platformChar_idle1.png")), 2);
		jump = new Sound(world, HelloMisterPresident.MEDIA_URL.concat("jump.mp3"));
		this.world = world;
		setCurrentFrameIndex(0);
		setFriction(0.05f);
		jumpHeight = 200;
		canJump = true;
	}
	
	@Override
	public void update() {
		gravity(5.0f);
		if (getX() <= 0) {
			setxSpeed(0);
			setX(0);
		}
		if(getY() <=0) {
			setySpeed(0);
			setY(0);
		}
		if(getX() >= world.width - size) {
			setxSpeed(0);
			setX(world.width - size);
		}
		if(getY() >= world.height - size) {
			setySpeed(0);
			setY(world.height - size);
		}
	}
	
	@Override
	public void keyPressed(int keyCode, char key) {
		final int speed = 5;
		if(keyCode == world.LEFT || key == 'a') {
			setDirectionSpeed(270, speed);
			setCurrentFrameIndex(1);
			setFriction(0.05f);
		}
		if(keyCode == world.UP && canJump || key == 'w' && canJump) {
			setDirectionSpeed(0, 10);
			setFriction(0.006f);
			if(world.soundButton.aanUit) {
			jump.rewind();
			jump.play();
			}
			canJump = false;
		}
		if(keyCode == world.RIGHT || key == 'd') {
			setDirectionSpeed(90, speed);
			setCurrentFrameIndex(0);
			setFriction(0.05f);
		}
		if(keyCode == world.DOWN || key == 's') {
			setDirectionSpeed(180, speed);
		}
		if(key == ' ') {
			System.out.println("Spatie!");
			System.out.println(getCenterX());
			
		}
	}
	
	@Override
	public void keyReleased(int keyCode, char key) {
		if(keyCode == world.UP) {
			setFriction(0.05f);
		}
	}
	
	@Override
	public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
		PVector vector;
		
		for(CollidedTile ct : collidedTiles) {
			if(ct.getTile() instanceof topGroundSprite) {
				if(CollisionSide.TOP.equals(ct.getCollisionSide())) {
					try {
						vector = world.getTileMap().getTilePixelLocation(ct.getTile());
						setY(vector.y - getHeight());
						setySpeed(0);
						canJump = true;
					} catch(TileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			if(ct.getTile() instanceof platformEndSprite || ct.getTile() instanceof platformSprite) {
				if(CollisionSide.TOP.equals(ct.getCollisionSide())) {
					try {
						vector = world.getTileMap().getTilePixelLocation(ct.getTile());
						setY(vector.y - getHeight());
						canJump = true;
					} catch(TileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			if(ct.getTile() instanceof platformEndSprite) {
				if(CollisionSide.RIGHT.equals(ct.getCollisionSide())) {
					try {
						vector = world.getTileMap().getTilePixelLocation(ct.getTile());
						setX(vector.x + getWidth());
					} catch(TileNotFoundException e) {
						e.printStackTrace();
					}
				}
				if(CollisionSide.LEFT.equals(ct.getCollisionSide())) {
					try {
						vector = world.getTileMap().getTilePixelLocation(ct.getTile());
						setX(vector.x - getWidth());
					} catch(TileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void die() {
		System.out.println("Player dead");
		world.deleteGameObject(this);
		lives -= 1;
	}
	
	public void gravity(float gravity) {
		this.setY(this.getY() + gravity);
	}

	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for(GameObject g : collidedGameObjects) {
			if(g instanceof Enemies) {
				if(this.getY() + this.getHeight() < g.getY() && this.getX() - 48 < g.getX() && this.getX() + 48 > g.getX()) {
					boolean eersteKeer = this.getY() + this.getHeight() > g.getY();
					boolean tweedeKeer = this.getX() - 48 < g.getX() && this.getX() + 48 > g.getX();
					System.out.println("1e: " + eersteKeer + " 2e: " + tweedeKeer);
					this.die();
				}
			}
			else if( g instanceof Projectiel) {
				this.die();
			}
		}
	}
}
	
	


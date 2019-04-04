package helloMisterPresident;

import java.util.List;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;

public class Projectiel extends SpriteObject implements ICollidableWithGameObjects {
	private float x;
	private float y;
	private float velocity;
	private int richting;
	
	
	
	
	public Projectiel(float x, float y, float velocity) {
		super(new Sprite (HelloMisterPresident.MEDIA_URL.concat("PNG/Items/Energy Orb.png"))); 
		this.x = x;
		this.y = y;
		this.velocity = velocity;
	}


	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		
		
	}


	@Override
	public void update() {
		beweegProjectiel();
		
	}

	public void beweegProjectiel() {
		if(richting == 0) {
			this.x += velocity;
			}
			if(richting == 1) {
			this.x -= velocity;
			}
	}
}

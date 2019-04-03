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
	
	
	
	
	public Projectiel(float x, float y, float velocity, int richting) {
		super(new Sprite (HelloMisterPresident.MEDIA_URL.concat("PNG/Characters/mon2_sprite_base1.png")));
		this.x = x;
		this.y = y;
		this.velocity = velocity;
		this.richting = richting;
	}


	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}

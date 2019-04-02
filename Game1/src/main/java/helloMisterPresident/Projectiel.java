package helloMisterPresident;

import java.util.List;
import nl.han.ica.oopg.objects.Sprite;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;

public class Projectiel implements ICollidableWithGameObjects {
	private int x;
	private int y;
	private float velocity;
	private int richting;
	
	Projectiel projectiel = new Projectiel(x,y, velocity, richting);
	
	
	public Projectiel(int x, int y, float velocity, int richting) {
		this.x = x;
		this.y = y;
		this.velocity = velocity;
		this.richting = richting;
	}


	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		// TODO Auto-generated method stub
		
	}

}

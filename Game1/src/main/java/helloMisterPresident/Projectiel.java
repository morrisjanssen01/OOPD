package helloMisterPresident;

import java.util.List;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;

public class Projectiel extends SpriteObject implements ICollidableWithGameObjects {
	@SuppressWarnings("unused")
	private float x;
	@SuppressWarnings("unused")
	private float y;
	private float velocity;
	private int richting;
	public HelloMisterPresident world;

	// Constructor
	public Projectiel(float x, float y, float velocity, int richting, HelloMisterPresident world) {
		super(new Sprite(HelloMisterPresident.MEDIA_URL.concat("PNG/Items/Energy Orb.png")));
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

	// Zorgt voor het bewegen van het projectiel.
	public void beweegProjectiel() {
		if (richting == 0) {
			this.setX(getX() - velocity);
		}
		if (richting == 1) {
			this.setX(getX() + velocity);
		}
	}

	// Returnt de richting die het projectiel op moet bewegen.
	public int getRichting() {
		if (world.getPlayer().getX() > this.getX()) {
			richting = 1;
		} else if (world.getPlayer().getX() < this.getX()) {
			richting = 0;
		}
		return richting;
	}
}

package helloMisterPresident;

import java.util.List;

import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;

public abstract class Enemies extends AnimatedSpriteObject implements ICollidableWithGameObjects{

	//Constructor
	public Enemies(int x, int y, Sprite sprite) {
		super((sprite), 8);
		this.x = x;
		this.y = y;
	}
	
	public abstract void beweeg();
	public abstract void die();

	@Override
	public void update() {
		
	}
	
	//Zorgt ervoor dat een Object van type Enemie de functie die() uitvoert op het moment dat er een collision is met de player vanaf boven
	@Override
	public void gameObjectCollisionOccurred(List<GameObject> collidedGameObjects) {
		for(GameObject g : collidedGameObjects) {
			if(g instanceof Player) {
				if(this.getY() + this.getHeight() > g.getY() + g.getHeight() && this.getX() - 48 < g.getX() && this.getX() + 48 > g.getX()) {
					this.die();
				}
			}
		}
	}
}

package helloMisterPresident;

import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;

public abstract class Enemies extends AnimatedSpriteObject {

	public Enemies(int x, int y, Sprite sprite) {
		super(( sprite), 2);
		this.x = x;
		this.y = y;
		
		
		// TODO Auto-generated constructor stub
	}
	
	public abstract void beweeg();

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}

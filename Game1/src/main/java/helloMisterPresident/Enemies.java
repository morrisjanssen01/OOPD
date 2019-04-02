package helloMisterPresident;

import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;

public class Enemies extends AnimatedSpriteObject {

	public Enemies(int x, int y, int grootte, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.grootte = grootte;
		super(new Sprite(HelloMisterPresident.MEDIA_URL.concat(this.sprite), 2))
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}

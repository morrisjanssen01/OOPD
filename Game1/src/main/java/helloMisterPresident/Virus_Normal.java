package helloMisterPresident;

import helloMisterPresident.Enemies;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;

public class Virus_Normal extends AnimatedSpriteObject extends Enemies{
	private int richting;
	private float speed;
	private int Punt1;
	private int Punt2;
	
	public Virus_Normal(int x, int y, int grootte, int richting) {
		super(new Sprite(HelloMisterPresident.MEDIA_URL.concat("PNG/Characters/Virus_Normal.png")), 2);
		super(x, y, grootte);
		this.richting = richting;
		this.Punt1 = this.x;
		this.Punt2 = this.Punt1 + 100;
	}
	
	@Override
	public void beweeg() {
		if(this.x >= Punt2) {
			richting = 1;
		}
		else if(this.prevX <= Punt1) {
			richting = 0;
		}
		if(richting == 0) {
		 this.x += speed;
		}
		if(richting == 1) {
			this.x -= speed;
		}
	}
	
	@Override
	public void update() {
		beweeg();
	}

}

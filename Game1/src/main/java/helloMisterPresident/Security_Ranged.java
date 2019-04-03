package helloMisterPresident;

import nl.han.ica.oopg.objects.Sprite;

public class Security_Ranged extends Enemies{

	private int richting;
	private float speed = 5;
	private int Punt1;
	private int Punt2;
	
	
	public Security_Ranged(int x, int y, Sprite sprite) {
		super(x, y, new Sprite(HelloMisterPresident.MEDIA_URL.concat("PNG/Characters/mon2_sprite_base1.png"))); //sprite nog aanpassen
		this.richting = richting;
		this.Punt1 = (int) this.x;
		this.Punt2 = this.Punt1 + 100;
		setCurrentFrameIndex(0);
	}

	@Override
	public void beweeg() {
		if(this.x >= Punt2) {
			richting = 1;
		}
		else if(this.x <= Punt1) {
			richting = 0;
		}
		if(richting == 0) {
		 this.x += speed;
		}
		if(richting == 1) {
			this.x -= speed;
		}
		
	}

}

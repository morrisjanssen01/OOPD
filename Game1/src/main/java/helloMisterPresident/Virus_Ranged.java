package helloMisterPresident;

import nl.han.ica.oopg.objects.Sprite;

public class Virus_Ranged extends Enemies {

	private int richting;
	private float speed = 1;
	private int Punt1;
	private int Punt2;
	
	public Virus_Ranged(int x, int y, Sprite sprite) {
		super(x, y, new Sprite(HelloMisterPresident.MEDIA_URL.concat("PNG/Characters/mon2_sprite_base1.png"))); //Sprite nog aanpassen
		this.richting = richting;
		this.Punt1 = (int) this.x;
		this.Punt2 = this.Punt1 + 100;
		setCurrentFrameIndex(0);
	}

	@Override
	public void beweeg() {
		// TODO Auto-generated method stub
		
	}

}

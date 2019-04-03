package helloMisterPresident;

import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.Sprite;

public class Virus_Ranged extends Enemies implements IAlarmListener {

	private int richting;
	private float speed = 1;
	private int Punt1;
	private int Punt2;
	private final HelloMisterPresident world;
	
	public Virus_Ranged(int x, int y, Sprite sprite, HelloMisterPresident world) {
		super(x, y, new Sprite(HelloMisterPresident.MEDIA_URL.concat("PNG/Characters/mon2_sprite_base1.png"))); //Sprite nog aanpassen
		this.richting = richting;
		this.Punt1 = (int) this.x;
		this.Punt2 = this.Punt1 + 100;
		setCurrentFrameIndex(0);
		this.world = world;
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
	public void schiet() {
		Projectiel projectiel = new Projectiel(this.x,this.y, 4f, this.richting);
		world.addGameObject(projectiel,(float)this.x, (float)this.getCenterY());
	}

	@Override
	public void triggerAlarm(String alarmName) {
		schiet();
		
	}

}

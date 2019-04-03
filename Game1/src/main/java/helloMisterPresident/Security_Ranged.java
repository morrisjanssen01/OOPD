package helloMisterPresident;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.Sprite;

public class Security_Ranged extends Enemies implements IAlarmListener{

	private int richting;
	private float speed = 5;
	private int Punt1;
	private int Punt2;
	private Alarm alarm;
	private final HelloMisterPresident world;
	private int schietRichting;
	private double schietDelay = 3;
	
	
	public Security_Ranged(int x, int y, Sprite sprite, HelloMisterPresident world) {
		super(x, y, new Sprite(HelloMisterPresident.MEDIA_URL.concat("PNG/Characters/mon2_sprite_base1.png"))); //sprite nog aanpassen
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

	public void getSchietRichting() {
		if (this.x >= getX()) {
		schietRichting = 1;
		}
		else {
			schietRichting = 0;
			}
	}
	public void schiet() {
		getSchietRichting();
		Projectiel projectiel = new Projectiel(this.x,this.y, 4f, schietRichting);
		world.addGameObject(projectiel,(float)this.x, (float)this.getCenterY());
	}
	
	public void startAlarm(){
	alarm = new Alarm("Schieten", schietDelay);
	alarm.addTarget(this);
	alarm.start();
	}
	
	@Override
	public void triggerAlarm(String alarmName) {
		Projectiel p = new Projectiel(this.x,this.y, 4f, this.richting);
		world.addGameObject(p,(float)this.x, (float)this.getCenterY());
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

}

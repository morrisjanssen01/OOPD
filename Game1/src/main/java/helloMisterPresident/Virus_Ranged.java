package helloMisterPresident;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.Sprite;

public class Virus_Ranged extends Enemies implements IAlarmListener {

	private int richting;
	private float speed = 0;
	private int Punt1;
	private int Punt2;
	private final HelloMisterPresident world;
	private double schietDelay = 3;
	private Alarm alarm;
	
	public Virus_Ranged(int x, int y, HelloMisterPresident world) {
		super(x, y, new Sprite(HelloMisterPresident.MEDIA_URL.concat("PNG/Characters/Virus_Ranged.png")));
		this.Punt1 = (int) this.x;
		this.Punt2 = this.Punt1 + 100;
		setCurrentFrameIndex(0);
		this.world = world;
		startAlarm();
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
		Projectiel projectiel = new Projectiel(this.x,this.y, 4f);
		world.addGameObject(projectiel,(float)this.x, (float)this.getCenterY());
	}
	
	public void startAlarm(){
	alarm = new Alarm("Schieten", schietDelay);
	alarm.addTarget(this);
	alarm.start();
	}
	
	@Override
	public void triggerAlarm(String alarmName) {
		if (alarmName == "Schieten") {
		Projectiel p = new Projectiel(this.x,this.y, 4f);
		world.addGameObject(p,(float)this.x, (float)this.getCenterY());
		alarm.start();
	}
		else if (alarmName =="Die") {
			this.die();
		}
	}
	@Override
	public void die() {
		world.deleteGameObject(this);
		alarm.stop();
	}
	
}

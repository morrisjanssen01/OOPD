package helloMisterPresident;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.Sprite;

public class Security_Ranged extends Enemies implements IAlarmListener{

	private int richting;
	private float speed = 1;
	private int Punt1;
	private int Punt2;
	private Alarm alarm;
	private Alarm die;
	private final HelloMisterPresident world;
	private double schietDelay = 3;
	private int schietRichting;
	
	
	public Security_Ranged(int x, int y, int richting, HelloMisterPresident world) {
		super(x, y, new Sprite(HelloMisterPresident.MEDIA_URL.concat("PNG/Characters/Security_Ranged1.png")));
		this.richting = richting;
		this.Punt1 = (int) this.x;
		this.Punt2 = this.Punt1 + 100;
		setCurrentFrameIndex(0);
		this.world = world;
		startSchiet();
	}

	@Override
	public void beweeg() {
		if(richting == 1) {
		 this.x += speed;
		 getRichting();
		}
		else if(richting == 0) {
			this.x -= speed;
			getRichting();
		}
	}
	
	public void schiet() {
		schietRichting = getSchietRichting();
		Projectiel projectiel = new Projectiel(this.x,this.y, 4f, schietRichting, world);
		world.addGameObject(projectiel,(float)this.x, (float)this.getCenterY());
	}
	
	public void startSchiet(){
	alarm = new Alarm("Schieten", schietDelay);
	alarm.addTarget(this);
	alarm.start();
	}
	
	public void startAlarm() {
		die = new Alarm("Die", 0.1);
		die.addTarget(this);
		die.start();
	}
	
	@Override
	public void triggerAlarm(String alarmName) {
		if (alarmName == "Schieten") {
		schiet();
		startSchiet();
	}
		else if (alarmName =="Die") {
			if(this.getCurrentFrameIndex() < 7) {
				this.setCurrentFrameIndex(getCurrentFrameIndex() + 1);
				startAlarm();
			}
			else if(getCurrentFrameIndex() > 6) {
				alarm.stop();
				world.deleteGameObject(this);
			}
		}
	}
               
	@Override
	public void die() {
		startAlarm();	
	}
	
	@Override
	public void update() {
		beweeg();
	}
	
	public int getSchietRichting() {
		if(world.getPlayer().getX() > this.getX()) {
			schietRichting = 1;
		}
		else if(world.getPlayer().getX() < this.getX()) {
			schietRichting = 0;
		}
		return schietRichting;
	}
	
	public void getRichting() {
		if(world.getPlayer().getX() > this.getX()) {
			richting = 1;
		}
		else if(world.getPlayer().getX() < this.getX()) {
			richting = 0;
		}
	}

}

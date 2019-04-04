package helloMisterPresident;

import helloMisterPresident.Enemies;
import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;

@SuppressWarnings("unused")
public class Security_Normal extends Enemies implements IAlarmListener{
	private int richting;
	private float speed = 1;
	private HelloMisterPresident world;
	private Alarm die;
	
	//Constructor
	public Security_Normal(int x, int y, HelloMisterPresident world) {
		super(x, y, new Sprite(HelloMisterPresident.MEDIA_URL.concat("PNG/Characters/Security.png")));
		setCurrentFrameIndex(0);
		this.world = world;
	}
	
	//Beweging van de Security.
	@Override
	public void beweeg() {
		if(richting == 1) {
		 this.x += speed;
		 getRichting();
		}
		if(richting == 0) {
			this.x -= speed;
			getRichting();
		}
	}
	
	@Override
	public void update() {
		beweeg();
	}
	
	//Start het sterf alarm.
	@Override
	public void die() {
		startAlarm();		
	}
	
	//Maak een alarm en start deze.
	private void startAlarm() {
		die = new Alarm("die", 0.1);
		die.addTarget(this);
		die.start();
	}
	
	//Zorgt voor de animatie en het verwijderen van de enemie.
	@Override
	public void triggerAlarm(String alarmName) {
		if(this.getCurrentFrameIndex()<7) {
			this.setCurrentFrameIndex(getCurrentFrameIndex()+1);
			startAlarm();
		}
		else if(getCurrentFrameIndex() > 6) {
			world.deleteGameObject(this);
		}
	}
	
	//Berekent welke kant de enemie op moet lopen om naar de player te lopen.
	public void getRichting() {
		if(world.getPlayer().getX() > this.getX()) {
			richting = 1;
		}
		else if(world.getPlayer().getX() < this.getX()) {
			richting = 0;
		}
	}

}

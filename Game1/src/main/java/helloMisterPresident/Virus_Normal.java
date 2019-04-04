package helloMisterPresident;

import helloMisterPresident.Enemies;
import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;

@SuppressWarnings("unused")
public class Virus_Normal extends Enemies implements IAlarmListener{
	private int richting;
	private float speed = 1;
	private int Punt1;
	private int Punt2;
	private Alarm die;
	private HelloMisterPresident world;
	
	//Constructor
	public Virus_Normal(int x, int y, int richting, HelloMisterPresident world) {
		super(x, y, new Sprite(HelloMisterPresident.MEDIA_URL.concat("PNG/Characters/Virus.png")));
		this.richting = richting;
		this.Punt1 = (int) this.x;
		this.Punt2 = this.Punt1 + 100;
		setCurrentFrameIndex(0);
		this.world = world;
		
	}
	
	//Zorgt voor een bewegingspatroon.
	@Override
	public void beweeg() {
		if(this.x >= Punt2) {
			richting = 1;
			setCurrentFrameIndex(1);
		}
		else if(this.x <= Punt1) {
			richting = 0;
			setCurrentFrameIndex(0);
		}
		if(richting == 0) {
		 this.x += speed;
		}
		if(richting == 1) {
			this.x -= speed;
		}
		
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
	
	@Override
	public void update() {
		beweeg();
	}
	
	//Zorgt voor de animatie en het verwijderen van de enemie
	@Override
	public void triggerAlarm(String alarmName) {
		if(this.getCurrentFrameIndex() < 7) {
			this.setCurrentFrameIndex(getCurrentFrameIndex() + 1);
			startAlarm();
		}
		else if(getCurrentFrameIndex() > 6) {
			world.deleteGameObject(this);
		}
	}

}

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
	private Alarm schiet;
	private int schietRichting;
	private Alarm die;

	// Constructor
	public Virus_Ranged(int x, int y, HelloMisterPresident world) {
		super(x, y, new Sprite(HelloMisterPresident.MEDIA_URL.concat("PNG/Characters/Virus_Ranged.png")));
		this.Punt1 = (int) this.x;
		this.Punt2 = this.Punt1 + 100;
		setCurrentFrameIndex(0);
		this.world = world;
		startSchiet();
	}

	// Beweging van de VirusRanged
	@Override
	public void beweeg() {
		if (this.x >= Punt2) {
			richting = 1;
		} else if (this.x <= Punt1) {
			richting = 0;
		}
		if (richting == 0) {
			this.x += speed;
		}
		if (richting == 1) {
			this.x -= speed;
		}
	}

	// Maakt een projectiel aan.
	public void schiet() {
		schietRichting = getSchietRichting();
		Projectiel projectiel = new Projectiel(this.x, this.y, 4f, schietRichting, world);
		world.addGameObject(projectiel, (float) this.x, (float) this.getCenterY());
	}

	// Maakt en start een alarm voor het schieten.
	public void startSchiet() {
		schiet = new Alarm("Schieten", schietDelay);
		schiet.addTarget(this);
		schiet.start();
	}

	// Maakt en start een alarm voor het sterven.
	public void startAlarm() {
		die = new Alarm("Die", 0.1);
		die.addTarget(this);
		die.start();
	}

	// Bevat de trigger voor het schieten en zorgt voor de animatie en het
	// verwijderen van het gameObject met sterven.
	@Override
	public void triggerAlarm(String alarmName) {
		if (alarmName == "Schieten") {
			schiet();
			startSchiet();
		} else if (alarmName == "Die") {
			if (this.getCurrentFrameIndex() < 7) {
				this.setCurrentFrameIndex(getCurrentFrameIndex() + 1);
				startAlarm();
			} else if (getCurrentFrameIndex() > 6) {
				schiet.stop();
				world.deleteGameObject(this);
			}
		}
	}

	// Start sterf alarm.
	@Override
	public void die() {
		startAlarm();
	}

	// Geeft de richting terug van het schieten.
	public int getSchietRichting() {
		if (world.getPlayer().getX() > this.getX()) {
			schietRichting = 1;
		} else if (world.getPlayer().getX() < this.getX()) {
			schietRichting = 0;
		}
		return schietRichting;
	}
}

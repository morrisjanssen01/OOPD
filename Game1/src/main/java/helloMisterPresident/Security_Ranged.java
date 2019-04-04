package helloMisterPresident;

import nl.han.ica.oopg.alarm.Alarm;
import nl.han.ica.oopg.alarm.IAlarmListener;
import nl.han.ica.oopg.objects.Sprite;

public class Security_Ranged extends Enemies implements IAlarmListener {

	private int richting;
	private float speed = 1;
	private int Punt1;
	@SuppressWarnings("unused")
	private int Punt2;
	private Alarm alarm;
	private Alarm die;
	private final HelloMisterPresident world;
	private double schietDelay = 3;
	private int schietRichting;

	// Constructor
	public Security_Ranged(int x, int y, int richting, HelloMisterPresident world) {
		super(x, y, new Sprite(HelloMisterPresident.MEDIA_URL.concat("PNG/Characters/Security_Ranged1.png")));
		this.richting = richting;
		this.Punt1 = (int) this.x;
		this.Punt2 = this.Punt1 + 100;
		setCurrentFrameIndex(0);
		this.world = world;
		startSchiet();
	}

	// Beweging van de securityRanged.
	@Override
	public void beweeg() {
		if (richting == 1) {
			this.x += speed;
			getRichting();
		} else if (richting == 0) {
			this.x -= speed;
			getRichting();
		}
	}

	// Maakt een projectiel aan
	public void schiet() {
		schietRichting = getSchietRichting();
		Projectiel projectiel = new Projectiel(this.x, this.y, 4f, schietRichting, world);
		world.addGameObject(projectiel, (float) this.x, (float) this.getCenterY());
	}

	// Maakt en start een alarm voor het schieten.
	public void startSchiet() {
		alarm = new Alarm("Schieten", schietDelay);
		alarm.addTarget(this);
		alarm.start();
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
				alarm.stop();
				world.deleteGameObject(this);
			}
		}
	}

	// Start sterf alarm.
	@Override
	public void die() {
		startAlarm();
	}

	@Override
	public void update() {
		beweeg();
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

	// Het zetten van de richting zodat hij naar de player loopt.
	public void getRichting() {
		if (world.getPlayer().getX() > this.getX()) {
			richting = 1;
		} else if (world.getPlayer().getX() < this.getX()) {
			richting = 0;
		}
	}

}

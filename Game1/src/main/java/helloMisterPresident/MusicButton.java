package helloMisterPresident;

import helloMisterPresident.Button;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.userinput.IMouseInput;

public class MusicButton extends AnimatedSpriteObject implements Button{
	public boolean aanUit;
	private final HelloMisterPresident world;
	
	MusicButton(HelloMisterPresident world){
		super(new Sprite(HelloMisterPresident.MEDIA_URL.concat("MusicButton.png")), 2);
		this.aanUit = true;
		setCurrentFrameIndex(0);
		this.world = world;
	}
	
	@Override
	public void update() {
		
	}
	
	@Override
	public void buttonAction(){
		if(aanUit) {
			setCurrentFrameIndex(1);
			aanUit = false;
			world.getBackgroundMusic().pause();
			world.getBackgroundMusic().rewind();
		}
		else if(!aanUit) {
			setCurrentFrameIndex(0);
			aanUit = true;
			world.getBackgroundMusic().loop(-1);
		}
	}
	
	@Override
	public void mouseClicked(int x, int y, int button) {
		if(x >= 0 && x <= 47 && y >= 0 && y <= 47) {
		this.buttonAction();
		}
	}
}
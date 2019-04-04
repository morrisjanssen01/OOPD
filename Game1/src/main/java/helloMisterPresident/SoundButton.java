package helloMisterPresident;

import helloMisterPresident.Button;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.userinput.IMouseInput;

@SuppressWarnings("unused")
public class SoundButton extends AnimatedSpriteObject implements Button{
	public boolean aanUit;
	
	SoundButton(){
		super(new Sprite(HelloMisterPresident.MEDIA_URL.concat("SoundButton.png")), 2);
		this.aanUit = true;
		setCurrentFrameIndex(0);
	}
	
	@Override
	public void update() {
	}
	
	@Override
	public void buttonAction(){
		if(aanUit) {
			setCurrentFrameIndex(1);
			aanUit = false;
		}
		else if(!aanUit) {
			setCurrentFrameIndex(0);
			aanUit = true;
		}
	}
	
	@Override
	public void mouseClicked(int x, int y, int button) {
		if(x >= 977 && x <= 1024 && y >= 0 && y <= 47) {
		this.buttonAction();
		}
	}
}

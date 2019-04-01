package helloMisterPresident;

import helloMisterPresident.Button;
import nl.han.ica.oopg.objects.AnimatedSpriteObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.userinput.IMouseInput;

public class MusicButton extends AnimatedSpriteObject implements Button{
	public boolean aanUit;
	
	MusicButton(){
		super(new Sprite(HelloMisterPresident.MEDIA_URL.concat("MusicButton.png")), 2);
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
			System.out.println("zet uit");
		}
		else if(!aanUit) {
			setCurrentFrameIndex(0);
			aanUit = true;
			System.out.println("zet aan");
		}
	}
	
	@Override
	public void mouseClicked(int x, int y, int button) {
		if(x >= 0 && x <= 47 && y >= 0 && y <= 47) {
		this.buttonAction();
		System.out.println("klik op knop");
		}
	}
}

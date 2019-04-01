package helloMisterPresident;

import nl.han.ica.oopg.userinput.IMouseInput;

public interface Button extends IMouseInput{
	
	public void buttonAction();
	public void mouseClicked(int x, int y, int button);

}

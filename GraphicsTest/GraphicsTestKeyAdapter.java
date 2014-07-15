import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GraphicsTestKeyAdapter extends KeyAdapter{
	public void keyReleased(KeyEvent e){
		System.out.println("released"+e.getKeyCode());
	}
	public void keyPressed(KeyEvent e){
		System.out.println("Pressed"+e.getKeyCode());
	}
}
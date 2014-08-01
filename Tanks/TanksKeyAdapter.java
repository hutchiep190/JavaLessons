import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TanksKeyAdapter extends KeyAdapter{
	private Application app;
	public TanksKeyAdapter(Application app){
		this.app = app;
	}
	public void keyReleased(KeyEvent e){
		app.setKeyReleased(e.getKeyCode());
	}
	public void keyPressed(KeyEvent e){
		app.setKeyPressed(e.getKeyCode());
	}
}
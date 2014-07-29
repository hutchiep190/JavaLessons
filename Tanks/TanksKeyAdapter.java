import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TanksKeyAdapter extends KeyAdapter{
	private Tanks gt;
	public TanksKeyAdapter(Tanks gt){
		this.gt = gt;
	}
	public void keyReleased(KeyEvent e){
		gt.setKeyReleased(e.getKeyCode());
	}
	public void keyPressed(KeyEvent e){
		gt.setKeyPressed(e.getKeyCode());
	}
}
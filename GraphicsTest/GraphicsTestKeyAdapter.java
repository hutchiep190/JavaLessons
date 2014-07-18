import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GraphicsTestKeyAdapter extends KeyAdapter{
	private GraphicsTest gt;
	public GraphicsTestKeyAdapter(GraphicsTest gt){
		this.gt = gt;
	}
	public void keyReleased(KeyEvent e){
		gt.setKeyReleased(e.getKeyCode());
	}
	public void keyPressed(KeyEvent e){
		gt.setKeyPressed(e.getKeyCode());
	}
}
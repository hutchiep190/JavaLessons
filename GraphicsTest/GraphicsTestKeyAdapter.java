import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GraphicsTestKeyAdapter extends KeyAdapter{
	private GraphicsTest gt;
	public GraphicsTestKeyAdapter(GraphicsTest gt){
		this.gt = gt;
	}
	public void keyReleased(KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			gt.setRightArrowKeyPressed(false);
		}
		
	}
	public void keyPressed(KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			gt.setRightArrowKeyPressed(true);
		}		
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			gt.setRunning(false);
		}
	}
}
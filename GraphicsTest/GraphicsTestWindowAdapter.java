import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GraphicsTestWindowAdapter extends WindowAdapter {
	private GraphicsTest gt; 
	public GraphicsTestWindowAdapter(GraphicsTest gt) {
		this.gt = gt;
	}
	public void windowClosing (WindowEvent e) {
		gt.setRunning(false);
	}
}
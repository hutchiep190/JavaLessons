import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TanksWindowAdapter extends WindowAdapter {
	private Tanks gt; 
	public TanksWindowAdapter(Tanks gt) {
		this.gt = gt;
	}
	public void windowClosing (WindowEvent e) {
		gt.setRunning(false);
	}
}
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TanksWindowAdapter extends WindowAdapter {
	private Application app; 
	public TanksWindowAdapter(Application app) {
		this.app = app;
	}
	public void windowClosing (WindowEvent e) {
		app.setRunning(false);
	}
}
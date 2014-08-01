import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TanksActionListener implements ActionListener{
	private Application app;
	public TanksActionListener(Application app){
		this.app = app;
	}
	public void actionPerformed(ActionEvent e){
		app.draw();
		app.update();
	}
}
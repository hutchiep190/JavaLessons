import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TanksActionListener implements ActionListener{
	private int t;
	private Tanks gt;
	public TanksActionListener(Tanks gt){
		this.gt = gt;
	}
	public void actionPerformed(ActionEvent e){
		gt.draw();
		gt.update();
		t += 1;
	}
}
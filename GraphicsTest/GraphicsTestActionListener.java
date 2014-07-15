import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GraphicsTestActionListener implements ActionListener{
	private int t;
	private GraphicsTest gt;
	public GraphicsTestActionListener(GraphicsTest gt){
		this.gt = gt;
	}
	public void actionPerformed(ActionEvent e){
		gt.draw();
		t += 1;
	}
}
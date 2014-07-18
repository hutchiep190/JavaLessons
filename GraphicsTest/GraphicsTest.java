import java.awt.Frame;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class GraphicsTest extends Frame {

	private int x = 0;
	private int y = 0;
	private boolean rightArrowKeyPressed = false;
	private boolean running = false;

	public GraphicsTest(){
		this.setBackground(new Color(0x38,0x8E,0x8E));
        this.addWindowListener(new GraphicsTestWindowAdapter());
        this.addKeyListener(new GraphicsTestKeyAdapter(this));
        this.setSize(320, 240);
        this.setUndecorated(true);
        this.setVisible(true);
        this.createBufferStrategy(2);

        Timer t = new Timer(20, new GraphicsTestActionListener(this));
        running = true;
        t.start();
	}

	public void setRunning(boolean running){
		this.running = running;
	}

	public void setRightArrowKeyPressed(boolean rightArrowKeyPressed){
		this.rightArrowKeyPressed = rightArrowKeyPressed;
	}

	public void update() {
		if(running == false) {
			System.exit (0);
		}
		if(rightArrowKeyPressed == true) {
			x++;
		} 
	}

	private void clearScreen(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0,0,320,240);
	}
	public void draw(){
		//x++;
		//y += 2;
		BufferStrategy bf = this.getBufferStrategy();
		Graphics g = null;
		try {
			g = bf.getDrawGraphics();
			clearScreen(g);
			g.setColor(new Color(0x00,0xEE,0x00));
			g.fillRect(x,y,32,32);
		} finally {
			g.dispose();
		}
		bf.show();
		Toolkit.getDefaultToolkit().sync();
	}
    public static void main (String[] args) {
    	new GraphicsTest();         
    }

}
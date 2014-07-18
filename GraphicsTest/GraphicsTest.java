import java.awt.Frame;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Set;
import java.util.HashSet;

public class GraphicsTest extends Frame {

	private int x = 0;
	private int y = 0;
	private boolean rightArrowKeyPressed = false;
	private boolean running = false;
	private Set<Integer> keysPressed = new HashSet<Integer>();

	public GraphicsTest(){
		this.setBackground(new Color(0x38,0x8E,0x8E));
        this.addWindowListener(new GraphicsTestWindowAdapter(this));
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

	public void update() {
		if(keysPressed.contains(KeyEvent.VK_ESCAPE)) {
			running = false;
		}
		if(keysPressed.contains(KeyEvent.VK_RIGHT)) {
			x++;
		}
		if(keysPressed.contains(KeyEvent.VK_LEFT)) {
			x--;
		}
		if(keysPressed.contains(KeyEvent.VK_DOWN)) {
			y++;
		}
		if(keysPressed.contains(KeyEvent.VK_UP)) {
			y--;
		}
		if(running == false) {
			System.exit(0);
		} 
	}

	public void setKeyPressed(int keyCode) {
		keysPressed.add(keyCode);
	}

	public void setKeyReleased(int keyCode) {
		keysPressed.remove(keyCode);
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
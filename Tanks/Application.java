import java.awt.Frame;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Set;
import java.util.HashSet;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Application extends Frame {

	private List<GameState> states = new ArrayList<GameState>();
	private boolean running = false;
	private Set<Integer> keysPressed = new HashSet<Integer>();
	private Set<Integer> previousKeysPressed = new HashSet<Integer>();
	private GameState state;
	
	public Application(){
		
		GameState tanksSession = new TanksSession(this);
		states.add(tanksSession);
		GameState menu = new Menu(this);
		states.add(menu);
		switchState(Menu.class);

		this.addWindowListener(new TanksWindowAdapter(this));
        this.addKeyListener(new TanksKeyAdapter(this));
        this.setSize(320, 240);
        this.setUndecorated(true);
        this.setVisible(true);
        this.createBufferStrategy(2);

        Timer timer = new Timer(20, new TanksActionListener(this));
        running = true;
        timer.start();
	}

	public void switchState(Class clazz){
		for(GameState state : states) {
			if(clazz.isInstance(state)){
				this.state = state;
			}
		}
		keysPressed.clear();
	}

	public void draw(){
		BufferStrategy bf = this.getBufferStrategy();
		Graphics g = null;
		try {
			g = bf.getDrawGraphics();
			state.draw(g);
		} finally {
			g.dispose();
		}
		bf.show();
		Toolkit.getDefaultToolkit().sync();
	}

	public void update() {
		
		state.update(keysPressed, previousKeysPressed);
		if(running == false) {
			System.exit(0);
		}
		previousKeysPressed.clear();
		for(Integer keyCode : keysPressed) {
			previousKeysPressed.add(keyCode);
		}
	}

	public void setRunning(boolean running){
		this.running = running;
	}

	private boolean keyJustPressed(int keyCode) {
		return keysPressed.contains(keyCode) && !previousKeysPressed.contains(keyCode);
	}

	public void setKeyPressed(int keyCode) {
		keysPressed.add(keyCode);
	}

	public void setKeyReleased(int keyCode) {
		keysPressed.remove(keyCode);
	}

    public static void main (String[] args) {
    	new Application();         
    }

}
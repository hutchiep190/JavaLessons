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

public class GraphicsTest extends Frame {

	private Image backroundImage;
	private Image tankTurretSprite;
	private Image tankSprite;
	private Image bulletSprite;
	private boolean running = false;
	private Set<Integer> keysPressed = new HashSet<Integer>();
	private Set<Integer> previousKeysPressed = new HashSet<Integer>();
	private List<Bullet> bullets = new ArrayList<Bullet>();
	private List<Tank> tanks = new ArrayList<Tank>();
	private Tank player;

	public GraphicsTest(){
		
		backroundImage = Utils.loadImage("tan-camo.png");
		bulletSprite = Utils.loadImage("bulletsprites.png");
		tankTurretSprite = Utils.loadImage("tankTurretSprite.png");
		tankSprite = Utils.loadImage("tankSprite.png");

		player = new Tank(0, 0, 0, tankSprite, tankTurretSprite);
		tanks.add(player);
        this.addWindowListener(new GraphicsTestWindowAdapter(this));
        this.addKeyListener(new GraphicsTestKeyAdapter(this));
        this.setSize(320, 240);
        this.setUndecorated(true);
        this.setVisible(true);
        this.createBufferStrategy(2);

        Timer timer = new Timer(20, new GraphicsTestActionListener(this));
        running = true;
        timer.start();
	}

	public void setRunning(boolean running){
		this.running = running;
	}

	private boolean keyJustPressed(int keyCode) {
		return keysPressed.contains(keyCode) && !previousKeysPressed.contains(keyCode);
	}

	public void update() {
		for(int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			if(bullet == null){
				continue;
			}
			if(bullet.getX() > 640 || bullet.getX() < -320 || bullet.getY() > 480 || bullet.getY() < -240) {
				bullets.set(i, null);
			}
		}
		bullets.removeAll(Collections.singleton(null));

		if(keysPressed.contains(KeyEvent.VK_ESCAPE)) {
			running = false;
		}
		if(keysPressed.contains(KeyEvent.VK_RIGHT)) {
			player.moveRight();
		}
		if(keysPressed.contains(KeyEvent.VK_LEFT)) {
			player.moveLeft();
		}
		if(keysPressed.contains(KeyEvent.VK_DOWN)) {
			player.moveDown();
		}
		if(keysPressed.contains(KeyEvent.VK_UP)) {
			player.moveUp();
		}
		if(keyJustPressed(KeyEvent.VK_W)) {
			player.setTurretDirection(0);
			bullets.add(new Bullet(bulletSprite, player.getX()+11, player.getY()-13, 0));
		}
		if(keyJustPressed(KeyEvent.VK_A)) {
			player.setTurretDirection(1);
			bullets.add(new Bullet(bulletSprite, player.getX()-13, player.getY()+11, 1));
		}
		if(keyJustPressed(KeyEvent.VK_S)) {
			player.setTurretDirection(2);
			bullets.add(new Bullet(bulletSprite, player.getX()+11, player.getY()+32, 2));
		}
		if(keyJustPressed(KeyEvent.VK_D)) {
			player.setTurretDirection(3);
			bullets.add(new Bullet(bulletSprite, player.getX()+32, player.getY()+11, 3));
		}
		for(Bullet bullet : bullets){
			bullet.update();
		}
		if(running == false) {
			System.exit(0);
		}
		previousKeysPressed.clear();
		for(Integer keyCode : keysPressed) {
			previousKeysPressed.add(keyCode);
		}
	}

	public void setKeyPressed(int keyCode) {
		keysPressed.add(keyCode);
	}

	public void setKeyReleased(int keyCode) {
		keysPressed.remove(keyCode);
	}
	private void clearScreen(Graphics g){
		Utils.drawSprite(g, backroundImage, 0, 0, 320, 240, 0, 0);
	}
	public void draw(){
		BufferStrategy bf = this.getBufferStrategy();
		Graphics g = null;
		try {
			g = bf.getDrawGraphics();
			clearScreen(g);
			for(Tank tank : tanks) {
				tank.draw(g);
			}
			for(Bullet bullet : bullets) {
				bullet.draw(g);
			}
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
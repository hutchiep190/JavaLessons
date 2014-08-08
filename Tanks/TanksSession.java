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

public class TanksSession implements GameState {

	private Application app;
	private Image backroundImage;
	private Image tankTurretSprite;
	private Image tankSprite;
	private Image bulletSprite;
	private List<Bullet> bullets = new ArrayList<Bullet>();
	private List<Tank> tanks = new ArrayList<Tank>();
	private Tank player;

	public TanksSession(Application app){
		
		backroundImage = Utils.loadImage("tan-camo.png");
		bulletSprite = Utils.loadImage("bulletsprites.png");
		tankTurretSprite = Utils.loadImage("tankTurretSprite.png");
		tankSprite = Utils.loadImage("tankSprite.png");

		player = new Tank(50, 0, Direction.RIGHT, tankSprite, tankTurretSprite);
		tanks.add(player);
		tanks.add(new Tank(200, 50, Direction.LEFT, tankSprite, tankTurretSprite));
		tanks.add(new Tank(0, 200, Direction.UP, tankSprite, tankTurretSprite));
		
		this.app = app;
    }
	public void update(Set<Integer> keysPressed,Set<Integer> previousKeysPressed) {
		for(int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			if(bullet == null){
				continue;
			}
			if(!bullet.isAlive()) {
				bullets.set(i, null);
			}
		}
		bullets.removeAll(Collections.singleton(null));
		if(keysPressed.contains(KeyEvent.VK_ESCAPE)) {
            app.switchState(Menu.class);
        }

        boolean moving = false;
        int dx = 0;
        int dy = 0;

		if(keysPressed.contains(KeyEvent.VK_D)) {
			moving = true;
			dx++;
		}
		if(keysPressed.contains(KeyEvent.VK_A)) {
			moving = true;
			dx--;
		}
		if(keysPressed.contains(KeyEvent.VK_S)) {
			moving = true;
			dy++;
		}
		if(keysPressed.contains(KeyEvent.VK_W)) {
			moving = true;
			dy--;
		}
		if(dx == 0 && dy == -1) player.setDirection(Direction.UP); 
		if(dx == -1 && dy == -1) player.setDirection(Direction.UP_LEFT);
		if(dx == -1 && dy == 0) player.setDirection(Direction.LEFT); 
		if(dx == -1 && dy == 1) player.setDirection(Direction.DOWN_LEFT); 
		if(dx == 0 && dy == 1) player.setDirection(Direction.DOWN); 
		if(dx == 1 && dy == 1) player.setDirection(Direction.DOWN_RIGHT); 
		if(dx == 1 && dy == 0) player.setDirection(Direction.RIGHT); 
		if(dx == 1 && dy == -1) player.setDirection(Direction.UP_RIGHT); 
		 
		player.setMoving(moving);

		if(Utils.keyJustPressed(KeyEvent.VK_NUMPAD8, keysPressed, previousKeysPressed)) {
			player.setTurretDirection(Direction.UP);
			bullets.add(new Bullet(bulletSprite, player.getX()+11, player.getY()-13, Direction.UP));
		}
		if(Utils.keyJustPressed(KeyEvent.VK_NUMPAD4, keysPressed, previousKeysPressed)) {
			player.setTurretDirection(Direction.LEFT);
			bullets.add(new Bullet(bulletSprite, player.getX()-13, player.getY()+11, Direction.LEFT));
		}
		if(Utils.keyJustPressed(KeyEvent.VK_NUMPAD2, keysPressed, previousKeysPressed)) {
			player.setTurretDirection(Direction.DOWN);
			bullets.add(new Bullet(bulletSprite, player.getX()+11, player.getY()+32, Direction.DOWN));
		}
		if(Utils.keyJustPressed(KeyEvent.VK_NUMPAD6, keysPressed, previousKeysPressed)) {
			player.setTurretDirection(Direction.RIGHT);
			bullets.add(new Bullet(bulletSprite, player.getX()+32, player.getY()+11, Direction.RIGHT));
		}
		if(Utils.keyJustPressed(KeyEvent.VK_NUMPAD1, keysPressed, previousKeysPressed)) {
			player.setTurretDirection(Direction.DOWN_LEFT);
			bullets.add(new Bullet(bulletSprite, player.getX()-13, player.getY()+32, Direction.DOWN_LEFT));
		}
		if(Utils.keyJustPressed(KeyEvent.VK_NUMPAD3, keysPressed, previousKeysPressed)) {
			player.setTurretDirection(Direction.DOWN_RIGHT);
			bullets.add(new Bullet(bulletSprite, player.getX()+32, player.getY()+32, Direction.DOWN_RIGHT));
		}
		if(Utils.keyJustPressed(KeyEvent.VK_NUMPAD9, keysPressed, previousKeysPressed)) {
			player.setTurretDirection(Direction.UP_RIGHT);
			bullets.add(new Bullet(bulletSprite, player.getX()+32, player.getY()-13, Direction.UP_RIGHT));
		}
		if(Utils.keyJustPressed(KeyEvent.VK_NUMPAD7, keysPressed, previousKeysPressed)) {
			player.setTurretDirection(Direction.UP_LEFT);
			bullets.add(new Bullet(bulletSprite, player.getX()-13, player.getY()-13, Direction.UP_LEFT));
		}
		for(Bullet bullet : bullets){
			bullet.update(tanks);
		}
		for(Tank tank : tanks){
			tank.update(tanks);
		}
	}
	private void clearScreen(Graphics g){
		Utils.drawSprite(g, backroundImage, 0, 0, 320, 240, 0, 0);
	}
	public void draw(Graphics g){
		clearScreen(g);
		for(Tank tank : tanks) {
			tank.draw(g);
		}
		for(Bullet bullet : bullets) {
			bullet.draw(g);
		}
	}
}
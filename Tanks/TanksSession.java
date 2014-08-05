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
    private boolean tanksCollide(Tank tankA, Tank tankB) {
    	if(tankB.getX() - tankA.getX() >= 32) {
    		return false;
    	}
    	if(tankA.getX() - tankB.getX() >= 32) {
    		return false;
    	}
    	if(tankB.getY() - tankA.getY() >= 32) {
    		return false;
    	}
    	if(tankA.getY() - tankB.getY() >= 32) {
    		return false;
    	}
    	return true;
    }
    private boolean tanksCollide(Tank anyTank, List<Tank> tanks) {
    	for(Tank tank : tanks) {
    		if(tank == anyTank) {
    			continue;
    		}
    		if(tanksCollide(tank, anyTank)) {
    			return true;
    		}
    	}
    	return false;
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
			if(bullet.getX() > 640 || bullet.getX() < -320 || bullet.getY() > 480 || bullet.getY() < -240) {
				bullets.set(i, null);
			}
		}
		bullets.removeAll(Collections.singleton(null));

		if(keysPressed.contains(KeyEvent.VK_ESCAPE)) {
            app.switchState(Menu.class);
        }
		if(keysPressed.contains(KeyEvent.VK_RIGHT)) {
			player.moveRight();
			if(tanksCollide(player, tanks)){
				player.moveLeft();
			}
		}
		if(keysPressed.contains(KeyEvent.VK_LEFT)) {
			player.moveLeft();
			if(tanksCollide(player, tanks)){
				player.moveRight();
			}
		}
		if(keysPressed.contains(KeyEvent.VK_DOWN)) {
			player.moveDown();
			if(tanksCollide(player, tanks)){
				player.moveUp();
			}
		}
		if(keysPressed.contains(KeyEvent.VK_UP)) {
			player.moveUp();
			if(tanksCollide(player, tanks)){
				player.moveDown();
			}
		}	

		if(Utils.keyJustPressed(KeyEvent.VK_W, keysPressed, previousKeysPressed)) {
			player.setTurretDirection(Direction.UP);
			bullets.add(new Bullet(bulletSprite, player.getX()+11, player.getY()-13, Direction.UP));
		}
		if(Utils.keyJustPressed(KeyEvent.VK_A, keysPressed, previousKeysPressed)) {
			player.setTurretDirection(Direction.LEFT);
			bullets.add(new Bullet(bulletSprite, player.getX()-13, player.getY()+11, Direction.LEFT));
		}
		if(Utils.keyJustPressed(KeyEvent.VK_S, keysPressed, previousKeysPressed)) {
			player.setTurretDirection(Direction.DOWN);
			bullets.add(new Bullet(bulletSprite, player.getX()+11, player.getY()+32, Direction.DOWN));
		}
		if(Utils.keyJustPressed(KeyEvent.VK_D, keysPressed, previousKeysPressed)) {
			player.setTurretDirection(Direction.RIGHT);
			bullets.add(new Bullet(bulletSprite, player.getX()+32, player.getY()+11, Direction.RIGHT));
		}
		for(Bullet bullet : bullets){
			bullet.update(tanks);
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